package com.ntc.paas.plat.model;

import java.io.Serializable;

/**
 * 角色管理-查询条件实体类
 * 
 *
 * @author allen.yuan
 * @date 2018年5月15日 上午9:26:55
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class RoleQuery implements Serializable {

	private static final long serialVersionUID = -2563427234149178532L;
	
	// 页号
	private Integer pageNum; 
	
	// 页数量
	private Integer pageSize; 
	
	// 名称
	private String role_name; 

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

	public String getRole_name() {
		return role_name == null ? "" : role_name.trim();
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
}
