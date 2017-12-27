package com.smict.all.model;

import java.util.List;

import com.smict.promotion.model.PromotionModel;

public class ReportReceiptOweModel extends FinanceModel {
	private String receipt_no,owe_no,create_date_receipt;
	  
	public ReportReceiptOweModel(){ 
		super();
	}

	public String getReceipt_no() {
		return receipt_no;
	} 
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	} 
	public String getCreate_date_receipt() {
		return create_date_receipt;
	} 
	public void setCreate_date_receipt(String create_date_receipt) {
		this.create_date_receipt = create_date_receipt;
	} 
	public String getOwe_no() {
		return owe_no;
	} 
	public void setOwe_no(String owe_no) {
		this.owe_no = owe_no;
	} 
	 
}