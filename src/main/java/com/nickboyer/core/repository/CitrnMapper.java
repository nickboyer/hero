/*
 * Copyright 2014 Buyforyou.cn All rights reserved
 * 
 * @author Kang.Y
 * 
 * @mail
 * 
 * @createtime 2017年8月29日 下午5:01:12
 */
package com.nickboyer.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.nickboyer.core.entry.Citrn;

/**
 * @title
 * @description
 * @since JDK1.8
 */
@Mapper
public interface CitrnMapper {

	@Select("select * from citrn")
	List<Citrn> getAll();
}
