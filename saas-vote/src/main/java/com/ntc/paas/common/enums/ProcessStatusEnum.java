package com.ntc.paas.common.enums;

/**
 * 处理状态-枚举值
 * 
 *
 * @author allen.yuan
 * @date 2018年5月14日 下午1:56:31
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum ProcessStatusEnum {

	All(0, "全部"), Processed(1, "已处理"), Untreated(2, "未处理");

	private Integer status;
	private String desc;

	ProcessStatusEnum(Integer status, String desc) {
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

		for (ProcessStatusEnum _enum : ProcessStatusEnum.values()) {
			if (_enum.getStatus() == status) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
