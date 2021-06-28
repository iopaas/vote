package com.ntc.paas.plat.model;

import java.io.Serializable;

/**
 * 账号管理-修改密码实体类
 * 
 *
 * @author allen.yuan
 * @date 2018年5月15日 上午9:26:55
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class AccountPwd implements Serializable {

	private static final long serialVersionUID = -2563447234149178532L;
	
	// 修改类型(1:个人重置密码;2:管理员重置密码)
	private Integer update_type = 0; 

	// 账号
	private String account_number; 
	
	// 密码
	private String account_pwd; 
	
	// 新密码
	private String account_pwd_new; 
	
	// 更新企业
	private String update_user; 

	// 更新时间
	private String update_time; 

	public Integer getUpdate_type() {
		return update_type == null ? 0 : update_type;
	}

	public void setUpdate_type(Integer update_type) {
		this.update_type = update_type;
	}

	public String getAccount_number() {
		return account_number == null ? "" : account_number.trim();
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_pwd() {
		return account_pwd == null ? "" : account_pwd.trim();
	}

	public void setAccount_pwd(String account_pwd) {
		this.account_pwd = account_pwd;
	}

	public String getAccount_pwd_new() {
		return account_pwd_new == null ? "" : account_pwd_new.trim();
	}

	public void setAccount_pwd_new(String account_pwd_new) {
		this.account_pwd_new = account_pwd_new;
	}

	public String getUpdate_user() {
		return update_user == null ? "" : update_user.trim();
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	public String getUpdate_time() {
		return update_time == null ? "" : update_time.trim();
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

}
