package com.smict.product.model;
 

public class LabServiceModel {
	
	private String lab_id;
	private String service_id;
	private String est_fee;
	
	private String lab_name;
	private String service_name;
	
	//Contructors
	public LabServiceModel(){}
 
	public LabServiceModel(String lab_id, String lab_name, String service_id, String service_name, String est_fee) {
		super();
		this.lab_id = lab_id;
		this.lab_name = lab_name;
		this.service_id = service_id; 
		this.service_name = service_name;
		this.est_fee = est_fee;
	}

	//Reset
	public void Reset_LabServiceModel(){
		this.lab_id = "";
		this.service_id = "";
		this.est_fee = ""; 
	}

	public String getLab_id() {
		return lab_id;
	}

	public void setLab_id(String lab_id) {
		this.lab_id = lab_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getEst_fee() {
		return est_fee;
	}

	public void setEst_fee(String est_fee) {
		this.est_fee = est_fee;
	}

	public String getLab_name() {
		return lab_name;
	}

	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	 
	
	//Get Set
}
