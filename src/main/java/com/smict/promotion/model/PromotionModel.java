package com.smict.promotion.model;

import java.util.List;

public class PromotionModel {
	/**
	 * gift voucher header
	 */
	private int gv_id,gv_numberlenght,gv_start_number,gv_run_count;
	private String gv_name,gv_description,gv_prefix,gv_suffix,gv_start_date,gv_expiredate,gv_status,gv_create_date;
	/**
	 * gift voucher line
	 */
	private int gvl_id;
	private String gvl_name,gvl_status;
	/**
	 * gift voucher privilege
	 */
	private int gvp_id,gvp_type,gvp_product_id,gvp_product_type;
	private double gvp_amount;
	private String gvp_amountString;
	private String [] gvp_amountArr,gvp_productArr;
	/**
	 * gift voucher usage
	 */
	
	
	/**
	 * day
	 */
	private int day_id,status_pro,service_charge;
	private String dayName,promotion_description,pro_amountbill,pro_branchID,pro_branchName;
	private String [] dayAll;
	/**
	 * promotion manage
	 */
	private int manage_id,type_cost,points_type,points_id;
	private double points,doctor_cost,company_cost;
	private String doctorCost,companyCost,docbaht,combaht,point;
	private String qty;
	/**
	 * promotion points
	 */
	private String [] firstpoints ;
	private int [] point_contactID;
	/**
	 * giftcard_giftcard
	 */
	String giftcard_name,giftcard_description,giftcard_prefix,giftcard_suffix
	,giftcard_create_date,giftcard_start_date,giftcard_expiredate,giftcard_status;
	int giftcard_id,giftcard_numberlenght,giftcard_start_number,giftcard_run_count;
	double giftcard_default_amount;
	/**
	 * giftcard_line
	 */
	String giftcard_line_name;
	int giftcard_line_id,giftcard_line_giftcard_id;
	double giftcard_line_amount;
	/**
	 * giftcard_line_rel_patient
	 */
	String giftcard_line_rel_patient_hn,firstname,prename,lastname;
	int giftcard_line_rel_patient_id,giftcardrel_giftcard_line_id;
	/**
	 * giftcard_usage
	 */
	int giftcard_usage_id,giftcard_usage_giftline_id,giftcard_usage_receipt_id;
	double giftcard_usage_amount;
	
	/**
	 * promotion sub contact
	 */
	String sub_contactid,sub_contactname,contact_id,sms_piority,sub_contact_type_id,sub_contact_addr,sub_contact_companyName;
	double sub_contact_amount;
	/**
	 * promotion sub contact wallet
	 */
	String sub_contact_walletid,patient_hn,contypeName;
	double total_amount;
	int status_subcontact;
	/**
	 * promotion sub contact wallet line
	 */
	String sub_contact_wallet_lineid,sub_contact_wallet_line_type,sub_contact_wallet_line_emp_id
			,subconwalline_treatment_patient_id,sub_wallet_line_status,sub_wallet_line_date;
	double amount;
	
	String name, use_condition, start_date, end_date, ismonday, istuesday, iswendesday, isthursday, 
					isfriday, issaturday, issunday,is_allday,is_alltime, start_time, end_time,is_allsubcontact,is_birthmonth,is_allage,is_allbranch;
	double billcostover;
	int promotion_id, from_age, to_age, is_treatmentcount,id;
	
	int[] sub_contact_id,subConID;

	String[]  sub_contact_name,promotion_branch_name,promotion_branch_id,probranchID; 
	
	private List<PromotionDetailModel> promotiondetailModel;
	
	public int[] getSub_contact_id() {
		return sub_contact_id;
	}
	public void setSub_contact_id(int[] sub_contact_id) {
		this.sub_contact_id = sub_contact_id;
	}
	public String[] getPromotion_branch_name() {
		return promotion_branch_name;
	}
	public String[] getPromotion_branch_id() {
		return promotion_branch_id;
	}
	public void setPromotion_branch_id(String[] promotion_branch_id) {
		this.promotion_branch_id = promotion_branch_id;
	}
	public void setPromotion_branch_name(String[] promotion_branch_name) {
		this.promotion_branch_name = promotion_branch_name;
	}
	
