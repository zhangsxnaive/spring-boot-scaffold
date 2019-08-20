package com.chmpay.idauth.common.util;

import com.chmpay.idauth.common.contants.StatusCode;
import com.chmpay.idauth.common.exception.MyException;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParamsUtils {

    /**
     * 组合Map
     *
     * @param paramMap
     * @param names
     * @param values
     * @return
     */
    public static void collectMap(Map<String, Object> paramMap, String[] names, String[] values)
            throws MyException {
        if (paramMap == null) {
            paramMap = new HashMap<>();
        }
        if (names == null || values == null) {
            throw new MyException(StatusCode.PARAM_ERROR);
        }

        if (names.length != values.length) {
            throw new MyException(StatusCode.PARAM_ERROR);
        }

        for (int i = 0; i < names.length; i++) {
            if (!StringUtils.isEmpty(values[i])) {
                paramMap.put(names[i], values[i]);
            }
        }

    }

    public static boolean contains(String key, String[] collections) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        Assert.notNull(collections);
        for (String coll : collections) {
            if (key.equals(coll)) {
                return true;
            }
        }
        return false;
    }

    public static boolean includes(String key, String[] collections) {
        if (StringUtils.isEmpty(key)) {
            return false;
        }
        Assert.notNull(collections);
        for (String coll : collections) {
            if (key.contains(coll)) {
                return true;
            }
        }
        return false;
    }

    public static Map<String, Object> collectMapNotEmpty(Map<String, Object> param) {


        if (param == null || param.isEmpty()) {
            return new HashMap<>();

        }
        Map<String, Object> map = new HashMap<>();
        for (Iterator<Map.Entry<String, Object>> its = param.entrySet().iterator(); its.hasNext(); ) {
            Map.Entry<String, Object> entry = its.next();
            if (entry.getValue() != null && !StringUtils.isEmpty(entry.getValue().toString())) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }
}
