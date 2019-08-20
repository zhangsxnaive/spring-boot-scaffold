package com.chmpay.idauth.console.config.interceptor;

import com.chmpay.idauth.common.annotation.PassToken;
import com.chmpay.idauth.common.annotation.RoleType;
import com.chmpay.idauth.common.annotation.UserLoginToken;
import com.chmpay.idauth.common.contants.Contants;
import com.chmpay.idauth.common.contants.StatusCode;
import com.chmpay.idauth.common.exception.MyException;
import com.chmpay.idauth.common.util.ParamsUtils;
import com.chmpay.idauth.console.config.IgnoreUrlsProp;
import com.chmpay.idauth.console.model.Resource;
import com.chmpay.idauth.console.service.JedisService;
import com.chmpay.idauth.console.service.ResourceService;
import com.chmpay.idauth.console.util.TokenUtil;
import com.chmpay.idauth.console.vo.LoginVo;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshuxin
 * @date 2019-06-25
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisService jedisService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private IgnoreUrlsProp ignoreUrlsProp;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("URI------------->" + request.getRequestURI());
        //System.out.println("preHandle HTTP STATUS---------->" + response.getStatus());
        if (ignoreUrlsProp.getIgnoreList().contains(request.getRequestURI())) {
            return true;
        }
        if (request.getRequestURI().startsWith("/idauth/swagger")) {
            return true;
        }
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");

        // 如果不是请求 controller 映射方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //System.out.println("method----------->" + method.getName());
        // 检查 method 是否有passToken注解，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            return true;
        }
        LoginVo loginVo = TokenUtil.checkLogin(token, jedisService);
        if (loginVo == null) {
            throw new MyException(StatusCode.NOT_LOGIN);
        }
        String roleType = loginVo.getRoleType();
        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (token == null) {
                throw new MyException("20", "无token，请重新登录");
            }
            RoleType roleTypeEnum = userLoginToken.role();

            boolean checkRoleAnnotation = checkRoleAnnotation(roleTypeEnum, roleType);
            //System.out.println("checkRoleAnnotation------------->" + checkRoleAnnotation);
            return checkRoleAnnotation;
        }

        // 如果两个注解都没有的话，从数据库检查角色资源对应关系
        boolean validate = validate(loginVo, resourceService, request.getRequestURI());
        if (validate) {
            return true;
        } else {
            if (!"/idauth/error".equals(request.getRequestURI())) {
                response.sendError(HttpStatus.SC_UNAUTHORIZED);
            }
            return false;
        }
    }

    public boolean checkRoleAnnotation(RoleType roleTypeEnum, String roleType) {
        if (roleTypeEnum == RoleType.ALL) {
            return !StringUtils.isEmpty(roleType);
        } else if (roleTypeEnum == RoleType.ADMIN) {
            return Arrays.asList(Contants.ADMIN_TYPE).contains(roleType);
        } else if (roleTypeEnum == RoleType.AGREEMENT) {
            return Arrays.asList(Contants.USER_TYPE_AGREEMENT).contains(roleType);
        } else if (roleTypeEnum == RoleType.NORMAL) {
            return Contants.ROLE_TYPE_NORMAL.equals(roleType);
        } else if (roleTypeEnum == RoleType.AGENT) {
            return Contants.ROLE_TYPE_AGENT.equals(roleType);
        } else if (roleTypeEnum == RoleType.SERVICE) {
            return Contants.ROLE_TYPE_SERVICE.equals(roleType);
        } else {
            return false;
        }
    }

    /**
     * 验证登录,若登录再验证权限
     *
     * @param vo
     * @param url 访问的URI
     * @return
     */
    public static boolean validate(LoginVo vo, ResourceService resourceService, String url) throws MyException {
        String roleId = vo.getRoleId();

        String[] names = {"role_id"};
        String[] values = {vo.getRoleId()};
        Map<String, Object> params = new HashMap<>();
        ParamsUtils.collectMap(params, names, values);
        List<Resource> resourceList = resourceService.findByRoleId(roleId);
        boolean isAllow = false;

        for (Resource resource :
                resourceList) {
            if (url.endsWith(resource.getResourceUrl())) {
                isAllow = true;
                break;
            }
        }
        return isAllow;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion HTTP STATUS---------------->" + response.getStatus());
    }
}
