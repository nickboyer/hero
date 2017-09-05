package com.nickboyer.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nickboyer.core.entry.SysRole;
import com.nickboyer.core.entry.SysRoleExample;

@Mapper
public interface SysRoleMapper {
	int countByExample(SysRoleExample example);

	int deleteByExample(SysRoleExample example);

	int deleteByPrimaryKey(Integer roleId);

	int insert(SysRole record);

	int insertSelective(SysRole record);

	List<SysRole> selectByExample(SysRoleExample example);

	SysRole selectByPrimaryKey(Integer roleId);

	int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	int updateByPrimaryKeySelective(SysRole record);

	int updateByPrimaryKey(SysRole record);

	/**
	 * 通过父ID获取角色信息
	 * 
	 * @param parentId
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月5日 下午2:26:42
	 */
	@Select("select * from sys_role where parent_id = #{parentId}")
	List<SysRole> selectByParentId(int parentId);
}