package com.smict.person.model;

import java.util.List;

import com.smict.all.model.ContypeModel;
import com.smict.all.model.PatFileModel;
import com.smict.all.model.ServicePatientModel;
import com.smict.document.model.DocumentModel;
import com.smict.product.model.ProductModel;

public class PatientModel extends Person {
	
	public String hn, hnFormat, hnBranch, relation_emp, line_id, email,
	bloodgroup, patient_type, contact_time_start, contact_time_end, 
	register_branch, create_by, update_by, confirm_brush_teeth,
	confirm_pregnant, confirm_now_receive_drug, drug_name, confirm_now_treatment,
	confirm_hospital_doctor_now_treatment, doctor_hospital_name, confirm_congenital,identification_type,identification,
	patient_type_name, status, other_congenital_disease, career;
	public String[] be_allergic;
	public String beallergic_name_th,beallergic_name_en,product_id,other_beallergic_name_th;
	public double deposit_money,weight,height;
	public int typerecommended, week_of_pregent, be_allergic_id, patneed_id, 
	pat_congenital_disease_id;
	public List<ProductModel> beallergic;
	public String[] patneed_message, congenital_disease , document_need;
	public List<CongenitalDiseaseModel> congenList;
	public List<ContypeModel> contypeList;
	public List<PatFileModel> patFileList;
	public List<DocumentModel> documentneed;
	private String searchPat;
	private boolean isNewRecord;
	
	/**
	 * EMERGENCY CALL NUMBER.
	 */
	private String emTellNumber, emTellRelevantPerson, emRelative;
	private int emTellID;
	
	//Contructor
	public PatientModel() {

		// TODO Auto-generated constructor stub
	}

	public PatientModel(String identification_type,String identification){
		this.identification_type = identification_type;
		this.identification = identification;
	}
	
	public PatientModel(String hn, String relation_emp, double deposit_money,String pre_name_id, String firstname_th, String lastname_th, String firstname_en,
			String lastname_en, String birth_date, String identification, String identification_type, String remark,
			String profile_pic, List<TelephoneModel> telModel, List<AddressModel> addrModel, List<FamilyModel> famModel,String status_married,
			String line_id,String email,String bloodgroup,String patient_type,String contact_time_start,String contact_time_end,
			double weight,double height,int typerecommended,String register_branch) {
		super(pre_name_id, firstname_th, lastname_th, firstname_en, lastname_en, birth_date, identification,
				identification_type, remark, profile_pic, telModel, addrModel, famModel, status_married);
		this.hn = hn;
		this.relation_emp = relation_emp;
		this.deposit_money = deposit_money;
		this.line_id = line_id;
		this.email = email;
		this.bloodgroup = bloodgroup;
		this.patient_type = patient_type;
		this.contact_time_start = contact_time_start;
		this.contact_time_end = contact_time_end;
		this.weight = weight;
		this.height = height;
		this.typerecommended = typerecommended;
		this.register_branch = register_branch;
	}
	
	
	public PatientModel(ServicePatientModel servicePatModel) {
		// TODO Auto-generated constructor stub
		super();
		// TODO Auto-generated constructor stub
		this.hn = servicePatModel.getHn();
		this.hnFormat = servicePatModel.getHnFormat();
		this.firstname_th = servicePatModel.getFirstname_th();
		this.lastname_th = servicePatModel.getLastname_th();
		this.firstname_en = servicePatModel.getFirstname_en();
		this.lastname_en = servicePatModel.getLastname_en();
		this.age = servicePatModel.getAge();
		this.pre_name_id = servicePatModel.getPre_name_id();
		this.pre_name_th = servicePatModel.getPre_name_th();
		this.birth_date = servicePatModel.getBirth_date();
		this.identification = servicePatModel.getIdentification();
		this.identification_type = servicePatModel.getIdentification_type();
		this.relation_emp = servicePatModel.getRelation_emp();
		this.register_branch = servicePatModel.getRegister_branch();
		this.remark = servicePatModel.getRemark();
		this.profile_pic = servicePatModel.getProfile_pic();
		this.deposit_money = servicePatModel.getDeposit_money();
		this.status_married = servicePatModel.getStatus_married();
		this.line_id = servicePatModel.getLine_id();
		this.email = servicePatModel.getEmail();
		this.nickname = servicePatModel.getNickname();
		this.bloodgroup = servicePatModel.getBloodgroup();
		this.patient_type = servicePatModel.getPatient_type();
		this.contact_time_start = servicePatModel.getContact_time_start();
		this.contact_time_end = servicePatModel.getContact_time_end();
		this.weight = servicePatModel.getWeight();
		this.height = servicePatModel.getHeight();
		this.typerecommended = servicePatModel.getTyperecommended();
		this.addrModel = servicePatModel.getAddrModel();
		this.ListTelModel = servicePatModel.getListTelModel();
		this.famModel = servicePatModel.getFamModel();
		this.beallergic = servicePatModel.getBeallergic();
		this.congenList = servicePatModel.getCongenList();
		this.patneed_id = servicePatModel.getPatneed_id();
		this.patient_type_name = servicePatModel.getPatient_type_name();
		this.contypeList = servicePatModel.getContypeList();
		this.patFileList = servicePatModel.getPatFileList();
		this.fam_id = servicePatModel.getFam_id();
		this.be_allergic_id = servicePatModel.getBe_allergic_id();
		this.pat_congenital_disease_id = servicePatModel.getPat_congenital_disease_id();
		this.status = servicePatModel.getStatus();
		this.congenList = servicePatModel.getCongenList();
		this.career = servicePatModel.getCareer();
		this.document_need = servicePatModel.getDocument_need();
		this.documentneed = servicePatModel.getDocumentneed();
		this.patneed_message = servicePatModel.getPatneed_message();
	}
	//Get Set
	public String[] getPatneed_message() {
		return patneed_message;
	}
	public void setPatneed_message(String[] patneed_message) {
		this.patneed_message = patneed_message;
	}
	
