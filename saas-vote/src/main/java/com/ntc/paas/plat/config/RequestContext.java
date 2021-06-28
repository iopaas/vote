package com.ntc.paas.plat.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntc.paas.common.model.LoginModel;

/**
 * ClassName:RequestContext <br/>
 * Function: 请求上下文信息. <br/>
 * Reason: 请求上下文信息. <br/>
 *
 * @version 1.0
 * @since
 * @see
 */
public class RequestContext {

	/**
	 * 请求对象
	 */
	private HttpServletRequest request;

	/**
	 * 响应对象
	 */
	private HttpServletResponse response;

	private LoginModel loginInfoVo;

	public RequestContext() {

	}

	public RequestContext(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public LoginModel getLoginInfoVo() {
		return loginInfoVo == null ? new LoginModel() : loginInfoVo;
	}

	public void setLoginInfoVo(LoginModel loginInfoVo) {
		this.loginInfoVo = loginInfoVo;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
