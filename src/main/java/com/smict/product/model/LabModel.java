package com.smict.product.model;
 

public class LabModel {
	
	private String lab_id;
	private String lab_name;
	private String addr_id; 
	
	//Contructors
	public LabModel(){}
 
	public LabModel(String lab_id, String lab_name, String addr_id) {
		super();
		this.lab_id = lab_id;
		this.lab_name = lab_name;
		this.addr_id = addr_id;
	}

	//Reset
	public void Reset_LabModel(){
		this.lab_id = "";
		this.lab_name = "";
		this.addr_id = ""; 
	}

	public String getLab_id() {
		return lab_id;
	}

	public void setLab_id(String lab_id) {
		this.lab_id = lab_id;
	}

	public String getLab_name() {
		return lab_name;
	}

	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}

	public String getAddr_id() {
		return addr_id;
	}

	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
	}
	
	//Get Set
}
