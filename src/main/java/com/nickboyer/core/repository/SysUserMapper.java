package com.nickboyer.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nickboyer.core.entry.SysUser;
import com.nickboyer.core.entry.SysUserExample;

@Mapper
public interface SysUserMapper {
	int countByExample(SysUserExample example);

	int deleteByExample(SysUserExample example);

	int deleteByPrimaryKey(Integer userId);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	List<SysUser> selectByExample(SysUserExample example);

	SysUser selectByPrimaryKey(Integer userId);

	int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

	int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);
}