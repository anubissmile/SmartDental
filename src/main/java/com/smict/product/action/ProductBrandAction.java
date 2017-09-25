package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.product.data.ProductBrandDB;
import com.smict.product.model.ProductBrandModel;

import ldc.util.Auth;



public class ProductBrandAction extends ActionSupport{
	
	ProductBrandModel productBrandModel; 
	
	/**
	 * CONSTRUCTOR
	 */
	public ProductBrandAction(){
		Auth.authCheck(false);
	}
	
	public ProductBrandModel getProductBrandModel() {
		return productBrandModel;
	}  
	public void setProductBrandModel(ProductBrandModel productBrandModel) {
		this.productBrandModel = productBrandModel;
	} 
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		ProductBrandDB branddb = new ProductBrandDB();
		List brandlist = branddb.Get_BrandList("", "");
		request.setAttribute("brandlist", brandlist);
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		ProductBrandDB branddb = new ProductBrandDB(); 
		  
		String save 	= 	request.getParameter("save");
		String updateb 	=	request.getParameter("updateb");
		String deleteb 	=	request.getParameter("deleteb");
		 
		if(save!=null){
			String productbrand_id 		= productBrandModel.getId();
			String productbrand_name 	= productBrandModel.getName();
			String create_by			= "smartict";
			
			branddb.AddBrand(productbrand_id, productbrand_name, create_by); 
		}
		
		if(updateb!=null){
			String productbrand_id 		= request.getParameter("id_up"); 
			String productbrand_name 	= request.getParameter("name_up"); 
			String update_by			= "smartict";
			
			branddb.UpdateBrand(productbrand_id, productbrand_name, update_by);
		}
		
		if(deleteb!=null){
			String productbrand_id 	= request.getParameter("id_de"); 
			
			branddb.DeleteBrand(productbrand_id);
		} 
		
		
		List brandlist = branddb.Get_BrandList("", "");
		request.setAttribute("brandlist", brandlist);
		
		return SUCCESS;
	} 
	
}
