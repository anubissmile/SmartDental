package com.smict.all.model;

public class FinanceModel {
	private String treatment_id;
	private String product_id,product_name,product_free,product_transfer,amount,amountTotal; 
	
	public FinanceModel(){ 
		super();
	}
	public FinanceModel(String product_id, String product_name, String product_free, String product_transfer,
			String amount, String amountTotal) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_free = product_free;
		this.product_transfer = product_transfer;
		this.amount = amount;
		this.amountTotal = amountTotal;
	}

	public String getTreatment_id() {
		return treatment_id;
	}  
	public void setTreatment_id(String treatment_id) {
		this.treatment_id = treatment_id;
	} 
	public String getProduct_id() {
		return product_id;
	} 
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	} 
	public String getProduct_name() {
		return product_name;
	} 
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	} 
	public String getProduct_free() {
		return product_free;
	} 
	public void setProduct_free(String product_free) {
		this.product_free = product_free;
	} 
	public String getProduct_transfer() {
		return product_transfer;
	} 
	public void setProduct_transfer(String product_transfer) {
		this.product_transfer = product_transfer;
	} 
	public String getAmount() {
		return amount;
	} 
	public void setAmount(String amount) {
		this.amount = amount;
	} 
	public String getAmountTotal() {
		return amountTotal;
	} 
	public void setAmountTotal(String amountTotal) {
		this.amountTotal = amountTotal;
	} 
}