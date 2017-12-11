package com.canaan.distribute.cluster;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.dubbo.rpc.cluster.support.AbstractClusterInvoker;
import com.canaan.distribute.common.DistributeSignature;
import com.canaan.distribute.exception.DistributeException;
import com.canaan.distribute.util.DistributeSignatureUtil;
import com.google.common.base.Throwables;

public class CustomerFailoverCluster implements Cluster {

	private static final Logger logger = LoggerFactory.getLogger(CustomerFailoverCluster.class);
	@Override
	public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
		return new AbstractClusterInvoker<T>(directory) {
            @SuppressWarnings({ "unchecked", "rawtypes" })
			public Result doInvoke(Invocation invocation, List<Invoker<T>> invokers, LoadBalance loadbalance) throws RpcException {
            	String name = invocation.getClass() + "-" + invocation.getMethodName();
            	List<Invoker<T>> copyinvokers = invokers;
                checkInvokers(copyinvokers, invocation);
                int len = getUrl().getMethodParameter(invocation.getMethodName(), Constants.RETRIES_KEY, Constants.DEFAULT_RETRIES) + 1;
                if (len <= 0) {
                    len = 1;
                }
                // retry loop.
                RpcException le = null; // last exception.
                List<Invoker<T>> invoked = new ArrayList<Invoker<T>>(copyinvokers.size()); // invoked invokers.
                Set<String> providers = new HashSet<String>(len);
                for (int i = 0; i < len; i++) {
                    //重试时，进行重新选择，避免重试时invoker列表已发生变化.
                    //注意：如果列表发生了变化，那么invoked判断会失效，因为invoker示例已经改变
                    if (i > 0) {
                        checkWheatherDestoried();
                        copyinvokers = list(invocation);
                        //重新检查一下
                        checkInvokers(copyinvokers, invocation);
                    }
                    Invoker<T> invoker = select(loadbalance, invocation, copyinvokers, invoked);
                    invoked.add(invoker);
                    RpcContext.getContext().setInvokers((List)invoked);
                    try {
                        Result result = invoker.invoke(invocation);
                        if (le != null && logger.isWarnEnabled()) {
                            logger.warn("Although retry the method " + invocation.getMethodName()
                                    + " in the service " + getInterface().getName()
                                    + " was successful by the provider " + invoker.getUrl().getAddress()
                                    + ", but there have been failed providers " + providers 
                                    + " (" + providers.size() + "/" + copyinvokers.size()
                                    + ") from the registry " + directory.getUrl().getAddress()
                                    + " on the consumer " + NetUtils.getLocalHost()
                                    + " using the dubbo version " + Version.getVersion() + ". Last error is: "
                                    + le.getMessage(), le);
                        }
                        //分布式一致性验证-此处为能够调到服务
                        if (DistributeSignatureUtil.hasMethodChainDurable()) {
	                        //抛出异常重试，直到达到重试次数
	                        Throwable t = result.getException();
	                        if (t != null && !(t instanceof DistributeException)) {
	                        	if (i == len - 1  && DistributeSignatureUtil.beSecRootChain(name)) {
	                        		result = bindResult(result,invocation);
	                        	} else {
	                        		continue;
	                        	}
	                        }
                        }
                        return result;
                    } catch (RpcException e) {
                        if (e.isBiz()) { // biz exception.
                            throw e;
                        }
                        le = e;
                    } catch (Throwable e) {
                        le = new RpcException(e.getMessage(), e);
                    } finally {
                        providers.add(invoker.getUrl().getAddress());
                    }
                }
                //不能调用服务，网络问题，切换调用服务器，直到达到重试次数，封装异常
                if (DistributeSignatureUtil.hasMethodChainDurable()) {
                	return bindResult(new RpcResult(le),invocation);
                }
                throw new RpcException(le != null ? le.getCode() : 0, "Failed to invoke the method "
                        + invocation.getMethodName() + " in the service " + getInterface().getName() 
                        + ". Tried " + len + " times of the providers " + providers 
                        + " (" + providers.size() + "/" + copyinvokers.size() 
                        + ") from the registry " + directory.getUrl().getAddress()
                        + " on the consumer " + NetUtils.getLocalHost() + " using the dubbo version "
                        + Version.getVersion() + ". Last error is: "
                        + (le != null ? le.getMessage() : ""), le != null && le.getCause() != null ? le.getCause() : le);
            }
        };
	}
	
	/**
	 * 封装异常
	 * @param result
	 * @return
	 */
	private RpcResult bindResult(Result result,Invocation invocation) {
		RpcResult r = new RpcResult();
		DistributeSignature ds = DistributeSignatureUtil.getExceptionChainMethod();
		if (result != null) {
			r.setValue(result);
			if (ds != null) {
				
				String exString = "分布式异常:id-" + ds.getDsuuid() + "，调用者-"  + ds.getInvokerName() 
					+ ",服务名-" + invocation.getMethodName();
				Object[] args = invocation.getArguments();
				int argsLength = 0;
				if (args != null && (argsLength = args.length) > 0) {
					exString += ",\n 参数列表-";
					for (int i=0; i < argsLength; i++) {
						exString += "[param]-" + (i + 1) + ",[value]-" +args[i];
					}
				}
				
	            //调用栈
	            String invokeChain = DistributeSignatureUtil.getMethodInvokeChain();
	            exString += "\n" + invokeChain;
	            //抛出异常重试，直到达到重试次数
	            Throwable t = result.getException(); 
	            if (t != null && t instanceof Exception) {
		            String apiExMsg = Throwables.getStackTraceAsString(t);
					exString += ".\n 服务调用异常信息:" + apiExMsg;
	            }
				DistributeException de = new DistributeException(t, exString);
				r.setException(de);
			}
		}
		
		return r;
	}

}
