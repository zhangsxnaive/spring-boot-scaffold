package com.chmpay.idauth.common.util;

import com.alibaba.fastjson.JSONObject;
import com.chmpay.idauth.common.contants.StatusCode;
import org.springframework.util.Assert;

public class PackUtils {

    public static void packageStatusCode(JSONObject object, StatusCode statusCode) {
        Assert.notNull(object);
        object.put("status", statusCode.getStatus());
        object.put("msg", statusCode.getMsg());
    }

    public static void packageStatusCodeAndData(JSONObject object, StatusCode statusCode, Object data) {
        Assert.notNull(object);
        object.put("status", statusCode.getStatus());
        object.put("msg", statusCode.getMsg());
        object.put("data", data);
    }
    
    public static void packageStatusCodeAndData(JSONObject object, StatusCode statusCode, String key[], String value[]) {
        Assert.notNull(object);
        object.put("status", statusCode.getStatus());
        object.put("msg", statusCode.getMsg());
        for(int i=0;i<key.length;i++){
        object.put(key[i], value[i]);
        }
    }
    

}
