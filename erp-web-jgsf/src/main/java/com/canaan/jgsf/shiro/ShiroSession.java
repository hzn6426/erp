package com.canaan.jgsf.shiro;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.apache.shiro.session.mgt.SimpleSession;

import lombok.Getter;
/**
 * 自定义shiro session用于解决 redis频繁更新session问题。
 * <p>shiro在每次访问对象（所有资源包括静态资源）时都会更新通过{@link #touch()}来更新lastAccessTime，
 * 导致进行redis频繁更新，而lastAccessTime对redis没有意义（redis键在expire后会自动删除）</p>
 * <p>通过添加beChanged参数，在除了lastAccessTime的更改之外进行记录，更新缓存时判断是否仅仅lastAccessTime更改，
 * 避免因为lastAccessTime更新频繁更新redis session</p>
 * @author zening
 * @date 2018年2月27日 下午2:21:08
 * @version 1.0.0
 */
public class ShiroSession extends SimpleSession {

	private static final long serialVersionUID = -9072572734937697229L;
	
	public ShiroSession() {
		super();
	}
	
	public ShiroSession(String host) {
		super(host);
	}
	
	@Getter
	private boolean changed = Boolean.FALSE;
	
	private void change() {
		this.changed = Boolean.TRUE;
	}
	
	@Override
	public Object removeAttribute(Object key) {
		this.change();
		return super.removeAttribute(key);
	}

	@Override
	public void setAttribute(Object key, Object value) {
		super.setAttribute(key, value);
		this.change();
	}

	@Override
	public void setAttributes(Map<Object, Object> attributes) {
		super.setAttributes(attributes);
		this.change();
	}


	@Override
	public void setHost(String host) {
		super.setHost(host);
		this.change();
	}

	@Override
	public void setId(Serializable id) {
		super.setId(id);
		this.change();
	}


	@Override
	public void setStopTimestamp(Date stopTimestamp) {
		super.setStopTimestamp(stopTimestamp);
		this.change();
	}

	@Override
	public void setTimeout(long timeout) {
		super.setTimeout(timeout);
		this.change();
	}

}
