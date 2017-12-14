package com.canaan.distribute.filter;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.canaan.distribute.common.DistributeSignature;
import com.canaan.distribute.common.User;
import com.canaan.distribute.constant.Constants;
import com.canaan.distribute.util.DistributeSignatureUtil;
import com.canaan.distribute.util.UserUtil;
/**
 * 提供者监控，用来封装消费者传递的调用栈信息
 * @author frog
 * @date 2017年9月12日 上午10:41:13
 * @version V1.0
 */
public class ProviderContextFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		Result result;
		String signature = RpcContext.getContext().getAttachment(Constants.DISTRIBUTE_SIGNATURE);
		if (StringUtils.isNotEmpty(signature)) {
			List<DistributeSignature> list = JSONArray.parseArray(signature, DistributeSignature.class);
			for (DistributeSignature ds : list) {
				DistributeSignatureUtil.addMethodChain(ds.getDsuuid() + '-' + ds.getName(), ds);
			}
		}
		String suser = RpcContext.getContext().getAttachment(Constants.USER);
		if (StringUtils.isNotEmpty(suser)) {
			UserUtil.put(JSONObject.parseObject(suser, User.class));
		}
		try {
			result = invoker.invoke(invocation);
		} finally {
			//清理方法链内存
			DistributeSignatureUtil.clearMethodChain();
			//清楚用户信息
			UserUtil.remove();
		}
		return result;
	}

}
