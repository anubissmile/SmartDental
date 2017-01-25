package com.smict.product.model;
 

public class LabModeModel {
	
	private String labmode_id;
	private String labmode_name; 
	
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
 
 
	//Get Set
}
