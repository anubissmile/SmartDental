package com.smict.person.model;

import java.util.List;

import com.smict.promotion.model.PromotionModel;

public class OweModel
{
	private int owe_id;
	private int receipt_id;
	private String hn;
	private String branch_id;
	private String owe_date;
	
	private List<OweModel> oweList;
	 
	
	// Contructor
	public OweModel(){
		super();
	}


	 
	public int getReceipt_id() {
		return receipt_id;
	} 
	public void setReceipt_id(int receipt_id) {
		this.receipt_id = receipt_id;
	} 
	public String getHn() {
		return hn;
	} 
	public void setHn(String hn) {
		this.hn = hn;
	} 
	public String getBranch_id() {
		return branch_id;
	} 
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	} 
	public String getOwe_date() {
		return owe_date;
	} 
	public void setOwe_date(String owe_date) {
		this.owe_date = owe_date;
	} 
	public List<OweModel> getOweList() {
		return oweList;
	} 
	public void setOweList(List<OweModel> oweList) {
		this.oweList = oweList;
	}    
	//Getter & Setter



	public int getOwe_id() {
		return owe_id;
	}



	public void setOwe_id(int owe_id) {
		this.owe_id = owe_id;
	}
	 
 
	
}