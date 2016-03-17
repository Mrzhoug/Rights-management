package zgq.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zgq.domain.Role;
import zgq.service.BusinessService;
import zgq.utils.WebUtils;

public class RoleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得参数
		String method = request.getParameter("method");
		if ("list".equals(method)) {
			// 查看所有的角色
			list(request, response);
		} else if ("addUI".equals(method)) {
			request.getRequestDispatcher(
					"/WEB-INF/jsp/manager/role/addRole.jsp").forward(request,
					response);
		} else if ("add".equals(method)) {
			add(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
		} else if ("updateUI".equals(method)) {
			updateUI(request, response);
		} else if ("update".equals(method)) {
			update(request, response);
		} else if ("addResource".equals(method)) {
			addResource(request, response);
		} else if ("addResourceUI".equals(method)) {
			addResourceUI(request, response);
		} else {
			forward(request, response, "此功能暂未开发");
		}
	}

	private void addResourceUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 提供分配权限的页面
		BusinessService service = new BusinessService();
		// 根据id查出角色
		String id = request.getParameter("id");
		Role role = service.findRole(id);
		// 查处角色分配的权限
		List roleResources = service.findRoleResources(id);
		// 查出系统所有的权限(资源)
		List systemResources = service.getAllResources();

		// 数据存入request域带给jsp
		request.setAttribute("role", role);
	    request.setAttribute("roleResources", roleResources);
		request.setAttribute("systemResources", systemResources);
		request.getRequestDispatcher(
				"/WEB-INF/jsp/manager/role/addResource.jsp").forward(request,
				response);
	}

	private void addResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 为角色分配权限
		// 获得用户选择的权限id 获得角色的id
		String[] resourceIds = request.getParameterValues("resource");
		String roleid = request.getParameter("roleid");
		BusinessService service = new BusinessService();
		boolean b = service.addRoleResources(resourceIds, roleid);
		if (b)
			forward(request, response, "分配权限成功！！");
		else
			forward(request, response, "分配权限失败！！！");
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 修改角色
		// 封装到bean
		Role role = WebUtils.request2Bean(request, Role.class);
		// 找service做修改
		BusinessService service = new BusinessService();
		boolean b = service.updateRole(role);
		if (b)
			forward(request, response, "修改成功！！");
		else
			forward(request, response, "修改失败！！！");
	}

	private void updateUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 发送修改页面 需要回显
		String id = request.getParameter("id");
		// 根据id找到对应的role
		BusinessService service = new BusinessService();
		Role role = service.findRole(id);
		// 存入request域 带给jsp
		request.setAttribute("role", role);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/role/update.jsp")
				.forward(request, response);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 删除角色
		// 获得id
		String id = request.getParameter("id");

		// 找service
		BusinessService service = new BusinessService();
		boolean b = service.deleteRole(id);
		if (b)
			forward(request, response, "删除成功！！");
		else
			forward(request, response, "删除失败！！！");
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查找所有的角色
		BusinessService service = new BusinessService();
		List roles = service.getAllRoles();
		request.setAttribute("roles", roles);
		// 转发 给 jsp显示
		request.getRequestDispatcher("/WEB-INF/jsp/manager/role/listrole.jsp")
				.forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 添加角色

		// 请求参数封装到bean
		Role bean = WebUtils.request2Bean(request, Role.class);

		// 找service做添加
		BusinessService service = new BusinessService();

		boolean b = service.insertRole(bean);
		if (b)
			forward(request, response, "添加成功！！");
		else
			forward(request, response, "添加失败！！！");
	}

	private void forward(HttpServletRequest request,
			HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
				request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
