package com.smict.person.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.BrandData;
import com.smict.person.model.BrandModel;  



public class BrandAction extends ActionSupport{
	
	BrandModel brandModel;  
	public BrandModel getBrandModel() {
		return brandModel;
	}
	public void setBrandModel(BrandModel brandModel) {
		this.brandModel = brandModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		BrandData branddb = new BrandData();
		List brandlist = branddb.select_brand(0, "");
		request.setAttribute("brandlist", brandlist);
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		BrandData branddb = new BrandData(); 
		  
		String save 	= 	request.getParameter("save");
		String updateb 	=	request.getParameter("updateb");
		String deleteb 	=	request.getParameter("deleteb");
		 
		if(save!=null){ 
			
			branddb.add_brand(brandModel);
		}
		
		if(updateb!=null){
			String brand_id 		= request.getParameter("id_up"); 
			String brand_name 		= request.getParameter("name_up"); 
			String update_by			= "smartict";
			
			branddb.UpdateBrand(Integer.parseInt(brand_id), brand_name);
		}
		
		if(deleteb!=null){
			String brand_id 	= request.getParameter("id_de"); 
			
			branddb.DeleteBrand(Integer.parseInt(brand_id));
		} 
		
		
		List brandlist = branddb.select_brand(0, "");
		request.setAttribute("brandlist", brandlist);
		
		return SUCCESS;
	} 
	
}
