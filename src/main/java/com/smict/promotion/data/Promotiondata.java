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

import com.smict.person.model.BranchModel;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;

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
		
		String SQL = "INSERT INTO promotion(name,start_date,end_date,use_condition,billcostover,ismonday,istuesday,"
				+ "iswendesday,isthursday,isfriday,issaturday,issunday,is_allday,is_alltime,start_time,end_time,"
				+ "is_allsubcontact,is_birthmonth,is_allage,from_age,to_age,is_treatmentcount,is_allbranch) VALUES "
				
					+ "('"+protionModel.getName()
					+"','"+protionModel.getStart_date()
					+"','"+protionModel.getEnd_date()
					+"','"+protionModel.getUse_condition()
					+"',"+protionModel.getBillcostover()
					+",'"+protionModel.getIsmonday()
					+"','"+protionModel.getIstuesday()
					+"','"+protionModel.getIswendesday()
					+"','"+protionModel.getIsthursday()
					+"','"+protionModel.getIsfriday()
					+"','"+protionModel.getIssaturday()
					+"','"+protionModel.getIssunday()
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
					+",'"+protionModel.getIs_allbranch()+"') "; 

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			ResultSet rs = pStmt.getGeneratedKeys();
			
			if (!rs.isClosed())
				rs.close();
			if (!pStmt.isClosed())
				pStmt.close();
			if (!conn.isClosed())
				conn.close();
			int promotion_id=0;
			if (rs.next()){
				promotion_id=rs.getInt(1);
			}
			return promotion_id;

		
		}
	
	public void addpromotionbranchinsert(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO promotion_condition_branch(branch_id,promotion_id,status) VALUES ";
			int i=0;		
				for(String Probranch : protionModel.getPromotion_branch_id()){
					if(i>0)
						SQL+=",";
					
				SQL+=	 "('"+Probranch
					+"',"+protionModel.getPromotion_id()
					+",'Active') ";
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
				for(int Procontact : protionModel.getSub_contact_id()){
					if(i>0)
						SQL+=",";
					
				SQL+=	 "("+Procontact
					+","+protionModel.getPromotion_id()
					+",'Active') ";
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
				+ "treatment_master.treatment_nameth,promotion_detail.product_type,promotion_detail.discount_baht,promotion_detail.discount_percent "
				+ "FROM "
				+ "promotion "
				+ "INNER JOIN promotion_detail ON promotion.id = promotion_detail.promotion_id "
				+ "INNER JOIN treatment_master ON promotion_detail.product_id = treatment_master.treatment_code "
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
				promotiondetailModel.setType(rs.getString("treatment_nameth"));
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
				+ "pi.use_condition, pi.billcostover, pi.ismonday, pi.istuesday, "
				+ "pi.iswendesday, pi.isthursday, pi.isfriday, pi.issaturday, pi.issunday, pi.start_time, pi.end_time "
				+ "FROM "
				+ "promotion AS pi ORDER BY 'pi.id' ASC ";
				
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
				promotionModel.setStart_date(rs.getString("start_date"));
				promotionModel.setEnd_date(rs.getString("end_date"));
				promotionModel.setUse_condition(rs.getString("use_condition"));
				promotionModel.setBillcostover(rs.getDouble("billcostover"));
				promotionModel.setIsmonday(rs.getString("ismonday"));
				promotionModel.setIstuesday(rs.getString("istuesday"));
				promotionModel.setIswendesday(rs.getString("iswendesday"));
				promotionModel.setIsthursday(rs.getString("isthursday"));
				promotionModel.setIsfriday(rs.getString("isfriday"));
				promotionModel.setIssaturday(rs.getString("issaturday"));
				promotionModel.setIssunday(rs.getString("issunday"));
				promotionModel.setStart_time(rs.getString("start_time"));
				promotionModel.setEnd_time(rs.getString("end_time"));
				Promotiondata promoDatadetail = new Promotiondata();
				promotionModel.setPromotiondetailModel(promoDatadetail.getListPromotiondetail(promotionModel.getPromotion_id()));
				
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

	public boolean PromotionDelete(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "DELETE FROM promotion  "
				+ " where id = "+protionModel.getPromotion_id()+"";
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();

			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
			
			if(sStmt>0){
				return true;
			}
		
				return false;
		
	}
public List<PromotionModel> getmemberlist(){
		
		String sql = "SELECT "
				+ "promotion_sub_contact.sub_contact_name,promotion_contact.contact_name, "
				+ "promotion_subcontact_type.`name`,promotion_subcontact_wallet.total_amount, "
				+ "promotion_sub_contact.sub_contact_id,promotion_subcontact_wallet.id, "
				+ "promotion_sub_contact.sms_piority,promotion_subcontact_wallet.patient_hn "
				+ "FROM "
				+ "promotion_contact "
				+ "INNER JOIN promotion_sub_contact ON promotion_sub_contact.contact_id = promotion_contact.contact_id "
				+ "LEFT  JOIN promotion_subcontact_type ON promotion_subcontact_type.id = promotion_sub_contact.sub_contact_type_id "
				+ "LEFT  JOIN promotion_subcontact_wallet ON promotion_subcontact_wallet.sub_contact_id = promotion_sub_contact.sub_contact_id "
				+ "GROUP BY promotion_sub_contact.sub_contact_id "
				+ "ORDER BY promotion_sub_contact.sub_contact_id ";
				
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
				promotionModel.setTotal_amount(rs.getDouble("promotion_subcontact_wallet.total_amount"));
				promotionModel.setName(rs.getString("promotion_contact.contact_name"));
				promotionModel.setContypeName(rs.getString("promotion_subcontact_type.name"));				
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

	
	
}



