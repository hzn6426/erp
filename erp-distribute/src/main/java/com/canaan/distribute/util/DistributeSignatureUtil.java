package com.canaan.distribute.util;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.canaan.distribute.common.DistributeSignature;
import com.canaan.distribute.constant.Constants;
/*** 
 * 分布式一致性工具，监控分布式标记，跟踪每一个方法的调用过程，调用过程为:
 *  controller -> DistributeAspect
 *  				| 
 *  	 ConstomerFailoverCluster(负载)		 <——	
 *  				|							|
 *  		ConsumerContextFilter			  	|	
 *  				|							|
 *			 DistributeException				|
 *					|  							|				
 *  		ProvierContextFilter -> DistributeAspect(有分布式标记) 
 * 
 *  
 *  形成方法调用链，调用链格式如下
 * Thread No:266
 *	|
 *	---Map<<key[357105388115460096-com.canaan.business.demo.DemoController-test] - value[有分布式标记,参数列表:无,执行状态:PREPARE,非持久化方法]>>
 *		|
 *		---Map<<key[357105388115460097-class com.alibaba.dubbo.rpc.RpcInvocation-insertForTest] - value[无分布式标记,参数列表:无,执行状态:LOCK,持久化方法]>>
 *		|
 *		---Map<<key[357105388123848704-com.canaan.basicdata.service.impl.SysAreaServiceImpl-insertForTest] - value[有分布式标记,参数列表:无,执行状态:PREPARE,持久化方法]>>
 *			|
 *			---Map<<key[357105388123848705-com.canaan.basicdata.service.impl.SysAreaServiceImpl-createForTest] - value[有分布式标记,参数列表:无,执行状态:SUCCESS,持久化方法]>>
 *			|
 *			---Map<<key[357105388128043008-class com.alibaba.dubbo.rpc.RpcInvocation-insertUserTest] - value[无分布式标记,参数列表:无,执行状态:SUCCESS,持久化方法]>>
 *			|
 *			---Map<<key[357105388149014528-class com.alibaba.dubbo.rpc.RpcInvocation-getuserbyusername] - value[无分布式标记,参数列表:[args-1:admin],执行状态:LOCK,非持久化方法]>>.
 * <p>方法执行状态:PREPARE-有分布式标志的方法调用开始 LOCK-方法被锁定执行中  SUCCESS-执行成功
 * <p> 注意：方法的参数能够被json进行序列化，否则会抛出序列化失败错误
 * 分布式签名工具
 * @author frog
 *
 */
public class DistributeSignatureUtil {
	
	private final static ThreadLocal<Map<String, DistributeSignature>> invokerChain = new ThreadLocal<>();
	
	/**
	 * 获取方法的调用栈，用来打印错误日志
	 * @return
	 */
	public static String getMethodInvokeChain() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Thread No:" + Thread.currentThread().getId());
		if (checkChainNull()) return "";
		Map<String,String> tabMap = new HashMap<>();
		String tabs = "\t";
		for (Map.Entry<String, DistributeSignature> ety : invokerChain.get().entrySet()) {
			String parent = "",t="",prefix="";
			if (StringUtils.isNotEmpty(parent = ety.getValue().getParent())) {
				t = tabs + (tabMap.get(parent) == null ? "" : tabMap.get(parent));
			}
			buffer.append("\r\n" + t + "|" );
			prefix = t;
			tabMap.put(ety.getKey(), prefix);
			String params = "";
			Map<String,Object> paramMap =  ety.getValue().getParams();
			if (paramMap == null) {
				params = "无";
			} else {
				params += "[";
				for (Map.Entry<String, Object> paramEty : paramMap.entrySet()) {
					params += paramEty.getKey() + ":" + paramEty.getValue();
				}
				params += "]";
			}
			buffer.append("\r\n" + prefix + "---Map<<key[" + ety.getKey() + "] - value[" 
					+ (Constants.LEVEL_TAG_ROOT.equalsIgnoreCase(ety.getValue().getLevelTag()) ? "有分布式标记" : "无分布式标记")
					+ "," + "参数列表:" + params
					+ "," + "执行状态:" + ety.getValue().getState()
					+ "," + (ety.getValue().getBeDurable() == true ? "持久化方法" : "非持久化方法") + "]>>");
			
		}
		return buffer.toString();
	}
	/**
	 * 控制台打印日志
	 */
	public static void printMethodChain() {
		System.out.println("Thread No:" + Thread.currentThread().getId());
		if (checkChainNull()) return;
		Map<String,String> tabMap = new HashMap<>();
//		logger.info("/***********************The method chain start********************");
		String tabs = "\t";
		for (Map.Entry<String, DistributeSignature> ety : invokerChain.get().entrySet()) {
			String parent = "",t="",prefix="";
			if (StringUtils.isNotEmpty(parent = ety.getValue().getParent())) {
				t = tabs + (tabMap.get(parent) == null ? "" : tabMap.get(parent));
			}
			System.out.println(t + "|");
			prefix = t;
			tabMap.put(ety.getKey(), prefix);
			System.out.println(prefix + "---Map<<key[" + ety.getKey() + "] - value[" 
					+ ety.getValue().getLevelTag()
					+ "," + ety.getValue().getDepth()
					+ "," + ety.getValue().getState()
					+ "," + ety.getValue().getParent() 
					+ "," + ety.getValue().getBeDurable() + "]>>");
			
		}
//		logger.info("/***********************The method chain end***********************");
	}
	
	private static boolean checkChainNull() {
		return invokerChain.get() == null;
	}
	/**
	 * 添加方法调用信息
	 * @param key
	 * @param signature
	 */
	public static void addMethodChain(String key, DistributeSignature signature) {
		Map<String,DistributeSignature> chainMap = invokerChain.get();
		if (checkChainNull()) {
			chainMap = new LinkedHashMap<String, DistributeSignature>(0);
			invokerChain.set(chainMap);
		}
		if (StringUtils.isNotEmpty(key) && signature != null) {
			chainMap.put(key, signature);
		}
	}
	/**
	 * 获取最上层的方法调用链
	 * @return
	 */
	public static DistributeSignature getRootMethodChain() {
		if (checkChainNull()) return null;
		DistributeSignature rootChain = null;
		int depth = -1;
		for (Map.Entry<String, DistributeSignature> ety : invokerChain.get().entrySet()) {
			if (ety.getValue() != null 
					&& Constants.LEVEL_TAG_ROOT.equals(ety.getValue().getLevelTag())
					&& Constants.METHOD_EXE_PREPARE.equalsIgnoreCase(ety.getValue().getState())) {
				if (ety.getValue().getDepth() > depth) {
					depth = ety.getValue().getDepth();
					rootChain = ety.getValue();
				}
			}
		}
		return rootChain;
	}
	
