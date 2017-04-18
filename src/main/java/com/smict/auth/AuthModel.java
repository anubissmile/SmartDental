package com.smict.auth;

public class AuthModel {
	
	private String empUsr, empPWD;
	private String fNameTH, lNameTH, fNameEN, lNameEN, prefixName;
	private String branchID, branchCode;
	private int role;
	private String roleNameTH, roleNameEN;
	
	public AuthModel(){
		super();
	}
	
	/**
	 * GETTER SETTER ZONE
	 */
	public String getEmpUsr() {
		return empUsr;
	}
	public void setEmpUsr(String empUsr) {
		this.empUsr = empUsr;
	}

	public String getfNameTH() {
		return fNameTH;
	}
	public String getEmpPWD() {
		return empPWD;
	}

	public void setEmpPWD(String empPWD) {
		this.empPWD = empPWD;
	}

	public void setfNameTH(String fNameTH) {
		this.fNameTH = fNameTH;
	}
	public String getlNameTH() {
		return lNameTH;
	}
	public void setlNameTH(String lNameTH) {
		this.lNameTH = lNameTH;
	}
	public String getfNameEN() {
		return fNameEN;
	}
	public void setfNameEN(String fNameEN) {
		this.fNameEN = fNameEN;
	}
	public String getlNameEN() {
		return lNameEN;
	}
	public void setlNameEN(String lNameEN) {
		this.lNameEN = lNameEN;
	}
	public String getPrefixName() {
		return prefixName;
	}
	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getRoleNameTH() {
		return roleNameTH;
	}

	public void setRoleNameTH(String roleNameTH) {
		this.roleNameTH = roleNameTH;
	}

	public String getRoleNameEN() {
		return roleNameEN;
	}

	public void setRoleNameEN(String roleNameEN) {
		this.roleNameEN = roleNameEN;
	}
}
