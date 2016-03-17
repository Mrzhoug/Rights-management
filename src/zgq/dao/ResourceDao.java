package zgq.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import zgq.domain.Resource;
import zgq.exception.DaoException;
import zgq.utils.JdbcUtils;

public class ResourceDao {
	//查找所有资源
	public List getAll(){
		
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from resources";
			List list=(List) runner.query(sql, new BeanListHandler(Resource.class));
			return list;
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}
	//根据id查找资源
	public Resource find(String id){
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="select * from resources where id= ?";
			Resource resource= (Resource) runner.query(sql, new BeanHandler(Resource.class),new Object[]{id});
			return resource;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	
	//添加资源
	public boolean insert(Resource resource){
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="insert into resources(id, name, url) values(?,?,?)";
			int count=runner.update(sql,new Object[]{resource.getId(),resource.getName(),resource.getUrl()});
			if(count>0)
				return true;
			else return false;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
	}
	
	//修改资源
	public boolean update(Resource resource){
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="update resources set name=?,url=? where id =?";
			int count=runner.update(sql,new Object[]{resource.getName(),resource.getUrl(),resource.getId()});
			if(count>0)
				return true;
			else return false;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}
	
	//删除资源
	public boolean delete(String id){
		try {
			QueryRunner runner=new QueryRunner(JdbcUtils.getDataSource());
			String sql="delete from resources where id =?";
			int count=runner.update(sql,new Object[]{id});
			if(count>0)
				return true;
			else return false;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}


}
