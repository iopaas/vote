package com.ntc.paas.common.enums;

/**
 * 监控类型-枚举
 * 
 * @author allen.yuan
 * @date 2018年6月15日 下午6:11:04
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum MonitorStatusEnum {

	All(0, "全部"), Normal(1, "正常"), Alarm(2, "预警"), Offline(3, "离线");

	private Integer value;
	private String desc;

	MonitorStatusEnum(Integer value, String desc) {
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

		for (MonitorStatusEnum _enum : MonitorStatusEnum.values()) {
			if (_enum.getValue() == value) {
				return _enum.getDesc();
			}
		}
		return "";
	}
}
