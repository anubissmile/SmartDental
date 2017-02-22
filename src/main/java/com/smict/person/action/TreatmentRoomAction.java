package com.smict.person.action;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.model.TreatmentRoomModel;

@SuppressWarnings("serial")
public class TreatmentRoomAction extends ActionSupport {

	private TreatmentRoomModel treatRoomModel;
	
	public String addNewRoom(){

		System.out.println("Room name : " + treatRoomModel.getRoom_name());
		System.out.println("Branch code : " + treatRoomModel.getRoom_branch_code());
		
		
		return SUCCESS;
	}
	
	
	
	/**
	 * GETTER SETTER ZONE.
	 */
	public TreatmentRoomModel getTreatRoomModel() {
		return treatRoomModel;
	}

	public void setTreatRoomModel(TreatmentRoomModel treatRoomModel) {
		this.treatRoomModel = treatRoomModel;
	}
	
}
