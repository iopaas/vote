package com.ntc.paas.plat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.ntc.paas.plat.entity.Account;
import com.ntc.paas.plat.model.AccountPwd;
import com.ntc.paas.plat.model.AccountQuery;

public interface AccountDao {

	int deleteByPrimaryKey(Long id);

	int insert(Account record);

	int insertSelective(Account record);

	Account selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Account record);

	int updateByPrimaryKey(Account record);

	List<Account> queryListByCond(AccountQuery record);

	Page<Account> queryListByPage(AccountQuery record);

	void updateStatus(Account record);

	Account queryForLogin(@Param("account_number") String account_number,
			@Param("account_pwdmd5") String account_pwdmd5);

	void updateLastLoginTime(@Param("id") Long id, @Param("last_login_time") String last_login_time);

	Account queryByAccountNumber(@Param("account_number") String account_number);

	void updateAccountPwd(AccountPwd accountPwd);
}