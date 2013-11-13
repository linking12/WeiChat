package net.chat.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class WeiChatFreeMarkerView extends FreeMarkerView {
	@Override
	protected void doRender(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Expose model to JSP tags (as request attributes).
		exposeModelAsRequestAttributes(model, request);
		// Expose all standard FreeMarker hash models.
		SimpleHash fmModel = buildTemplateModel(model, request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("Rendering FreeMarker template [" + getUrl()
					+ "] in FreeMarkerView '" + getBeanName() + "'");
		}
		// Grab the locale-specific version of the template.
		Locale locale = RequestContextUtils.getLocale(request);

		/*
		 * 默认生成静态文件,除非在编写ModelAndView时指定CREATE_HTML = false, 这样对静态文件生成的粒度控制更细一点
		 * 例如：ModelAndView mav = new ModelAndView("search");
		 * mav.addObject("CREATE_HTML", false);
		 */
		if (Boolean.FALSE.equals(model.get("CREATE_STATIC_HTML"))) {
			processTemplate(getTemplate(locale), fmModel, response);
		} else {
			createHTML(getTemplate(locale), fmModel, request, response);
		}
	}

	public void createHTML(Template template, SimpleHash model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, TemplateException, ServletException {
		// 站点根目录的绝对路径
		String basePath = request.getSession().getServletContext()
				.getRealPath("/");
		String requestHTML = this.getRequestHTML(request);
		// 静态页面绝对路径
		String htmlPath = basePath +"/WEB-INF/resources/html"+requestHTML;

		File htmlFile = new File(htmlPath);
		if (!htmlFile.getParentFile().exists()) {
			htmlFile.getParentFile().mkdirs();
		}
		if (!htmlFile.exists()) {
			htmlFile.createNewFile();
		}
		Writer out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(htmlFile), "UTF-8"));
		// 处理模版
		template.process(model, out);
		out.flush();
		out.close();
		
		String htmlURL = "/html"+requestHTML;

		/* 将请求转发到生成的htm文件 */
		request.getRequestDispatcher(htmlURL).forward(request, response);
	}

	/**
	 * 计算要生成的静态文件相对路径 因为大家在调试的时候一般在Tomcat的webapps下面新建站点目录的，
	 * 但在实际应用时直接布署到ROOT目录里面,这里要保证路径的一致性。
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return /目录/*.htm
	 */
	private String getRequestHTML(HttpServletRequest request) {
		// web应用名称,部署在ROOT目录时为空
		String contextPath = request.getContextPath();
		// web应用/目录/文件.do
		String requestURI = request.getRequestURI();
		// basePath里面已经有了web应用名称，所以直接把它replace掉，以免重复
		requestURI = requestURI.replaceFirst(contextPath, "");
		// 将.do改为.htm,稍后将请求转发到此htm文件
		requestURI = requestURI + "htm.htm";

		return requestURI;
	}

}
