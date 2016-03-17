package zgq.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zgq.domain.Resource;
import zgq.service.BusinessService;
import zgq.utils.WebUtils;

public class ResourceServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ò���
		String method = request.getParameter("method");
		if("list".equals(method)) {
			// �鿴���е���Դurl
			list(request, response);
		} else if("addUI".equals(method)) {
			request.getRequestDispatcher("/WEB-INF/jsp/manager/resource/addResource.jsp").forward(request, response);
		} else if("add".equals(method)) {
			add(request, response);
		} else if("delete".equals(method)) {
			delete(request, response);
		} else if("updateUI".equals(method)) {
			updateUI(request, response);
		} else if("update".equals(method)) {
			update(request, response);
		} else {
			forward(request, response, "�˹�����δ����");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �޸Ľ�ɫ
		// ��װ��bean
		Resource resource = WebUtils.request2Bean(request, Resource.class);
		
		// ��service���޸�
		BusinessService service = new BusinessService();
		boolean b = service.updateResource(resource);
		if(b) 
			forward(request, response, "�޸ĳɹ�����");
		else
			forward(request, response, "�޸�ʧ�ܣ�����");
	}

	private void updateUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// �����޸�ҳ��  ��Ҫ����
		String id = request.getParameter("id");
		// ����id�ҵ���Ӧ��resource
		BusinessService service = new BusinessService();
		Resource resource = service.findResource(id);
		// ����request�� ����jsp
		request.setAttribute("resource", resource);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/resource/update.jsp").forward(request, response);
	
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ɾ��
		// ���id
		String id = request.getParameter("id");
		
		// ��service
		BusinessService service = new BusinessService();
		boolean b = service.deleteResource(id);
		if(b) 
			forward(request, response, "ɾ���ɹ�����");
		else
			forward(request, response, "ɾ��ʧ�ܣ�����");
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �������еĽ�ɫ
		BusinessService service = new BusinessService();
		List resources = service.getAllResources();
		request.setAttribute("resources", resources);
		// ת�� �� jsp��ʾ
		request.getRequestDispatcher("/WEB-INF/jsp/manager/resource/listResource.jsp").forward(request, response);
	}
	 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ӽ�ɫ
		// ���������װ��bean
		Resource bean = WebUtils.request2Bean(request, Resource.class);
		
		// ��service�����
		BusinessService service = new BusinessService();
		
		boolean b = service.insertResource(bean);
		if(b) 
			forward(request, response, "��ӳɹ�����");
		else
			forward(request, response, "���ʧ�ܣ�����");
	}


	private void forward(HttpServletRequest request,HttpServletResponse response, String message) 
							throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
