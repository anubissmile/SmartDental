package com.smict.treatment.model;

public class TreatmentModel {
	
	/**
	 * Treatment
	 */
	private int treatmentID;
	private String treatmentCode;
	private String treatmentNameTH, treatmentNameEN;
	private int autoHomeCall, recall, isContinue;
	
	
	/**
	 * Treatment group
	 */
	private int treatmentGroupID;
	private String treatmentGroupCode, treatmentGroupName;
	
	/**
	 * Treatment category
	 */
	private int treatmentCategoryID;
	private String treatmentCategoryName, treatmentCategoryCode;
	
	/**
	 * Tooth picture
	 */
	private String toothPicCode, toothPicName;
	
	/**
	 * Tooth type
	 */
	private int toothTypeID;
	private String toothTypeNameTH, toothTypeNameEN;

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


	public int getTreatmentGroupID() {
		return treatmentGroupID;
	}


	public void setTreatmentGroupID(int treatmentGroupID) {
		this.treatmentGroupID = treatmentGroupID;
	}


	public String getTreatmentGroupCode() {
		return treatmentGroupCode;
	}


	public void setTreatmentGroupCode(String treatmentGroupCode) {
		this.treatmentGroupCode = treatmentGroupCode;
	}


	public String getTreatmentGroupName() {
		return treatmentGroupName;
	}


	public void setTreatmentGroupName(String treatmentGroupName) {
		this.treatmentGroupName = treatmentGroupName;
	}


	public int getTreatmentCategoryID() {
		return treatmentCategoryID;
	}


	public void setTreatmentCategoryID(int treatmentCategoryID) {
		this.treatmentCategoryID = treatmentCategoryID;
	}


	public String getTreatmentCategoryName() {
		return treatmentCategoryName;
	}


	public void setTreatmentCategoryName(String treatmentCategoryName) {
		this.treatmentCategoryName = treatmentCategoryName;
	}


	public String getTreatmentCategoryCode() {
		return treatmentCategoryCode;
	}


	public void setTreatmentCategoryCode(String treatmentCategoryCode) {
		this.treatmentCategoryCode = treatmentCategoryCode;
	}


	public String getToothPicCode() {
		return toothPicCode;
	}


	public void setToothPicCode(String toothPicCode) {
		this.toothPicCode = toothPicCode;
	}


	public String getToothPicName() {
		return toothPicName;
	}


	public void setToothPicName(String toothPicName) {
		this.toothPicName = toothPicName;
	}


	public int getToothTypeID() {
		return toothTypeID;
	}


	public void setToothTypeID(int toothTypeID) {
		this.toothTypeID = toothTypeID;
	}


	public String getToothTypeNameTH() {
		return toothTypeNameTH;
	}


	public void setToothTypeNameTH(String toothTypeNameTH) {
		this.toothTypeNameTH = toothTypeNameTH;
	}


	public String getToothTypeNameEN() {
		return toothTypeNameEN;
	}


	public void setToothTypeNameEN(String toothTypeNameEN) {
		this.toothTypeNameEN = toothTypeNameEN;
	}

}
