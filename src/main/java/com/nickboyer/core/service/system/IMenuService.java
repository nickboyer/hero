/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年9月5日 下午2:32:42
 */
package com.nickboyer.core.service.system;

import java.util.List;

import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.entry.SysMenu;
import com.nickboyer.core.entry.SysRole;

/**
 * @title
 * @description
 * @since JDK1.8
 */
public interface IMenuService {

	/**
	 * 获取角色对应的所有的菜单。
	 * 
	 * @param roles
	 * @return
	 * @throws BizException
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月5日 下午2:35:53
	 */
	List<SysMenu> getMenus(List<SysRole> roles) throws BizException;
}
