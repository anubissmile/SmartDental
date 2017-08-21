package com.smict.promotion.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.IsNull;

import com.smict.person.model.BranchModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.Person;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;
import com.smict.treatment.model.TreatmentModel;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;

public class Promotiondata {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();	
	
	public int addpromotioninsert(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO promotion(name,start_date,end_date,use_condition,billcostover,"
				+ "is_allday,is_alltime,start_time,end_time,"
				+ "is_allsubcontact,is_birthmonth,is_allage,from_age,to_age,is_treatmentcount,is_allbranch,description,status) VALUES "
				
					+ "('"+protionModel.getName()
					+"','"+protionModel.getStart_date()
					+"','"+protionModel.getEnd_date()
					+"','"+protionModel.getUse_condition()
					+"','"+protionModel.getBillcostover()
					+"','"+protionModel.getIs_allday()
					+"','"+protionModel.getIs_alltime()
					+"','"+protionModel.getStart_time()
					+"','"+protionModel.getEnd_time()
					+"','"+protionModel.getIs_allsubcontact()
					+"','"+protionModel.getIs_birthmonth()
					+"','"+protionModel.getIs_allage()
					+"',"+protionModel.getFrom_age()
					+","+protionModel.getTo_age()
					+","+protionModel.getIs_treatmentcount()
					+",'"+protionModel.getIs_allbranch()
					+"','"+protionModel.getPromotion_description()+"','1')"; 

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
	
	public void addpromotionbranchinsert(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO promotion_condition_branch(branch_id,promotion_id,status) VALUES ";
			int i=0;		
				for(String Probranch : protionModel.getProbranchID()){
					if(i>0)
						SQL+=",";
					
				SQL+=	 "('"+Probranch
					+"',"+protionModel.getPromotion_id()
					+",'0') ";
					i++;
				}
					
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
		
		}
	public void addpromotioncontactinsert(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO promotion_condition_subcontact(sub_contact_id,promotion_id,status) VALUES ";
			int i=0;		
				for(int Procontact : protionModel.getSubConID()){
					if(i>0)
						SQL+=",";
					
				SQL+=	 "("+Procontact
					+","+protionModel.getPromotion_id()
					+",'0') ";
					i++;
				}
					
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
		
		}
	public void addpromotionDay(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO promotion_condition_day(day_id,promotion_id,status) VALUES ";
			int i=0;		
				for(String Proday : protionModel.getDayAll()){
					if(i>0)
						SQL+=",";
					
				SQL+=	 "('"+Proday
					+"',"+protionModel.getPromotion_id()
					+",'0') ";
					i++;
				}
					
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
		
		}
	public List<PromotionDetailModel> getListPromotiondetail(int id ){
		
		String sql = "SELECT "
				+ "treatment_master.nameth,promotion_detail.product_type,promotion_detail.discount_baht,promotion_detail.discount_percent "
				+ "FROM "
				+ "promotion "
				+ "INNER JOIN promotion_detail ON promotion.id = promotion_detail.promotion_id "
				+ "INNER JOIN treatment_master ON promotion_detail.product_id = treatment_master.code "
				+ "Where promotion_detail.promotion_id = "+id+" "
				+ "UNION ALL "
				+ "SELECT "
				+ "pro_product.product_name,promotion_detail.product_type,promotion_detail.discount_baht,promotion_detail.discount_percent "
				+ "FROM "
				+ "promotion_detail "
				+ "INNER JOIN pro_product ON pro_product.product_id = promotion_detail.product_id "
				+ "Where promotion_detail.promotion_id = "+id+" ";

		List<PromotionDetailModel> promotiondetaillist = new LinkedList<PromotionDetailModel>();
//		HashMap<String, String> pDetailMap = new HashMap<String, String>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			String pid = "", pName = "";
			
			while (rs.next()) {
				PromotionDetailModel promotiondetailModel = new PromotionDetailModel();
				
				promotiondetailModel.setProduct_type(rs.getString("product_type"));
				promotiondetailModel.setType(rs.getString("nameth"));
				promotiondetailModel.setDiscount_baht(rs.getInt("discount_baht"));
				promotiondetailModel.setDiscount_percent(rs.getInt("discount_percent"));
				promotiondetaillist.add(promotiondetailModel);
				
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return promotiondetaillist;
		}
public List<PromotionModel> getListPromotion(){
		
		String sql = "SELECT "
				+ "pi.id, pi.name, pi.start_date, pi.end_date, "
				+ "pi.start_time, pi.end_time,pi.status,pi.is_alltime "
				+ "FROM "
				+ "promotion AS pi "
				+ "ORDER BY 'pi.start_date' ASC ";
				
		List<PromotionModel> promotionList = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel promotionModel = new PromotionModel();
				
				promotionModel.setPromotion_id(rs.getInt("id"));
				promotionModel.setName(rs.getString("name"));
				promotionModel.setStart_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd/MM/yyyy",rs.getString("start_date"),true));
				promotionModel.setEnd_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd/MM/yyyy",rs.getString("end_date"),true));
				promotionModel.setIs_alltime(rs.getString("is_alltime"));				
				promotionModel.setStart_time(dateUtil.convertDateSpecificationPattern("HH:mm:ss","HH:mm",rs.getString("start_time"),false));
				promotionModel.setEnd_time(dateUtil.convertDateSpecificationPattern("HH:mm:ss","HH:mm",rs.getString("end_time"),false));			
				promotionModel.setStatus_pro(rs.getInt("status"));
				
/*				Promotiondata promoDatadetail = new Promotiondata();
				promotionModel.setPromotiondetailModel(promoDatadetail.getListPromotiondetail(promotionModel.getPromotion_id()));
				*/
				promotionList.add(promotionModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return promotionList;
	}

	public void PromotionDelete(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "DELETE FROM promotion  "
				+ " where id = "+protionModel.getPromotion_id()+" ;"
				
				+ "DELETE FROM promotion_condition_branch  "				
				+ " where promotion_id = "+protionModel.getPromotion_id()+" ;"
				
				+ "DELETE FROM promotion_condition_day  "
				+ " where promotion_id = "+protionModel.getPromotion_id()+" ;"
				
				+ "DELETE FROM promotion_condition_subcontact  "
				+ " where promotion_id = "+protionModel.getPromotion_id()+" ;"
				
				+ "DELETE FROM promotion_manage  "
				+ " where promotion_id = "+protionModel.getPromotion_id()+" ;"
				
				+ "DELETE FROM promotion_detail  "
				+ " where promotion_id = "+protionModel.getPromotion_id()+"";
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		
	}
public List<PromotionModel> getmemberlist(){
		
		String sql = "SELECT "
				+ "promotion_sub_contact.sub_contact_name,promotion_contact.contact_name,promotion_sub_contact.contact_id, "
				+ "promotion_subcontact_type.`name`,promotion_sub_contact.sub_contact_type_id,promotion_sub_contact.address, "
				+ "promotion_sub_contact.sub_contact_id,promotion_sub_contact.status, "
				+ "promotion_sub_contact.sms_piority,promotion_sub_contact.company_name,promotion_sub_contact.amount_default "
				+ "FROM "
				+ "promotion_contact "
				+ "INNER JOIN promotion_sub_contact ON promotion_sub_contact.contact_id = promotion_contact.contact_id "
				+ "LEFT  JOIN promotion_subcontact_type ON promotion_subcontact_type.id = promotion_sub_contact.sub_contact_type_id ";

				
		List<PromotionModel> promotionList = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel promotionModel = new PromotionModel();
				
				promotionModel.setSub_contactid(rs.getString("promotion_sub_contact.sub_contact_id"));
				promotionModel.setSub_contactname(rs.getString("sub_contact_name"));
				promotionModel.setName(rs.getString("promotion_contact.contact_name"));
				promotionModel.setContypeName(rs.getString("promotion_subcontact_type.name"));
				promotionModel.setContact_id(rs.getString("promotion_sub_contact.contact_id"));
				promotionModel.setSub_contact_type_id(rs.getString("promotion_sub_contact.sub_contact_type_id"));
				promotionModel.setStatus_subcontact(rs.getInt("promotion_sub_contact.status"));
				promotionList.add(promotionModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return promotionList;
	}
	public List<PromotionModel> getSubcontactwalletLinelist(String subwalid){
		
		String sql = "SELECT "
				+ "promotion_subcontact_wallet_line.id,promotion_subcontact_wallet_line.subcontact_wallet_id, "
				+ "promotion_subcontact_wallet_line.amount, "
				+ "promotion_subcontact_wallet_line.type,promotion_subcontact_wallet_line.emp_id, "
				+ "promotion_subcontact_wallet_line.create_date,promotion_subcontact_wallet_line.isstatus "
				+ "FROM "
				+ "promotion_subcontact_wallet_line "
				+ "WHERE promotion_subcontact_wallet_line.subcontact_wallet_id = "+subwalid+" "
				+ "AND promotion_subcontact_wallet_line.isstatus = 't' ";
	
				
		List<PromotionModel> promotionList = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel promotionModel = new PromotionModel();
				
				promotionModel.setSub_contact_wallet_lineid(rs.getString("promotion_subcontact_wallet_line.id"));
				promotionModel.setSub_contact_wallet_line_type(rs.getString("promotion_subcontact_wallet_line.type"));
				promotionModel.setSub_contact_wallet_line_emp_id(rs.getString("promotion_subcontact_wallet_line.emp_id"));
				promotionModel.setAmount(rs.getDouble("promotion_subcontact_wallet_line.amount"));
				promotionModel.setSub_wallet_line_date(rs.getString("promotion_subcontact_wallet_line.create_date"));
				promotionModel.setSub_wallet_line_status(rs.getString("promotion_subcontact_wallet_line.isstatus"));
				promotionList.add(promotionModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return promotionList;
	}
public int insertMember(PromotionModel protionModel) throws IOException, Exception{
				if(StringUtils.isEmpty(protionModel.getSms_piority())){
					protionModel.setSms_piority("0");
				}
	String SQL = "INSERT INTO promotion_sub_contact (sub_contact_name,contact_id,sms_piority,status ";
			if(protionModel.getContact_id().equals("2")){
				SQL += ",address,company_name,sub_contact_type_id ";
			}
			if(!StringUtils.isEmpty(protionModel.getSub_contact_type_id())){
				if(protionModel.getSub_contact_type_id().equals("3") ){
					SQL += ",amount_default ";
				}
			}	
			SQL += ") VALUES "
				+ "('"+protionModel.getSub_contactname()+"','"+protionModel.getContact_id()+"' "
				+ ",'"+protionModel.getSms_piority()+"',1 ";
			if(protionModel.getContact_id().equals("2")){
				SQL += ",'"+protionModel.getSub_contact_addr()+"','"+protionModel.getSub_contact_companyName()+"'"
					+ ",'"+protionModel.getSub_contact_type_id()+"' ";
			}
			if(!StringUtils.isEmpty(protionModel.getSub_contact_type_id())){
				if(protionModel.getSub_contact_type_id().equals("3")){
					SQL += ",'"+protionModel.getSub_contact_amount()+"' ";
				}
			}
			SQL += ") ";
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

	public void insertsubcontactWallet(int subID,double amount,String hn){
	
		String SQL ="insert into promotion_subcontact_wallet (sub_contact_id,total_amount,isstatus,patient_hn) "
				+ "VALUES "
				+ " ('"+subID+"','"+amount+"','t','"+hn+"') ";
	
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
	public void updateStatusSubcontact(PromotionModel protionModel){
		
		String SQL ="UPDATE promotion_sub_contact "
				+ "SET "
				+ "status = ";
				if(protionModel.getStatus_subcontact() == 1){
					SQL+="'0' ";
				}else{
					SQL+="'1' ";
				}
				SQL+="WHERE sub_contact_id = "+protionModel.getSub_contactid()+" ";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public PromotionModel getMemberModel(int subID,int subtype){
		
		String sql = "SELECT "
				+ "promotion_sub_contact.sub_contact_id,promotion_sub_contact.sub_contact_name, "
				+ "promotion_sub_contact.contact_id,promotion_sub_contact.sms_piority, "
				+ "promotion_sub_contact.sub_contact_type_id,promotion_sub_contact.address, "
				+ "promotion_sub_contact.amount_default,promotion_sub_contact.company_name, "
				+ "promotion_sub_contact.`status` ";
			if(subtype == 2){
				sql += ",promotion_subcontact_wallet.total_amount,promotion_subcontact_wallet.id ";
			}				
				sql += "FROM "
					+ "promotion_sub_contact ";
			if(subtype == 2){
				sql += "INNER  JOIN promotion_subcontact_wallet ON promotion_sub_contact.sub_contact_id "
					+ "= promotion_subcontact_wallet.sub_contact_id AND  promotion_subcontact_wallet.isstatus = 't' ";
			}					
				sql += "WHERE promotion_sub_contact.sub_contact_id = "+subID+" ";

		PromotionModel promotionModel = new PromotionModel();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
								
				promotionModel.setSub_contactid(rs.getString("promotion_sub_contact.sub_contact_id"));
				promotionModel.setSub_contactname(rs.getString("sub_contact_name"));
				promotionModel.setSub_contact_addr(rs.getString("promotion_sub_contact.address"));
				promotionModel.setSms_piority(rs.getString("promotion_sub_contact.sms_piority"));
				promotionModel.setContact_id(rs.getString("promotion_sub_contact.contact_id"));
				promotionModel.setSub_contact_type_id(rs.getString("promotion_sub_contact.sub_contact_type_id"));
				promotionModel.setStatus_subcontact(rs.getInt("promotion_sub_contact.status"));
				promotionModel.setSub_contact_amount(rs.getDouble("promotion_sub_contact.amount_default"));
				promotionModel.setSub_contact_companyName(rs.getString("promotion_sub_contact.company_name"));
				if(subtype == 2){
					promotionModel.setTotal_amount(rs.getDouble("promotion_subcontact_wallet.total_amount"));
					promotionModel.setSub_contact_walletid(rs.getString("promotion_subcontact_wallet.id"));
				}
				
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return promotionModel;
	}	
	public boolean IsSameMembertype(String subID){
		
		String sql = "SELECT "
				+ "promotion_sub_contact.sub_contact_id,promotion_sub_contact.sub_contact_name, "
				+ "promotion_sub_contact.contact_id,promotion_sub_contact.sms_piority, "
				+ "promotion_sub_contact.sub_contact_type_id,promotion_sub_contact.address, "
				+ "promotion_sub_contact.amount_default,promotion_sub_contact.company_name, "
				+ "promotion_sub_contact.`status` "			
				+ "FROM "
				+ "promotion_sub_contact "				
				+ "WHERE promotion_sub_contact.sub_contact_id = "+subID+" ";
		boolean check = true;
		PromotionModel promotionModel = new PromotionModel();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
								
				promotionModel.setUse_condition(rs.getString("promotion_sub_contact.sub_contact_type_id"));
				if(promotionModel.getUse_condition().equals("2")){
					check =false;
				}
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return check;
	}
	public boolean IsSameWallet(String subID){
		
		String sql = "SELECT "
				+ "sub_contact_id "		
				+ "FROM "
				+ "promotion_subcontact_wallet "				
				+ "WHERE sub_contact_id = "+subID+" ";
		boolean check = true;
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {								
					check =false;
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return check;
	}	
	public void updateisStatusSubcontactWallet(String subID,String ischeck){
		
		String SQL ="UPDATE promotion_subcontact_wallet "
				+ "SET "
				+ "isstatus = '"+ischeck+"' "
				+ "WHERE sub_contact_id = '"+subID+"' ";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void updateSubcontact(PromotionModel protionModel){
		
		if(StringUtils.isEmpty(protionModel.getSms_piority())){
			protionModel.setSms_piority("0");
		}
		
		String SQL ="UPDATE promotion_sub_contact "
						+ "SET "
						+ "sub_contact_name = '"+protionModel.getSub_contactname()+"' "
						+ ",sms_piority = '"+protionModel.getSms_piority()+"' ";
					if(protionModel.getContact_id().equals("2")){
					SQL += ",sub_contact_type_id = '"+protionModel.getSub_contact_type_id()+"' "
						+ ",address = '"+protionModel.getSub_contact_addr()+"' "
						+ ",company_name = '"+protionModel.getSub_contact_companyName()+"' ";
						if(!StringUtils.isEmpty(protionModel.getSub_contact_type_id())){
							if(protionModel.getSub_contact_type_id().equals("3") ){
								SQL += ",amount_default = '"+protionModel.getSub_contact_amount()+"'";
							}
						}
					}
						SQL += "WHERE sub_contact_id = "+protionModel.getSub_contactid()+" ";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void insertSubcontactWalletline(String subwalID,Double totalamount,int type){
		
		String SQL ="INSERT INTO promotion_subcontact_wallet_line "
				+ "(subcontact_wallet_id,amount,emp_id,type,create_date,isstatus) "
				+ "VALUES "
				+ " ( '"+subwalID+"' "
				+ ",'"+totalamount+"' "
				+ ",'"+Auth.user().getEmpUsr()+"' "
				+ ",'"+type+"',now(),'t') ";

		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
	public void AdjustSubcontactWallet(String subwalID,Double amount){
		
		String SQL ="UPDATE promotion_subcontact_wallet "
				+ "SET "
				+ "total_amount = '"+amount+"' "
				+ "WHERE id = '"+subwalID+"' ";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void updateDefaultmoneySubcontact(PromotionModel protionModel){
		
		String SQL ="UPDATE promotion_sub_contact "
				+ "SET "
				+ "amount_default = '"+protionModel.getSub_contact_amount()+"' "
				+ "WHERE sub_contact_id = "+protionModel.getSub_contactid()+" ";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
	public int addgiftcard(PromotionModel giftModel){
		int gift_id=0;
		String SQL ="INSERT INTO giftcard_giftcard "
				+ "(name,description,prefix,numberlenght,start_number, "
				+ "run_count,suffix,default_amount,create_date,start_date,expiredate,status) "
				+ "VALUES "
				+ "('"+giftModel.getGiftcard_name()+"','"+giftModel.getGiftcard_description()+"'"
				+ ",'"+giftModel.getGiftcard_prefix()+"','"+giftModel.getGiftcard_numberlenght()+"'"
				+ ",'"+giftModel.getGiftcard_start_number()+"','"+giftModel.getGiftcard_run_count()+"'"
				+ ",'"+giftModel.getGiftcard_suffix()+"','"+giftModel.getGiftcard_default_amount()+"'"
				+ ",NOW(),'"+giftModel.getGiftcard_start_date()+"','"+giftModel.getGiftcard_expiredate()+"'"
				+ ",'t')";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if (rs.next()){
				gift_id=rs.getInt(1);
			}
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gift_id;
	}	
	public void addgiftcardline(PromotionModel giftModel,String allname){
		try {
			String [] allnumber = allname.split(",");
			String SQL ="INSERT INTO giftcard_line "
					+ "(name,amount,giftcard_id) "
					+ "VALUES ";
					int countnumber=0;
					for(String onemunber : allnumber ){
						if(countnumber>0){
							SQL +=",";
						}
							SQL +="('";
						if(!StringUtils.isEmpty(giftModel.getGiftcard_prefix())){
							SQL+=""+giftModel.getGiftcard_prefix()+"";
						}						
						SQL+=""+onemunber+"";
						if(!StringUtils.isEmpty(giftModel.getGiftcard_suffix())){
							SQL+=""+giftModel.getGiftcard_suffix()+"";
						}
						SQL+= "',"							
							+ "'"+giftModel.getGiftcard_default_amount()+"','"+giftModel.getGiftcard_id()+"') ";
					
						if(countnumber == 999){
							conn = agent.getConnectMYSql();
							pStmt = conn.prepareStatement(SQL);
							pStmt.executeUpdate();		
							if (!pStmt.isClosed())
								pStmt.close();
							if (!conn.isClosed())
								conn.close();
							
						 SQL ="INSERT INTO giftcard_line "
									+ "(name,amount,giftcard_id) "
									+ "VALUES ";
						 countnumber = 0;
						}else{
							countnumber++;
						}
					}
	
				conn = agent.getConnectMYSql();
				pStmt = conn.prepareStatement(SQL);
				pStmt.executeUpdate();
		
						
				if (!pStmt.isClosed())
					pStmt.close();
				if (!conn.isClosed())
					conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
	public List<PromotionModel> getGiftCardList(int giftID){
		
		String sql = "SELECT "
				+ "giftcard_giftcard.id,giftcard_giftcard.`name`,giftcard_giftcard.description, "
				+ "giftcard_giftcard.prefix,giftcard_giftcard.numberlenght,giftcard_giftcard.start_number, "
				+ "giftcard_giftcard.run_count,giftcard_giftcard.suffix,giftcard_giftcard.default_amount, "
				+ "giftcard_giftcard.create_date,giftcard_giftcard.start_date,giftcard_giftcard.expiredate,giftcard_giftcard.`status` ";
			if(giftID != 0){
				sql += ",giftcard_line.id,giftcard_line.`name`,giftcard_line.amount,giftcard_line.giftcard_id ";
			}				
				sql += "FROM "
					+ "giftcard_giftcard ";
			if(giftID != 0){
				sql += "INNER JOIN giftcard_line ON giftcard_giftcard.id = giftcard_line.giftcard_id "
					+ "WHERE giftcard_giftcard.id = "+giftID+" ";
			}					

		List<PromotionModel> giftlist = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel giftModel = new PromotionModel();				
				giftModel.setGiftcard_id(rs.getInt("giftcard_giftcard.id"));
				giftModel.setGiftcard_name(rs.getString("giftcard_giftcard.name"));
				giftModel.setGiftcard_description(rs.getString("giftcard_giftcard.description"));
				giftModel.setGiftcard_prefix(rs.getString("giftcard_giftcard.prefix"));
				giftModel.setGiftcard_numberlenght(rs.getInt("giftcard_giftcard.numberlenght"));
				giftModel.setGiftcard_start_number(rs.getInt("giftcard_giftcard.start_number"));
				giftModel.setGiftcard_run_count(rs.getInt("giftcard_giftcard.run_count"));
				giftModel.setGiftcard_suffix(rs.getString("giftcard_giftcard.suffix"));
				giftModel.setGiftcard_default_amount(rs.getDouble("giftcard_giftcard.default_amount"));
				giftModel.setGiftcard_create_date(rs.getString("giftcard_giftcard.create_date"));
				giftModel.setGiftcard_start_date(rs.getString("giftcard_giftcard.start_date"));
				giftModel.setGiftcard_expiredate(rs.getString("giftcard_giftcard.expiredate"));
				giftModel.setGiftcard_status(rs.getString("giftcard_giftcard.status"));
				if(giftID != 0){
					giftModel.setGiftcard_line_id(rs.getInt("giftcard_line.id"));
					giftModel.setGiftcard_line_name(rs.getString("giftcard_line.name"));
					giftModel.setGiftcard_line_amount(rs.getDouble("giftcard_line.amount"));
					giftModel.setGiftcard_line_giftcard_id(rs.getInt("giftcard_line.giftcard_id"));
				}
				giftlist.add(giftModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return giftlist;
	}
	public void changeStatusgift(PromotionModel giftModel){
		
		String SQL ="UPDATE giftcard_giftcard "
				+ "SET "
				+ "status = ";
				if(giftModel.getGiftcard_status().equals("t")){
					SQL+="'f' ";
				}else{
					SQL+="'t' ";
				}
				SQL+="WHERE id = "+giftModel.getGiftcard_id()+" ";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void deletegiftcardANDline(PromotionModel giftModel){
		
		String SQL ="DELETE FROM giftcard_giftcard "
				+ "WHERE id = "+giftModel.getGiftcard_id()+" ; "

				+ "DELETE FROM giftcard_line "
				+ "WHERE giftcard_id = "+giftModel.getGiftcard_id()+" ";

		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
	public void updategift(PromotionModel giftModel){
		
		String SQL ="UPDATE giftcard_giftcard "
				+ "SET "
				+ "name = '"+giftModel.getGiftcard_name()+"' "
				+ ",description = '"+giftModel.getGiftcard_description()+"' "
				+ "WHERE id = "+giftModel.getGiftcard_id()+" ";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public List<PromotionModel> getGiftCardlineWithpatientList(int giftID){
		
		String sql = "SELECT "
				+ "giftcard_line.id,giftcard_line.`name`, "
				+ "giftcard_line.amount,giftcard_line.giftcard_id, "
				+ "giftcard_line_rel_patient.id,giftcard_line_rel_patient.patient_hn, "
				+ "giftcard_line_rel_patient.giftcard_line_id,pre_name.pre_name_th, "
				+ "patient.first_name_th,patient.last_name_th "
				+ "FROM giftcard_line "
				+ "INNER JOIN giftcard_line_rel_patient ON giftcard_line.id = giftcard_line_rel_patient.giftcard_line_id "
				+ "INNER JOIN patient ON giftcard_line_rel_patient.patient_hn = patient.hn "
				+ "INNER JOIN pre_name ON patient.pre_name_id = pre_name.pre_name_id "
				+ "WHERE  giftcard_line.id = "+giftID+" ";
				

		List<PromotionModel> giftlist = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel giftModel = new PromotionModel();				
					giftModel.setGiftcard_line_id(rs.getInt("giftcard_line.id"));
					giftModel.setGiftcard_line_name(rs.getString("giftcard_line.name"));
					giftModel.setGiftcard_line_amount(rs.getDouble("giftcard_line.amount"));
					giftModel.setGiftcard_line_giftcard_id(rs.getInt("giftcard_line.giftcard_id"));
					giftModel.setGiftcard_line_rel_patient_id(rs.getInt("giftcard_line_rel_patient.id"));
					giftModel.setGiftcard_line_rel_patient_hn(rs.getString("giftcard_line_rel_patient.patient_hn"));
					giftModel.setFirstname(rs.getString("patient.first_name_th"));
					giftModel.setLastname(rs.getString("patient.last_name_th"));
					giftModel.setPrename(rs.getString("pre_name.pre_name_th"));
				giftlist.add(giftModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return giftlist;
	}
	public void deletegiftcardwithpatient(PromotionModel giftModel){
		
		String SQL ="DELETE FROM giftcard_line_rel_patient "
				+ "WHERE id = "+giftModel.getGiftcard_line_rel_patient_id()+"  ";

		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public List<PatientModel> getpatientList(){
		
		String sql = "SELECT "
				+ "pre_name.pre_name_th,patient.first_name_th,patient.last_name_th,patient.hn "
				+ "FROM patient "
				+ "INNER JOIN pre_name ON patient.pre_name_id = pre_name.pre_name_id ";
				

		List<PatientModel> giftlist = new LinkedList<PatientModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PatientModel giftModel = new PatientModel();				
					giftModel.setFirstname_th(rs.getString("patient.first_name_th"));
					giftModel.setLastname_th(rs.getString("patient.last_name_th"));
					giftModel.setPre_name_th(rs.getString("pre_name.pre_name_th"));
					giftModel.setHn(rs.getString("patient.hn"));
				giftlist.add(giftModel);
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return giftlist;
	}	
	public void addgiftcardwithpatient(PromotionModel giftModel){
		
		String SQL ="INSERT INTO giftcard_line_rel_patient (patient_hn,giftcard_line_id) "
				+ "VALUES  "
				+ "('"+giftModel.getGiftcard_line_rel_patient_hn()+"','"+giftModel.getGiftcard_line_id()+"')";

		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public PromotionModel getManagePoints(int promotionID){
		
		String sql = "SELECT "
				+ "promotion_manage.id,promotion_manage.points, "
				+ "promotion_manage.doctor_cost,promotion_manage.company_cost, "
				+ "promotion_manage.type_cost,promotion_manage.promotion_id,promotion_manage.points_type "
				+ "FROM "
				+ "promotion_manage "
				+ "WHERE promotion_manage.promotion_id = '"+promotionID+"'";

		PromotionModel promotionModel = new PromotionModel();
		promotionModel.setPromotion_id(promotionID);
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				promotionModel.setManage_id(rs.getInt("promotion_manage.id"));
				promotionModel.setPoints(rs.getDouble("promotion_manage.points"));
				promotionModel.setType_cost(rs.getInt("promotion_manage.type_cost"));
				promotionModel.setDoctor_cost(rs.getDouble("promotion_manage.doctor_cost"));
				promotionModel.setCompany_cost(rs.getDouble("promotion_manage.company_cost"));
				promotionModel.setPoints_type(rs.getInt("promotion_manage.points_type"));
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return promotionModel;
	}	
	public void InsertORUpdatePromotionPoints(PromotionModel promodel){
		String SQL="";
		if(promodel.getManage_id()== 0){
			 SQL +="INSERT INTO promotion_manage (points,points_type,type_cost,doctor_cost,company_cost,promotion_id) "
					+ "VALUES  "
					+ "('"+promodel.getPoints()+"','"+promodel.getPoints_type()+"','"+promodel.getType_cost()+"'"
					+ ",'"+promodel.getDoctor_cost()+"','"+promodel.getCompany_cost()+"','"+promodel.getPromotion_id()+"')";
		}else{
			 SQL +="UPDATE  promotion_manage "
					+ "SET  "
					+ "points = '"+promodel.getPoints()+"'"
					+ ",points_type = '"+promodel.getPoints_type()+"'"
					+ ",type_cost = '"+promodel.getType_cost()+"'"
					+ ",doctor_cost = '"+promodel.getDoctor_cost()+"'"
					+ ",company_cost = '"+promodel.getCompany_cost()+"' "
					+ "WHERE id = '"+promodel.getManage_id()+"'";
		}
		

		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
	
					
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
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



