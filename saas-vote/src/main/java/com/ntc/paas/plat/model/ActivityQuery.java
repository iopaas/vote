package com.ntc.paas.plat.model;

import java.io.Serializable;

/**
 * 投票活动管理-查询条件实体类
 * 
 *
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class ActivityQuery implements Serializable {
	private static final long serialVersionUID = -2563427234149178532L;

	// 页号
	private Integer pageNum;

	// 页数量
	private Integer pageSize;

	private String activityTitle;

	private String activityDesc;

	private String startTime;

	private String endTime;

	public ActivityQuery() {
		
	}
	
	public ActivityQuery(int pageNum, int pageSize,String endTime) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.endTime = endTime;
	}
	
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

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getStartTime() {
		return startTime == null ? "" : startTime.trim();
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime == null ? "" : endTime.trim();
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
