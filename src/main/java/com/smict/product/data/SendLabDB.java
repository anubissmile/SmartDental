package com.smict.product.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
 
import com.smict.product.model.*;

import ldc.util.*;

public class SendLabDB {
	DBConnect agent = new DBConnect();
	Connection conn = null, conn1 = null;
	Statement Stmt = null, Stmt1 = null;
	PreparedStatement pStmt = null, pStmt1 = null;
	ResultSet rs = null, rs1 = null;
	DateUtil dateUtil = new DateUtil();
	  
	DecimalFormat df1 = new DecimalFormat("#,###,###");
	
	public List<SendLabModel> Get_SendLabList(String service_name, String lab_name, String first_name_th, String treatment_code,
			String req_date_from, String req_date_to, String upd_date_from, String upd_date_to) throws IOException, Exception {
		
		if(req_date_from!=null&&!req_date_from.equals("")) {
			req_date_from = req_date_from.replace("-", "/");
			req_date_from = dateUtil.CnvToYYYYMMDDEngYear(req_date_from, '-');
		}
		if(req_date_to!=null&&!req_date_to.equals("")) {
			req_date_to = req_date_to.replace("-", "/");
			req_date_to = dateUtil.CnvToYYYYMMDDEngYear(req_date_to, '-');
		}
		if(upd_date_from!=null&&!upd_date_from.equals("")) {
			upd_date_from = upd_date_from.replace("-", "/");
			upd_date_from = dateUtil.CnvToYYYYMMDDEngYear(upd_date_from, '-');
		}
		if(upd_date_to!=null&&!upd_date_to.equals("")) {
			upd_date_to = upd_date_to.replace("-", "/");
			upd_date_to = dateUtil.CnvToYYYYMMDDEngYear(upd_date_to, '-');
		}
		
		String id = "", service_id = "", lab_id = "", hn = "", patientname = "", doctor_id = "", last_name_th = "", first_name_en = "", last_name_en = "";   
		String create_date = "", required_date = "", update_date = "", return_lab = "", timebegin = "", timeend = "";
		String remark = "", lab_status = "", ref_invoice = "";
		String est_fee = "", lab_fee = "", status_end = "", ref_id = "";
		boolean checkuse = false;
		
		String sqlQuery = "select a.lab_id, id, est_fee, lab_fee, a.treatment_code, create_date, required_date, update_date, timebegin, timeend, a.remark, lab_status, "
				+ "return_lab, ref_bill, ref_invoice, a.surf, a.tooth, a.tooth_range, lab_servicelab_id, e.hn, a.doctor_id, a.ref_id, "
				+ "concat(d.first_name_th,' ',d.last_name_th) as doctorname, concat(f.first_name_th,' ',f.last_name_th) as patientname, "
				+ "d.first_name_th, d.last_name_th, d.first_name_en, d.last_name_en, c.service_name, b.lab_name, a.status_end "
				+ "from lab_transaction a inner join lab b on(b.lab_id = a.lab_id) "
				+ "inner join lab_allservice c on(c.service_id = a.lab_servicelab_id) "
				+ "inner join doctor d on(d.doctor_id = a.doctor_id) "  
				+ "left join history_treatment e on(e.treatment_code = a.treatment_code) "
				+ "inner join patient f on(f.hn = e.hn) "
				+ "where ";

		if (new Validate().Check_String_notnull_notempty(lab_id))
			sqlQuery += "c.service_name like '%"+service_name+"%' and ";

		if (new Validate().Check_String_notnull_notempty(lab_name))
			sqlQuery += "b.lab_name like '%"+lab_name+"%' and ";
		
		if (new Validate().Check_String_notnull_notempty(first_name_th))
			sqlQuery += "doctorname like '%"+first_name_th+"%' and ";
		
		if (new Validate().Check_String_notnull_notempty(treatment_code))
			sqlQuery += "a.treatment_code = '"+treatment_code+"' and ";
		
		if (new Validate().Check_String_notnull_notempty(req_date_from)&&new Validate().Check_String_notnull_notempty(req_date_to)){
			sqlQuery += "a.required_date between '"+req_date_from+"' and '"+req_date_to+"' and ";
		}else if(new Validate().Check_String_notnull_notempty(req_date_from)){
			sqlQuery += "a.required_date = '"+req_date_from+"' and ";
		}
		
		if (new Validate().Check_String_notnull_notempty(upd_date_from)&&new Validate().Check_String_notnull_notempty(upd_date_to)){
			sqlQuery += "a.update_date between '"+upd_date_from+"' and '"+upd_date_to+"' and ";
		}else if(new Validate().Check_String_notnull_notempty(upd_date_from)){
			sqlQuery += "a.update_date = '"+upd_date_from+"' and ";
		}
		
		sqlQuery += "a.lab_id <> '' order by a.id, lab_status DESC, create_date ";

		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<SendLabModel> ResultList = new ArrayList<SendLabModel>();
		while (rs.next()) {
			id					=	rs.getString("id");
			service_id			=	rs.getString("lab_servicelab_id");
			service_name		=	rs.getString("service_name");
			lab_id				=	rs.getString("lab_id");
			lab_name			=	rs.getString("lab_name");
			hn					=	rs.getString("hn");
			patientname			=	rs.getString("patientname");
			doctor_id			=	rs.getString("doctor_id");
			first_name_th		=	rs.getString("doctorname");
			last_name_th		=	rs.getString("last_name_th");
			first_name_en		=	rs.getString("first_name_en");
			last_name_en		=	rs.getString("last_name_en");
			create_date			=	rs.getString("create_date");
			required_date		=	rs.getString("required_date");
			update_date			=	rs.getString("update_date");
			return_lab			=   rs.getString("return_lab"); 
			
			if(create_date!=null) 	create_date		= dateUtil.CnvToDDMMYYYYThaiYear(create_date);
			if(update_date!=null) 	update_date		= dateUtil.CnvToDDMMYYYYThaiYear(update_date); else update_date = "";
			if(required_date!=null) required_date	= dateUtil.CnvToDDMMYYYYThaiYear(required_date); else required_date = "";
			if(return_lab!=null) 	return_lab		= dateUtil.CnvToDDMMYYYYThaiYear(return_lab); else return_lab = "";
			
			timebegin			=	rs.getString("timebegin");
			if(timebegin!=null) timebegin = timebegin.substring(0, 5); else timebegin	= "";
			timeend				=	rs.getString("timeend");
			if(timeend!=null) timeend = timeend.substring(0, 5); else timeend	= "";
			
			remark				=	rs.getString("remark");
			if(remark==null) remark = "";
			lab_status			=	rs.getString("lab_status"); 
			treatment_code		=	rs.getString("treatment_code"); 
			
			ref_invoice			=   rs.getString("ref_invoice");
			if(ref_invoice==null) ref_invoice = "";
			est_fee			= 	rs.getString("est_fee"); 
			lab_fee			= 	rs.getString("lab_fee");
			
			if(est_fee!=null) est_fee = df1.format(Float.parseFloat(est_fee)); else est_fee = "";
			if(lab_fee!=null) lab_fee = df1.format(Float.parseFloat(lab_fee)); else lab_fee = "";  
			
			status_end			=   rs.getString("status_end"); 
			if(rs.getString("ref_id")!=null) ref_id = rs.getString("ref_id"); else ref_id = rs.getString("id");
			
			checkuse = checkuse(id);
			
			ResultList.add(new SendLabModel(id, service_id, service_name, lab_id, lab_name, hn, patientname, doctor_id, 
					first_name_th, last_name_th, first_name_en, last_name_en, create_date, required_date, update_date, 
					return_lab, timebegin, timeend, remark, lab_status, treatment_code, ref_invoice, est_fee, lab_fee, status_end, checkuse, ref_id));
		}

		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}
public boolean checkuse (String id) throws IOException, Exception {
		boolean chkecked = false;
		String ref_used = "";
		String sqlQuery = "SELECT ref_used from lab_transaction WHERE id = '"+id+"' ";
		
		conn1 = agent.getConnectMYSql();
		Stmt1 = conn1.createStatement();
		rs1 = Stmt1.executeQuery(sqlQuery);
		while (rs1.next()) { 
			ref_used = rs1.getString("ref_used");  
			if(ref_used.equals("Y")) chkecked = true;
		}
		 
		Stmt1.close(); 
		rs1.close(); 
		conn1.close();
		
	return chkecked; 
}
	
public List<SendLabModel> Get_TreatmentList(String hn) throws IOException, Exception {
		
		String treatment_name = "", treatment_code = "", treatment_date = "", doctor_id = "", doctor_name = "", surf = "", tooth = "", tooth_range = "", patientname = "";
		
		/*String sqlQuery = "SELECT a.hn, CONCAT(d.first_name_th,' ',d.last_name_th) as patientname, a.treatment_code, "
				+ "b.treatment_name, a.doctor_id, CONCAT(c.first_name_th,' ',c.last_name_th) as doctor_name, a.treatment_date, "
				+ "a.surf, a.tooth, a.tooth_range " 
				+ "FROM history_treatment a "
				+ "INNER JOIN price_list b on (b.treatment_code = a.treatment_code) "
				+ "INNER JOIN doctor c on (c.doctor_id = a.doctor_Id) "
				+ "INNER JOIN patient d on (d.hn = a.hn) "
				
				+ "where "; 
		
		if (new Validate().Check_String_notnull_notempty(hn))
			sqlQuery += "a.hn = '"+hn+"' and ";
		 
		sqlQuery += "a.hn <> '' ";*/
		
		String sqlQuery = "SELECT treatment_patient.patient_hn AS hn, CONCAT(patient.first_name_th, ' ', patient.last_name_th) AS patientname, "
				+ "treatment_patient_line.treatment_id, treatment_master.`code` AS treatment_code, "
				+ "treatment_master.nameth AS treatment_name, treatment_patient.doctor_id, "
				+ "CONCAT(doctor.first_name_th, ' ', doctor.last_name_th) AS doctor_name, treatment_patient.start_time AS treatment_date, "
				+ "treatment_patient_line.surf, treatment_patient_line.tooth, "
				+ "treatment_patient_line.tooth_type_id AS tooth_range "
				+ "FROM treatment_patient "
				+ "INNER JOIN treatment_patient_line ON treatment_patient.id = treatment_patient_line.treatment_patient_id "
				+ "INNER JOIN patient ON treatment_patient.patient_hn = patient.hn "
				+ "INNER JOIN treatment_master ON treatment_patient_line.treatment_id = treatment_master.id "
				+ "INNER JOIN doctor ON treatment_patient.doctor_id = doctor.doctor_id "
				+ "INNER JOIN room_id ON treatment_patient.room_id = room_id.room_id "
				+ "INNER JOIN branch ON room_id.room_branch_code = branch.branch_code "
				+ "WHERE room_id.room_branch_code = '" + Auth.user().getBranchCode() + "' ";
		
		if(new Validate().Check_String_notnull_notempty(hn)){
			sqlQuery += " AND treatment_patient.patient_hn = '" + hn + "' ";
		}else{
			sqlQuery += " AND treatment_patient.patient_hn <> '' ";
		}
		
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);

