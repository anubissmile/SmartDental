package com.smict.promotion.model;

public class PromotionDetailModel {

	String name,type,list;
	double discount_b,discount_p;
	int id;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public double getDiscount_b() {
		return discount_b;
	}
	public void setDiscount_b(double discount_b) {
		this.discount_b = discount_b;
	}
	public double getDiscount_p() {
		return discount_p;
	}
	public void setDiscount_p(double discount_p) {
		this.discount_p = discount_p;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
