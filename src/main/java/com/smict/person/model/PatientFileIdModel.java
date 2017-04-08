package com.smict.person.model;

public class PatientFileIdModel {
	
	/**
	 * FIELDs
	 */
	String hn, branchHn;
	String branchId;

	public PatientFileIdModel(String hn, String branchHn, String branchId) {
		// TODO Auto-generated constructor stub
		super();
		setHn(hn);
		setBranchHn(branchHn);
		setBranchId(branchId);
	}

	
	/**
	 * GETTER & SETTER ZONE
	 */
	
	public String getHn() {
		return hn;
	}

	public void setHn(String hn) {
		this.hn = hn;
	}

	public String getBranchHn() {
		return branchHn;
	}

	public void setBranchHn(String branchHn) {
		this.branchHn = branchHn;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
