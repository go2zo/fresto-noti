package com.fresto.noti.mail.domain;

import java.io.Serializable;
import java.util.Date;

public class Mail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2011775887649805609L;
	
	/**
	 * 메일 번호
	 */
	private Long mailNo;
	
	/**
	 * 템플릿 아이디
	 */
	private String templateId;
	
	/**
	 * 상태 코드 
	 */
	private String statusCode;
	
	/**
	 * 받는이 주소
	 */
	private String recipients;
	
	/**
	 * 보내는이 주소
	 */
	private String sender;
	
	/**
	 * 제목 데이터
	 */
	private String subjectData;
	
	/**
	 * 본문 데이터
	 */
	private String contentData;
	
	/**
	 * 발송자
	 */
	private String sentBy;
	
	/**
	 * 발송일
	 */
	private Date sentDate;
	
	/**
	 * 생성자
	 */
	private String createdBy;
	
	/**
	 * 생성일
	 */
	private Date creationDate;
	
	/**
	 * 수정자
	 */
	private String updatedBy;
	
	/**
	 * 수정일
	 */
	private Date updatedDate;
	
	public Long getMailNo() {
		return mailNo;
	}
	public void setMailNo(Long mailNo) {
		this.mailNo = mailNo;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSubjectData() {
		return subjectData;
	}
	public void setSubjectData(String subjectData) {
		this.subjectData = subjectData;
	}
	public String getContentData() {
		return contentData;
	}
	public void setContentData(String contentData) {
		this.contentData = contentData;
	}
	public String getSentBy() {
		return sentBy;
	}
	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
