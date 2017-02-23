package com.smict.person.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.BranchData;
import com.smict.person.data.TreatmentRoomData;
import com.smict.person.model.BranchModel;
import com.smict.person.model.TreatmentRoomModel;

import ldc.util.Thailand; 

@SuppressWarnings("serial")
public class BranchAction extends ActionSupport{
	
	BranchModel branchModel;
	private List<TreatmentRoomModel> treatRoomList = new ArrayList<TreatmentRoomModel>();
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		BranchData branchData = new BranchData();
		/**
		 * ACTIVE BRANCH
		 */
		List<BranchModel> branchActive = branchData.select_branch("", "", "", "", 1);
		request.setAttribute("branchActive", branchActive);
		
		/**
		 * INACTIVE BRANCH
		 */
		List<BranchModel> branchInactive = branchData.select_branch("", "", "", "", 0);
		request.setAttribute("branchInactive", branchInactive);

		return SUCCESS;
	}

	/**
	 * Parameter
	 */
	private String location = "none";
	private String brand_id = null , brand_name = null;
	private String branch_id = null, branch_name = null, branch_code = null;
	private String doctor_name = null;
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();  
		HttpSession session = request.getSession(false);
		BranchData branchData = new BranchData(); 
		
		String modeAction = request.getParameter("modeAction");
		String branch_code = request.getParameter("branchCode");
		
		if(modeAction.equals("delete")){
			/**
			 * DELETION
			 */
			branchDeletion(branch_code);
		}else if(modeAction.equals("add")){
			/**
			 * ADDITION
			 */
		}else{
			/**
			 * DISPLAY
			 */
		}
		
		if(location.equals("view")){
			branchModel = (BranchModel) session.getAttribute("branchModel");
		}
		
		String alertMessage = null;
		String save 	= 	request.getParameter("save"); 
		String search 	= 	request.getParameter("konha");
		String chkDetail	=	request.getParameter("chkDetail");
		String chkDelete	=	request.getParameter("chkDelete");

		if(save!=null){ 
			branchModel.setNext_number(1);
			branchData.add_branch(branchModel);
			
			alertMessage = "การแก้ไขสำเร็จแล้ว";
		} 
		
		if(branchModel.getS_brand_name()!=null&&!branchModel.getS_brand_name().equals("")
				||branchModel.getS_branch_id()!=null&&!branchModel.getS_branch_id().equals("")
				||branchModel.getS_branch_name()!=null&&!branchModel.getS_branch_name().equals("")
				||branchModel.getS_docter_name()!=null&&!branchModel.getS_docter_name().equals("")){ 
			
			brand_name 	= branchModel.getS_brand_name();
			branch_id 	= branchModel.getS_branch_id();
			branch_name 	= branchModel.getS_branch_name();
			doctor_name 	= branchModel.getS_docter_name();
			
			List branchlist = branchData.select_branch(brand_name, branch_id, branch_name, doctor_name, 1);
			request.setAttribute("branchlist", branchlist);
		}else{
			List branchlist = branchData.select_branch("", "", "", "", 1);
			request.setAttribute("branchlist", branchlist);
		}
		
		if(chkDetail.equals("detail")){
			int brand_id 		= branchModel.getBrand_id();
			String branch_id 	= branchModel.getBranch_id();
			
			List branch_detail = branchData.set_branchdetail(brand_id, branch_id);
			
			if (branch_detail.size() == 1) {
				branchModel = (BranchModel) branch_detail.get(0);  
				
				Thailand thailand = new Thailand(); 
				request.setAttribute("addr_provincename", thailand.Get_Provinceid(branchModel.getAddr_provinceid()));
				request.setAttribute("addr_aumphurname", thailand.Get_Amphures(branchModel.getAddr_aumphurid())); 
				request.setAttribute("addr_districtname", thailand.Get_District(branchModel.getAddr_districtid()));
				
				/**
				 * GET ROOM LIST.
				 */
				TreatmentRoomData treatRoomData = new TreatmentRoomData();
				treatRoomList = treatRoomData.findRoomByBranchCode(branchModel.getBranch_code());

				String forward = "detail";
				return forward;
			}else{
				alertMessage = "ไม่พบรายการ";
			}
		} 
		if(chkDelete.equals("delete")){
			int brand_id 		= branchModel.getBrand_id();
			String branch_id 	= branchModel.getBranch_id();
			
			boolean chkStatus = false;
			chkStatus = branchData.DeleteBranch(brand_id, branch_id);
			
			if(chkStatus==true){
				alertMessage = "ลบรายการเรียบร้อยแล้ว";
				
			}
		} 
		
		request.setAttribute("alertMessage", alertMessage);
		return SUCCESS;
	}
	
	private boolean branchDeletion(String branch_code){
		BranchData branchData = new BranchData();
		branchData.deleteBranch(branch_code);
		return false;
	}
	
	public String detail() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		BranchData branchData = new BranchData();
		
		String hdbrand_id 	= request.getParameter("hdbrand_id");
		String hdbranch_id 	= request.getParameter("hdbranch_id");
		
		if((!hdbranch_id.isEmpty() || !hdbranch_id.equals(null)) && (!hdbrand_id.isEmpty() || !hdbrand_id.equals(null))){
			branchData.update_branch(branchModel, hdbrand_id, hdbranch_id);
		}
		
		request.setAttribute("alertMessage", "การแก้ไขสำเร็จเรียบร้อยแล้ว");
		
		List branch_detail = branchData.set_branchdetail(branchModel.getBrand_id(), branchModel.getBranch_id());
		 
			BranchModel branchInfo = (BranchModel) branch_detail.get(0);  
			
			request.setAttribute("brand_id", branchInfo.getBrand_id());
			request.setAttribute("branch_id", branchInfo.getBranch_id().toUpperCase());
			request.setAttribute("branch_name", branchInfo.getBranch_name());
			request.setAttribute("doctor_id", branchInfo.getDoctor_id());
			request.setAttribute("price_doctor", branchInfo.getPrice_doctor());
			
			request.setAttribute("addr_no", branchInfo.getAddr_no());
			request.setAttribute("addr_bloc", branchInfo.getAddr_bloc());
			request.setAttribute("addr_village", branchInfo.getAddr_village());
			request.setAttribute("addr_alley", branchInfo.getAddr_alley());
			request.setAttribute("addr_road", branchInfo.getAddr_road()); 
			request.setAttribute("addr_zipcode", branchInfo.getAddr_zipcode()); 
			
			request.setAttribute("tel_id", branchInfo.getTel_id());
			request.setAttribute("tels_id", branchInfo.getTels_id());
			
			Thailand thailand = new Thailand(); 
			request.setAttribute("addr_provincename", thailand.Get_Provinceid(branchInfo.getAddr_provinceid()));
			request.setAttribute("addr_aumphurname", thailand.Get_Amphures(branchInfo.getAddr_aumphurid()));
			request.setAttribute("addr_districtname", thailand.Get_District(branchInfo.getAddr_districtid())); 
		
		return SUCCESS;
	}
	public List<TreatmentRoomModel> getTreatRoomList() {
		return treatRoomList;
	}
	public void setTreatRoomList(List<TreatmentRoomModel> treatRoomList) {
		this.treatRoomList = treatRoomList;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
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

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public BranchModel getBranchModel() {
		return branchModel;
	}

	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}
	
}
