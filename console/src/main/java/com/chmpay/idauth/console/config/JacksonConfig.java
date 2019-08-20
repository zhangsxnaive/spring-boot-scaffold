package com.chmpay.idauth.console.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author zhangshuxin
 * @date 2019-07-09
 *
 * 返回json空值去掉null和""
 *
 */
@Configuration
public class JacksonConfig {

    /**
     * Include.Include.ALWAYS	默认
     * Include.NON_DEFAULT	属性为默认值不序列化
     * Include.NON_EMPTY	属性为 空（""） 或者为 NULL 都不序列化
     * Include.NON_NULL	属性为NULL 不序列化
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

}
