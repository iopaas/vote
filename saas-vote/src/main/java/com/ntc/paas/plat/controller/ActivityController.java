package com.ntc.paas.plat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.ntc.paas.common.enums.ConmmonEnum;
import com.ntc.paas.common.model.DefaultValue;
import com.ntc.paas.common.model.PageInfo;
import com.ntc.paas.common.model.RespModel;
import com.ntc.paas.common.model.RespUtil;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.plat.entity.Activity;
import com.ntc.paas.plat.entity.User;
import com.ntc.paas.plat.log.OperateType;
import com.ntc.paas.plat.log.SystemLog;
import com.ntc.paas.plat.model.ActivityQuery;
import com.ntc.paas.plat.model.VoteUserQuery;
import com.ntc.paas.plat.service.ActivityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 选举活动发布管理-接口
 * 
 * @author allen.yuan
 * @date 2021年6月22日 下午7:38:21
 */
@Api(description = "选举活动管理")
@RequestMapping("/api/plat/activity")
@RestController
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	/**
	 * 保存
	 * 
	 * @param url=${baseUrl}
	 * @param json={}
	 * 
	 * @return respModel
	 */
	@SystemLog(opType = OperateType.Create)
	@ApiOperation(value = "新增选举活动", notes = "发布一个选举活动")
	@PostMapping(value = "/add")
	public RespModel<?> addActivity(@RequestBody Activity vo) {

		RespModel<?> respModel = null;
		int result = activityService.insert(vo);
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
	 * @return respModel
	 */
	@SystemLog(opType = OperateType.Modify)
	@ApiOperation(value = "修改选举活动", notes = "修改一个选举活动&投票时间")
	@PutMapping(value = "/update")
	public RespModel<?> updateActivity(@RequestBody Activity vo) {

		vo.setUpdateTime(DateUtil.getDateTime());
		activityService.updateByPrimaryKey(vo);

		return RespUtil.success(new DefaultValue(vo.getId()));
	}

	/**
	 * 删除
	 * 
	 * @param url=${baseUrl}?id=0
	 * 
	 * @return respModel
	 */
	@SystemLog(opType = OperateType.Delete)
	@ApiOperation(value = "删除选举活动", notes = "按Id删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键ID", required = false, dataType = "long") })
	@DeleteMapping(value = "/delete")
	public RespModel<?> deleteActivity(Long id) {

		RespModel<?> respModel = null;
		int flag = activityService.deleteByPrimaryKey(id);
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
	 * @return respModel
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查询选举活动", notes = "按Id查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键ID", required = false, dataType = "long") })
	@GetMapping(value = "/query")
	public RespModel<?> queryActivity(Long id) {

		return RespUtil.success(activityService.selectExtById(id));
	}

	/**
	 * 查询全部
	 * 
	 * @param url=${baseUrl}?id=0
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查询候选人成员", notes = "按活动ID查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "activityId", value = "活动ID", required = false, dataType = "long") })
	@GetMapping(value = "/queryMemberList")
	public RespModel<?> queryActivityMemberList(Long activityId) {

		return RespUtil.success(activityService.queryMemberList(activityId));
	}

	/**
	 * 分页查询
	 * 
	 * @param url=${baseUrl}?pageNum=1&pageSize=10
	 * 
	 * @return respModel
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查询选举活动(分页)", notes = "表格分页展示")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "1", required = false, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "5", required = false, dataType = "int") })
	@PostMapping(value = "/queryListForPage")
	public RespModel<?> queryListForPage(ActivityQuery query) {

		Page<Activity> pages = activityService.queryListByPage(query);
		PageInfo<Activity> pageInfo = new PageInfo<Activity>(pages);
		return RespUtil.success(pageInfo);
	}

	/**
	 * 分页查询
	 * 
	 * @param url=${baseUrl}?pageNum=1&pageSize=10
	 * 
	 * @return respModel
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查询投票用户(分页)", notes = "表格分页展示")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "1", required = false, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "10", required = false, dataType = "int") })
	@PostMapping(value = "/queryVoteListForPage")
	public RespModel<?> queryVoteListForPage(VoteUserQuery query) {

		Page<User> pages = activityService.queryVoteListForPage(query);
		PageInfo<User> pageInfo = new PageInfo<User>(pages);
		return RespUtil.success(pageInfo);
	}
}
