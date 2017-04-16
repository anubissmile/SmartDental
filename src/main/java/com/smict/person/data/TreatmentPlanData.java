package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ldc.util.DBConnect;
import ldc.util.DateUtil;

import com.smict.all.model.ServicePatientModel;
import com.smict.all.model.TreatmentPlanModel;;

public class TreatmentPlanData {
	DBConnect agent = new DBConnect();
	DateUtil dateUtil = new DateUtil();
	
	public List<TreatmentPlanModel> getListTreatmentPlanHeader(TreatmentPlanModel treatPlanModel){
		List<TreatmentPlanModel> listPlan = new ArrayList<TreatmentPlanModel>();
		String sql = "select * "
				+ "from treatment_plan treatPlan "
				+ "INNER JOIN system_status sysStatus on (treatPlan.header_status = sysStatus.status_id)"
				+ "where hn = '"+treatPlanModel.getHn()+"'";
		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TreatmentPlanModel aTreatmentPlanModel = new TreatmentPlanModel();
				aTreatmentPlanModel.setTreatment_planid(rs.getInt("treatment_planid"));
				aTreatmentPlanModel.setTreatmentPlanname(rs.getString("treatment_planname"));
				aTreatmentPlanModel.setCreateDatetime(rs.getDate("create_datetime"));
				aTreatmentPlanModel.setHeaderStatus(rs.getString("header_status"));
				aTreatmentPlanModel.setHeaderStatusName(rs.getString("status_name"));
				listPlan.add(aTreatmentPlanModel);
			}
			
