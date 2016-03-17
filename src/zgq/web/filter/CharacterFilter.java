package zgq.web.filter;

//����ַ�����������
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
		// ���� request �� response�ı���
		String encoding = this.config.getInitParameter("encoding");
		String serverEncoding = this.config.getInitParameter("serverEncoding");
		
		response.setContentType("text/html;charset="+encoding);
		// ���� get ��ʽ�ύ�Ĳ�����Ч
		// ������������� getParameter ������  ��Ҫ�� ��װ���ģʽ
		// ���е�ʱ�� �����װ��request
		if(!encoding.equals(serverEncoding)) {
			// ˵�� ������ʹ�õı������ָ���ı��벻һ��
			request.setCharacterEncoding(encoding);	// ��� post ��ʽ
			request = new MyHttpServletRequest(request, serverEncoding);	// ���get��ʽ
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
			// 1. ���ñ���װ�� request.getParameter
			String value = this.request.getParameter(name);
			// �ж�����ʽ
			String method = this.request.getMethod();
			if ("get".equalsIgnoreCase(method)) {
				// �ȱ��� �ٽ���
				value = new String(value.getBytes(this.serverEncoding), request.getCharacterEncoding());
			}
			return value;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}