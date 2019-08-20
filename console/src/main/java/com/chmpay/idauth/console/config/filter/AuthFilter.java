package com.chmpay.idauth.console.config.filter;

import com.alibaba.fastjson.JSONObject;
import com.chmpay.idauth.common.contants.Contants;
import com.chmpay.idauth.common.contants.StatusCode;
import com.chmpay.idauth.common.exception.MyException;
import com.chmpay.idauth.common.util.HttpUtils;
import com.chmpay.idauth.console.service.JedisService;
import com.chmpay.idauth.console.service.impl.JedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 身份验证过滤器
 *
 * @author zhangsx
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {


    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    private JedisService jedisService;


    @Override
    public void init(FilterConfig filterConfig) {

        jedisService = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext())
                .getBean(JedisServiceImpl.class);

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 获取请求头的信息
        Enumeration<String> namePairs1 = request.getHeaderNames();
        while (namePairs1.hasMoreElements()) {
            String requestName = namePairs1.nextElement();
            String value = request.getHeader(requestName);
            logger.debug(String.format("request header [ %s = %s ]", requestName, value));
        }

        // 跨域设置
        HttpUtils.crossDomain(response);

        if (request.getMethod().equals(Contants.HTTP_METHOD_OPTIONS)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 限制10mb
        int contentlength = request.getContentLength();
        if (contentlength > Contants.HTTP_MAXCONTENTLENGTH) {
            response.getWriter().write(JSONObject.toJSONString(StatusCode.MAX_SIZE));
            return;
        }

        // 获取参数信息
        Enumeration<String> namePairs = request.getParameterNames();
        while (namePairs.hasMoreElements()) {
            String requestName = namePairs.nextElement();
            String value = request.getParameter(requestName);
            logger.debug(String.format("request parameter [ %s = %s] ", requestName, value));
        }

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("application/json;charset=utf-8");
            System.out.println("过滤器拦截到错误了");
            if (e instanceof MyException) {
                MyException ex = (MyException) e;
                response.getWriter().write(JSONObject.toJSONString(ex.getStatusCode()));
            }else {
                StatusCode statusCode = StatusCode.EXCEPTION;
                if (e instanceof javax.servlet.ServletException) {
                    if (e.getMessage() != null && e.getMessage().indexOf("MyException") != -1 && e.getMessage().indexOf(":")!= -1){
                        statusCode.setMsg(e.getMessage().substring(e.getMessage().indexOf(":") + 1));
                    }
                }
                response.getWriter().write(JSONObject.toJSONString(statusCode));
            }
        }

    }

    @Override
    public void destroy() {

    }

}
