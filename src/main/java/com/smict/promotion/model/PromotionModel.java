package com.smict.promotion.model;

import java.util.List;

public class PromotionModel {
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
	
	int[] sub_contact_id;

	String[]  sub_contact_name,promotion_branch_name,promotion_branch_id; 
	
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
	public String getIsmonday() {

		return buildStringfromboolean(ismonday);
	}
	
	public String buildStringfromboolean(String strBoolean){
		if(strBoolean.length()<=4)
		{
			return "1";
		}
		else{
			return "0";
		}

	}
	public String buildStringfrombooleanFalse(String strBooleanFalse){
		if(strBooleanFalse.length()<=4)
		{
			return "0";
		}
		else{
			return "1";
		}

	}	
	
	public void setIsmonday(String ismonday) {
		
			this.ismonday = ismonday;
	}
	public String getIstuesday() {
		return buildStringfromboolean(istuesday);
	}
	public void setIstuesday(String istuesday) {
		this.istuesday = istuesday;
	}
	public String getIswendesday() {
		return buildStringfromboolean(iswendesday);
	}
	public void setIswendesday(String iswendesday) {
		this.iswendesday = iswendesday;
	}
	public String getIsthursday() {
		return buildStringfromboolean(isthursday);
	}
	public void setIsthursday(String isthursday) {
		this.isthursday = isthursday;
	}
	public String getIsfriday() {
		return buildStringfromboolean(isfriday);
	}
	public void setIsfriday(String isfriday) {
		this.isfriday = isfriday;
	}
	public String getIssaturday() {
		return buildStringfromboolean(issaturday);
	}
	public void setIssaturday(String issaturday) {
		this.issaturday = issaturday;
	}
	public String getIssunday() {
		return buildStringfromboolean(issunday);
	}
	public void setIssunday(String issunday) {
		this.issunday = issunday;
	}
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
	public String getIs_allday() {
		return buildStringfrombooleanFalse(is_allday);
	}
	public void setIs_allday(String is_allday) {
		this.is_allday = is_allday;
	}
	public String getIs_alltime() {
		return buildStringfromboolean(is_alltime);
	}
	public void setIs_alltime(String is_alltime) {
		this.is_alltime = is_alltime;
	}
	public String getIs_allsubcontact() {
		return buildStringfromboolean(is_allsubcontact);
	}
	public void setIs_allsubcontact(String is_allsubcontact) {
		this.is_allsubcontact = is_allsubcontact;
	}
	public String getIs_birthmonth() {
		return buildStringfromboolean(is_birthmonth);
	}
	public void setIs_birthmonth(String is_birthmonth) {
		this.is_birthmonth = is_birthmonth;
	}
	public String getIs_allage() {
		return buildStringfrombooleanFalse(is_allage);
	}
	public void setIs_allage(String is_allage) {
		this.is_allage = is_allage;
	}
	public String getIs_allbranch() {
		return buildStringfromboolean(is_allbranch);
	}
	public void setIs_allbranch(String is_allbranch) {
		this.is_allbranch = is_allbranch;
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
}
