<%@ page language="java" import="java.util.*,java.text.DecimalFormat" pageEncoding="utf-8"%>
<%@ page import="ldc.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<%@ page import="com.smict.all.model.*" %>
<% 
	DBConnect dbcon = new DBConnect(); 
	ServicePatientModel servicePatModel;
	servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
	String hn 				= servicePatModel.getHn();

	String treatment_code 		= request.getParameter("treatment_code");
	String doctor_id 			= request.getParameter("doctor_id");
	String price		 		= request.getParameter("price");
	price						= price.replace(".00", "");
	String discount				= request.getParameter("discount"); if(discount.equals("")) discount = "0";
	String total_price			= "0";
	if(!price.equals("")) total_price = price;
	if(!price.equals("")&&!discount.equals("")) total_price = String.valueOf(Integer.parseInt(price)-Integer.parseInt(discount));
	String phase_qty			= "1";
	if(!request.getParameter("phase_qty").equals("")) phase_qty = (String) request.getParameter("phase_qty");
	String phase_amount			= request.getParameter("phase_amount");
	int continue_id				= 1;
	String continue_phase		= request.getParameter("continue_phase");
	int continue_phase_value	= Integer.valueOf(continue_phase)+1;
	String method_type 	= request.getParameter("method_type"); 
	String tcode 		= request.getParameter("tcode");  
	String dcode 		= request.getParameter("dcode"); 
	String drug_qty		= request.getParameter("drug_qty");
	  
	String continue_id_chk	= request.getParameter("continue_id"); 
	
	Connection conn = null;
	Statement Stmt = null;
	ResultSet rs = null; 
	if(method_type.equals("get")){
		conn = dbcon.getConnectMYSql();
		String sql = "";
		
		conn.setAutoCommit(false);
		
		if(continue_id_chk.equals("")){
			
		sql = "SELECT max(continue_id)+1 as continue_id " 
				+"FROM "
				+"treatcontinue_setup a  " 
				+"where a.continue_id != '' ";
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql); 
		while (rs.next()){ 
			if(rs.getInt("continue_id")!=0){
				continue_id = rs.getInt("continue_id");    
			}
		}
		rs.close();
		Stmt.close();
		
		
	// insert treatment continue setup hd
		sql = "INSERT INTO treatcontinue_setup "
				+ "(treatment_code, hn, doctor_id, continue_id, price, discount, total_price, treatcontinue_status_id) "
				+ "VALUES ('"+treatment_code+"', '"+hn+"', "+doctor_id+", "+continue_id+", "+price+", "+discount+", "+total_price+", '3')" ;
		  
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			Stmt.close();
		}else{
			continue_id = Integer.valueOf(continue_id_chk.trim());
		}
		
		
		for(int i=0,j=1; i<Integer.valueOf(phase_qty); i++,j++){
			int continue_count_all = 1; 
			sql = "SELECT max(continue_count_all)+1 as continue_count_all " 
					+"FROM "
					+"treatcontinue_transaction a  " 
					+"where a.continue_id = "+continue_id+" ";
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql); 
			while (rs.next()){  
					continue_count_all = rs.getInt("continue_count_all");  
					if(continue_count_all==0) continue_count_all = 1;
			}
			rs.close();
			Stmt.close();
			
			String[] tcode_s = tcode.split(","); 
			for(String tcodes:tcode_s){
				// insert treatment
				sql = "INSERT INTO treatcontinue_treatmentcode "
						+ "(continue_id, continue_phase, continue_count, continue_count_all, treatment_code) "
						+ "VALUES ("+continue_id+", "+continue_phase_value+", "+j+", "+continue_count_all+", '"+tcodes+"')" ;
				  
					Stmt = conn.createStatement();
					Stmt.executeUpdate(sql);
					Stmt.close(); 
			}
			String[] dcode_s = dcode.split(",");
			String[] drug_qty_s = drug_qty.split(",");
			int k = 0;
			for(String dcodes:dcode_s){
				// insert durg 
				sql = "INSERT INTO treatcontinue_product "
						+ "(continue_id, continue_phase, continue_count, continue_count_all, product_id, qty, producttype_id) "
						+ "VALUES ("+continue_id+", "+continue_phase_value+", "+j+", "+continue_count_all+", "+dcodes+", "+drug_qty_s[k]+", '0001')" ;
				  
					Stmt = conn.createStatement();
					Stmt.executeUpdate(sql);
					Stmt.close();
				k++;
			}
			// insert treatment continue transaction
			sql = "INSERT INTO treatcontinue_transaction "
					+ "(continue_id, continue_phase, continue_count, continue_count_all, setup_price, price) "
					+ "VALUES ("+continue_id+", "+continue_phase_value+", "+j+", "+continue_count_all+", "+phase_amount+", 0)" ;
			   
				Stmt = conn.createStatement();
				Stmt.executeUpdate(sql);
				Stmt.close();
		} 
		conn.commit();
	}   
	conn.close();
	  		
	out.println(continue_id);
%>