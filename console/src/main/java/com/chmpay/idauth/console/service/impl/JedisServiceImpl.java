package com.chmpay.idauth.console.service.impl;

import com.chmpay.idauth.console.service.JedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangsx
 * @date 2018/9/18
 */
@Service
public class JedisServiceImpl implements JedisService {

    /**
     *
     */
    private static final long serialVersionUID = -1109612941817090634L;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String get(String key) throws Exception {

        Assert.notNull(key);

        return stringRedisTemplate.boundValueOps(key).get();

    }

    @Override
    public boolean set(String key, String value) throws Exception {
        Assert.notNull(key);
        Assert.notNull(value);
        return set(key, value, null);
    }

    /**
     * 将value对象写入缓存
     *
     * @param key
     * @param value
     * @param time
     *            失效时间(秒)
     */
    private boolean set(String key, Object value, Long time) throws Exception {

        try {
            stringRedisTemplate.opsForValue().set(key, value.toString());
            if (time != null && time > 0) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 将value对象写入缓存
     *
     * @param key
     * @param value
     * @param time
     *            失效时间(秒)
     */
    private String setAfGet(String key, Object value, Long time) {
        try {
            String obj = stringRedisTemplate.opsForValue().getAndSet(key,
                    value.toString());

            if (time != null && time > 0) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public Long ttl(String key) {
        Assert.notNull(key);
        return stringRedisTemplate.boundValueOps(key).getExpire();
    }

    @Override
    public Long del(String key) {
        Assert.notNull(key);
        try {
            stringRedisTemplate.delete(key);
            return 1L;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }

    }

    @Override
    public Long expire(String key, int timeout) {
        Assert.notNull(key);
        try {

            Boolean _setExire = stringRedisTemplate.expire(key,
                    Long.parseLong(timeout + ""), TimeUnit.SECONDS);
            if (_setExire) {
                return 1L;
            }
            return 0L;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }

    }

    @Override
    public boolean exists(String key) {

        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public boolean setNxAndExpire(String key, String value, int seconds)
            throws Exception {
        if (exists(key)) {
            return false;
        }

        return setAndExpire(key, value, seconds);
    }

    @Override
    public String setAndGet(String key, String value) throws Exception {

        return setAfGet(key, value, null);
    }

    @Override
    public boolean setAndExpire(String key, String value, int seconds)
            throws Exception {
        return set(key, value, Long.parseLong(seconds + ""));

    }

}
