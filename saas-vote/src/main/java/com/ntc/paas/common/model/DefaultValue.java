package com.ntc.paas.common.model;

/**
 * 返回数量-增加&删除&修改&校验是否存在
 * 
 * @author allen.yuan
 * @date 2018年6月1日 上午9:03:39
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class DefaultValue {

	// 业务参数
	private String param = "";

	public DefaultValue() {

	}

	public DefaultValue(String param) {
		this.param = param;
	}

	public DefaultValue(Object param) {
		if (param == null) {
			this.param = "";
		}
		this.param = String.valueOf(param);
	}

	public String getParam() {
		return param == null ? "" : param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
