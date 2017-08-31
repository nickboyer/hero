/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年8月29日 下午2:20:49
 */
package com.nickboyer.core.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nickboyer.core.controller.BaseController;
import com.nickboyer.core.entry.Citrn;
import com.nickboyer.core.service.citrn.ICitrnService;
import com.nickboyer.core.service.system.ILoginService;

/**
 * 系统控制器
 * 
 * @title
 * @description
 * @since JDK1.8
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private ICitrnService citrnService;
	@Autowired
	private ILoginService loginService;

	/**
	 * 跳转到登录页面
	 * 
	 * @param model
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年8月29日 下午2:23:22
	 */
	@RequestMapping(value = "/toLogin")
	public String toLogin() {

		return "system/login";
	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {

		String account = request.getParameter("account");
		String password = request.getParameter("password");
		return loginService.login(account, password);

	}

	@RequestMapping(value = "/welcome")
	public String toWelcome() {
		return "system/welcome";
	}

	@RequestMapping(value = "/user_list")
	public ModelAndView listUser(ModelAndView mv) {
		List<Citrn> list = citrnService.getAll();
		mv.addObject("list", list);
		mv.setViewName("system/user_list");
		return mv;
	}

	@RequestMapping(value = "/getList")
	@ResponseBody
	public Object getList(HttpServletRequest request) {
		List<Citrn> list = citrnService.getAll();
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("code", "0");
		returnMap.put("msg", "");
		returnMap.put("count", 1000);
		returnMap.put("data", list);
		return returnMap;
	}
}
