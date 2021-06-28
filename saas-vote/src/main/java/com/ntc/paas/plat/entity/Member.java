package com.ntc.paas.plat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Member {
	
    private Long id;

    private String candidateName;

    private String candidatePhoto;

    private String candidateEmail;

    private String candidateIdCard;

    private Integer status;

    private String createTime;

    @JsonIgnore
    private String updateTime;

    public Member(Long id, String candidateName, String candidatePhoto, String candidateEmail, String candidateIdCard, Integer status, String createTime, String updateTime) {
        this.id = id;
        this.candidateName = candidateName;
        this.candidatePhoto = candidatePhoto;
        this.candidateEmail = candidateEmail;
        this.candidateIdCard = candidateIdCard;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Member() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName == null ? null : candidateName.trim();
    }

    public String getcandidatePhoto() {
        return candidatePhoto;
    }

    public void setcandidatePhoto(String candidatePhoto) {
        this.candidatePhoto = candidatePhoto == null ? null : candidatePhoto.trim();
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail == null ? null : candidateEmail.trim();
    }

    public String getCandidateIdCard() {
        return candidateIdCard;
    }

    public void setCandidateIdCard(String candidateIdCard) {
        this.candidateIdCard = candidateIdCard == null ? null : candidateIdCard.trim();
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
}