package com.chmpay.idauth.console.service;

import java.io.Serializable;

/**
 * 定义redis需要的接口
 *
 * @author zhangsx
 * @date 2018/9/18
 */
public interface JedisService extends Serializable {
    /**
     * 根据key值获取
     *
     * @param key
     *            指定key
     * @return 存在返回值,不存在返回null
     */
    String get(String key) throws Exception;

    /**
     * 设置值(String kei)
     *
     * @param key
     *            key值
     * @param value
     *            内容
     * @return true设置成功 false设置失败
     */
    boolean set(String key, String value) throws Exception;

    /**
     * 设置值(String kei)
     *
     * @param key
     *            key值
     * @param value
     *            内容
     * @return 返回老值
     */
    String setAndGet(String key, String value) throws Exception;

    /**
     * 得到key的剩余时间
     *
     * @param key
     *            指定key
     * @return -2不存在 -1存在但无剩余时间 其他为剩余时间
     */
    Long ttl(String key) throws Exception;

    /**
     * 等价于 set + expire
     *
     * @param key
     *            设置的key
     * @param seconds
     *            存活时间,到期自动删除
     * @param value
     *            对应值
     * @return 结果
     */
    boolean setAndExpire(final String key, final String value, final int seconds)
            throws Exception;

    /**
     * 删除指定key
     *
     * @param key
     *            指定key
     * @return 不存在返回0 返回删除个数
     */
    Long del(String key) throws Exception;

    /**
     * 给指定key设置时间
     *
     * @param key
     *            指定key
     * @param timeout
     *            超时时间,单位秒
     * @return 1成功 0失败
     */
    Long expire(String key, int timeout) throws Exception;

    /**
     * 检测key是否存在
     *
     * @param key
     *            指定key
     * @return true存在
     */
    boolean exists(String key) throws Exception;

    /**
     * 不存在则设置,并延时,存在则什么都不做
     *
     * @param key
     *            key值
     * @param value
     *            值
     * @param seconds
     *            超时时间
     * @return true成功
     */
    boolean setNxAndExpire(final String key, final String value,
                           final int seconds) throws Exception;

}
