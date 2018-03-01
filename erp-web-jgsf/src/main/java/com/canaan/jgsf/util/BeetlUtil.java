package com.canaan.jgsf.util;

import org.beetl.core.GroupTemplate;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

public class BeetlUtil {

	public static GroupTemplate beetlGroupTemplate() {
		BeetlGroupUtilConfiguration config = (BeetlGroupUtilConfiguration) SpringUtil.getBean("beetlConfig");
		GroupTemplate group = config.getGroupTemplate();
		return group;
	}
}
