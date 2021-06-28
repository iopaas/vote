package com.ntc.paas.plat.model;

import java.io.Serializable;

/**
 * 投票活动管理-查询条件实体类
 * 
 *
 * @author allen.yuan
 * @date 2021年6月25日 下午14:11:21
 * @Copyright © 2021 NTC. All Rights Reserved.
 */
public class VoteUserQuery implements Serializable {

	private static final long serialVersionUID = -2563167234149178532L;

	// 页号
	private Integer pageNum;

	// 页数量
	private Integer pageSize;

	// 投票活动Id
	private Long activityId;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum == null ? 1 : pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize == null ? 10 : pageSize;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

}
