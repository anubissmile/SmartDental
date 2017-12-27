package com.smict.promotion.model;

public class PromotionDetailModel {

	String name,type,tname,pname;
	double discount_baht,discount_percent;
	int id,promotion_id;
	private double discount_amount;
	private int pro_treatmentID,pro_treatmentType,discount_type,product_type,product_id;
	private String dis_amountbaht,dis_amountpercent,qty;
	
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

	public double getDiscount_amount() {
		return discount_amount;
	}

	public int getPro_treatmentID() {
		return pro_treatmentID;
	}

	public int getPro_treatmentType() {
		return pro_treatmentType;
	}

	public int getDiscount_type() {
		return discount_type;
	}

	public String getDis_amountbaht() {
		return dis_amountbaht;
	}

	public String getDis_amountpercent() {
		return dis_amountpercent;
	}

	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}

	public void setPro_treatmentID(int pro_treatmentID) {
		this.pro_treatmentID = pro_treatmentID;
	}

	public void setPro_treatmentType(int pro_treatmentType) {
		this.pro_treatmentType = pro_treatmentType;
	}

	public void setDiscount_type(int discount_type) {
		this.discount_type = discount_type;
	}

	public void setDis_amountbaht(String dis_amountbaht) {
		this.dis_amountbaht = dis_amountbaht;
	}

	public void setDis_amountpercent(String dis_amountpercent) {
		this.dis_amountpercent = dis_amountpercent;
	}

	public int getProduct_type() {
		return product_type;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

}