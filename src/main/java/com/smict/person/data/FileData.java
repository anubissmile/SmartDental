package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smict.all.model.PatFileModel;

import ldc.util.CalculateNumber;
import ldc.util.DBConnect;
import ldc.util.DateUtil;

public class FileData {
	
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	public void addPatFileID(String hn, String branch_id){
		
		int fileno = new CalculateNumber().plusOneInt(new BranchData().getHighestBranchFileNumber(branch_id),1);
		
		increaseBranchPatfileId(branch_id, String.valueOf(fileno));
		
		String sql = "";
		
		try {
			
			sql = "INSERT INTO patient_file_id (hn, branch_id, file_id) VALUES ('"+hn+"','"+branch_id+"','"+fileno+"') ";
			
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			Stmt.close();
			conn.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void increaseBranchPatfileId(String branch_id, String highestPatFileId){
		
		String sql = "";
		
		try {
			
			sql = "update running_file set file_id = '"+highestPatFileId+"' where branch_id = '"+branch_id+"'";
			
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			Stmt.executeUpdate(sql);
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<PatFileModel> getListPatFileModel(String patHn){
		
		List<PatFileModel> listPatFileModel = new ArrayList<PatFileModel>();
		
		String sql = "select patFile.file_id, patFile.hn, branch.branch_id, branch.branch_name "
				+ "from patient_file_id patFile "
				+ "INNER JOIN branch on (patFile.branch_id = branch.branch_id) "
				+ "where patFile.hn = '"+patHn+"'";
		
		try {
			Connection connPatfile = null;
			Statement StmtPatfile = null;
			ResultSet rsPatfile = null;
			
			connPatfile = agent.getConnectMYSql();
			StmtPatfile = connPatfile.createStatement();
			rsPatfile = StmtPatfile.executeQuery(sql);
			
			while (rsPatfile.next()) {
				PatFileModel aPatFileModel = new PatFileModel();
				aPatFileModel.setBranch_id(rsPatfile.getString("branch_id"));
				aPatFileModel.setBranch_name(rsPatfile.getString("branch_name"));
				aPatFileModel.setFileId(rsPatfile.getString("file_id"));
				listPatFileModel.add(aPatFileModel);
			}
			if (!rsPatfile.isClosed())
				rsPatfile.close();
			if (!StmtPatfile.isClosed())
				StmtPatfile.close();
			if (!connPatfile.isClosed())
				connPatfile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listPatFileModel;
	}
}
