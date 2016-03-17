package zgq.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zgq.domain.User;
import zgq.service.BusinessService;
import zgq.utils.WebUtils;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得参数
		String method = request.getParameter("method");
		if ("list".equals(method)) {
			// 查看所有员工
			list(request, response);
		} else if ("addUI".equals(method)) {
			request.getRequestDispatcher(
					"/WEB-INF/jsp/manager/user/addUser.jsp").forward(request,
					response);
		} else if ("add".equals(method)) {
			// 添加员工
			add(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
		} else if ("updateUI".equals(method)) {
			updateUI(request, response);
		}
		else if ("update".equals(method)) {
			update(request, response);
		} 
		else if ("addRoleUI".equals(method)) {
			addRoleUI(request, response);
		} 
		else if ("addRole".equals(method)) {
			addRole(request, response);
		}else {
			request.setAttribute("message", "错误了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void addRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 为某个用户修改角色
		// 获得 userid
		String userid = request.getParameter("userid");
		// 获得用户选择的所有角色
		String[] roleIds = request.getParameterValues("role");
		
		BusinessService service = new BusinessService();
		boolean b = service.addUserRole(userid, roleIds);
		
		if (b) {
			request.setAttribute("message", "分配成功了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "分配错误了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	
	private void addRoleUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 获得 用户user  获得系统角色 获得已分配角色
		String userid = request.getParameter("id");
		
		BusinessService service = new BusinessService();
		User user = service.findUser(userid);
		List userRoles = service.findUserRoles(userid);
		List systemRoles = service.getAllRoles();
		
		// 带数据给jsp
		request.setAttribute("user", user);
		request.setAttribute("userRoles", userRoles);
		request.setAttribute("systemRoles", systemRoles);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/user/addRole.jsp").forward(request, response);
		
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 修改用户
		// 请求参数封装到bean
		User user = WebUtils.request2Bean(request, User.class);
		
		//找Service做修改
		BusinessService service=new BusinessService();
		boolean b=service.updateUser(user);
		if (b) {
			request.setAttribute("message", "修改成功了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "修改错误了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void updateUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// 根据id找到对应的user
		BusinessService service = new BusinessService();
		User user = service.findUser(id);
		// 存入Request域，带给jsp
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/user/update.jsp")
				.forward(request, response);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 删除用户
		// 先获取id
		String id = request.getParameter("id");

		BusinessService service = new BusinessService();
		boolean b = service.deleteUser(id);

		if (b) {
			request.setAttribute("message", "删除成功了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除错误了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 添加用户
		// 请求参数封装到bean
		User bean = WebUtils.request2Bean(request, User.class);

		// 找Service做添加
		BusinessService service = new BusinessService();
		boolean b = service.insertUser(bean);
		if (b) {
			request.setAttribute("message", "添加成功了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "添加错误了");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查找所有的用户
		BusinessService businessService = new BusinessService();
		List list = businessService.getAllUsers();
		request.setAttribute("users", list);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/user/listuser.jsp")
				.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
