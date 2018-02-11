package com.canaan.jgsf.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
        	.apiInfo(apiInfo())
        	.forCodeGeneration(true)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.canaan.jgsf.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
    	StringBuilder notes = new StringBuilder();
    	notes.append("<p>约束:所有的基于Ajax请求返回的数据格式都为:{code:ixxx,message:sxxx,data:[jsondata]},totalSize:isize}</p>")
    		.append("<ul>")
    		.append("<li>").append("ixxx: 业务逻辑编码，200表示请求成功").append("</li>")
    		.append("<li>").append("sxxx: 逻辑代码不为200，即请求异常时表示的异常消息，默认为OK").append("</li>")
    		.append("<li>").append("jsondata:根据请求的业务不同而不同，默认为空").append("</li>")
    		.append("<li>").append("isize: 为data的数据条数，默认为0").append("</li>")
    		.append("</ul>")
    		.append("<p>对于save,update,delete方法，默认返回数据格式为:{code:200,message:\"OK\",data:[],totalSize:0}</p>");
        return new ApiInfoBuilder()
            .title("ERP接口API")
            .description(notes.toString())
            .version("1.0.0")
//            .termsOfServiceUrl("http://terms-of-services.url")
            .license("All rights reserved by Canaan.")
            .contact(new Contact("canaan", "", ""))
//            .licenseUrl("http://url-to-license.com")
            .build();
    }
}
