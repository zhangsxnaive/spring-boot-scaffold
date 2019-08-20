package com.chmpay.idauth.common.util;

import com.chmpay.idauth.common.contants.Contants;
import org.apache.commons.lang.StringUtils;

/**
 * 角色定义工具
 * @author dell
 */
public class RoleUtils {

    public static boolean isAdmin(String roleType) {
        if (StringUtils.isEmpty(roleType)) {
            return false;
        }
        for (String role : Contants.ADMIN_TYPE) {
            if (role.equals(roleType)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNormal(String normal) {
        if (StringUtils.isEmpty(normal)) {
            return false;
        }
        for (String role : Contants.USER_TYPE) {
            if (role.equals(normal)) {
                return true;
            }
        }
        return false;
    }
}
