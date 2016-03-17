package zgq.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import zgq.domain.Resource;
import zgq.domain.Role;
import zgq.exception.DaoException;
import zgq.utils.JdbcUtils;

public class RoleDao {
	// 查找所有角色
	public List getAll() {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from roles";
			List list = (List) runner.query(sql,
					new BeanListHandler(Role.class));
			return list;
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	// 根据id查找角色
	public Role find(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from roles where id= ?";
			Role role = (Role) runner.query(sql, new BeanHandler(Role.class),
					new Object[] { id });
			return role;

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	// 添加角色
	public boolean insert(Role role) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into roles(id, name, description) values(?,?,?)";
			int count = runner.update(
					sql,
					new Object[] { role.getId(), role.getName(),
							role.getDescription() });
			if (count > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	// 修改角色
	public boolean update(Role role) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "update roles set name=?,description=? where id =?";
			int count = runner.update(
					sql,
					new Object[] { role.getName(), role.getDescription(),
							role.getId() });
			if (count > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	// 删除角色
	public boolean delete(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "delete from roles where id =?";
			int count = runner.update(sql, new Object[] { id });
			if (count > 0)
				return true;
			else
				return false;

		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	// 根据role 的 id 找出 resources
	public List findRoleResources(String roleid) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select res.* from resources res,roles_resources rr where res.id=rr.resources_id and rr.roles_id=?";

			List list = (List) runner.query(sql, new BeanListHandler(
					Resource.class), new Object[] { roleid });
			return list;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public boolean addRoleResources(String[] resourceIds, String roleid) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			// 删除所有已分配的权限
			String sql = "delete from roles_resources where roles_id=?";
			int count = runner.update(sql, roleid);

			// 有可能插入多条记录
			// 批处理
			sql = "insert into roles_resources (roles_id, resources_id) values(?,?)";
			Object[][] params = new Object[resourceIds.length][2];
			for (int i = 0; i < resourceIds.length; i++) {
				params[i][0] = roleid;
				params[i][1] = resourceIds[i];
			}
			int[] arr = runner.batch(sql, params);
			boolean b = true;
			for (int num : arr)
				if (num == 0)
					b = false;
			return b;
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

}