	public int getPat_congenital_disease_id() {
		return pat_congenital_disease_id;
	}
	public void setPat_congenital_disease_id(int pat_congenital_disease_id) {
		this.pat_congenital_disease_id = pat_congenital_disease_id;
	}
	public int getPatneed_id() {
		return patneed_id;
	}
	public void setPatneed_id(int patneed_id) {
		this.patneed_id = patneed_id;
	}
	public String getHn() {
		return hn;
	}
	public void setHn(String hn) {
		this.hn = hn;
	}
	public List<ProductModel> getBeallergic() {
		return beallergic;
	}
	public void setBeallergic(List<ProductModel> beallergic) {
		this.beallergic = beallergic;
	}
	public int getBe_allergic_id() {
		return be_allergic_id;
	}
	public void setBe_allergic_id(int be_allergic_id) {
		this.be_allergic_id = be_allergic_id;
	}
	public String getRegister_branch() {
		return register_branch;
	}
	public void setRegister_branch(String register_branch) {
		this.register_branch = register_branch;
	}
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentification_type() {
		return identification_type;
	}
	public void setIdentification_type(String identification_type) {
		this.identification_type = identification_type;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getBloodgroup() {
		return bloodgroup;
	}
	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}
	public String getPatient_type() {
		return patient_type;
	}
	public void setPatient_type(String patient_type) {
		this.patient_type = patient_type;
	}
	public String getContact_time_start() {
		return contact_time_start;
	}
	public void setContact_time_start(String contact_time_start) {
		this.contact_time_start = contact_time_start;
	}
	public String getContact_time_end() {
		return contact_time_end;
	}
	public void setContact_time_end(String contact_time_end) {
		this.contact_time_end = contact_time_end;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getConfirm_brush_teeth() {
		return confirm_brush_teeth;
	}
	public void setConfirm_brush_teeth(String confirm_brush_teeth) {
		this.confirm_brush_teeth = confirm_brush_teeth;
	}
	public String getConfirm_pregnant() {
		return confirm_pregnant;
	}
	public void setConfirm_pregnant(String confirm_pregnant) {
		this.confirm_pregnant = confirm_pregnant;
	}
	public String getConfirm_now_receive_drug() {
		return confirm_now_receive_drug;
	}
	public void setConfirm_now_receive_drug(String confirm_now_receive_drug) {
		this.confirm_now_receive_drug = confirm_now_receive_drug;
	}
	public String getDrug_name() {
		return drug_name;
	}
	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}
	public String getConfirm_now_treatment() {
		return confirm_now_treatment;
	}
	public void setConfirm_now_treatment(String confirm_now_treatment) {
		this.confirm_now_treatment = confirm_now_treatment;
	}
	public String getConfirm_hospital_doctor_now_treatment() {
		return confirm_hospital_doctor_now_treatment;
	}
	public void setConfirm_hospital_doctor_now_treatment(String confirm_hospital_doctor_now_treatment) {
		this.confirm_hospital_doctor_now_treatment = confirm_hospital_doctor_now_treatment;
	}
	public String getDoctor_hospital_name() {
		return doctor_hospital_name;
	}
	public void setDoctor_hospital_name(String doctor_hospital_name) {
		this.doctor_hospital_name = doctor_hospital_name;
	}
	public String getConfirm_congenital() {
		return confirm_congenital;
	}
	public void setConfirm_congenital(String confirm_congenital) {
		this.confirm_congenital = confirm_congenital;
	}
	public int getWeek_of_pregent() {
		return week_of_pregent;
	}
	public void setWeek_of_pregent(int week_of_pregent) {
		this.week_of_pregent = week_of_pregent;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getTyperecommended() {
		return typerecommended;
	}
	public void setTyperecommended(int typerecommended) {
		this.typerecommended = typerecommended;
	}
	public String getRelation_emp() {
		return relation_emp;
	}
	public void setRelation_emp(String relation_emp) {
		this.relation_emp = relation_emp;
	}
	public double getDeposit_money() {
		return deposit_money;
	}
	public void setDeposit_money(double deposit_money) {
		this.deposit_money = deposit_money;
	}
	public List<CongenitalDiseaseModel> getCongenList() {
		return congenList;
	}
	public void setCongenList(List<CongenitalDiseaseModel> congenList) {
		this.congenList = congenList;
	}
	public String getPatient_type_name() {
		return patient_type_name;
	}
	public void setPatient_type_name(String patient_type_name) {
		this.patient_type_name = patient_type_name;
	}
	public List<ContypeModel> getContypeList() {
		return contypeList;
	}
	public void setContypeList(List<ContypeModel> contypeList) {
		this.contypeList = contypeList;
	}
	public List<PatFileModel> getPatFileList() {
		return patFileList;
	}
	public void setPatFileList(List<PatFileModel> patFileList) {
		this.patFileList = patFileList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getCongenital_disease() {
		return congenital_disease;
	}
	public void setCongenital_disease(String[] congenital_disease) {
		this.congenital_disease = congenital_disease;
	}
	public String getOther_congenital_disease() {
		return other_congenital_disease;
	}
	public void setOther_congenital_disease(String other_congenital_disease) {
		this.other_congenital_disease = other_congenital_disease;
	}
	public String getHnFormat() {
		return hnFormat;
	}
	public void setHnFormat(String hnFormat) {
		this.hnFormat = hnFormat;
	}
	public String getSearchPat() {
		return searchPat;
	}
	public void setSearchPat(String searchPat) {
		this.searchPat = searchPat;
	}

	public String getHnBranch() {
		return hnBranch;
	}

	public void setHnBranch(String hnBranch) {
		this.hnBranch = hnBranch;
	}

	public boolean isNewRecord() {
		return isNewRecord;
	}

	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	public String[] getBe_allergic() {
		return be_allergic;
	}

	public void setBe_allergic(String[] be_allergic) {
		this.be_allergic = be_allergic;
	}

	public String getBeallergic_name_th() {
		return beallergic_name_th;
	}

	public String getBeallergic_name_en() {
		return beallergic_name_en;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setBeallergic_name_th(String beallergic_name_th) {
		this.beallergic_name_th = beallergic_name_th;
	}

	public void setBeallergic_name_en(String beallergic_name_en) {
		this.beallergic_name_en = beallergic_name_en;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getOther_beallergic_name_th() {
		return other_beallergic_name_th;
	}

	public void setOther_beallergic_name_th(String other_beallergic_name_th) {
		this.other_beallergic_name_th = other_beallergic_name_th;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String[] getDocument_need() {
		return document_need;
	}

	public void setDocument_need(String[] document_need) {
		this.document_need = document_need;
	}

	public List<DocumentModel> getDocumentneed() {
		return documentneed;
	}

	public void setDocumentneed(List<DocumentModel> documentneed) {
		this.documentneed = documentneed;
	}

	public String getEmTellNumber() {
		return emTellNumber;
	}

	public void setEmTellNumber(String emTellNumber) {
		this.emTellNumber = emTellNumber;
	}

	public String getEmTellRelevantPerson() {
		return emTellRelevantPerson;
	}

	public void setEmTellRelevantPerson(String emTellRelevantPerson) {
		this.emTellRelevantPerson = emTellRelevantPerson;
	}

	public String getEmRelative() {
		return emRelative;
	}

	public void setEmRelative(String emRelative) {
		this.emRelative = emRelative;
	}

	public int getEmTellID() {
		return emTellID;
	}

	public void setEmTellID(int emTellID) {
		this.emTellID = emTellID;
	}
}
