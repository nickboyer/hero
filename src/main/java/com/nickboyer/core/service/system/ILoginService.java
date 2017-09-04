/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年8月31日 下午4:15:12
 */
package com.nickboyer.core.service.system;

import javax.servlet.http.HttpServletResponse;

import com.nickboyer.core.biz.BizException;

/**
 * @title
 * @description
 * @since JDK1.8
 */
public interface ILoginService {

	/**
	 * @param account
	 * @param password
	 * @param code
	 * @param response
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年8月31日 下午4:17:15
	 */
	Object login(String account, String password, String code, HttpServletResponse response) throws BizException;

}
