package com.smict.person.model;

public class Pre_nameModel
{
	private String pre_name_id;
	private String pre_name_th;
	private String pre_name_en;
	// Contructor
	public Pre_nameModel() {
		super();
	}

	public Pre_nameModel(String pre_name_id, String pre_name_th, String pre_name_en) {
		this.pre_name_id = pre_name_id;
		this.pre_name_th = pre_name_th;
		this.pre_name_en = pre_name_en;
	}

	// Getter & Setter
	
	public String getPre_name_th() 
	{
		return pre_name_th;
	}

	public void setPre_name_th(String pre_name_th) 
	{
		this.pre_name_th = pre_name_th;
	}

	public String getPre_name_en() 
	{
		return pre_name_en;
	}

	public void setPre_name_en(String pre_name_en) 
	{
		this.pre_name_en = pre_name_en;
	}

	// Reset
	public void ResetForm() 
	{
		this.pre_name_id="";
		this.pre_name_th = "";
		this.pre_name_en = "";
	}

	public String getPre_name_id() {
		return pre_name_id;
	}

	public void setPre_name_id(String pre_name_id) {
		this.pre_name_id = pre_name_id;
	}

}