			if(conn.isClosed()) conn.close();
			if(stmt.isClosed()) stmt.close();
			if(rs.isClosed()) rs.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listPlan;
	}
	
	public List<TreatmentPlanModel> getListTreatmentPlanDetail(TreatmentPlanModel treatPlanModel){
		List<TreatmentPlanModel> listPlanDetail = new ArrayList<TreatmentPlanModel>();
		String sql = "SELECT treatPlan.treatment_planid, treatPlan.hn, treatPlan.treatment_planname, treatPlanDetail.treatment_code,"
				+ "treatPlanDetail.surf, treatPlanDetail.tooth, treatPlanDetail.tooth_range, system_status.status_name,"
				+ "system_status.status_id, treatPlanDetail.create_datetime, treatPlanDetail.create_by,treatMent.treatment_nameth, "
				+ "treatMent.treatment_nameen, treatMent.price_standard "
				+ "FROM "
				+ "treatment_plan AS treatPlan "
				+ "INNER JOIN treatment_plandetail AS treatPlanDetail ON treatPlanDetail.treatment_planid = treatPlan.treatment_planid "
				+ "INNER JOIN system_status ON system_status.status_id = treatPlanDetail.detail_status "
				+ "INNER JOIN treatment_master AS treatMent ON treatMent.treatment_code = treatPlanDetail.treatment_code "
				+ "where treatPlan.treatment_planid = "+treatPlanModel.getTreatment_planid();
		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TreatmentPlanModel aTreatmentPlanModel = new TreatmentPlanModel();
				aTreatmentPlanModel.setTreatment_planid(rs.getInt("treatment_planid"));
				aTreatmentPlanModel.setTreatmentPlanname(rs.getString("treatment_planname"));
				aTreatmentPlanModel.setTreatment_code(rs.getString("treatment_code"));
				aTreatmentPlanModel.setTreatment_nameth(rs.getString("treatment_nameth"));
				aTreatmentPlanModel.setTreatment_nameen(rs.getString("treatment_nameen"));
				aTreatmentPlanModel.setPrice(rs.getString("price_standard"));
				aTreatmentPlanModel.setCreateDatetime(rs.getDate("create_datetime"));
				aTreatmentPlanModel.setDetailStatus(rs.getString("status_id"));
				aTreatmentPlanModel.setDetailStatusName(rs.getString("status_name"));
				listPlanDetail.add(aTreatmentPlanModel);
			}
			
			if(conn.isClosed()) conn.close();
			if(stmt.isClosed()) stmt.close();
			if(rs.isClosed()) rs.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listPlanDetail;
	}
	
	public List<TreatmentPlanModel> getTreatmentPlanDetailHeader(TreatmentPlanModel treatPlanModel){
		List<TreatmentPlanModel> listPlanDetail = new ArrayList<TreatmentPlanModel>();
		String sql = "SELECT "
				+ "treatment_plan.treatment_planid, treatment_plan.hn, treatment_plan.treatment_planname, treatment_plan.create_datetime, "
				+ "treatment_plan.update_datetime, system_status.status_name as header_statusname, treatment_plan.header_status "
				+ "FROM "
				+ "treatment_plan "
				+ "INNER JOIN system_status ON system_status.status_id = treatment_plan.header_status "
				+ "where treatment_planid = "+treatPlanModel.getTreatment_planid();

		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TreatmentPlanModel aTreatmentPlanModel = new TreatmentPlanModel();
				aTreatmentPlanModel.setTreatment_planid(rs.getInt("treatment_planid"));
				aTreatmentPlanModel.setTreatmentPlanname(rs.getString("treatment_planname"));
				aTreatmentPlanModel.setCreateDatetime(rs.getDate("create_datetime"));
				aTreatmentPlanModel.setHeaderStatusName(rs.getString("header_statusname"));
				listPlanDetail.add(aTreatmentPlanModel);
			}
			
			if(conn.isClosed()) conn.close();
			if(stmt.isClosed()) stmt.close();
			if(rs.isClosed()) rs.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listPlanDetail;
	}
	
	public int getTreatmentPlanIdByHnOderbyLimit(TreatmentPlanModel treatPlanModel){
		int treatment_planid = 0;
		String sql = "select * "
				+ "from treatment_plan treatPlan "
				+ "INNER JOIN system_status sysStatus on (treatPlan.header_status = sysStatus.status_id)"
				+ "where hn = '"+treatPlanModel.getHn()+"' "
				+ "ORDER BY create_datetime DESC "
				+ "LIMIT 1";
		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				treatment_planid = rs.getInt("treatment_planid");
			}
			
			if(conn.isClosed()) conn.close();
			if(stmt.isClosed()) stmt.close();
			if(rs.isClosed()) rs.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return treatment_planid;
	}
	
	public boolean hasCreateTreatmentPlan(TreatmentPlanModel aTreatmentPlanModel){
		
		String sql = "insert into treatment_plan "
				+ "(hn, treatment_planname, create_datetime, header_status, doctor_id) "
				+ "VALUES ('"+aTreatmentPlanModel.getHn()+"','"+aTreatmentPlanModel.getTreatmentPlanname()+"',now(), 2, " + aTreatmentPlanModel.getDoctorId() + ")";
		
		agent.connectMySQL();
		int rec = agent.exeUpdate(sql);
		agent.disconnectMySQL();
		return (rec > 0) ? true : false;
	}

	public boolean addDetailTreatmentPlan(int treatment_planid, ServicePatientModel servicePatModel, String create_by){
		
		String tooth = "", tooth_range = "", surf = "";
		
		if(!servicePatModel.getTooth_tooth().equals("")){ 
			surf = "";
			tooth	= servicePatModel.getTooth_tooth();
			tooth_range = "";
		}else if(!servicePatModel.getSurf_tooth().equals("")&&!servicePatModel.getSurf().equals("")){ 
			//surf 				= surf;
			tooth		= servicePatModel.getSurf_tooth();
			tooth_range 	= "";
		}else if(!servicePatModel.getQuadrant().equals("")){ 
			tooth		= servicePatModel.getQuadrant();
			tooth_range 	= "";
		}else if(!servicePatModel.getArch().equals("")){ 
			surf 				= "";
			tooth		= servicePatModel.getArch();
			tooth_range 	= "";
		}else{
			// Mouth, SexTant
			surf 				= "";
			tooth		= "True";
			tooth_range 	= "";
		}
		
		String sql = "insert into treatment_plandetail "
				+ "(treatment_planid, treatment_code, surf, tooth, "
				+ "tooth_range, detail_status, create_datetime, create_by) "
				+ "VALUES ("+treatment_planid+", '"+servicePatModel.getTreatment_code()+"', '"+servicePatModel.getSurf()+"', '"+tooth+"', "
						+ "'"+tooth_range+"', "+2+", now(), '"+create_by+"')";
		
		boolean addStatus = false;
		
		try {
			
			Connection conn = agent.getConnectMYSql();
			Statement Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				addStatus = true;
			}
			
			Stmt.close();
			conn.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return addStatus;
	}
	
	public boolean hasDeleteDetailTreatmentPlan(TreatmentPlanModel treatPlanModel){
		String sql = "delete from treatment_plandetail where treatment_planid = '"+treatPlanModel.getTreatment_planid()+"' ";
				if(treatPlanModel.getTreatment_code() != null){
					sql += "and treatment_code = '"+treatPlanModel.getTreatment_code()+"'";
				}
					
		
		boolean addStatus = false;
		
		try {
			
			
			
			
			Connection conn = agent.getConnectMYSql();
			Statement Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				addStatus = true;
			}
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return addStatus;
	}
	
	public boolean hasUpdateHeaderTreatmentPlan(TreatmentPlanModel treatPlanModel){
		String sql = "update treatment_plan set treatment_planname = '"+treatPlanModel.getTreatmentPlanname()+"' "
						+ "where treatment_planid = '"+treatPlanModel.getTreatment_planid()+"'";
		
		boolean updateStatus = false;
		
		try {
			
			
			
			
			Connection conn = agent.getConnectMYSql();
			Statement Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				updateStatus = true;
			}
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateStatus;
	}
	
	public boolean hasdeleteHeaderTreatmentPlan(TreatmentPlanModel treatPlanModel){
		String sql = "delete from treatment_plan where treatment_planid = '"+treatPlanModel.getTreatment_planid()+"'";
		
		boolean deleteStatus = false;
		
		hasDeleteDetailTreatmentPlan(treatPlanModel);
		try {
			Connection conn = agent.getConnectMYSql();
			Statement Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				
				deleteStatus = true;
			}
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleteStatus;
	}
	
	public boolean changePlanHeaderStatusToActive(TreatmentPlanModel treatPlanModel){
		String sql = "update treatment_plan set header_status = '1' where treatment_planid = '"+treatPlanModel.getTreatment_planid()+"'";
		
		boolean changeStatus = false;
		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				
				changeStatus = true;
			}
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return changeStatus;
	}
	
	public boolean changePlanHeaderStatusToDeactive(TreatmentPlanModel treatPlanModel){
		String sql = "update treatment_plan set header_status = '2' where hn = '"+treatPlanModel.getHn()+"'";
		
		boolean changeStatus = false;
		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement Stmt = conn.createStatement();
			if(Stmt.executeUpdate(sql) > 0){
				
				changeStatus = true;
			}
			Stmt.close();
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return changeStatus;
	}
}
