package com.ntc.paas.plat.model;

import java.io.Serializable;

public class ToEmail implements Serializable {

	  private static final long serialVersionUID = -5948649259208721680L;
	
	  /**
	   * 邮件接发送方
	   */
	  private String from;
		  
	  /**
	   * 邮件接收方，可多人
	   */
	  private String[] tos;
	  /**
	   * 邮件主题
	   */
	  private String subject;
	  /**
	   * 邮件内容
	   */
	  private String content;
	  
	  public ToEmail() {
		  
	  }
	  
	  public ToEmail(String from,String[] tos,String subject,String content) {
		  this.from = from;
		  this.tos = tos;
		  this.subject = subject;
		  this.content = content;
	  }
  

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String[] getTos() {
		return tos == null? new String[0] : tos;
	}
	public void setTos(String[] tos) {
		this.tos = tos;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
 
