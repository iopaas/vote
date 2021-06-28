package com.ntc.paas.plat.log;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ntc.paas.common.util.JsonUtil;

/**
 * spring日志切面拦截器及处理-spring自定义注解
 *
 * @author allen.yuan
 * @date 2021年6月23日 上午10:06:40
 * @Copyright © 2021 NTC. All Rights Reserved.
 */
@Aspect
@Component
public class LogAspect {

	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

	/**
	 * 定义拦截切面
	 * 
	 * @info @SystemLog 日志注解
	 */
	@Pointcut("@annotation(com.ntc.paas.plat.log.SystemLog)")
	public void webLog() {

	}

	/**
	 * 环绕切面处理逻辑
	 * 
	 * @param point
	 * @param systemLog
	 * @return result
	 */
	@Around("webLog() && @annotation(systemLog)")
	public Object around(ProceedingJoinPoint point, SystemLog systemLog) {

		long startTime = System.currentTimeMillis();// 设置请求起始时间
		String methodName = point.getSignature().getName() + "()";// 业务执行方法名

		// 业务执行前日志方法
		beforLog(point, systemLog, methodName);

		Object result = null;
		try {
			result = point.proceed();
		} catch (Throwable ex) {
			log.error("业务异常，ex={}", ex);
		}

		// 业务执行后日志方法
		afterLog(systemLog, methodName, startTime, result);

		return result;
	}

	/**
	 * 业务执行前日志方法
	 * 
	 * @param point
	 * @param systemLog
	 * @param methodName
	 */
	private void beforLog(ProceedingJoinPoint point, SystemLog systemLog, String methodName) {

		try {

			// 获得参数列表
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String[] argNames = getFieldsName(point); // 参数名
			Object[] argValues = point.getArgs(); // 参数值
			if ((argNames != null) && (argValues != null) && (argNames.length == argValues.length)) {
				int index = 0;
				for (String _arg : argNames) {
					if (_arg != null) {
						Object _value = argValues[index];
						// 过滤特殊类型
						if (_value instanceof MultipartFile || _value instanceof HttpServletRequest
								|| _value instanceof HttpServletResponse) {
							continue;
						}
						if (_value instanceof Object) {
							_value = JsonUtil.toJsonString(_value);
						}
						paramMap.put(_arg, _value);
						index++;
					}
				}
			}

			// 转换参数列表
			int index = 0;
			StringBuffer buffer = new StringBuffer();
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				buffer.append(entry.getKey()).append("=" + entry.getValue());
				index++;
				if ((index < paramMap.size())) {
					buffer.append(", "); // 多个参数以","分割
				}
			}
			paramMap = null;
			String paramStr = buffer.toString();
			buffer.setLength(0);// 清空buffer

			// 打印请求参数
			buffer.append("请求信息::");
			buffer.append("logic=>[desc=" + systemLog.desc() + ", ");
			buffer.append("method=" + methodName + ", ");
			buffer.append("opType=" + systemLog.opType() + "], ");
			buffer.append("params=>[").append(paramStr).append("]");
			log.info(buffer.toString());// 输出日志

		} catch (Exception e) {
			log.error("业务异常，ex={}", e);
		}
	}

	/**
	 * 业务执行后日志方法
	 * 
	 * @param systemLog
	 * @param methodName
	 * @param startTime
	 * @param result
	 */
	private void afterLog(SystemLog systemLog, String methodName, long startTime, Object result) {

		try {

			// 计算执行时间
			long spendTime = (System.currentTimeMillis() - startTime);
			// 转换返回结果
			String respJson = JsonUtil.toJsonString(result);

			StringBuffer buffer = new StringBuffer("响应信息::");// 打印请求参数
			buffer.append("logic=>[desc=" + systemLog.desc() + ", ");
			buffer.append("method=" + methodName + ", ");
			buffer.append("times=" + spendTime + "ms], ");
			buffer.append("result=>");
			buffer.append(respJson);
			log.info(buffer.toString());// 输出日志

		} catch (Exception e) {
			log.error("业务异常，ex={}", e);
		}
	}

	/**
	 * 返回方法的参数名 数组
	 * 
	 * @param point
	 * @return paramNames
	 */
	private static String[] getFieldsName(JoinPoint point) {

		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		// 通过这获取到方法的所有参数名称的字符串数组
		String[] parameterNames = methodSignature.getParameterNames();
		return parameterNames;
	}

}
