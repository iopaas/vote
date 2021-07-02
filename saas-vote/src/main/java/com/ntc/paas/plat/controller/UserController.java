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
import com.ntc.paas.common.enums.StatusEnum;
import com.ntc.paas.common.model.DefaultValue;
import com.ntc.paas.common.model.PageInfo;
import com.ntc.paas.common.model.RespModel;
import com.ntc.paas.common.model.RespUtil;
import com.ntc.paas.common.util.DateUtil;
import com.ntc.paas.plat.entity.User;
import com.ntc.paas.plat.log.OperateType;
import com.ntc.paas.plat.log.SystemLog;
import com.ntc.paas.plat.model.VoteUserQuery;
import com.ntc.paas.plat.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理-接口
 * 
 * @author allen.yuan
 * @date 2021年6月22日 下午7:38:21
 */
@Api(description = "用户管理")
@RequestMapping("/api/plat/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 保存
	 * 
	 * @param url=${baseUrl}
	 * @param json={}
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Create)
	@ApiOperation(value = "新增用户", notes = "新增一个用户")
	@PostMapping(value = "/add")
	public RespModel<?> addUser(@RequestBody User vo) {

		vo.setStatus(StatusEnum.Enable.getStatus());
		vo.setCreateTime(DateUtil.getDateTime());
		vo.setUpdateTime(vo.getCreateTime());

		RespModel<?> respModel = null;
		int result = userService.insert(vo);
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
	@ApiOperation(value = "修改用户", notes = "修改一个用户&投票时间")
	@PutMapping(value = "/update")
	public RespModel<?> updateUser(@RequestBody User vo) {

		vo.setStatus(StatusEnum.Enable.getStatus());
		vo.setUpdateTime(DateUtil.getDateTime());
		userService.updateByPrimaryKey(vo);

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
	@ApiOperation(value = "删除用户", notes = "按Id删除")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键ID", required = false, dataType = "long") })
	@DeleteMapping(value = "/delete")
	public RespModel<?> deleteUser(Long id) {

		RespModel<?> respModel = null;
		int flag = userService.deleteByPrimaryKey(id);
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
	@ApiOperation(value = "查询用户", notes = "按Id查询")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "主键ID", required = false, dataType = "long") })
	@GetMapping(value = "/query")
	public RespModel<?> queryUser(Long id) {

		return RespUtil.success(userService.selectByPrimaryKey(id));
	}

	/**
	 * 分页查询
	 * 
	 * @param url=${baseUrl}?pageNum=1&pageSize=10
	 * 
	 * @return
	 */
	@SystemLog(opType = OperateType.Query)
	@ApiOperation(value = "查询用户(分页)", notes = "表格分页展示")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNum", value = "第几页", required = false, dataType = "int"),
			@ApiImplicitParam(name = "pageSize", value = "条数", required = false, dataType = "int") })
	@PostMapping(value = "/queryListForPage")
	public RespModel<?> queryListForPage(VoteUserQuery query) {

		Page<User> pages = userService.queryListByPage(query);
		PageInfo<User> pageInfo = new PageInfo<User>(pages);
		return RespUtil.success(pageInfo);
	}

}
