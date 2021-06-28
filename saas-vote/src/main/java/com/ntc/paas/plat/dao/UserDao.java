package com.ntc.paas.plat.dao;

import com.github.pagehelper.Page;
import com.ntc.paas.plat.entity.User;
import com.ntc.paas.plat.model.VoteUserQuery;

public interface UserDao {

	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Long id);

	User selectByIdCard(String idCard);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	Page<User> queryListByPage(VoteUserQuery query);
}