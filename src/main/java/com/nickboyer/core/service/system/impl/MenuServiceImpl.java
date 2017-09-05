/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年9月5日 下午2:32:57
 */
package com.nickboyer.core.service.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.entry.SysMenu;
import com.nickboyer.core.entry.SysRole;
import com.nickboyer.core.repository.SysMenuMapper;
import com.nickboyer.core.service.system.IMenuService;
import com.nickboyer.core.util.RightsUtil;

/**
 * 菜单service
 * 
 * @title
 * @description
 * @since JDK1.8
 */
@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private SysMenuMapper menuMapper;

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.nickboyer.core.service.system.IMenuService#getMenus(java.util.List)
	 */
	@Override
	public List<SysMenu> getMenus(List<SysRole> roles) throws BizException {

		// 返回参数
		List<SysMenu> list = new ArrayList<>();

		// 1.获取所有的菜单权限
		List<String> rights = new ArrayList<>();
		for (SysRole role : roles) {

			rights.add(role.getRights());
		}
		// 2.获取所有的菜单。
		List<SysMenu> menus = menuMapper.findAll();
		// 3.遍历匹配权限
		for (SysMenu menu : menus) {

			for (String right : rights) {

				if (RightsUtil.testRights(right, menu.getMenuId() + "")) {

					list.add(menu);
				}
			}
		}

		// 4.拼装menu格式
		Collections.sort(list, new Comparator<SysMenu>() {

			@Override
			public int compare(SysMenu o1, SysMenu o2) {

				if (o1.getParentId() - o2.getParentId() == 0) {

					return o1.getMenuId() - o2.getMenuId();
				} else {

					return o1.getParentId() - o2.getParentId();
				}

			}

		});

		// 组装返回参数
		List<SysMenu> returnList = new ArrayList<>();
		Map<String, SysMenu> returnMap = new LinkedHashMap<>();
		int parentId = 0;
		for (SysMenu menu : list) {

			returnMap.put(menu.getMenuId() + "", menu);
			if (parentId == menu.getParentId()) {

				returnList.add(menu);
			} else {

				returnMap.get(menu.getParentId() + "").addMenu(menu);
			}
		}

		return returnList;
	}

}
