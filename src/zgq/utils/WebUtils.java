package zgq.utils;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
	private WebUtils(){}
	
	public static<T> T request2Bean(HttpServletRequest request, Class<T> clazz) {
		try {
			T bean = clazz.newInstance();
			// 添加id  如果是带着id来的说明是修改  也就是说 没有id才需要添加新的
			if(request.getParameter("id")==null) {
				String id = UUID.randomUUID().toString();
				BeanUtils.setProperty(bean, "id", id);
			}
			// 批量封装bean
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String value = request.getParameter(name);
				BeanUtils.setProperty(bean, name, value);
			}
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