	public String[] getSub_contact_name() {
		return sub_contact_name;
	}
	public void setSub_contact_name(String[] sub_contact_name) {
		this.sub_contact_name = sub_contact_name;
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
	public String getUse_condition() {
		return use_condition;
	}
	public void setUse_condition(String use_condition) {
		this.use_condition = use_condition;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
/*	public String buildStringfromboolean(String strBoolean){
		if(strBoolean.length()<=4)
		{
			return "1";
		}
		else{
			return "0";
		}

	}*/
/*	public String buildStringfrombooleanFalse(String strBooleanFalse){
		if(strBooleanFalse.length()<=4)
		{
			return "0";
		}
		else{
			return "1";
		}

	}*/	

	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public double getBillcostover() {
		return billcostover;
	}
	public void setBillcostover(double billcostover) {
		this.billcostover = billcostover;
	}
	public int getFrom_age() {
		return from_age;
	}
	public void setFrom_age(int from_age) {
		this.from_age = from_age;
	}
	public int getTo_age() {
		return to_age;
	}
	public void setTo_age(int to_age) {
		this.to_age = to_age;
	}
	public int getIs_treatmentcount() {
		return is_treatmentcount;
	}
	public void setIs_treatmentcount(int is_treatmentcount) {
		this.is_treatmentcount = is_treatmentcount;
	}
	public List<PromotionDetailModel> getPromotiondetailModel() {
		return promotiondetailModel;
	}
	public void setPromotiondetailModel(List<PromotionDetailModel> promotiondetailModel) {
		this.promotiondetailModel = promotiondetailModel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSub_contactid() {
		return sub_contactid;
	}
	public String getSub_contactname() {
		return sub_contactname;
	}
	public String getContact_id() {
		return contact_id;
	}
	public String getSms_piority() {
		return sms_piority;
	}
	public String getSub_contact_type_id() {
		return sub_contact_type_id;
	}
	public String getSub_contact_walletid() {
		return sub_contact_walletid;
	}
	public String getPatient_hn() {
		return patient_hn;
	}
	public double getTotal_amount() {
		return total_amount;
	}
	public String getSub_contact_wallet_lineid() {
		return sub_contact_wallet_lineid;
	}
	public String getSub_contact_wallet_line_type() {
		return sub_contact_wallet_line_type;
	}
	public String getSub_contact_wallet_line_emp_id() {
		return sub_contact_wallet_line_emp_id;
	}
	public String getSubconwalline_treatment_patient_id() {
		return subconwalline_treatment_patient_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setSub_contactid(String sub_contactid) {
		this.sub_contactid = sub_contactid;
	}
	public void setSub_contactname(String sub_contactname) {
		this.sub_contactname = sub_contactname;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public void setSms_piority(String sms_piority) {
		this.sms_piority = sms_piority;
	}
	public void setSub_contact_type_id(String sub_contact_type_id) {
		this.sub_contact_type_id = sub_contact_type_id;
	}
	public void setSub_contact_walletid(String sub_contact_walletid) {
		this.sub_contact_walletid = sub_contact_walletid;
	}
	public void setPatient_hn(String patient_hn) {
		this.patient_hn = patient_hn;
	}
	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}
	public void setSub_contact_wallet_lineid(String sub_contact_wallet_lineid) {
		this.sub_contact_wallet_lineid = sub_contact_wallet_lineid;
	}
	public void setSub_contact_wallet_line_type(String sub_contact_wallet_line_type) {
		this.sub_contact_wallet_line_type = sub_contact_wallet_line_type;
	}
	public void setSub_contact_wallet_line_emp_id(String sub_contact_wallet_line_emp_id) {
		this.sub_contact_wallet_line_emp_id = sub_contact_wallet_line_emp_id;
	}
	public void setSubconwalline_treatment_patient_id(String subconwalline_treatment_patient_id) {
		this.subconwalline_treatment_patient_id = subconwalline_treatment_patient_id;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getContypeName() {
		return contypeName;
	}
	public void setContypeName(String contypeName) {
		this.contypeName = contypeName;
	}
	public int getStatus_subcontact() {
		return status_subcontact;
	}
	public void setStatus_subcontact(int status_subcontact) {
		this.status_subcontact = status_subcontact;
	}
	public String getSub_contact_addr() {
		return sub_contact_addr;
	}
	public String getSub_contact_companyName() {
		return sub_contact_companyName;
	}
	public double getSub_contact_amount() {
		return sub_contact_amount;
	}
	public void setSub_contact_addr(String sub_contact_addr) {
		this.sub_contact_addr = sub_contact_addr;
	}
	public void setSub_contact_companyName(String sub_contact_companyName) {
		this.sub_contact_companyName = sub_contact_companyName;
	}
	public void setSub_contact_amount(double sub_contact_amount) {
		this.sub_contact_amount = sub_contact_amount;
	}
	public String getSub_wallet_line_status() {
		return sub_wallet_line_status;
	}
	public String getSub_wallet_line_date() {
		return sub_wallet_line_date;
	}
	public void setSub_wallet_line_status(String sub_wallet_line_status) {
		this.sub_wallet_line_status = sub_wallet_line_status;
	}
	public void setSub_wallet_line_date(String sub_wallet_line_date) {
		this.sub_wallet_line_date = sub_wallet_line_date;
	}
	public String getGiftcard_name() {
		return giftcard_name;
	}
	public String getGiftcard_description() {
		return giftcard_description;
	}
	public String getGiftcard_prefix() {
		return giftcard_prefix;
	}
	public String getGiftcard_suffix() {
		return giftcard_suffix;
	}
	public String getGiftcard_create_date() {
		return giftcard_create_date;
	}
	public String getGiftcard_start_date() {
		return giftcard_start_date;
	}
	public String getGiftcard_expiredate() {
		return giftcard_expiredate;
	}
	public String getGiftcard_status() {
		return giftcard_status;
	}
	public int getGiftcard_id() {
		return giftcard_id;
	}
	public int getGiftcard_numberlenght() {
		return giftcard_numberlenght;
	}
	public int getGiftcard_start_number() {
		return giftcard_start_number;
	}
	public int getGiftcard_run_count() {
		return giftcard_run_count;
	}
	public double getGiftcard_default_amount() {
		return giftcard_default_amount;
	}
	public String getGiftcard_line_name() {
		return giftcard_line_name;
	}
	public int getGiftcard_line_id() {
		return giftcard_line_id;
	}
	public int getGiftcard_line_giftcard_id() {
		return giftcard_line_giftcard_id;
	}
	public double getGiftcard_line_amount() {
		return giftcard_line_amount;
	}
	public String getGiftcard_line_rel_patient_hn() {
		return giftcard_line_rel_patient_hn;
	}
	public int getGiftcard_line_rel_patient_id() {
		return giftcard_line_rel_patient_id;
	}
	public int getGiftcardrel_giftcard_line_id() {
		return giftcardrel_giftcard_line_id;
	}
	public int getGiftcard_usage_id() {
		return giftcard_usage_id;
	}
	public int getGiftcard_usage_giftline_id() {
		return giftcard_usage_giftline_id;
	}
	public int getGiftcard_usage_receipt_id() {
		return giftcard_usage_receipt_id;
	}
	public double getGiftcard_usage_amount() {
		return giftcard_usage_amount;
	}
	public void setGiftcard_name(String giftcard_name) {
		this.giftcard_name = giftcard_name;
	}
	public void setGiftcard_description(String giftcard_description) {
		this.giftcard_description = giftcard_description;
	}
	public void setGiftcard_prefix(String giftcard_prefix) {
		this.giftcard_prefix = giftcard_prefix;
	}
	public void setGiftcard_suffix(String giftcard_suffix) {
		this.giftcard_suffix = giftcard_suffix;
	}
	public void setGiftcard_create_date(String giftcard_create_date) {
		this.giftcard_create_date = giftcard_create_date;
	}
	public void setGiftcard_start_date(String giftcard_start_date) {
		this.giftcard_start_date = giftcard_start_date;
	}
	public void setGiftcard_expiredate(String giftcard_expiredate) {
		this.giftcard_expiredate = giftcard_expiredate;
	}
	public void setGiftcard_status(String giftcard_status) {
		this.giftcard_status = giftcard_status;
	}
	public void setGiftcard_id(int giftcard_id) {
		this.giftcard_id = giftcard_id;
	}
	public void setGiftcard_numberlenght(int giftcard_numberlenght) {
		this.giftcard_numberlenght = giftcard_numberlenght;
	}
	public void setGiftcard_start_number(int giftcard_start_number) {
		this.giftcard_start_number = giftcard_start_number;
	}
	public void setGiftcard_run_count(int giftcard_run_count) {
		this.giftcard_run_count = giftcard_run_count;
	}
	public void setGiftcard_default_amount(double giftcard_default_amount) {
		this.giftcard_default_amount = giftcard_default_amount;
	}
	public void setGiftcard_line_name(String giftcard_line_name) {
		this.giftcard_line_name = giftcard_line_name;
	}
	public void setGiftcard_line_id(int giftcard_line_id) {
		this.giftcard_line_id = giftcard_line_id;
	}
	public void setGiftcard_line_giftcard_id(int giftcard_line_giftcard_id) {
		this.giftcard_line_giftcard_id = giftcard_line_giftcard_id;
	}
	public void setGiftcard_line_amount(double giftcard_line_amount) {
		this.giftcard_line_amount = giftcard_line_amount;
	}
	public void setGiftcard_line_rel_patient_hn(String giftcard_line_rel_patient_hn) {
		this.giftcard_line_rel_patient_hn = giftcard_line_rel_patient_hn;
	}
	public void setGiftcard_line_rel_patient_id(int giftcard_line_rel_patient_id) {
		this.giftcard_line_rel_patient_id = giftcard_line_rel_patient_id;
	}
	public void setGiftcardrel_giftcard_line_id(int giftcardrel_giftcard_line_id) {
		this.giftcardrel_giftcard_line_id = giftcardrel_giftcard_line_id;
	}
	public void setGiftcard_usage_id(int giftcard_usage_id) {
		this.giftcard_usage_id = giftcard_usage_id;
	}
	public void setGiftcard_usage_giftline_id(int giftcard_usage_giftline_id) {
		this.giftcard_usage_giftline_id = giftcard_usage_giftline_id;
	}
	public void setGiftcard_usage_receipt_id(int giftcard_usage_receipt_id) {
		this.giftcard_usage_receipt_id = giftcard_usage_receipt_id;
	}
	public void setGiftcard_usage_amount(double giftcard_usage_amount) {
		this.giftcard_usage_amount = giftcard_usage_amount;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getPrename() {
		return prename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setPrename(String prename) {
		this.prename = prename;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getDay_id() {
		return day_id;
	}
	public String getDayName() {
		return dayName;
	}
	public String[] getDayAll() {
		return dayAll;
	}
	public void setDay_id(int day_id) {
		this.day_id = day_id;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public void setDayAll(String[] dayAll) {
		this.dayAll = dayAll;
	}
	public String getIsmonday() {
		return ismonday;
	}
	public String getIstuesday() {
		return istuesday;
	}
	public String getIswendesday() {
		return iswendesday;
	}
	public String getIsthursday() {
		return isthursday;
	}
	public String getIsfriday() {
		return isfriday;
	}
	public String getIssaturday() {
		return issaturday;
	}
	public String getIssunday() {
		return issunday;
	}
	public String getIs_allday() {
		return is_allday;
	}
	public String getIs_allage() {
		return is_allage;
	}
	public void setIsmonday(String ismonday) {
		this.ismonday = ismonday;
	}
	public void setIstuesday(String istuesday) {
		this.istuesday = istuesday;
	}
	public void setIswendesday(String iswendesday) {
		this.iswendesday = iswendesday;
	}
	public void setIsthursday(String isthursday) {
		this.isthursday = isthursday;
	}
	public void setIsfriday(String isfriday) {
		this.isfriday = isfriday;
	}
	public void setIssaturday(String issaturday) {
		this.issaturday = issaturday;
	}
	public void setIssunday(String issunday) {
		this.issunday = issunday;
	}
	public void setIs_allday(String is_allday) {
		this.is_allday = is_allday;
	}
	public void setIs_allage(String is_allage) {
		this.is_allage = is_allage;
	}
	public String getIs_alltime() {
		return is_alltime;
	}
	public String getIs_allsubcontact() {
		return is_allsubcontact;
	}
	public String getIs_birthmonth() {
		return is_birthmonth;
	}
	public String getIs_allbranch() {
		return is_allbranch;
	}
	public void setIs_alltime(String is_alltime) {
		this.is_alltime = is_alltime;
	}
	public void setIs_allsubcontact(String is_allsubcontact) {
		this.is_allsubcontact = is_allsubcontact;
	}
	public void setIs_birthmonth(String is_birthmonth) {
		this.is_birthmonth = is_birthmonth;
	}
	public void setIs_allbranch(String is_allbranch) {
		this.is_allbranch = is_allbranch;
	}
	public int[] getSubConID() {
		return subConID;
	}
	public String[] getProbranchID() {
		return probranchID;
	}
	public void setSubConID(int[] subConID) {
		this.subConID = subConID;
	}
	public void setProbranchID(String[] probranchID) {
		this.probranchID = probranchID;
	}
	public String getPromotion_description() {
		return promotion_description;
	}
	public void setPromotion_description(String promotion_description) {
		this.promotion_description = promotion_description;
	}
	public String getPro_amountbill() {
		return pro_amountbill;
	}
	public void setPro_amountbill(String pro_amountbill) {
		this.pro_amountbill = pro_amountbill;
	}
	public int getStatus_pro() {
		return status_pro;
	}
	public void setStatus_pro(int status_pro) {
		this.status_pro = status_pro;
	}
	public int getManage_id() {
		return manage_id;
	}
	public int getType_cost() {
		return type_cost;
	}
	public double getPoints() {
		return points;
	}
	public double getDoctor_cost() {
		return doctor_cost;
	}
	public double getCompany_cost() {
		return company_cost;
	}
	public String getDoctorCost() {
		return doctorCost;
	}
	public String getCompanyCost() {
		return companyCost;
	}
	public void setManage_id(int manage_id) {
		this.manage_id = manage_id;
	}
	public void setType_cost(int type_cost) {
		this.type_cost = type_cost;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public void setDoctor_cost(double doctor_cost) {
		this.doctor_cost = doctor_cost;
	}
	public void setCompany_cost(double company_cost) {
		this.company_cost = company_cost;
	}
	public void setDoctorCost(String doctorCost) {
		this.doctorCost = doctorCost;
	}
	public void setCompanyCost(String companyCost) {
		this.companyCost = companyCost;
	}
	public String getDocbaht() {
		return docbaht;
	}
	public String getCombaht() {
		return combaht;
	}
	public void setDocbaht(String docbaht) {
		this.docbaht = docbaht;
	}
	public void setCombaht(String combaht) {
		this.combaht = combaht;
	}
	public int getPoints_type() {
		return points_type;
	}
	public void setPoints_type(int points_type) {
		this.points_type = points_type;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getPro_branchID() {
		return pro_branchID;
	}
	public String getPro_branchName() {
		return pro_branchName;
	}
	public void setPro_branchID(String pro_branchID) {
		this.pro_branchID = pro_branchID;
	}
	public void setPro_branchName(String pro_branchName) {
		this.pro_branchName = pro_branchName;
	}
	public int getService_charge() {
		return service_charge;
	}
	public void setService_charge(int service_charge) {
		this.service_charge = service_charge;
	}
	public int [] getPoint_contactID() {
		return point_contactID;
	}
	public void setPoint_contactID(int [] point_contactID) {
		this.point_contactID = point_contactID;
	}
	public String [] getFirstpoints() {
		return firstpoints;
	}
	public void setFirstpoints(String [] firstpoints) {
		this.firstpoints = firstpoints;
	}
	public int getPoints_id() {
		return points_id;
	}
	public void setPoints_id(int points_id) {
		this.points_id = points_id;
	}
	public int getGv_id() {
		return gv_id;
	}
	public int getGv_numberlenght() {
		return gv_numberlenght;
	}
	public int getGv_start_number() {
		return gv_start_number;
	}
	public int getGv_run_count() {
		return gv_run_count;
	}
	public String getGv_name() {
		return gv_name;
	}
	public String getGv_description() {
		return gv_description;
	}
	public String getGv_prefix() {
		return gv_prefix;
	}
	public String getGv_suffix() {
		return gv_suffix;
	}
	public String getGv_start_date() {
		return gv_start_date;
	}
	public String getGv_expiredate() {
		return gv_expiredate;
	}
	public String getGv_status() {
		return gv_status;
	}
	public void setGv_id(int gv_id) {
		this.gv_id = gv_id;
	}
	public void setGv_numberlenght(int gv_numberlenght) {
		this.gv_numberlenght = gv_numberlenght;
	}
	public void setGv_start_number(int gv_start_number) {
		this.gv_start_number = gv_start_number;
	}
	public void setGv_run_count(int gv_run_count) {
		this.gv_run_count = gv_run_count;
	}
	public void setGv_name(String gv_name) {
		this.gv_name = gv_name;
	}
	public void setGv_description(String gv_description) {
		this.gv_description = gv_description;
	}
	public void setGv_prefix(String gv_prefix) {
		this.gv_prefix = gv_prefix;
	}
	public void setGv_suffix(String gv_suffix) {
		this.gv_suffix = gv_suffix;
	}
	public void setGv_start_date(String gv_start_date) {
		this.gv_start_date = gv_start_date;
	}
	public void setGv_expiredate(String gv_expiredate) {
		this.gv_expiredate = gv_expiredate;
	}
	public void setGv_status(String gv_status) {
		this.gv_status = gv_status;
	}
	public int getGvl_id() {
		return gvl_id;
	}
	public String getGvl_name() {
		return gvl_name;
	}
	public int getGvp_id() {
		return gvp_id;
	}
	public int getGvp_product_id() {
		return gvp_product_id;
	}
	public int getGvp_product_type() {
		return gvp_product_type;
	}
	public double getGvp_amount() {
		return gvp_amount;
	}
	public String getGvp_amountString() {
		return gvp_amountString;
	}
	public void setGvl_id(int gvl_id) {
		this.gvl_id = gvl_id;
	}
	public void setGvl_name(String gvl_name) {
		this.gvl_name = gvl_name;
	}
	public void setGvp_id(int gvp_id) {
		this.gvp_id = gvp_id;
	}
	public void setGvp_product_id(int gvp_product_id) {
		this.gvp_product_id = gvp_product_id;
	}
	public void setGvp_product_type(int gvp_product_type) {
		this.gvp_product_type = gvp_product_type;
	}
	public void setGvp_amount(double gvp_amount) {
		this.gvp_amount = gvp_amount;
	}
	public void setGvp_amountString(String gvp_amountString) {
		this.gvp_amountString = gvp_amountString;
	}
	public String getGvl_status() {
		return gvl_status;
	}
	public void setGvl_status(String gvl_status) {
		this.gvl_status = gvl_status;
	}
	public String getGv_create_date() {
		return gv_create_date;
	}
	public void setGv_create_date(String gv_create_date) {
		this.gv_create_date = gv_create_date;
	}
	public String[] getGvp_amountArr() {
		return gvp_amountArr;
	}
	public String[] getGvp_productArr() {
		return gvp_productArr;
	}
	public void setGvp_amountArr(String[] gvp_amountArr) {
		this.gvp_amountArr = gvp_amountArr;
	}
	public void setGvp_productArr(String[] gvp_productArr) {
		this.gvp_productArr = gvp_productArr;
	}
	public int getGvp_type() {
		return gvp_type;
	}
	public void setGvp_type(int gvp_type) {
		this.gvp_type = gvp_type;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
}
