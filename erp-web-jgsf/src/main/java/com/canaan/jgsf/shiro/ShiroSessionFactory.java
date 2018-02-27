package com.canaan.jgsf.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
/**
 * 自定义sessionFactory,创建自定义{@link ShiroSession}，以优化更改规则
 * @author zening
 * @date 2018年2月27日 下午2:31:53
 * @version 1.0.0
 */
public class ShiroSessionFactory implements SessionFactory {

	@Override
	public Session createSession(SessionContext context) {
		if (context != null) {
			String host = context.getHost();
			if (host != null) {
				return new ShiroSession(host);
			}
		}

		return new ShiroSession();
	}

}
