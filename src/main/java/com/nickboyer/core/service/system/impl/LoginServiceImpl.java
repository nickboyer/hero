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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickboyer.core.biz.BizException;
import com.nickboyer.core.common.Const;
import com.nickboyer.core.entry.SysUser;
import com.nickboyer.core.entry.SysUserExample;
import com.nickboyer.core.entry.SysUserExample.Criteria;
import com.nickboyer.core.repository.SysUserMapper;
import com.nickboyer.core.service.base.BaseService;
import com.nickboyer.core.service.system.ILoginService;

/**
 * 登录服务
 * 
 * @title
 * @description
 * @since JDK1.8
 */
@Service
public class LoginServiceImpl extends BaseService implements ILoginService {

	@Autowired
	private SysUserMapper userMapper;

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * com.nickboyer.core.service.system.ILoginService#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Object login(String account, String password, String code, HttpServletResponse response)
			throws BizException {

		Map<String, Object> returnMap = new HashMap<>();

		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(account);
		criteria.andPasswordEqualTo(password);
		criteria.andStatusEqualTo("1");
		List<SysUser> list = userMapper.selectByExample(example);
		Object code_ = SecurityUtils.getSubject().getSession().getAttribute(Const.VERIFY_CODE);
		if (list.isEmpty()) {

			returnMap.put("status", "1");
			returnMap.put("msg", "用户名或密码错误");
		} else if (!code.equals(code_)) {

			returnMap.put("status", "2");
			returnMap.put("msg", "验证码错误");
		} else {

			SecurityUtils.getSubject().getSession().removeAttribute(Const.VERIFY_CODE);
			SecurityUtils.getSubject().getSession().setAttribute("user", list.get(0));
			// shiro加入身份验证
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(account, password);
			logger.info("登录信息：用户名：" + account + ",密码：" + password);
			subject.login(token);
			returnMap.put("status", "0");
			returnMap.put("msg", "success");
		}
		return returnMap;
	}

}
