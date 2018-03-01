package com.canaan.jgsf.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import lombok.Getter;

public class SpringUtil implements ApplicationContextAware {

	@Getter
	private static ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	public static Object getBean(String name) throws BeansException {  
        return context.getBean(name);  
    } 

}
