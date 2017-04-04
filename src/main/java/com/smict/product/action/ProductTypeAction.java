package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.product.data.ProductBrandDB;
import com.smict.product.data.ProducttypeDB;
import com.smict.product.model.ProductBrandModel;
import com.smict.product.model.ProducttypeModel;

import ldc.util.Auth;



public class ProductTypeAction extends ActionSupport{
	
	ProducttypeModel ProductTypeModel; 
	
	/**
	 * CONSTRUCTOR
	 */
	public ProductTypeAction(){
		Auth.authCheck(false);
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		ProducttypeDB typedb = new ProducttypeDB();
		List typelist = typedb.Get_typeList("", "");
		request.setAttribute("typelist", typelist);
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		ProducttypeDB branddb = new ProducttypeDB(); 
		  
		String save 	= 	request.getParameter("save");
		String updateb 	=	request.getParameter("updatet");
		String deleteb 	=	request.getParameter("deletet");
		 
		if(save!=null){
			String productbrand_id 		= ProductTypeModel.getId();
			String productbrand_name 	= ProductTypeModel.getName();
			String create_by			= "smartict";
			
			branddb.Addtype(productbrand_id, productbrand_name, create_by); 
		}
		
		if(updateb!=null){
			String productbrand_id 		= request.getParameter("id_up"); 
			String productbrand_name 	= request.getParameter("name_up"); 
			String update_by			= "smartict";
			
			branddb.Updatetype(productbrand_id, productbrand_name, update_by);
		}
		
		if(deleteb!=null){
			String productbrand_id 	= request.getParameter("id_de"); 
			
			branddb.Deletetype(productbrand_id);
		} 
		
		
		List brandlist = branddb.Get_typeList("", "");
		request.setAttribute("typelist", brandlist);
		
		return SUCCESS;
	} 
	
}
