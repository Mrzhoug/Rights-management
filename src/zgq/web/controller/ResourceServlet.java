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
		// 获得参数
		String method = request.getParameter("method");
		if("list".equals(method)) {
			// 查看所有的资源url
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
			forward(request, response, "此功能暂未开发");
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 修改角色
		// 封装到bean
		Resource resource = WebUtils.request2Bean(request, Resource.class);
		
		// 找service做修改
		BusinessService service = new BusinessService();
		boolean b = service.updateResource(resource);
		if(b) 
			forward(request, response, "修改成功！！");
		else
			forward(request, response, "修改失败！！！");
	}

	private void updateUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 发送修改页面  需要回显
		String id = request.getParameter("id");
		// 根据id找到对应的resource
		BusinessService service = new BusinessService();
		Resource resource = service.findResource(id);
		// 存入request域 带给jsp
		request.setAttribute("resource", resource);
		request.getRequestDispatcher("/WEB-INF/jsp/manager/resource/update.jsp").forward(request, response);
	
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 删除
		// 获得id
		String id = request.getParameter("id");
		
		// 找service
		BusinessService service = new BusinessService();
		boolean b = service.deleteResource(id);
		if(b) 
			forward(request, response, "删除成功！！");
		else
			forward(request, response, "删除失败！！！");
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 查找所有的角色
		BusinessService service = new BusinessService();
		List resources = service.getAllResources();
		request.setAttribute("resources", resources);
		// 转发 给 jsp显示
		request.getRequestDispatcher("/WEB-INF/jsp/manager/resource/listResource.jsp").forward(request, response);
	}
	 
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 添加角色
		// 请求参数封装到bean
		Resource bean = WebUtils.request2Bean(request, Resource.class);
		
		// 找service做添加
		BusinessService service = new BusinessService();
		
		boolean b = service.insertResource(bean);
		if(b) 
			forward(request, response, "添加成功！！");
		else
			forward(request, response, "添加失败！！！");
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
