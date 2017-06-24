package com.smict.person.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import com.smict.document.model.DocumentModel;
import com.smict.person.model.AddressModel;
import com.smict.person.model.BranchModel;
import com.smict.person.model.CongenitalDiseaseModel;
import com.smict.person.model.FamilyModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.TelephoneModel;
import com.smict.person.model.PatientFileIdModel;
import com.smict.product.model.ProductModel;
import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionModel;

import ldc.util.Auth;
import ldc.util.CalculateNumber;
import ldc.util.DBConnect;
import ldc.util.DateUtil;
import ldc.util.Validate;

public class PatientData {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null;
	PreparedStatement pStmt = null;
	ResultSet rs = null;
	DateUtil dateUtil = new DateUtil();
	
	public boolean hasEditPatientDetail(PatientModel patModel,String emp_id){
		String sql = "update patient set pre_name_id = '"+patModel.getPre_name_id()+"', first_name_th = '"+patModel.getFirstname_th()+"', last_name_th = '"+patModel.getLastname_th()+"', nickname = '"+patModel.getNickname()+"', "
					+ "first_name_en = '"+patModel.getFirstname_en()+"', last_name_en = '"+patModel.getLastname_en()+"', birth_date = '"+patModel.getBirth_date()+"', identification = '"+patModel.getIdentification()+"', identification_type = '"+patModel.getIdentification_type()+"', profile_pic = '"+patModel.getProfile_pic()+"', "
					+ "line_id = '"+patModel.getLine_id()+"', email = '"+patModel.getEmail()+"', update_datetime = now(), weight = '"+patModel.getWeight()+"', height = '"+patModel.getHeight()+"', bloodgroup = '"+patModel.getBloodgroup()+"', "
					+ "patient_type = '"+patModel.getPatient_type()+"', contact_time_start = '"+patModel.getContact_time_start()+"', contact_time_end = '"+patModel.getContact_time_end()+"', typerecommended = '"+patModel.getTyperecommended()+"', status_married = '"+patModel.getStatus_married()+"', "
					+ "update_by = '"+emp_id+"', confirm_brush_teeth = '"+patModel.getConfirm_brush_teeth()+"', confirm_pregnant = '"+patModel.getConfirm_pregnant()+"', week_of_pregent = "+patModel.getWeek_of_pregent()+", confirm_now_receive_drug = '"+patModel.getConfirm_now_receive_drug()+"', "
					+ "drug_name = '"+patModel.getDrug_name()+"', confirm_now_treatment = '"+patModel.getConfirm_now_treatment()+"', confirm_hospital_doctor_now_treatment = '"+patModel.getConfirm_hospital_doctor_now_treatment()+"', doctor_hospital_name = '"+patModel.getDoctor_hospital_name()+"', "
					+ "confirm_congenital = '"+patModel.getConfirm_congenital()+"', career = '"+patModel.getCareer()+"', remark= '"+patModel.getRemark()+"' "
					+ "where hn = '"+patModel.getHn()+"'";
		
		System.out.println(sql);
		boolean isEditSuccess = false;
		try {
			Connection connEdit = agent.getConnectMYSql();
			Statement stmtEdit = connEdit.createStatement();
			if(stmtEdit.executeUpdate(sql) > 0){
				isEditSuccess = true;
			}
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return isEditSuccess;
	}
	
	public String Add_Patient(PatientModel patModel,String emp_id, String branch_id){
		
		String sql = "insert into patient (hn, pre_name_id, first_name_th, last_name_th, "
				+ "first_name_en, last_name_en,nickname, birth_date, identification, "
				+ "identification_type, relation_emp, register_branch, remark, "
				+ "profile_pic, deposit_money, status_married, line_id, "
				+ "email, bloodgroup, patient_type, contact_time_start, "
				+ "contact_time_end, weight, height, typerecommended, "
				+ "create_by, create_datetime, confirm_brush_teeth, confirm_pregnant,"
				+ "week_of_pregent, confirm_now_receive_drug, drug_name, confirm_now_treatment,"
				+ "confirm_hospital_doctor_now_treatment, doctor_hospital_name, confirm_congenital, tel_id, "
				+ "addr_id, patneed_id, pat_congenital_disease_id, career ) "
				+ "values "
				+ "( ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, "
				+ "?, ?, ?, ?, "
				+ "?, ?, ?, ?, "
				+ "?, ?, ?, ?, "
				+ "?, now(), ?, ?, "
				+ "?, ?, ?, ?, "
				+ "?, ?, ?, ?, "
				+ "?, ?, ?, ? )";
		
		String patient_id = "";
		try {
			String mirror_patient_id = PlusOneID_FormatID(GetHighest_HN());
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mirror_patient_id);
			pStmt.setString(2, patModel.getPre_name_id());
			pStmt.setString(3, patModel.getFirstname_th());
			pStmt.setString(4, patModel.getLastname_th()); //1
			pStmt.setString(5, patModel.getFirstname_en());
			pStmt.setString(6, patModel.getLastname_en());
			pStmt.setString(7, patModel.getNickname());
			pStmt.setString(8, patModel.getBirth_date()); //BirthDate
			pStmt.setString(9, patModel.getIdentification());//2
			pStmt.setString(10, patModel.getIdentification_type());
			pStmt.setString(11, patModel.getRelation_emp());
			pStmt.setString(12, branch_id); //Branch
			pStmt.setString(13, patModel.getRemark());//
			pStmt.setString(14, patModel.getProfile_pic());
			pStmt.setDouble(15, patModel.getDeposit_money());
			pStmt.setString(16, patModel.getStatus_married());
			pStmt.setString(17, patModel.getLine_id());//3
			pStmt.setString(18, patModel.getEmail());
			pStmt.setString(19, patModel.getBloodgroup());
			pStmt.setString(20, patModel.getPatient_type());
			pStmt.setString(21, patModel.getContact_time_start());//4
			pStmt.setString(22, patModel.getContact_time_end());
			pStmt.setDouble(23, patModel.getWeight());
			pStmt.setDouble(24, patModel.getHeight());
			pStmt.setInt(25, patModel.getTyperecommended());//5
			pStmt.setString(26, emp_id); //EMPID
			pStmt.setString(27, patModel.getConfirm_brush_teeth());
			pStmt.setString(28, patModel.getConfirm_pregnant());
			pStmt.setInt(29, patModel.getWeek_of_pregent());//6
			pStmt.setString(30, patModel.getConfirm_now_receive_drug());
			pStmt.setString(31, patModel.getDrug_name());
			pStmt.setString(32, patModel.getConfirm_now_treatment());
			pStmt.setString(33, patModel.getConfirm_hospital_doctor_now_treatment());//7
			pStmt.setString(34, patModel.getDoctor_hospital_name());
			pStmt.setString(35, patModel.getConfirm_congenital());
			pStmt.setInt(36, patModel.getTel_id());
			pStmt.setInt(37, patModel.getAddr_id());
			pStmt.setInt(38, patModel.getPatneed_id());
			pStmt.setInt(39, patModel.getPat_congenital_disease_id());
			pStmt.setString(40, patModel.getCareer());
			
			patient_id = pStmt.executeUpdate() > 0 ? mirror_patient_id : "" ;
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patient_id;
	}
	
	public void Update_Patient(PatientModel patModel){
		
		String sql = "update patient set pre_name_id = ?, firstname_th = ?, lastname_th = ?,"
				+ "firstname_en = ?, lastname_en = ?, birth_date = ?, identification = ?,"
				+ "identification_type = ?, relation_emp = ?, register_branch = ?, remark = ?,"
				+ "profile_pic = ?, deposit_money = ? "
				+ "where hn = ? ";
		
		try {
			
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(17, patModel.getHn());
			pStmt.setString(1, "02");
			pStmt.setString(2, patModel.getFirstname_th());
			pStmt.setString(3, patModel.getLastname_th());
			pStmt.setString(4, patModel.getFirstname_en());
			pStmt.setString(5, patModel.getLastname_en());
			pStmt.setDate(6, java.sql.Date.valueOf("2005-05-21"));
			pStmt.setString(7, patModel.getIdentification());
			pStmt.setString(8, patModel.getIdentification_type());
			pStmt.setString(10, patModel.getRelation_emp());
			pStmt.setString(11, "00010");
			pStmt.setString(12, patModel.getRemark());
			pStmt.setString(13, patModel.getProfile_pic());
			pStmt.setDouble(14, patModel.getDeposit_money());
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void Delete_Patient(PatientModel patModel){
		
		String sql = "delete from patient where hn = ?";
	//	System.out.println(patModel.getHn());
		try {
			
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, patModel.getHn());
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<AddressModel> getPatientAddr(String HN){
		String SQL = "SELECT patient.hn, address.addr_id, "
				+ "address.addr_no, address.addr_bloc, "
				+ "address.addr_village, address.addr_alley, "
				+ "address.addr_road, address.addr_provinceid, "
				+ "address.addr_aumphurid, address.addr_districtid, "
				+ "address.addr_zipcode, address.addr_typeid, "
				+ "address_type.addr_typeid, address_type.addr_typename, "
				+ "districts.DISTRICT_ID, districts.DISTRICT_CODE, "
				+ "districts.DISTRICT_NAME, districts.AMPHUR_ID, "
				+ "districts.PROVINCE_ID, districts.GEO_ID, "
				+ "amphures.AMPHUR_ID, amphures.AMPHUR_CODE, "
				+ "amphures.AMPHUR_NAME, amphures.GEO_ID, "
				+ "amphures.PROVINCE_ID, provinces.PROVINCE_ID, "
				+ "provinces.PROVINCE_CODE, provinces.PROVINCE_NAME, "
				+ "provinces.GEO_ID, zipcodes.ZIPCODE_ID, "
				+ "zipcodes.DISTRICT_CODE, zipcodes.ZIPCODE "
				+ "FROM patient "
				+ "LEFT JOIN address ON patient.addr_id = address.addr_id "
				+ "LEFT JOIN address_type ON address.addr_typeid = address_type.addr_typeid "
				+ "LEFT JOIN districts ON address.addr_districtid = districts.DISTRICT_ID "
				+ "LEFT JOIN amphures ON address.addr_aumphurid = amphures.AMPHUR_ID "
				+ "LEFT JOIN provinces ON address.addr_provinceid = provinces.PROVINCE_ID "
				+ "LEFT JOIN zipcodes ON districts.DISTRICT_ID = zipcodes.DISTRICT_ID "
				+ "WHERE patient.hn = '" + HN + "' ";
		
		try {
			agent.connectMySQL();
			agent.exeQuery(SQL);
			List<AddressModel> addrList = new ArrayList<AddressModel>();
			while(agent.getRs().next()){
				AddressModel addrModel = new AddressModel();
				addrModel.setAddr_zipcode(agent.getRs().getString("zipcodes.zipcode"));
				addrModel.setAddr_province_name(agent.getRs().getString("PROVINCE_NAME"));
				addrModel.setAddr_provinceid(agent.getRs().getString("PROVINCE_ID"));
				addrModel.setAddr_aumphur_name(agent.getRs().getString("AMPHUR_NAME"));
				addrModel.setAddr_aumphurid(agent.getRs().getString("AMPHUR_ID"));
				addrModel.setAddr_district_name(agent.getRs().getString("DISTRICT_NAME"));
				addrModel.setAddr_districtid(agent.getRs().getString("DISTRICT_ID"));
				addrModel.setAddr_typename(agent.getRs().getString("addr_typename"));
				addrModel.setAddr_typeid(agent.getRs().getString("addr_typeid"));
				addrModel.setAddr_road(agent.getRs().getString("addr_road"));
				addrModel.setAddr_alley(agent.getRs().getString("addr_alley"));
				addrModel.setAddr_village(agent.getRs().getString("addr_village"));
				addrModel.setAddr_bloc(agent.getRs().getString("addr_bloc"));
				addrModel.setAddr_no(agent.getRs().getString("addr_no"));
				addrModel.setNew_addr_id(agent.getRs().getInt("addr_id"));
				
				addrList.add(addrModel);
			}
			agent.disconnectMySQL();
			
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<TelephoneModel> getPatientPhone(String HN){
		String SQL = "SELECT patient.hn, patient.tel_id, "
				+ "tel_telephone.tel_id, tel_telephone.tel_number, "
				+ "tel_telephone.tel_typeid, tel_teltype.tel_typeid, "
				+ "tel_teltype.tel_typename, tel_telephone.tel_relevant_person, "
				+ "tel_telephone.tel_relative "
				+ "FROM patient "
				+ "INNER JOIN tel_telephone ON patient.tel_id = tel_telephone.tel_id "
				+ "INNER JOIN tel_teltype ON tel_telephone.tel_typeid = tel_teltype.tel_typeid "
				+ "WHERE patient.hn = '" + HN + "' AND tel_telephone.tel_typeid != 5 ";
		
		try {
			agent.connectMySQL();
			agent.exeQuery(SQL);
			
			List<TelephoneModel> tList = new ArrayList<TelephoneModel>();
			while(agent.getRs().next()){
				TelephoneModel tModel = new TelephoneModel();
				tModel.setTel_id(agent.getRs().getInt("patient.tel_id"));
				tModel.setTel_typeid(agent.getRs().getInt("tel_telephone.tel_typeid"));
				tModel.setTel_typename(agent.getRs().getString("tel_teltype.tel_typename"));
				tModel.setTel_number(agent.getRs().getString("tel_telephone.tel_number"));
				tModel.setRelevant_person(agent.getRs().getString("tel_telephone.tel_relevant_person"));
				tModel.setTel_relative(agent.getRs().getString("tel_telephone.tel_relative"));
				tList.add(tModel);
			}
			agent.disconnectMySQL();
			return tList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getPatientNeed(String HN){
		String SQL = "SELECT "
				+ "patient.hn, "
				+ "patient_need.patneed_id, "
				+ "patient_need.patneed_message "
				+ "FROM patient "
				+ "LEFT JOIN patient_need ON patient.patneed_id = patient_need.patneed_id "
				+ "WHERE patient.hn = '" + HN + "'";
		System.out.println(SQL);
		try {
			agent.connectMySQL();
			agent.exeQuery(SQL);
			System.out.println(agent.size());
			String[] sr = new String[agent.size()];
			int i = 0;
			while(agent.getRs().next()){
				sr[i] = agent.getRs().getString("patneed_message");
				++i;
			}
			agent.disconnectMySQL();
			return sr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public PatientModel selectPatientByHN(String HN){
		String SQL = "SELECT a.hn, "
				+ "a.pre_name_id, "
				+ "prename.pre_name_th, "
				+ "prename.pre_name_en, "
				+ "a.first_name_th, "
				+ "a.last_name_th, "
				+ "a.nickname, "
				+ "a.first_name_en, "
				+ "a.last_name_en, "
				+ "a.birth_date, "
				+ "a.identification, "
				+ "a.identification_type, "
				+ "a.relation_emp, "
				+ "a.register_branch, "
				+ "a.remark, "
				+ "a.profile_pic, "
				+ "a.deposit_money, "
				+ "status_married, "
				+ "line_id, "
				+ "email, "
				+ "bloodgroup, "
				+ "patient_type.patient_type, "
				+ "contact_time_start, "
				+ "contact_time_end, "
				+ "weight, height, "
				+ "typerecommended, "
				+ "tel_id, addr_id, "
				+ "patient_type.patient_typename, "
				+ "patneed_id, "
				+ "pat_congenital_disease_id, "
				+ "career "
				+ "FROM patient AS a "
				+ "INNER JOIN pre_name prename on (a.pre_name_id = prename.pre_name_id) "
				+ "LEFT JOIN patient_type on (a.patient_type = patient_type.patient_type) "
				+ "where a.hn like '%" + HN + "%' and a.hn != ''";
		
		try {
			agent.connectMySQL();
			rs = agent.exeQuery(SQL);
			PatientModel pModel = new PatientModel();
			while(rs.next()){
				pModel.setHn(rs.getString("hn"));
				pModel.setPre_name_id(rs.getString("pre_name_id"));
				pModel.setPre_name_th(rs.getString("pre_name_th"));
				pModel.setPre_name_en(rs.getString("pre_name_en"));
				pModel.setFirstname_th(rs.getString("first_name_th"));
				pModel.setLastname_th(rs.getString("last_name_th"));
				pModel.setFirstname_en(rs.getString("first_name_en"));
				pModel.setLastname_en(rs.getString("last_name_en"));
				pModel.setNickname(rs.getString("nickname"));
				pModel.setBirth_date(rs.getString("birth_date"));
				pModel.setIdentification(rs.getString("identification"));
				pModel.setIdentification_type(rs.getString("identification_type"));
				pModel.setRelation_emp(rs.getString("relation_emp"));
				pModel.setRegister_branch(rs.getString("register_branch"));
				pModel.setRemark(rs.getString("remark"));
				pModel.setProfile_pic(rs.getString("profile_pic"));
				pModel.setDeposit_money(rs.getInt("deposit_money"));
				pModel.setStatus_married(rs.getString("status_married"));
				pModel.setLine_id(rs.getString("line_id"));
				pModel.setEmail(rs.getString("email"));
				pModel.setBloodgroup(rs.getString("bloodgroup"));
				pModel.setPatient_type(rs.getString("patient_type"));
				pModel.setContact_time_start(rs.getString("contact_time_start"));
				pModel.setContact_time_end(rs.getString("contact_time_end"));
				pModel.setWeight(rs.getDouble("weight"));
				pModel.setHeight(rs.getDouble("height"));
				pModel.setTyperecommended(rs.getInt("typerecommended"));
				pModel.setTel_id(rs.getInt("tel_id"));
				pModel.setAddr_id(rs.getInt("addr_id"));
				pModel.setPatient_type_name(rs.getString("patient_typename"));
				pModel.setPatneed_id(rs.getInt("patneed_id"));
				pModel.setPat_congenital_disease_id(rs.getInt("pat_congenital_disease_id"));
				pModel.setCareer(rs.getString("career"));
			}
			agent.disconnectMySQL();
			return pModel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Search patient by hn_code or first and last name in th & en or ID.
	 * @author anubissmile
	 * @param PatientModel patModel
	 * @return List<PatientModel>
	 */
	public List<PatientModel> searchPatient(PatientModel patModel){
		String search = patModel.getSearchPat();
		String SQL = "SELECT patient.hn, patient.pre_name_id, "
				+ "patient.first_name_th, patient.last_name_th, "
				+ "patient.nickname, patient.first_name_en, "
				+ "patient.last_name_en, patient.identification, "
				+ "patient_file_id.hn, patient_file_id.branch_hn, "
				+ "patient_file_id.branch_id "
				+ "FROM patient "
				+ "LEFT JOIN patient_file_id ON patient.hn = patient_file_id.hn AND patient_file_id.branch_id = '" + Auth.user().getBranchID() + "' "
				+ "WHERE patient.first_name_th LIKE '%" + search + "%' OR "
				+ "patient.last_name_th LIKE '%" + search + "%' OR "
				+ "patient.first_name_en LIKE '%" + search + "%' OR "
				+ "patient.last_name_en LIKE '%" + search + "%' OR "
				+ "patient.identification = '" + search + "' OR "
				+ "patient.hn LIKE '%" + search + "%' OR "
				+ "patient_file_id.branch_hn LIKE '%" + search + "%' "
				+ "GROUP BY patient.hn ";
		
		try {
			agent.connectMySQL();
			rs = agent.exeQuery(SQL);
			List<PatientModel> patientList = new ArrayList<PatientModel>();
			
			while(rs.next()){
				PatientModel pModel = new PatientModel();
				pModel.setHn(rs.getString("hn"));
				if(rs.getString("branch_hn") == null){
					pModel.setHnBranch("N/A");
				}else{
					pModel.setHnBranch(rs.getString("branch_hn"));
				}
				pModel.setFirstname_th(rs.getString("first_name_th"));
				pModel.setLastname_th(rs.getString("last_name_th"));
				pModel.setFirstname_en(rs.getString("first_name_en"));
				pModel.setLastname_en(rs.getString("last_name_en"));
				pModel.setNickname(rs.getString("nickname"));
				if(rs.getString("identification").isEmpty() || rs.getString("identification") == null){
					pModel.setIdentification("N/A");
				}else{
					pModel.setIdentification(rs.getString("identification"));
				}
				patientList.add(pModel);
			}
			agent.disconnectMySQL();
			return patientList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String[] getPatientCongenitalDisease(String HN){
		String SQL = "SELECT "
				+ "patient.hn, "
				+ "patient.pat_congenital_disease_id, "
				+ "patient_congenital_disease.pat_congenital_disease_id, "
				+ "patient_congenital_disease.congenital_id, "
				+ "patient_congenital_disease.congenital_name_th, "
				+ "patient_congenital_disease.congenital_name_en "
				+ "FROM patient "
				+ "INNER JOIN patient_congenital_disease ON patient.pat_congenital_disease_id = patient_congenital_disease.pat_congenital_disease_id "
				+ "WHERE patient.hn = '" + HN + "'";
		
		try {
			agent.connectMySQL();
			agent.exeQuery(SQL);
			String[] str = new String[agent.size()];
			if(agent.size()>0){
				int i = 0;
				while(agent.getRs().next()){
					str[i] = agent.getRs().getString("congenital_name_th");
					++i;
				}
			}
			agent.disconnectMySQL();
			return  str;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * Get the patient's allergic product list.
	 * @author anubissmile
	 * @param HN | Patient's HN Code
	 * @return List<ProductModel>
	 */
	public List<ProductModel> getPatientBeAllergic(String HN){
		String SQL = "SELECT "
				+ "patient.hn, "
				+ "patient_beallergic.be_allergic_id, "
				+ "patient_beallergic.product_id, "
				+ "pro_product.product_id, "
				+ "pro_product.product_name, "
				+ "pro_product.product_name_en, "
				+ "pro_product.price, "
				+ "pro_product.create_by, "
				+ "pro_product.create_datetime, "
				+ "pro_product.update_by, "
				+ "pro_product.update_datetime, "
				+ "pro_product.productunit_id, "
				+ "pro_product.producttype_id, "
				+ "pro_product.productgroup_id, "
				+ "pro_product.productbrand_id "
				+ "FROM patient "
				+ "INNER JOIN patient_beallergic ON patient.hn = patient_beallergic.hn "
				+ "INNER JOIN pro_product ON patient_beallergic.product_id = pro_product.product_id "
				+ "WHERE patient.hn = '" + HN + "'";
		
		System.out.println("-----------------------------------------------\n" + SQL);
		
		try {
			agent.connectMySQL();
			agent.exeQuery(SQL);
			List<ProductModel> productList= new ArrayList<ProductModel>();
			while(agent.getRs().next()){
				ProductModel pModel = new ProductModel();
				pModel.setProduct_id(agent.getRs().getInt("product_id"));
				pModel.setProduct_name(agent.getRs().getString("product_name"));
				pModel.setProduct_name_en(agent.getRs().getString("product_name_en"));
				pModel.setPrice(agent.getRs().getDouble("price"));
				productList.add(pModel);
			}
			agent.disconnectMySQL();
			return productList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<PatientModel> getListPatModelForTovNav(PatientModel patModel){
		
		String sql = "SELECT "
				+ "a.hn, a.pre_name_id, prename.pre_name_th, a.first_name_th, "
				+ "a.last_name_th, a.nickname, a.first_name_en, a.last_name_en, "
				+ "a.birth_date, a.identification, a.identification_type, a.relation_emp, "
				+ "a.register_branch, a.remark, a.deposit_money, "
				+ "status_married, line_id, email, bloodgroup, "
				+ "patient_type, contact_time_start, contact_time_end, weight, "
				+ "height, typerecommended, tel_id, addr_id, "
				+ "patneed_id, pat_congenital_disease_id, promotion_sub_contact.sub_contact_id, promotion_sub_contact.sub_contact_name "
				+ "FROM patient AS a "
				+ "INNER JOIN pre_name prename on (a.pre_name_id = prename.pre_name_id) "
				+ "LEFT JOIN promotion_sub_contact on (a.patient_type = promotion_sub_contact.sub_contact_id) "
				+ "where ";
				
				Validate classvalidate = new Validate();
				String hn = "";
				if(patModel != null) hn = patModel.getHn();

				/*
				if(classvalidate.Check_String_notnull_notempty(hn))
					sql += "a.hn like '%"+hn+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getFirstname_th()))
					sql += "a.firstname_th like '%"+patModel.getFirstname_th()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getFirstname_en()))
					sql += "a.firstname_en like '%"+patModel.getFirstname_en()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getLastname_th()))
					sql += "a.lastname_th like '%"+patModel.getLastname_th()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getLastname_en()))
					sql += "a.lastname_en like '%"+patModel.getLastname_en()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getBirth_date()))
					sql += "a.birth_date = '"+patModel.getBirth_date()+"' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getIdentification()))
					sql += "a.identification = '"+patModel.getIdentification()+"' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getIdentification_type()))
					sql += "a.identification_type = '"+patModel.getIdentification_type()+"' and ";
				*/
				sql += "a.hn != '"+hn+"' ";
				
				List <PatientModel> resultList = new ArrayList<PatientModel>();
				FamilyData famData = new FamilyData();
				try {
					
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					rs = Stmt.executeQuery(sql);
					String[] hnFormat = new String[2];
					while (rs.next()) {
						PatientModel makePatModel = new PatientModel();
						hnFormat[0] = rs.getString("hn").substring(0, 3);
						hnFormat[1] = rs.getString("hn").substring(3, rs.getString("hn").length());
						makePatModel.setHnFormat(hnFormat[0] + "-" + hnFormat[1]);
						makePatModel.setHn(rs.getString("hn"));
						makePatModel.setPre_name_id(rs.getString("pre_name_id"));
						makePatModel.setPre_name(rs.getString("pre_name_th"));
						makePatModel.setFirstname_th(rs.getString("first_name_th"));
						makePatModel.setLastname_th(rs.getString("last_name_th"));
						makePatModel.setFirstname_en(rs.getString("first_name_en"));
						makePatModel.setLastname_en(rs.getString("last_name_en"));
						makePatModel.setBirth_date(rs.getString("birth_date"));
						makePatModel.setIdentification(rs.getString("identification"));
						makePatModel.setIdentification_type(rs.getString("identification_type"));
						makePatModel.setRelation_emp(rs.getString("relation_emp"));
						makePatModel.setRegister_branch(rs.getString("register_branch"));
						makePatModel.setRemark(rs.getString("remark"));
						makePatModel.setDeposit_money(rs.getDouble("deposit_money"));
						makePatModel.setStatus_married(rs.getString("status_married"));
						makePatModel.setLine_id(rs.getString("line_id"));
						makePatModel.setEmail(rs.getString("email"));
						makePatModel.setNickname(rs.getString("nickname"));
						makePatModel.setBloodgroup(rs.getString("bloodgroup"));
						makePatModel.setPatient_type(rs.getString("patient_type"));
						makePatModel.setContact_time_start(rs.getString("contact_time_start"));
						makePatModel.setContact_time_end(rs.getString("contact_time_end"));
						makePatModel.setWeight(rs.getDouble("weight"));
						makePatModel.setHeight(rs.getDouble("height"));
						makePatModel.setPatneed_id(rs.getInt("patneed_id"));
						makePatModel.setTyperecommended(rs.getInt("typerecommended"));
						makePatModel.setPatient_type_name(rs.getString("sub_contact_name"));
						makePatModel.setAddr_id(rs.getInt("addr_id"));
						makePatModel.setTel_id(rs.getInt("tel_id"));
						
						makePatModel.setPat_congenital_disease_id(rs.getInt("pat_congenital_disease_id"));
						makePatModel.setFam_id(famData.getPatFamilyID(makePatModel.getHn(), 2));
						resultList.add(makePatModel);
						
					}
					
					if(!rs.isClosed()) rs.close();
					if(!Stmt.isClosed()) Stmt.close();
					if(!conn.isClosed()) conn.close();
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
		return resultList;
	}
	
	public List<PatientModel> select_patient(PatientModel patModel){
		
		String sql = "SELECT "
				+ "a.hn, a.pre_name_id, prename.pre_name_th, a.first_name_th, "
				+ "a.last_name_th, a.nickname, a.first_name_en, a.last_name_en, "
				+ "a.birth_date, a.identification, a.identification_type, a.relation_emp, "
				+ "a.register_branch, a.remark, a.deposit_money, "
				+ "status_married, line_id, email, bloodgroup, "
				+ "patient_type, contact_time_start, contact_time_end, weight, "
				+ "height, typerecommended, tel_id, addr_id, "
				+ "patneed_id, pat_congenital_disease_id, promotion_sub_contact.sub_contact_id, promotion_sub_contact.sub_contact_name "
				+ "FROM patient AS a "
				+ "INNER JOIN pre_name prename on (a.pre_name_id = prename.pre_name_id) "
				+ "LEFT JOIN promotion_sub_contact on (a.patient_type = promotion_sub_contact.sub_contact_id) "
				+ "where ";
				
				Validate classvalidate = new Validate();
				String hn = "";
				if(patModel != null) hn = patModel.getHn();
				
				if(classvalidate.Check_String_notnull_notempty(hn))
					sql += "a.hn like '%"+hn+"%' and ";
				/*
				if(classvalidate.Check_String_notnull_notempty(patModel.getFirstname_th()))
					sql += "a.firstname_th like '%"+patModel.getFirstname_th()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getFirstname_en()))
					sql += "a.firstname_en like '%"+patModel.getFirstname_en()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getLastname_th()))
					sql += "a.lastname_th like '%"+patModel.getLastname_th()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getLastname_en()))
					sql += "a.lastname_en like '%"+patModel.getLastname_en()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getBirth_date()))
					sql += "a.birth_date = '"+patModel.getBirth_date()+"' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getIdentification()))
					sql += "a.identification = '"+patModel.getIdentification()+"' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getIdentification_type()))
					sql += "a.identification_type = '"+patModel.getIdentification_type()+"' and ";
				*/
				sql += "a.hn != '' ";
				
				List <PatientModel> resultList = new ArrayList<PatientModel>();
				try {
					
					
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					rs = Stmt.executeQuery(sql);
					while (rs.next()) {
						PatientModel makePatModel = new PatientModel();
						makePatModel.setHn(rs.getString("hn"));
						makePatModel.setPre_name_id(rs.getString("pre_name_id"));
						makePatModel.setPre_name(rs.getString("pre_name_th"));
						makePatModel.setFirstname_th(rs.getString("first_name_th"));
						makePatModel.setLastname_th(rs.getString("last_name_th"));
						makePatModel.setFirstname_en(rs.getString("first_name_en"));
						makePatModel.setLastname_en(rs.getString("last_name_en"));
						makePatModel.setBirth_date(rs.getString("birth_date"));
						makePatModel.setIdentification(rs.getString("identification"));
						makePatModel.setIdentification_type(rs.getString("identification_type"));
						makePatModel.setRelation_emp(rs.getString("relation_emp"));
						makePatModel.setRegister_branch(rs.getString("register_branch"));
						makePatModel.setRemark(rs.getString("remark"));
						makePatModel.setDeposit_money(rs.getDouble("deposit_money"));
						makePatModel.setStatus_married(rs.getString("status_married"));
						makePatModel.setLine_id(rs.getString("line_id"));
						makePatModel.setEmail(rs.getString("email"));
						makePatModel.setNickname(rs.getString("nickname"));
						makePatModel.setBloodgroup(rs.getString("bloodgroup"));
						makePatModel.setPatient_type(rs.getString("patient_type"));
						makePatModel.setContact_time_start(rs.getString("contact_time_start"));
						makePatModel.setContact_time_end(rs.getString("contact_time_end"));
						makePatModel.setWeight(rs.getDouble("weight"));
						makePatModel.setHeight(rs.getDouble("height"));
						makePatModel.setTyperecommended(rs.getInt("typerecommended"));
						makePatModel.setPatient_type_name(rs.getString("sub_contact_name"));
						makePatModel.setAddrModel(new AddressData().getMultiAddr(rs.getInt("addr_id")));
						makePatModel.setListTelModel(new TelephoneData().getMultiple_Telephone(new TelephoneModel("","","","",rs.getInt("tel_id"),0,1)));
						int fam_id = 0;
						if(patModel != null) fam_id = patModel.getFam_id();
							
						makePatModel.setFamModel(new FamilyData().getFamModel_MemberFamilyList(fam_id, "", "", "", ""));
						makePatModel.setBeallergic(getModelList_Beallergic(patModel));
						
						int disease_id = 0;
						if(patModel != null) disease_id = patModel.getPat_congenital_disease_id();
						
						makePatModel.setCongenList(new CongenitalData().getConginentalDisease(new CongenitalDiseaseModel(0,disease_id,"","")));
						makePatModel.setPatneed_id(rs.getInt("patneed_id"));
						resultList.add(makePatModel);
						
					}
					
					if(!rs.isClosed()) rs.close();
					if(!Stmt.isClosed()) Stmt.close();
					if(!conn.isClosed()) conn.close();
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
		return resultList;
	}
	
	/**
	 * Get patient's branch hn list.
	 * @author anubissmile
	 * @param String | hn
	 * @return List<PatientModel>
	 */
	public List<PatientFileIdModel> getBranchHNList(String hn){
		String SQL = "SELECT patient_file_id.hn, "
				+ "patient_file_id.branch_hn, "
				+ "patient_file_id.branch_id "
				+ "FROM patient_file_id "
				+ "WHERE patient_file_id.hn = '" + hn + "' ";
		
		agent.connectMySQL();
		agent.exeQuery(SQL);
		try {
			List<PatientFileIdModel> patBranchHnList = new ArrayList<PatientFileIdModel>();
			while(agent.getRs().next()){
				PatientFileIdModel patFileIdModel = new PatientFileIdModel(
					agent.getRs().getString("hn"), 
					agent.getRs().getString("branch_hn"), 
					agent.getRs().getString("branch_id")
				);
				
				patBranchHnList.add(patFileIdModel);
			}
			agent.disconnectMySQL();
			return patBranchHnList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public PatientModel getPatModel_patient(PatientModel patModel){
		
		String sql = "SELECT "
				+ "a.hn, a.pre_name_id, prename.pre_name_th, prename.pre_name_en, a.first_name_th, "
				+ "a.last_name_th, a.nickname, a.first_name_en, a.last_name_en, "
				+ "a.birth_date, a.identification, a.identification_type, a.relation_emp, "
				+ "a.register_branch, a.remark, a.profile_pic, a.deposit_money, "
				+ "status_married, line_id, email, bloodgroup, "
				+ "patient_type.patient_type, contact_time_start, contact_time_end, weight, "
				+ "height, typerecommended, tel_id, addr_id, patient_type.patient_typename, "
				+ "patneed_id, pat_congenital_disease_id, career "
				+ "FROM patient AS a "
				+ "INNER JOIN pre_name prename on (a.pre_name_id = prename.pre_name_id) "
				+ "LEFT JOIN patient_type on (a.patient_type = patient_type.patient_type) "
				+ "where ";
				
				Validate classvalidate = new Validate();
		
				if(classvalidate.Check_String_notnull_notempty(patModel.getHn()))
					sql += "a.hn like '%"+patModel.getHn()+"%' and ";
				/*
				if(classvalidate.Check_String_notnull_notempty(patModel.getFirstname_th()))
					sql += "a.firstname_th like '%"+patModel.getFirstname_th()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getFirstname_en()))
					sql += "a.firstname_en like '%"+patModel.getFirstname_en()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getLastname_th()))
					sql += "a.lastname_th like '%"+patModel.getLastname_th()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getLastname_en()))
					sql += "a.lastname_en like '%"+patModel.getLastname_en()+"%' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getBirth_date()))
					sql += "a.birth_date = '"+patModel.getBirth_date()+"' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getIdentification()))
					sql += "a.identification = '"+patModel.getIdentification()+"' and ";
				
				if(classvalidate.Check_String_notnull_notempty(patModel.getIdentification_type()))
					sql += "a.identification_type = '"+patModel.getIdentification_type()+"' and ";
				*/
				sql += "a.hn != '' ";
				System.out.println(sql);
				PatientModel makePatModel = new PatientModel();
				PatContypeData aPatContypeData = new PatContypeData();
				FileData aFileData = new FileData();
				CalculateNumber classCalNum = new CalculateNumber();
				try {
					
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					rs = Stmt.executeQuery(sql);
					
					String[] hnFormat = new String[2];
					
					while (rs.next()) {
					
						hnFormat[0] = rs.getString("hn").substring(0, 3);
						hnFormat[1] = rs.getString("hn").substring(3, rs.getString("hn").length());
						makePatModel.setHn(rs.getString("hn"));
						makePatModel.setHnFormat(hnFormat[0] + "-" + hnFormat[1]);
						makePatModel.setPre_name_id(rs.getString("pre_name_id"));
						makePatModel.setPre_name_th(rs.getString("pre_name_th"));
						makePatModel.setPre_name_en(rs.getString("pre_name_en"));
//						System.out.println("Patneed ID : "+rs.getInt("patneed_id"));
						makePatModel.setFirstname_th(rs.getString("first_name_th"));
						makePatModel.setLastname_th(rs.getString("last_name_th"));
						makePatModel.setFirstname_en(rs.getString("first_name_en"));
						makePatModel.setLastname_en(rs.getString("last_name_en"));
						makePatModel.setBirth_date(rs.getString("birth_date"));
						makePatModel.setIdentification(rs.getString("identification"));
						makePatModel.setIdentification_type(rs.getString("identification_type"));
						makePatModel.setRelation_emp(rs.getString("relation_emp"));
						makePatModel.setRegister_branch(rs.getString("register_branch"));
						makePatModel.setRemark(rs.getString("remark"));
						makePatModel.setProfile_pic(rs.getString("profile_pic"));
						makePatModel.setDeposit_money(rs.getDouble("deposit_money"));
						makePatModel.setStatus_married(rs.getString("status_married"));
						makePatModel.setLine_id(rs.getString("line_id"));
						makePatModel.setEmail(rs.getString("email"));
						makePatModel.setNickname(rs.getString("nickname"));
						makePatModel.setBloodgroup(rs.getString("bloodgroup"));
						makePatModel.setPatient_type(rs.getString("patient_type"));
						makePatModel.setContact_time_start(rs.getString("contact_time_start"));
						makePatModel.setContact_time_end(rs.getString("contact_time_end"));
						makePatModel.setWeight(rs.getDouble("weight"));
						makePatModel.setHeight(rs.getDouble("height"));
						makePatModel.setTyperecommended(rs.getInt("typerecommended"));
						makePatModel.setPatient_type_name(rs.getString("patient_typename"));
						makePatModel.setAddrModel(new AddressData().getMultiAddr(rs.getInt("addr_id")));
						makePatModel.setCareer(rs.getString("career"));
						makePatModel.setPatneed_id(rs.getInt("patneed_id"));
						makePatModel.setListTelModel(new TelephoneData().getMultiple_Telephone(new TelephoneModel("","","","",rs.getInt("tel_id"),0,1)));
						makePatModel.setContypeList(aPatContypeData.getListContype(makePatModel.getHn(), 0));
						makePatModel.setPatneed_message(getPatientNeed(makePatModel.getHn()));
						makePatModel.setBeallergic(getModelListBeallergic(patModel));
						makePatModel.setBe_allergic_id(patModel.getBe_allergic_id());
						makePatModel.setDocumentneed(getModelListDocument(patModel));
						int disease_id = 0;
						if(patModel != null) disease_id = patModel.getPat_congenital_disease_id();
						
						makePatModel.setCongenList(new CongenitalData().getConginentalDisease(new CongenitalDiseaseModel(0,disease_id,"","")));
						makePatModel.setPat_congenital_disease_id(disease_id);
						
						//makePatModel.setPatFileList(aFileData.getListPatFileModel(makePatModel.getHn()));
						
						makePatModel.setAge(classCalNum.getIntAge_fromBirthDate(Integer.parseInt(makePatModel.getBirth_date().split("-")[0]), 
								Integer.parseInt(makePatModel.getBirth_date().split("-")[1]), Integer.parseInt(makePatModel.getBirth_date().split("-")[2])));
						makePatModel.setStatus(getPatStatus(makePatModel.getHn())); 
						
					}					
					if(!rs.isClosed()) rs.close();
					if(!Stmt.isClosed()) Stmt.close();
					if(!conn.isClosed()) conn.close();
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				System.out.print(sql);
		return makePatModel;
	}
	
	public PatientModel getPatConfirmHistory(String patHn){
		
		String sql = "SELECT confirm_brush_teeth, confirm_pregnant, week_of_pregent, confirm_now_receive_drug, "
					+ "drug_name, confirm_now_treatment, confirm_hospital_doctor_now_treatment, doctor_hospital_name, "
					+ "confirm_congenital "
				+ "FROM `patient` where hn = '"+patHn+"';";
		
		PatientModel patModel = new PatientModel();
		try {
			Connection connPatConfirm = agent.getConnectMYSql();
			Statement StmtPatConfirm = connPatConfirm.createStatement();
			ResultSet rsPatConfirm = StmtPatConfirm.executeQuery(sql);
			
			
			while (rsPatConfirm.next()) {
				patModel.setConfirm_brush_teeth(rsPatConfirm.getString("confirm_brush_teeth"));
				patModel.setConfirm_pregnant(rsPatConfirm.getString("confirm_pregnant"));
				patModel.setWeek_of_pregent(rsPatConfirm.getInt("week_of_pregent"));
				patModel.setConfirm_now_receive_drug(rsPatConfirm.getString("confirm_now_receive_drug"));
				patModel.setDrug_name(rsPatConfirm.getString("drug_name"));
				patModel.setConfirm_now_treatment(rsPatConfirm.getString("confirm_now_treatment"));
				patModel.setConfirm_hospital_doctor_now_treatment(rsPatConfirm.getString("confirm_hospital_doctor_now_treatment"));
				patModel.setDoctor_hospital_name(rsPatConfirm.getString("doctor_hospital_name"));
				patModel.setConfirm_congenital(rsPatConfirm.getString("confirm_congenital"));
			}
			
			if(!rsPatConfirm.isClosed()) rsPatConfirm.close();
			if(!StmtPatConfirm.isClosed())	StmtPatConfirm.close();
			if(!connPatConfirm.isClosed())	connPatConfirm.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return patModel;
	}

	public String GetHighest_HN(){
		String sqlQuery = "select MAX(hn) as hn from patient";
		String ResultString = "";
		
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			
			if(rs.next())
			{
				ResultString = rs.getString("hn");
				//ResultString = rs.getString("pre_name_th");
				//ResultString = rs.getString("pre_name_en");
			}
		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}

		return ResultString;
	}
	
	
	public String PlusOneID_FormatID(String hn)
	{
		if(hn == null)
		{
			hn = "0000001";
			// hn = "HN000000001";
		}
		else
		{
			hn = String.valueOf((Integer.parseInt(hn)+1));
			
			//�ٻ�����ӹǹ addr_id
			
			int length = hn.length();
			for(;length < 7; length++)
			{
				hn = "0"+hn;
			}
			
		}
		
		return hn;
	}
	
	// Start BeAllerGic
	
	public int getMaxPatient_Beallergic(){
		// 2016-08-19 Loriz
		String sqlQuery = "select IFNULL(MAX(be_allergic_id),0) as be_allergic_id from patient_beallergic";
		int highest_be_allergic_id = 0;
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				highest_be_allergic_id = rs.getInt("be_allergic_id");
			}
		} 
		catch (IOException e)
		{
		
			e.printStackTrace();
		}
		catch (Exception e) 
		{
	
			e.printStackTrace();
		}

		return highest_be_allergic_id;
	}
	public void addmutiallergic(PatientModel patModel) throws IOException, Exception{
		
		String SQL = "INSERT INTO patient_beallergic (product_id,hn,product_name_th,product_name_en) VALUES "
				+ "("+patModel.getProduct_id()+",'"+patModel.getHn()+"','"+patModel.getBeallergic_name_th()+"','"+patModel.getBeallergic_name_en()+"')";
		
					
					
				
					
			System.out.print(SQL);
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
		
		}	
	public int add_multi_BeAllergic(List<ProductModel> beallergicList){

		int beallergic_id = new CalculateNumber().plusOneInt(getMaxPatient_Beallergic(), 1);
		String sql = "";
		
		try {
			
			sql = "INSERT INTO patient_beallergic (hn, product_id) VALUES ";
			int i = 0;
			for (ProductModel productModel : beallergicList) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql +="("+beallergic_id+","+productModel.getProduct_id()+")";				
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
		
		return beallergic_id;
	}
	
	public List<ProductModel> getModelList_Beallergic(PatientModel patModel){
		
		if(patModel == null){
			return null;
		}
		
		List <ProductModel> resultList = new ArrayList<ProductModel>();
		String sql = "SELECT "
				+ "pro_product.product_id, pro_product.product_name, pro_product.product_name_en, pro_product.price, "
				+ "pro_product.create_by, pro_product.create_datetime, pro_product.update_by, pro_product.update_datetime, "
				+ "patient_beallergic.be_allergic_id, pro_productbrand.productbrand_id, pro_productbrand.productbrand_name, pro_productgroup.productgroup_id, "
				+ "pro_productgroup.productgroup_name, pro_productunit.productunit_id, pro_productunit.productunit_name,pro_producttype.producttype_id, "
				+ "pro_producttype.producttype_name FROM patient_beallergic "
				+ "INNER JOIN pro_product ON patient_beallergic.product_id = pro_product.product_id "
				+ "INNER JOIN pro_productbrand ON pro_productbrand.productbrand_id = pro_product.productbrand_id "
				+ "INNER JOIN pro_productgroup ON pro_productgroup.productgroup_id = pro_product.productgroup_id "
				+ "INNER JOIN pro_producttype ON pro_producttype.producttype_Id = pro_product.producttype_id "
				+ "INNER JOIN pro_productunit ON pro_productunit.productunit_id = pro_product.productunit_id "
				+ " where patient_beallergic.hn = "+patModel.getHn();

				
				
				try {
					
					
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					ResultSet rsgetModelList_Beallergic = Stmt.executeQuery(sql);
					while (rsgetModelList_Beallergic.next()) {
						ProductModel proModel = new ProductModel();
						proModel.setProduct_id(rsgetModelList_Beallergic.getInt("product_id"));
						proModel.setProduct_name(rsgetModelList_Beallergic.getString("product_name"));
						proModel.setProduct_name_en(rsgetModelList_Beallergic.getString("product_name_en"));
						proModel.setProductbrand_id(rsgetModelList_Beallergic.getString("productbrand_id"));
						proModel.setProductbrand_name(rsgetModelList_Beallergic.getString("productbrand_name"));
						proModel.setProducttype_Id(rsgetModelList_Beallergic.getString("producttype_id"));
						proModel.setProducttype_name(rsgetModelList_Beallergic.getString("producttype_name"));
						proModel.setProductgroup_id(rsgetModelList_Beallergic.getString("productgroup_id"));
						proModel.setProductgroup_name(rsgetModelList_Beallergic.getString("productgroup_name"));
						proModel.setProductunit_id(rsgetModelList_Beallergic.getString("productunit_id"));
						proModel.setProductunit_name(rsgetModelList_Beallergic.getString("productunit_name"));
						resultList.add(proModel);
					}
					
					if(!rsgetModelList_Beallergic.isClosed()) rsgetModelList_Beallergic.close();
					if(!Stmt.isClosed()) Stmt.close();
					if(!conn.isClosed()) conn.close();
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
		return resultList;
	}
	public List<ProductModel> getList_Beallergic(PatientModel patModel){
		
		if(patModel == null){
			return null;
		}
		
		List <ProductModel> resultList = new ArrayList<ProductModel>();
		String sql = "SELECT "
				+ "pro_product.product_id, pro_product.product_name, pro_product.product_name_en, pro_product.price, "
				+ "pro_product.create_by, pro_product.create_datetime, pro_product.update_by, pro_product.update_datetime, "
				+ "patient_beallergic.be_allergic_id, pro_productbrand.productbrand_id, pro_productbrand.productbrand_name, pro_productgroup.productgroup_id, "
				+ "pro_productgroup.productgroup_name, pro_productunit.productunit_id, pro_productunit.productunit_name,pro_producttype.producttype_id, "
				+ "pro_producttype.producttype_name FROM patient_beallergic "
				+ "INNER JOIN pro_product ON patient_beallergic.product_id = pro_product.product_id "
				+ "INNER JOIN pro_productbrand ON pro_productbrand.productbrand_id = pro_product.productbrand_id "
				+ "INNER JOIN pro_productgroup ON pro_productgroup.productgroup_id = pro_product.productgroup_id "
				+ "INNER JOIN pro_producttype ON pro_producttype.producttype_Id = pro_product.producttype_id "
				+ "INNER JOIN pro_productunit ON pro_productunit.productunit_id = pro_product.productunit_id "
				+ " where patient_beallergic.be_allergic_id = "+patModel.getHn();

				
				
				try {
					
					
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					ResultSet rsgetModelList_Beallergic = Stmt.executeQuery(sql);
					while (rsgetModelList_Beallergic.next()) {
						ProductModel proModel = new ProductModel();
						proModel.setProduct_id(rsgetModelList_Beallergic.getInt("product_id"));
						proModel.setProduct_name(rsgetModelList_Beallergic.getString("product_name"));
						proModel.setProduct_name_en(rsgetModelList_Beallergic.getString("product_name_en"));
						proModel.setProductbrand_id(rsgetModelList_Beallergic.getString("productbrand_id"));
						proModel.setProductbrand_name(rsgetModelList_Beallergic.getString("productbrand_name"));
						proModel.setProducttype_Id(rsgetModelList_Beallergic.getString("producttype_id"));
						proModel.setProducttype_name(rsgetModelList_Beallergic.getString("producttype_name"));
						proModel.setProductgroup_id(rsgetModelList_Beallergic.getString("productgroup_id"));
						proModel.setProductgroup_name(rsgetModelList_Beallergic.getString("productgroup_name"));
						proModel.setProductunit_id(rsgetModelList_Beallergic.getString("productunit_id"));
						proModel.setProductunit_name(rsgetModelList_Beallergic.getString("productunit_name"));
						resultList.add(proModel);
					}
					
					if(!rsgetModelList_Beallergic.isClosed()) rsgetModelList_Beallergic.close();
					if(!Stmt.isClosed()) Stmt.close();
					if(!conn.isClosed()) conn.close();
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
		return resultList;
	}	
	// End BeAllerGic
	
	public PatientModel getIdPatientReference(String patHn){
		String sql = "SELECT hn, tel_id, addr_id, "
					+ "patneed_id, pat_congenital_disease_id, profile_pic "
					+ "FROM "
					+ "patient "
					+ "where hn = '"+patHn+"'";
		
		Connection connIdRefer;
		PatientModel patModel = new PatientModel();
		try {
			
			connIdRefer = agent.getConnectMYSql();
			Statement StmtIdRefer = connIdRefer.createStatement();
			ResultSet rsIdRefer = StmtIdRefer.executeQuery(sql);

			while (rsIdRefer.next()) {
				patModel.setTel_id(rsIdRefer.getInt("tel_id"));
				patModel.setAddr_id(rsIdRefer.getInt("addr_id"));
				patModel.setPatneed_id(rsIdRefer.getInt("patneed_id"));
				patModel.setPat_congenital_disease_id(rsIdRefer.getInt("pat_congenital_disease_id"));
				patModel.setProfile_pic(rsIdRefer.getString("profile_pic"));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patModel;
	}
	
	//Start PatNeed
	public int getMaxPatient_needID(){
		// 2016-08-19 Loriz
		String sqlQuery = "select IFNULL(MAX(patneed_id),0) as patneed_id from patient_need";
		int highest_patient_needid = 0;
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				highest_patient_needid = rs.getInt("patneed_id");
			}
		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}

		return highest_patient_needid;
	}
	public int getMaxPatientneedID_RunningID(){

		String sqlQuery = "select next_number as patneed_id from running_doc "
				+ "WHERE type ='patneed' ";
		int highest_patient_needid = 0;
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sqlQuery);
			if(rs.next())
			{
				highest_patient_needid = rs.getInt("patneed_id");
			}
		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}

		return highest_patient_needid;
	}
	public void UpdateRunning_Patneed_id(PatientModel patModel){

		String sql = "UPDATE running_doc set next_number = next_number+1  "
				+ "where type = 'patneed' ";
		

					try {
						conn = agent.getConnectMYSql();
						Stmt = conn.createStatement();
						Stmt.executeUpdate(sql);
						Stmt.close();
						conn.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		
	}
	public int add_multi_Patneed(PatientModel patModel){

		int patneed_id = getMaxPatientneedID_RunningID();
		String sql = "";
		
		try {
			
			sql = "INSERT INTO patient_need (patneed_id, patneed_message) VALUES ";
			int i = 0;
			for (String patneed_message : patModel.getPatneed_message()) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql +="("+patneed_id+",'"+patneed_message+"')";				
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
		
		return patneed_id;
	}
	
	public void addmulti_Patneed(PatientModel patModel){

		String sql = "";
		
		try {
			
			sql = "INSERT INTO patient_need (patneed_id, patneed_message) VALUES ";
			int i = 0;
			for (String patneed_message : patModel.getPatneed_message()) {
				i++;
				if(i>1){
					sql += ",";
				}
				sql +="("+patModel.getPatneed_id()+",'"+patneed_message+"')";				
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
	
	public void Delete_patneed(PatientModel patModel){
		
		String sql = "delete from patient_need where patneed_id = ?";
	//	System.out.println(patModel.getHn());
		try {
			
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, patModel.getPatneed_id());
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void Delete_patneedIsEmpty(PatientModel patModel){
		
		String sql = "delete from patient_need where patneed_id = ? and patneed_message = ''";
	//	System.out.println(patModel.getHn());
		try {
			
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, patModel.getPatneed_id());
			pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	//End PatNeed
	
	//Start congen
		public int getMaxPatient_congenID(){
			// 2016-08-19 Loriz
			String sqlQuery = "select IFNULL(MAX(pat_congenital_disease_id),0) as pat_congenital_disease_id from patient_congenital_disease";
			int highest_patient_needid = 0;
			try 
			{
				conn = agent.getConnectMYSql();
				Stmt = conn.createStatement();
				rs = Stmt.executeQuery(sqlQuery);
				if(rs.next())
				{
					highest_patient_needid = rs.getInt("pat_congenital_disease_id");
				}
			} 
			catch (IOException e)
			{
				
				e.printStackTrace();
			}
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}

			return highest_patient_needid;
		}
		public int getMaxcongenID_RunningID(){

			String sqlQuery = "select next_number from running_doc "
					+ "WHERE type ='congen' ";
			int highest_patient_needid = 0;
			try 
			{
				conn = agent.getConnectMYSql();
				Stmt = conn.createStatement();
				rs = Stmt.executeQuery(sqlQuery);
				if(rs.next())
				{
					highest_patient_needid = rs.getInt("next_number");
				}
			} 
			catch (IOException e)
			{
				
				e.printStackTrace();
			}
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}

			return highest_patient_needid;
		}		
		public void Update_Running_CongenID(){

			String sql = "UPDATE running_doc SET next_number = next_number+1 "
					+ "Where type = 'congen' ";
			

						try {
							conn = agent.getConnectMYSql();
							Stmt = conn.createStatement();
							Stmt.executeUpdate(sql);
							Stmt.close();
							conn.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

			
		}		
		public void Delete_CongenIsEmpty(PatientModel patModel){
			
			String sql = "delete from patient_congenital_disease where pat_congenital_disease_id = ? and congenital_id = ''";
		//	System.out.println(patModel.getHn());
			try {
				
				conn = agent.getConnectMYSql();
				pStmt = conn.prepareStatement(sql);
				pStmt.setInt(1, patModel.getPat_congenital_disease_id());
				pStmt.executeUpdate();
				
				if(!pStmt.isClosed()) pStmt.close();
				if(!conn.isClosed()) conn.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}			
		public int add_multi_congenID(List<CongenitalDiseaseModel> congenList){

			int pat_congenital_disease_id = getMaxcongenID_RunningID();
			String sql = "";
			
			try {
				
				sql = "INSERT INTO patient_congenital_disease (pat_congenital_disease_id, congenital_id, congenital_name_th, congenital_name_en) VALUES ";
				int i = 0;
				for (CongenitalDiseaseModel congen : congenList) {
					i++;
					if(i>1){
						sql += ",";
					}
					sql +="("+pat_congenital_disease_id+",'"+congen.getCongenital_id()+"', '"+congen.getCongenital_name_th()+"', '"+congen.getCongenital_name_en()+"')";				
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
			
			return pat_congenital_disease_id;
		}
		//End congen
	public  List<PatientModel> select_Identification_Type(){
		String sql = "select * from identification_type";
		
			//System.out.println(sql);
			List<PatientModel> ResultList = new ArrayList<PatientModel>();
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while (rs.next()) {
				ResultList.add(new PatientModel(rs.getString("identification_typeid"),rs.getString("identification_typename")));
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return ResultList;
	}
	public List<PatientModel> get_identification_type(String identID){
		
		List<PatientModel> pList = new ArrayList<PatientModel>();
		String sql = "SELECT * FROM identification_type WHERE identification_typeid = ?";
		try {
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,identID);
			rs = pStmt.executeQuery();getClass();
			int i =0;
			while (rs.next()) {
				PatientModel pModel = new PatientModel();
				pModel.setIdentification("identification_typename");
				pModel.setIdentification_type(rs.getString("identification_typeid"));
				pList.add(pModel);
				i++;
			}
			if(i==0){ 
				pList = select_Identification_Type();
			}
			
			if (!rs.isClosed())
				rs.close();
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
		
		return pList;
	}
	
	public String[] getPatneedMessage(int patneed_id){
		List<String> listMessage = new ArrayList<String>();
		
		String sql = "select * from patient_need where patneed_id = "+patneed_id;
		try {
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while (rs.next()) {
				listMessage.add(rs.getString("patneed_message"));
			}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!Stmt.isClosed())
				Stmt.close();
			if (!conn.isClosed())
				conn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int sizeOfList = listMessage.size();
		String[] patneed_message = new String[sizeOfList];
		for(int i = 0; i < listMessage.size() ; i ++){
			patneed_message[i] = listMessage.get(i);
		}
		
		return patneed_message;
	}
	public String getPatStatus(String hn){
		String patstatus = "N";
		
		String sql = "SELECT status FROM treatment_working WHERE hn = '"+hn+"'";
		try {
			Connection connPatStatus = agent.getConnectMYSql();
			Statement StmtPatStatus = connPatStatus.createStatement();
			ResultSet rsPatStatus = StmtPatStatus.executeQuery(sql);
			while (rsPatStatus.next()) {
				if(rsPatStatus.getString("status")!=null) patstatus = rsPatStatus.getString("status"); else patstatus = "N";
			} 
			
			if (!rsPatStatus.isClosed())
				rsPatStatus.close();
			if (!StmtPatStatus.isClosed())
				StmtPatStatus.close();
			if (!connPatStatus.isClosed())
				connPatStatus.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return patstatus;
	}
	public boolean allergicupdate(PatientModel patModel) throws IOException, Exception{
		

		String SQL = "DELETE FROM patient_beallergic "
				+ " where hn = '"+patModel.getHn()+"' and product_id not in ('";
				int i=0;		
				for(String beallergic : patModel.getBe_allergic()){
					if(i>0)
						SQL+=",";
						
				SQL+=	"" +beallergic+"";
					i++;
				}		
				SQL+= "')";

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}	
	public  boolean isNewAllergic(PatientModel patModel, String pro_id) throws IOException, Exception{
		String sql = "SELECT hn,product_id "
				+ "From patient_beallergic "
				+ "Where hn = '"+patModel.getHn()+"' and product_id = "+pro_id;
		boolean newAllergic = true;
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while (rs.next()) {
				newAllergic = false;
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
			
	
			return newAllergic;
	}
	public void addIsNewAllergic(PatientModel patModel, String beallergic) throws IOException, Exception{
		
		String SQL = "INSERT INTO patient_beallergic (product_id,hn,product_name_th,product_name_en) VALUES ";
				ProductModel proModel =  new ProductModel();
				proModel = getlistBeallergicIsNew(beallergic);
				if(beallergic.equals("1")){
					proModel.setBeallergic_name_th(patModel.getOther_beallergic_name_th());
					SQL+=	 "("+proModel.getProduct_id()+",'"+patModel.getHn()
					 +"','"+proModel.getBeallergic_name_th()+"','"+proModel.getBeallergic_name_en()+"')";
				}else{				
					SQL+=	 "("+proModel.getProduct_id()+",'"+patModel.getHn()
							 +"','"+proModel.getBeallergic_name_th()+"','"+proModel.getBeallergic_name_en()+"')";
				}
	 			

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		
		}
	public void addIsNewAllergicUpdate(PatientModel patModel) throws IOException, Exception{
		
		String SQL = "Update patient_beallergic set "
				+ "product_name_th ='"+patModel.getOther_beallergic_name_th()
				+ "' Where product_id = 1 and hn ="+patModel.getHn();
		

			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			pStmt.executeUpdate();
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
		
		}
public List<ProductModel> getModelListBeallergic(PatientModel patModel){
		
		if(patModel == null){
			return null;
		}
		
		List <ProductModel> resultList = new ArrayList<ProductModel>();
		String sql = "SELECT "
				+ "product_id, hn, product_name_th, product_name_en"
				+ " FROM patient_beallergic "
				+ " where patient_beallergic.hn = "+patModel.getHn();

				
				System.out.println(sql);
				try {
					
					
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					ResultSet rsgetModelList_Beallergic = Stmt.executeQuery(sql);
					while (rsgetModelList_Beallergic.next()) {
						ProductModel proModel = new ProductModel();
						proModel.setProduct_id(rsgetModelList_Beallergic.getInt("product_id"));
						proModel.setBeallergic_name_th(rsgetModelList_Beallergic.getString("product_name_th"));
						proModel.setBeallergic_name_en(rsgetModelList_Beallergic.getString("product_name_en"));
						resultList.add(proModel);
					}
					
					if(!rsgetModelList_Beallergic.isClosed()) rsgetModelList_Beallergic.close();
					if(!Stmt.isClosed()) Stmt.close();
					if(!conn.isClosed()) conn.close();
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
		return resultList;
	}
	public List<ProductModel> getListBeallergic(String hn){
		
		if(hn == null){
			return null;
		}
		
		List <ProductModel> resultList = new ArrayList<ProductModel>();
		String sql = "SELECT "
				+ "product_id, hn, product_name_th, product_name_en"
				+ " FROM patient_beallergic "
				+ " where patient_beallergic.hn = "+hn;
	
				
				System.out.println(sql);
				try {
					
					
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					ResultSet rsgetModelList_Beallergic = Stmt.executeQuery(sql);
					while (rsgetModelList_Beallergic.next()) {
						ProductModel proModel = new ProductModel();
						proModel.setProduct_id(rsgetModelList_Beallergic.getInt("product_id"));
						proModel.setBeallergic_name_th(rsgetModelList_Beallergic.getString("product_name_th"));
						proModel.setBeallergic_name_en(rsgetModelList_Beallergic.getString("product_name_en"));
						resultList.add(proModel);
					}
					
					if(!rsgetModelList_Beallergic.isClosed()) rsgetModelList_Beallergic.close();
					if(!Stmt.isClosed()) Stmt.close();
					if(!conn.isClosed()) conn.close();
					
					
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
		return resultList;
	}
	
	public ProductModel getlistBeallergicIsNew(String proid){
		
		if(proid == null){
			return null;
		}
		ProductModel proModel = new ProductModel();
		String sql = "SELECT "
				+ "product_id, product_name, product_name_en "
				+ "from pro_product"				
				+ " where product_id = "+proid;
	
				
				
				try {
					
					
					conn = agent.getConnectMYSql();
					Stmt = conn.createStatement();
					ResultSet rsgetModelList_Beallergic = Stmt.executeQuery(sql);
					
					while (rsgetModelList_Beallergic.next()) {						
						proModel.setProduct_id(rsgetModelList_Beallergic.getInt("product_id"));
						proModel.setBeallergic_name_th(rsgetModelList_Beallergic.getString("product_name"));
						proModel.setBeallergic_name_en(rsgetModelList_Beallergic.getString("product_name_en"));
					}

	
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
		return proModel;
	}
	//document_need
	public void document_need_addmuti(PatientModel patModel) throws IOException, Exception{
		
		String sql = "INSERT into patient_document_need "
				+ "(document_need_id,hn ) "
				+ "value ";
				int i = 0;
				for(String docu :  patModel.getDocument_need()){
					if(i>0){
						sql +=",";
					}
					sql +="("+docu+",'"+patModel.getHn()+"')";
					i++;
				}

				conn = agent.getConnectMYSql();
				pStmt = conn.prepareStatement(sql);
				pStmt.executeUpdate();
				
				if(!pStmt.isClosed()) pStmt.close();
				if(!conn.isClosed()) conn.close();
	}
	public List<DocumentModel> getListDocument(String hn){

		String sql = "SELECT "
				+ "document_need_master.document_id, document_need_master.document_name, patient_document_need.hn "
				+ "FROM "
				+ "patient_document_need "
				+ "INNER JOIN document_need_master ON document_need_master.document_id = patient_document_need.document_need_id "
				+ "WHERE patient_document_need.hn = '"+hn+"'";
				
		List<DocumentModel> documentList = new LinkedList<DocumentModel>();
		try 
		{
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			
			while (rs.next()) {
				DocumentModel docModel = new DocumentModel();
				
				docModel.setDocument_id(rs.getInt("document_id"));
				docModel.setDoc_name(rs.getString("document_name"));
				
				documentList.add(docModel);
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
		
		return documentList;
	}
	public List<DocumentModel> getModelListDocument(PatientModel patModel){

		String sql = "SELECT "
				+ "document_need_master.document_id, document_need_master.document_name, patient_document_need.hn "
				+ "FROM "
				+ "patient_document_need "
				+ "INNER JOIN document_need_master ON document_need_master.document_id = patient_document_need.document_need_id "
				+ "WHERE patient_document_need.hn = '"+patModel.getHn()+"'";
				
		List<DocumentModel> documentList = new LinkedList<DocumentModel>();
		try 
		{
			Connection connPatStatus = agent.getConnectMYSql();
			Statement StmtPatStatus = connPatStatus.createStatement();
			ResultSet rsPatStatus = StmtPatStatus.executeQuery(sql);
			
			while (rsPatStatus.next()) {
				DocumentModel docModel = new DocumentModel();
				
				docModel.setDocument_id(rsPatStatus.getInt("document_id"));
				docModel.setDoc_name(rsPatStatus.getString("document_name"));
				
				documentList.add(docModel);
			}
			
			if (!rsPatStatus.isClosed())
				rsPatStatus.close();
			if (!StmtPatStatus.isClosed())
				StmtPatStatus.close();
			if (!connPatStatus.isClosed())
				connPatStatus.close();
		} 
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return documentList;
	}	
	public boolean documentNeedDel(PatientModel patModel) throws IOException, Exception{
		

		String SQL = "DELETE FROM patient_document_need "
				+ " where hn = '"+patModel.getHn()+"' and document_need_id not in ('";
				int i=0;		
				for(String docuNeed : patModel.getDocument_need()){
					if(i>0)
						SQL+=",";
						
				SQL+=	"" +docuNeed+"";
					i++;
				}		
				SQL+= "')";
			conn = agent.getConnectMYSql();
			pStmt = conn.prepareStatement(SQL);
			int sStmt = pStmt.executeUpdate();
			
			if(!pStmt.isClosed()) pStmt.close();
			if(!conn.isClosed()) conn.close();
			if(sStmt>0){
				return true;
			}
		
				return false;
		
		}
	public  boolean isNewDocuNeed(PatientModel patModel, String docu_id) throws IOException, Exception{
		String sql = "SELECT hn,document_need_id "
				+ "From patient_document_need "
				+ "Where hn = '"+patModel.getHn()+"' and document_need_id = "+docu_id;
		boolean newDocuNeed = true;
			
			conn = agent.getConnectMYSql();
			Stmt = conn.createStatement();
			rs = Stmt.executeQuery(sql);
			while (rs.next()) {
				newDocuNeed = false;
			}
			
			if(!rs.isClosed()) rs.close();
			if(!Stmt.isClosed()) Stmt.close();
			if(!conn.isClosed()) conn.close();
			
	
			return newDocuNeed;
	}
	public void IsNewAddDocuNeed(PatientModel patModel,String docu_id) throws IOException, Exception{
		
		String sql = "INSERT into patient_document_need "
				+ "(document_need_id,hn ) "
				+ "value "
				+ "("+docu_id+",'"+patModel.getHn()+"')";
				conn = agent.getConnectMYSql();
				pStmt = conn.prepareStatement(sql);
				pStmt.executeUpdate();
				if(!pStmt.isClosed()) pStmt.close();
				if(!conn.isClosed()) conn.close();
	}
	//document_need end
	
}
