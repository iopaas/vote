package com.ntc.paas.common.util;

/**
 * 注册树形菜单生产ID
 * 
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class GeneratorTreeUtil {

	/**
	 * 
	 * 101 <br>
	 * 100101<br>
	 * 101102
	 * 
	 * 算法生产一个新的code
	 * 
	 * @param last_code
	 *            上一个编码
	 * @param parent_code
	 *            父级编码
	 * @return
	 */
	public static String getIntallCode(String parent_code, String last_code) {

		try {

			String code = "10";
			if (StringUtil.notEmpty(parent_code)) {
				if (StringUtil.isEmpty(last_code)) {
					code = parent_code + code;
				} else {
					code = last_code;
				}
			}else{
				if (StringUtil.notEmpty(last_code)) {
					code = last_code;
				} 
			}
			
			code = String.valueOf(Long.valueOf(code) + 1);
			return code;
		} catch (Exception e) {

		}
		return "";
	}
}
