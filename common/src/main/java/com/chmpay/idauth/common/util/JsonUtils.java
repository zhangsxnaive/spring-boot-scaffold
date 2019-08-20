package com.chmpay.idauth.common.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

    public static SerializerFeature[] serializerFeatures = new SerializerFeature[]{SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.SkipTransientField,
            SerializerFeature.DisableCircularReferenceDetect};

    public static String toString(Object res) {
        return JSONObject.toJSONString(res, serializerFeatures);
    }
}
