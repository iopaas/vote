package com.ntc.paas.common.model;

import com.ntc.paas.common.enums.ConmmonEnum;

/**
 * API接口返回助手类
 * 
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class RespUtil {

	/**
	 * 请求返回成功-无data
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static RespModel success() {
		return success(null);
	}

	/**
	 * 请求返回成功-返回data
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> RespModel success(T object) {
		RespModel msg = new RespModel();
		msg.setData(object);
		return msg;
	}

	/**
	 * 请求返回失败-无data
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static RespModel unAuthorized() {

		RespModel msg = new RespModel();
		msg.setCode(ConmmonEnum.UN_AUTHORIZED.getCode());
		msg.setMsg(ConmmonEnum.UN_AUTHORIZED.getDesc());
		return msg;
	}

	/**
	 * 请求返回失败-返回data
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static RespModel failure() {

		RespModel msg = new RespModel();
		msg.setCode(ConmmonEnum.FAILURE.getCode());
		msg.setMsg(ConmmonEnum.FAILURE.getDesc());
		return msg;
	}

	/**
	 * 请求返回失败-无data
	 * 
	 * @param code
	 * @param desc
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static RespModel failure(Integer code, String desc) {

		RespModel msg = new RespModel();
		msg.setCode(code);
		msg.setMsg(desc);
		return msg;
	}

	/**
	 * 请求返回失败-返回data
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> RespModel failure(T object) {

		RespModel msg = new RespModel();
		msg.setCode(ConmmonEnum.FAILURE.getCode());
		msg.setMsg(ConmmonEnum.FAILURE.getDesc());
		msg.setData(object);
		return msg;
	}

	/**
	 * 请求返回失败-返回data
	 * 
	 * @param code
	 * @param desc
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> RespModel failure(Integer code, String desc, T object) {

		RespModel msg = new RespModel();
		msg.setCode(code);
		msg.setMsg(desc);
		msg.setData(object);
		return msg;
	}
}
