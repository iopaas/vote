package com.ntc.paas.plat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.enums.StatusEnum;
import com.ntc.paas.common.model.DefaultValue;
import com.ntc.paas.common.model.PageInfo;
import com.ntc.paas.common.model.RespModel;
import com.ntc.paas.common.model.RespUtil;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.plat.entity.Member;
import com.ntc.paas.plat.log.OperateType;
import com.ntc.paas.plat.log.SystemLog;
import com.ntc.paas.plat.model.MemberQuery;
import com.ntc.paas.plat.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 候选人成员管理-接口
 * 
 * @author allen.yuan
 * @date 2021年6月22日 下午7:38:21
 * @Copyright © 2021 NTC. All Rights Reserved.
 */
@Api(description = "候选人成员管理")
@RequestMapping("/api/plat/member")
@RestController
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * 保存
	 * 
	 * @param url=${baseUrl}
	 * @param json={}
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Create)
	@ApiOperation(value = "新增候选人成员", notes = "扩展一个候选人成员")
	@PostMapping(value = "/add")
	public RespModel<?> addMember(@RequestBody Member vo) {

		RespModel<?> respModel = null;
		int result = memberService.insert(vo);
		if (result > 0) {
			respModel = RespUtil.success(new DefaultValue(vo.getId()));
		} else {
			respModel = RespUtil.failure(ConmmonEnum.FAILURE.getCode(), "添加失败，参数异常.");
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
	@ApiOperation(value = "修改候选人成员", notes = "修改一个候选人成员&投票时间")
	@PostMapping(value = "/update")
	public RespModel<?> updateMember(@RequestBody Member vo) {

		vo.setStatus(StatusEnum.Enable.getStatus());
		vo.setUpdateTime(DateUtil.getDateTime());
		memberService.updateByPrimaryKey(vo);

		return RespUtil.success(new DefaultValue(vo.getId()));
	}

	/**
	 * 删除
	 * 
	 * @param url=${baseUrl}?id=0
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Delete)
	@ApiOperation(value = "删除候选人成员", notes = "按Id删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键ID", required = false, dataType = "long") })
	@GetMapping(value = "/delete")
	public RespModel<?> deleteMember(Long id) {

		RespModel<?> respModel = null;
		int flag = memberService.deleteByPrimaryKey(id);
		if (flag == ConmmonEnum.SUCCESS.getCode()) {
			respModel = RespUtil.success(new DefaultValue(id));
		} else {
			respModel = RespUtil.failure(ConmmonEnum.FAILURE.getCode(), "删除失败，请重试.");
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
	@ApiOperation(value = "查询候选人成员", notes = "按Id查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键ID", required = false, dataType = "long") })
	@GetMapping(value = "/query")
	public RespModel<?> queryMember(Long id) {

		return RespUtil.success(memberService.selectByPrimaryKey(id));
	}

	/**
	 * 查询全部
	 * 
	 * @param url=${baseUrl}?id=0
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查询候选人所有成员", notes = "按Id查询")
	@GetMapping(value = "/querylist")
	public RespModel<?> queryMemberList() {

		return RespUtil.success(memberService.queryList());
	}

	/**
	 * 分页查询
	 * 
	 * @param url=${baseUrl}?pageNum=1&pageSize=10
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查询候选人成员(分页)", notes = "表格分页展示")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "第几页", required = false, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "条数", required = false, dataType = "int") })
	@PostMapping(value = "/queryListForPage")
	public RespModel<?> queryListForPage(@RequestBody MemberQuery query) {

		Page<Member> pages = memberService.queryListByPage(query);
		PageInfo<Member> pageInfo = new PageInfo<Member>(pages);
		return RespUtil.success(pageInfo);
	}

}
