package com.smict.all.model;
import java.util.Date;

public class ContypeModel {
	int patient_contypeid, sub_contact_id, dayOutBalance, contact_id, renewalYear;
	String hn, sub_contact_name, contact_name;
	Date create_datetime, expire_datetime;
	
	public ContypeModel(){}

	public int getPatient_contypeid() {
		return patient_contypeid;
	}

	public void setPatient_contypeid(int patient_contypeid) {
		this.patient_contypeid = patient_contypeid;
	}

	public int getSub_contact_id() {
		return sub_contact_id;
	}

	public void setSub_contact_id(int sub_contact_id) {
		this.sub_contact_id = sub_contact_id;
	}

	public String getHn() {
		return hn;
	}

	public void setHn(String hn) {
		this.hn = hn;
	}

	public Date getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(Date create_datetime) {
		this.create_datetime = create_datetime;
	}

	public Date getExpire_datetime() {
		return expire_datetime;
	}

	public void setExpire_datetime(Date expire_datetime) {
		this.expire_datetime = expire_datetime;
	}

	public String getSub_contact_name() {
		return sub_contact_name;
	}

	public void setSub_contact_name(String sub_contact_name) {
		this.sub_contact_name = sub_contact_name;
	}

	public int getDayOutBalance() {
		return dayOutBalance;
	}

	public void setDayOutBalance(int dayOutBalance) {
		this.dayOutBalance = dayOutBalance;
	}

	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public int getRenewalYear() {
		return renewalYear;
	}

	public void setRenewalYear(int renewalYear) {
		this.renewalYear = renewalYear;
	}
	
}
