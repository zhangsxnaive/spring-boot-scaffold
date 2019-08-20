package com.chmpay.idauth.console.util;

import com.alibaba.fastjson.JSONObject;
import com.chmpay.idauth.common.contants.Contants;
import com.chmpay.idauth.common.util.RedisUtils;
import com.chmpay.idauth.console.service.JedisService;
import com.chmpay.idauth.console.vo.LoginVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangshuxin
 * @date 2019-06-25
 */
public class TokenUtil {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * 获取当前登录的用户
     * @param jedisService
     * @return
     */
    public static LoginVo getLoginVO(JedisService jedisService) {
        String token = getRequest().getHeader("token");

        //IDAUTH 88355bc6c4003defb9cc4440cdb4f5bb
        /*

        {
    "appid": "1000000000000007",
    "email": "zhangsx@chmpay.com",
    "phone": "16602106524",
    "roleId": "1",
    "roleType": "admin",
    "userName": "zhangsx"
}

         */


        return checkLogin(token, jedisService);
    }

    /**
     * 检查是否登录
     *
     * @param token
     * @return
     */
    public static LoginVo checkLogin(String token, JedisService jedisService) {

        try {
            if (StringUtils.isEmpty(token) || jedisService == null) {
                return null;
            }
            String loginVoStr = jedisService.get(Contants.REDIS_PRFIX.concat(token));
            if (RedisUtils.isEmpty(loginVoStr)) {
                return null;
            }
            LoginVo vo = JSONObject.parseObject(loginVoStr, LoginVo.class);

            return vo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getToken() {
        return getRequest().getHeader("token");
    }

}
