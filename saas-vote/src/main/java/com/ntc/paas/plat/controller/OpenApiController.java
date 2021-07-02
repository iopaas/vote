package com.ntc.paas.plat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Objects;
import com.ntc.paas.common.config.PublicKey;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.enums.LoginTypeEnum;
import com.ntc.paas.common.enums.RoleTypeEnum;
import com.ntc.paas.common.enums.StatusEnum;
import com.ntc.paas.common.exception.BusinessException;
import com.ntc.paas.common.model.DefaultValue;
import com.ntc.paas.common.model.LoginModel;
import com.ntc.paas.common.model.RespModel;
import com.ntc.paas.common.model.RespUtil;
import com.ntc.paas.common.redis.RedisUtil;
import com.ntc.paas.common.util.AESUtil;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.common.util.MD5Util;
import com.ntc.paas.common.util.StringUtil;
import com.ntc.paas.plat.config.NoLoginAllow;
import com.ntc.paas.plat.config.RequestContextMgr;
import com.ntc.paas.plat.entity.User;
import com.ntc.paas.plat.entity.UserVote;
import com.ntc.paas.plat.log.OperateType;
import com.ntc.paas.plat.log.SystemLog;
import com.ntc.paas.plat.model.UserLogin;
import com.ntc.paas.plat.service.ActivityService;
import com.ntc.paas.plat.service.UserService;
import com.ntc.paas.plat.service.UserVoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * H5接口管理
 * 
 * @author allen.yuan
 * @date 2021年6月25日 下午14:11:21
 */
@Api(description = "H5接口管理")
@RequestMapping("/api/plat/apps")
@RestController
public class OpenApiController {

	private static final Logger log = LoggerFactory.getLogger(OpenApiController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserVoteService userVoteService;
	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 登录接口
	 * 
	 * @param url=${baseUrl}
	 * @param json={"idCard":"S8890123","email":"8890123@hk.com","role_id":"2"}
	 * 
	 * @return respModel
	 */
	@NoLoginAllow
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "用户登记", notes = "H5用户登记")
	@PostMapping(value = "/user/login")
	public RespModel<?> userLogin(@RequestBody UserLogin uLogin) {

		RespModel<?> respModel = null;
		try {

			User acct = userService.selectByIdCard(uLogin.getIdCard());
			if (acct == null || StringUtil.isEmpty(acct.getIdCard())) {
				acct = new User(MD5Util.md5Hex("123456"), uLogin.getEmail(), uLogin.getIdCard(),
						StatusEnum.Enable.getStatus(), DateUtil.getDateTime());
				// 新用户添加
				userService.insert(acct);
			} else {
				// 邮箱变化->去更新
				if (!Objects.equal(uLogin.getEmail(), acct.getEmail())) {
					acct.setEmail(uLogin.getEmail());
					userService.updateByPrimaryKey(acct);
				}
			}

			// 账号启用状态
			LoginModel loginModel = null;
			if (acct.getStatus() == StatusEnum.Enable.getStatus()) {

				// 登入类型|用戶角色|
				loginModel = new LoginModel(acct.getStatus(), "", LoginTypeEnum.Login.getType(), acct.getId(), "",
						RoleTypeEnum.User.getType(), DateUtil.parseTimestamp());

				// 获得token
				String tv = String.format(PublicKey.P_P_PS_FIELD, RoleTypeEnum.User.getType(),
						loginModel.getAccount_id(), loginModel.getTimestamp());
				String token = AESUtil.encrypt(tv);// 加密
				loginModel.setToken(token);// 设置token

				// 设置登录会话到REDIS中
				String sessionKey = String.format(PublicKey.LOGIN_SESSION, loginModel.getToken());
				redisUtil.set(sessionKey, loginModel, (72 * 60 * 60));
			} else {
				loginModel = new LoginModel(ConmmonEnum.FAILURE.getCode(), "账号无效");
			}

			respModel = RespUtil.success(loginModel);
		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}
		return respModel;
	}

	/**
	 * 投票列表查询
	 * 
	 * @param url=${baseUrl}
	 * 
	 * @return respModel
	 */
	@NoLoginAllow
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "投票列表查询", notes = "")
	@GetMapping(value = "/activity/list")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "1", required = false, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "10", required = false, dataType = "int") })
	public RespModel<?> queryActivityList(Integer pageNum, Integer pageSize) {

		LoginModel loginUser = RequestContextMgr.getLocalContext().getLoginInfoVo();
		return RespUtil.success(activityService.queryList(loginUser.getAccount_id(), pageNum, pageSize));
	}

	/**
	 * 投票列表查询
	 * 
	 * @param url=${baseUrl}?id=0
	 * 
	 * @return respModel
	 */
	@NoLoginAllow
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "投票详情查询", notes = "条件Id查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键ID", required = false, dataType = "long") })
	@GetMapping(value = "/activity/detail")
	public RespModel<?> queryActivityDetail(Long id) {

		LoginModel loginUser = RequestContextMgr.getLocalContext().getLoginInfoVo();
		return RespUtil.success(activityService.selectById(id, loginUser.getAccount_id()));
	}

	/**
	 * 保存
	 * 
	 * @param url=${baseUrl}
	 * @param json={}
	 * 
	 * @return respModel
	 */
	@SystemLog(opType = OperateType.Create)
	@ApiOperation(value = "新增用戶投票", notes = "")
	@PostMapping(value = "/uservote/add")
	public RespModel<?> addUserVote(@RequestBody UserVote vo) {

		RespModel<?> respModel = null;
		LoginModel loginUser = RequestContextMgr.getLocalContext().getLoginInfoVo();
		vo.setUserId(Long.valueOf(loginUser.getAccount_id()));
		int result = userVoteService.insert(vo);
		if (result > 0) {
			respModel = RespUtil.success(new DefaultValue(vo.getId()));
		} else if (result == ConmmonEnum.EXISTS.getCode()) {
			respModel = RespUtil.failure(ConmmonEnum.EXISTS.getCode(), ConmmonEnum.EXISTS.getDesc());
		} else if (result == ConmmonEnum.EXPIRE.getCode()) {
			respModel = RespUtil.failure(ConmmonEnum.EXPIRE.getCode(), ConmmonEnum.EXPIRE.getDesc());
		} else {
			respModel = RespUtil.failure(ConmmonEnum.FAILURE.getCode(), ConmmonEnum.FAILURE.getDesc());
		}
		return respModel;
	}

}
