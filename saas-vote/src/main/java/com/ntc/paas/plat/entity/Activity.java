package com.ntc.paas.plat.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntc.paas.common.util.DateUtil;

public class Activity {

	private Long id;

	private String activityTitle;

	private String activityDesc;

	private String startTime;

	private String endTime;

	private Integer status;

	private List<ActivityMember> actMemberList;

	@SuppressWarnings("unused")
	private Integer enabled;

	private List<Long> memberIds;

	@JsonIgnore
	private String createTime;

	@JsonIgnore
	private String updateTime;

	public Activity(Long id, String activityTitle, String activityDesc, String startTime, String endTime,
			Integer status, String createTime, String updateTime) {
		this.id = id;
		this.activityTitle = activityTitle;
		this.activityDesc = activityDesc;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Activity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle == null ? null : activityTitle.trim();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime == null ? null : startTime.trim();
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime == null ? null : endTime.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc == null ? null : activityDesc.trim();
	}

	public List<Long> getMemberIds() {
		return memberIds == null ? new ArrayList<>() : memberIds;
	}

	public void setMemberIds(List<Long> memberIds) {
		this.memberIds = memberIds;
	}

	public List<ActivityMember> getActMemberList() {
		return actMemberList == null ? new ArrayList<>() : actMemberList;
	}

	public void setActMemberList(List<ActivityMember> actMemberList) {
		this.actMemberList = actMemberList;
	}

	public Integer getEnabled() {
		return enabled = DateUtil.dateExpire(this.getStartTime(), this.getEndTime()) ? 1 : 0;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}