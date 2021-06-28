package com.ntc.paas.common.enums;

/**
 * 关系标识(未添加/已添加)
 * 
 *
 * @author allen.yuan
 * @date 2018年5月14日 下午1:56:31
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum RelationFlagEnum {

	All(0, "全部"), Yes(1, "已添加"), No(2, "未添加");

	private Integer flag;
	private String desc;

	RelationFlagEnum(Integer flag, String desc) {
		this.flag = flag;
		this.desc = desc;
	}

	public Integer getFlag() {
		return flag;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(int flag) {

		for (RelationFlagEnum _enum : RelationFlagEnum.values()) {
			if (_enum.getFlag() == flag) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
