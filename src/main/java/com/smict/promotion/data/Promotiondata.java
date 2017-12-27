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
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
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
				+ "is_allsubcontact,is_birthmonth,is_allage,from_age,to_age,is_treatmentcount,is_allbranch,description,"
				+ "service_charge,status) VALUES "
				
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
					+"','"+protionModel.getPromotion_description()
					+"','"+protionModel.getService_charge()+"','1')"; 

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
	public void addpromotioncontactinsert(PromotionModel protionModel,int isAll) throws IOException, Exception{
		/**
		 * isAll  0 = add not all contact  , 1 = add all contact
		 */
		String SQL = "";
		if(isAll == 0){
		SQL += "INSERT INTO promotion_condition_subcontact(sub_contact_id,promotion_id,status) VALUES ";
			int i=0;		
				for(int Procontact : protionModel.getSubConID()){
					if(i>0)
						SQL+=",";
					
				SQL+=	 "("+Procontact
					+","+protionModel.getPromotion_id()
					+",'0') ";
					i++;
				}
				
		}else if(isAll == 1){		
		 SQL += "INSERT INTO promotion_condition_subcontact(sub_contact_id,promotion_id,status)  "
				+"(SELECT sub_contact_id,'"+protionModel.getPromotion_id()+"',0 FROM promotion_sub_contact) ";
		 
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
				
				promotiondetailModel.setProduct_type(rs.getInt("product_type"));
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
				promotionModel.setStart_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("start_date"),true));
				promotionModel.setEnd_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("end_date"),true));
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
				giftModel.setGiftcard_start_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("giftcard_giftcard.start_date"),true));
				giftModel.setGiftcard_expiredate(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("giftcard_giftcard.expiredate"),true));
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
	public PromotionModel getManageAmount(int promotionID){
		
		String sql = "SELECT "
				+ "promotion_manage.id, "
				+ "promotion_manage.doctor_cost,promotion_manage.company_cost, "
				+ "promotion_manage.type_cost,promotion_manage.promotion_id "
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
			/*	promotionModel.setPoints(rs.getDouble("promotion_manage.points"));*/
				promotionModel.setType_cost(rs.getInt("promotion_manage.type_cost"));
				promotionModel.setDoctor_cost(rs.getDouble("promotion_manage.doctor_cost"));
				promotionModel.setCompany_cost(rs.getDouble("promotion_manage.company_cost"));
			/*	promotionModel.setPoints_type(rs.getInt("promotion_manage.points_type"));*/
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
	public PromotionModel getManagePoints(int promotionID){
		
		String sql = "SELECT "
				+ "promotion_points.id,promotion_points.receive_points "
				+ "FROM "
				+ "promotion_points "
				+ "WHERE promotion_points.promotion_id = '"+promotionID+"'";

		PromotionModel promotionModel = new PromotionModel();
		promotionModel.setPromotion_id(promotionID);
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				promotionModel.setPoints_id(rs.getInt("promotion_points.id"));
				promotionModel.setPoints_type(rs.getInt("promotion_points.receive_points"));
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
	public void InsertORUpdatePromotionPoints(PromotionModel promodel,int statdoing){
		/**
		 * status 1 = insert Manage 2 = updatePoints 3 = divide amount 
		 */
		
		String SQL="";
		if(statdoing == 1){
			 SQL +="INSERT INTO promotion_manage (promotion_id) "
					+ "VALUES  "
					/*+ "('"+promodel.getPoints()+"','"+promodel.getPoints_type()+"','"+promodel.getType_cost()+"'"
					+ ",'"+promodel.getDoctor_cost()+"','"+promodel.getCompany_cost()+"','"+promodel.getPromotion_id()+"')";*/
					+"('"+promodel.getPromotion_id()+"') ; "
					+ "INSERT INTO promotion_points (receive_points,promotion_id) "
					+ "VALUES  "
					/*+ "('"+promodel.getPoints()+"','"+promodel.getPoints_type()+"','"+promodel.getType_cost()+"'"
					+ ",'"+promodel.getDoctor_cost()+"','"+promodel.getCompany_cost()+"','"+promodel.getPromotion_id()+"')";*/
					+"('0','"+promodel.getPromotion_id()+"') " ;
			 
		}else if(statdoing == 2){
			 SQL +="UPDATE  promotion_points "
					+ "SET  "
					+ "receive_points = '"+promodel.getPoints_type()+"'"
					+ "WHERE id = '"+promodel.getPoints_id()+"'";
		}else if(statdoing == 3){
			SQL +="UPDATE  promotion_manage "
					+ "SET  "
					+ "type_cost = '"+promodel.getType_cost()+"'"
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
	public void updatePromotionStatus(PromotionModel promodel){
		 
		String	SQL ="UPDATE  promotion "
					+ "SET  "
					+ "status = '"+promodel.getStatus_pro()+"'"
					+ "WHERE id = '"+promodel.getPromotion_id()+"'";
		
		

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
	public PromotionModel getPromotionEdit(int proID){
		
		String sql = "SELECT "
				+ "promotion.id,promotion.`name`,promotion.start_date, "
				+ "promotion.end_date,promotion.use_condition,promotion.billcostover, "
				+ "promotion.is_allday,promotion.is_alltime,promotion.start_time, "
				+ "promotion.end_time,promotion.is_allsubcontact,promotion.is_birthmonth, "
				+ "promotion.is_allage,promotion.from_age,promotion.to_age, "
				+ "promotion.is_treatmentcount,promotion.is_allbranch,promotion.description,promotion.service_charge, "
				+ "promotion.`status` "
				+ "FROM "
				+ "promotion   "
				+ "WHERE promotion.id = '"+proID+"' ";
				
		PromotionModel promotionModel = new PromotionModel();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {	
				promotionModel.setPromotion_id(rs.getInt("id"));
				promotionModel.setName(rs.getString("name"));
				promotionModel.setStart_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("start_date"),false));
				promotionModel.setEnd_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("end_date"),false));
				promotionModel.setUse_condition(rs.getString("use_condition"));
				promotionModel.setBillcostover(rs.getDouble("billcostover"));
				promotionModel.setIs_allday(rs.getString("is_allday"));
				promotionModel.setIs_alltime(rs.getString("is_alltime"));
				if(promotionModel.getIs_alltime().equals("0") ){
					promotionModel.setStart_time(dateUtil.convertDateSpecificationPattern("HH:mm:ss","HH:mm",rs.getString("start_time"),false));
					promotionModel.setEnd_time(dateUtil.convertDateSpecificationPattern("HH:mm:ss","HH:mm",rs.getString("end_time"),false));
				}else{
					promotionModel.setStart_time("00:00");
					promotionModel.setEnd_time("00:00");
				}
				promotionModel.setIs_allsubcontact(rs.getString("is_allsubcontact"));
				promotionModel.setIs_birthmonth(rs.getString("is_birthmonth"));
				promotionModel.setIs_allage(rs.getString("is_allage"));
				promotionModel.setFrom_age(rs.getInt("from_age"));
				promotionModel.setTo_age(rs.getInt("to_age"));
				promotionModel.setIs_treatmentcount(rs.getInt("is_treatmentcount"));
				promotionModel.setIs_allbranch(rs.getString("is_allbranch"));
				promotionModel.setPromotion_description(rs.getString("description"));
				promotionModel.setService_charge(rs.getInt("service_charge"));
				promotionModel.setStatus_pro(rs.getInt("status"));

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
	public List<PromotionModel> getPromotiondayList(int proID){
		
		String sql = "SELECT "
				+ "promotion_condition_day.id,promotion_condition_day.day_id,promotion_condition_day.promotion_id "
				+ "FROM  "
				+ "promotion_condition_day "
				+ "WHERE promotion_condition_day.promotion_id = '"+proID+"' ";
				

		List<PromotionModel> promotionlist = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel promotionModel = new PromotionModel();				
				promotionModel.setDay_id(rs.getInt("day_id"));
				promotionlist.add(promotionModel);
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
		
		return promotionlist;
	}
	public List<PromotionModel> getPromotionsubcontactList(int proID){
		
		String sql = "SELECT "
				+ "promotion_condition_subcontact.id,promotion_condition_subcontact.sub_contact_id"
				+ ",promotion_condition_subcontact.promotion_id,promotion_sub_contact.sub_contact_name "
				+ "FROM  "
				+ "promotion_condition_subcontact "
				+ "INNER JOIN promotion_sub_contact ON promotion_condition_subcontact.sub_contact_id = promotion_sub_contact.sub_contact_id "
				+ "WHERE promotion_condition_subcontact.promotion_id = '"+proID+"' ";
				

		List<PromotionModel> promotionlist = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel promotionModel = new PromotionModel();				
				promotionModel.setSub_contactid(rs.getString("promotion_condition_subcontact.sub_contact_id"));
				promotionModel.setSub_contactname(rs.getString("promotion_sub_contact.sub_contact_name"));
				promotionlist.add(promotionModel);
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
		
		return promotionlist;
	}
	public List<PromotionModel> getPromotionBranchList(int proID){
		
		String sql = "SELECT "
				+ "promotion_condition_branch.id,promotion_condition_branch.branch_id,promotion_condition_branch.promotion_id, "
				+ "branch.branch_name "
				+ "FROM  "
				+ "promotion_condition_branch "
				+ "INNER JOIN branch ON promotion_condition_branch.branch_id = branch.branch_id "
				+ "WHERE promotion_condition_branch.promotion_id = '"+proID+"' ";
				

		List<PromotionModel> promotionlist = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel promotionModel = new PromotionModel();				
				promotionModel.setPro_branchID(rs.getString("promotion_condition_branch.branch_id"));
				promotionModel.setPro_branchName(rs.getString("branch.branch_name"));
				promotionlist.add(promotionModel);
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
		
		return promotionlist;
	}
	public void UpdatePromotionHeader(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "UPDATE promotion "
				+ "SET "
				+ "name ='"+protionModel.getName()
				+ "',start_date ='"+protionModel.getStart_date()
				+ "',end_date ='"+protionModel.getEnd_date()
				+ "',use_condition ='"+protionModel.getUse_condition()
				+ "',billcostover ='"+protionModel.getBillcostover()
				+ "',is_allday ='"+protionModel.getIs_allday()
				+ "',is_alltime ='"+protionModel.getIs_alltime()
				+ "',start_time ='"+protionModel.getStart_time()
				+ "',end_time ='"+protionModel.getEnd_time()
				+ "',is_allsubcontact ='"+protionModel.getIs_allsubcontact()
				+ "',is_birthmonth ='"+protionModel.getIs_birthmonth()
				+ "',is_allage ='"+protionModel.getIs_allage()
				+ "',from_age ='"+protionModel.getFrom_age()
				+ "',to_age ='"+protionModel.getTo_age()
				+ "',is_treatmentcount ='"+protionModel.getIs_treatmentcount()
				+ "',is_allbranch ='"+protionModel.getIs_allbranch()
				+ "',description = '"+protionModel.getPromotion_description()
				+ "',service_charge = '"+protionModel.getService_charge()
				+ "' WHERE id ='"+protionModel.getPromotion_id()+"'";


			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		
		}	
	public void PromotionDeleteCondition(int proID,int branch,int subcon,int day) throws IOException, Exception{
		
		String SQL = ""	;
				if(branch == 1){
					SQL	+= "DELETE FROM promotion_condition_branch  "				
							+ " where promotion_id = "+proID+" ";
				}else if(day == 1){
					SQL	+= "DELETE FROM promotion_condition_day  "
							+ " where promotion_id = "+proID+" ";
				}else if(subcon == 1){
					SQL	+= "DELETE FROM promotion_condition_subcontact  "
							+ " where promotion_id = "+proID+" ";
				}
			
				
				
				
				
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		
	}
	public void PromotionPointsLineInsert(int proID,int stat) throws IOException, Exception{
		/**
		 *  1 = select all ,2 = not all
		 */
		
		String SQL ="";
		 if(stat == 1){
			 SQL = "INSERT INTO promotion_points_line (points,contact_id,points_id) "
						+ "( SELECT 0,promotion_condition_subcontact.sub_contact_id,promotion_points.id "
						+ "FROM "
						+ "promotion "
						+ "INNER JOIN promotion_condition_subcontact ON promotion.id = promotion_condition_subcontact.promotion_id "
						+ "INNER JOIN promotion_points ON promotion.id = promotion_points.promotion_id "
						+ "WHERE promotion.id = "+proID+" )";
		 }else if(stat == 2){
			 SQL = "INSERT INTO promotion_points_line (points,contact_id,points_id) "
						+ "( SELECT 0,promotion_condition_subcontact.sub_contact_id,promotion_points.id "
						+ "FROM "
						+ "promotion "
						+ "INNER JOIN promotion_condition_subcontact ON promotion.id = promotion_condition_subcontact.promotion_id "
						+ "INNER JOIN promotion_points ON promotion.id = promotion_points.promotion_id "
						+ "WHERE promotion.id = "+proID+" "
						+ "AND  promotion_condition_subcontact.sub_contact_id NOT IN "
						+ "(SELECT contact_id FROM promotion_points_line  "
						+ "INNER JOIN promotion_points ON promotion_points_line.points_id = promotion_points.id "
						+ "WHERE promotion_points.promotion_id = 1) )";
		 }
			
			
				
				
				
				
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		
	}
	public void PromotionPointsLineUpdate(int proID,int subConID,double points) {
		
		String SQL = "UPDATE  promotion_points_line "
				+ "SET  "
				+ "points = '"+points+"' "
				+ "WHERE points_id = '"+proID+"' AND contact_id = '"+subConID+"' " ;
			
				
				
				
				
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
	public void PromotionDeletePointsLine(int proID,int [] subcon,int stat) throws IOException, Exception{
		/**
		 *  1= delete all , 2 = not all 
		 */
		String SQL = ""	;
				if(stat == 1){
					SQL	+= "DELETE  promotion_points_line "				
							+ "FROM "
							+ "promotion_points_line "
							+ "INNER JOIN promotion_points ON promotion_points.id = promotion_points_line.points_id   "
							+ "WHERE promotion_points.promotion_id = "+proID+" ";
				}else if(stat == 2){
					SQL	+= "DELETE  promotion_points_line "				
							+ "FROM "
							+ "promotion_points_line "
							+ "INNER JOIN promotion_points ON promotion_points.id = promotion_points_line.points_id   "
							+ "WHERE promotion_points.promotion_id = "+proID+" "
							+ "AND promotion_points_line.contact_id NOT IN ( ";
						 int i=0;		
							for(int Procontact : subcon){
								if(i>0)
									SQL+=",";
								
							SQL+=	 "'"+Procontact+"'";
								i++;
							}
							SQL+=") ";
				}
			
				
				
				
				
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();

			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();

		
	}
	public List<PromotionModel> getGiftVoucherList(int giftID){
		
		String sql = "SELECT "
				+ "gv.id,gv.`name`,gv.description,gv.prefix,gv.numberlenght, "
				+ "gv.start_number,gv.run_count,gv.suffix, "
				+ "gv.create_date,gv.start_date,gv.expiredate,gv.`status` ";
			if(giftID != 0){
				sql += ",gvl.id,gvl.`name`,gvl.`status` ";
			}				
				sql += "FROM "
					+ "giftvoucher_giftvoucher AS gv ";
			if(giftID != 0){
				sql += "INNER JOIN giftvoucher_line AS gvl ON gv.id = gvl.giftvoucher_id "
					+ " WHERE gv.id  = "+giftID+" ";
			}					

		List<PromotionModel> giftlist = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel giftModel = new PromotionModel();				
				giftModel.setGv_id(rs.getInt("gv.id"));
				giftModel.setGv_start_number(rs.getInt("gv.start_number"));
				giftModel.setGv_numberlenght(rs.getInt("gv.numberlenght"));
				giftModel.setGv_run_count(rs.getInt("gv.run_count"));
				giftModel.setGv_name(rs.getString("gv.name"));
				giftModel.setGv_description(rs.getString("gv.description"));
				giftModel.setGv_prefix(rs.getString("gv.prefix"));
				giftModel.setGv_suffix(rs.getString("gv.suffix"));
				giftModel.setGv_status(rs.getString("gv.status"));
				giftModel.setGv_create_date(rs.getString("gv.create_date"));
				giftModel.setGv_start_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("gv.start_date"),true));
				giftModel.setGv_expiredate(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("gv.expiredate"),true));
				if(giftID != 0){
					giftModel.setGvl_id(rs.getInt("gvl.id"));
					giftModel.setGvl_name(rs.getString("gvl.name"));
					giftModel.setGvl_status(rs.getString("gvl.status"));
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
	public int addgiftVoucher(PromotionModel giftModel){
		int gift_id=0;
		String SQL ="INSERT INTO giftvoucher_giftvoucher "
				+ "(name,description,prefix,numberlenght,start_number, "
				+ "run_count,suffix,create_date,start_date,expiredate,status) "
				+ "VALUES "
				+ "('"+giftModel.getGv_name()+"','"+giftModel.getGv_description()+"'"
				+ ",'"+giftModel.getGv_prefix()+"','"+giftModel.getGv_numberlenght()+"'"
				+ ",'"+giftModel.getGv_start_number()+"','"+giftModel.getGv_run_count()+"'"
				+ ",'"+giftModel.getGv_suffix()+"'"
				+ ",NOW(),'"+giftModel.getGv_start_date()+"','"+giftModel.getGv_expiredate()+"'"
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
	public void addgiftVoucherline(PromotionModel giftModel,String allname){
		try {
			String [] allnumber = allname.split(",");
			String SQL ="INSERT INTO giftvoucher_line "
					+ "(name,giftvoucher_id,status) "
					+ "VALUES ";
					int countnumber=0;
					for(String onemunber : allnumber ){
						if(countnumber>0){
							SQL +=",";
						}
							SQL +="('";
						if(!StringUtils.isEmpty(giftModel.getGv_prefix())){
							SQL+=""+giftModel.getGv_prefix()+"";
						}						
						SQL+=""+onemunber+"";
						if(!StringUtils.isEmpty(giftModel.getGv_suffix())){
							SQL+=""+giftModel.getGv_suffix()+"";
						}
						SQL+= "',"							
							+ "'"+giftModel.getGv_id()+"','t') ";
					
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
	public void addgiftVoucherPrivilege(PromotionModel giftModel){

		String SQL ="INSERT INTO giftvoucher_privilege "
				+ "(amount,type,product_id,product_type,giftvoucher_id) "
				+ "VALUES "
				+ "('"+giftModel.getGvp_amount()+"',"
				+ "'"+giftModel.getGvp_type()+"',"
				+ "'"+giftModel.getGvp_product_id()+"',"
				+ "'"+giftModel.getGvp_product_type()+"',"
				+ "'"+giftModel.getGv_id()+"')";

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
	public void deletegiftVoucherAndlineAndPrivilege(PromotionModel giftModel){
		
		String SQL ="DELETE FROM giftvoucher_giftvoucher "
				+ "WHERE id = "+giftModel.getGv_id()+" ; "

				+ "DELETE FROM giftvoucher_line "
				+ "WHERE giftvoucher_id = "+giftModel.getGv_id()+"; "
				
				+ "DELETE FROM giftvoucher_privilege "
				+ "WHERE giftvoucher_id = "+giftModel.getGv_id()+" ";
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
	public void changeStatusgiftVoucher(PromotionModel giftModel){
		
		String SQL ="UPDATE giftvoucher_giftvoucher "
				+ "SET "
				+ "status = ";
				if(giftModel.getGv_status().equals("t")){
					SQL+="'f' ";
				}else{
					SQL+="'t' ";
				}
				SQL+="WHERE id = "+giftModel.getGv_id()+" ";
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
	public void updategiftVoucher(PromotionModel giftModel){
		
		String SQL ="UPDATE giftvoucher_giftvoucher "
				+ "SET "
				+ "name = '"+giftModel.getGv_name()+"' "
				+ ",description = '"+giftModel.getGv_description()+"' "
				+ "WHERE id = "+giftModel.getGv_id()+" ";
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
	public JSONArray getGiftVocherPrivilege(String giftID){
		 
		StringBuilder sql = new StringBuilder("SELECT gvp.id,gvp.amount,gvp.type,gvp.product_id,"
				+ "gvp.product_type,gvp.giftvoucher_id,treatment_master.nameth "
				+ "FROM giftvoucher_privilege AS gvp "
				+ "INNER JOIN treatment_master ON gvp.product_id = treatment_master.id "
				+ "WHERE gvp.product_type = 7 AND gvp.giftvoucher_id = '"+giftID+"' "  
				+ "UNION ALL "
				+ "SELECT gvp.id,gvp.amount,gvp.type,gvp.product_id,"
				+ "gvp.product_type,gvp.giftvoucher_id,treatment_category.`name` "
				+ "FROM "
				+ "giftvoucher_privilege AS gvp "
				+ "INNER JOIN treatment_category ON treatment_category.id = gvp.product_id "
				+ "WHERE gvp.product_type = 6 AND gvp.giftvoucher_id ='"+giftID+"' "
				+ "UNION ALL "
				+ "SELECT "
				+ "gvp.id,gvp.amount,gvp.type,gvp.product_id, "
				+ "gvp.product_type,gvp.giftvoucher_id,treatment_group.`name` "
				+ "FROM "
				+ "giftvoucher_privilege AS gvp "
				+ "INNER JOIN treatment_group ON treatment_group.id = gvp.product_id "
				+ "WHERE gvp.product_type = 5 AND gvp.giftvoucher_id = '"+giftID+"' "
				+ "UNION ALL "
				+ "SELECT "
				+ "gvp.id,gvp.amount,gvp.type,gvp.product_id, "
				+ "gvp.product_type,gvp.giftvoucher_id,IFNULL('-','-') "
				+ "FROM giftvoucher_privilege AS gvp "
				+ "WHERE gvp.product_type = 4 AND gvp.giftvoucher_id = '"+giftID+"' ");


		JSONArray jsonArray = new JSONArray();
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(sql.toString());
			while(rs.next()){
				JSONObject jsonOBJ = new JSONObject(); 
				jsonOBJ.put("name", rs.getString("nameth")); 
				jsonOBJ.put("pro_type", rs.getString("product_type")); 
				jsonOBJ.put("type", rs.getString("type"));
				jsonOBJ.put("amount", rs.getString("amount"));
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
}



