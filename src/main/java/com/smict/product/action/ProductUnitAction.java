package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport; 
import com.smict.product.data.ProductUnitDB; 
import com.smict.product.model.ProductUnitModel;

import ldc.util.Auth;
 
public class ProductUnitAction extends ActionSupport{
	
	ProductUnitModel productUnitModel;  
	
	/**
	 * CONSTRUCTOR
	 */
	public ProductUnitAction(){
		Auth.authCheck(false);
	}
	
	public ProductUnitModel getProductUnitModel() {
		return productUnitModel;
	}
	public void setProductUnitModel(ProductUnitModel productUnitModel) {
		this.productUnitModel = productUnitModel;
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		ProductUnitDB unitdb = new ProductUnitDB();
		List unitlist = unitdb.Get_UnitList("", "");
		request.setAttribute("unitlist", unitlist);
		
		return SUCCESS;
	}
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		ProductUnitDB unitdb = new ProductUnitDB();
		  
		String save 	= 	request.getParameter("save");
		String updateb 	=	request.getParameter("updateb");
		String deleteb 	=	request.getParameter("deleteb");
		 
		if(save!=null){
			String productunit_id 		= productUnitModel.getId();
			String productunit_name 	= productUnitModel.getName();
			String create_by			= "smartict";
			
			unitdb.AddUnit(productunit_id, productunit_name, create_by);
		}
		
		if(updateb!=null){
			String productunit_id 		= request.getParameter("id_up"); 
			String productunit_name 	= request.getParameter("name_up"); 
			String update_by			= "smartict";
			
			unitdb.UpdateUnit(productunit_id, productunit_name, update_by);
		}
		
		if(deleteb!=null){
			String productunit_id 	= request.getParameter("id_de"); 
			
			unitdb.DeleteUnit(productunit_id);
		} 
		
		
		List unitlist = unitdb.Get_UnitList("", "");
		request.setAttribute("unitlist", unitlist);
		
		return SUCCESS;
	} 
	
}
