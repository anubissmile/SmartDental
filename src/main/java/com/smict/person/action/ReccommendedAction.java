package com.smict.person.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.PatientRecommendedData;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.RecommendedModel;

public class ReccommendedAction extends ActionSupport {	
	RecommendedModel recommendedModel;
	
	public RecommendedModel getRecommendedModel() {
		return recommendedModel;
	}
	public void setRecommendedModel(RecommendedModel recommendedModel) {
		this.recommendedModel = recommendedModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		PatientRecommendedData recData = new PatientRecommendedData();
		recommendedModel = new RecommendedModel();
		List recommendedList = recData.select_Recommended(recommendedModel);
		request.setAttribute("recommendedList", recommendedList);
		
		return SUCCESS;
	}
	public String excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		PatientRecommendedData recData = new PatientRecommendedData();
		String saveBTN 	= 	request.getParameter("saveType");
		String updatBTN 	=	request.getParameter("upType");
		String deletBTN 	=	request.getParameter("delType");
		if(saveBTN!=null){ 
			recData.add_Recommended(recommendedModel);
			request.setAttribute("del_error", "");
			request.setAttribute("del_success", "Add Success!");
		}
		if(updatBTN!=null){
			recommendedModel.setTyperecommended(Integer.parseInt(request.getParameter("id_up")));
			recommendedModel.setTyperecommended_name(request.getParameter("name_up"));
			recData.UpdateRecommended(recommendedModel);
			request.setAttribute("del_error", "");
			request.setAttribute("del_success", "Update Success!");
		}
		if(deletBTN!=null){
			int typerecommended = Integer.parseInt(request.getParameter("id_de"));
			if(!recData.CheckRecommended(typerecommended)){
				Boolean delStatus = recData.DeleteRecommended(typerecommended);
				if(delStatus){
					request.setAttribute("del_error", "");
					request.setAttribute("del_success", "Deleted !");
				}else { 
					request.setAttribute("del_success", "");
					request.setAttribute("del_error", "Deleted Not Success!"); }
			}else{
				request.setAttribute("del_success", "");
				request.setAttribute("del_error", "Delete error TelType in use!");}
		}
		recommendedModel = new RecommendedModel();
		List recommendedList = recData.select_Recommended(recommendedModel);
		request.setAttribute("recommendedList", recommendedList);
	return SUCCESS;
	}
}
