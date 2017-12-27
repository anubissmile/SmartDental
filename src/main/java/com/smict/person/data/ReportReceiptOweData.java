package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smict.all.model.FinanceModel;
import com.smict.all.model.ReportReceiptOweModel;
import com.smict.person.model.DepositModel; 

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class ReportReceiptOweData
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	/**
	 * Chunking all brand table
	 * @author anubissmile and setthaphong
	 * @return List<BrandModel>
	 */
	public List<ReportReceiptOweModel> getOrder_list_receipt(String hn){ 
		List<ReportReceiptOweModel> orderreceiptlist = new ArrayList<ReportReceiptOweModel>();
		String sql = "SELECT a.order_id,b.receipt_id, a.create_date "
				//+ ",(CASE WHEN b.receipt_type = 1 THEN 'ประกันสังคม' ELSE 'ปกติ' END) as receipt_typename "
				+ ",receipt_type as receipt_typename " 
				//+ ",COUNT(a.id) as countrow " 
				
				+ "FROM order_order_receipt a "
				+ "inner join order_line_receipt b on(b.receipt_id = a.id) " 
				+ "left join pro_product pp ON (pp.product_id = b.product_id) " 
				+ "where ";
		
		if(hn!="") sql += "a.hn = '"+hn+"' ";
		
		sql += "group by a.id order by b.id ";
		
		int countrow = 1;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				ReportReceiptOweModel rroModel = new ReportReceiptOweModel();
				rroModel.setOrder_ID(rs.getInt("order_id"));
				rroModel.setReceipt_id(rs.getInt("receipt_id"));
				rroModel.setCountrow(countrow);
				rroModel.setReceipt_typename(rs.getString("receipt_typename")); 
				
				rroModel.setCreate_date_receipt(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("create_date").substring(0, 10),true)
						+" "+rs.getString("create_date").substring(11, 19));
				
				String receive_id_text = String.valueOf(rs.getInt("receipt_id"));
				
				if(receive_id_text.length()==1) receive_id_text = "00000"+receive_id_text;
				else if(receive_id_text.length()==2) receive_id_text = "0000"+receive_id_text;
				else if(receive_id_text.length()==3) receive_id_text = "000"+receive_id_text;
				else if(receive_id_text.length()==4) receive_id_text = "00"+receive_id_text;
				else if(receive_id_text.length()==5) receive_id_text = "0"+receive_id_text;
				
				rroModel.setReceipt_no(receive_id_text);
				  
				orderreceiptlist.add(rroModel);
				countrow++;
			}
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		return orderreceiptlist;
	}
	public List<ReportReceiptOweModel> getOrder_list_owe(String hn){ 
		List<ReportReceiptOweModel> orderreceiptlist = new ArrayList<ReportReceiptOweModel>();
		String sql = "SELECT a.id,b.owe_id,b.receipt_id,a.create_date "
				//+ ",(CASE WHEN b.receipt_type = 1 THEN 'ประกันสังคม' ELSE 'ปกติ' END) as receipt_typename " 
				//+ ",COUNT(a.id) as countrow " 
				
				+ "FROM order_receipt_owe a "
				+ "inner join order_line_receipt_owe b on(b.owe_id = a.id) " 
				+ "left join pro_product pp ON (pp.product_id = b.product_id) " 
				+ "where ";
		
		if(hn!="") sql += "a.hn = '"+hn+"' ";
		
		sql += "group by a.id order by b.id ";
		
		int countrow = 1;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				ReportReceiptOweModel rroModel = new ReportReceiptOweModel(); 
				rroModel.setReceipt_id(rs.getInt("receipt_id"));
				rroModel.setOwe_id(rs.getInt("owe_id"));
				rroModel.setCountrow(countrow); 
				
				rroModel.setCreate_date_receipt(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("create_date").substring(0, 10),true)
						+" "+rs.getString("create_date").substring(11, 19));
				
				String owe_id_text = String.valueOf(rs.getInt("owe_id"));
				
				if(owe_id_text.length()==1) owe_id_text = "00000"+owe_id_text;
				else if(owe_id_text.length()==2) owe_id_text = "0000"+owe_id_text;
				else if(owe_id_text.length()==3) owe_id_text = "000"+owe_id_text;
				else if(owe_id_text.length()==4) owe_id_text = "00"+owe_id_text;
				else if(owe_id_text.length()==5) owe_id_text = "0"+owe_id_text;
				
				rroModel.setOwe_no(owe_id_text);
				  
				orderreceiptlist.add(rroModel);
				countrow++;
			}
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		return orderreceiptlist;
	} 
		
}
