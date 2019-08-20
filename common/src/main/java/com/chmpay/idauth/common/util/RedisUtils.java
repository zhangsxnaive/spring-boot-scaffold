package com.chmpay.idauth.common.util;

import org.apache.commons.lang.StringUtils;

public class RedisUtils {

    public static boolean isEmpty(String val) {
        if (StringUtils.isEmpty(val) || "nil".equals(val) || "null".equals(val.toLowerCase())) {
            return true;
        }
        return false;
    }
}
