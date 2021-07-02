package com.ntc.paas.plat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ntc.paas.common.exception.BusinessException;
import com.ntc.paas.plat.dao.UserDao;
import com.ntc.paas.plat.entity.User;
import com.ntc.paas.plat.model.VoteUserQuery;

/**
 * 被先投票人管理业务方法
 * 
 * @author allen.yuan
 * @date 2021年6月23日 上午10:21:40
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public int insert(User record) throws BusinessException {

		return userDao.insert(record);
	}

	public int deleteByPrimaryKey(Long id) throws BusinessException {

		return userDao.deleteByPrimaryKey(id);
	}

	public User selectByPrimaryKey(Long id) throws BusinessException {

		return userDao.selectByPrimaryKey(id);
	}

	public User selectByIdCard(String idCard) throws BusinessException {

		return userDao.selectByIdCard(idCard);
	}

	public int updateByPrimaryKey(User record) throws BusinessException {

		return userDao.updateByPrimaryKey(record);
	}

	public Page<User> queryListByPage(VoteUserQuery query) throws BusinessException {

		PageHelper.startPage(query.getPageNum(), query.getPageSize());
		return userDao.queryListByPage(query);
	}

}
