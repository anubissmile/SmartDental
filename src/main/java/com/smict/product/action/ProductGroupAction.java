package com.smict.product.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport; 
import com.smict.product.data.ProductgroupDB;
import com.smict.product.model.ProductgroupModel;

import ldc.util.Auth;



public class ProductGroupAction extends ActionSupport{
	
	ProductgroupModel productgroupModel;  
	
	/**
	 * CONSTRUCTOR
	 */
	public ProductGroupAction(){
		Auth.authCheck(false);
	}
	
	public ProductgroupModel getProductgroupModel() {
		return productgroupModel;
	}
	public void setProductgroupModel(ProductgroupModel productgroupModel) {
		this.productgroupModel = productgroupModel;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		ProductgroupDB groupdb = new ProductgroupDB();
		List grouplist = groupdb.Get_groupList("", "");
		request.setAttribute("grouplist", grouplist);
		
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		ProductgroupDB groupdb = new ProductgroupDB();
		  
		String save 	= 	request.getParameter("save");
		String updateg 	=	request.getParameter("updateg");
		String deleteg 	=	request.getParameter("deleteg");
		 
		if(save!=null){
			String productgroup_id 		= productgroupModel.getId();
			String productgroup_name 	= productgroupModel.getName();
			String create_by			= "smartict";
			
			groupdb.Addgroup(productgroup_id, productgroup_name, create_by);
		}
		
		if(updateg!=null){
			String productgroup_id 		= request.getParameter("id_up"); 
			String productgroup_name 	= request.getParameter("name_up"); 
			String update_by			= "smartict";
			
			groupdb.Updategroup(productgroup_id, productgroup_name, update_by);
		}
		
		if(deleteg!=null){
			String productgroup_id 	= request.getParameter("id_de"); 
			
			groupdb.Deletegroup(productgroup_id);
		} 
		
		
		List grouplist = groupdb.Get_groupList("", "");
		request.setAttribute("grouplist", grouplist);
		
		return SUCCESS;
	} 
	
}
