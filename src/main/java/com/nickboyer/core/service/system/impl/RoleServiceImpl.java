/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年9月5日 下午2:04:17
 */
package com.nickboyer.core.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.entry.SysRole;
import com.nickboyer.core.repository.SysRoleMapper;
import com.nickboyer.core.service.base.BaseService;
import com.nickboyer.core.service.system.IRoleService;

/**
 * 角色service
 * 
 * @title
 * @description
 * @since JDK1.8
 */
@Service
public class RoleServiceImpl extends BaseService implements IRoleService {

	@Autowired
	private SysRoleMapper roleMapper;

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.nickboyer.core.service.system.IRoleService#getMenus(java.lang.String)
	 */
	@Override
	public List<SysRole> getRoles(int id) throws BizException {

		// 1.获取所有的角色
		List<SysRole> list = new ArrayList<>();
		SysRole role = roleMapper.selectByPrimaryKey(id);
		list.add(role);
		getRoleByParentId(role.getRoleId(), list);

		// 2.获取角色拥有的所有菜单权限
		return list;
	}

	/**
	 * 获取所有子角色
	 * 
	 * @param roleId
	 * @param list
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月5日 下午2:30:19
	 */
	private void getRoleByParentId(int roleId, List<SysRole> list) {

		List<SysRole> roles = roleMapper.selectByParentId(roleId);
		list.addAll(roles);
		for (SysRole sysRole : roles) {

			getRoleByParentId(sysRole.getRoleId(), list);
		}
	}
}