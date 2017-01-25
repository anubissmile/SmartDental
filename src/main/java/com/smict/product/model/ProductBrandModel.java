package com.smict.product.model;

import com.smict.standatd.Standard_id_name;

public class ProductBrandModel extends Standard_id_name {
	//Contructors
	public ProductBrandModel(){}

	public ProductBrandModel(String id, String name, String create_by, String create_datetime, String update_by,
			String update_datetime) {
		super(id, name, create_by, create_datetime, update_by, update_datetime);
		// TODO Auto-generated constructor stub
	}
	//Reset
	public void Reset_ProductBrandModel(){
		this.id = "";
		this.name = "";
		this.create_by = "";
		this.create_datetime = "";
		this.update_by = "";
		this.update_datetime = "";
	}
	
	//Get Set
}