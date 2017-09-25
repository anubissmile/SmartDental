package com.smict.person.model;

public class TreatmentRoomModel {
	int room_id;
	String room_name = "", room_branch_code = "";

	public TreatmentRoomModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * CONSTRUCTOR.
	 * @param room_id
	 * @param room_name
	 * @param room_branch_code
	 */
	public TreatmentRoomModel(int room_id, String room_name, String room_branch_code){
		this.room_id = room_id;
		this.room_name = room_name;
		this.room_branch_code = room_branch_code;
	}

	
	/**
	 * GETTER SETTER ZONE.
	 */
	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getRoom_branch_code() {
		return room_branch_code;
	}

	public void setRoom_branch_code(String room_branch_code) {
		this.room_branch_code = room_branch_code;
	}
}
