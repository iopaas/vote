package com.ntc.paas.plat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntc.paas.common.config.PublicKey;
import com.ntc.paas.common.constants.PublicConstants;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.enums.LoginTypeEnum;
import com.ntc.paas.common.enums.RoleTypeEnum;
import com.ntc.paas.common.enums.StatusEnum;
import com.ntc.paas.common.exception.BusinessException;
import com.ntc.paas.common.model.LoginModel;
import com.ntc.paas.common.model.RespModel;
import com.ntc.paas.common.model.RespUtil;
import com.ntc.paas.common.redis.RedisUtil;
import com.ntc.paas.common.util.AESUtil;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.common.util.JsonUtil;
import com.ntc.paas.common.util.MD5Util;
import com.ntc.paas.common.util.StringUtil;
import com.ntc.paas.plat.config.NoLoginAllow;
import com.ntc.paas.plat.entity.Account;
import com.ntc.paas.plat.log.OperateType;
import com.ntc.paas.plat.log.SystemLog;
import com.ntc.paas.plat.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账号登录&退出-接口
 * 
 * @author allen.yuan
 * @date 2018年5月14日 下午2:47:14
 * @version SaaS平台_V1.0
 * @Copyright © 2018 NTC. All Rights Reserved.
 */
@Api(description = "登录&退出管理")
@RequestMapping("/api/plat/comm")
@RestController
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AccountService accountService;
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 登录接口
	 * 
	 * @param url=${baseUrl}
	 * @param json={"account_number":"admin","account_pwd":"abc","role_id":"1"}
	 * 
	 * @return
	 */
	@NoLoginAllow
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "账号登录", notes = "管理后台登录")
	@PostMapping(value = "/accountLogin")
	public RespModel<?> doLogin(@RequestBody Account account) {

		RespModel<?> respModel = null;
		LoginModel loginModel = null;
		try {

			 
				String account_pwdmd5 = "";
				// md5(32位加密)，当前接口支持加密和未加密的请求
				if (StringUtil.notEmpty(account.getAccount_pwd()) && account.getAccount_pwd().length() < 32) {
					account_pwdmd5 = MD5Util.md5Hex(account.getAccount_pwd()); // md5加密
					account.setAccount_pwdmd5(account_pwdmd5);
				} else {
					account.setAccount_pwdmd5(account.getAccount_pwd());
				}

				Account acct = accountService.queryForLogin(account.getAccount_number(), account.getAccount_pwdmd5());
				if (acct != null && StringUtil.notEmpty(acct.getAccount_name())) {

					// 账号启用状态
					if (acct.getStatus() == StatusEnum.Enable.getStatus()) {

						// 登入类型|管理员角色|
						loginModel = new LoginModel(acct.getStatus(), "", LoginTypeEnum.Login.getType(),
								acct.getId(), acct.getAccount_number(), RoleTypeEnum.Admin.getType(),
								DateUtil.parseTimestamp());

						// 获得token
						String tv = String.format(PublicKey.P_P_PS_FIELD, RoleTypeEnum.Admin.getType(),
								loginModel.getAccount_id(), loginModel.getTimestamp());
						String token = AESUtil.encrypt(tv);// 加密
						loginModel.setToken(token);// 设置token

						// 设置登录会话到REDIS中
						String sessionKey = String.format(PublicKey.LOGIN_SESSION, loginModel.getToken());
						redisUtil.set(sessionKey, loginModel, (24 * 60 * 60));

						// 更新最后登录数据
						accountService.updateLastLoginTime(acct.getId(), DateUtil.getDateTime());
					} else if (acct.getStatus() == StatusEnum.All.getStatus()
							|| acct.getStatus() == StatusEnum.Disable.getStatus()) {
						loginModel = new LoginModel(ConmmonEnum.FAILURE.getCode(), "账号已禁用");
					} else if (acct.getStatus() == StatusEnum.Delete.getStatus()) {
						loginModel = new LoginModel(ConmmonEnum.FAILURE.getCode(), "该账户已被管理员删除");
					}

				} else {
					loginModel = new LoginModel(ConmmonEnum.FAILURE.getCode(), "账号或密码错误");
				}
	 
			respModel = RespUtil.success(loginModel);

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}
		return respModel;
	}

	/**
	 * 登出操作
	 * 
	 * @param url=${baseUrl}?id=1&
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "账号退出", notes = "管理后台退出")
	@GetMapping(value = "/accountLogout")
	public RespModel<?> doLogOut(@RequestParam(value = "id", required = false) Long id,
			@RequestHeader HttpHeaders headers) {

		RespModel<?> respModel = null;
		try {

			String account_number = headers.getFirst(PublicConstants.HEADER_ACCOUNT_KEY);
			String token = headers.getFirst(PublicConstants.HEADER_TOKEN_KEY);// header方式
			log.info("请求参数id={}, account_number={}, token={}", id, account_number, token);

			// 登出类型|管理员角色|
			LoginModel loginModel = new LoginModel(StatusEnum.Enable.getStatus(), "", LoginTypeEnum.Logout.getType(),
					id, "", RoleTypeEnum.Admin.getType(), DateUtil.parseTimestamp());

			// 从REDIS中删除会话
			String accountKey = String.format(PublicKey.LOGIN_ACCOUNT, loginModel.getRole_id(), String.valueOf(id));
			String sessionKey = String.format(PublicKey.LOGIN_SESSION, token);
			redisUtil.del(accountKey);
			redisUtil.del(sessionKey);

			respModel = RespUtil.success(loginModel);

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}

		log.info("请求结果respJson={}", JsonUtil.toJsonString(respModel));
		return respModel;
	}
}
