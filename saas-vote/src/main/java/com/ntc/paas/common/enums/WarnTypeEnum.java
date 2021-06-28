package com.ntc.paas.common.enums;

/**
 * 预警状态-常量
 * 
 *
 * @author xu
 * @date 2018年6月15日 下午4:56:31
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum WarnTypeEnum {

	All(0, "全部"), EquipmentAlarm(1, "设备报警"), SafetyAlarm(2, "安全报警"), ProductionAlarm(3, "生产报警");

	private Integer type;
	private String desc;

	WarnTypeEnum(Integer type, String desc) {
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

		for (WarnTypeEnum _enum : WarnTypeEnum.values()) {
			if (_enum.getType() == type) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
