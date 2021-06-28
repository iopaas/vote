package com.ntc.paas.common.enums;

/**
 * 设置状态
 *
 * @author allen.yuan
 * @date 2018年5月14日 下午1:56:31
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum EquipmentStatusEnum {

	All(0, "全部"), Online(1, "在线"), Offline(2, "离线"), Trouble(3, "预警");

	private Integer status;
	private String desc;

	EquipmentStatusEnum(Integer status, String desc) {
		this.status = status;
		this.desc = desc;
	}

	public Integer getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(int status) {

		for (EquipmentStatusEnum _enum : EquipmentStatusEnum.values()) {
			if (_enum.getStatus() == status) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
