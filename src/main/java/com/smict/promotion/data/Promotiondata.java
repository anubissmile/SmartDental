package com.smict.promotion.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.smict.product.model.ProductModel;
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
	
	public boolean addpromotioninsert(PromotionModel protionModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO promotion(name,use_condition,billcostover,start_date,end_date,ismonday,istuesday,iswendesday,isthursday,isfriday,issaturday,issunday,start_time,end_time) VALUES "
					+ "('"+protionModel.getName()
					+"','"+protionModel.getUse_condition()
					+"',"+protionModel.getBillcostover()
					+",'"+protionModel.getStart_date()
					+"','"+protionModel.getEnd_date()
					+"','"+protionModel.getIsmonday()
					+"','"+protionModel.getIstuesday()
					+"','"+protionModel.getIswendesday()
					+"','"+protionModel.getIsthursday()
					+"','"+protionModel.getIsfriday()
					+"','"+protionModel.getIssaturday()
					+"','"+protionModel.getIssunday()
					+"','"+protionModel.getStart_time()
					+"','"+protionModel.getEnd_time()+"')";
			System.out.println(SQL);
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}
	
	public List<PromotionModel> getListPromotion(){
		
		String sql = "SELECT "
				+ "pi.id, pi.name, pi.start_date, pi.end_date, "
				+ "pi.use_condition, pi.billcostover, pi.ismonday, pi.istuesday, "
				+ "pi.iswendesday, pi.isthursday, pi.isfriday, pi.issaturday, pi.issunday, pi.start_time, pi.end_time "
				+ "FROM "
				+ "promotion AS pi ";
				
		List<PromotionModel> promotionList = new LinkedList<PromotionModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				PromotionModel promotionModel = new PromotionModel();
				
				promotionModel.setId(rs.getInt("id"));
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
				+ " where id = '"+protionModel.getId()+"'";
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}
	
}



