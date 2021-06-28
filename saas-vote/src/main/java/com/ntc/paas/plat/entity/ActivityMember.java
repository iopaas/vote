package com.ntc.paas.plat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ActivityMember {
	
	private Long id;

	private Long activityId;

	private Long memberId;

    private String candidateName;

    private String candidatePhoto;
    
    private String candidateEmail;
    
    private String userEmail;
    
    private Integer votes;
    
    private Integer existFlag;
    
	@JsonIgnore
	private String createTime;

	@JsonIgnore
	private String updateTime;

	
	public ActivityMember(Long id, Long activityId, Long memberId, String createTime, String updateTime) {
		this.id = id;
		this.activityId = activityId;
		this.memberId = memberId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public ActivityMember() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCandidateName() {
		return candidateName == null ? "" : candidateName.trim();
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidatePhoto() {
		return candidatePhoto == null ? "" : candidatePhoto.trim();
	}

	public void setCandidatePhoto(String candidatePhoto) {
		this.candidatePhoto = candidatePhoto;
	}
	

	public String getCandidateEmail() {
		return candidateEmail == null ? "" : candidateEmail.trim();
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public Integer getVotes() {
		return votes == null ? 0 : votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Integer getExistFlag() {
		return existFlag == null ? 0 : existFlag;
	}

	public void setExistFlag(Integer existFlag) {
		this.existFlag = existFlag;
	}

	public String getUserEmail() {
		return userEmail== null ? "" : userEmail.trim();
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}