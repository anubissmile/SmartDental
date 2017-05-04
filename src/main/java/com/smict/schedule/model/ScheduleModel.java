package com.smict.schedule.model;



public class ScheduleModel {
	private int doctorId, branchId, branchRoomId, workDayId;
	private int workHour;
	private String startTime, endTime, startDateTime, endDateTime, checkInStatus, checkInDateTime, checkOutDateTime, workDate;
	
	/**
	 * DOCTOR CREDENTIALS
	 */
	private String first_name_th, last_name_th, pre_name_th;
	
	/**
	 * ROOM.
	 */
	private String roomName;
	
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
	
	public boolean setDBField(int doctorId, int branchId, int branchRoomId, String startDateTime, 
			String endDateTime, String checkInStatus, String checkInDateTime, String checkOutDateTime, int workHour, String first_name_th, 
			String last_name_th, String roomName, String workDate, int workDayId){
		
		setWorkHour(workHour);
		setDoctorId(doctorId);
		setBranchId(branchId);
		setBranchRoomId(branchRoomId);
		setStartDateTime(startDateTime);
		setEndDateTime(endDateTime);
		setCheckInStatus(checkInStatus);
		setCheckInDateTime(checkInDateTime);
		setCheckOutDateTime(checkOutDateTime);
		setFirst_name_th(first_name_th);
		setLast_name_th(last_name_th);
		setRoomName(roomName);
		setWorkDate(workDate);
		setWorkDayId(workDayId);
		
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

	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}

	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
	 * @return the workDayId
	 */
	public int getWorkDayId() {
		return workDayId;
	}

	/**
	 * @param workDayId the workDayId to set
	 */
	public void setWorkDayId(int workDayId) {
		this.workDayId = workDayId;
	}



	public String getPre_name_th() {
		return pre_name_th;
	}

	public void setPre_name_th(String pre_name_th) {
		this.pre_name_th = pre_name_th;
	}
}
