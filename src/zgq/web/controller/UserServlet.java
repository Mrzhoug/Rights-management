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
		// ��ò���
		String method = request.getParameter("method");
		if ("list".equals(method)) {
			// �鿴����Ա��
			list(request, response);
		} else if ("addUI".equals(method)) {
			request.getRequestDispatcher(
					"/WEB-INF/jsp/manager/user/addUser.jsp").forward(request,
					response);
		} else if ("add".equals(method)) {
			// ���Ա��
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
			request.setAttribute("message", "������");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void addRole(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Ϊĳ���û��޸Ľ�ɫ
		// ��� userid
		String userid = request.getParameter("userid");
		// ����û�ѡ������н�ɫ
		String[] roleIds = request.getParameterValues("role");
		
		BusinessService service = new BusinessService();
		boolean b = service.addUserRole(userid, roleIds);
		
		if (b) {
			request.setAttribute("message", "����ɹ���");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "���������");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	
	private void addRoleUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��� �û�user  ���ϵͳ��ɫ ����ѷ����ɫ
		String userid = request.getParameter("id");
		
		BusinessService service = new BusinessService();
		User user = service.findUser(userid);
		List userRoles = service.findUserRoles(userid);
		List systemRoles = service.getAllRoles();
		
		// �����ݸ�jsp
		request.setAttribute("user", user);
		request.setAttribute("userRoles", userRoles);
		request.setAttribute("systemRoles", systemRoles);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/user/addRole.jsp").forward(request, response);
		
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �޸��û�
		// ���������װ��bean
		User user = WebUtils.request2Bean(request, User.class);
		
		//��Service���޸�
		BusinessService service=new BusinessService();
		boolean b=service.updateUser(user);
		if (b) {
			request.setAttribute("message", "�޸ĳɹ���");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "�޸Ĵ�����");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void updateUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		// ����id�ҵ���Ӧ��user
		BusinessService service = new BusinessService();
		User user = service.findUser(id);
		// ����Request�򣬴���jsp
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/user/update.jsp")
				.forward(request, response);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ɾ���û�
		// �Ȼ�ȡid
		String id = request.getParameter("id");

		BusinessService service = new BusinessService();
		boolean b = service.deleteUser(id);

		if (b) {
			request.setAttribute("message", "ɾ���ɹ���");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "ɾ��������");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����û�
		// ���������װ��bean
		User bean = WebUtils.request2Bean(request, User.class);

		// ��Service�����
		BusinessService service = new BusinessService();
		boolean b = service.insertUser(bean);
		if (b) {
			request.setAttribute("message", "��ӳɹ���");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "��Ӵ�����");
			request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(
					request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������е��û�
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
