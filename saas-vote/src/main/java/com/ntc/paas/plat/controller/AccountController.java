package com.ntc.paas.plat.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ntc.paas.common.constants.PublicConstants;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.exception.BusinessException;
import com.ntc.paas.common.model.DefaultValue;
import com.ntc.paas.common.model.LoginModel;
import com.ntc.paas.common.model.PageInfo;
import com.ntc.paas.common.model.RespModel;
import com.ntc.paas.common.model.RespUtil;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.common.util.MD5Util;
import com.ntc.paas.common.util.StringUtil;
import com.ntc.paas.plat.config.RequestContextMgr;
import com.ntc.paas.plat.entity.Account;
import com.ntc.paas.plat.log.OperateType;
import com.ntc.paas.plat.log.SystemLog;
import com.ntc.paas.plat.model.AccountQuery;
import com.ntc.paas.plat.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账号管理-接口
 * 
 * @author allen.yuan
 * @date 2021年6月22日 下午12:38:21
 */
@Api(description = "账号管理")
@RequestMapping("/api/plat/account")
@RestController
public class AccountController {

	private static final Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	/**
	 * 保存
	 * 
	 * @param url=${baseUrl}
	 * @param json={}
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Create)
	@ApiOperation(value = "新增账号", notes = "")
	@PostMapping(value = "/add")
	public RespModel<?> addAccount(@RequestBody Account vo) {

		RespModel<?> respModel = null;
		try {

			if (vo != null) {
				// md5(32位加密)，当前接口支持加密和未加密的请求
				if (StringUtil.notEmpty(vo.getAccount_pwd()) && vo.getAccount_pwd().length() < 32) {
					String pwd = MD5Util.md5Hex(vo.getAccount_pwd());
					vo.setAccount_pwdmd5(pwd); // 设置密码
				}
				LoginModel loginUser = RequestContextMgr.getLocalContext().getLoginInfoVo();
				vo.setCreate_time(DateUtil.getDateTime());
				vo.setUpdate_user(loginUser.getAccount_number());
				vo.setUpdate_time(vo.getCreate_time());
				vo.setUpdate_user(vo.getCreate_user());
				// 根据企业名查重
				Account account = accountService.queryByAccountNumber(vo.getAccount_number());
				if (account == null) {
					accountService.insert(vo);
					respModel = RespUtil.success(new DefaultValue(vo.getId()));
				} else {
					respModel = RespUtil.failure(ConmmonEnum.FAILURE.getCode(), "添加失败,该账户名已存在!");
				}
			}

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}

		return respModel;
	}

	/**
	 * 修改
	 * 
	 * @param url=${baseUrl}
	 * @param json={}
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Modify)
	@ApiOperation(value = "修改账号", notes = "")
	@PutMapping(value = "/update")
	public RespModel<?> updateAccount(@RequestBody Account vo) {

		RespModel<?> respModel = null;
		try {

			if (vo != null) {
				LoginModel loginUser = RequestContextMgr.getLocalContext().getLoginInfoVo();
				vo.setUpdate_time(DateUtil.getDateTime());
				vo.setUpdate_user(loginUser.getAccount_number());
				accountService.updateByPrimaryKey(vo);
				respModel = RespUtil.success(new DefaultValue(vo.getId()));
			}

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}

		return respModel;
	}

	/**
	 * 删除
	 * 
	 * @param url=${baseUrl}?id=0
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Delete)
	@ApiOperation(value = "删除账号", notes = "")
	@DeleteMapping(value = "/delete")
	public RespModel<?> deleteAccount(@RequestParam(value = "id", required = true) Long id,
			@RequestHeader HttpHeaders headers) {

		RespModel<?> respModel = null;
		try {

			int code = accountService.deleteByPrimaryKey(id, headers.getFirst(PublicConstants.HEADER_ACCOUNT_KEY));
			if (code == ConmmonEnum.SUCCESS.getCode()) {
				respModel = RespUtil.success(new DefaultValue(id));
			} else if (code == ConmmonEnum.FAILURE.getCode()) {
				respModel = RespUtil.failure(ConmmonEnum.FAILURE.getCode(), "无法删除正常状态下账户，请先禁用");
			}

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}

		return respModel;
	}

	/**
	 * id查询
	 * 
	 * @param url=${baseUrl}?id=0
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查詢账号", notes = "通过Id查询")
	@GetMapping(value = "/query")
	public RespModel<?> queryAccount(@RequestParam(value = "id", required = true) Long id) {

		RespModel<?> respModel = null;
		try {

			Account vo = accountService.selectByPrimaryKey(id);
			vo.setAccount_pwdmd5("");
			vo.setAccount_pwd("");
			respModel = RespUtil.success(vo);

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}

		return respModel;
	}

	/**
	 * 全部查询
	 * 
	 * @param url=${baseUrl}
	 * @param json={}
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查詢账号列表", notes = "条件查询")
	@PostMapping(value = "/queryList")
	public RespModel<?> queryAccountList(@RequestBody AccountQuery query) {

		RespModel<?> respModel = null;
		try {

			List<Account> list = accountService.queryListByCond(query);
			respModel = RespUtil.success(list);

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}

		return respModel;
	}

	/**
	 * 分页查询
	 * 
	 * @param url=${baseUrl}
	 * @param json={"pageNum":1,"pageSize":10,}
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "分页查询账号列表", notes = "条件查询")
	@PostMapping(value = "/queryListForPage")
	public RespModel<?> queryAccountListForPage(@RequestBody AccountQuery query) {

		RespModel<?> respModel = null;
		try {

			Page<Account> pages = accountService.queryListByPage(query);
			PageInfo<Account> pageInfo = new PageInfo<Account>(pages);
			respModel = RespUtil.success(pageInfo);

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}

		return respModel;
	}

	/**
	 * 修改状态
	 * 
	 * @param url=${baseUrl}?id=0&status=2
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Modify)
	@ApiOperation(value = "修改账号状态", notes = "禁用&启用账号")
	@PutMapping(value = "/updateStatus/{id}/{staus}")
	public RespModel<?> updateAccountStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {

		RespModel<?> respModel = null;
		try {

			Account vo = accountService.selectByPrimaryKey(id);
			if (vo != null) {
				LoginModel loginUser = RequestContextMgr.getLocalContext().getLoginInfoVo();
				vo.setStatus(status);
				vo.setUpdate_time(DateUtil.getDateTime());
				vo.setUpdate_user(loginUser.getAccount_number());
				accountService.updateStatus(vo);
				respModel = RespUtil.success(new DefaultValue(id));
			}

		} catch (BusinessException e) {
			respModel = RespUtil.failure();
			log.error("请求处理异常,ex={}", e);
		}
		return respModel;
	}

}
