package com.smict.person.model;

import java.util.List;

import com.smict.promotion.model.PromotionModel;

public class DepositModel
{
	private int deposit_id;
	private String hn;
	private String branch_id;
	private String deposit_type;
	private String deposit_by;
	private String deposit_date;
	private double old_money;
	private double transfer_money;
	private double total_money;
	private String remark;
	
	private List<DepositModel> depositList;
	
	private int[] brandIDArr;
	private String[] brandNameArr;
	
	
	// Contructor
	public DepositModel(){
		super();
	}  
	//Reset Form
	public void ResetForm()
	{
		this.deposit_id = 0;
		this.deposit_type = "";
	}

	//Getter & Setter
	public int getDeposit_id() {
		return deposit_id;
	}

	public void setDeposit_id(int deposit_id) {
		this.deposit_id = deposit_id;
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

	public String getDeposit_type() {
		return deposit_type;
	}

	public void setDeposit_type(String deposit_type) {
		this.deposit_type = deposit_type;
	}

	public String getDeposit_by() {
		return deposit_by;
	}

	public void setDeposit_by(String deposit_by) {
		this.deposit_by = deposit_by;
	}

	public double getOld_money() {
		return old_money;
	}

	public void setOld_money(double old_money) {
		this.old_money = old_money;
	}

	public double getTransfer_money() {
		return transfer_money;
	}

	public void setTransfer_money(double transfer_money) {
		this.transfer_money = transfer_money;
	}

	public double getTotal_money() {
		return total_money;
	}

	public void setTotal_money(double total_money) {
		this.total_money = total_money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeposit_date() {
		return deposit_date;
	}
	public void setDeposit_date(String deposit_date) {
		this.deposit_date = deposit_date;
	}
	public List<DepositModel> getDepositList() {
		return depositList;
	}
	public void setDepositList(List<DepositModel> depositList) {
		this.depositList = depositList;
	}
 
	
}