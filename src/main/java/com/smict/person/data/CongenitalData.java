package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ldc.util.CalculateNumber;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

import com.smict.person.model.CongenitalDiseaseModel;
import com.smict.person.model.PatientModel;

public class CongenitalData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	public List<CongenitalDiseaseModel> getMasterConginentalDisease(CongenitalDiseaseModel congen_model){
		String sql = "select * from congenital_disease where ";
		int congenital_id = congen_model.getCongenital_id(), pat_congenital_disease_id = congen_model.getPat_congenital_disease_id();
		String congen_name_th = congen_model.getCongenital_name_th(), congen_name_en = congen_model.getCongenital_name_en();
		
		if(new Validate().checkIntegerNotZero(pat_congenital_disease_id))
			sql += "pat_congenital_disease_id = "+pat_congenital_disease_id+" and " ;
		
		if(new Validate().checkIntegerNotZero(congenital_id))
			sql += "congenital_id = "+congenital_id+" and " ;
		
		if(new Validate().Check_String_notnull_notempty(congen_name_th))
			sql += "congen_name_th = "+congen_name_th+" and " ;
		
		if(new Validate().Check_String_notnull_notempty(congen_name_en))
			sql += "congen_name_en = "+congen_name_en+" and " ;
		
		sql += "congenital_id > 0 " ;
		
		List<CongenitalDiseaseModel> ListSelectResult = new ArrayList<CongenitalDiseaseModel>();
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				CongenitalDiseaseModel congenModel = new CongenitalDiseaseModel();
				
				congenModel.setCongenital_id(rs.getInt("congenital_id"));
				congenModel.setCongenital_name_th(rs.getString("congenital_name_th"));
				congenModel.setCongenital_name_en(rs.getString("congenital_name_en"));
				
				ListSelectResult.add(congenModel);
			}
			
			rs.close();
			Stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ListSelectResult;
	}
	
	public List<CongenitalDiseaseModel> getConginentalDisease(CongenitalDiseaseModel congen_model){
		
		List<CongenitalDiseaseModel> ListSelectResult = new ArrayList<CongenitalDiseaseModel>();
		
		if(congen_model == null){
			return null;
		}else{
			
		
		
		String sql = "select * from patient_congenital_disease where ";
		int congenital_id = congen_model.getCongenital_id(), pat_congenital_disease_id = congen_model.getPat_congenital_disease_id();
		String congen_name_th = congen_model.getCongenital_name_th(), congen_name_en = congen_model.getCongenital_name_en();
		
		if(new Validate().Check_String_notnull_notempty(congen_name_th))
			sql += "congen_name_th = "+congen_name_th+" and " ;
		
		if(new Validate().Check_String_notnull_notempty(congen_name_en))
			sql += "congen_name_en = "+congen_name_en+" and " ;
		
		
			sql += "pat_congenital_disease_id = "+pat_congenital_disease_id;
		
		
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			ResultSet rsgetConginentalDisease = Stmt.executeQuery(sql);
			
			while (rsgetConginentalDisease.next()) {
				
				CongenitalDiseaseModel congenModel = new CongenitalDiseaseModel();
				
				congenModel.setCongenital_id(rsgetConginentalDisease.getInt("congenital_id"));
				congenModel.setCongenital_name_th(rsgetConginentalDisease.getString("congenital_name_th"));
				congenModel.setCongenital_name_en(rsgetConginentalDisease.getString("congenital_name_en"));
				
				ListSelectResult.add(congenModel);
			}
			
			rsgetConginentalDisease.close();
			Stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return ListSelectResult;
	}
	
	public void addMultiPatCongen(PatientModel patModel){
		
		String sql = "";
		
		try {
			
			sql = "INSERT INTO patient_congenital_disease (pat_congenital_disease_id, congenital_id, congenital_name_th, congenital_name_en) VALUES ";
			int i = 0;
			for (String congenDetail : patModel.getCongenital_disease()) {
				
				if(i > 0) sql +=",";
				
				CongenitalDiseaseModel congenModel = new CongenitalDiseaseModel();
				congenModel = getCongeModel(Integer.parseInt(congenDetail));
				
				if(!congenDetail.equals("100")){
					sql +="('"+patModel.getPat_congenital_disease_id()+"','"+congenModel.getCongenital_id()+"','"+congenModel.getCongenital_name_th()+"','"+congenModel.getCongenital_name_en()+"')";
				}else{
					sql +="('"+patModel.getPat_congenital_disease_id()+"','"+congenDetail+"','"+patModel.getOther_congenital_disease()+"','"+congenModel.getCongenital_name_en()+"')";
				}
				
				i++;
			}
			
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
	
	public void removePatCongen(PatientModel patModel){
		
		String sql = "";
		
		try {
			
			sql = "delete from patient_congenital_disease where pat_congenital_disease_id = "+patModel.getPat_congenital_disease_id()+" ";
			
			Connection connRemove = agent.getConnectMYSql();
			Statement StmtRemove = connRemove.createStatement();
			StmtRemove.executeUpdate(sql);
			
			if(!StmtRemove.isClosed()) StmtRemove.close();
			
			if(!connRemove.isClosed()) connRemove.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CongenitalDiseaseModel getCongeModel(int congenital_id){
		String sql ="SELECT congenital_id, congenital_name_th, congenital_name_en "
				+ "FROM "
				+ "congenital_disease where congenital_id = "+congenital_id;
		
		Connection connGetCongen;
		CongenitalDiseaseModel congenModel = new CongenitalDiseaseModel();
		try {
			connGetCongen = agent.getConnectMYSql();
			Statement stmtGetCongen = connGetCongen.createStatement();
			ResultSet rsGetCongen = stmtGetCongen.executeQuery(sql);
			while (rsGetCongen.next()) {
				congenModel.setCongenital_id(rsGetCongen.getInt("congenital_id"));
				congenModel.setCongenital_name_th(rsGetCongen.getString("congenital_name_th"));
				congenModel.setCongenital_name_en(rsGetCongen.getString("congenital_name_en"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return congenModel;
	}
}
