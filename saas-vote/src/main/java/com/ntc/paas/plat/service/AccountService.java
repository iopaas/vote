package com.ntc.paas.plat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.enums.StatusEnum;
import com.ntc.paas.common.exception.BusinessException;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.plat.dao.AccountDao;
import com.ntc.paas.plat.entity.Account;
import com.ntc.paas.plat.model.AccountPwd;
import com.ntc.paas.plat.model.AccountQuery;

/**
 * 
 * 账号管理服务实现类
 *
 * @author allen.yuan
 * @date 2018年5月14日 下午3:40:52
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
@Service
public class AccountService {

	private static final Logger log = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	private AccountDao accountDao;

	@Transactional(rollbackFor = BusinessException.class)
	public int insert(Account record) throws BusinessException {

		try {
			accountDao.insert(record);

			return ConmmonEnum.SUCCESS.getCode();
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}

	}

	@Transactional(rollbackFor = BusinessException.class)
	public int deleteByPrimaryKey(Long id, String updateUser) throws BusinessException {

		try {

			Account record2 = accountDao.selectByPrimaryKey(id);
			if (record2.getStatus() == StatusEnum.Enable.getStatus()) {
				return ConmmonEnum.FAILURE.getCode();
			}

			record2.setStatus(StatusEnum.Delete.getStatus());
			record2.setUpdate_user(updateUser);
			record2.setUpdate_time(DateUtil.getDateTime());
			accountDao.updateStatus(record2);

			return ConmmonEnum.SUCCESS.getCode();

		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}

	}

	public Account selectByPrimaryKey(Long id) throws BusinessException {

		try {
			return accountDao.selectByPrimaryKey(id);
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}
	}

	@Transactional(rollbackFor = BusinessException.class)
	public int updateByPrimaryKey(Account record) {

		try {

			accountDao.updateByPrimaryKeySelective(record);

			return ConmmonEnum.SUCCESS.getCode();

		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}

	}

	public List<Account> queryListByCond(AccountQuery record) throws BusinessException {

		try {
			return accountDao.queryListByCond(record);
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}
	}

	public Page<Account> queryListByPage(AccountQuery query) throws BusinessException {

		try {
			PageHelper.startPage(query.getPageNum(), query.getPageSize());
			return accountDao.queryListByPage(query);
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}
	}

	public void updateStatus(Account account) throws BusinessException {

		try {
			accountDao.updateStatus(account);
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}

	}

	public Account queryForLogin(String account_number, String account_pwdmd5) throws BusinessException {

		try {
			return accountDao.queryForLogin(account_number, account_pwdmd5);
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}
	}

	public void updateLastLoginTime(Long id, String last_login_time) throws BusinessException {

		try {
			accountDao.updateLastLoginTime(id, last_login_time);
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}

	}

	public Account queryByAccountNumber(String account_number) throws BusinessException {

		try {
			return accountDao.queryByAccountNumber(account_number);
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}
	}

	public Integer updateAccountPwd(AccountPwd accountPwd) throws BusinessException {

		try {

			Account acct = accountDao.queryByAccountNumber(accountPwd.getAccount_number());
			if (acct != null) {
				accountDao.updateAccountPwd(accountPwd);
			}

			return ConmmonEnum.SUCCESS.getCode();
		} catch (Exception e) {
			log.error("业务异常,ex={}", e);
			throw new BusinessException(e);
		}
	}
}
