package com.nickboyer.core.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nickboyer.core.entry.SysMenu;
import com.nickboyer.core.entry.SysMenuExample;

@Mapper
public interface SysMenuMapper {
	int countByExample(SysMenuExample example);

	int deleteByExample(SysMenuExample example);

	int deleteByPrimaryKey(Integer menuId);

	int insert(SysMenu record);

	int insertSelective(SysMenu record);

	List<SysMenu> selectByExample(SysMenuExample example);

	SysMenu selectByPrimaryKey(Integer menuId);

	int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

	int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

	int updateByPrimaryKeySelective(SysMenu record);

	int updateByPrimaryKey(SysMenu record);

	/**
	 * 获取所有显示的菜单
	 * 
	 * @return
	 *
	 * @authz Kang.Y
	 * @createtime 2017年9月5日 下午2:38:00
	 */
	@Select("select * from sys_menu where menu_type = '1'")
	List<SysMenu> findAll();
}