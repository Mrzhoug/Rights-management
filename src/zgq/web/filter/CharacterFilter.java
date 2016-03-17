package zgq.web.filter;

//解决字符串乱码问题
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter extends HttpFilter {

	private FilterConfig config;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	@Override
	public void doFilter(HttpServletRequest request,HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 设置 request 和 response的编码
		String encoding = this.config.getInitParameter("encoding");
		String serverEncoding = this.config.getInitParameter("serverEncoding");
		
		response.setContentType("text/html;charset="+encoding);
		// 对于 get 方式提交的参数无效
		// 乱码问题出现与 getParameter 方法，  需要用 包装设计模式
		// 放行的时候 传入包装的request
		if(!encoding.equals(serverEncoding)) {
			// 说明 服务器使用的编码和我指定的编码不一致
			request.setCharacterEncoding(encoding);	// 解决 post 方式
			request = new MyHttpServletRequest(request, serverEncoding);	// 解决get方式
		}
		chain.doFilter(request, response);
	}

}

class MyHttpServletRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	private String serverEncoding;
	public MyHttpServletRequest(HttpServletRequest request, String serverEncoding) {
		super(request);
		this.request = request;
		this.serverEncoding = serverEncoding;
	}

	@Override
	public String getParameter(String name) {
		
		try {
			// 1. 调用被包装的 request.getParameter
			String value = this.request.getParameter(name);
			// 判断请求方式
			String method = this.request.getMethod();
			if ("get".equalsIgnoreCase(method)) {
				// 先编码 再解码
				value = new String(value.getBytes(this.serverEncoding), request.getCharacterEncoding());
			}
			return value;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}