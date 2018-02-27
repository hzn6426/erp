package com.canaan.jgsf.beetl;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

	@Override
	protected void initOther() {
		groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
	}

}
