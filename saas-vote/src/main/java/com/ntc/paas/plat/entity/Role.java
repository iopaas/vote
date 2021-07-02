package com.ntc.paas.plat.entity;

import java.io.Serializable;

/**
 * 
 *
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class Role extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8512867492247225161L;

	// 主键ID
	private Long id;

	// 角色名称
	private String role_name;

	// 角色描述
	private String role_desc;

	// 拥有角色的用户数量
	private Integer role_count;

	// 状态
	private Integer status;

	public Long getId() {
		return id == null ? 0 : id ;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole_name() {
	   return role_name == null ? "" : role_name.trim();
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_desc() {
		return  role_desc == null ? "" : role_desc.trim();
	}

	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}

	public Integer getRole_count() {
		return role_count == null ? 0 : role_count;
	}

	public void setRole_count(Integer role_count) {
		this.role_count = role_count;
	}

	public Integer getStatus() {
		return status == null ? 0 : status ;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}