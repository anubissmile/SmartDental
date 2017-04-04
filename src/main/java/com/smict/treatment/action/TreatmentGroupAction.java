package com.smict.treatment.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.TreatmentMasterModel;
import com.smict.product.data.LabModeDB;
import com.smict.treatment.data.TreatmentGroupData;

import ldc.util.Auth;

@SuppressWarnings("serial")
public class TreatmentGroupAction extends ActionSupport{
	TreatmentMasterModel teatmentModel;

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
		HttpServletRequest request = ServletActionContext.getRequest();
		
		LabModeDB labModeDB = new LabModeDB();
		List labmodelist = labModeDB.Get_LabModeList("", "");
		request.setAttribute("labmodelist", labmodelist);
		 
		TreatmentGroupData tGroupData = new TreatmentGroupData();
		List<TreatmentMasterModel> treatmentGList = tGroupData.Select_treatment_group("", "","");
		request.setAttribute("treatmentGList", treatmentGList); 
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		TreatmentGroupData tGroupData = new TreatmentGroupData();
		String save 	= 	request.getParameter("save");
		String updateb 	=	request.getParameter("updateb");
		String deleteb 	=	request.getParameter("deleteb");
		
		if(save!=null){ 
			
			int row = tGroupData.AddTreatmentGroup(teatmentModel);
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
		
		LabModeDB labModeDB = new LabModeDB();
		List labmodelist = labModeDB.Get_LabModeList("", "");
		request.setAttribute("labmodelist", labmodelist);
		
		List<TreatmentMasterModel> treatmentGList = tGroupData.Select_treatment_group("", "","");
		request.setAttribute("treatmentGList", treatmentGList); 
		
		return SUCCESS;
	}
}