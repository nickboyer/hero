/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年9月5日 下午2:04:00
 */
package com.nickboyer.core.service.system;

import java.util.List;

import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.entry.SysRole;

/**
 * @title
 * @description
 * @since JDK1.8
 */
public interface IRoleService {

	/**
	 * 获取所有角色
	 * 
	 * @param id 角色ID
	 * @return
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月5日 下午2:08:36
	 */
	List<SysRole> getRoles(int id) throws BizException;

	/**
	 * @param page
	 * @param limit
	 * @param roleId
	 * @param roleName
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月7日 下午5:13:48
	 */
	List<SysRole> list(String page, String limit, String roleId, String roleName) throws BizException;

	/**
	 * @param roleId
	 * @param roleName
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月8日 下午2:29:28
	 */
	int listCount(String roleId, String roleName) throws BizException;
}
