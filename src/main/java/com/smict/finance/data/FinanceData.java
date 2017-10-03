package com.smict.finance.data;
 
import java.io.IOException; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.smict.all.model.FinanceModel;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;
import com.smict.treatment.model.TreatmentModel;

import ldc.util.Auth;
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
	public List<FinanceModel>  getTreatmentLineForFinance(String treatpatID) throws Exception{
		
		String SQL = "SELECT "
				+ "tpl.id,tpl.treatment_id,tpl.treatment_patient_id, "
				+ "tpl.treatment_price,tpl.surf,tpl.tooth, "
				+ "tpl.tooth_type_id,tpl.treatment_plandetail_id, "
				+ "tm.nameth,tm.auto_homecall,tm.recall_typeid, "
				+ "treatment_category.group_id,"
				+ "	tm.category_id "
				+ "FROM treatment_patient_line AS tpl "
				+ "INNER JOIN treatment_master AS tm ON tpl.treatment_id = tm.id "
				+ "INNER JOIN treatment_category ON tm.category_id = treatment_category.id "
				+ "WHERE tpl.treatment_patient_id =  '"+treatpatID+"' ";

		List<FinanceModel> orderLineList = new ArrayList<FinanceModel>(); 
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					FinanceModel orderModel = new FinanceModel();
					orderModel.setOrderLine_TreatID(rs.getInt("tpl.treatment_id"));
					orderModel.setOrderLine_treatPatID(rs.getInt("treatment_patient_id"));
					orderModel.setOrderLine_homecall(rs.getString("tm.auto_homecall"));
					orderModel.setOrderLine_recall(rs.getString("tm.recall_typeid"));
					orderModel.setOrderLine_toothTypeID(rs.getInt("tpl.tooth_type_id"));
					orderModel.setOrderLine_surf(rs.getString("tpl.surf"));
					orderModel.setOrderLine_tooth(rs.getString("tpl.tooth"));
					orderModel.setOrderLine_treatName(rs.getString("tm.nameth"));
					orderModel.setOrderLine_groupID(rs.getInt("treatment_category.group_id"));
					orderModel.setOrderLine_catID(rs.getInt("tm.category_id"));
					orderModel.setOrderLine_price(rs.getDouble("tpl.treatment_price"));
					orderLineList.add(orderModel);
				}
				agent.disconnectMySQL();
				return orderLineList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public FinanceModel  getTreatmentPatientForFinance(String treatPatId) throws Exception{
		FinanceModel orderModel = new FinanceModel();
		String SQL = "SELECT "
				+ "tp.id, "
				+ "tp.patient_hn,pre_pat.pre_name_th,pt.first_name_th, "
				+ "pt.last_name_th,pt.first_name_en,pt.last_name_en, "
				+ "room_id.room_name, "
				+ "doc.doctor_id,pre_doc.pre_name_th,doc.first_name_th, "
				+ "doc.last_name_th,doc.first_name_en,doc.last_name_en "
				+ "FROM treatment_patient AS tp "
				+ "INNER JOIN patient AS pt ON tp.patient_hn = pt.hn "
				+ "INNER JOIN pre_name AS pre_pat ON pt.pre_name_id = pre_pat.pre_name_id "
				+ "INNER JOIN room_id ON tp.room_id = room_id.room_id "
				+ "INNER JOIN doctor AS doc ON tp.doctor_id = doc.doctor_id "
				+ "INNER JOIN pre_name AS pre_doc ON doc.pre_name_id = pre_doc.pre_name_id "
				+ "WHERE tp.id  = '"+treatPatId+"' ";


		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					orderModel.setOrder_ID(rs.getInt("tp.id"));
					orderModel.setOrder_Hn(rs.getString("tp.patient_hn"));
					orderModel.setOrder_pat_pname(rs.getString("pre_pat.pre_name_th"));
					orderModel.setOrder_pat_FnameTh(rs.getString("pt.first_name_th"));
					orderModel.setOrder_pat_LnameTh(rs.getString("pt.last_name_th"));
					orderModel.setOrder_pat_FnameEn(rs.getString("pt.first_name_en"));
					orderModel.setOrder_pat_LnameEn(rs.getString("pt.last_name_en"));
					orderModel.setOrder_roomName(rs.getString("room_id.room_name"));
					orderModel.setOrder_docID(rs.getInt("doc.doctor_id"));
					orderModel.setOrder_doc_pname(rs.getString("pre_doc.pre_name_th"));
					orderModel.setOrder_doc_FnameTh(rs.getString("doc.first_name_th"));
					orderModel.setOrder_doc_LnameTh(rs.getString("doc.last_name_th"));
					orderModel.setOrder_doc_FnameEn(rs.getString("doc.first_name_en"));
					orderModel.setOrder_doc_LnameEn(rs.getString("doc.last_name_en"));
				}
				agent.disconnectMySQL();
				return orderModel;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public List<PromotionModel>  getPromotion(String hn,String subcontypeID) throws Exception{
		
		String SQL = "SELECT "
				+ "promotion.id,promotion.`name`,promotion.start_date,promotion.end_date, "
				+ "promotion.use_condition,promotion.billcostover,promotion.is_allday, "
				+ "promotion.is_alltime,promotion.start_time,promotion.end_time, "
				+ "promotion.is_allsubcontact,promotion.is_birthmonth,promotion.is_allage, "
				+ "promotion.from_age,promotion.to_age,promotion.is_treatmentcount, "
				+ "promotion.is_allbranch,promotion.service_charge,promotion.description, "
				+ "promotion.`status`,promotion_condition_branch.branch_id,promotion_condition_day.day_id, "
				+ "promotion_condition_subcontact.sub_contact_id,`day`.name_th "
				+ "FROM promotion "
				+ "LEFT JOIN promotion_condition_branch ON promotion.id = promotion_condition_branch.promotion_id "
				+ "LEFT JOIN promotion_condition_day ON promotion.id = promotion_condition_day.promotion_id "
				+ "INNER JOIN promotion_condition_subcontact ON promotion.id = promotion_condition_subcontact.promotion_id "
				+ "LEFT JOIN `day` ON promotion_condition_day.day_id = `day`.id "
				+ "WHERE promotion.`status` = 0  "
				+ "AND (promotion.is_allbranch = 1 OR promotion_condition_branch.branch_id = '"+Auth.user().getBranchID()+"') "
				+ "AND (promotion.is_allday = 1 OR promotion_condition_day.day_id = WEEKDAY(NOW()) ) "
				+ "AND  (promotion_condition_subcontact.sub_contact_id  IN ("+subcontypeID+")) "
				+ "AND (CURDATE() BETWEEN promotion.start_date AND promotion.end_date ) "
				+ "AND (promotion.is_alltime = 1 OR TIME(NOW()) BETWEEN promotion.start_time AND promotion.end_time) "
				+ "AND (promotion.is_birthmonth = 0 OR (promotion.is_birthmonth = 1 AND MONTH(DATE(NOW())) = MONTH(DATE((SELECT patient.birth_date FROM patient WHERE hn = '"+hn+"'))))) "
				+ "AND (promotion.is_allage = 1 OR YEAR(FROM_DAYS(DATEDIFF(NOW(),(SELECT patient.birth_date FROM patient WHERE hn = '"+hn+"')))) BETWEEN promotion.from_age AND  promotion.to_age) "
				+ "AND (promotion.billcostover <= 100) "
				+ "AND (promotion.use_condition = 'REUSE' OR (promotion.use_condition = 'ONETIME' AND  (SELECT COUNT(id) FROM order_order WHERE hn = '"+hn+"' AND order_order.discount_type = 1 AND order_order.discount_ref = promotion.id))=0 ) "
				+ "AND (promotion.is_treatmentcount <= (SELECT COUNT(DISTINCT order_order.id) FROM order_order INNER JOIN order_line ON order_order.id = order_line.order_id WHERE order_line.product_type = 4 AND order_order.hn = '"+hn+"')) "
				+ "GROUP BY promotion.id ";

		List<PromotionModel> promoList = new ArrayList<PromotionModel>(); 
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					PromotionModel promoModel = new PromotionModel();
					promoModel.setName(rs.getString("promotion.name"));
					promoModel.setPromotion_id(rs.getInt("promotion.id"));
					promoList.add(promoModel);
				}
				agent.disconnectMySQL();
				return promoList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	public JSONArray getJsonArrayListProduct(String hn,String proID,String type){

		String sql ="SELECT pro_product.product_id,pro_product.product_name, "
				+ "pro_productunit.productunit_name,IFNULL(patient_beallergic.product_id,'nu'), "
				+ "pro_product.price,pro_product.producttype_id "
				+ "FROM "
				+ "pro_product " 
				+ "LEFT JOIN patient_beallergic ON pro_product.product_id = patient_beallergic.product_id "
				+ "AND patient_beallergic.hn = '"+hn+"' "
				+ "INNER JOIN pro_productunit ON pro_product.productunit_id = pro_productunit.productunit_id "
				+ "WHERE pro_product.product_id != 1 AND pro_product.producttype_id = '"+type+"' "
				+ "AND IFNULL(patient_beallergic.product_id,'nu') = 'nu' "
				+ "AND pro_product.product_id NOT IN ("+proID+") "
				+ ""; 

		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("proid", rs.getString("pro_product.product_id"));
				jsonOBJ.put("proname", rs.getString("pro_product.product_name"));
				jsonOBJ.put("proprice", rs.getString("pro_product.price"));
				jsonOBJ.put("protype", rs.getString("pro_product.producttype_id"));
				jsonArray.put(jsonOBJ);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return jsonArray;
	}
	public List<FinanceModel>  getContactFromPatAndPro(String hn,String proID) throws Exception{
		
		String SQL = "SELECT "
				+ "promotion_sub_contact.sub_contact_id,promotion_sub_contact.sub_contact_name "
				+ "FROM promotion_sub_contact "
				+ "INNER JOIN promotion_condition_subcontact ON promotion_sub_contact.sub_contact_id = promotion_condition_subcontact.sub_contact_id "
				+ "INNER JOIN patient_contype ON patient_contype.sub_contact_id = promotion_sub_contact.sub_contact_id "
				+ "WHERE hn = '"+hn+"' ";
					if(proID!=null)
					SQL+= "AND promotion_id =  '"+proID+"'  ";

		List<FinanceModel> orderLineList = new ArrayList<FinanceModel>(); 
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					FinanceModel orderModel = new FinanceModel();
					orderModel.setContact_id(rs.getString("promotion_sub_contact.sub_contact_id"));
					orderModel.setContactName(rs.getString("promotion_sub_contact.sub_contact_name"));
					orderLineList.add(orderModel);
				}
				agent.disconnectMySQL();
				return orderLineList;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return null;
	}
	
	public JSONObject getJsonArrayListProduct(String giftnum){

		String sql ="SELECT giftcard_line.amount "
				+ "FROM giftcard_line "
				+ "WHERE giftcard_line.name = '"+giftnum+"'";

		JSONObject jsonOBJ = new JSONObject(); 
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				
				jsonOBJ.put("giftamount", rs.getInt("giftcard_line.amount"));
			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return jsonOBJ;
	}
	
	
}
