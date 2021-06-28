package com.ntc.paas.plat.config;

/**
 * ClassName:RequestContextMgr <br/>
 * Function: 请求上下文管理对象. <br/>
 * Reason: 请求上下文管理对象. <br/>
 *
 * @version 1.0
 * @since
 * @see
 */
public class RequestContextMgr {

	private static final ThreadLocal<RequestContext> LOCAL_CONTEXT = new ThreadLocal<>();

	/**
	 * 
	 * setLocalContext:(设置请求上下文环境). <br/>
	 *
	 * @author songs
	 * @Param context
	 * @since
	 */
	public static void setLocalContext(RequestContext context) {
		LOCAL_CONTEXT.set(context);
	}

	/**
	 * 
	 * getLocalContext:(获取请求上下文环境). <br/>
	 *
	 * @author songs
	 * @Return
	 * @since
	 */
	public static RequestContext getLocalContext() {
		RequestContext context = LOCAL_CONTEXT.get();
		return context == null ? new RequestContext() : context;
	}

	/**
	 * 
	 * clearContext:(清空请求上下文环境). <br/>
	 *
	 * @author songs
	 * @since
	 */
	public static void clearContext() {
		LOCAL_CONTEXT.remove();
	}
}
