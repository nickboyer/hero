/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年8月29日 下午4:58:38
 */
package com.nickboyer.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nickboyer.core.entry.Citrn;
import com.nickboyer.core.repository.CitrnMapper;
import com.nickboyer.core.service.ICitrnService;

/**
 * @title
 * @description
 * @since JDK1.8
 */
@Service
public class CitrnServiceImpl implements ICitrnService {

	@Autowired
	private CitrnMapper mapper;

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.nickboyer.core.service.ICitrnService#getAll()
	 */
	@Override
	public List<Citrn> getAll() {
		// TODO 自动生成方法存根
		return mapper.getAll();
	}

}
