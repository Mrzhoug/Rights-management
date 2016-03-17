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
		// ��ò���
		String method = request.getParameter("method");
		if ("list".equals(method)) {
			// �鿴���еĽ�ɫ
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
			forward(request, response, "�˹�����δ����");
		}
	}

	private void addResourceUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �ṩ����Ȩ�޵�ҳ��
		BusinessService service = new BusinessService();
		// ����id�����ɫ
		String id = request.getParameter("id");
		Role role = service.findRole(id);
		// �鴦��ɫ�����Ȩ��
		List roleResources = service.findRoleResources(id);
		// ���ϵͳ���е�Ȩ��(��Դ)
		List systemResources = service.getAllResources();

		// ���ݴ���request�����jsp
		request.setAttribute("role", role);
	    request.setAttribute("roleResources", roleResources);
		request.setAttribute("systemResources", systemResources);
		request.getRequestDispatcher(
				"/WEB-INF/jsp/manager/role/addResource.jsp").forward(request,
				response);
	}

	private void addResource(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Ϊ��ɫ����Ȩ��
		// ����û�ѡ���Ȩ��id ��ý�ɫ��id
		String[] resourceIds = request.getParameterValues("resource");
		String roleid = request.getParameter("roleid");
		BusinessService service = new BusinessService();
		boolean b = service.addRoleResources(resourceIds, roleid);
		if (b)
			forward(request, response, "����Ȩ�޳ɹ�����");
		else
			forward(request, response, "����Ȩ��ʧ�ܣ�����");
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �޸Ľ�ɫ
		// ��װ��bean
		Role role = WebUtils.request2Bean(request, Role.class);
		// ��service���޸�
		BusinessService service = new BusinessService();
		boolean b = service.updateRole(role);
		if (b)
			forward(request, response, "�޸ĳɹ�����");
		else
			forward(request, response, "�޸�ʧ�ܣ�����");
	}

	private void updateUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �����޸�ҳ�� ��Ҫ����
		String id = request.getParameter("id");
		// ����id�ҵ���Ӧ��role
		BusinessService service = new BusinessService();
		Role role = service.findRole(id);
		// ����request�� ����jsp
		request.setAttribute("role", role);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/role/update.jsp")
				.forward(request, response);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ɾ����ɫ
		// ���id
		String id = request.getParameter("id");

		// ��service
		BusinessService service = new BusinessService();
		boolean b = service.deleteRole(id);
		if (b)
			forward(request, response, "ɾ���ɹ�����");
		else
			forward(request, response, "ɾ��ʧ�ܣ�����");
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������еĽ�ɫ
		BusinessService service = new BusinessService();
		List roles = service.getAllRoles();
		request.setAttribute("roles", roles);
		// ת�� �� jsp��ʾ
		request.getRequestDispatcher("/WEB-INF/jsp/manager/role/listrole.jsp")
				.forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ӽ�ɫ

		// ���������װ��bean
		Role bean = WebUtils.request2Bean(request, Role.class);

		// ��service�����
		BusinessService service = new BusinessService();

		boolean b = service.insertRole(bean);
		if (b)
			forward(request, response, "��ӳɹ�����");
		else
			forward(request, response, "���ʧ�ܣ�����");
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
