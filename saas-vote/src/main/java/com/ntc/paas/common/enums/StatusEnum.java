package com.ntc.paas.common.enums;

/**
 * 状态(启用/禁用)
 * 
 *
 * @author allen.yuan
 * @date 2021年6月25日 下午6:28:13
 */
public enum StatusEnum {

	All(0, "全部"), Enable(1, "启用"), Disable(2, "禁用"), Delete(3, "删除");

	private Integer status;
	private String desc;

	StatusEnum(Integer status, String desc) {
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

		for (StatusEnum _enum : StatusEnum.values()) {
			if (_enum.getStatus() == status) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
