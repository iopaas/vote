package com.ntc.paas.common.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 *
 * @author allen.yuan
 * @date 2018年5月14日 上午11:28:40
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class StringUtil {

	public static final String ALL_MOBILE = "^[1](([3]|[4]|[5]|[7]|[8])[0-9]{1})[0-9]{8}$";
	public static final String PARAM_VALIDE = "\\#[^\\#]+\\#";
	private static final Pattern ALL_MOBILE_PATTERN = Pattern.compile(ALL_MOBILE);

	private static final String CHINESE_TEXT = "[\u4E00-\u9FA5]{2,20}";
	private static final Pattern CHINESE_TEXT_PATTERN = Pattern.compile(CHINESE_TEXT);

	private static final String CHINESE_LEFT_NUM_TEXT = "^[\u4E00-\u9FA5a-zA-Z0-9]{2,20}$";
	private static final Pattern CHINESE_LEFT_NUM_TEXT_PATTERN = Pattern.compile(CHINESE_LEFT_NUM_TEXT);
	public static final String CODE_VILIDE = "^[0-9]{4,10}$";

	/**
	 * 判断给入的字符串是否为空,null、""、" "都表示空字符串
	 * 
	 * @param str
	 *            待判定的字符串
	 * @return 空符串返回true，否则返回false
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.trim()) || "null".equals(str.trim())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 非空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 返回字符串的值，如果字符串为空则返回默认值
	 * 
	 * @param src
	 *            字符串
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public static final String nvl(String src, String defaultValue) {
		if (src != null && src.length() > 0) {
			return src;
		} else {
			return defaultValue;
		}
	}

	public static final String nvl(String src) {
		return nvl(src, "");
	}

	/**
	 * 得到当前日期/时间字符串
	 * 
	 * @return 返回日期/时间字符串
	 */
	public static String getNowDateTimeString(String type) {
		Date date = new Date();
		SimpleDateFormat formattxt = new SimpleDateFormat(type);
		return formattxt.format(date);
	}

	/**
	 * 生成签名
	 * 
	 * @return
	 */
	public static String generateAppKey() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").trim();
		return uuid;
	}

	public static boolean validZH_CNMobile(String mobile) {
		Matcher matcher = ALL_MOBILE_PATTERN.matcher(mobile);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	public static boolean validChineseText(String text) {
		Matcher matcher = CHINESE_TEXT_PATTERN.matcher(text);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 校验汉字、字母、数字的组合
	 * 
	 * @param key
	 * @return
	 */
	public static boolean validChineseLfN(String key) {
		Matcher matcher = CHINESE_LEFT_NUM_TEXT_PATTERN.matcher(key);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 生成uuid
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 字符编码转换
	 * 
	 * @param source
	 * @param srcEncode
	 * @param destEncode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String ConverterStringCode(String source, String srcEncode, String destEncode)
			throws UnsupportedEncodingException {
		return new String(source.getBytes(srcEncode), destEncode);
	}

	public static List<String> getParams(String str) {
		Pattern p = Pattern.compile(PARAM_VALIDE);
		List<String> strs = new ArrayList<String>();
		Matcher m = p.matcher(str);
		while (m.find()) {
			strs.add(m.group());
		}
		return strs;
	}
}
