package com.ntc.paas.common.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Json 助手类
 * 
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class JsonUtil {

	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

	/**
	 * 对象转换成json字符串
	 * 
	 * @date：2015-5-29
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj) {
		try {
			String result = JSON.toJSONString(obj);
			return result;
		} catch (Exception e) {
			log.error("JSON转换异常,ex={}", e);
		}
		return "";
	}

	/**
	 * @Description：通过fastjson生成json格式字符串 @date：2015-5-29
	 * @param t
	 * @return
	 */
	public static <T extends Object> String toJsonString(T t) {
		try {
			if (t == null) {
				return "";
			}
			StringBuilder builder = new StringBuilder("");
			builder.append(JSON.toJSONStringWithDateFormat(t, "yyyy-MM-dd HH:mm:ss",
					SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue,
					SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty,
					SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty));
			return builder.append("").toString();
		} catch (Exception e) {
			log.error("JSON转换异常,ex={}", e);
		}
		return "";
	}

	/**
	 * @Description：通过fastjson生成json格式字符串，不转换成序列化的字段 @date：2015-5-29
	 * @param t
	 * @return
	 */
	public static <T extends Object> String toJsonStringNoSeria(T t) {
		try {
			StringBuilder builder = new StringBuilder("");
			builder.append(JSON.toJSONStringWithDateFormat(t, "yyyy-MM-dd HH:mm:ss"));
			return builder.append("").toString();
		} catch (Exception e) {
			log.error("JSON转换异常,ex={}", e);
		}
		return "";
	}

	/**
	 * @Description：通过fastjson生成json格式字符串 @date：2015-5-29
	 * @param t
	 * @return
	 */
	public static <T extends Object> String toJsonStringNoFormat(T t) {

		try {
			StringBuilder builder = new StringBuilder("");
			builder.append(JSON.toJSONString(t));
			return builder.append("").toString();
		} catch (Exception e) {
			log.error("JSON转换异常,ex={}", e);
		}
		return "";
	}

	/**
	 * @Description：将json格式的字符串转换成制定的对象 @date：2015-5-29
	 * @param jsonstr
	 * @param ref
	 * @return
	 * @throws Exception
	 */
	public static <T extends Object> T parseToObject(String jsonstr, TypeReference<T> ref) {
		try {
			if (jsonstr == null || "".equals(jsonstr.trim())) {
				return null;
			}
			jsonstr = jsonstr.trim();
			return JSONObject.parseObject(jsonstr, ref);
		} catch (Exception e) {
			log.error("JSON转换异常,ex={}", e);
		}
		return null;
	}

	/**
	 * @Description：将json格式的字符串转换成制定的对象 @date：2015-5-29
	 * @param jsonstr
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public static <T extends Object> T parseToObject(String jsonstr, Class<T> clz) {
		try {

			if (jsonstr == null || "".equals(jsonstr.trim())) {
				return null;
			}
			jsonstr = jsonstr.trim();
			return JSONObject.parseObject(jsonstr, clz);
		} catch (Exception e) {
			log.error("JSON转换异常,ex={}", e);
		}
		return null;

	}

	/**
	 * @Description：将json格式的字符串转换成制定的数组列表
	 * @param jsonstr
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public static <T extends Object> List<T> parseToList(String jsonstr, Class<T> clz) {
		try {

			if (jsonstr == null || "".equals(jsonstr.trim())) {
				return null;
			}
			jsonstr = jsonstr.trim();
			return (List<T>) JSONArray.parseArray(jsonstr, clz);

		} catch (Exception e) {
			log.error("JSON转换异常,ex={}", e);
		}
		return null;

	}
}
