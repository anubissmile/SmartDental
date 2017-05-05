package com.smict.treatment.model;

public class TreatmentModel {

	/**
	 * Patient
	 */
	private String preName, firstNameTH, lastNameTH, firstNameEN, lastNameEN, hn;
	
	/**
	 * Branch
	 */
	private String branchCode, branchId, branchName;
	
	/**
	 * Queue status.
	 */
	private int qstatusKey, queueId;
	private String qstatusDescription;
	
	/**
	 * DATETIME 
	 */
	private String createdAt, updatedAt;
	
	/**
	 * Doctor workday
	 */
	private int workdayId;
	
	public TreatmentModel() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * GETTER & SETTER ZONE
	 */
	
	public String getPreName() {
		return preName;
	}



	public void setPreName(String preName) {
		this.preName = preName;
	}



	public String getFirstNameTH() {
		return firstNameTH;
	}



	public void setFirstNameTH(String firstNameTH) {
		this.firstNameTH = firstNameTH;
	}



	public String getLastNameTH() {
		return lastNameTH;
	}



	public void setLastNameTH(String lastNameTH) {
		this.lastNameTH = lastNameTH;
	}



	public String getFirstNameEN() {
		return firstNameEN;
	}



	public void setFirstNameEN(String firstNameEN) {
		this.firstNameEN = firstNameEN;
	}



	public String getLastNameEN() {
		return lastNameEN;
	}



	public void setLastNameEN(String lastNameEN) {
		this.lastNameEN = lastNameEN;
	}



	public String getHn() {
		return hn;
	}



	public void setHn(String hn) {
		this.hn = hn;
	}



	public String getBranchCode() {
		return branchCode;
	}



	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}



	public String getBranchId() {
		return branchId;
	}



	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}



	public String getBranchName() {
		return branchName;
	}



	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public int getQstatusKey() {
		return qstatusKey;
	}


	public void setQstatusKey(int qstatusKey) {
		this.qstatusKey = qstatusKey;
	}

	public String getQstatusDescription() {
		return qstatusDescription;
	}


	public void setQstatusDescription(String qstatusDescription) {
		this.qstatusDescription = qstatusDescription;
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


	public int getWorkdayId() {
		return workdayId;
	}


	public void setWorkdayId(int workdayId) {
		this.workdayId = workdayId;
	}


	/**
	 * @return the queueId
	 */
	public int getQueueId() {
		return queueId;
	}


	/**
	 * @param queueId the queueId to set
	 */
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}

}
