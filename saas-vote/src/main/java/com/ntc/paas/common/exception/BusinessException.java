package com.ntc.paas.common.exception;

/**
 * 业务异常定义类
 * 
 * @author allen.yuan
 * @date 2021年6月25日 下午3:28:11
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3525626682829091711L;

	public BusinessException(Exception e) {
		super(e);
	}

	public BusinessException(String msg) {
		super(msg);
	}
}