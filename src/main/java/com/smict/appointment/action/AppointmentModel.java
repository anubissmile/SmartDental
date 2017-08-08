package com.smict.appointment.action;

import java.util.List;

public class AppointmentModel {
	
	private int appointmentID;
	private int doctorID;
	private String postponeReferenceID;
	private int remindDate;
	private String appointmentCode;
	private String branchCode, branchID;
	private String dateStart, dateEnd, dateTimeZoneStart, dateTimeZoneEnd, date, timeStart, timeEnd;
	private int contactStatus, appointmentStatus,appconstatus;
	private String description,appointCode,recommend,referID,conractdes,contactdate,contimestart;
	private List<AppointmentModel> appoinmentList;
	private int remindDateCount;
	
	/**
	 * Patients
	 */
	private String HN;
	private String firstNameTH, lastNameTH,patPrenameth,pattimestart,pattimeend;
	private String firstNameEN, lastNameEN;
	private String identification;
	
	/**
	 * Symptom
	 */
	private String symptom,sympDescription;
	private int symptomID;
	
	/**
	 * branch
	 */
	private String branchName;
	
	
	/**
	 * doctor
	 */
	private String docfirstname,doclastname,docprenameth;
	private int docid;
	
	/**
	 * GETTER & SETTER ZONE
	 */
	
	public int getAppointmentID() {
		return appointmentID;
	}
	public int getDoctorID() {
		return doctorID;
	}
	public String getHN() {
		return HN;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public String getBranchID() {
		return branchID;
	}
	public String getDateStart() {
		return dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public String getDescription() {
		return description;
	}
	public List<AppointmentModel> getAppoinmentList() {
		return appoinmentList;
	}
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public void setHN(String hN) {
		HN = hN;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAppoinmentList(List<AppointmentModel> appoinmentList) {
		this.appoinmentList = appoinmentList;
	}
	public String getFirstNameTH() {
		return firstNameTH;
	}
	public String getLastNameTH() {
		return lastNameTH;
	}
	public String getFirstNameEN() {
		return firstNameEN;
	}
	public String getLastNameEN() {
		return lastNameEN;
	}
	public String getIdentification() {
		return identification;
	}
	public void setFirstNameTH(String firstNameTH) {
		this.firstNameTH = firstNameTH;
	}
	public void setLastNameTH(String lastNameTH) {
		this.lastNameTH = lastNameTH;
	}
	public void setFirstNameEN(String firstNameEN) {
		this.firstNameEN = firstNameEN;
	}
	public void setLastNameEN(String lastNameEN) {
		this.lastNameEN = lastNameEN;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSymptom() {
		return symptom;
	}
	public int getSymptomID() {
		return symptomID;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public void setSymptomID(int symptomID) {
		this.symptomID = symptomID;
	}
	public String getDateTimeZoneStart() {
		return dateTimeZoneStart;
	}
	public String getDateTimeZoneEnd() {
		return dateTimeZoneEnd;
	}
	public void setDateTimeZoneStart(String dateTimeZoneStart) {
		this.dateTimeZoneStart = dateTimeZoneStart;
	}
	public void setDateTimeZoneEnd(String dateTimeZoneEnd) {
		this.dateTimeZoneEnd = dateTimeZoneEnd;
	}
	public String getTimeStart() {
		return timeStart;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	public int getContactStatus() {
		return contactStatus;
	}
	public int getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setContactStatus(int contactStatus) {
		this.contactStatus = contactStatus;
	}
	public void setAppointmentStatus(int appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	public String getAppointmentCode() {
		return appointmentCode;
	}
	public void setAppointmentCode(String appointmentCode) {
		this.appointmentCode = appointmentCode;
	}
	public String getPostponeReferenceID() {
		return postponeReferenceID;
	}
	public void setPostponeReferenceID(String postponeReferenceID) {
		this.postponeReferenceID = postponeReferenceID;
	}
	public int getRemindDate() {
		return remindDate;
	}
	public void setRemindDate(int remindDate) {
		this.remindDate = remindDate;
	}
	public String getBranchName() {
		return branchName;
	}
	public String getDocfirstname() {
		return docfirstname;
	}
	public String getDoclastname() {
		return doclastname;
	}
	public int getDocid() {
		return docid;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public void setDocfirstname(String docfirstname) {
		this.docfirstname = docfirstname;
	}
	public void setDoclastname(String doclastname) {
		this.doclastname = doclastname;
	}
	public void setDocid(int docid) {
		this.docid = docid;
	}
	public String getAppointCode() {
		return appointCode;
	}
	public void setAppointCode(String appointCode) {
		this.appointCode = appointCode;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getReferID() {
		return referID;
	}
	public void setReferID(String referID) {
		this.referID = referID;
	}
	public String getDocprenameth() {
		return docprenameth;
	}
	public void setDocprenameth(String docprenameth) {
		this.docprenameth = docprenameth;
	}
	public String getPatPrenameth() {
		return patPrenameth;
	}
	public void setPatPrenameth(String patPrenameth) {
		this.patPrenameth = patPrenameth;
	}
	public String getSympDescription() {
		return sympDescription;
	}
	public void setSympDescription(String sympDescription) {
		this.sympDescription = sympDescription;
	}
	public String getConractdes() {
		return conractdes;
	}
	public String getContactdate() {
		return contactdate;
	}
	public String getContimestart() {
		return contimestart;
	}
	public void setConractdes(String conractdes) {
		this.conractdes = conractdes;
	}
	public void setContactdate(String contactdate) {
		this.contactdate = contactdate;
	}
	public void setContimestart(String contimestart) {
		this.contimestart = contimestart;
	}
	public int getAppconstatus() {
		return appconstatus;
	}
	public void setAppconstatus(int appconstatus) {
		this.appconstatus = appconstatus;
	}
	public String getPattimestart() {
		return pattimestart;
	}
	public String getPattimeend() {
		return pattimeend;
	}
	public void setPattimestart(String pattimestart) {
		this.pattimestart = pattimestart;
	}
	public void setPattimeend(String pattimeend) {
		this.pattimeend = pattimeend;
	}
	public int getRemindDateCount() {
		return remindDateCount;
	}
	public void setRemindDateCount(int remindDateCount) {
		this.remindDateCount = remindDateCount;
	}

}
