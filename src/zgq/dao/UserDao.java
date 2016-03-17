package zgq.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import zgq.domain.Role;
import zgq.domain.User;
import zgq.exception.DaoException;
import zgq.utils.JdbcUtils;

public class UserDao {
	//���������û�
	public List getAll(){
		
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from users";
			List list=(List) runner.query(sql, new BeanListHandler(User.class));
			return list;
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}
	//����id�����û�
	public User find(String id){
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from users where id=?";
			User user= (User) runner.query(sql, new BeanHandler(User.class),new Object[]{id});
			return user;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	
	//����û�
	public boolean insert(User user){
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into users(id, username, password) values(?,?,?)";
			int count=runner.update(sql,new Object[]{user.getId(),user.getUsername(),user.getPassword()});
			if(count>0)
				return true;
			else return false;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
	}
	
	//�޸��û�
	public boolean update(User user){
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update users set username=?,password=? where id =?";
			int count=runner.update(sql,new Object[]{user.getUsername(),user.getPassword(),user.getId()});
			if(count>0)
				return true;
			else return false;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	//ɾ���û�
	public boolean delete(String id){
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from users where id =?";
			int count=runner.update(sql,new Object[]{id});
			if(count>0)
				return true;
			else return false;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public List findUserRoles(String userid) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select r.* from roles r,users_roles ur where r.id=ur.roles_id and ur.users_id=? ";
			List list = (List) runner.query(sql, new BeanListHandler(Role.class), new Object[]{userid});
			return list;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public boolean addUserRole(String userid, String[] roleIds) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			// ɾ�����еĽ�ɫ
			String sql = "delete from users_roles where users_id=?";
			runner.update(sql, userid);
			
			// ����µĽ�ɫ
			sql = "insert into users_roles (users_id, roles_id) values(?,?)";
			
			Object[][] params = new Object[roleIds.length][2];
			for(int i=0; i<roleIds.length; i++) {
				params[i][0] = userid;
				params[i][1] = roleIds[i];
			}
			int[] arr = runner.batch(sql, params);
			boolean b = true;
			for(int num : arr)
				if(num==0)
					b = false;
			return b;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	public User find(String username, String password) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			// �����û�����������user
			String sql = "select * from users where username=? and password=?";
			
			User user = (User) runner.query(sql, new BeanHandler(User.class), new Object[]{username, password});
			
			return user;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
