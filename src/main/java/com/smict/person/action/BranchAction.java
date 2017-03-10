package com.smict.person.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.StringUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.AddressData;
import com.smict.person.data.BranchData;
import com.smict.person.data.BrandData;
import com.smict.person.data.DoctorData;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.AddressModel;
import com.smict.person.model.BranchModel;
import com.smict.person.model.BrandModel;
import com.smict.person.model.DoctorModel;
import com.smict.person.model.TelephoneModel;
import com.smict.person.model.TreatmentRoomModel;

import freemarker.template.utility.StringUtil;
import ldc.util.Thailand; 

@SuppressWarnings("serial")
public class BranchAction extends ActionSupport{
	
	BranchModel branchModel;
	DoctorModel doctorModel;
	
	private List<TreatmentRoomModel> treatRoomList = new ArrayList<TreatmentRoomModel>();
	private List<DoctorModel> doctorList = new ArrayList<DoctorModel>();
	private HashMap<String, String> doctorMap = new HashMap<String, String>();
	private List<BrandModel> brandList = new ArrayList<BrandModel>();
	private HashMap<String, String> brandMap = new HashMap<String, String>();

	/**
	 * DATA CLASS
	 */
	BranchData branchData = new BranchData();
	TelephoneData teleData = new TelephoneData();
	AddressData addrData = new AddressData();
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		BranchData branchData = new BranchData();
		DoctorData doctorData = new DoctorData();
		BrandData brandData = new BrandData();
		
		/**
		 * ACTIVE BRANCH
		 */
		List<BranchModel> branchActive = branchData.getBranch("1");
		request.setAttribute("branchActive", branchActive);
		
		/**
		 * INACTIVE BRANCH
		 */
		List<BranchModel> branchInactive = branchData.getBranch("0");
		request.setAttribute("branchInactive", branchInactive);
		
		/**
		 * FETCH DOCTOR LIST.
		 */
		doctorList = doctorData.Get_DoctorList(null);
		for(DoctorModel dm : doctorList){
			doctorMap.put(Integer.valueOf(dm.getDoctorID()).toString(), dm.getFirstname_th() + " " + dm.getLastname_th());
		}
		
		/**
		 * FETCH BRAND LIST.
		 */
		brandList = brandData.chunkBrand();
		for(BrandModel bm : brandList){
			brandMap.put(Integer.valueOf(bm.getBrand_id()).toString(), bm.getBrand_name());
		}

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
		
		String modeAction = request.getParameter("modeAction");
		String branch_code = request.getParameter("branchCode");
		String activeType = request.getParameter("activeType");
		String alertMessage = null;
		int rec = 0;
		
		if(modeAction.equals("delete")){
			/**
			 * SWOP ACTIVE BRANCH
			 */
			rec = branchData.swopActiveBranch(branch_code, activeType);
			alertMessage = (rec > 0) ? "ดำเนินการเรียบร้อย" : "ผิดพลาด! ไม่พบรายการ";
		}else if(modeAction.equals("add")){
			/**
			 * ADDITIOIN
			 */
			System.out.println(modeAction.toString());
			rec = addNewBranch(branchModel);
			alertMessage = (rec > 0) ? "ดำเนินการเรียบร้อย" : "การเพิ่มสาขาผิดพลาด";
		}else{
			/**
			 * DISPLAY
			 */
			if(!getBranch_code().isEmpty()){
				branchModel = branchData.getBranchByID(getBranch_code());
				return "detail";
			}else{
				request.setAttribute("alertMessage", "ไม่พบรายการ");
				return "detail";
			}
		}
		
		request.setAttribute("alertMessage", alertMessage);
		return SUCCESS;
	}

	private int addNewBranch(BranchModel bModel) {
		/**
		 * ADD PHONE NUMBER.
		 */
		int tel_id = teleData.Gethight_telID();
		++tel_id;
		TelephoneModel telModel = new TelephoneModel(tel_id, bModel.getTel_id(), 4);
		TelephoneModel telsModel = new TelephoneModel(tel_id, bModel.getTels_id(), 1);
		teleData.add_telephone(telModel);
		teleData.add_telephone(telsModel);
		
		/**
		 * ADD ADDRESS
		 */
		int addr_id = addrData.getHighestID();
		AddressModel addrModel = new AddressModel();
		addrModel.setAddr_no(bModel.getAddr_no());
		addrModel.setAddr_bloc(bModel.getAddr_bloc());
		addrModel.setAddr_village(bModel.getAddr_village());
		addrModel.setAddr_alley(bModel.getAddr_alley());
		addrModel.setAddr_road(bModel.getAddr_road());
		addrModel.setAddr_provinceid(bModel.getAddr_provinceid());
		addrModel.setAddr_aumphurid(bModel.getAddr_aumphurid());
		addrModel.setAddr_districtid(bModel.getAddr_districtid());
		addrModel.setAddr_zipcode(bModel.getAddr_zipcode());
		addrModel.setNew_addr_id(++addr_id);
		addrData.addNewAddress(addrModel);
		
		/**
		 * ADD BRANCH.
		 */
		branchModel.setAddr_id(String.valueOf(addr_id));
		branchModel.setTel_id(String.valueOf(tel_id));
		branchModel.setTels_id(String.valueOf(tel_id));
		return branchData.addNewBranch(branchModel);
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

	public List<DoctorModel> getDoctorList() {
		return doctorList;
	}

	public void setDoctorList(List<DoctorModel> doctorList) {
		this.doctorList = doctorList;
	}

	public HashMap<String, String> getDoctorMap() {
		return doctorMap;
	}

	public void setDoctorMap(HashMap<String, String> doctorMap) {
		this.doctorMap = doctorMap;
	}

	public DoctorModel getDoctorModel() {
		return doctorModel;
	}

	public void setDoctorModel(DoctorModel doctorModel) {
		this.doctorModel = doctorModel;
	}

	public List<BrandModel> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<BrandModel> brandList) {
		this.brandList = brandList;
	}

	public HashMap<String, String> getBrandMap() {
		return brandMap;
	}

	public void setBrandMap(HashMap<String, String> brandMap) {
		this.brandMap = brandMap;
	}
	
}
