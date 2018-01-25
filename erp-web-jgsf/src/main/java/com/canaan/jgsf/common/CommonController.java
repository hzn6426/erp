package com.canaan.jgsf.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.canaan.jgsf.constant.SystemConstants;

@Controller
public class CommonController {

	@RequestMapping({"/","/login"})
	public ModelAndView login() {
		return new ModelAndView(SystemConstants.LOGIN);
	}
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(SystemConstants.INDEX);
	}
}
