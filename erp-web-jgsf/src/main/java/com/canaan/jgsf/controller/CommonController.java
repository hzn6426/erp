package com.canaan.jgsf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.canaan.jgsf.constant.SystemConsts;
import com.canaan.jgsf.exception.ClientBizException;
import com.canaan.jgsf.exception.ClientExceptionEnum;
import com.canaan.jgsf.shiro.ShiroUser;
import com.canaan.jgsf.util.ShiroUtil;

import io.swagger.annotations.ApiOperation;

@Controller
public class CommonController {

	@Autowired
	protected HttpServletRequest httpServletRequest;
	@Autowired
	protected HttpServletResponse httpServletResponse;
	
	@RequestMapping({"/","/login"})
	public ModelAndView login() {
		return new ModelAndView(SystemConsts.LOGIN);
	}
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(SystemConsts.INDEX);
	}
	
	@ApiOperation(value = "登录")
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public ModelAndView checkLogin(RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:login");
		String errorClassName = (String)httpServletRequest.getAttribute("shiro_login_failure");  
        if(UnknownAccountException.class.getName().equals(errorClassName)) {
        	
        } else if (LockedAccountException.class.getName().equals(errorClassName)) {
        	
        } else if (ExcessiveAttemptsException.class.getName().equals(errorClassName)) {
        	
        } else if (errorClassName != null) {
        	
        }
//		String username = httpServletRequest.getParameter("username");
//	    String password = httpServletRequest.getParameter("password");
//		String remeberMe = httpServletRequest.getParameter("remember");
//		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//		if ("on".equalsIgnoreCase(remeberMe)) {
//			token.setRememberMe(true);
//		}
//		Subject subject = SecurityUtils.getSubject();
//		try {
//			subject.login(token);
//		} catch (UnknownAccountException | IncorrectCredentialsException uae) {
//			token.clear();
//			ClientBizException bizException = new ClientBizException(ClientExceptionEnum.USER_NAME_OR_PASSWD_INVLID);
//			redirectAttributes.addAttribute("code", bizException.getCode());
//			redirectAttributes.addAttribute("msg", bizException.getMessage());
//			return mv;
//		} catch (LockedAccountException lae) {
//			token.clear();
//			ClientBizException bizException = new ClientBizException(ClientExceptionEnum.ACCOUNT_IS_LOCK);
//			redirectAttributes.addAttribute("code", bizException.getCode());
//			redirectAttributes.addAttribute("msg", bizException.getMessage());
//			return mv;
//		} catch (ExcessiveAttemptsException eae) {
//			token.clear();
//			ClientBizException bizException = new ClientBizException(ClientExceptionEnum.TOO_MANY_TIME_FOR_LOGON);
//			redirectAttributes.addAttribute("code", bizException.getCode());
//			redirectAttributes.addAttribute("msg", bizException.getMessage());
//			return mv;
//		}
		ShiroUser user = ShiroUtil.getUser();
		httpServletRequest.getSession().setAttribute(SystemConsts.USER_SESSION, user);
		mv.setViewName("redirect:/swagger-ui.html");
		return mv;
	}
}
