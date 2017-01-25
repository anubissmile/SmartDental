package com.smict.product.model;
 

public class ServiceModel {
	private String labmode_id;
	private String service_id;
	private String service_name; 
	
	//Contructors
	public ServiceModel(){}
 
	public ServiceModel(String labmode_id, String service_id, String service_name) {
		super();
		this.labmode_id = labmode_id;
		this.service_id = service_id;
		this.service_name = service_name; 
	}

	//Reset
	public void Reset_ServiceModel(){
		this.service_id = "";
		this.service_name = ""; 
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getLabmode_id() {
		return labmode_id;
	}

	public void setLabmode_id(String labmode_id) {
		this.labmode_id = labmode_id;
	}

	 
	//Get Set
}
