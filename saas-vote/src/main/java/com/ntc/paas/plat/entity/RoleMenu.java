package com.ntc.paas.plat.entity;

import java.io.Serializable;

public class RoleMenu extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8629347523222977996L;

	// 主键
	private Long id;

	// 角色Id
	private Long role_id;

	// 菜单id
	private String menu_ids;

	// 全部加载
	private Integer menu_all;

	// 状态
	private Integer status;

	public Long getId() {
		return id == null ? 0 : id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRole_id() {
		return role_id == null ? 0 : role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getMenu_ids() {
		return menu_ids == null ? "" : menu_ids.trim();
	}

	public void setMenu_ids(String menu_ids) {
		this.menu_ids = menu_ids;
	}

	public Integer getMenu_all() {
		return menu_all == null ? 0 : menu_all;
	}

	public void setMenu_all(Integer menu_all) {
		this.menu_all = menu_all;
	}

	public Integer getStatus() {
		return status == null ? 0 : status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}