package com.ntc.paas.common.model;

/**
 * 登录结果-实体类
 * 
 *
 * @author allen.yuan
 * @date 2018年5月14日 下午3:28:36
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class LoginModel {

	private Integer status = 1; // 状态(1:成功;-1失败)
	private String desc = "成功"; // 描述

	private Integer type = 1;// (1:登录;2:退出)
	private Long account_id; // 账号ID
	private String account_number = ""; // 账号名称
	private Integer role_id = 1; // 角色ID
	private String token = "";// 当前会话token
	private Long timestamp = 0L; // 时间戳

	public LoginModel() {

	}

	public LoginModel(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public LoginModel(Integer status, String desc, Integer type, Long account_id, String account_number,
			Integer role_id, Long timestamp) {
		this.status = status;
		this.desc = desc;
		this.type = type;
		this.account_id = account_id;
		this.account_number = account_number;
		this.role_id = role_id;
		this.timestamp = timestamp;
	}

	public LoginModel(Integer status, String desc, Integer type, Long account_id, String account_number,
			Integer role_id, String token, Long timestamp) {
		this.status = status;
		this.desc = desc;
		this.type = type;
		this.account_id = account_id;
		this.account_number = account_number;
		this.role_id = role_id;
		this.token = token;
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getAccount_id() {
		return account_id == null ? null : account_id;
	}

	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
