package com.ntc.paas.plat.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserVote {

	@JsonIgnore
	private Long id;

	@JsonIgnore
	private Long userId;

	private Long activityId;

	private Long memberId;

	private List<Long> memberIds;

	@JsonIgnore
	private String voteTime;

	@JsonIgnore
	private String createTime;

	@JsonIgnore
	private String updateTime;

	public UserVote(Long id, Long userId, Long activityId, Long memberId, String voteTime, String createTime,
			String updateTime) {
		this.id = id;
		this.userId = userId;
		this.activityId = activityId;
		this.memberId = memberId;
		this.voteTime = voteTime;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public UserVote() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public List<Long> getMemberIds() {
		return memberIds == null ? new ArrayList<>() : memberIds;
	}

	public void setMemberIds(List<Long> memberIds) {
		this.memberIds = memberIds;
	}

	public String getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(String voteTime) {
		this.voteTime = voteTime == null ? null : voteTime.trim();
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
}