package com.smict.auth;

public class AuthModel {
	
	private String empUsr, empPWD, empId;
	private String fNameTH, lNameTH, fNameEN, lNameEN, prefixName;
	private String branchID, branchCode;
	private int role;
	private String roleNameTH, roleNameEN;
	private String birth, identification, hireDate, phone, remark;
	private int isAssistant;
	private String picture;
	private String branchName, brandName;

	/**
	 * ADDRESS.
	 */
	private String no, block, village, alley, road, district, city, province, zipcode;
	
	
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIsAssistant() {
		return isAssistant;
	}

	public void setIsAssistant(int isAssistant) {
		this.isAssistant = isAssistant;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getAlley() {
		return alley;
	}

	public void setAlley(String alley) {
		this.alley = alley;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
}
