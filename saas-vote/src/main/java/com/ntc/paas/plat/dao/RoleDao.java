package com.ntc.paas.plat.dao;

import java.util.List;

import com.github.pagehelper.Page;
import com.ntc.paas.plat.entity.Role;
import com.ntc.paas.plat.model.RoleQuery;

public interface RoleDao {

	int deleteByPrimaryKey(Long id);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<Role> queryListByCond(RoleQuery record);

	Page<Role> queryListByPage(RoleQuery record);

	void updateStatus(Role record);
}