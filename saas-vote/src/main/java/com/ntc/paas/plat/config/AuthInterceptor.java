package com.ntc.paas.plat.config;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ntc.paas.common.config.PublicKey;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.model.LoginModel;
import com.ntc.paas.common.model.RespModel;
import com.ntc.paas.common.model.RespUtil;
import com.ntc.paas.common.redis.RedisUtil;
import com.ntc.paas.common.util.JsonUtil;

/**
 * 登录回话拦截器
 * 
 * @author allen.yuan
 * @date 2021年6月22日 下午13:29:15
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {

		String token = httpServletRequest.getHeader("token");
		boolean noLogin = allowPass(object);
		if(StringUtils.isEmpty(token)) {
			if(noLogin) {
			   return true;
			}else {
			   this.responseMessage(httpServletResponse, ConmmonEnum.UN_AUTHORIZED.getCode());
			   return false;
			}
		}
		
		String sessionKey = String.format(PublicKey.LOGIN_SESSION, token);
		String obj = redisUtil.get(sessionKey);
		if (Objects.isNull(obj)) {
			this.responseMessage(httpServletResponse, ConmmonEnum.UN_AUTHORIZED.getCode());
			return false;
		}
		LoginModel loginModel = JsonUtil.parseToObject(obj, LoginModel.class);
		if (loginModel != null) {

			RequestContext requestContext = new RequestContext(httpServletRequest, httpServletResponse);
			requestContext.setLoginInfoVo(loginModel);
			RequestContextMgr.setLocalContext(requestContext);
			return true;
		}

		this.responseMessage(httpServletResponse, ConmmonEnum.UN_AUTHORIZED.getCode());
		return false;
	}

	public boolean allowPass(Object object) {

		// 如果不是映射到方法直接通过
		if (!(object instanceof HandlerMethod)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod) object;
		NoLoginAllow declaredAnnotation = handlerMethod.getBeanType().getDeclaredAnnotation(NoLoginAllow.class);
		if (null != declaredAnnotation && declaredAnnotation.required()) {
			return true;
		}
		Method method = handlerMethod.getMethod();
		// 检查是否有NoLoginAllow注释，有则跳过认证
		if (method.isAnnotationPresent(NoLoginAllow.class)) {
			NoLoginAllow noLoginRequired = method.getAnnotation(NoLoginAllow.class);
			if (noLoginRequired.required()) {
				return true;
			}
		}
		return false;
	}

	public void responseMessage(HttpServletResponse response, Integer status) {
		ServletOutputStream writer = null;

		try {
			RespModel<?> resp = RespUtil.unAuthorized();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=UTF-8");
			writer = response.getOutputStream();
			writer.write(JsonUtil.objectToJson(resp).getBytes());
			writer.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

		RequestContextMgr.clearContext();
	}

}
