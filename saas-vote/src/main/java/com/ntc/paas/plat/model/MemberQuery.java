package com.ntc.paas.plat.model;

import java.io.Serializable;

/**
 * 候选人管理-查询条件实体类
 * 
 * @author allen.yuan
 * @date 2021年6月26日 下午2:28:10
 */
public class MemberQuery implements Serializable {

	private static final long serialVersionUID = -2563427234149178532L;

	// 页号
	private Integer pageNum;

	// 页数量
	private Integer pageSize;

	private String candidateName;

	private String candidateEmail;

	private String candidateIdCard;

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

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

	public String getCandidateIdCard() {
		return candidateIdCard;
	}

	public void setCandidateIdCard(String candidateIdCard) {
		this.candidateIdCard = candidateIdCard;
	}
}
