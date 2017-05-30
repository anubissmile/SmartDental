package com.smict.treatment.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.TreatmentMasterModel;
import com.smict.product.data.LabModeDB;
import com.smict.product.model.LabModeModel;
import com.smict.treatment.data.TreatmentGroupData;

import ldc.util.Auth;

@SuppressWarnings("serial")
public class TreatmentGroupAction extends ActionSupport{
	TreatmentMasterModel teatmentModel;
	List<TreatmentMasterModel> categoryList;
	Map<String,String> treatGroupMap;
	/**
	 * CONSTRUCTOR
	 */
	public TreatmentGroupAction(){
		Auth.authCheck(false);
	}
	
	public TreatmentMasterModel getTeatmentModel() {
		return teatmentModel;
	}

	public void setTeatmentModel(TreatmentMasterModel teatmentModel) {
		this.teatmentModel = teatmentModel;
	}
	
	public String begin() throws Exception{

		
		 
		TreatmentGroupData tGroupData = new TreatmentGroupData();
		setCategoryList(tGroupData.gettreatmentCategorylist());
		setTreatGroupMap(tGroupData.Get_treatment_groupMap());
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		TreatmentGroupData tGroupData = new TreatmentGroupData();
		String save 	= 	request.getParameter("save");
		String updateb 	=	request.getParameter("updateb");
		String deleteb 	=	request.getParameter("deleteb");
		
		if(save!=null){ 
			
			int row = tGroupData.AddtreatmentCategory(teatmentModel);
			if(row>0){
				request.setAttribute("status_error", "");
				request.setAttribute("status_success", "เพิ่มข้อมูลเรียบร้อย !");
			}else{
				request.setAttribute("status_error", "เพิ่มข้อมูลไม่สำเร็จ");
				request.setAttribute("status_success", "");
			}
		}
		
		if(updateb!=null){
			String id_up 		= request.getParameter("id_up"); 
			String name_up 		= request.getParameter("name_up"); 
			String type_up 		= request.getParameter("type_up"); 
			
			teatmentModel.setTreatment_group_code(id_up);
			teatmentModel.setTreatment_group_name(name_up);
			teatmentModel.setLabmode_id(type_up);
			
			int row = tGroupData.UpdateTreatmentGroup(teatmentModel);
			if(row>0){
				request.setAttribute("status_error", "");
				request.setAttribute("status_success", "อัทเดทเรียบร้อยแล้ว !");
			}else{
				request.setAttribute("status_error", "อัทเดทข้อมูลไม่สำเร็จ");
				request.setAttribute("status_success", "");
			}
		}
		
		if(deleteb!=null){
			String id_de 	= request.getParameter("id_de"); 
			
			if(tGroupData.DeleteTreatmentGroup(id_de)){
				request.setAttribute("status_error", "");
				request.setAttribute("status_success", "ลบเรียบร้อยแล้ว !");
			}else{
				request.setAttribute("status_error", "ลบข้อมูลไม่สำเร็จ");
				request.setAttribute("status_success", "");
			}
		}
		

		setCategoryList(tGroupData.gettreatmentCategorylist());
		setTreatGroupMap(tGroupData.Get_treatment_groupMap());

		return SUCCESS;
	}


	public List<TreatmentMasterModel> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<TreatmentMasterModel> categoryList) {
		this.categoryList = categoryList;
	}

	public Map<String, String> getTreatGroupMap() {
		return treatGroupMap;
	}

	public void setTreatGroupMap(Map<String, String> treatGroupMap) {
		this.treatGroupMap = treatGroupMap;
	}
}