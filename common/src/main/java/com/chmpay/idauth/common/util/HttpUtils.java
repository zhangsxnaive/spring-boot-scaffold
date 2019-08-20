package com.chmpay.idauth.common.util;

import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author zhangshuxin
 * @date 2019-06-27
 */
public class HttpUtils {

    /**
     * 跨域设置
     *
     * @param response
     */
    public static void crossDomain(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        response.addHeader("Access-Control-Max-Age", "1000");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, withcredentials, Authorization,token,version,source,_," +
                        "loginData,uniIdentifier,");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0.
        response.setHeader("Expires", "0");

        response.setHeader("Access-Control-Allow-Credentials", "true");
    }

}
