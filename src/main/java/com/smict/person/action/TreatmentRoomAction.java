package com.smict.person.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.TreatmentRoomData;
import com.smict.person.model.BranchModel;
import com.smict.person.model.TreatmentRoomModel;
import com.sun.jersey.api.core.HttpRequestContext;

@SuppressWarnings("serial")
public class TreatmentRoomAction extends ActionSupport {

	private TreatmentRoomModel treatRoomModel;
	private BranchModel branchModel;
	private int brand_id;
	private String doctor_name;
	private String brand_name, branch_id, branch_code, branch_name;
	
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
		session.setAttribute("branchModel", branchModel);
		
		
		
		return SUCCESS;
	}
	
	public void validateAddNewRoom(){
		System.out.println("kdjfkd");
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
	
}