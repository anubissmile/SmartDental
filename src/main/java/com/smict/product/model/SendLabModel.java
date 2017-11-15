package com.smict.product.model;
 

public class SendLabModel {
	
	private String id;
	private String service_id;
	private String service_name;
	private String lab_id;
	private String lab_name;
	private String lab_status;
	private String treatment_id;
	private String treatment_code;
	private String doctor_id; 
	private String first_name_th,last_name_th,first_name_en,last_name_en;
	private String create_date, required_date, update_date, return_lab, timebegin, timeend;
	private String remark;
	private String status_end;
	
	private String ref_invoice; 
	private String est_fee;
	private String lab_fee;
	
	// history_treatment 
	private String hn;
	private String patientname;
	private String treatment_name;
	private String treatment_date;
	private String doctor_name; 
	private String surf;
	private String tooth;
	private String tooth_range;
	
	// search lab
	private String s_service_name;
	private String s_lab_name;
	private String s_doctor_name; 
	private String s_treatment_code;
	private String req_date_from;
	private String req_date_to;
	private String upd_date_from;
	private String upd_date_to;
	
	// full calendar
	private String title;
	private String room;
	private String branch_id;
	private String confirm_status;
	
	// update lab
	private String ref_id; 
	private String up_service_id; 
	private String up_service_name;
	private String up_lab_id;
	private String up_lab_name;
	private String up_lab_status;
	private String up_treatment_code;
	private String up_doctor_id;
	
	private String up_surf;
	private String up_tooth;
	private String up_tooth_range;
	private String up_est_fee;
	private String up_required_date; 
	private String up_remark;
	
	private boolean checkuse;
	
	public SendLabModel(String treatment_id, String treatment_code, String doctor_id, String treatment_name, String treatment_date,
			String doctor_name, String surf, String tooth, String tooth_range, String hn, String patientname) {
		super();
		this.treatment_id = treatment_id;
		this.treatment_code = treatment_code;
		this.doctor_id 		= doctor_id;
		this.treatment_name = treatment_name;
		this.treatment_date = treatment_date;
		this.doctor_name 	= doctor_name;
		this.surf 			= surf;
		this.tooth 			= tooth;
		this.tooth_range 	= tooth_range;
		this.hn 			= hn;
		this.patientname 	= patientname;
	}
	public SendLabModel(String id, String service_id, String service_name, String lab_id, String lab_name, String patientname, String first_name_th, String treatment_code) {
		super();
		this.id = id;
		this.service_id = service_id;
		this.service_name = service_name; 
		this.treatment_code = treatment_code;
		this.lab_name = lab_name;
		this.patientname = patientname;
		this.first_name_th = first_name_th;
	}
	// history_treatment 

	//Contructors
	public SendLabModel(){}
 
	 
	public SendLabModel(String id, String service_id, String service_name, String lab_id, String lab_name, String hn, String patientname, String doctor_id,
			String first_name_th, String last_name_th, String first_name_en, String last_name_en, String create_date,
			String required_date, String update_date, String return_lab, String timebegin, String timeend, String remark, String lab_status, String treatment_code,
			String ref_invoice, String est_fee, String lab_fee, String status_end, boolean checkuse, String ref_id) {
		super();
		this.id				= id;
		this.service_id 	= service_id;
		this.service_name 	= service_name;
		this.lab_id 		= lab_id;
		this.lab_name 		= lab_name;
		this.hn				= hn;
		this.patientname		= patientname;
		this.doctor_id 		= doctor_id;
		this.first_name_th 	= first_name_th;
		this.last_name_th 	= last_name_th;
		this.first_name_en 	= first_name_en;
		this.last_name_en 	= last_name_en;
		this.create_date 	= create_date;
		this.required_date 	= required_date;
		this.update_date 	= update_date;
		this.return_lab 	= return_lab;
		this.timebegin 		= timebegin;
		this.timeend 		= timeend;
		this.remark 		= remark;
		this.lab_status 	= lab_status;
		this.treatment_code = treatment_code;
		this.ref_invoice 	= ref_invoice;
		this.est_fee 		= est_fee;
		this.lab_fee 		= lab_fee;
		this.status_end		= status_end;
		this.checkuse		= checkuse;
		this.ref_id			= ref_id;
	}


