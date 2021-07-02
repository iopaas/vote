package com.ntc.paas.common.enums;

/**
 * 角色类型
 * 
 * @author allen.yuan
 * @date 2021年6月24日 下午5:28:06
 */
public enum RoleTypeEnum {

	All(0, "全部"), Admin(1, "后台"), User(2, "用户"),;

	private Integer type;
	private String desc;

	RoleTypeEnum(Integer type, String desc) {
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

		for (RoleTypeEnum _enum : RoleTypeEnum.values()) {
			if (_enum.getType() == type) {
				return _enum.getDesc();
			}
		}
		return "";
	}

}
