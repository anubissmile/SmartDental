package com.smict.person.model;

public class BrandModel
{
	private int brand_id;
	private String brand_name;
	
	private int[] brandIDArr;
	private String[] brandNameArr;
	
	
	// Contructor
	public BrandModel()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public BrandModel(int brand_id, String brand_name) 
	{
		super();
		this.brand_id = brand_id;
		this.brand_name = brand_name;
	}





	//Getter & Setter
	public int getBrand_id() 
	{
		return brand_id;
	}


	public void setBrand_id(int brand_id) 
	{
		this.brand_id = brand_id;
	}


	public String getBrand_name()
	{
		return brand_name;
	}


	public void setBrand_name(String brand_name) 
	{
		this.brand_name = brand_name;
	}
	
	
	//Reset Form
	public void ResetForm()
	{
		this.brand_id = 0;
		this.brand_name = "";
	}


	public int[] getBrandIDArr() {
		return brandIDArr;
	}


	public void setBrandIDArr(int[] brandIDArr) {
		this.brandIDArr = brandIDArr;
	}


	public String[] getBrandNameArr() {
		return brandNameArr;
	}


	public void setBrandNameArr(String[] brandNameArr) {
		this.brandNameArr = brandNameArr;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}