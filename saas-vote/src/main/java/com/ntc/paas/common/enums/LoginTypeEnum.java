package com.ntc.paas.common.enums;

/**
 * 登录类型定义
 * 
 * @author allen.yuan
 * @date 2018年5月19日 下午3:13:55
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum LoginTypeEnum {

	Login(1, "登录"), Logout(2, "退出");

	private Integer type;
	private String desc;

	LoginTypeEnum(Integer type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(int type) {

		for (LoginTypeEnum _enum : LoginTypeEnum.values()) {
			if (_enum.getType() == type) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
