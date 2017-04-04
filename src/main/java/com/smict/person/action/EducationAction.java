package com.smict.person.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.EducationData;
import com.smict.person.model.PatientModel;

import ldc.util.Auth;

public class EducationAction extends ActionSupport {
	PatientModel patModel;
	
	/**
	 * CONSTRUCTOR
	 */
	public EducationAction(){
		Auth.authCheck(false);
	}
	
	public String education_begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		EducationData eduData = new EducationData();
		List EduList = eduData.select_education("", "", "");
		request.setAttribute("EduList", EduList);
		
		return SUCCESS;
	}
	public String education_vocabulary_begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		EducationData eduData = new EducationData();
		List EduList = eduData.select_education_vocabulary("", "", "");
		request.setAttribute("EduVoList", EduList);
		
		return SUCCESS;
	}
	
	public String education_vocabulary_excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		EducationData eduData = new EducationData();
		String saveEdu 	= 	request.getParameter("saveEdu");
		String updatEdu 	=	request.getParameter("updatEdu");
		String deletEdu 	=	request.getParameter("deletEdu");
		
		if(saveEdu!=null){
			eduData.add_Education_vocabulary(patModel);
		}
		if(updatEdu!=null){
			String education_vocabulary_id = request.getParameter("EduIDUp") ,
					education_vocabulary_th= request.getParameter("EduTHUp") ,
					education_vocabulary_en= request.getParameter("EduENUp") ;
			eduData.UpdateEducation_vocabulary(education_vocabulary_id, education_vocabulary_th, education_vocabulary_en);
		
		}
		if(deletEdu!=null){
			String education_vocabulary_id = request.getParameter("EduIDdel");
			Boolean delStatus = eduData.DeleteEducation_vocabulary(education_vocabulary_id);
			if(delStatus){
				request.setAttribute("del_status", "Deleted !");
			}else { request.setAttribute("del_status", "Deleted Not Success!"); }
			
		}
		List EduList = eduData.select_education_vocabulary("", "", "");
		request.setAttribute("EduVoList", EduList);
	return SUCCESS;
	}
	public String education_excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		EducationData eduData = new EducationData();
		String saveEdu 	= 	request.getParameter("saveEdu");
		String updatEdu 	=	request.getParameter("updatEdu");
		String deletEdu 	=	request.getParameter("deletEdu");
		
		if(saveEdu!=null){
			eduData.add_Education(patModel);
		}
		if(updatEdu!=null){
			String education_id = request.getParameter("EduIDUp") ,
					education_th= request.getParameter("EduTHUp") ,
					education_en= request.getParameter("EduENUp") ;
			eduData.UpdateEducation(education_id, education_th, education_en);
		
		}
		if(deletEdu!=null){
			String education_id = request.getParameter("EduIDdel");
			Boolean delStatus = eduData.DeleteEducation(education_id);
			if(delStatus){
				request.setAttribute("del_status", "Deleted !");
			}else { request.setAttribute("del_status", "Deleted Not Success!"); }
			
		}
		List EduList = eduData.select_education("", "", "");
		request.setAttribute("EduList", EduList);
	return SUCCESS;
	}
	public PatientModel getPatModel() {
		return patModel;
	}
	public void setPatModel(PatientModel patModel) {
		this.patModel = patModel;
	}
}
