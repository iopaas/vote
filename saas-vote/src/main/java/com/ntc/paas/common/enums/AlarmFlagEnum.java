package com.ntc.paas.common.enums;

/**
 * 是否预警-枚举
 * 
 * @author allen.yuan
 * @date 2018年6月15日 下午6:11:04
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum AlarmFlagEnum {

	No(0, "否"), Alarm(1, "预警"), Tip(2, "提示");

	private Integer value;
	private String desc;

	AlarmFlagEnum(Integer value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public Integer getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(int value) {

		for (AlarmFlagEnum _enum : AlarmFlagEnum.values()) {
			if (_enum.getValue() == value) {
				return _enum.getDesc();
			}
		}
		return "";
	}
}
