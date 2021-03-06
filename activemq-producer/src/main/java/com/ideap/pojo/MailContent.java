package com.ideap.pojo;

import java.io.Serializable;

public class MailContent implements Serializable{

	private static final long serialVersionUID = 1L;
	//发送人
	private String from ;
	//收件人
	private String to ;
	//主题
	private String subject ;
	//正文
	private String content ;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
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
