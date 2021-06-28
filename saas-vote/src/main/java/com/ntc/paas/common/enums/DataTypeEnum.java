package com.ntc.paas.common.enums;

/**
 * 监测项目类数据类型-常量
 * 
 *
 * @author xu
 * @date 2018年6月15日 下午4:56:31
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum DataTypeEnum {

	All(0, "全部"), Switch(1, "开关或档位"), Time(2, "时刻"), Numeric(3, "数值"), Other(9, "其他");

	private Integer type;
	private String desc;

	DataTypeEnum(Integer type, String desc) {
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

		for (DataTypeEnum _enum : DataTypeEnum.values()) {
			if (_enum.getType() == type) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
