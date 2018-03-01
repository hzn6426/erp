package com.canaan.jgsf.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MapUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.canaan.jgsf.common.ActionController;
import com.canaan.jgsf.common.ResponseResult;
import com.canaan.jgsf.constant.SystemConsts;
import com.canaan.jgsf.dto.LinkDTO;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.canaan.jgsf.shiro.ShiroUser;
import com.canaan.jgsf.util.ShiroUtil;
import com.canaan.util.tool.Checker;
import com.canaan.util.tool.ConvertUtil;

import io.swagger.annotations.ApiOperation;

@Controller
public class SystemController extends ActionController{

	
	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView(SystemConsts.LOGIN);
	}
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(SystemConsts.INDEX);
	}
	
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public RedirectView doLogin(RedirectAttributes redirectAttributes) {
		RedirectView mv = new RedirectView("/login");
		String username = getRequestParameter("username");
		String password = getRequestParameter("password");
		String remeber = getRequestParameter("remeber");
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		if ("on".equals(remeber)) {
			token.setRememberMe(true);
		}
		ClientBizException bizException = null;
		try {
			ShiroUtil.getSubject().login(token);
		} catch (UnknownAccountException | IncorrectCredentialsException es) {
			bizException = new ClientBizException(ClientExceptionEnum.USER_NAME_OR_PASSWD_INVLID);
			redirectAttributes.addFlashAttribute("code", bizException.getCode());
			redirectAttributes.addFlashAttribute("msg", bizException.getMessage());
		} catch (LockedAccountException lae) {
			bizException = new ClientBizException(ClientExceptionEnum.ACCOUNT_IS_LOCK);
			redirectAttributes.addFlashAttribute("code", bizException.getCode());
			redirectAttributes.addFlashAttribute("msg", bizException.getMessage());
		} catch (ExcessiveAttemptsException eae) {
			bizException = new ClientBizException(ClientExceptionEnum.TOO_MANY_TIME_FOR_LOGON);
			redirectAttributes.addFlashAttribute("code", bizException.getCode());
			redirectAttributes.addFlashAttribute("msg", bizException.getMessage());
		}
		 if (bizException != null) {
        	getSession().removeAttribute(SystemConsts.USER_SESSION);
        	redirectAttributes.addFlashAttribute("username", getRequest().getParameter("username"));
        	return mv;
        }
		ShiroUser user = ShiroUtil.getUser();
		getRequest().getSession().setAttribute(SystemConsts.USER_SESSION, user);
//			return null;
		mv.setUrl("/swagger-ui.html");
		return mv;
	}
	
//	@ApiOperation(value = "登录")
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public RedirectView checkLogin(RedirectAttributes redirectAttributes) {
//		RedirectView mv = new RedirectView("/login");
//		String errorClassName = (String)getRequest().getAttribute("shiro_login_failure"); 
//		ClientBizException bizException = null;
//        if(UnknownAccountException.class.getName().equals(errorClassName) 
//        		|| IncorrectCredentialsException.class.getName().equals(errorClassName)) {
//			bizException = new ClientBizException(ClientExceptionEnum.USER_NAME_OR_PASSWD_INVLID);
//			redirectAttributes.addFlashAttribute("code", bizException.getCode());
//			redirectAttributes.addFlashAttribute("msg", bizException.getMessage());
//        } else if (LockedAccountException.class.getName().equals(errorClassName)) {
//        	bizException = new ClientBizException(ClientExceptionEnum.ACCOUNT_IS_LOCK);
//			redirectAttributes.addFlashAttribute("code", bizException.getCode());
//			redirectAttributes.addFlashAttribute("msg", bizException.getMessage());
//        } else if (ExcessiveAttemptsException.class.getName().equals(errorClassName)) {
//        	bizException = new ClientBizException(ClientExceptionEnum.TOO_MANY_TIME_FOR_LOGON);
//			redirectAttributes.addFlashAttribute("code", bizException.getCode());
//			redirectAttributes.addFlashAttribute("msg", bizException.getMessage());
//        } else if (errorClassName != null) {
//        	bizException = new ClientBizException(ClientExceptionEnum.UN_CHECKED_EXCEPTION);
//			redirectAttributes.addFlashAttribute("code", bizException.getCode());
//			redirectAttributes.addFlashAttribute("msg", bizException.getMessage());
//        }
//        
//        if (bizException != null) {
//        	getSession().removeAttribute(SystemConsts.USER_SESSION);
//        	redirectAttributes.addFlashAttribute("username", getRequest().getParameter("username"));
//        	return mv;
//        }
//		ShiroUser user = ShiroUtil.getUser();
//		getRequest().getSession().setAttribute(SystemConsts.USER_SESSION, user);
////		return null;
//		mv.setUrl("/swagger-ui.html");
//		return mv;
//	}
	
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
