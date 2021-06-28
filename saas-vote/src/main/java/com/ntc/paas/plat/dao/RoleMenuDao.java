package com.ntc.paas.plat.dao;

import java.util.List;

import com.github.pagehelper.Page;
import com.ntc.paas.plat.entity.RoleMenu;
import com.ntc.paas.plat.model.RoleMenuQuery;

public interface RoleMenuDao {

	int deleteByPrimaryKey(Long id);

	int insert(RoleMenu record);

	int insertSelective(RoleMenu record);

	RoleMenu selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(RoleMenu record);

	int updateByPrimaryKey(RoleMenu record);

	List<RoleMenu> queryListByCond(RoleMenuQuery record);

	Page<RoleMenu> queryListByPage(RoleMenuQuery record);

	void updateStatus(RoleMenu record);

	RoleMenu queryByRoleId(Long role_id);
}