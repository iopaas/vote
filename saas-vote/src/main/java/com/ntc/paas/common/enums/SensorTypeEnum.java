package com.ntc.paas.common.enums;

/**
 * 传感器类型
 * 
 * @author allen.yuan
 * @date 2018年6月15日 下午6:11:04
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum SensorTypeEnum {

	All(0, "全部"), Numerical(1, "数值型"), Switch(2, "开关型"), Location(3, "定位型"), Photo(4, "图片型"), Switch_Uncontrollable(5,
			"开关型不可控"), Gear(6, "挡位型"), Video(7, "视频型");

	private Integer type;
	private String desc;

	SensorTypeEnum(Integer type, String desc) {
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

		for (SensorTypeEnum _enum : SensorTypeEnum.values()) {
			if (_enum.getType() == type) {
				return _enum.getDesc();
			}
		}
		return "";
	}
}
