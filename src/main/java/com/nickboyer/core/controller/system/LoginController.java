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
import com.nickboyer.core.service.ICitrnService;

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
	public ModelAndView toLogin(ModelAndView mv) {

		mv.setViewName("system/login");
		return mv;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, ModelAndView mv) {

		String account = request.getParameter("account");
		String password = request.getParameter("password");

		logger.info("登录信息：用户名：" + account + ",密码：" + password);
		mv.setViewName("system/index");
		return mv;
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
