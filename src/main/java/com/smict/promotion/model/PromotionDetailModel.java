package com.smict.promotion.model;

public class PromotionDetailModel {

	String name,type,product_type,product_id,tname,pname;
	double discount_baht,discount_percent;
	int id,promotion_id;

	public PromotionDetailModel() {
		super();
	}
	
	public int getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(int promotion_id) {
		this.promotion_id = promotion_id;
	}
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
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public double getDiscount_baht() {
		return discount_baht;
	}
	public void setDiscount_baht(double discount_baht) {
		this.discount_baht = discount_baht;
	}
	public double getDiscount_percent() {
		return discount_percent;
	}
	public void setDiscount_percent(double discount_percent) {
		this.discount_percent = discount_percent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

}