//	public static DistributeSignature getRootMethodChainByKey(String key) {
//		DistributeSignature ds = null;
//		if (checkChainNull() || (ds = invokerChain.get().get(key)) == null) return null;
//		do {
//			String parent = ds.getParent();
//			if (!StringUtil.isNotEmpty(parent) || (ds = invokerChain.get().get(parent)) == null) break;
//			
//		} while (!Constants.LEVEL_TAG_ROOT.equalsIgnoreCase(ds.getLevelTag()));
//		return ds;
//	}
	
	/**
	 * 清理调用链
	 */
	public static void clearMethodChain() {
		if (!checkChainNull()) invokerChain.remove();
	}
	
	/**
	 * 调用链是否存在已持久化的方法
	 * @return
	 */
	public static Boolean hasMethodChainDurable() {
		if (checkChainNull()) return false;
		for (Map.Entry<String, DistributeSignature> ety : invokerChain.get().entrySet()) {
			if (ety.getValue() != null 
					&& Constants.METHOD_EXE_SUCCESS.equalsIgnoreCase(ety.getValue().getState()) 
					//&& Constants.LEVEL_TAG_ROOT.equalsIgnoreCase(ety.getValue().getLevelTag())
					&& ety.getValue().getBeDurable() == Boolean.TRUE) return true;
		}
		return false;
	}
	/**
	 * 是否是第二层调用链，如果从controller调用，二层链是服务层
	 * @param name
	 * @return
	 */
	public static boolean beSecRootChain(String name) {
		DistributeSignature ds = null;
		if (checkChainNull() || !StringUtils.isNotEmpty(name)) return false;
		for (Map.Entry<String, DistributeSignature> ety : invokerChain.get().entrySet()) {
			if (name.equals(ety.getValue().getName())) {
				ds = ety.getValue();
			}
		}
		if (ds == null) return false;
		String parent = ds.getParent();
		DistributeSignature parentDs = invokerChain.get().get(parent);
		Boolean result = parentDs == null ? false : (StringUtils.isNotEmpty(parentDs.getParent()) ? true : false);
		return result;
	}
	
	/**
	 * 根据key获取签名
	 * @param key
	 * @return
	 */
	public static DistributeSignature getMethodChainByKey (String key) {
		if (checkChainNull() || !StringUtils.isNotEmpty(key)) return null;
		return invokerChain.get().get(key);
	}
	/**
	 * 获取完整的分布式签名
	 * @return
	 */
	public static Map<String, DistributeSignature> getMethodChain() {
		return invokerChain.get();
	}
	/**
	 * 创建一个方法签名
	 * @param uuid
	 * @param name
	 * @param args
	 * @param levelTag
	 * @return
	 */
	public static DistributeSignature newDistributeSignature(String uuid,String name, Object[] args, String levelTag) {
		DistributeSignature ds = new DistributeSignature();
		ds.setDsuuid(uuid);
		ds.setName(name);
		ds.setLevelTag(Constants.LEVEL_TAG_ROOT.equalsIgnoreCase(levelTag) 
				? Constants.LEVEL_TAG_ROOT : Constants.LEVEL_TAG_NODE);
		DistributeSignature parentDs = DistributeSignatureUtil.getRootMethodChain();
		ds.setInvokerName(parentDs == null ? "" : parentDs.getName());
		ds.setParent(parentDs == null ? "" : parentDs.getDsuuid() + '-' + parentDs.getName());
		ds.setDepth(parentDs == null ? 1 : parentDs.getDepth() + 1);
		int argsLength = 0;
		if (args != null && (argsLength = args.length) > 0) {
			Map<String,Object> paramMap = new HashMap<>();
			for (int i=0; i < argsLength; i++) {
				paramMap.put("args-" + (i + 1),args[i]);
			}
			ds.setParams(paramMap);
		}
		return ds;
	}
	/**
	 * 获取调用链中异常的信息
	 * @return
	 */
	public static DistributeSignature getExceptionChainMethod() {
		DistributeSignature ds = null;
		if (checkChainNull()) return ds;
		int depth = -1;
		for (Map.Entry<String, DistributeSignature> ety : invokerChain.get().entrySet()) {
			if (ety.getValue() != null 
					&& Constants.METHOD_EXE_LOCK.equalsIgnoreCase(ety.getValue().getState())) {
				if (ety.getValue().getDepth() > depth) {
					depth = ety.getValue().getDepth();
					ds = ety.getValue();
				}
				
			}
		}
		return ds;
	}
	
	
}
