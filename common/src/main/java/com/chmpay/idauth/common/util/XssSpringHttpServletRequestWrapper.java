package com.chmpay.idauth.common.util;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssSpringHttpServletRequestWrapper extends
		HttpServletRequestWrapper {

	public XssSpringHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 对数组参数进行特殊字符过滤
	 */
	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		String[] newValues = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			// spring的HtmlUtils进行转义
			newValues[i] = HtmlUtils.htmlEscape(values[i]);
		}
		return newValues;
	}

	/**
	 * 拦截参数,并对其进行字符转义
	 */
	@Override
	public String getParameter(String name) {
		return HtmlUtils.htmlEscape(name);
	}

	/**
	 * 拦截参数,并对其进行字符转义
	 */
	@Override
	public Object getAttribute(String name) {
		return HtmlUtils.htmlEscape(name);
	}

}