package com.smict.finance.data;
 
import java.io.IOException; 
import java.sql.Connection;
import java.sql.PreparedStatement;
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
	PreparedStatement pStmt = null;
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
				+ "WHERE tpl.treatment_patient_id =  '"+treatpatID+"' and status_payment is null ";

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
	public FinanceModel  getTreatmentPatientForFinanceSearchToHN(String hn) throws Exception{
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
				+ "WHERE tp.patient_hn  = '"+hn+"' and tp.status_work = '2' ";


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
	public int  getTreatmentPatientIDForFinanceSearchToHN(String hn) throws Exception{
		int treatment_patient_id = 0;
		String SQL = "SELECT "
				+ "tp.id " 
				+ "FROM treatment_patient AS tp " 
				+ "WHERE tp.patient_hn  = '"+hn+"' and tp.status_work = '2' ";


		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					treatment_patient_id = rs.getInt("tp.id"); 
				}
				agent.disconnectMySQL();
				return treatment_patient_id;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		agent.disconnectMySQL();
		return (Integer) null;
	}
	public List<PromotionModel>  getPromotion(String hn,int subcontypeID) throws Exception{
		
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
				+ "INNER JOIN giftcard_giftcard ON giftcard_giftcard.id = giftcard_line.giftcard_id "
				+ "WHERE giftcard_line.name = '"+giftnum+"' "
				+ "AND giftcard_giftcard.status = 't' "
				+ "AND CURDATE() BETWEEN giftcard_giftcard.start_date AND giftcard_giftcard.expiredate ";

		JSONObject jsonOBJ = new JSONObject(); 
		int check = 0;
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				check = 1;
				jsonOBJ.put("giftamount", rs.getInt("giftcard_line.amount"));
				jsonOBJ.put("type", check);
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
	public List<FinanceModel>  getgiftVoucher(String gvID) throws Exception{
		
		String SQL = "SELECT "
				+ "giftvoucher_line.`name`,giftvoucher_privilege.amount, "
				+ "giftvoucher_privilege.type,giftvoucher_privilege.product_id, "
				+ "giftvoucher_privilege.product_type "
				+ "FROM giftvoucher_giftvoucher "
				+ "INNER JOIN giftvoucher_line ON giftvoucher_giftvoucher.id = giftvoucher_line.giftvoucher_id "
				+ "INNER JOIN giftvoucher_privilege ON giftvoucher_giftvoucher.id = giftvoucher_privilege.giftvoucher_id "
				+ "WHERE giftvoucher_line.name = '"+gvID+"' "
				+ "AND giftvoucher_giftvoucher.status = 't'  "
				+ "AND CURDATE() BETWEEN giftvoucher_giftvoucher.start_date AND giftvoucher_giftvoucher.expiredate ";
		
		List<FinanceModel> orderLineList = new ArrayList<FinanceModel>(); 
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					FinanceModel orderModel = new FinanceModel();
					orderModel.setGv_type(rs.getInt("giftvoucher_privilege.type"));
					orderModel.setGv_proID(rs.getInt("giftvoucher_privilege.product_id"));
					orderModel.setGv_protype(rs.getInt("giftvoucher_privilege.product_type"));
					orderModel.setGv_amount(rs.getDouble("giftvoucher_privilege.amount"));
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
	public JSONArray getJsonArrayListPromotiondetail(String idpro){

		String sql = "SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id, "
				+ "promotion_detail.promotion_id, "
				+ "pro_product.product_name AS allname,promotion_detail.qty "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN pro_product ON promotion_detail.product_id = pro_product.product_id "
				+ "WHERE promotion_detail.product_type in (1,2,3) AND promotion_detail.promotion_id ="+idpro
					
				+ " UNION ALL "
				
				+"SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id, "
				+ "promotion_detail.promotion_id, "
				+ "treatment_master.nameth,promotion_detail.qty "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN treatment_master ON treatment_master.id = promotion_detail.product_id "
				+ "WHERE promotion_detail.product_type = 7 AND promotion_detail.promotion_id ="+idpro
				
				+ " UNION ALL "
				
				+"SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id, "
				+ "promotion_detail.promotion_id, "
				+ "treatment_category.`name`,promotion_detail.qty "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN treatment_category ON promotion_detail.product_id = treatment_category.id "
				+ "WHERE promotion_detail.product_type = 6 AND  promotion_detail.promotion_id ="+idpro
				
				+ " UNION ALL "
				
				+"SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id, "
				+ "promotion_detail.promotion_id, "
				+ "treatment_group.`code`,promotion_detail.qty "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN treatment_group ON promotion_detail.product_id = treatment_group.id "
				+ "WHERE promotion_detail.product_type = 5 AND promotion_detail.promotion_id ="+idpro
				+ " UNION ALL "
				
				+"SELECT "
				+ "promotion_detail.id,promotion_detail.`name`,promotion_detail.discount_amount, "
				+ "promotion_detail.discount_type,promotion_detail.product_type, "
				+ "promotion_detail.product_id, "
				+ "promotion_detail.promotion_id, "
				+ "IFNULL('-','-'),promotion_detail.qty "
				+ "FROM "
				+ "promotion_detail "
				+ "WHERE promotion_detail.product_type = 4 AND promotion_detail.promotion_id ="+idpro;

		
		JSONArray json = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject();
				jsonOBJ.put("namede",rs.getString("name"));
				jsonOBJ.put("namedetaill",rs.getString("allname"));
				jsonOBJ.put("protypedetail", rs.getInt("product_type"));
				jsonOBJ.put("prodistypedetail", rs.getInt("discount_type"));
				jsonOBJ.put("prodisdetail", rs.getDouble("discount_amount"));
				jsonOBJ.put("qty", rs.getInt("qty"));
				json.put(jsonOBJ);

			}
			
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return json;
	}
	public JSONObject checksocialSecurity(String branchID){

		String sql ="SELECT branch.branch_id "
				+ "FROM branch "
				+ "WHERE branch.branch_id = '"+branchID+"' AND branch.socialSecurity = '1' ";

		JSONObject jsonOBJ = new JSONObject(); 
		boolean check = false;
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				check = true;				
			}
			jsonOBJ.put("check", check);
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
	public boolean checkSocialSecurityTreatment(List treatmentID){
		boolean check = false;
		
		try {
		Connection conn = agent.getConnectMYSql();
		
		for(int i=0; i<treatmentID.size(); i++) {
			String sql ="SELECT tm.id "
					+ "FROM treatment_master tm "
					+ "WHERE tm.id = '"+treatmentID.get(i)+"' AND tm.is_social_security = '1' ";
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				check = true;				
			}
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
		} 
			
			if(!conn.isClosed()) conn.close();		
			
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return check;
	}
	 
	public JSONObject checkAndgetContact(String subconID){

		String sql ="SELECT ps.sub_contact_id,ps.sub_contact_name, "
				+ "ps.contact_id,ps.sms_piority,ps.sub_contact_type_id, "
				+ "ps.address,ps.amount_default,ps.company_name,ps.`status` "
				+ "FROM promotion_sub_contact AS ps "
				+ "WHERE ps.sub_contact_id = '"+subconID+"' ";

		JSONObject jsonOBJ = new JSONObject(); 

		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				jsonOBJ.put("conID", rs.getInt("ps.contact_id"));
				jsonOBJ.put("subContypeID", rs.getInt("ps.sub_contact_type_id"));
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
	public JSONObject getContactamount(String subconID,String hn){

		String sql ="SELECT pw.id,pw.sub_contact_id, "
				+ "pw.total_amount,pw.patient_hn,pw.isstatus "
				+ "FROM promotion_subcontact_wallet AS pw "
				+ "WHERE pw.sub_contact_id = '"+subconID+"'  ";
				if(hn != "null"){
					sql += "AND pw.patient_hn = '"+hn+"' ";
				}
		

		JSONObject jsonOBJ = new JSONObject(); 
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				jsonOBJ.put("totalamountall", rs.getDouble("total_amount"));
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
	public  double getDepositamount(String hn){
		double amountDeposit = 0;
		String sql ="SELECT dmt.total_money " 
				+ "FROM deposit_money_transaction AS dmt "
				+ "WHERE dmt.hn = '"+hn+"' ORDER BY dmt.id desc limit 1 "; 
 
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				amountDeposit = rs.getDouble("total_money");
			}
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			if(!conn.isClosed()) conn.close();				
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return amountDeposit;
	}
	public List<FinanceModel>  getAssistant(String patID) throws Exception{
		
		String SQL = "SELECT employee.emp_id,pre_name.pre_name_th, "
				+ "employee.first_name_th,employee.last_name_th, "
				+ "employee.first_name_en,employee.last_name_en "
				+ "FROM treatment_assistant "
				+ "INNER JOIN employee ON treatment_assistant.emp_id = employee.emp_id "
				+ "INNER JOIN pre_name ON employee.pre_name_id = pre_name.pre_name_id "
				+ "WHERE treatment_assistant.treatment_patient_id = '"+patID+"' ";

		
		List<FinanceModel> orderLineList = new ArrayList<FinanceModel>(); 
		agent.connectMySQL();
		agent.exeQuery(SQL);
		if(agent.size() > 0){
			try {
				ResultSet rs = agent.getRs();				
				while(rs.next()){
					FinanceModel orderModel = new FinanceModel();
					orderModel.setOs_empid(rs.getString("employee.emp_id"));
					orderModel.setOs_emp_pname(rs.getString("pre_name.pre_name_th"));
					orderModel.setOs_emp_FnameTh(rs.getString("employee.first_name_th"));
					orderModel.setOs_emp_LnameTh(rs.getString("employee.last_name_th"));
					orderModel.setOs_emp_FnameEn(rs.getString("employee.first_name_en"));
					orderModel.setOs_emp_LnameEn(rs.getString("employee.last_name_en"));
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
	public int addOrderOrder(FinanceModel fModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO order_order(hn,pat_pre_name,pat_firstname_th,pat_lastname_th,pat_firstname_en,"
				+ "pat_lastname_en,doctor_id,doctor_pre_name,doctor_firstname_th,doctor_lastname_th,doctor_firstname_en,"
				+ "doctor_lastname_en,emp_id,emp_pre_name,emp_firstname_th,emp_lastname_th,emp_firstname_en,emp_lastname_en,pat_checkin_room,"
				+ "branch_id,sub_contact_id,amount_untaxed,amount_tax,amount_total,doctor_disbaht_total,branch_disbaht_total,discount_total,"
				+ "discount_type,discount_ref,pay_amount_total,remain_amount_total,status,create_date"
				+ ") VALUES "
				
					+ "('"+fModel.getOrder_Hn()
					+"','"+fModel.getOrder_pat_pname()
					+"','"+fModel.getOrder_pat_FnameTh()
					+"','"+fModel.getOrder_pat_LnameTh()
					+"','"+fModel.getOrder_pat_FnameEn()
					+"','"+fModel.getOrder_pat_LnameEn()
					+"','"+fModel.getOrder_docID()
					+"','"+fModel.getOrder_doc_pname()
					+"','"+fModel.getOrder_doc_FnameTh()
					+"','"+fModel.getOrder_doc_LnameTh()
					+"','"+fModel.getOrder_doc_FnameEn()
					+"','"+fModel.getOrder_doc_LnameEn()
					+"','"+Auth.user().getEmpId()
					+"','"+Auth.user().getPrefixName()
					+"','"+Auth.user().getfNameTH()
					+"','"+Auth.user().getlNameTH()
					+"','"+Auth.user().getfNameEN()
					+"','"+Auth.user().getlNameTH()
					+"','"+fModel.getOrder_roomName()
					+"','"+Auth.user().getBranchID()
					+"','"+fModel.getOrder_SubcontactID()
					+"','"+fModel.getOr_amount_untaxed()
					+"','"+fModel.getOr_amount_tax()
					+"','"+fModel.getOr_amount_total()
					+"','"+fModel.getOr_doctor_disbaht_total()
					+"','"+fModel.getOr_branch_disbaht_total()
					+"','"+fModel.getOr_discount_total()
					+"','"+fModel.getOrder_discountType()
					+"','"+fModel.getOrder_discount_ref()
					+"','"+fModel.getOr_pay_amount_total()
					+"','"+fModel.getOr_remain_amount_total()+"','2',now())";  

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			int promotion_id=0;
			if (rs.next()){
				promotion_id=rs.getInt(1);
			}			
			if (!rs.isClosed())
				rs.close();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close(); 
			
			return promotion_id; 
		}
public int addOrderOrderReceipt(FinanceModel fModel, int order_id) throws IOException, Exception{
		
		String SQL = "INSERT INTO order_order_receipt(hn,order_id,pat_pre_name,pat_firstname_th,pat_lastname_th,pat_firstname_en,"
				+ "pat_lastname_en,doctor_id,doctor_pre_name,doctor_firstname_th,doctor_lastname_th,doctor_firstname_en,"
				+ "doctor_lastname_en,emp_id,emp_pre_name,emp_firstname_th,emp_lastname_th,emp_firstname_en,emp_lastname_en,pat_checkin_room,"
				+ "branch_id,sub_contact_id,amount_untaxed,amount_tax,amount_total,doctor_disbaht_total,branch_disbaht_total,discount_total,"
				+ "discount_type,discount_ref,pay_amount_total,remain_amount_total,status,create_date"
				+ ") VALUES "
				
					+ "('"+fModel.getOrder_Hn()
					+"',"+order_id
					+",'"+fModel.getOrder_pat_pname()
					+"','"+fModel.getOrder_pat_FnameTh()
					+"','"+fModel.getOrder_pat_LnameTh()
					+"','"+fModel.getOrder_pat_FnameEn()
					+"','"+fModel.getOrder_pat_LnameEn()
					+"','"+fModel.getOrder_docID()
					+"','"+fModel.getOrder_doc_pname()
					+"','"+fModel.getOrder_doc_FnameTh()
					+"','"+fModel.getOrder_doc_LnameTh()
					+"','"+fModel.getOrder_doc_FnameEn()
					+"','"+fModel.getOrder_doc_LnameEn()
					+"','"+Auth.user().getEmpId()
					+"','"+Auth.user().getPrefixName()
					+"','"+Auth.user().getfNameTH()
					+"','"+Auth.user().getlNameTH()
					+"','"+Auth.user().getfNameEN()
					+"','"+Auth.user().getlNameTH()
					+"','"+fModel.getOrder_roomName()
					+"','"+Auth.user().getBranchID()
					+"','"+fModel.getOrder_SubcontactID()
					+"','"+fModel.getOr_amount_untaxed()
					+"','"+fModel.getOr_amount_tax()
					+"','"+fModel.getOr_amount_total()
					+"','"+fModel.getOr_doctor_disbaht_total()
					+"','"+fModel.getOr_branch_disbaht_total()
					+"','"+fModel.getOr_discount_total()
					+"','"+fModel.getOrder_discountType()
					+"','"+fModel.getOrder_discount_ref()
					+"','"+fModel.getOr_pay_amount_total()
					+"','"+fModel.getOr_remain_amount_total()+"','2',now())";  
 
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			int receiptId=0;
			if (rs.next()){
				receiptId=rs.getInt(1);
			}			
			if (!rs.isClosed())
				rs.close();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			
			return receiptId; 
		}
public int addOrderReceiptOwe(FinanceModel fModel, int receipt_id) throws IOException, Exception{
	
	String SQL = "INSERT INTO order_receipt_owe(hn,receipt_id,pat_pre_name,pat_firstname_th,pat_lastname_th,pat_firstname_en,"
			+ "pat_lastname_en,doctor_id,doctor_pre_name,doctor_firstname_th,doctor_lastname_th,doctor_firstname_en,"
			+ "doctor_lastname_en,emp_id,emp_pre_name,emp_firstname_th,emp_lastname_th,emp_firstname_en,emp_lastname_en,pat_checkin_room,"
			+ "branch_id,sub_contact_id,amount_untaxed,amount_tax,amount_total,doctor_disbaht_total,branch_disbaht_total,discount_total,"
			+ "discount_type,discount_ref,pay_amount_total,remain_amount_total,status,create_date"
			+ ") VALUES "
			
				+ "('"+fModel.getOrder_Hn()
				+"',"+receipt_id
				+",'"+fModel.getOrder_pat_pname()
				+"','"+fModel.getOrder_pat_FnameTh()
				+"','"+fModel.getOrder_pat_LnameTh()
				+"','"+fModel.getOrder_pat_FnameEn()
				+"','"+fModel.getOrder_pat_LnameEn()
				+"','"+fModel.getOrder_docID()
				+"','"+fModel.getOrder_doc_pname()
				+"','"+fModel.getOrder_doc_FnameTh()
				+"','"+fModel.getOrder_doc_LnameTh()
				+"','"+fModel.getOrder_doc_FnameEn()
				+"','"+fModel.getOrder_doc_LnameEn()
				+"','"+Auth.user().getEmpId()
				+"','"+Auth.user().getPrefixName()
				+"','"+Auth.user().getfNameTH()
				+"','"+Auth.user().getlNameTH()
				+"','"+Auth.user().getfNameEN()
				+"','"+Auth.user().getlNameTH()
				+"','"+fModel.getOrder_roomName()
				+"','"+Auth.user().getBranchID()
				+"','"+fModel.getOrder_SubcontactID()
				+"','"+fModel.getOr_amount_untaxed()
				+"','"+fModel.getOr_amount_tax()
				+"','"+fModel.getOr_amount_total()
				+"','"+fModel.getOr_doctor_disbaht_total()
				+"','"+fModel.getOr_branch_disbaht_total()
				+"','"+fModel.getOr_discount_total()
				+"','"+fModel.getOrder_discountType()
				+"','"+fModel.getOrder_discount_ref()
				+"','"+fModel.getOr_pay_amount_total()
				+"','"+fModel.getOr_remain_amount_total()+"','2',now())";  

		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		ResultSet rs = pStmt.getGeneratedKeys();
		int receiptId=0;
		if (rs.next()){
			receiptId=rs.getInt(1);
		}			
		if (!rs.isClosed())
			rs.close();
		if (!pStmt.isClosed())
			pStmt.close();
		if (!conn.isClosed())
			conn.close();
		
		return receiptId; 
	}
public void addOrderReceiptOweline(FinanceModel fModel,int type,int isfree,int owe_id) throws IOException, Exception{
	
	String SQL ="INSERT INTO order_line_receipt_owe(receipt_id,orderline_id,owe_id,product_id,product_type,qty,price,"
					+ "amount_untaxed,amount_tax,disdoc_disbaht,branch_disbaht,amount_total,"
					+ "discount,hn,owe_amount,pay_amount,pay_sso,paid_amount,free_status,recall_status,homecall_status_timer,surf,tooth,"
					+ "tooth_type_id,df,status,create_date,paylast_type"
					+ ") VALUES "
				
					+ "('"+fModel.getReceipt_id()
					+"','"+fModel.getOrderLine_ID()
					+"','"+owe_id 
					+"','"+fModel.getProduct_id()
					+"','"+type
					+"','"+fModel.getOr_qty()
					+"','"+fModel.getOrderLine_price()
					+"','"+fModel.getOr_amount_untaxed()
					+"','"+fModel.getOr_amount_tax()
					+"','"+fModel.getOr_doctor_disbaht_total()
					+"','"+fModel.getOr_branch_disbaht_total()
					+"','"+fModel.getOr_amount_total()
					+"','"+fModel.getOr_discount_total()
					+"','"+fModel.getOrder_Hn()
					+"','"+fModel.getOr_owe()
					+"','"+fModel.getPay_amount_total()
					+"','"+fModel.getOr_pay_amount_total()
					+"','"+fModel.getPaid_amount()
					+"','"+isfree
					+"','"+fModel.getOrderLine_recall()
					+"','"+fModel.getOrderLine_homecall()
					+"','"+fModel.getOrderLine_surf()
					+"','"+fModel.getOrderLine_tooth()
					+"','"+fModel.getOrderLine_toothTypeID()
					+"','"+fModel.getOl_df()+"','1',now()"
					+",'"+fModel.getPaylast_type()+"')"; 
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		 		 
		if (!pStmt.isClosed())
			pStmt.close();
		if (!conn.isClosed())
			conn.close();

}
public int addOrderReceiptline(FinanceModel fModel,int type,int isfree,int receipt_id,String receipt_type) throws IOException, Exception{
	
			String SQL ="INSERT INTO order_line_receipt(order_id,orderline_id,receipt_id,receipt_type,product_id,product_type,qty,price,"
							+ "amount_untaxed,amount_tax,disdoc_disbaht,branch_disbaht,amount_total,"
							+ "discount,hn,pay_amount,pay_sso,paid_amount,free_status,recall_status,homecall_status_timer,surf,tooth,"
							+ "tooth_type_id,df,status,create_date,paylast_type"
							+ ") VALUES "
						
							+ "('"+fModel.getOrder_ID()
							+"','"+fModel.getOrderLine_ID()
							+"','"+receipt_id
							+"','"+receipt_type
							+"','"+fModel.getProduct_id()
							+"','"+type
							+"','"+fModel.getOr_qty()
							+"','"+fModel.getOrderLine_price()
							+"','"+fModel.getOr_amount_untaxed()
							+"','"+fModel.getOr_amount_tax()
							+"','"+fModel.getOr_doctor_disbaht_total()
							+"','"+fModel.getOr_branch_disbaht_total()
							+"','"+fModel.getOr_amount_total()
							+"','"+fModel.getOr_discount_total()
							+"','"+fModel.getOrder_Hn()
							+"','"+fModel.getPay_amount_total()
							+"','"+fModel.getOr_pay_amount_total()
							+"','"+fModel.getPaid_amount()
							+"','"+isfree
							+"','"+fModel.getOrderLine_recall()
							+"','"+fModel.getOrderLine_homecall()
							+"','"+fModel.getOrderLine_surf()
							+"','"+fModel.getOrderLine_tooth()
							+"','"+fModel.getOrderLine_toothTypeID()
							+"','"+fModel.getOl_df()+"','1',now()"
							+",'"+fModel.getPaylast_type()+"')"; 
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			int receiptLineID=0;
			if (rs.next()){
				receiptLineID=rs.getInt(1);
			}			
			if (!rs.isClosed())
				rs.close();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			
			return receiptLineID; 
}
public void addOrderPaymentOwe(String hn,int receiptId,int orderline_id,double payment_amount, double owe_amount) throws IOException, Exception{
	
	String SQL ="INSERT INTO order_payment_owe(hn,receipt_id,order_line_id,payment_amount,owe_amount) "
				+ "VALUES ('"+hn+"',"+receiptId+","+orderline_id+","+payment_amount+","+owe_amount+")"; 
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		 		 
		if (!pStmt.isClosed())
			pStmt.close();
		if (!conn.isClosed())
			conn.close(); 
}
public void addOrderOwe(String hn,int orderId,int orderlineID,double owe) throws IOException, Exception{
	
	String SQL ="INSERT INTO order_owe(hn,order_id,ref_id,owe) "
				+ "VALUES ('"+hn+"',"+orderId+","+orderlineID+","+owe+")"; 
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		 		 
		if (!pStmt.isClosed())
			pStmt.close();
		if (!conn.isClosed())
			conn.close();

}
public void updateOwe_StatusPayment(int owe_id) throws IOException, Exception{
	 
	  String SQL ="UPDATE order_receipt_owe "
					+ "SET update_payment = 't', update_date = now() "
					+ "WHERE id = "+owe_id+" "; 
				 
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		 		 
		if (!pStmt.isClosed())
			pStmt.close();
		if (!conn.isClosed())
			conn.close(); 
}
public void updateTreatmentLine_StatusPayment(FinanceModel fModel) throws IOException, Exception{
	
	/* SQL SERVER
	 * String SQL ="UPDATE t1 set status_payment = 't' "
					+ "FROM treatment_patient_line t1 "
					+ "INNER JOIN treatment_patient t2 on(t2.id = t1.treatment_patient_id) " 
					+ "WHERE t2.id = (SELECT MAX(id) FROM treatment_patient WHERE patient_hn = '"+fModel.getOrder_Hn()+"')  ";*/
	  String SQL ="UPDATE treatment_patient_line a INNER JOIN treatment_patient b ON(b.id = a.treatment_patient_id) "
					+ "SET a.status_payment = 't'  "
					+ "WHERE b.id = (SELECT MAX(id) FROM treatment_patient WHERE patient_hn = '"+fModel.getOrder_Hn()+"') "
					+ "AND a.treatment_id = '"+fModel.getProduct_id()+"' "; 
				 
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		 		 
		if (!pStmt.isClosed())
			pStmt.close();
		if (!conn.isClosed())
			conn.close(); 
}
public void updateProductLine_StatusPayment(FinanceModel fModel) throws IOException, Exception{
	
		String SQL ="UPDATE treatment_patient_medicine a INNER JOIN treatment_patient b ON(b.id = a.treatment_patient_id) "
				+ "SET a.status_payment = 't'  "
				+ "WHERE b.id = (SELECT MAX(id) FROM treatment_patient WHERE patient_hn = '"+fModel.getOrder_Hn()+"') "
				+ "AND a.product_id = '"+fModel.getProduct_id()+"' "; 
	  
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();
		 		 
		if (!pStmt.isClosed())
			pStmt.close();
		if (!conn.isClosed())
			conn.close(); 
}
	public int addOrderline(FinanceModel fModel,int type,int isfree) throws IOException, Exception{
		
		String SQL = "";
				if(type == 7 && isfree ==0) {
					SQL+="INSERT INTO order_line(order_id,product_id,product_type,qty,price,"
							+ "amount_untaxed,amount_tax,disdoc_disbaht,branch_disbaht,amount_total,"
							+ "discount,hn,free_status,recall_status,homecall_status_timer,surf,tooth,"
							+ "tooth_type_id,df,pay_amount,status"
							+ ") VALUES "
							
								+ "('"+fModel.getOrder_ID()
								+"','"+fModel.getProduct_id()
								+"','"+type
								+"','"+fModel.getOr_qty()
								+"','"+fModel.getOrderLine_price()
								+"','"+fModel.getOr_amount_untaxed()
								+"','"+fModel.getOr_amount_tax()
								+"','"+fModel.getOr_doctor_disbaht_total()
								+"','"+fModel.getOr_branch_disbaht_total()
								+"','"+fModel.getOr_amount_total()
								+"','"+fModel.getOr_discount_total()
								+"','"+fModel.getOrder_Hn()
								+"','"+isfree
								+"','"+fModel.getOrderLine_recall()
								+"','"+fModel.getOrderLine_homecall()
								+"','"+fModel.getOrderLine_surf()
								+"','"+fModel.getOrderLine_tooth()
								+"','"+fModel.getOrderLine_toothTypeID()
								+"','"+fModel.getOl_df()
								+"','"+fModel.getOr_pay_amount_total()
								+"','1')"; 
				}else if(type == 1 && isfree ==0) {
					SQL+="INSERT INTO order_line(order_id,product_id,product_type,qty,price,"
							+ "amount_untaxed,amount_tax,disdoc_disbaht,branch_disbaht,amount_total,"
							+ "discount,hn,pay_amount,free_status,"
							+ "status"
							+ ") VALUES "
							
								+ "('"+fModel.getOrder_ID()
								+"','"+fModel.getProduct_id()
								+"','"+type
								+"','"+fModel.getOr_qty()
								+"','"+fModel.getOrderLine_price()
								+"','"+fModel.getOr_amount_untaxed()
								+"','"+fModel.getOr_amount_tax()
								+"','0"
								+"','"+fModel.getOr_branch_disbaht_total()
								+"','"+fModel.getOr_amount_total()
								+"','"+fModel.getOr_discount_total()
								+"','"+fModel.getOrder_Hn()
								+"','"+isfree
								+"','"+fModel.getOr_pay_amount_total()
								+"','1')"; 
				}else if(type == 2 && isfree ==0) {
					SQL+="INSERT INTO order_line(order_id,product_id,product_type,qty,price,"
							+ "amount_untaxed,amount_tax,disdoc_disbaht,branch_disbaht,amount_total,"
							+ "discount,hn,free_status,"
							+ "status"
							+ ") VALUES "
							
								+ "('"+fModel.getOrder_ID()
								+"','"+fModel.getProduct_id()
								+"','"+type
								+"','"+fModel.getOr_qty()
								+"','"+fModel.getOrderLine_price()
								+"','"+fModel.getOr_amount_untaxed()
								+"','"+fModel.getOr_amount_tax()
								+"','0"
								+"','"+fModel.getOr_branch_disbaht_total()
								+"','"+fModel.getOr_amount_total()
								+"','"+fModel.getOr_discount_total()
								+"','"+fModel.getOrder_Hn()
								+"','"+isfree
								+"','"+fModel.getOr_pay_amount_total()
								+"','1')"; 
				}
				if(isfree == 1) {
					SQL+="INSERT INTO order_line(order_id,product_id,product_type,qty,price,"
							+ "amount_untaxed,amount_tax,disdoc_disbaht,branch_disbaht,amount_total,"
							+ "discount,hn,free_status,"
							+ "status"
							+ ") VALUES "
							
								+ "('"+fModel.getOrder_ID()
								+"','"+fModel.getProduct_id()
								+"','"+type
								+"','"+fModel.getOr_qty()
								+"','0"
								+"','0"
								+"','0"
								+"','0"
								+"','0"
								+"','0"
								+"','0"
								+"','"+fModel.getOrder_Hn()
								+"','"+isfree+"','1')";
				}
				
			/*conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();*/

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			int order_line=0;
			if (rs.next()){
				order_line=rs.getInt(1);
			}			
			if (!rs.isClosed())
				rs.close();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			
			return order_line; 
		}
	
	public void addOrderAssistant(FinanceModel fModel) throws IOException, Exception{
		
		String SQL="INSERT INTO order_assistant(order_order_id,emp_id,emp_prename,emp_firstname_th,emp_lastname_th,"
							+ "emp_firstname_en,emp_lastname_en"
							+ ")  SELECT '"+fModel.getOrder_ID()+"' ,employee.emp_id,pre_name.pre_name_th,employee.first_name_th,employee.last_name_th,"
							+ "employee.first_name_en,employee.last_name_en FROM treatment_assistant "
							+ "INNER JOIN employee ON treatment_assistant.emp_id = employee.emp_id "
							+ "INNER JOIN pre_name ON employee.pre_name_id = pre_name.pre_name_id "
							+ "WHERE treatment_assistant.treatment_patient_id = '"+fModel.getOrder_treatpatID()+"' ";


				
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		
		}
		public void addReceiptChannel(int orderId, int receiptId, String channel_id, double amount) throws IOException, Exception{
		
		String SQL ="INSERT INTO order_payment_channel_receipt(order_id,receipt_id,channel_id,amount) VALUES " 
					+ "("+orderId+","+receiptId+",'"+channel_id+"',"+amount+")"; 

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();  
		}
	public void addOrderChannelReceipt(FinanceModel fModel,int receiptId) throws IOException, Exception{
		
		String SQL ="INSERT INTO order_payment_channel_receipt(order_id,receipt_id,channel_id,amount) VALUES "
							
								+ "('"+fModel.getOrder_ID()
								+"',"+receiptId
								+",'"+fModel.getChannel_id()
								+"','"+fModel.getChannel_amount() 
								+"')";  
				
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close(); 
		}
	public void addOrderChannel(FinanceModel fModel) throws IOException, Exception{
		
		String SQL ="INSERT INTO order_payment_channel(order_id,channel_id,amount,ref1"
							+ ") VALUES "
							
								+ "('"+fModel.getOrder_ID()
								+"','"+fModel.getChannel_id()
								+"','"+fModel.getChannel_amount()
								+"','"+fModel.getRef1()
								+"')"; 

				
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close(); 
		}
	public int getTreatment_SocialSecurityTreatment(List treatmentID){
		int getTreatment = 0;
		boolean checktreatment = false;
		try {
		Connection conn = agent.getConnectMYSql();
		
		for(int i=0; i<treatmentID.size(); i++) {
			String sql ="SELECT tm.id "
					+ "FROM treatment_master tm "
					+ "WHERE tm.id = '"+treatmentID.get(i)+"' AND tm.is_social_security = '1' ";
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				getTreatment = rs.getInt("id"); 
				checktreatment = true;
			}
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close();
			
			if(checktreatment) i += treatmentID.size();
		} 
			
			if(!conn.isClosed()) conn.close();		
			
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return getTreatment;
	}	
	public List<FinanceModel> getOrder_list_treament(String order_id){ 
		List<FinanceModel> orderlineList = new ArrayList<FinanceModel>();
		String sql = "SELECT "
				+ "a.id as orderid,a.hn as ahn,a.pat_pre_name,a.pat_firstname_th,a.pat_lastname_th,a.pat_firstname_en,a.pat_lastname_en,a.doctor_id "
				+ ",a.doctor_pre_name,a.doctor_firstname_th,a.doctor_lastname_th,a.doctor_firstname_en,a.doctor_lastname_en "
				+ ",a.emp_id,a.emp_pre_name,a.emp_firstname_th,a.emp_lastname_th,a.emp_firstname_en,a.emp_lastname_en "
				+ ",a.pat_checkin_room,a.branch_id,a.sub_contact_id,a.amount_untaxed,a.amount_tax " 
				+ ",a.doctor_disbaht_total,a.branch_disbaht_total,a.discount_total,a.discount_type,a.discount_ref "
				+ ",a.amount_total,a.pay_amount_total,a.remain_amount_total,a.status,a.create_date,a.create_date,a.update_date "
				
				+ ",b.id as orderlineid,b.order_id,b.product_id,b.product_type,b.qty,b.price,b.amount_untaxed,b.amount_tax,b.amount_total,b.disdoc_disbaht "
				+ ",b.branch_disbaht,b.discount,b.hn,b.remain_amount,b.pay_amount,b.free_status,b.recall_status,b.homecall_status_timer"
				+ ",b.homecall_remark,b.surf,b.tooth,b.tooth_type_id,b.status,b.update_date,b.df "
				
				+ ",tm.nameth as treatname, tm.id as treatid "
				//+ ",((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht))as total "
				+ ",((b.qty*b.price)-(sum(b.discount)+sum(b.disdoc_disbaht)+sum(b.branch_disbaht)))as total "
				//+ ",ifnull(owe,0)as owe_total "
				+ ",ifnull(c.owe,0)as owe_total "
				//+ ",ifnull(d.pay_sso,0)as pay_sso "
				+ ",ifnull(sum(d.pay_sso),0)as pay_sso "
				//+ ",ifnull(d.pay_amount,0)as payment_amount "
				+ ",ifnull(sum(CASE WHEN d.pay_sso > 0 THEN d.pay_sso ELSE d.pay_amount END),0)as payment_amount "
				//+ ",(((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht)) "
				+ ",(((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht)) "
				//+ "-(ifnull(owe,0)+ifnull(d.pay_amount,0)+ifnull(d.pay_sso,0))) as can_payment "
				+ "-(ifnull(owe,0)+ifnull(sum(d.pay_amount),0)+ifnull(sum(d.pay_sso),0))) as can_payment  "
				+ ",max(d.paylast_type)as typelastsave "
				
				+ "FROM order_order a "
				+ "inner join order_line b on(b.order_id = a.id) " 
				+ "left join order_owe c on(c.ref_id = b.id) " 
				+ "left join order_line_receipt d on(d.orderline_id = b.id and d.product_type = b.product_type) "
				+ "inner join treatment_master tm ON (tm.id = b.product_id) "
				+ "where ";
		
		if(order_id!="") sql += "a.id = '"+order_id+"' and b.product_type = 7  ";
		
		sql += "group by a.id,b.id order by b.id ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				
				if(rs.getString("treatid")!=null&&rs.getDouble("can_payment")>0.00||rs.getDouble("owe_total")>0.00) { //
				
					if(rs.getString("typelastsave")==null||rs.getString("typelastsave").equals("f")) {
					
						FinanceModel financeModel = new FinanceModel();
						
						financeModel.setOrder_ID(rs.getInt("orderid"));
						financeModel.setOrderLine_ID(rs.getInt("orderlineid"));
						
						financeModel.setOrder_Hn(rs.getString("ahn"));
						financeModel.setProduct_id(rs.getString("product_id"));
						financeModel.setOrderLine_TreatID(rs.getInt("treatid"));
						financeModel.setOrderLine_treatName(rs.getString("treatname")); 
						financeModel.setOrderLine_price(rs.getDouble("price"));
						financeModel.setDiscount(rs.getDouble("discount"));
						financeModel.setDisdoc_disbaht(rs.getDouble("disdoc_disbaht"));
						financeModel.setBranch_disbaht(rs.getDouble("branch_disbaht"));
						financeModel.setOrderLine_homecall(rs.getString("homecall_status_timer"));
						financeModel.setOrderLine_recall(rs.getString("recall_status"));
						  
						financeModel.setOrderLine_surf(rs.getString("surf"));
						financeModel.setOrderLine_tooth(rs.getString("tooth"));
						financeModel.setOrderLine_toothTypeID(rs.getInt("tooth_type_id"));
						
						financeModel.setOr_branch_disbaht_total(rs.getDouble("total"));
						financeModel.setOr_owe(rs.getDouble("owe_total"));
						financeModel.setPay_sso(rs.getDouble("pay_sso"));
						financeModel.setOr_pay_amount_total(rs.getDouble("payment_amount"));
						financeModel.setCan_payment(rs.getDouble("can_payment")); 
						  
						orderlineList.add(financeModel);
					}
				}
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
			
		return orderlineList;
	}
	public List<FinanceModel> getOrder_list_medicine(String order_id){ 
		List<FinanceModel> listtreatpatmedicine = new ArrayList<FinanceModel>();
		String sql = "SELECT "
				+ "a.id as orderid,a.hn as ahn,a.pat_pre_name,a.pat_firstname_th,a.pat_lastname_th,a.pat_firstname_en,a.pat_lastname_en,a.doctor_id "
				+ ",a.doctor_pre_name,a.doctor_firstname_th,a.doctor_lastname_th,a.doctor_firstname_en,a.doctor_lastname_en "
				+ ",a.emp_id,a.emp_pre_name,a.emp_firstname_th,a.emp_lastname_th,a.emp_firstname_en,a.emp_lastname_en "
				+ ",a.pat_checkin_room,a.branch_id,a.sub_contact_id,a.amount_untaxed,a.amount_tax " 
				+ ",a.doctor_disbaht_total,a.branch_disbaht_total,a.discount_total,a.discount_type,a.discount_ref "
				+ ",a.amount_total,a.pay_amount_total,a.remain_amount_total,a.status,a.create_date,a.create_date,a.update_date "
				
				+ ",b.id as orderlineid,b.order_id,b.product_id,b.product_type,b.qty,b.price,b.amount_untaxed,b.amount_tax,b.amount_total,b.disdoc_disbaht "
				+ ",b.branch_disbaht,b.discount,b.hn,b.remain_amount,b.pay_amount,b.free_status,b.recall_status,b.homecall_status_timer"
				+ ",b.homecall_remark,b.surf,b.tooth,b.tooth_type_id,b.status,b.update_date,b.df "
				
				+ ",pp.product_name as product_name " 
				+ ",b.qty*b.price as med_total "
				+ ",((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht))as total "
				+ ",ifnull(c.owe,0)as owe_total "
				+ ",ifnull(sum(d.pay_amount),0)as payment_amount "
				+ ",(((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht)) " 
				+ "-(ifnull(c.owe,0)+ifnull(sum(d.pay_amount),0))) as can_payment "
				+ ",max(d.paylast_type)as typelastsave "         
				
				+ "FROM order_order a "
				+ "inner join order_line b on(b.order_id = a.id) " 
				+ "left join order_owe c on(c.ref_id = b.id) " 
				+ "left join order_line_receipt d on(d.orderline_id = b.id and d.product_type = b.product_type) "
				+ "left join pro_product pp ON (pp.product_id = b.product_id) "
				+ "where ";
		
		if(order_id!="") sql += "a.id = '"+order_id+"' and b.product_type = 1 ";
		
		sql += "group by a.id,b.id order by b.id ";
		
		try {   
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				
				if(rs.getString("product_id")!=null&&rs.getDouble("can_payment")>0.00||rs.getDouble("owe_total")>0.00) { //&&rs.getDouble("can_payment")>0.00
					
					if(rs.getString("typelastsave")==null||rs.getString("typelastsave").equals("f")) {
						
						FinanceModel financeModel = new FinanceModel(); 
						financeModel.setOrder_ID(rs.getInt("orderid"));
						financeModel.setOrderLine_ID(rs.getInt("orderlineid"));
						
						financeModel.setProduct_id(rs.getString("product_id")); 
						financeModel.setProduct_name(rs.getString("product_name")); 
						financeModel.setOr_qty(rs.getDouble("qty"));
						financeModel.setOrderLine_price(rs.getDouble("price"));
						financeModel.setMed_total(rs.getDouble("med_total"));
						financeModel.setDisdoc_disbaht(rs.getDouble("disdoc_disbaht"));
						financeModel.setBranch_disbaht(rs.getDouble("branch_disbaht"));
						financeModel.setOr_branch_disbaht_total(rs.getDouble("total"));
					 
						financeModel.setOr_owe(rs.getDouble("owe_total"));
						
						financeModel.setOr_pay_amount_total(rs.getDouble("payment_amount"));
						financeModel.setCan_payment(rs.getDouble("can_payment"));
						  
						listtreatpatmedicine.add(financeModel);
					}
				}
				 
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
			
		return listtreatpatmedicine;
	}
	public List<FinanceModel> getOrder_list_product(String order_id){ 
		List<FinanceModel> orderlineList = new ArrayList<FinanceModel>();
		String sql = "SELECT "
				+ "a.id as orderid,a.hn as ahn,a.pat_pre_name,a.pat_firstname_th,a.pat_lastname_th,a.pat_firstname_en,a.pat_lastname_en,a.doctor_id "
				+ ",a.doctor_pre_name,a.doctor_firstname_th,a.doctor_lastname_th,a.doctor_firstname_en,a.doctor_lastname_en "
				+ ",a.emp_id,a.emp_pre_name,a.emp_firstname_th,a.emp_lastname_th,a.emp_firstname_en,a.emp_lastname_en "
				+ ",a.pat_checkin_room,a.branch_id,a.sub_contact_id,a.amount_untaxed,a.amount_tax " 
				+ ",a.doctor_disbaht_total,a.branch_disbaht_total,a.discount_total,a.discount_type,a.discount_ref "
				+ ",a.amount_total,a.pay_amount_total,a.remain_amount_total,a.status,a.create_date,a.create_date,a.update_date "
				
				+ ",b.id as orderlineid,b.order_id,b.product_id,b.product_type,b.qty,b.price,b.amount_untaxed,b.amount_tax,b.amount_total,b.disdoc_disbaht "
				+ ",b.branch_disbaht,b.discount,b.hn,b.remain_amount,b.pay_amount,b.free_status,b.recall_status,b.homecall_status_timer"
				+ ",b.homecall_remark,b.surf,b.tooth,b.tooth_type_id,b.status,b.update_date,b.df "
				
				+ ",pp.product_name as product_name " 
				+ ",b.qty*b.price as med_total "
				+ ",((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht))as total "
				+ ",ifnull(c.owe,0)as owe_total "
				+ ",ifnull(sum(d.pay_amount),0)as payment_amount "
				+ ",(((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht))-ifnull(sum(d.pay_amount),0)) as can_payment  " 
				+ ",max(d.paylast_type)as typelastsave "
				
				+ "FROM order_order a "
				+ "inner join order_line b on(b.order_id = a.id) " 
				+ "left join order_owe c on(c.ref_id = b.id) " 
				+ "left join order_line_receipt d on(d.orderline_id = b.id and d.product_type = b.product_type) "
				+ "left join pro_product pp ON (pp.product_id = b.product_id) "
				+ "where ";
		
		if(order_id!="") sql += "a.id = '"+order_id+"' and b.product_type = 2 ";
		
		sql += "order by b.id ";
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				
				if(rs.getString("product_id")!=null&&rs.getDouble("can_payment")>0.00||rs.getDouble("owe_total")>0.00) {
					
					if(rs.getString("typelastsave")==null||rs.getString("typelastsave").equals("f")) {
						
						FinanceModel financeModel = new FinanceModel();
						financeModel.setOrder_ID(rs.getInt("orderid"));
						financeModel.setOrderLine_ID(rs.getInt("orderlineid"));
						
						financeModel.setProduct_id(rs.getString("product_id")); 
						financeModel.setProduct_name(rs.getString("product_name")); 
						financeModel.setOr_qty(rs.getDouble("qty"));
						financeModel.setOrderLine_price(rs.getDouble("price"));
						financeModel.setMed_total(rs.getDouble("med_total"));
						financeModel.setDisdoc_disbaht(rs.getDouble("disdoc_disbaht"));
						financeModel.setBranch_disbaht(rs.getDouble("branch_disbaht"));
						financeModel.setOr_branch_disbaht_total(rs.getDouble("total"));
						
						financeModel.setOr_owe(rs.getDouble("owe_total"));
						
						financeModel.setOr_pay_amount_total(rs.getDouble("payment_amount"));
						financeModel.setCan_payment(rs.getDouble("can_payment"));
						  
						orderlineList.add(financeModel);
					}
				} 
				
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
			
		return orderlineList;
	}
	public List<FinanceModel> getOrder_list_receipt(String order_id){ 
		List<FinanceModel> orderreceiptlist = new ArrayList<FinanceModel>();
		String sql = "SELECT a.order_id,b.receipt_id "
				//+ ",(CASE WHEN b.receipt_type = 1 THEN 'ประกันสังคม' ELSE 'ปกติ' END) as receipt_typename "
				+ ",receipt_type as receipt_typename " 
				//+ ",COUNT(a.id) as countrow " 
				
				+ "FROM order_order_receipt a "
				+ "inner join order_line_receipt b on(b.receipt_id = a.id) " 
				+ "left join pro_product pp ON (pp.product_id = b.product_id) " 
				+ "where ";
		
		if(order_id!="") sql += "a.order_id = '"+order_id+"' ";
		
		sql += "group by a.id order by b.id ";
		
		int countrow = 1;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				FinanceModel financeModel = new FinanceModel();
				financeModel.setOrder_ID(rs.getInt("order_id"));
				financeModel.setReceipt_id(rs.getInt("receipt_id"));
				financeModel.setCountrow(countrow);
				financeModel.setReceipt_typename(rs.getString("receipt_typename")); 
				  
				orderreceiptlist.add(financeModel);
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
	public List<FinanceModel> getChannel_Pay(String order_id){ 
		List<FinanceModel> channelpaylist = new ArrayList<FinanceModel>();
		String sql = "SELECT a.channel_id,(a.amount-IFNULL(sum(b.amount),0))as amount  " 
				
				+ "FROM order_payment_channel a "
				+ "left join order_payment_channel_receipt b on(b.order_id = a.order_id and b.channel_id = a.channel_id) "  
				+ "where ";
		
		if(order_id!="") sql += "a.order_id = '"+order_id+"' ";
		
		sql += "group by a.id order by a.channel_id ";
		 
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				FinanceModel financeModel = new FinanceModel();
				financeModel.setChannel_id(rs.getString("channel_id"));
				financeModel.setAmount_channel(rs.getString("amount"));
				 
				channelpaylist.add(financeModel); 
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
			
		return channelpaylist;
	}
	public String getOrderID(String hn){ 
		String order_id = ""; 
		
		String sql = "SELECT "
				+"a.id " 
				+"FROM "
				+"order_order a " 
				+"where a.hn = "+hn+" order by id desc limit 1 "; 
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){ 
				order_id = rs.getString("id"); 
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
			
		return order_id;
	}
	public double getOrderDoctorPrice(int treatment_id,int order_doctor_id, double amount_doctor){ 
		double doctor_price = 0; 
		
		String sql = "SELECT a.df_percent,a.df_baht,a.price_lab " 
				+"FROM "
				+"doctor_position_treatment a " 
				+"where a.treatment_id = "+treatment_id+" and a.doc_position_id = "+order_doctor_id+" "
				+ "and a.df_percent is not null and a.df_baht is not null "; 
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){ 
				if(rs.getDouble("df_baht")>0) {
					doctor_price = rs.getDouble("df_baht"); 
				}else {
					doctor_price = rs.getDouble("df_percent"); 
					doctor_price = (amount_doctor/100)*doctor_price;
				}
				
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
			
		return doctor_price;
	}
	public void addOrderPaymentPrice(int order_id, int receiptId, int treatment_id, int doctor_id, int product_id, double price,String type_payment) throws IOException, Exception{
		
		String SQL="INSERT INTO order_payment_type(order_id,receipt_id,treatment_id,dorctor_id,product_id,price,create_date,type)  " 
							+ "VALUES("+order_id+","+receiptId+","+treatment_id+","+doctor_id+","+product_id+","+price+",now(),'"+type_payment+"') "; 
				
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		
		}
	public void updateTreatment_StatusWork(int order_id) throws IOException, Exception{
		
		  String SQL ="UPDATE treatment_patient "
						+ "SET status_work = 1  "
						+ "WHERE id = '"+order_id+"' "; 
					 
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			 		 
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close(); 
	}
	public boolean checkOweOrder(int order_id){
		boolean check = false;
		
		try {
		Connection conn = agent.getConnectMYSql();
		
		 
			String sql ="SELECT order_id "
					+ "FROM order_owe "
					+ "WHERE order_id = "+order_id+" group by order_id ";
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				check = true;				
			}
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close(); 
			if(!conn.isClosed()) conn.close();		
			
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return check;
	}
	public int checkOweReceipt(int receipt_id){
		int check = 0;
		
		try {
		Connection conn = agent.getConnectMYSql();
		
		 
			String sql ="SELECT receipt_id "
					+ "FROM order_receipt_owe "
					+ "WHERE receipt_id = "+receipt_id+" group by receipt_id ";
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()){
				check++;		
			}
			if(!rs.isClosed()) rs.close();
			if(!stmt.isClosed()) stmt.close(); 
			if(!conn.isClosed()) conn.close();		
			
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return check;
	}
	public String getPatientOrderID(String patient_hn){ 
		String patient_order_id = ""; 
		
		String sql = "SELECT "
				+"a.id " 
				+"FROM "
				+"treatment_patient a " 
				+"where a.patient_hn = "+patient_hn+" order by id desc limit 1 "; 
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){ 
				patient_order_id = rs.getString("id"); 
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
			
		return patient_order_id;
	}
	public List<FinanceModel> getOrder_list_receipt_owe(String order_id,String receipt_id){ 
		List<FinanceModel> orderreceiptlist = new ArrayList<FinanceModel>();
		String sql = "SELECT a.order_id,b.receipt_id "
				//+ ",(CASE WHEN b.receipt_type = 1 THEN 'ประกันสังคม' ELSE 'ปกติ' END) as receipt_typename "
				+ ",receipt_type as receipt_typename " 
				//+ ",COUNT(a.id) as countrow " 
				
				+ "FROM order_order_receipt a "
				+ "inner join order_line_receipt b on(b.receipt_id = a.id) " 
				+ "left join pro_product pp ON (pp.product_id = b.product_id) " 
				+ "where a.order_id = '"+order_id+"' and a.id = '"+receipt_id+"' ";
		
		sql += "group by a.id order by b.id ";
		
		int countrow = 1;
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while(rs.next()){
				FinanceModel financeModel = new FinanceModel();
				financeModel.setOrder_ID(rs.getInt("order_id"));
				financeModel.setReceipt_id(rs.getInt("receipt_id"));
				financeModel.setCountrow(countrow);
				financeModel.setReceipt_typename(rs.getString("receipt_typename")); 
				  
				orderreceiptlist.add(financeModel);
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
