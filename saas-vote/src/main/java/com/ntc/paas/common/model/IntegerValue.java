package com.ntc.paas.common.model;

/**
 * 返回数量-增加&删除&修改&校验是否存在
 * 
 * @author allen.yuan
 * @date 2021年6月25日 下午3:28:11
 */
public class IntegerValue {

	// 业务参数
	private Integer param = 0;

	public IntegerValue() {

	}

	public IntegerValue(Integer param) {
		if (param == null) {
			this.param = 0;
		}
		this.param = Integer.valueOf(param);
	}

	public Integer getParam() {
		return param == null ? 0 : param;
	}

	public void setParam(Integer param) {
		this.param = param;
	}

}
