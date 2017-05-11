package com.smict.person.model;

public class DoctorModel extends Person{
	private int DoctorID,BranchID,BookBankId,edu_id,doctor_education_vocabulary;
	
	private String TMCLicense,HireDate,WorkStatus,Title,pre_name,branchName;

	private String first_name_th,last_name_th,first_name_en,last_name_en,pre_name_en,emp_id,contract_id,education_name;

	private String position_id,position_name_th,position_name_en,position_name_short;
	private String branch_id,branchStandID;
	private int price;
	private int checkSize;
	public DoctorModel(){
		
	}

	public DoctorModel(int doctorID,String pre_name, String first_name_th, String last_name_th, String first_name_en,
			String last_name_en,String branchName ,String pre_name_en) {
		super();
		DoctorID = doctorID;
		this.pre_name = pre_name;
		this.first_name_th = first_name_th;
		this.last_name_th = last_name_th;
		this.first_name_en = first_name_en;
		this.last_name_en = last_name_en;
		this.branchName = branchName;
		this.pre_name_en = pre_name_en;
	}

	public int getDoctorID() {
		return DoctorID;
	}

	public void setDoctorID(int doctorID) {
		DoctorID = doctorID;
	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}

	public String getTMCLicense() {
		return TMCLicense;
	}

	public void setTMCLicense(String tMCLicense) {
		TMCLicense = tMCLicense;
	}

	public String getHireDate() {
		return HireDate;
	}

	public void setHireDate(String hireDate) {
		HireDate = hireDate;
	}

	public String getWorkStatus() {
		return WorkStatus;
	}

	public void setWorkStatus(String workStatus) {
		WorkStatus = workStatus;
	}
	
	public String getPre_name() {
		return pre_name;
	}

	public void setPre_name(String pre_name) {
		this.pre_name = pre_name;
	}
	public String getContract_id() {
		return contract_id;
	}

	public void setContract_id(String contract_id) {
		this.contract_id = contract_id;
	}
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getFirst_name_th() {
		return first_name_th;
	}

	public void setFirst_name_th(String first_name_th) {
		this.first_name_th = first_name_th;
	}

	public String getLast_name_th() {
		return last_name_th;
	}

	public void setLast_name_th(String last_name_th) {
		this.last_name_th = last_name_th;
	}

	public String getFirst_name_en() {
		return first_name_en;
	}

	public void setFirst_name_en(String first_name_en) {
		this.first_name_en = first_name_en;
	}

	public String getLast_name_en() {
		return last_name_en;
	}

	public void setLast_name_en(String last_name_en) {
		this.last_name_en = last_name_en;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPre_name_en() {
		return pre_name_en;
	}

	public void setPre_name_en(String pre_name_en) {
		this.pre_name_en = pre_name_en;
	}
	public int getBookBankId() {
		return BookBankId;
	}

	public void setBookBankId(int bookBankId) {
		BookBankId = bookBankId;
	}
	public String getPosition_id() {
		return position_id;
	}

	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}

	public String getPosition_name_th() {
		return position_name_th;
	}

	public void setPosition_name_th(String position_name_th) {
		this.position_name_th = position_name_th;
	}

	public String getPosition_name_en() {
		return position_name_en;
	}

	public void setPosition_name_en(String position_name_en) {
		this.position_name_en = position_name_en;
	}

	public String getPosition_name_short() {
		return position_name_short;
	}

	public void setPosition_name_short(String position_name_short) {
		this.position_name_short = position_name_short;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public int getEdu_id() {
		return edu_id;
	}

	public void setEdu_id(int edu_id) {
		this.edu_id = edu_id;
	}

	public int getDoctor_education_vocabulary() {
		return doctor_education_vocabulary;
	}

	public void setDoctor_education_vocabulary(int doctor_education_vocabulary) {
		this.doctor_education_vocabulary = doctor_education_vocabulary;
	}

	public String getEducation_name() {
		return education_name;
	}

	public void setEducation_name(String education_name) {
		this.education_name = education_name;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBranchStandID() {
		return branchStandID;
	}

	public void setBranchStandID(String branchStandID) {
		this.branchStandID = branchStandID;
	}

	public int getCheckSize() {
		return checkSize;
	}

	public void setCheckSize(int checkSize) {
		this.checkSize = checkSize;
	}

}
