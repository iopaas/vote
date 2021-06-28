package com.ntc.paas.plat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ActivityExt extends Activity {

	// 用戶ID
	@JsonIgnore
	private Long userId;

	// 投递标记[0:未投递,1:以投递]
	private Integer voteFlag = 0;

	// 投递数量
	@SuppressWarnings("unused")
	private Integer votes = 0;

	public ActivityExt() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getVoteFlag() {
		return voteFlag == null ? 0 : voteFlag;
	}

	public void setVoteFlag(Integer voteFlag) {
		this.voteFlag = voteFlag;
	}

	public Integer getVotes() {
		return votes = getActMemberList().stream().mapToInt(ActivityMember::getVotes).sum();
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

}