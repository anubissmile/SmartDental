package com.smict.promotion.model;

import java.util.List;

public class PromotionModel {
	
	/**
	 * promotion sub contact
	 */
	String sub_contactid,sub_contactname,contact_id,sms_piority,sub_contact_type_id;
	/**
	 * promotion sub contact wallet
	 */
	String sub_contact_walletid,patient_hn,contypeName;
	double total_amount;
	/**
	 * promotion sub contact wallet line
	 */
	String sub_contact_wallet_lineid,sub_contact_wallet_line_type,sub_contact_wallet_line_emp_id,subconwalline_treatment_patient_id;
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
}
