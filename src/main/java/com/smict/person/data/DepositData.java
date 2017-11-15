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
import com.smict.promotion.model.PromotionModel;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class DepositData
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
public List<DepositModel> getDeposit(String hn) throws Exception{
	
	String SQL = "SELECT a.id, a.deposit_type, a.deposit_by, a.deposit_date, a.remark, "
			+ "a.old_money, a.transfer_money, a.total_money, a.hn, a.branch_id "
			+ "FROM deposit_money_transaction a "
			+ "WHERE a.hn = '"+hn+"' ";

	List<DepositModel> depositList = new ArrayList<DepositModel>(); 
	agent.connectMySQL();
	agent.exeQuery(SQL);
	if(agent.size() > 0){
		try {
			ResultSet rs = agent.getRs();				
			while(rs.next()){
				DepositModel depositModel = new DepositModel();
				depositModel.setDeposit_id(rs.getInt("id"));
				depositModel.setHn(rs.getString("hn"));
				depositModel.setBranch_id(rs.getString("branch_id"));
				depositModel.setDeposit_type(rs.getString("deposit_type"));
				depositModel.setDeposit_by(rs.getString("deposit_by")); 
				depositModel.setDeposit_date(dateUtil.convertDateSpecificationPattern("yyyy-MM-dd","dd-MM-yyyy",rs.getString("deposit_date").substring(0, 10),true)
						+" "+rs.getString("deposit_date").substring(11, 19));
				depositModel.setRemark(rs.getString("remark"));
				
				depositModel.setOld_money(rs.getDouble("old_money"));
				depositModel.setTransfer_money(rs.getDouble("transfer_money"));
				depositModel.setTotal_money(rs.getDouble("total_money"));
				
				depositList.add(depositModel);
			}
			agent.disconnectMySQL();
			return depositList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	agent.disconnectMySQL();
	return null;
} 
	
public void addDeposit(DepositModel dpModel) throws IOException, Exception{
		
		String SQL ="INSERT INTO deposit_money_transaction(deposit_type,deposit_by,deposit_date,remark,old_money,transfer_money,total_money,hn,branch_id) "
								+ "VALUES ('"+dpModel.getDeposit_type()+"','"+dpModel.getDeposit_by()+"',now(),'"+dpModel.getRemark()+"' "
								+ ","+dpModel.getOld_money()+","+dpModel.getTransfer_money()+","+dpModel.getTotal_money()+" "
								+ ",'"+dpModel.getHn()+"','"+dpModel.getBranch_id()+"')";
								 
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
		
}
