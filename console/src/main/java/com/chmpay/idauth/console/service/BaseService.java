package com.chmpay.idauth.console.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chmpay.idauth.common.contants.Contants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshuxin
 * @date 2019-06-10
 */
@Service
public class BaseService {

    @Autowired
    protected JedisService jedisService;
    /**
     * 检查REDIS的返回值是否为空
     *
     * @param resuslt
     * @return
     */
    public static boolean isEmptyRedis(String resuslt) {
        if (StringUtils.isEmpty(resuslt) || "nil".equals(resuslt)) {
            return true;
        }
        return false;
    }

    /**
     * 检查list的返回值是否为空
     *
     * @param resuslts
     * @return
     */
    public static boolean isEmptyList(List<?> resuslts) {
        return resuslts == null || resuslts.isEmpty();
    }

    /**
     * 计算开始行数
     *
     * @param pages 页码
     * @param rows  每页显示行数
     * @return
     */
    public int startRows(int pages, int rows) {
        return (pages - 1) * rows + 1;
    }


    public void setCache(String key, String value) {
        try {
            jedisService.setAndExpire(key, value, Contants.NORMAL_TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCacheNoExpire(String key, String value) {
        try {
            jedisService.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public <T> T getCache(String key, Class <T> cls) {
        try {
            String jsonString = jedisService.get(key);
            if (!isEmptyRedis(jsonString)) {
                return JSONObject.parseObject(jsonString, cls);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> List <T> getCacheList(String key, Class <T> cls) {
        try {
            String jsonString = jedisService.get(key);
            if (!isEmptyRedis(jsonString)) {
                return JSONArray.parseArray(jsonString, cls);
            }
            return new ArrayList <T>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList <T>();
        }
    }

    public void deleteCache(String key) {

        try {
            jedisService.del(key);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
