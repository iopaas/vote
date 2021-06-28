package com.ntc.paas.plat.model;

import java.io.Serializable;

/**
 * 用户登录实体类
 * 
 * @author allen.yuan
 * @date 2021年6月25日 下午14:11:21
 * @Copyright © 2021 NTC. All Rights Reserved.
 */
public class UserLogin implements Serializable {

	private static final long serialVersionUID = -2563447234149178532L;

	// 身份证
	private String idCard;
	// 邮箱
	private String email;

	public String getIdCard() {
		return idCard == null ? "" : idCard.trim();
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEmail() {
		return email == null ? "" : email.trim();
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
