package com.smict.appointment.action;

import java.util.List;

public class AppointmentModel {
	
	private int appointmentID;
	private int doctorID;
	private String branchCode, branchID;
	private String dateStart, dateEnd;
	private int status;
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
	public int getStatus() {
		return status;
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
	public void setStatus(int status) {
		this.status = status;
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
}
