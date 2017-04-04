package com.smict.person.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.CongenitalData;
import com.smict.person.model.CongenitalDiseaseModel;
import com.smict.product.data.ProductData;

public class CongenitalAction extends ActionSupport {

	private List<CongenitalDiseaseModel> CrModel;
	private CongenitalDiseaseModel abcModel;
	
	
	
	
	
	public String addCongenital() throws IOException, Exception{
		
		CongenitalData proData = new CongenitalData();
		  proData.addcon(abcModel);
		  
		  CongenitalData con = new CongenitalData();
			setCrModel(con.getListCongenital());
			
		  return SUCCESS;
		  
		 }
	

	public String getCongenitalList(){
		CongenitalData con = new CongenitalData();
		setCrModel(con.getListCongenital());
		return NONE;
			  
	}

	

	public String CongenitalDel() throws IOException, Exception{

		  CongenitalData proData = new CongenitalData();
		  proData.CongenitalDelete(abcModel);

		  CongenitalData con = new CongenitalData();
		  setCrModel(con.getListCongenital());

		  return SUCCESS;
		 }
	
	
	
	public String addCongenitalUpdate() throws IOException, Exception{
		CongenitalData proData = new CongenitalData();
		  proData.addconupdate(abcModel);
		  
		  CongenitalData con = new CongenitalData();
		  setCrModel(con.getListCongenital());
		  return SUCCESS;
	}
	


	public List<CongenitalDiseaseModel> getCrModel() {
		return CrModel;
	}


	public void setCrModel(List<CongenitalDiseaseModel> crModel) {
		CrModel = crModel;
	}



	public CongenitalDiseaseModel getAbcModel() {
		return abcModel;
	}


	public void setAbcModel(CongenitalDiseaseModel abcModel) {
		this.abcModel = abcModel;
	}
	
	
	


	
	
}
