package com.ntc.paas.common.enums;

/**
 * 定义api请求结果状态(失败/成功/未授权) <br>
 * 2.定义接口结果错误码&描述
 * 
 *
 * @author allen.yuan
 * @date 2018年5月14日 下午1:56:31
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public enum ConmmonEnum {

	All(0, "全部"), SUCCESS(1, "成功"), UN_AUTHORIZED(2, "未授权"), FAILURE(-1, "失败"), EXISTS(-2, "不能重复投递"),
	EXPIRE(-3, "投票已过期"),

	CODE_100011(-100011, "参数无效"),

	CODE_100012(-100012, "查询失败"),

	CODE_100013(-100013, "添加失败"),

	CODE_100014(-100014, "删除失败"),

	CODE_100015(-100015, "修改失败");

	private Integer code;
	private String desc;

	ConmmonEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(Integer code) {

		for (ConmmonEnum _enum : ConmmonEnum.values()) {
			if (_enum.getCode() == code) {
				return _enum.getDesc();
			}
		}
		return "";
	}
}
