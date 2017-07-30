package com.smict.appointment.action;

import java.util.List;

public class AppointmentModel {
	
	private int appointmentID;
	private int doctorID;
	private String branchCode, branchID;
	private String dateStart, dateEnd, dateTimeZoneStart, dateTimeZoneEnd, date, timeStart, timeEnd;
	private int contactStatus, appointmentStatus;
	private String description;
	private List<AppointmentModel> appoinmentList;
	
	/**
	 * Patients
	 */
	private String HN;
	private String firstNameTH, lastNameTH;
	private String firstNameEN, lastNameEN;
	private String identification;
	
	/**
	 * Symptom
	 */
	private String symptom;
	private int symptomID;
	
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
}
