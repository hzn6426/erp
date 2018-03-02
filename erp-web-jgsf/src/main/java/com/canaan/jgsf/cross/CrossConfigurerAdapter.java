package com.canaan.jgsf.cross;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import lombok.Setter;
/**
 * 用于springmvc中 跨域请求过滤
 * @author zening
 * @date 2018年2月28日 上午11:17:08
 * @version 1.0.0
 */
public class CrossConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Setter
	private String allowCross;
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] allowOrigins = StringUtils.isNotBlank(allowCross) ? allowCross.split(",") : new String[]{};
		registry.addMapping("/*").allowedOrigins("*");
	}

}
