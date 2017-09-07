/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年9月7日 下午2:03:35
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

import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.entry.SysRole;
import com.nickboyer.core.service.system.IRoleService;

/**
 * 角色管理controller
 * 
 * @title
 * @description
 * @since JDK1.8
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping("/toList")
	public String toList() {

		return "system/role_list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Object list(HttpServletRequest request) throws Exception {

		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("code", 0);
		returnMap.put("msg", "");

		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		String roleId = request.getParameter("roleId");
		String roleName = request.getParameter("roleName");
		try {
			List<SysRole> list = roleService.list(page, limit, roleId, roleName);
			returnMap.put("count", list.size());
			returnMap.put("data", list);
		} catch (BizException e) {

			returnMap.put("code", e.messageCode);
			returnMap.put("msg", e.getMessage());
			e.printStackTrace();
		}

		return returnMap;

	}
}
