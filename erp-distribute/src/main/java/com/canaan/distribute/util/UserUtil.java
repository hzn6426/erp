package com.canaan.distribute.util;

import com.canaan.distribute.common.User;

public class UserUtil {

	private static final ThreadLocal<User> userLocal = new ThreadLocal<>();
	
	public static void put(User user) {
		if (!Checker.BeNotNull(get())) {
			userLocal.set(user);
		}
	}
	
	public static User get() {
		return userLocal.get();
	}
	
	public static void remove() {
		if (Checker.BeNotNull(get())) {
			userLocal.remove();
		}
	}
}
