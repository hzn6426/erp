package com.canaan.distribute.constant;

public class Constants {
	/**
	 * 执行方法的状态
	 */
	public static final String METHOD_EXE_PREPARE = "PREPARE";
	public static final String METHOD_EXE_SUCCESS = "SUCCESS";
	public static final String METHOD_EXE_LOCK= "LOCK";
	public static final String METHOD_EXE_FAILURE = "FAILURE";
	
	/**
	 * 分布式签名参数标示
	 */
	public static final String DISTRIBUTE_SIGNATURE = "DISTRIBUTE_SIGNATURE";
	/**
	 * 分布式签名持久化标示
	 */
	public static final String DISTRIBUTE_PERSISTENT = "DISTRIBUTE_PERSISTENT";
	//方法调用链标记
	public static final String LEVEL_TAG_ROOT = "LEVEL_TAG_ROOT";
	public static final String LEVEL_TAG_NODE = "LEVEL_TAG_NODE";
	
	public static final String USER = "SYSTEM_USER";
	
}
