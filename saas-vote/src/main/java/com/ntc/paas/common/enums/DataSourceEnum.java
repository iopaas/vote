package com.ntc.paas.common.enums;

/**
 * 监测项目类数据来源类型-常量
 * 
 *
 * @author xu
 * @date 2018年6月15日 下午4:56:31
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum DataSourceEnum {

	All(0, "全部"), IOT(1, "IOT"), IOT_Transfer(2, "IOT改档位值"), Number_Transfer(3, "数转档"), Merge_Gear(4,
			"合并22档"), Catalog_Code(5, "监测项目类编码");

	private Integer type;
	private String desc;

	DataSourceEnum(Integer type, String desc) {
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

		for (DataSourceEnum _enum : DataSourceEnum.values()) {
			if (_enum.getType() == type) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
