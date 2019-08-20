package com.chmpay.idauth.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否需要 token 验证
 *
 * @author zhangshuxin
 * @date 2019-06-25
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    /**
     * true 需要token验证 false 不需要token验证
     * @return
     */
    boolean required() default true;
}
