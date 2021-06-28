package com.ntc.paas.plat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ntc.paas.plat.entity.Menu;

public interface MenuDao {

	int deleteByPrimaryKey(Long id);

	int insert(Menu record);

	int insertSelective(Menu record);

	Menu selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Menu record);

	int updateByPrimaryKey(Menu record);

	List<Menu> queryList();

	List<Menu> queryListByIds(@Param("ids") List<String> ids);
}