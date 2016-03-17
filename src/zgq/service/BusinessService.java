package zgq.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import zgq.dao.ResourceDao;
import zgq.dao.RoleDao;
import zgq.dao.UserDao;
import zgq.domain.Resource;
import zgq.domain.Role;
import zgq.domain.User;

public class BusinessService {
	private UserDao userDao = new UserDao();
	private RoleDao roleDao = new RoleDao();
	private ResourceDao resourceDao = new ResourceDao();

	// һϵ�м򵥵�Service
	// user
	public List getAllUsers() {
		return userDao.getAll();
	}

	public User findUser(String id) {
		return userDao.find(id);
	}

	public boolean updateUser(User user) {
		return userDao.update(user);
	}

	public boolean deleteUser(String id) {
		return userDao.delete(id);
	}

	public boolean insertUser(User user) {
		return userDao.insert(user);
	}

	// role
	public List getAllRoles() {
		return roleDao.getAll();
	}

	public Role findRole(String id) {
		return roleDao.find(id);
	}

	public boolean updateRole(Role role) {
		return roleDao.update(role);
	}

	public boolean deleteRole(String id) {
		return roleDao.delete(id);
	}

	public boolean insertRole(Role role) {
		return roleDao.insert(role);
	}

	// resource
	public List getAllResources() {
		return resourceDao.getAll();
	}

	public Resource findResource(String id) {
		return resourceDao.find(id);
	}

	public boolean updateResource(Resource resource) {
		return resourceDao.update(resource);
	}

	public boolean deleteResource(String id) {
		return resourceDao.delete(id);
	}

	public boolean insertResource(Resource resource) {
		return resourceDao.insert(resource);
	}

	// �ҳ���ɫ��Ӧ������Ȩ��
	public List findRoleResources(String id) {

		return roleDao.findRoleResources(id);
	}

	// Ϊ��ɫ����Ȩ��
	public boolean addRoleResources(String[] resourceIds, String roleid) {
		// ɾ�����е�Ȩ��

		return roleDao.addRoleResources(resourceIds, roleid);
	}

	public List findUserRoles(String userid) {
		// TODO Auto-generated method stub
		return userDao.findUserRoles(userid);
	}

	public boolean addUserRole(String userid, String[] roleIds) {
		// TODO Auto-generated method stub
		return userDao.addUserRole(userid, roleIds);
	}

	public List findUserResource(String id) {
		// �����û���Ӧ������Ȩ��
		// 1. ����û������н�ɫ
		List<Role> userRoles = userDao.findUserRoles(id);
		// 2. �������еĽ�ɫ��Ȩ��
		Set userResources = new HashSet();
		for (Role role : userRoles) {
			List roleResources = roleDao.findRoleResources(role.getId());
			userResources.addAll(roleResources);
		}
		List list = new ArrayList();
		list.addAll(userResources);
		return list;
	}

	public User loginUser(String username, String password) {
		// ����dao
		// ͨ�� username ����user
		User bean = userDao.find(username, password);
		return bean;
	}

}
