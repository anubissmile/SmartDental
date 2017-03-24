package com.smict.schedule.model;



public class ScheduleModel {
	private int doctorId, branchId, branchRoomId;
	private int workHour;
	private String startTime, endTime, startDateTime, endDateTime, checkInStatus, checkInDateTime, checkOutDateTime, workDate;
	
	public ScheduleModel(){
		super();
	}
	
	public boolean setAll(int doctorId, int branchId, int branchRoomId, String startDateTime, 
			String endDateTime, String checkInStatus, String checkInDateTime, String checkOutDateTime, int workHour, String workDate){
		
		setWorkHour(workHour);
		setDoctorId(doctorId);
		setBranchId(branchId);
		setBranchRoomId(branchRoomId);
		setStartDateTime(startDateTime);
		setEndDateTime(endDateTime);
		setCheckInStatus(checkInStatus);
		setCheckInDateTime(checkInDateTime);
		setCheckOutDateTime(checkOutDateTime);
		setWorkDate(workDate);
		
		return true;
	}
	
	/**
	 * GETTER & SETTER ZONE. 
	 */

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getBranchRoomId() {
		return branchRoomId;
	}

	public void setBranchRoomId(int branchRoomId) {
		this.branchRoomId = branchRoomId;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getCheckInStatus() {
		return checkInStatus;
	}

	public void setCheckInStatus(String checkInStatus) {
		this.checkInStatus = checkInStatus;
	}

	public String getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(String checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public String getCheckOutDateTime() {
		return checkOutDateTime;
	}

	public void setCheckOutDateTime(String checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}

	/**
	 * @return the workHour
	 */
	public int getWorkHour() {
		return workHour;
	}

	/**
	 * @param workHour the workHour to set
	 */
	public void setWorkHour(int workHour) {
		this.workHour = workHour;
	}

	/**
	 * @return the workDate
	 */
	public String getWorkDate() {
		return workDate;
	}

	/**
	 * @param workDate the workDate to set
	 */
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
