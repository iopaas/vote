package com.ntc.paas.plat.entity;

import java.io.Serializable;

/**
 * 账号表-实体类
 * 
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class Account extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8512867492247225166L;

	// 主键
	private Long id;

	// 账号
	private String account_number;

	// 密码
	private String account_pwd;

	// 加密密码
	private String account_pwdmd5;

	// 用户名称
	private String account_name;

	// 手机号
	private String mobile;

	// 角色Id(1:管理员：2：用户)
	private Integer role_id;

	// 上次登录时间
	private String last_login_time;

	// 状态(1:启用;2:禁用;3:删除)
	private Integer status;

	private Role role;

	public Long getId() {
		return id == null ? 0 : id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAccount_pwdmd5() {
		return account_pwdmd5 == null ? "" : account_pwdmd5.trim();
	}

	public void setAccount_pwdmd5(String account_pwdmd5) {
		this.account_pwdmd5 = account_pwdmd5;
	}

	public String getAccount_name() {
		return account_name == null ? "" : account_name.trim();
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getMobile() {
		return mobile == null ? "" : mobile.trim();
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getRole_id() {
		return role_id == null ? 0 : role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getLast_login_time() {
		this.last_login_time = (last_login_time == null) ? "" : last_login_time.trim();
		if (this.last_login_time.length() > 19) {
			this.last_login_time = last_login_time.substring(0, 19);
		}
		return last_login_time;
	}

	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}

	public Integer getStatus() {
		return status == null ? 0 : status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}