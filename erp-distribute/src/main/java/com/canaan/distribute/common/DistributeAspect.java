package com.canaan.distribute.common;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import com.alibaba.dubbo.rpc.RpcException;
import com.canaan.distribute.util.SnowflakeIdWorker;
import com.canaan.distribute.constant.Constants;
import com.canaan.distribute.exception.DistributeException;
import com.canaan.distribute.util.DistributeSignatureUtil;
/**
 * 分布式一致性标志监控，监控{@link Distribute}标志的方法，保证方法内多服务调用的事物一致性
 * <p>对于方法内多服务调用（多个写服务）时，为保证事务一致性采取的策略，如方法中调用A，B，C三个写服务，A执行成功，数据持久化，B调用失败，C服务不会调用，会对B进行重试，
 * 重试次数在<code>dubbo:reference</code>中添加retries为准，建议两次，即一共三次调用，失败后抛出异常，并且记录日志，以便人工干预方法来平账A
 * 服务的持久化信息。
 * <p>该类会在每一个分布式标记{@link Distribute}方法调用生成 {@link DistributeSignature},记录方法调用信息，方法出现异常后根据方法调用链的持久化情况
 * 进行日志存储，并记录方法调用链{@link DistributeSignatureUtil}
 * @author frog
 * @since 2017年9月1日 下午1:52:57
 * @version V1.0
 */
@Aspect
@Order(100)
public class DistributeAspect {

//	private static final Logger logger = LoggerFactory.getLogger(DistributeAspect.class);  
	
	/**
	 * 监控Distribute标记 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(com.canaan.distribute.common.Distribute)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		Object r = null;
		Exception ex = null;
		DistributeSignature ds = null;
		MethodSignature signature = (MethodSignature)point.getSignature();
		Method method = point.getTarget().getClass().getMethod(signature.getName(), signature.getParameterTypes());
		String name = point.getTarget().getClass().getName() + '-' + method.getName();
		try {
			//创建分布式签名
			String dsuuid = SnowflakeIdWorker.getId();
			if ((ds = DistributeSignatureUtil.getMethodChainByKey(dsuuid + '-' + name)) == null) {
				ds = DistributeSignatureUtil.newDistributeSignature(dsuuid, name, point.getArgs(), Constants.LEVEL_TAG_ROOT);
				DistributeSignatureUtil.addMethodChain(dsuuid + '-' + name, ds);
			}
			r =  point.proceed();
			ds.setState(Constants.METHOD_EXE_SUCCESS);
			
		} catch(RpcException re) {
			ex = re;
			//如果分布式调用链中存在持久化,抛出分布式异常 
			if (DistributeSignatureUtil.hasMethodChainDurable()) {
				throw new DistributeException(re, "方法调用链:\n" + DistributeSignatureUtil.getMethodInvokeChain());
			}
			throw re;
		} catch (DistributeException de) {
			ex = de;
			throw de;
		} catch (Exception te) {
			ex = te;
			ex.printStackTrace();
			if (DistributeSignatureUtil.hasMethodChainDurable()) {
				throw new DistributeException(te);
			}
			throw te;
		}finally {
			//清理方法线程栈，防止内存溢出
			if (ex != null || ds == null ||
					ds.getDepth() == 1 && Constants.LEVEL_TAG_ROOT.equalsIgnoreCase(ds.getLevelTag())) {
				DistributeSignatureUtil.clearMethodChain();
			}
		}
		return r;
	}
}
