package com.ntc.paas.common.enums;

/**
 * 传感器值-类型枚举
 * 
 * @author allen.yuan
 * @date 2018年6月15日 下午6:11:04
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum ValueTypeEnum {

	Zero("0", "否"), One("1", "是");

	private String value;
	private String desc;

	ValueTypeEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(String value) {
		for (ValueTypeEnum _enum : ValueTypeEnum.values()) {
			if (_enum.getValue().equals(value)) {
				return _enum.getDesc();
			}
		}
		return "";
	}
}
