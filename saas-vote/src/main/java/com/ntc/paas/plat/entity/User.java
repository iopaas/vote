package com.ntc.paas.plat.entity;

public class User {
    private Long id;

    private String userNumber;

    private String userPwdmd5;

    private String userName;

    private String mobile;

    private String email;

    private String idCard;

    private Integer status;

    private String createTime;

    private String updateTime;

    public User(Long id, String userNumber, String userPwdmd5, String userName, String mobile, String email, String idCard, Integer status, String createTime, String updateTime) {
        this.id = id;
        this.userNumber = userNumber;
        this.userPwdmd5 = userPwdmd5;
        this.userName = userName;
        this.mobile = mobile;
        this.email = email;
        this.idCard = idCard;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    
    public User(String userPwdmd5 ,String email, String idCard, Integer status, String createTime) {
        this.userPwdmd5 = userPwdmd5;
        this.email = email;
        this.idCard = idCard;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = createTime;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber == null ? null : userNumber.trim();
    }

    public String getUserPwdmd5() {
        return userPwdmd5;
    }

    public void setUserPwdmd5(String userPwdmd5) {
        this.userPwdmd5 = userPwdmd5 == null ? null : userPwdmd5.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
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