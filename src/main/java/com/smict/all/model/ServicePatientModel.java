package com.smict.all.model;

import java.util.List;

import com.smict.person.model.PatientModel;
import com.smict.person.model.TelephoneModel;

public class ServicePatientModel extends PatientModel{ 
	
	private String datetime;  
	private String tel_number;  
	
	private String doctor_name, room_name, room_status, treatment_code, treatment_name, price_standard;
	private int doctor_id, room_id, treatment_id, count; 
	private String tooth_tooth, surf_tooth, surf, quadrant, arch, treatment_mode;
	
	public ServicePatientModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ServicePatientModel(PatientModel patModel) {
		super();
		// TODO Auto-generated constructor stub
		this.hn = patModel.getHn();
		this.firstname_th = patModel.getFirstname_th();
		this.lastname_th = patModel.getLastname_th();
		this.firstname_en = patModel.getFirstname_en();
		this.lastname_en = patModel.getLastname_en();
		this.age = patModel.getAge();
		this.pre_name_id = patModel.getPre_name_id();
		this.pre_name_th = patModel.getPre_name_th();
		this.birth_date = patModel.getBirth_date();
		this.identification = patModel.getIdentification();
		this.identification_type = patModel.getIdentification_type();
		this.relation_emp = patModel.getRelation_emp();
		this.register_branch = patModel.getRegister_branch();
		this.remark = patModel.getRemark();
		this.profile_pic = patModel.getProfile_pic();
		this.deposit_money = patModel.getDeposit_money();
		this.status_married = patModel.getStatus_married();
		this.line_id = patModel.getLine_id();
		this.email = patModel.getEmail();
		this.nickname = patModel.getNickname();
		this.bloodgroup = patModel.getBloodgroup();
		this.patient_type = patModel.getPatient_type();
		this.contact_time_start = patModel.getContact_time_start();
		this.contact_time_end = patModel.getContact_time_end();
		this.weight = patModel.getWeight();
		this.height = patModel.getHeight();
		this.typerecommended = patModel.getTyperecommended();
		this.addrModel = patModel.getAddrModel();
		this.ListTelModel = patModel.getListTelModel();
		this.famModel = patModel.getFamModel();
		this.beallergic = patModel.getBeallergic();
		this.congenList = patModel.getCongenList();
		this.patneed_id = patModel.getPatneed_id();
		this.patient_type_name = patModel.getPatient_type_name();
		this.contypeList = patModel.getContypeList();
		this.patFileList = patModel.getPatFileList();
		this.fam_id = patModel.getFam_id();
		this.be_allergic_id = patModel.getBe_allergic_id();
		this.pat_congenital_disease_id = patModel.getPat_congenital_disease_id();
		this.status = patModel.getStatus();
	}
	
	public ServicePatientModel(String hn, String pre_name_id, String first_name_th, String last_name_th, String first_name_en, String last_name_en, 
			String birth_date, double deposit_money, String status, List<TelephoneModel> telModel) {
		this.hn = hn;
		this.pre_name_id 	= pre_name_id;
		this.firstname_th 	= first_name_th;
		this.lastname_th 	= last_name_th;
		this.firstname_en 	= first_name_en;
		this.lastname_en 	= last_name_en; 
		this.birth_date 	= birth_date;
		this.deposit_money 	= deposit_money; 
		this.status 		= status;
		this.ListTelModel 	= telModel;
	}
	public ServicePatientModel(String doctor_name, String room_name, String room_status, int doctor_id, int room_id) {
		super();
		this.doctor_name = doctor_name;
		this.room_name = room_name;
		this.room_status = room_status;
		this.doctor_id = doctor_id;
		this.room_id = room_id;
	} 
	public ServicePatientModel(int treatment_id, int count, String doctor_name, String treatment_code, 
			String treatment_name, String price_standard, String treatment_mode) {
		super();
		this.treatment_id	= treatment_id;
		this.count			= count;
		this.doctor_name 	= doctor_name;
		this.treatment_code = treatment_code;
		this.treatment_name = treatment_name;
		this.price_standard = price_standard; 
		this.treatment_mode = treatment_mode; 
	} 
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getTel_number() {
		return tel_number;
	}
	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}  
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getRoom_status() {
		return room_status;
	}
	public void setRoom_status(String room_status) {
		this.room_status = room_status;
	} 

	public String getTreatment_code() {
		return treatment_code;
	}

	public void setTreatment_code(String treatment_code) {
		this.treatment_code = treatment_code;
	}

	public String getTreatment_name() {
		return treatment_name;
	}

	public void setTreatment_name(String treatment_name) {
		this.treatment_name = treatment_name;
	}

	public String getPrice_standard() {
		return price_standard;
	}

	public void setPrice_standard(String price_standard) {
		this.price_standard = price_standard;
	}

	public int getTreatment_id() {
		return treatment_id;
	}

	public void setTreatment_id(int treatment_id) {
		this.treatment_id = treatment_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getTooth_tooth() {
		return tooth_tooth;
	}

	public void setTooth_tooth(String tooth_tooth) {
		this.tooth_tooth = tooth_tooth;
	}

	public String getSurf_tooth() {
		return surf_tooth;
	}

	public void setSurf_tooth(String surf_tooth) {
		this.surf_tooth = surf_tooth;
	}

	public String getSurf() {
		return surf;
	}

	public void setSurf(String surf) {
		this.surf = surf;
	}

	public String getQuadrant() {
		return quadrant;
	}

	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public String getTreatment_mode() {
		return treatment_mode;
	}

	public void setTreatment_mode(String treatment_mode) {
		this.treatment_mode = treatment_mode;
	}
	 
 
}