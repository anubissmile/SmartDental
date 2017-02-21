package com.smict.person.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest; 
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
	private List<TreatmentRoomModel> treatRoomList;
	 
	public BranchModel getBranchModel() {
		return branchModel;
	}
	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		BranchData branchData = new BranchData();
		List branchlist = branchData.select_branch("", "", "", "");
		request.setAttribute("branchlist", branchlist);
		
		return SUCCESS;
	}
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();  
		BranchData branchData = new BranchData(); 
		  
		String alertMessage = null;
		
		String save 	= 	request.getParameter("save"); 
		String search 	= 	request.getParameter("konha");
		String chkDetail	=	request.getParameter("chkDetail");
		String chkDelete	=	request.getParameter("chkDelete");
		 
		if(save!=null){ 
			branchModel.setNext_number(1);
			branchData.add_branch(branchModel);
			
			alertMessage = "ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Â¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½";
		} 
		 
		if(branchModel.getS_brand_name()!=null&&!branchModel.getS_brand_name().equals("")
				||branchModel.getS_branch_id()!=null&&!branchModel.getS_branch_id().equals("")
				||branchModel.getS_branch_name()!=null&&!branchModel.getS_branch_name().equals("")
				||branchModel.getS_docter_name()!=null&&!branchModel.getS_docter_name().equals("")){ 
			
			String brand_name 	= branchModel.getS_brand_name();
			String branch_id 	= branchModel.getS_branch_id();
			String branch_name 	= branchModel.getS_branch_name();
			String doctor_name 	= branchModel.getS_docter_name();
			
			List branchlist = branchData.select_branch(brand_name, branch_id, branch_name, doctor_name);
			request.setAttribute("branchlist", branchlist);
		}else{
			List branchlist = branchData.select_branch("", "", "", "");
			request.setAttribute("branchlist", branchlist);
		}
		if(chkDetail.equals("detail")){
			int brand_id 		= branchModel.getBrand_id();
			String branch_id 	= branchModel.getBranch_id();
			
			List branch_detail = branchData.set_branchdetail(brand_id, branch_id);
			
			if (branch_detail.size() == 1) {
				BranchModel branchInfo = (BranchModel) branch_detail.get(0);  
				
				request.setAttribute("brand_id", branchInfo.getBrand_id());
				request.setAttribute("branch_id", branchInfo.getBranch_id());
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
				
				/**
				 * GET ROOM LIST.
				 */
				TreatmentRoomData treatRoomData = new TreatmentRoomData();
				treatRoomList = treatRoomData.findRoomByBranchCode(branchInfo.getBranch_code());

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
				alertMessage = "Åºï¿½ï¿½Â¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½";
				
			}
		} 
		
		request.setAttribute("alertMessage", alertMessage);
		return SUCCESS;
	}
	
	public String detail() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		BranchData branchData = new BranchData();
		 
		String hdbrand_id 	= request.getParameter("hdbrand_id");
		String hdbranch_id 	= request.getParameter("hdbranch_id");
		
		branchData.update_branch(branchModel, hdbrand_id, hdbranch_id);
		
		request.setAttribute("alertMessage", "ï¿½ï¿½ï¿½ï¿½ï¿½Â¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½");
		
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
	
}
