package com.chmpay.idauth.console.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置
 *
 * @author zhangshuxin
 * @date 2019-06-25
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 只有标记@ApiOperation才会暴露出给swagger
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 设置basePackage会将包下的所有类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com.chmpay.idauth.console.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("实名认证管理平台API文档")
                .description("spring boot版本Swagger2文档")
                .version("1.0")
                .build();
    }


}
