package com.smict.df;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import ldc.util.DBConnect;

public class AJAX_JSON {
		
	public void ajax_json() {
	
		HttpServletRequest request = ServletActionContext.getRequest();	
		DFDB dfDB = new DFDB();
		
		String doctor_id = "";  
		String isData = "";
		if(request.getParameter("isData") != null) isData = request.getParameter("isData").toString();
				 
		JSONObject jOBJ = new JSONObject();
		JSONArray jsonResponse = new JSONArray();
		if(isData.equals("d")){ 
			doctor_id = request.getParameter("q").toString();
			jsonResponse = dfDB.getJsonArrayListDortor(doctor_id);
		}else if(isData.equals("b")){
			doctor_id = request.getParameter("doctor_id").toString();
			jsonResponse = dfDB.getJsonArrayListBranch(doctor_id);
		} 
		  
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
		try {
			jOBJ.put("more", true);
			jOBJ.put("results", jsonResponse);
			response.getWriter().write(jOBJ.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
public void ajax_json_treatment() {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		DFDB dfDB = new DFDB();
		JSONArray jsonResponse = new JSONArray();
		
		if(request.getParameter("type").equals("main")){
			String doctor_id = "", branch_id = "";   
			if(request.getParameter("doctor_id") != null) doctor_id = request.getParameter("doctor_id").toString();
			if(request.getParameter("branch_id") != null) branch_id = request.getParameter("branch_id").toString(); 
			  
			jsonResponse = dfDB.getTreatmentList(doctor_id,branch_id); 
		}else if (request.getParameter("type").equals("cat")){
			String treatment_code = "";
			if(request.getParameter("treatment_code") != null) treatment_code = request.getParameter("treatment_code").toString();
			jsonResponse = dfDB.getCategoryList(treatment_code);  
			
		}else if (request.getParameter("type").equals("group")){
			String group_id = "";
			if(request.getParameter("group_id") != null) group_id = request.getParameter("group_id").toString();
			jsonResponse = dfDB.getGroupList(group_id);  
		}
		  
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
		try { 
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
public void ajax_json_groupcheck() {
	
	HttpServletRequest request = ServletActionContext.getRequest();	
	DFDB dfDB = new DFDB();
	JSONArray jsonResponse = new JSONArray();
	
		String group_id = "";  
		if(request.getParameter("group_id") != null) group_id = request.getParameter("group_id").toString();
		  
		jsonResponse = dfDB.getCategoryListcheck(group_id); 	
	  
	HttpServletResponse response = ServletActionContext.getResponse();
	 
	response.setCharacterEncoding("UTF-8");
	response.setContentType("application/json"); 
	response.setHeader("cache-control", "no-cache");
	try { 
		response.getWriter().write(jsonResponse.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
}
public void ajax_json_bodyscopegroupcheck() {
	
	HttpServletRequest request = ServletActionContext.getRequest();	
	DFDB dfDB = new DFDB();
	JSONArray jsonResponse = new JSONArray();
	
		String group_id = "";  
		if(request.getParameter("group_id") != null) group_id = request.getParameter("group_id").toString();
		  
		jsonResponse = dfDB.gettreatmentListcheckbyGroup(group_id); 	
	  
	HttpServletResponse response = ServletActionContext.getResponse();
	 
	response.setCharacterEncoding("UTF-8");
	response.setContentType("application/json"); 
	response.setHeader("cache-control", "no-cache");
	try { 
		response.getWriter().write(jsonResponse.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
}
	public void ajax_json_contact(){
		HttpServletRequest request = ServletActionContext.getRequest();	
		JSONArray jsonResponse = new JSONArray();
		DFDB dfDB = new DFDB();
			String contact_id = "";  
			if(request.getParameter("contactid") != null) contact_id = request.getParameter("contactid").toString();			  	
			jsonResponse = dfDB.getcontactlist(contact_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
		try { 
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
	}
	public void ajax_json_daycheck() {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		DFDB dfDB = new DFDB();
		JSONArray jsonResponse = new JSONArray();
		
			String pro_id = "";  
			if(request.getParameter("pro_id") != null) pro_id = request.getParameter("pro_id").toString();
			  
			jsonResponse = dfDB.getPromotionday(pro_id); 	
		  
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
		try { 
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public void ajax_json_contactPoints() {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		DFDB dfDB = new DFDB();
		JSONArray jsonResponse = new JSONArray();
		
			String promotionID = "";  
			if(request.getParameter("promotionID") != null) promotionID = request.getParameter("promotionID").toString();
			  
			jsonResponse = dfDB.getPromotionContact(promotionID); 	
		  
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
		try { 
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public void ajax_json_contactPointsLine() {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		DFDB dfDB = new DFDB();
		JSONArray jsonResponse = new JSONArray();
		
			String promotionID = "";  
			if(request.getParameter("promotionID") != null) promotionID = request.getParameter("promotionID").toString();
			  
			jsonResponse = dfDB.getPromotionContactLine(promotionID); 	
		  
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
		try { 
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
