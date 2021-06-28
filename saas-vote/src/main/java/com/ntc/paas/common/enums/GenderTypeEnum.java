package com.ntc.paas.common.enums;

/**
 * 性别类型定义
 * 
 * @author allen.yuan
 * @date 2018年5月19日 下午3:13:55
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum GenderTypeEnum {

	All(0, "全部"), Male(1, "男"), Female(2, "女");

	private Integer type;
	private String desc;

	GenderTypeEnum(Integer type, String desc) {
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

		for (GenderTypeEnum _enum : GenderTypeEnum.values()) {
			if (_enum.getType() == type) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
