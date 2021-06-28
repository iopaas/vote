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
public class RoleMenuQuery implements Serializable {

	private static final long serialVersionUID = -2563427234149178536L;

	// 页号
	private Integer pageNum;

	// 页数量
	private Integer pageSize;

	// 角色ID
	private Long role_id;

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

	public Long getRole_id() {
		return role_id == null ? 0 : role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

}
