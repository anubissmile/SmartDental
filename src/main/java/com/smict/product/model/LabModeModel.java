package com.smict.product.model;
 

public class LabModeModel {
	
	private String labmode_id;
	private String labmode_name; 
	private String labmode_code;
	private String treatG_id, treatG_name, treatG_code;
	private String categoryID,categoryCode,categoryName;
	//Contructors
	public LabModeModel(){}
 
	public LabModeModel(String labmode_id, String labmode_name) {
		super();
		this.labmode_id = labmode_id;
		this.labmode_name = labmode_name;
	}

	//Reset
	public void Reset_ModeLabModel(){
		this.labmode_id = "";
		this.labmode_name = ""; 
	}

	public String getLabmode_id() {
		return labmode_id;
	}

	public void setLabmode_id(String labmode_id) {
		this.labmode_id = labmode_id;
	}

	public String getLabmode_name() {
		return labmode_name;
	}

	public void setLabmode_name(String labmode_name) {
		this.labmode_name = labmode_name;
	}

	public String getLabmode_code() {
		return labmode_code;
	}

	public void setLabmode_code(String labmode_code) {
		this.labmode_code = labmode_code;
	}

	public String getTreatG_name() {
		return treatG_name;
	}

	public void setTreatG_name(String treatG_name) {
		this.treatG_name = treatG_name;
	}

	public String getTreatG_id() {
		return treatG_id;
	}

	public void setTreatG_id(String treatG_id) {
		this.treatG_id = treatG_id;
	}

	public String getTreatG_code() {
		return treatG_code;
	}

	public void setTreatG_code(String treatG_code) {
		this.treatG_code = treatG_code;
	}

	public String getCategoryID() {
		return categoryID;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
 
 
	//Get Set
}