		List<SendLabModel> ResultList = new ArrayList<SendLabModel>();
		while (rs.next()) { 
			hn					=	rs.getString("hn");
			patientname			=	rs.getString("patientname");
			treatment_code		=	rs.getString("treatment_code");
			treatment_name		=	rs.getString("treatment_name");
			treatment_date		=	rs.getString("treatment_date");
			treatment_date		= treatment_date.substring(0, 10);
			treatment_date		= dateUtil.CnvToDDMMYYYYThaiYear(treatment_date);
			doctor_id			=	rs.getString("doctor_id"); 
			if(rs.getString("doctor_name")!=null) 	doctor_name	=	rs.getString("doctor_name"); else doctor_name = "";
			if(rs.getString("surf")!=null) 			surf		=	rs.getString("surf"); else	surf = "";
			if(rs.getString("tooth")!=null) 		tooth		=	rs.getString("tooth"); else tooth = "";
			if(rs.getString("tooth_range")!=null) 	tooth_range	=	rs.getString("tooth_range"); else tooth_range = "";
			
			ResultList.add(new SendLabModel(treatment_code, doctor_id, treatment_name, treatment_date, doctor_name, surf, tooth, tooth_range, hn, patientname));
		}

		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();

		return ResultList;
	}

public void AddLab(String service_id, String lab_id, String treatment_code, String doctor_id, String est_fee, String required_date, String remark){

	if(required_date!=null) {
		required_date = required_date.replace("-", "/");
		required_date = dateUtil.CnvToYYYYMMDDEngYear(required_date, '-');
	}
	
	String sql = "INSERT INTO lab_transaction "
			+ "(lab_servicelab_id, lab_id, treatment_code, doctor_id , est_fee , create_date, required_date , remark, lab_status) "
			+ "VALUES ("+service_id+", "+lab_id+", '"+treatment_code+"', "+doctor_id+", '"+est_fee+"', now(), '"+required_date+"', '"+remark+"', 'W')" ;
	
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		
		Stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
public void InsertRefLab(String service_id, String lab_id, String treatment_code, String doctor_id, String est_fee, 
		String required_date, String remark, String ref_id, String id){

	if(required_date!=null) {
		required_date = required_date.replace("-", "/");
		required_date = dateUtil.CnvToYYYYMMDDEngYear(required_date, '-');
	}
	try {
	String sql = "INSERT INTO lab_transaction "
			+ "(lab_servicelab_id, lab_id, treatment_code, doctor_id , est_fee , create_date, required_date , remark, lab_status, ref_id, ref_used) "
			+ "VALUES ("+service_id+", "+lab_id+", '"+treatment_code+"', "+doctor_id+", '"+est_fee+"', now(), '"+required_date+"', '"+remark+"', 'W', '"+ref_id+"', 'N')" ;
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
	
	sql = "UPDATE lab_transaction set ref_used = 'Y' WHERE id = "+id+" ";
	
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		
		sql = "DELETE FROM fullcalendar " 
				+ "WHERE lab_id = '"+ref_id+"'";	 
	 
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		Stmt.close();
		conn.close();
		
		Stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
public void UpdateTimeLab(String id, String return_lab, String timebegin, String timeend
		,String title, String hn, String doctor_id, String room, String confirm_status) throws IOException, Exception {
	
	if(return_lab!=null) {
		return_lab = return_lab.replace("-", "/");
		return_lab = dateUtil.CnvToYYYYMMDDEngYear(return_lab, '-'); 
	}
	
	String sql = "UPDATE lab_transaction set return_lab = '"+return_lab+"', timebegin = '"+timebegin+"', timeend = '"+timeend+"', status_end = 'Y' "
			+ "WHERE id = '"+id+"'";
	
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql); 
		
		Stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	
	return_lab 	= return_lab.substring(0, 10);
	timebegin 	= return_lab+" "+timebegin;
	timeend 	= return_lab+" "+timeend;
	
	String fullcalendar_id = "";
	sql = "SELECT id " 
			+ "FROM fullcalendar " 
			+ "where lab_id = '"+id+"'";   
	conn = agent.getConnectMYSql();
	Stmt = conn.createStatement();
	rs = Stmt.executeQuery(sql);
	
	while (rs.next()) {  
		fullcalendar_id = rs.getString("id");
	} 
	
	if(fullcalendar_id==null||fullcalendar_id.equals("")){
	
		sql = "INSERT INTO fullcalendar "
				+ "(start, end, title, doctor_id , hong , confirm_status, lab_id, color, hn) "
				+ "VALUES ('"+timebegin+"', '"+timeend+"', '"+title+"', "+doctor_id+", '"+room+"', '"+confirm_status+"', '"+id+"', '#00FF00', '"+hn+"')" ; 
	}else{ 
		sql = "UPDATE fullcalendar set start = '"+timebegin+"', end = '"+timeend+"', "
				+ "title = '"+title+"', hong = '"+room+"', hn = '"+hn+"' "
				+ "WHERE id = '"+fullcalendar_id+"' and lab_id = '"+id+"'";	 
	}
	
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		Stmt.close();
		conn.close();
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	
	
} 

public void UpdateLab(String id, String update_date, String ref_invoice, String lab_fee, String lab_status){
	
	if(update_date!=null) {
		update_date = update_date.replace("-", "/");
		update_date = dateUtil.CnvToYYYYMMDDEngYear(update_date, '-');
	}
	
	String sql = "UPDATE lab_transaction set update_date = '"+update_date+"', ref_invoice = '"+ref_invoice+"', lab_fee = '"+lab_fee+"', lab_status = '"+lab_status+"' "
			+ "WHERE id = '"+id+"'";
	
	try {
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		Stmt.executeUpdate(sql);
		
		Stmt.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
}
 
public List<SendLabModel> Get_Ref_List(String id) throws IOException, Exception { 
	String service_name = "", service_id = "", lab_id = "", lab_name = "", patientname = "", first_name_th = "", treatment_code = ""; 
	String sqlQuery = "select a.lab_id, id, est_fee, lab_fee, a.treatment_code, create_date, required_date, update_date, timebegin, timeend, a.remark, lab_status, "
			+ "return_lab, ref_bill, ref_invoice, a.surf, a.tooth, a.tooth_range, lab_servicelab_id, e.hn, a.doctor_id, a.ref_id, "
			+ "concat(d.first_name_th,' ',d.last_name_th) as doctorname, concat(f.first_name_th,' ',f.last_name_th) as patientname, "
			+ "d.first_name_th, d.last_name_th, d.first_name_en, d.last_name_en, c.service_name, b.lab_name, a.status_end "
			+ "from lab_transaction a inner join lab b on(b.lab_id = a.lab_id) "
			+ "inner join lab_allservice c on(c.service_id = a.lab_servicelab_id) "
			+ "inner join doctor d on(d.doctor_id = a.doctor_id) "  
			+ "left join history_treatment e on(e.treatment_code = a.treatment_code) "
			+ "inner join patient f on(f.hn = e.hn) "
			+ "where "; 
	
	if (new Validate().Check_String_notnull_notempty(id))
		sqlQuery += "a.ref_id = '"+id+"' "; 
	
	conn = agent.getConnectMYSql();
	Stmt = conn.createStatement();
	rs = Stmt.executeQuery(sqlQuery);

	List<SendLabModel> ResultList = new ArrayList<SendLabModel>();
	while (rs.next()) { 
		id					=	rs.getString("id");
		service_id			=	rs.getString("lab_servicelab_id");
		service_name		=	rs.getString("service_name");
		lab_id				=	rs.getString("lab_id");
		lab_name			=	rs.getString("lab_name"); 
		patientname			=	rs.getString("patientname");
		first_name_th		=	rs.getString("doctorname");
		treatment_code		=	rs.getString("treatment_code");
		
		ResultList.add(new SendLabModel(id, service_id, service_name, lab_id, lab_name, patientname, first_name_th, treatment_code));
	}

	if (!rs.isClosed())
		rs.close();
	if (!Stmt.isClosed())
		Stmt.close();
	if (!conn.isClosed())
		conn.close();

	return ResultList;
}
public String Get_RefID(String id) throws IOException, Exception {
	
		String sqlQuery = "select id, ref_id from lab_transaction " 
						+ "where id = '"+id+"' "; 
	 	
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		rs = Stmt.executeQuery(sqlQuery);
		while (rs.next()) { 
			if(rs.getString("ref_id")!=null) id = rs.getString("ref_id"); else id = rs.getString("id");
		}
		if (!rs.isClosed())
			rs.close();
		if (!Stmt.isClosed())
			Stmt.close();
		if (!conn.isClosed())
			conn.close();
	
	return id;
}

}
