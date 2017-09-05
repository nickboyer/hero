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
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.common.Const;
import com.nickboyer.core.controller.BaseController;
import com.nickboyer.core.entry.Citrn;
import com.nickboyer.core.entry.SysMenu;
import com.nickboyer.core.entry.SysRole;
import com.nickboyer.core.entry.SysUser;
import com.nickboyer.core.service.citrn.ICitrnService;
import com.nickboyer.core.service.system.ILoginService;
import com.nickboyer.core.service.system.IMenuService;
import com.nickboyer.core.service.system.IRoleService;
import com.nickboyer.core.util.VerifyCodeUtils;

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
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IMenuService menuService;

	/**
	 * 跳转到登录页面
	 * 
	 * @param model
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年8月29日 下午2:23:22
	 */
	@RequestMapping(value = "/login")
	public String toLogin() {

		return "system/login";
	}

	/**
	 * 登录方法
	 * 
	 * @param account 账号
	 * @param password 密码
	 * @return
	 * @throws Exception
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月4日 下午1:02:07
	 */
	@RequestMapping(value = "/login_login")
	@ResponseBody
	public Object login(@RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("code") String code, HttpServletResponse response) throws Exception {

		return loginService.login(account, password, code, response);

	}

	/**
	 * 跳转到首页
	 * 
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月4日 下午1:02:23
	 */
	@RequestMapping(value = "/index")
	public ModelAndView toIndex(ModelAndView mv) throws BizException {

		// 1.获取登录用户信息
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");
		mv.addObject("user", user);

		// 2.获取用户菜单信息
		List<SysRole> roles = roleService.getRoles(user.getRoleId());
		List<SysMenu> menus = menuService.getMenus(roles);
		mv.addObject("menus", menus);
		mv.setViewName("system/index");
		return mv;
	}

	@RequestMapping(value = "/verifyCode")
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 获取验证码
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入session
		SecurityUtils.getSubject().getSession().removeAttribute(Const.VERIFY_CODE);
		SecurityUtils.getSubject().getSession().setAttribute(Const.VERIFY_CODE, verifyCode);

		// 写入验证码
		VerifyCodeUtils.outputImage(85, 37, response.getOutputStream(), verifyCode.toUpperCase());
	}

	/**
	 * 欢迎页面
	 * 
	 * @param mv
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月4日 下午1:03:06
	 */
	@RequestMapping(value = "/welcome")
	public ModelAndView toWelcome(ModelAndView mv) {

		mv.setViewName("system/welcome");
		return mv;
	}

	@RequestMapping(value = "/toUserEdit")
	public ModelAndView toUserEdit(ModelAndView mv) {
		mv.setViewName("system/user_edit");
		return mv;
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
