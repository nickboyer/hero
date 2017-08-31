/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年8月31日 下午4:15:31
 */
package com.nickboyer.core.service.system.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.common.Assert;
import com.nickboyer.core.entry.SysUser;
import com.nickboyer.core.entry.SysUserExample;
import com.nickboyer.core.entry.SysUserExample.Criteria;
import com.nickboyer.core.repository.SysUserMapper;
import com.nickboyer.core.service.system.ILoginService;

/**
 * 登录服务
 * 
 * @title
 * @description
 * @since JDK1.8
 */
@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private SysUserMapper userMapper;

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.nickboyer.core.service.system.ILoginService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public Object login(String account, String password) throws BizException {

		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(account);
		criteria.andPasswordEqualTo(password);
		criteria.andStatusEqualTo("1");
		List<SysUser> userInfo = userMapper.selectByExample(example);
		Assert.notNull(userInfo, errCode, errMsg, args);
		// shiro加入身份验证
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		subject.login(token);
		// 登出
		logger.info("登录信息：用户名：" + account + ",密码：" + password);

		return null;
	}

}
