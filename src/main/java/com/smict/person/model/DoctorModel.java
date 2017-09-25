package com.smict.person.model;

import java.util.List;

public class DoctorModel extends Person{
	private int DoctorID,BranchID,BookBankId,edu_id,doctor_education_vocabulary;
	private String strBranchCode, strBranchID,docotorColor;
	
	private int branchMgrID, branchStandardID;

	private String TMCLicense,HireDate,WorkStatus,Title,pre_name,branchName;
	
	private String first_name_th,last_name_th,first_name_en,last_name_en,pre_name_en,emp_id,contract_id,education_name,educational_background;

	private String position_id,position_name_th,position_name_en,position_name_short,positontreatmentCode,treatment_Code,position_treatment_id
					,isCheck,treatment_nameth,can_change,treatmentID,treatment_codeName;
	private String catCode,groupCode;
	private int catid,groupid,doctor_position_treatmentID;
	private double dfpercent,dfbaht,dflap;
	private List<DoctorModel> docModelList;
	/**
	 * Contact
	 */
	private String lineId, email;
	
	/**
	 * 
	 */
	private String branch_id,branchStandID;
	private int price;
	private int checkSize;
	/**
	 * branch stand
	 */
	private String start_datetime,finish_datetime,checkout_time,checkin_time;
	private int income_type,work_hour,late_min,early_min;
	private int account_id,account_bank_id,account_branch_id;
	private String account_number,account_branchID,account_branchName;
	
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

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getEducational_background() {
		return educational_background;
	}

	public void setEducational_background(String educational_background) {
		this.educational_background = educational_background;
	}

	public String getTreatment_Code() {
		return treatment_Code;
	}

	public void setTreatment_Code(String treatment_Code) {
		this.treatment_Code = treatment_Code;
	}

	public String getPosition_treatment_id() {
		return position_treatment_id;
	}

	public void setPosition_treatment_id(String position_treatment_id) {
		this.position_treatment_id = position_treatment_id;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getTreatment_nameth() {
		return treatment_nameth;
	}

	public void setTreatment_nameth(String treatment_nameth) {
		this.treatment_nameth = treatment_nameth;
	}

	public String getPositontreatmentCode() {
		return positontreatmentCode;
	}

	public void setPositontreatmentCode(String positontreatmentCode) {
		this.positontreatmentCode = positontreatmentCode;
	}

	public String getCan_change() {
		return can_change;
	}

	public void setCan_change(String can_change) {
		this.can_change = can_change;
	}

	public String getTreatmentID() {
		return treatmentID;
	}

	public void setTreatmentID(String treatmentID) {
		this.treatmentID = treatmentID;
	}

	public String getTreatment_codeName() {
		return treatment_codeName;
	}

	public void setTreatment_codeName(String treatment_codeName) {
		this.treatment_codeName = treatment_codeName;
	}

	public double getDfpercent() {
		return dfpercent;
	}

	public double getDfbaht() {
		return dfbaht;
	}

	public double getDflap() {
		return dflap;
	}

	public void setDfpercent(double dfpercent) {
		this.dfpercent = dfpercent;
	}

	public void setDfbaht(double dfbaht) {
		this.dfbaht = dfbaht;
	}

	public void setDflap(double dflap) {
		this.dflap = dflap;
	}

	public String getCatCode() {
		return catCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public int getCatid() {
		return catid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getDoctor_position_treatmentID() {
		return doctor_position_treatmentID;
	}

	public void setDoctor_position_treatmentID(int doctor_position_treatmentID) {
		this.doctor_position_treatmentID = doctor_position_treatmentID;
	}
	public int getBranchMgrID() {
		return branchMgrID;
	}

	public int getBranchStandardID() {
		return branchStandardID;
	}

	public void setBranchMgrID(int branchMgrID) {
		this.branchMgrID = branchMgrID;
	}

	public void setBranchStandardID(int branchStandardID) {
		this.branchStandardID = branchStandardID;
	}

	public List<DoctorModel> getDocModelList() {
		return docModelList;
	}

	public void setDocModelList(List<DoctorModel> docModelList) {
		this.docModelList = docModelList;
	}

	public String getStrBranchCode() {
		return strBranchCode;
	}

	public String getStrBranchID() {
		return strBranchID;
	}

	public void setStrBranchCode(String strBranchCode) {
		this.strBranchCode = strBranchCode;
	}

	public void setStrBranchID(String strBranchID) {
		this.strBranchID = strBranchID;
	}

	public String getStart_datetime() {
		return start_datetime;
	}

	public String getFinish_datetime() {
		return finish_datetime;
	}

	public String getCheckout_time() {
		return checkout_time;
	}

	public String getCheckin_time() {
		return checkin_time;
	}

	public int getIncome_type() {
		return income_type;
	}

	public int getWork_hour() {
		return work_hour;
	}

	public int getLate_min() {
		return late_min;
	}

	public int getEarly_min() {
		return early_min;
	}

	public void setStart_datetime(String start_datetime) {
		this.start_datetime = start_datetime;
	}

	public void setFinish_datetime(String finish_datetime) {
		this.finish_datetime = finish_datetime;
	}

	public void setCheckout_time(String checkout_time) {
		this.checkout_time = checkout_time;
	}

	public void setCheckin_time(String checkin_time) {
		this.checkin_time = checkin_time;
	}

	public void setIncome_type(int income_type) {
		this.income_type = income_type;
	}

	public void setWork_hour(int work_hour) {
		this.work_hour = work_hour;
	}

	public void setLate_min(int late_min) {
		this.late_min = late_min;
	}

	public void setEarly_min(int early_min) {
		this.early_min = early_min;
	}

	public int getAccount_id() {
		return account_id;
	}

	public int getAccount_bank_id() {
		return account_bank_id;
	}

	public int getAccount_branch_id() {
		return account_branch_id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public void setAccount_bank_id(int account_bank_id) {
		this.account_bank_id = account_bank_id;
	}

	public void setAccount_branch_id(int account_branch_id) {
		this.account_branch_id = account_branch_id;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getAccount_branchID() {
		return account_branchID;
	}

	public String getAccount_branchName() {
		return account_branchName;
	}

	public void setAccount_branchID(String account_branchID) {
		this.account_branchID = account_branchID;
	}

	public void setAccount_branchName(String account_branchName) {
		this.account_branchName = account_branchName;
	}

	public String getDocotorColor() {
		return docotorColor;
	}

	public void setDocotorColor(String docotorColor) {
		this.docotorColor = docotorColor;
	}

}
