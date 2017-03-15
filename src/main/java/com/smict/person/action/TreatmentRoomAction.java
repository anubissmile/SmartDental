package com.smict.person.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.TreatmentRoomData;
import com.smict.person.model.BranchModel;
import com.smict.person.model.TreatmentRoomModel;

import ldc.util.Servlet;

@SuppressWarnings("serial")
public class TreatmentRoomAction extends ActionSupport {

	private TreatmentRoomModel treatRoomModel;
	private BranchModel branchModel;
	private int brand_id;
	private String doctor_name;
	private String brand_name, branch_id, branch_code, branch_name;
	private String room_id, room_name;
	
	/**
	 * @author anubissmile
	 * Add new treatment room function.
	 * @return String
	 */
	public String addNewRoom(){

//		System.out.println("Room name : " + treatRoomModel.getRoom_name());
//		System.out.println("Branch code : " + treatRoomModel.getRoom_branch_code());
		TreatmentRoomData trData = new TreatmentRoomData();
		trData.addRoom(treatRoomModel.getRoom_branch_code(), treatRoomModel.getRoom_name());
		
		brand_id = branchModel.getBrand_id();
		doctor_name = branchModel.getDoctor_name();
		brand_name = branchModel.getBrand_name();
		branch_id = branchModel.getBranch_id();
		branch_code = branchModel.getBranch_code();
		branch_name = branchModel.getBranch_name();
		

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		HttpServletResponse response = ServletActionContext.getResponse();
		String site = "branchM-" + branch_code;
		
//		session.setAttribute("branchModel", branchModel);
		/**
		 * REDIRECTING.
		 */
		Servlet serve = new Servlet();
		try {
			serve.redirect(request, response, site);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void validateAddNewRoom(){
		System.out.println("kdjfkd");
	}
	
	public String deleteTreatmentRoom(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String site = "branchM-" + getBranch_code();
		
		TreatmentRoomData trData = new TreatmentRoomData();
		int[] rec = trData.deleteTreatmentRoom(getRoom_id(), getBranch_code());
		if(rec[1]>0){
			try {
				Servlet serv = new Servlet();
				serv.redirect(request, response, site);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NONE;
	}
	

	public String editTreatmentRoom(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String site = "branchM-" + getBranch_code();
		
		TreatmentRoomData trData = new TreatmentRoomData();
		int rec = trData.editTreatmentRoom(getRoom_id(), getRoom_name(), getBranch_code());
		if(rec>0){
			try {
				new Servlet().redirect(request, response, site);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return NONE;
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



	public BranchModel getBranchModel() {
		return branchModel;
	}



	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	
}
