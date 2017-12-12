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
import com.smict.person.model.BrandModel;
import com.smict.person.model.DepositModel;
import com.smict.person.model.OweModel;
import com.smict.promotion.model.PromotionModel;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class OweData
{
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	/**
	 * Chunking all brand table
	 * @author anubissmile
	 * @return List<BrandModel>
	 */
public List<OweModel> getOwe(String hn) throws Exception{
	
	String SQL = "SELECT a.id, a.hn, a.receipt_id, a.branch_id, a.create_date "
			+ "FROM order_receipt_owe a "
			+ "WHERE a.hn = '"+hn+"' and a.update_payment IS NULL group by a.receipt_id ";

	List<OweModel> oweList = new ArrayList<OweModel>(); 
	agent.connectMySQL();
	agent.exeQuery(SQL);
	if(agent.size() > 0){
		try {
			ResultSet rs = agent.getRs();				
			while(rs.next()){
				OweModel oweModel = new OweModel();
				oweModel.setOwe_id(rs.getInt("id"));
				oweModel.setHn(rs.getString("hn"));
				oweModel.setBranch_id(rs.getString("branch_id")); 
				oweModel.setReceipt_id(rs.getInt("receipt_id"));
				oweModel.setOwe_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("create_date").substring(0, 10),true)
						+" "+rs.getString("create_date").substring(11, 19)); 
				oweList.add(oweModel);
			}
			agent.disconnectMySQL();
			return oweList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	agent.disconnectMySQL();
	return null;
} 
	
public void addDeposit(DepositModel dpModel) throws IOException, Exception{
		
		String SQL ="INSERT INTO deposit_money_transaction(deposit_type,deposit_by,deposit_date,remark,old_money,transfer_money,total_money,hn,branch_id,type_money) "
								+ "VALUES ('"+dpModel.getDeposit_type()+"','"+dpModel.getDeposit_by()+"',now(),'"+dpModel.getRemark()+"' "
								+ ","+dpModel.getOld_money()+","+dpModel.getTransfer_money()+","+dpModel.getTotal_money()+" "
								+ ",'"+dpModel.getHn()+"','"+dpModel.getBranch_id()+"','"+dpModel.getType_money()+"')";
								 
		conn = agent.getConnectMYSql();
		pStmt = conn.prepareStatement(SQL);
		pStmt.executeUpdate();

		if (!pStmt.isClosed())
			pStmt.close();
		if (!conn.isClosed())
			conn.close(); 
} 
public double GetOldMoney(String hn){
	
	String sqlQuery = "select total_money as money from deposit_money_transaction a " +
					  "WHERE a.hn = '"+hn+"' order by id desc limit 1 ";
	double ResultInt = 0;
	
	try  {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		if(rs.next()) {
			ResultInt = rs.getInt("money"); 
		}
	} 
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return ResultInt;
}    
public List<FinanceModel> getOrder_list_treament(String own_id,String status_producttype){ 
	List<FinanceModel> orderlineList = new ArrayList<FinanceModel>();
	String sql = "SELECT a.id as owe_id "
			+ ",a.receipt_id,c.order_id,a.hn as ahn,a.pat_pre_name,a.pat_firstname_th,a.pat_lastname_th,a.pat_firstname_en,a.pat_lastname_en,a.doctor_id "
			+ ",a.doctor_pre_name,a.doctor_firstname_th,a.doctor_lastname_th,a.doctor_firstname_en,a.doctor_lastname_en "
			+ ",a.emp_id,a.emp_pre_name,a.emp_firstname_th,a.emp_lastname_th,a.emp_firstname_en,a.emp_lastname_en "
			+ ",a.pat_checkin_room,a.branch_id,a.sub_contact_id,a.amount_untaxed,a.amount_tax " 
			+ ",a.doctor_disbaht_total,a.branch_disbaht_total,a.discount_total,a.discount_type,a.discount_ref "
			+ ",a.amount_total,a.pay_amount_total,a.remain_amount_total,a.status,a.create_date,a.create_date,a.update_date "
			
			+ ",b.orderline_id,b.owe_id,b.product_id,b.product_type,b.qty,b.price,b.amount_untaxed,b.amount_tax,b.amount_total,b.disdoc_disbaht "
			+ ",b.branch_disbaht,b.discount,b.hn,b.remain_amount,b.pay_amount,b.free_status,b.recall_status,b.homecall_status_timer"
			+ ",b.homecall_remark,b.surf,b.tooth,b.tooth_type_id,b.status,b.update_date,b.df "
			
			+ ",tm.nameth as treatname, tm.id as treatid "
			//+ ",((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht))as total "
			+ ",((b.qty*b.price)-(sum(b.discount)+sum(b.disdoc_disbaht)+sum(b.branch_disbaht)))as total " 
			//+ ",(((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht)) " 
			+ ",(ifnull(b.owe_amount,0)) as can_payment "
			+ ",(ifnull(e.payment_amount,0)) as payment_amount  "
			
			+ "FROM order_receipt_owe a "
			+ "inner join order_line_receipt_owe b on(b.owe_id = a.id) "  
			+ "inner join treatment_master tm ON (tm.id = b.product_id) "
			+ "left join order_order_receipt c on(c.id = a.receipt_id) " 
			//+ "inner join order_line_receipt_owe g on(g.orderline_id = b.orderline_id) " 
			+ "left join order_owe d on(d.ref_id = b.orderline_id) " 
			+ "left join order_payment_owe e on(e.receipt_id = b.receipt_id) "
			
			+ "where ";
	
	if(own_id!="") sql += "a.id = '"+own_id+"' and b.product_type = '"+status_producttype+"' ";
	
	sql += "group by a.id,b.id order by b.id ";
	
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		
		while(rs.next()){
			
			if(rs.getString("treatid")!=null&&rs.getDouble("can_payment")>0.00&&rs.getDouble("payment_amount")==0) {
			
				FinanceModel financeModel = new FinanceModel();
				financeModel.setOwe_id(rs.getInt("owe_id"));
				financeModel.setReceipt_id(rs.getInt("receipt_id"));
				financeModel.setOrder_ID(rs.getInt("order_id"));
				financeModel.setOrderLine_ID(rs.getInt("orderline_id"));
				
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
				//financeModel.setOr_owe(rs.getDouble("owe_total")); 
				//financeModel.setOr_pay_amount_total(rs.getDouble("payment_amount"));
				financeModel.setCan_payment(rs.getDouble("can_payment"));
				  
				orderlineList.add(financeModel);
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
public List<FinanceModel> getOrder_list_product(String own_id,String status_producttype){ 
	List<FinanceModel> orderlineList = new ArrayList<FinanceModel>();
	String sql = "SELECT a.id as owe_id "
			+ ",a.receipt_id,c.order_id,a.hn as ahn,a.pat_pre_name,a.pat_firstname_th,a.pat_lastname_th,a.pat_firstname_en,a.pat_lastname_en,a.doctor_id "
			+ ",a.doctor_pre_name,a.doctor_firstname_th,a.doctor_lastname_th,a.doctor_firstname_en,a.doctor_lastname_en "
			+ ",a.emp_id,a.emp_pre_name,a.emp_firstname_th,a.emp_lastname_th,a.emp_firstname_en,a.emp_lastname_en "
			+ ",a.pat_checkin_room,a.branch_id,a.sub_contact_id,a.amount_untaxed,a.amount_tax " 
			+ ",a.doctor_disbaht_total,a.branch_disbaht_total,a.discount_total,a.discount_type,a.discount_ref "
			+ ",a.amount_total,a.pay_amount_total,a.remain_amount_total,a.status,a.create_date,a.create_date,a.update_date "
			
			+ ",b.orderline_id,b.owe_id,b.product_id,b.product_type,b.qty,b.price,b.amount_untaxed,b.amount_tax,b.amount_total,b.disdoc_disbaht "
			+ ",b.branch_disbaht,b.discount,b.hn,b.remain_amount,b.pay_amount,b.free_status,b.recall_status,b.homecall_status_timer"
			+ ",b.homecall_remark,b.surf,b.tooth,b.tooth_type_id,b.status,b.update_date,b.df "
			
			+ ",tm.product_name, tm.product_id "
			+ ",b.qty*b.price as med_total "
			//+ ",((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht))as total "
			+ ",((b.qty*b.price)-(sum(b.discount)+sum(b.disdoc_disbaht)+sum(b.branch_disbaht)))as total " 
			//+ ",(((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht)) " 
			+ ",(ifnull(b.owe_amount,0)) as can_payment "
			+ ",(ifnull(e.payment_amount,0)) as payment_amount  "
			
			+ "FROM order_receipt_owe a "
			+ "inner join order_line_receipt_owe b on(b.owe_id = a.id) "  
			+ "inner join pro_product tm ON (tm.product_id = b.product_id) "
			+ "left join order_order_receipt c on(c.id = a.receipt_id) " 
			//+ "inner join order_line_receipt_owe g on(g.orderline_id = b.orderline_id) " 
			+ "left join order_owe d on(d.ref_id = b.orderline_id) " 
			+ "left join order_payment_owe e on(e.receipt_id = b.receipt_id) "
			
			+ "where ";
	
	if(own_id!="") sql += "a.id = '"+own_id+"' and b.product_type = '"+status_producttype+"' ";
	
	sql += "group by a.id,b.id order by b.id ";
	
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		
		while(rs.next()){
			
			if(rs.getString("product_id")!=null&&rs.getDouble("can_payment")>0.00&&rs.getDouble("payment_amount")==0) {
			
				FinanceModel financeModel = new FinanceModel();
				financeModel.setOwe_id(rs.getInt("owe_id"));
				financeModel.setReceipt_id(rs.getInt("receipt_id"));
				financeModel.setOrder_ID(rs.getInt("order_id"));
				financeModel.setOrderLine_ID(rs.getInt("orderline_id"));
				
				financeModel.setProduct_id(rs.getString("product_id")); 
				financeModel.setProduct_name(rs.getString("product_name")); 
				financeModel.setOr_qty(rs.getDouble("qty"));
				financeModel.setOrderLine_price(rs.getDouble("price")); 
				financeModel.setMed_total(rs.getDouble("med_total"));
				
				financeModel.setOrder_Hn(rs.getString("ahn")); 
				financeModel.setOrderLine_price(rs.getDouble("price"));
				financeModel.setDiscount(rs.getDouble("discount"));
				financeModel.setDisdoc_disbaht(rs.getDouble("disdoc_disbaht"));
				financeModel.setBranch_disbaht(rs.getDouble("branch_disbaht"));  
				
				financeModel.setOr_branch_disbaht_total(rs.getDouble("total"));
				//financeModel.setOr_owe(rs.getDouble("owe_total")); 
				//financeModel.setOr_pay_amount_total(rs.getDouble("payment_amount"));
				financeModel.setCan_payment(rs.getDouble("can_payment"));
				  
				orderlineList.add(financeModel);
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
public List<FinanceModel> getOrder_list_medicine(String own_id){ 
	List<FinanceModel> listtreatpatmedicine = new ArrayList<FinanceModel>();
	String sql = "SELECT " 
			+ "a.receipt_id,c.order_id,a.hn as ahn,a.pat_pre_name,a.pat_firstname_th,a.pat_lastname_th,a.pat_firstname_en,a.pat_lastname_en,a.doctor_id "
			+ ",a.doctor_pre_name,a.doctor_firstname_th,a.doctor_lastname_th,a.doctor_firstname_en,a.doctor_lastname_en "
			+ ",a.emp_id,a.emp_pre_name,a.emp_firstname_th,a.emp_lastname_th,a.emp_firstname_en,a.emp_lastname_en "
			+ ",a.pat_checkin_room,a.branch_id,a.sub_contact_id,a.amount_untaxed,a.amount_tax " 
			+ ",a.doctor_disbaht_total,a.branch_disbaht_total,a.discount_total,a.discount_type,a.discount_ref "
			+ ",a.amount_total,a.pay_amount_total,a.remain_amount_total,a.status,a.create_date,a.create_date,a.update_date "
			
			+ ",b.orderline_id,b.owe_id,b.product_id,b.product_type,b.qty,b.price,b.amount_untaxed,b.amount_tax,b.amount_total,b.disdoc_disbaht "
			+ ",b.branch_disbaht,b.discount,b.hn,b.remain_amount,b.pay_amount,b.free_status,b.recall_status,b.homecall_status_timer"
			+ ",b.homecall_remark,b.surf,b.tooth,b.tooth_type_id,b.status,b.update_date,b.df "
			
			+ ",pp.product_name as product_name " 
			+ ",b.qty*b.price as med_total "
			//+ ",((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht))as total "
			+ ",((b.qty*b.price)-(sum(b.discount)+sum(b.disdoc_disbaht)+sum(b.branch_disbaht)))as total " 
			//+ ",(((b.qty*b.price)-(b.discount+b.disdoc_disbaht+b.branch_disbaht)) " 
			+ ",(ifnull(b.owe_amount,0)) as can_payment "
			+ ",(ifnull(e.payment_amount,0)) as payment_amount  "
			
			+ "FROM order_receipt_owe a "
			+ "inner join order_line_receipt_owe b on(b.owe_id = a.id) "  
			+ "left join pro_product pp ON (pp.product_id = b.product_id) "
			+ "inner join order_order_receipt c on(c.id = a.receipt_id) " 
			//+ "inner join order_line_receipt_owe g on(g.orderline_id = b.orderline_id) " 
			+ "left join order_owe d on(d.ref_id = b.orderline_id) " 
			+ "left join order_payment_owe e on(e.receipt_id = b.receipt_id) "
			
			+ "where ";
	if(own_id!="") sql += "a.id = '"+own_id+"' and b.product_type = 1 "; 
	
	sql += "group by a.id,b.id order by b.id ";
	
	try {   
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sql);
		
		while(rs.next()){
			
			if(rs.getString("product_id")!=null&&rs.getDouble("can_payment")>0.00) {
				FinanceModel financeModel = new FinanceModel(); 
				financeModel.setReceipt_id(rs.getInt("receipt_id"));
				financeModel.setOrder_ID(rs.getInt("order_id"));
				financeModel.setOrderLine_ID(rs.getInt("orderline_id"));
				
				financeModel.setProduct_id(rs.getString("product_id")); 
				financeModel.setProduct_name(rs.getString("product_name")); 
				financeModel.setOr_qty(rs.getDouble("qty"));
				financeModel.setOrderLine_price(rs.getDouble("price"));
				financeModel.setMed_total(rs.getDouble("med_total"));
				financeModel.setDisdoc_disbaht(rs.getDouble("disdoc_disbaht"));
				financeModel.setBranch_disbaht(rs.getDouble("branch_disbaht"));
				financeModel.setOr_branch_disbaht_total(rs.getDouble("total"));
			  
				financeModel.setCan_payment(rs.getDouble("can_payment"));
				  
				listtreatpatmedicine.add(financeModel);
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
		
}
