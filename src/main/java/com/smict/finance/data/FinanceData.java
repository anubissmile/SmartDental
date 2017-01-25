package com.smict.finance.data;
 
import java.io.IOException; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.smict.all.model.FinanceModel; 

import ldc.util.DBConnect;

public class FinanceData {

	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null,Stmt2=null;
	ResultSet rs = null;
	public List<FinanceModel> getDrug(int treatment_id){ 
		List<FinanceModel> drugList = new ArrayList<FinanceModel>();
		String sql = "SELECT "
				+"a.product_id, b.product_name, a.product_free, a.product_transfer, b.price " 
				+"FROM "
				+"history_treatment_product a " 
				+"INNER JOIN pro_product b ON (b.product_id = a.product_id and b.producttype_id = '0001') where ";
		
		if(treatment_id!=0) sql += "a.treatment_id = '"+treatment_id+"' and a.product_transfer <> 0 ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				FinanceModel financeModel = new FinanceModel();
				financeModel.setProduct_id(rs.getString("product_id"));
				financeModel.setProduct_name(rs.getString("product_name")); 
				
				String product_free 	= rs.getString("product_free").replace(".00", "");
				String product_transfer = rs.getString("product_transfer").replace(".00", "");
				String price			= rs.getString("price").replace(".00", "");
				
				String amountTotal = "0";
				if(Integer.valueOf(product_free)>Integer.valueOf(product_transfer)){
					amountTotal = "0";
				}else{
					amountTotal = String.valueOf(((Integer.valueOf(product_transfer)-Integer.valueOf(product_free))*Integer.valueOf(price)));
				} 
				financeModel.setProduct_free(product_free);
				financeModel.setProduct_transfer(product_transfer);
				financeModel.setAmount(price);
				financeModel.setAmountTotal(amountTotal); 
				
				drugList.add(financeModel);
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
			
		return drugList;
	}
	public List<FinanceModel> getProduct(int treatment_id){ 
		List<FinanceModel> productList = new ArrayList<FinanceModel>();
		String sql = "SELECT "
				+"a.product_id, b.product_name, a.qty, b.price " 
				+"FROM "
				+"history_treatment_product a " 
				+"INNER JOIN pro_product b ON (b.product_id = a.product_id and b.producttype_id = '0002') where ";
		
		if(treatment_id!=0) sql += "a.treatment_id = '"+treatment_id+"' ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				FinanceModel financeModel = new FinanceModel();
				financeModel.setProduct_id(rs.getString("product_id"));
				financeModel.setProduct_name(rs.getString("product_name")); 
				
				String qty 	= rs.getString("qty").replace(".00", ""); 
				String price			= rs.getString("price").replace(".00", "");
				
				String amountTotal = "0";
				 
				amountTotal = String.valueOf(Integer.valueOf(qty)*Integer.valueOf(price));
				  
				financeModel.setProduct_transfer(qty);
				financeModel.setAmount(price);
				financeModel.setAmountTotal(amountTotal); 
				
				productList.add(financeModel);
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
			
		return productList;
	}
	public int addDocument(String hn,String path,String doc_type,String folderName,String class_icon){
		int rt = 0;
		String inSQL = "INSERT INTO document_upload (hn,path,document_type,upload_date,document_folder,class_icon) VALUES("
				+ "'"+hn+"','"+path+"','"+doc_type+"',NOW(),'"+folderName+"','"+class_icon+"')";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rt = Stmt.executeUpdate(inSQL);
			
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rt;
	}
	 
	public String getReportNo_Running(String year){ 
		 String report_no = ""; 
		
		String sql = "SELECT "
				+"a.year, a.report_no, a.treatment_id " 
				+"FROM "
				+"running_number_report_treatment a " 
				+"where ";
		
		if(year!=null) sql += "a.year = '"+year+"' ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){ 
				report_no = rs.getString("report_no"); 
			}
			if(!report_no.equals("")){ 
				report_no = String.valueOf(Integer.valueOf(report_no)+1);
			}else{ 
				report_no = "1";
			}
			if(report_no.length()==1)  report_no = "0000"+report_no;
			else if(report_no.length()==2)  report_no = "000"+report_no;
			else if(report_no.length()==3)  report_no = "00"+report_no;
			else if(report_no.length()==4)  report_no = "0"+report_no;
			
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		return report_no;
	}
	public String getReportNo(String year, int treatment_id){ 
		 String report_no = ""; 
		
		String sql = "SELECT "
				+"a.year, a.report_no, a.treatment_id " 
				+"FROM "
				+"running_number_report_treatment a " 
				+"where a.year = '"+year+"' and a.treatment_id = "+treatment_id+" "; 
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){ 
				report_no = rs.getString("report_no"); 
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
			
		return report_no;
	}
	public boolean checkReportNo(String year, int treatment_id){ 
		boolean checkReportNo = false;
		String sql = "SELECT "
				+"a.year, a.report_no, a.treatment_id " 
				+"FROM "
				+"running_number_report_treatment a " 
				+"where a.year = '"+year+"' and a.treatment_id = "+treatment_id+" "; 
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){ 
				checkReportNo = true;
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
			
		return checkReportNo;
	}
	public void insertReportNo(String year, int treatment_id, String report_no){
		 
		String inSQL = "INSERT INTO running_number_report_treatment (year,treatment_id,report_no) VALUES("
				+ "'"+year+"','"+treatment_id+"','"+report_no+"')";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(inSQL);
			
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public void updateTreatmentidfornull(String hn){
		 
		String inSQL = "UPDATE patient set treatment_id = null "
				+ "WHERE hn = '"+hn+"'";
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(inSQL);
			
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
}
