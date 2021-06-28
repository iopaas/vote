package com.ntc.paas.plat.model;

import java.io.Serializable;

/**
 * 账号管理-查询条件实体类
 * 
 *
 * @author allen.yuan
 * @date 2018年5月15日 上午9:26:55
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class AccountQuery implements Serializable {

	private static final long serialVersionUID = -2563447234149178532L;

	// 页号
	private Integer pageNum;

	// 页数量
	private Integer pageSize;

	// 账号
	private String account_number;

	// 姓名
	private String account_name;

	// 角色id
	private Long role_id;

	// 账户状态
	private Integer status;
	

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum == null ? 1 : pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize == null ? 10 : pageSize;
	}

	public String getAccount_number() {
		return account_number == null ? "" : account_number.trim();
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_name() {
		return account_name == null ? "" : account_name.trim();
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public Long getRole_id() {
		return role_id == null ? 0 : role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public Integer getStatus() {
		return status == null ? 0 : status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
