package com.ntc.paas.common.enums;

/**
 * 数据存储模式
 * 
 * @author allen.yuan
 * @date 2018年9月3日 上午9:44:39
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum StorageModeEnum {

	All(0, "全部"), No(1, "不存"), Change(2, "变存"), Daily(3, "日存"), Synchro(4, "同步存");

	private Integer type;
	private String desc;

	StorageModeEnum(Integer type, String desc) {
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

		for (StorageModeEnum _enum : StorageModeEnum.values()) {
			if (_enum.getType() == type) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
