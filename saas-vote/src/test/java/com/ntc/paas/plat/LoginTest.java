package com.ntc.paas.plat;

import java.util.HashMap;
import java.util.Map;

import com.ntc.paas.common.model.LoginModel;
import com.ntc.paas.common.model.RespModel;
import com.ntc.paas.common.util.HttpClientUtil;
import com.ntc.paas.common.util.JsonUtil;
import com.ntc.paas.common.util.StringUtil;

/**
 * 执行登录-查询菜单流程-测试
 * 
 * @author allen.yuan
 * @date 2018年6月21日 下午2:09:23
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
public class LoginTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		try {

			String url = "http://192.168.1.58:8601/api/plat/accountLogin";
			Map<String, String> headerMap = new HashMap<String, String>();
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("account_number", "admin");
			paramMap.put("account_pwd", "123456");

			System.out.println("执行开始登录请求===");
			String res = HttpClientUtil.INSTANCE.httpPost(url, JsonUtil.toJsonString(paramMap), headerMap);
			System.out.println("登录结果message=" + res);
			RespModel<LoginModel> respModel = JsonUtil.parseToObject(res, RespModel.class);

			String token = "";
			String account = "";
			if (respModel != null && respModel.getCode() == 1) {
				LoginModel loginModel = JsonUtil.parseToObject(String.valueOf(respModel.getData()), LoginModel.class);
				if (loginModel != null && loginModel.getStatus() == 1) {
					token = loginModel.getToken();
					account = loginModel.getAccount_number();
				}
			}

			res = StringUtil.notEmpty(token) ? "成功" : "失败";
			System.out.println("执行开始登录请求结果：" + res);

			url = "http://192.168.1.58:8601/api/plat/queryListMenu";
			headerMap.put("token", token);
			headerMap.put("account", account);

			System.out.println("执行开始查询菜单请求===");
			res = HttpClientUtil.INSTANCE.httpPost(url, "", headerMap);
			System.out.println("查询菜单结果message=" + res);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