	//Reset
	public void Reset_LabModel(){
		this.lab_id = "";
		this.lab_name = ""; 
	}


	public String getService_id() {
		return service_id;
	}


	public void setService_id(String service_id) {
		this.service_id = service_id;
	}


	public String getService_name() {
		return service_name;
	}


	public void setService_name(String service_name) {
		this.service_name = service_name;
	}


	public String getLab_id() {
		return lab_id;
	}


	public void setLab_id(String lab_id) {
		this.lab_id = lab_id;
	}


	public String getLab_name() {
		return lab_name;
	}


	public void setLab_name(String lab_name) {
		this.lab_name = lab_name;
	}


	public String getDoctor_id() {
		return doctor_id;
	}


	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}


	public String getFirst_name_th() {
		return first_name_th;
	}


	public void setFirst_name_th(String first_name_th) {
		this.first_name_th = first_name_th;
	}


	public String getLast_name_th() {
		return last_name_th;
	}


	public void setLast_name_th(String last_name_th) {
		this.last_name_th = last_name_th;
	}


	public String getFirst_name_en() {
		return first_name_en;
	}


	public void setFirst_name_en(String first_name_en) {
		this.first_name_en = first_name_en;
	}


	public String getLast_name_en() {
		return last_name_en;
	}


	public void setLast_name_en(String last_name_en) {
		this.last_name_en = last_name_en;
	} 
	   

	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getLab_status() {
		return lab_status;
	}


	public void setLab_status(String lab_status) {
		this.lab_status = lab_status;
	}


	public String getCreate_date() {
		return create_date;
	}


	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}


	public String getRequired_date() {
		return required_date;
	}


	public void setRequired_date(String required_date) {
		this.required_date = required_date;
	}


	public String getUpdate_date() {
		return update_date;
	}


	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}


	public String getTreatment_code() {
		return treatment_code;
	}


	public void setTreatment_code(String treatment_code) {
		this.treatment_code = treatment_code;
	}


	public String getReturn_lab() {
		return return_lab;
	}


	public void setReturn_lab(String return_lab) {
		this.return_lab = return_lab;
	}


	public String getTreatment_name() {
		return treatment_name;
	}


	public void setTreatment_name(String treatment_name) {
		this.treatment_name = treatment_name;
	}


	public String getTreatment_date() {
		return treatment_date;
	}


	public void setTreatment_date(String treatment_date) {
		this.treatment_date = treatment_date;
	}


	public String getDoctor_name() {
		return doctor_name;
	}


	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
 
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRef_invoice() {
		return ref_invoice;
	}

	public void setRef_invoice(String ref_invoice) {
		this.ref_invoice = ref_invoice;
	}

	public String getEst_fee() {
		return est_fee;
	}

	public void setEst_fee(String est_fee) {
		this.est_fee = est_fee;
	}

	public String getLab_fee() {
		return lab_fee;
	}

	public void setLab_fee(String lab_fee) {
		this.lab_fee = lab_fee;
	}

	public String getS_service_name() {
		return s_service_name;
	}

	public void setS_service_name(String s_service_name) {
		this.s_service_name = s_service_name;
	}

	public String getS_lab_name() {
		return s_lab_name;
	}

	public void setS_lab_name(String s_lab_name) {
		this.s_lab_name = s_lab_name;
	}

	public String getS_doctor_name() {
		return s_doctor_name;
	}

	public void setS_doctor_name(String s_doctor_name) {
		this.s_doctor_name = s_doctor_name;
	}

	public String getS_treatment_code() {
		return s_treatment_code;
	}

	public void setS_treatment_code(String s_treatment_code) {
		this.s_treatment_code = s_treatment_code;
	}

	public String getSurf() {
		return surf;
	}

	public void setSurf(String surf) {
		this.surf = surf;
	}

	public String getTooth() {
		return tooth;
	}

	public void setTooth(String tooth) {
		this.tooth = tooth;
	}

	public String getTooth_range() {
		return tooth_range;
	}

	public void setTooth_range(String tooth_range) {
		this.tooth_range = tooth_range;
	}

	public String getTimebegin() {
		return timebegin;
	}

	public void setTimebegin(String timebegin) {
		this.timebegin = timebegin;
	}

	public String getTimeend() {
		return timeend;
	}

	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	} 

	public String getConfirm_status() {
		return confirm_status;
	}

	public void setConfirm_status(String confirm_status) {
		this.confirm_status = confirm_status;
	}

	public String getHn() {
		return hn;
	}

	public void setHn(String hn) {
		this.hn = hn;
	}

	public String getStatus_end() {
		return status_end;
	}

	public void setStatus_end(String status_end) {
		this.status_end = status_end;
	}
 
	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getReq_date_from() {
		return req_date_from;
	}

	public void setReq_date_from(String req_date_from) {
		this.req_date_from = req_date_from;
	}

	public String getReq_date_to() {
		return req_date_to;
	}

	public void setReq_date_to(String req_date_to) {
		this.req_date_to = req_date_to;
	}

	public String getUpd_date_from() {
		return upd_date_from;
	}

	public void setUpd_date_from(String upd_date_from) {
		this.upd_date_from = upd_date_from;
	}

	public String getUpd_date_to() {
		return upd_date_to;
	}

	public void setUpd_date_to(String upd_date_to) {
		this.upd_date_to = upd_date_to;
	}

	public String getRef_id() {
		return ref_id;
	}

	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}

	public String getUp_service_id() {
		return up_service_id;
	}

	public void setUp_service_id(String up_service_id) {
		this.up_service_id = up_service_id;
	}

	public String getUp_service_name() {
		return up_service_name;
	}

	public void setUp_service_name(String up_service_name) {
		this.up_service_name = up_service_name;
	}

	public String getUp_lab_id() {
		return up_lab_id;
	}

	public void setUp_lab_id(String up_lab_id) {
		this.up_lab_id = up_lab_id;
	}

	public String getUp_lab_name() {
		return up_lab_name;
	}

	public void setUp_lab_name(String up_lab_name) {
		this.up_lab_name = up_lab_name;
	}

	public String getUp_lab_status() {
		return up_lab_status;
	}

	public void setUp_lab_status(String up_lab_status) {
		this.up_lab_status = up_lab_status;
	}

	public String getUp_treatment_code() {
		return up_treatment_code;
	}

	public void setUp_treatment_code(String up_treatment_code) {
		this.up_treatment_code = up_treatment_code;
	}

	public String getUp_doctor_id() {
		return up_doctor_id;
	}

	public void setUp_doctor_id(String up_doctor_id) {
		this.up_doctor_id = up_doctor_id;
	}

	public String getUp_surf() {
		return up_surf;
	}

	public void setUp_surf(String up_surf) {
		this.up_surf = up_surf;
	}

	public String getUp_tooth() {
		return up_tooth;
	}

	public void setUp_tooth(String up_tooth) {
		this.up_tooth = up_tooth;
	}

	public String getUp_tooth_range() {
		return up_tooth_range;
	}

	public void setUp_tooth_range(String up_tooth_range) {
		this.up_tooth_range = up_tooth_range;
	}

	public String getUp_est_fee() {
		return up_est_fee;
	}

	public void setUp_est_fee(String up_est_fee) {
		this.up_est_fee = up_est_fee;
	}

	public String getUp_required_date() {
		return up_required_date;
	}

	public void setUp_required_date(String up_required_date) {
		this.up_required_date = up_required_date;
	}

	public String getUp_remark() {
		return up_remark;
	}

	public void setUp_remark(String up_remark) {
		this.up_remark = up_remark;
	}

	public boolean isCheckuse() {
		return checkuse;
	}

	public void setCheckuse(boolean checkuse) {
		this.checkuse = checkuse;
	}
	public String getTreatment_id() {
		return treatment_id;
	}
	public void setTreatment_id(String treatment_id) {
		this.treatment_id = treatment_id;
	}
 
	
	//Get Set
}
