package com.canaan.jgsf.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        	.forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("ERP接口API")
            .description("接口文档测试")
            .version("1.0")
//            .termsOfServiceUrl("http://terms-of-services.url")
            .license("All rights reserved by Canaan.")
//            .licenseUrl("http://url-to-license.com")
            .build();
    }
}
