package com.ntc.paas.common.exception;

/**
 * 业务异常定义类
 * 
 * @author allen.yuan
 * @date 2018年5月19日 下午2:04:20
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
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