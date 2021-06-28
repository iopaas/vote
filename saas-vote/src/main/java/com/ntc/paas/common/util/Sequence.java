package com.ntc.paas.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Sequence {

	private static AtomicInteger seq = new AtomicInteger(0);

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

	public static String getDateTimeString(Date date, String type) {
		SimpleDateFormat formattxt = new SimpleDateFormat(type);
		return formattxt.format(date);
	}

	public static String getOrdersId(String uid, String brand_no, String src) {
		if (brand_no == null || "".equals(brand_no)) {
			brand_no = "888";
		}
		if (src == null || "".equals(src)) {
			src = "88";
		}
		StringBuffer sbstr = new StringBuffer();
		seq.compareAndSet(999, 0);
		sbstr.append(brand_no);
		sbstr.append(src);
		sbstr.append(Sequence.getNowDateTimeString("yyyyMMddHHmmssSS"));
		sbstr.append(Integer.toString(seq.getAndAdd(1)));
		sbstr.append(uid);
		return sbstr.toString();
	}

}
