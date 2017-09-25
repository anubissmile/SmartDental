package com.smict.treatment.action;

import java.util.List;

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
	List<LabModeModel> treatGlist;
	List<TreatmentMasterModel> categoryList;
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
		LabModeDB labModeDB = new LabModeDB();
		setTreatGlist(labModeDB.Get_treatmentGroup());
		 
		TreatmentGroupData tGroupData = new TreatmentGroupData();
		setCategoryList(tGroupData.gettreatmentCategorylist());

		
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
			String hid 		= request.getParameter("hdid_up");
			teatmentModel.setTreatCategory_id(hid);
			teatmentModel.setTreatCategory_code(id_up);
			teatmentModel.setTreatCategory_name(name_up);
			teatmentModel.setTreatCategory_groupid(type_up);
			
			int row = tGroupData.UpdatetreatmentCategory(teatmentModel);
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
			
			if(tGroupData.DeletetreatmentCategory(id_de)){
				request.setAttribute("status_error", "");
				request.setAttribute("status_success", "ลบเรียบร้อยแล้ว !");
			}else{
				request.setAttribute("status_error", "ลบข้อมูลไม่สำเร็จ");
				request.setAttribute("status_success", "");
			}
		}
		
		LabModeDB labModeDB = new LabModeDB();
		setTreatGlist(labModeDB.Get_treatmentGroup());
		setCategoryList(tGroupData.gettreatmentCategorylist());

		return SUCCESS;
	}

	public List<LabModeModel> getTreatGlist() {
		return treatGlist;
	}

	public void setTreatGlist(List<LabModeModel> treatGlist) {
		this.treatGlist = treatGlist;
	}

	public List<TreatmentMasterModel> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<TreatmentMasterModel> categoryList) {
		this.categoryList = categoryList;
	}
}