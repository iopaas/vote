package com.ntc.paas.common.model;

import java.util.HashMap;

import com.ntc.paas.common.enums.ConmmonEnum;

/**
 * API接口返回实体结构
 * 
 *
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class RespModel<T> {

	/* 错误码 */
	private int code = ConmmonEnum.SUCCESS.getCode();

	/* 提示信息 */
	private String msg = ConmmonEnum.SUCCESS.getDesc();

	/* 具体内容 */
	private T data = null;

	public RespModel() {

	}

	public RespModel(int code, String msg) {

		this.code = code;
		this.msg = msg;
	}

	public RespModel(Integer code, String msg, T data) {

		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		if (msg == null) {
			msg = "";
		}
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T getData() {
		if (data == null) {
			data = (T) new HashMap();
		}
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
