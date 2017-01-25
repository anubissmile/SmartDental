package com.smict.person.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.MarriedStatusData;
import com.smict.person.data.Pre_nameData;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.Person;
import com.smict.person.model.Pre_nameModel;
import com.smict.person.model.TelephoneModel;

public class MarriedStatusAction extends ActionSupport {
	Person person;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		MarriedStatusData marriedData = new MarriedStatusData();
		person = new Person();
		List MarriedList = marriedData.select_MarriedStatus(person);
		request.setAttribute("MarriedList", MarriedList);
		
		return SUCCESS;
	}
	public String excute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		MarriedStatusData marriedData = new MarriedStatusData();
		String saveBTN 	= 	request.getParameter("saveType");
		String updatBTN 	=	request.getParameter("upType");
		String deletBTN 	=	request.getParameter("delType");
		if(saveBTN!=null){ 
			marriedData.add_MarriedStatus(person);
			request.setAttribute("del_error", "");
			request.setAttribute("del_success", "Add Success!");
		} 
		if(updatBTN!=null){
			String married_statusid = request.getParameter("id_up"),
				   married_statusname = request.getParameter("name_up");	
			marriedData.UpdateMarriedStatus(married_statusid, married_statusname);
			request.setAttribute("del_error", "");
			request.setAttribute("del_success", "Update Success!");
		}
		if(deletBTN!=null){
			String married_statusid = request.getParameter("id_de");
			if(!marriedData.Check_MarriedStatus(married_statusid)){
				Boolean delStatus = marriedData.DeleteMarriedStatus(married_statusid);
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
		person = new Person();
		List MarriedList = marriedData.select_MarriedStatus(person);
		request.setAttribute("MarriedList", MarriedList);
	return SUCCESS;
	}


}
