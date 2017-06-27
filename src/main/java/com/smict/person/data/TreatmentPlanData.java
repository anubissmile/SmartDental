package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ldc.util.Auth;
import ldc.util.DBConnect;
import ldc.util.DateUtil;

import com.smict.all.model.ServicePatientModel;
import com.smict.all.model.TreatmentPlanModel;
import com.smict.treatment.model.TreatmentModel;;

public class TreatmentPlanData {
	DBConnect agent = new DBConnect();
	DateUtil dateUtil = new DateUtil();
	
	public boolean updateDateTime(String planId){
		/**
		 * UPDATE UPDATE DATETIME AT TREATMENT PLAN TABLE.
		 */
		String SQL = "UPDATE `treatment_plan` SET `update_datetime`= NOW() WHERE (`treatment_planid`='" + planId + "')";
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		return (rec > 0) ? true : false;
	}
	
	public List<TreatmentPlanModel> getListTreatmentPlanHeader(TreatmentPlanModel treatPlanModel){
		List<TreatmentPlanModel> listPlan = new ArrayList<TreatmentPlanModel>();
		String sql = "SELECT treatPlan.treatment_planid, "
				+ "treatPlan.hn, "
				+ "treatPlan.treatment_planname, "
				+ "treatPlan.create_datetime, "
				+ "treatPlan.update_datetime, "
				+ "treatPlan.header_status, "
				+ "treatPlan.doctor_id, "
				+ "doctor.doctor_id, "
				+ "doctor.pre_name_id, "
				+ "doctor.first_name_th, "
				+ "doctor.last_name_th, "
				+ "doctor.first_name_en, "
				+ "doctor.last_name_en, "
				+ "sysStatus.status_name "
				+ "FROM treatment_plan AS treatPlan "
				+ "INNER JOIN system_status AS sysStatus ON (treatPlan.header_status = sysStatus.status_id) "
				+ "LEFT JOIN doctor ON treatPlan.doctor_id = doctor.doctor_id "
				+ "WHERE hn = '" + treatPlanModel.getHn() + "'";
		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TreatmentPlanModel aTreatmentPlanModel = new TreatmentPlanModel();
				aTreatmentPlanModel.setTreatment_planid(rs.getInt("treatment_planid"));
				aTreatmentPlanModel.setTreatmentPlanname(rs.getString("treatment_planname"));
				aTreatmentPlanModel.setCreateDatetime(rs.getDate("create_datetime"));
				aTreatmentPlanModel.setUpdateDatetime(rs.getDate("update_datetime"));
				aTreatmentPlanModel.setHeaderStatus(rs.getString("header_status"));
				aTreatmentPlanModel.setFirstNameTH(rs.getString("first_name_th"));
				aTreatmentPlanModel.setLastNameTH(rs.getString("last_name_th"));
				aTreatmentPlanModel.setFirstNamtEN(rs.getString("first_name_en"));
				aTreatmentPlanModel.setLastNameEN(rs.getString("last_name_en"));
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
		String sql = "SELECT treatPlanDetail.id ,treatPlan.treatment_planid, treatPlan.hn, treatPlan.treatment_planname, treatPlanDetail.treatment_id,"
				+ "treatPlanDetail.surf, treatPlanDetail.tooth, treatPlanDetail.tooth_type_id, system_status.status_name,"
				+ "system_status.status_id, treatPlanDetail.create_datetime, treatPlanDetail.create_by,treatMent.nameth, "
				+ "treatMent.nameen, treatment_pricelist.amount "
				+ "FROM "
				+ "treatment_plan AS treatPlan "
				+ "INNER JOIN treatment_plandetail AS treatPlanDetail ON treatPlanDetail.treatment_planid = treatPlan.treatment_planid "
				+ "INNER JOIN system_status ON system_status.status_id = treatPlanDetail.detail_status "
				+ "INNER JOIN treatment_master AS treatMent ON treatMent.id = treatPlanDetail.treatment_id "
				+ "INNER JOIN treatment_pricelist ON treatMent.id = treatment_pricelist.treatment_id "
				+ "where treatPlan.treatment_planid = "+treatPlanModel.getTreatment_planid()+" "
				+ "AND treatment_pricelist.price_typeid = '1' "
				+ "AND treatment_pricelist.brand_id = (SELECT brand_id FROM branch where branch_id = '"+Auth.user().getBranchID()+"') "
				+ "GROUP BY treatMent.id ";
		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TreatmentPlanModel aTreatmentPlanModel = new TreatmentPlanModel();
				aTreatmentPlanModel.setTreatament_plandetail_ID(rs.getInt("treatPlanDetail.id"));
				aTreatmentPlanModel.setTreatment_planid(rs.getInt("treatment_planid"));
				aTreatmentPlanModel.setTreatmentPlanname(rs.getString("treatment_planname"));
				aTreatmentPlanModel.setTreatment_code(rs.getString("treatment_id"));
				aTreatmentPlanModel.setTreatment_nameth(rs.getString("nameth"));
				aTreatmentPlanModel.setTreatment_nameen(rs.getString("nameen"));
				aTreatmentPlanModel.setPrice(rs.getString("treatment_pricelist.amount"));
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
	public List<TreatmentPlanModel> getListTreatmentPlanforTreatment(String hn){
		List<TreatmentPlanModel> listPlanDetail = new ArrayList<TreatmentPlanModel>();
		String sql = "SELECT treatPlan.treatment_planid, treatPlan.hn, treatPlan.treatment_planname, treatPlanDetail.treatment_id,"
				+ "treatPlanDetail.surf, treatPlanDetail.tooth, treatPlanDetail.tooth_type_id, system_status.status_name,"
				+ "system_status.status_id, treatPlanDetail.create_datetime, treatPlanDetail.create_by,treatMent.nameth, "
				+ "treatMent.nameen, treatment_pricelist.amount,treatMent.id,treatMent.code,treatMent.is_continue,treatPlanDetail.id "
				+ "FROM "
				+ "treatment_plan AS treatPlan "
				+ "INNER JOIN treatment_plandetail AS treatPlanDetail ON treatPlanDetail.treatment_planid = treatPlan.treatment_planid "
				+ "INNER JOIN system_status ON system_status.status_id = treatPlanDetail.detail_status "
				+ "INNER JOIN treatment_master AS treatMent ON treatMent.id = treatPlanDetail.treatment_id "
				+ "INNER JOIN treatment_pricelist ON treatMent.id = treatment_pricelist.treatment_id "
				+ "where treatPlan.hn = '"+hn+"' AND treatPlan.header_status ='1' "
				+ "AND treatment_pricelist.price_typeid = '1' AND treatPlanDetail.detail_status = '2' "
				+ "AND treatment_pricelist.brand_id = (SELECT brand_id FROM branch where branch_id = '"+Auth.user().getBranchID()+"') "
				+ "GROUP BY treatMent.id ";
		
		try {
			Connection conn = agent.getConnectMYSql();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				TreatmentPlanModel aTreatmentPlanModel = new TreatmentPlanModel();
				aTreatmentPlanModel.setTreatament_plandetail_ID(rs.getInt("treatPlanDetail.id"));
				aTreatmentPlanModel.setTooth(rs.getString("treatPlanDetail.tooth"));
				aTreatmentPlanModel.setSurf(rs.getString("treatPlanDetail.surf"));
				aTreatmentPlanModel.setTooth_type(rs.getString("treatPlanDetail.tooth_type_id"));
				aTreatmentPlanModel.setTreatment_planid(rs.getInt("treatment_planid"));
				aTreatmentPlanModel.setTreatment_id(rs.getString("treatMent.id"));
				aTreatmentPlanModel.setTreatmentPlanname(rs.getString("treatment_planname"));
				aTreatmentPlanModel.setTreatment_code(rs.getString("treatMent.code"));
				aTreatmentPlanModel.setTreatment_nameth(rs.getString("nameth"));
				aTreatmentPlanModel.setTreatment_nameen(rs.getString("nameen"));
				aTreatmentPlanModel.setPrice(rs.getString("treatment_pricelist.amount"));
				aTreatmentPlanModel.setCreateDatetime(rs.getDate("create_datetime"));
				aTreatmentPlanModel.setDetailStatus(rs.getString("status_id"));
				aTreatmentPlanModel.setDetailStatusName(rs.getString("status_name"));
				aTreatmentPlanModel.setTreatment_iscon(rs.getString("treatMent.is_continue"));
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
				+ "(hn, treatment_planname, create_datetime, update_datetime, header_status, doctor_id) "
				+ "VALUES ('"+aTreatmentPlanModel.getHn()+"','"+aTreatmentPlanModel.getTreatmentPlanname()+"',now(), now(), 2, " + aTreatmentPlanModel.getDoctorId() + ")";
		
		agent.connectMySQL();
		int rec = agent.exeUpdate(sql);
		agent.disconnectMySQL();
		return (rec > 0) ? true : false;
	}

	public boolean addDetailTreatmentPlan(int treatment_planid, TreatmentModel treatModel){
		
		String SQL ="INSERT INTO treatment_plandetail "
				+ "(treatment_id,treatment_planid,detail_status,create_datetime,create_by ";
				if(!treatModel.getSurface().equals("")){
					SQL +=",surf ";
				}
				if(!treatModel.getTooth().equals("")){
					SQL +=",tooth ";
				}else if(!treatModel.getSurface_tooth().equals("")){
					SQL +=",tooth ";
				}else if(!treatModel.getToothRange().equals("")){
					SQL +=",tooth ";
				}else if(treatModel.getQuadrant()!=null){
					SQL +=",tooth ";
				}else if(treatModel.getArch()!=null){
					SQL +=",tooth ";
				}
				SQL+= ",tooth_type_id) "
				+ "VALUES "
				+ "('"+treatModel.getTreatment_ID()+"','"+treatment_planid+"' "
				+ ",2,now(),'"+Auth.user().getEmpUsr()+"' ";
				if(!treatModel.getSurface().equals("")){
					SQL +=",'"+treatModel.getSurface()+"' ";
				}
				if(!treatModel.getTooth().equals("")){
					SQL +=",'"+treatModel.getTooth()+"' ";
				}else if(!treatModel.getSurface_tooth().equals("")){
					SQL +=",'"+treatModel.getSurface_tooth()+"' ";
				}else if(!treatModel.getToothRange().equals("")){
					SQL +=",'"+treatModel.getToothRange()+"' ";
				}else if(treatModel.getQuadrant()!=null){
					SQL +=",'"+treatModel.getQuadrant()+"' ";
				}else if(treatModel.getArch()!=null){
					SQL +=",'"+treatModel.getArch()+"' ";
				}
				SQL += ",'"+treatModel.getTooth_types()+"')";
		
		
		
		agent.connectMySQL();
		int rec = agent.exeUpdate(SQL);
		agent.disconnectMySQL();
		if(rec > 0){
			/**
			 * UPDATE UPDATE DATETIME AT TREATMENT PLAN TABLE.
			 */
			String sql = "UPDATE `treatment_plan` SET `update_datetime`= NOW() WHERE (`treatment_planid`='" + treatment_planid + "')";
			agent.connectMySQL();
			rec = agent.exeUpdate(sql);
			agent.disconnectMySQL();
			return true;
		}
		return false;
	}
	
	public boolean hasDeleteDetailTreatmentPlan(TreatmentPlanModel treatPlanModel){
		String sql = "delete from treatment_plandetail where treatment_planid = '"+treatPlanModel.getTreatment_planid()+"' ";
				if(treatPlanModel.getTreatment_code() != null){
					sql += "and treatment_id = '"+treatPlanModel.getTreatment_code()+"'";
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
		
		/**
		 * DELETE PLAN DETAIL
		 */
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
