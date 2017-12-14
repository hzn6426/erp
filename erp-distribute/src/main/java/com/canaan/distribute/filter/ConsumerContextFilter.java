package com.canaan.distribute.filter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.canaan.distribute.util.SnowflakeIdWorker;
import com.canaan.distribute.util.UserUtil;
import com.canaan.distribute.common.DistributeSignature;
import com.canaan.distribute.common.User;
import com.canaan.distribute.constant.Constants;
import com.canaan.distribute.util.Checker;
import com.canaan.distribute.util.DistributeSignatureUtil;
/**
 * 
 * 服务消费监控，用来对服务进行分布式一致性签名 
 * @author frog
 * @date 2017年9月12日 上午10:37:46
 * @version V1.0
 */
public class ConsumerContextFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		String name = invocation.getClass() + "-" + invocation.getMethodName();
		String uuid = SnowflakeIdWorker.getId();
		Map<String,DistributeSignature> chainMap = DistributeSignatureUtil.getMethodChain();
		if (chainMap != null ) {
			DistributeSignature ds = DistributeSignatureUtil.newDistributeSignature(uuid, name, invocation.getArguments(), Constants.LEVEL_TAG_NODE);
			ds.setState(Constants.METHOD_EXE_LOCK);
			DistributeSignatureUtil.addMethodChain(uuid + '-' + ds.getName(), ds);
			List<DistributeSignature> chainList = new LinkedList<>();
			for (Map.Entry<String, DistributeSignature> ety : chainMap.entrySet()) {
				chainList.add(ety.getValue());
			}
			RpcContext.getContext().setAttachment(Constants.DISTRIBUTE_SIGNATURE, JSONArray.toJSONString(chainList));
		}
		User user = UserUtil.get();
		if (Checker.BeNotNull(user)) {
			RpcContext.getContext().setAttachment(Constants.USER, JSONObject.toJSONString(user));
		}
		Result result =  invoker.invoke(invocation);
		if (result.getException() == null) {
			DistributeSignature lds = DistributeSignatureUtil.getMethodChainByKey(uuid + '-' + name);
			if (lds != null) {
				lds.setState(Constants.METHOD_EXE_SUCCESS);
			}
		}
		return result;
	}

}
