package com.chmpay.idauth.common.util;

import com.chmpay.idauth.common.contants.StatusCode;
import com.chmpay.idauth.common.exception.MyException;
import org.apache.commons.lang.StringUtils;

public class ValidateUtils {

    /**
     * 是否存在空值,存在空值则放回空值NAMES
     *
     * @param names
     * @return
     */
    public static String isEmpty(String[] names, String[] values) throws MyException {
        if (names == null || values == null) {
            throw new MyException(StatusCode.EXCEPTION);
        }
        if (names.length != values.length) {
            throw new MyException(StatusCode.EXCEPTION);
        }
        for (int i = 0; i < names.length; i++) {
            if (StringUtils.isEmpty(values[i])) {
                return names[i];
            }
        }
        return null;
    }

    /**
     * 是否存在该值
     *
     * @param key
     * @return
     */
    public static boolean contains(String key, String[] collections) throws MyException {
        if (collections == null || collections.length == 0) {
            return false;
        }

        for (int i = 0; i < collections.length; i++) {
            if (collections[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证字符串长度
     * @param str 要验证的字符串
     * @param strLength 限制的长度
     * @param isNull 是否允许为空
     * @return
     */
    public static boolean valiStrLength(String str, Integer strLength, boolean isNull) {
        if (null != str && !"".equals(str)) {
            if (str.length() > strLength) {
                return false;
            }
            return true;
        }
        return isNull;
    }

    /**
     * 获取字符串，null返回""
     *
     * @param obj
     * @return
     */
    public static String getString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String getDateString(Object obj, String sdf) {
        if (obj == null) {
            return "";
        }
        String dateStr = getString(obj);
        long datelong = DateUtil.parseStrDate(dateStr, sdf).getTime();
        return String.valueOf(datelong);
    }

}
