package com.smict.document.model;

public class DocumentModel {
	private int document_id;
	private String hn,path,doc_type,upload_date,document_folder,class_icon,docDate,doc_name;
	private String reason;
	private int docStatus;

	/**
	 * Document status.
	 */
	int statusKey;
	String statusDescription;
	
	/**
	 * Document Log.
	 */
	int logId, oldStatus, newStatus;
	String empId;
	String createdAt, updatedAt;
	
	
	public DocumentModel(){
		super();
	}
	
	public int getDocument_id() {
		return document_id;
	}
	public void setDocument_id(int document_id) {
		this.document_id = document_id;
	}
	public String getHn() {
		return hn;
	}
	public void setHn(String hn) {
		this.hn = hn;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}

	public String getDocument_folder() {
		return document_folder;
	}

	public void setDocument_folder(String document_folder) {
		this.document_folder = document_folder;
	}

	public String getClass_icon() {
		return class_icon;
	}

	public void setClass_icon(String class_icon) {
		this.class_icon = class_icon;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(int docStatus) {
		this.docStatus = docStatus;
	}

	public int getStatusKey() {
		return statusKey;
	}

	public void setStatusKey(int statusKey) {
		this.statusKey = statusKey;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(int oldStatus) {
		this.oldStatus = oldStatus;
	}

	public int getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(int newStatus) {
		this.newStatus = newStatus;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
