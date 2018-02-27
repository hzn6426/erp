package com.canaan.jgsf.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MapUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.dto.LinkDTO;
import com.canaan.util.tool.Checker;
import com.canaan.util.tool.ConvertUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "系统管理", description = "系统相关功能!")
@RequestMapping(value="/system", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SystemController {

	@ApiOperation(value="获取系统所有链接")
	@RequestMapping(value = "listAllLinks", method = RequestMethod.GET)
	public ResponseResult<LinkDTO> listAllLinks(HttpServletRequest request) {
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        MapIterator<RequestMappingInfo, HandlerMethod> mapIter = MapUtils.iterableMap(handlerMethods).mapIterator();
        Set<LinkDTO> linkSet = new HashSet<>();
        while (mapIter.hasNext()) {
        	Set<String> urls = new HashSet<>(1);
        	urls.addAll(mapIter.next().getPatternsCondition().getPatterns());
        	String classMethod = mapIter.getValue().getShortLogMessage();
        	Set<RequestMethod> methodSet = mapIter.next().getMethodsCondition().getMethods();
        	String method = Checker.BeNotEmpty(methodSet) ? ConvertUtil.toList(methodSet).get(0).name() : "";
        	linkSet.add(new LinkDTO().setMethod(method).setUrlSet(urls).setClassMethod(classMethod));
        }
        return ResponseResult.build(ConvertUtil.toList(linkSet));
	}
}
