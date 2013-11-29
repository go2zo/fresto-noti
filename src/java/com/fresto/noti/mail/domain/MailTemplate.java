package com.fresto.noti.mail.domain;

import java.io.Serializable;
import java.util.Date;

public class MailTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5081137582434454213L;

	/**
	 * 템플릿 아이디
	 */
	private String templateId;
	
	/**
	 * 템플릿 경로
	 */
	private String templateDir;
	
	/**
	 * 템플릿 파일명
	 */
	private String templateFilename;
	
	/**
	 * 메일 제목
	 */
	private String mailSubject;
	
	/**
	 * 데이터 모델 종류
	 * <li>J: JSON</li>
	 * <li>X: XML</li>
	 * <li>P: PARAMETER</li>
	 */
	private String dataModelType;
	
	/**
	 * 사용 유무 (Y/N)
	 */
	private String useFlag;
	
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
	
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateDir() {
		return templateDir;
	}
	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}
	public String getTemplateFilename() {
		return templateFilename;
	}
	public void setTemplateFilename(String templateFilename) {
		this.templateFilename = templateFilename;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getDataModelType() {
		return dataModelType;
	}
	public void setDataModelType(String dataModelType) {
		this.dataModelType = dataModelType;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
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
