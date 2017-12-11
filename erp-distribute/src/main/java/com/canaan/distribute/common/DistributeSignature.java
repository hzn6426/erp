package com.canaan.distribute.common;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.canaan.distribute.constant.Constants;


/**
 * 分布式签名
 * @author frog
 * @date 2017年9月1日 下午3:27:52
 * @version V1.0
 */
public class DistributeSignature implements Serializable{

	private static final long serialVersionUID = 1L;
	private String dsuuid;		//签名的标示
	private String invokerName; //调用者
	private String name;		//当前方法名称
	private String parent;		//上一级调用信息
	private String state = Constants.METHOD_EXE_PREPARE;//执行状态
	private Map<String,Object> params;//参数列表
	private Boolean beDurable = Boolean.FALSE;//是否持久化数据库
	private String throwabling;//方法执行抛出的异常信息
	private String levelTag = Constants.LEVEL_TAG_NODE;//方法调用上存在分布式标记则值为LEVEL_TAG_ROOT，意味着该服务有并发一致性需求
	private int depth;	//深度 从1开始
	public DistributeSignature() {
	}
	public String getDsuuid() {
		return dsuuid;
	}
	public void setDsuuid(String dsuuid) {
		this.dsuuid = dsuuid;
	}
	public String getInvokerName() {
		return invokerName;
	}
	public void setInvokerName(String invokerName) {
		this.invokerName = invokerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		String writePattern = "^(create|delete|insert|do|save|update).*$";
		String methodName = (name == null || !name.contains("-")) ? null : name.split("-")[1];
		if (StringUtils.isNotEmpty(methodName) && methodName.matches(writePattern)) {
			this.beDurable = true;
		}
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public String getThrowabling() {
		return throwabling;
	}
	public void setThrowabling(String throwabling) {
		this.throwabling = throwabling;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getBeDurable() {
		return beDurable;
	}
	@Override
	public String toString() {
		return this.invokerName + '-' + this.name;
	}
	public String getLevelTag() {
		return levelTag;
	}
	public void setLevelTag(String levelTag) {
		this.levelTag = levelTag;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
}
