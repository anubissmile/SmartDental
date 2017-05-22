package com.smict.person.model;

import java.util.List;

public class Person {
	public String pre_name_id, firstname_th, lastname_th, firstname_en,
	lastname_en, birth_date, identification, identification_type,
	remark, profile_pic, status_married, nickname,
	pre_name,birth_date_en, pre_name_th, pre_name_en;
	
	private String education_vocabulary_th,education_vocabulary_en;
	private String education_id,education_th,education_en,educational_background;
	//employee
	public String empuser,emppassword,emp_id,branch_id,hired_date,is_asistant,tel_number,work_status,empname_th,emplastname_th; 
	
	public int tel_id,addr_id,age,work_history_id,education_vocabulary_id, fam_id, user_type_id;
	
	private String location,position,start_date,end_date,remark_message,work_type,salary;

	public String married_statusid, married_statusname, emergency_call;
	
	private String lineId, email;
	


	public List<TelephoneModel> ListTelModel;
	public List<AddressModel> addrModel;
	public List<FamilyModel> famModel;
	
	//contructor
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Person(String pre_name_id, String firstname_th, String lastname_th, String firstname_en, String lastname_en,
			String birth_date, String identification, String identification_type, String remark, 
			String profile_pic,List<TelephoneModel> telModel,List<AddressModel> addrModel,List<FamilyModel> famModel,
			String status_married) {
		super();
		this.pre_name_id = pre_name_id;
		this.firstname_th = firstname_th;
		this.lastname_th = lastname_th;
		this.firstname_en = firstname_en;
		this.lastname_en = lastname_en;
		this.birth_date = birth_date;
		this.identification = identification;
		this.identification_type = identification_type;
		this.remark = remark;
		this.profile_pic = profile_pic;
		this.ListTelModel = telModel;
		this.addrModel = addrModel;
		this.famModel = famModel;
		this.status_married = status_married;
	}
	public Person(String married_statusid,String married_statusname){
		this.married_statusid = married_statusid;
		this.married_statusname = married_statusname;
	}
	
	//Reset
	public void Person_reset(){
		this.pre_name_id = "";
		this.firstname_th = "";
		this.lastname_th = "";
		this.firstname_en = "";
		this.lastname_en = "";
		this.birth_date = "";
		this.identification = "";
		this.identification_type = "";
		this.remark = "";
		this.profile_pic = "";
		this.status_married = "";
	}

	//get set
	public String getPre_name_id() {
		return pre_name_id;
	}
	public void setPre_name_id(String pre_name_id) {
		this.pre_name_id = pre_name_id;
	}
	public String getEmergency_call() {
		return emergency_call;
	}
	public String getEducation_id() {
		return education_id;
	}

	public void setEducation_id(String education_id) {
		this.education_id = education_id;
	}
	public int getWork_history_id() {
		return work_history_id;
	}

	public void setWork_history_id(int work_history_id) {
		this.work_history_id = work_history_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getRemark_message() {
		return remark_message;
	}

	public void setRemark_message(String remark_message) {
		this.remark_message = remark_message;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getEducation_th() {
		return education_th;
	}

	public void setEducation_th(String education_th) {
		this.education_th = education_th;
	}

	public String getEducation_en() {
		return education_en;
	}

	public void setEducation_en(String education_en) {
		this.education_en = education_en;
	}
	public void setEmergency_call(String emergency_call) {
		this.emergency_call = emergency_call;
	}

	public List<TelephoneModel> getListTelModel() {
		return ListTelModel;
	}

	public void setListTelModel(List<TelephoneModel> listTelModel) {
		ListTelModel = listTelModel;
	}

	public List<AddressModel> getAddrModel() {
		return addrModel;
	}

	public void setAddrModel(List<AddressModel> addrModel) {
		this.addrModel = addrModel;
	}

	public List<FamilyModel> getFamModel() {
		return famModel;
	}

	public void setFamModel(List<FamilyModel> famModel) {
		this.famModel = famModel;
	}

	public String getStatus_married() {
		return status_married;
	}

	public void setStatus_married(String status_married) {
		this.status_married = status_married;
	}

	public int getEducation_vocabulary_id() {
		return education_vocabulary_id;
	}

	public void setEducation_vocabulary_id(int education_vocabulary_id) {
		this.education_vocabulary_id = education_vocabulary_id;
	}

	public String getEducation_vocabulary_th() {
		return education_vocabulary_th;
	}

	public void setEducation_vocabulary_th(String education_vocabulary_th) {
		this.education_vocabulary_th = education_vocabulary_th;
	}

	public String getEducation_vocabulary_en() {
		return education_vocabulary_en;
	}

	public void setEducation_vocabulary_en(String education_vocabulary_en) {
		this.education_vocabulary_en = education_vocabulary_en;
	}

	public String getBirth_date_en() {
		return birth_date_en;
	}

	public void setBirth_date_en(String birth_date_en) {
		this.birth_date_en = birth_date_en;
	}

	public String getFirstname_th() {
		return firstname_th;
	}
	public void setFirstname_th(String firstname_th) {
		this.firstname_th = firstname_th;
	}
	public String getLastname_th() {
		return lastname_th;
	}
	public void setLastname_th(String lastname_th) {
		this.lastname_th = lastname_th;
	}
	public String getFirstname_en() {
		return firstname_en;
	}
	public void setFirstname_en(String firstname_en) {
		this.firstname_en = firstname_en;
	}
	public String getLastname_en() {
		return lastname_en;
	}
	public void setLastname_en(String lastname_en) {
		this.lastname_en = lastname_en;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getIdentification_type() {
		return identification_type;
	}
	public void setIdentification_type(String identification_type) {
		this.identification_type = identification_type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	/*public List<TelephoneModel> getTelModel() {
		
		List<TelephoneModel> listTel = new ArrayList<TelephoneModel>();
		listTel.add(telModel);
		return  listTel;
	}
	public void setTelModel(List<TelephoneModel> telModel) {
		for(TelephoneModel retriveTelModel : telModel){
			this.telModel = retriveTelModel;
		}
	}*/
	/*public AddressModel getAddrModel() {
		return addrModel;
	}
	public void setAddrModel(AddressModel addrModel) {
		this.addrModel = addrModel;
	}
	public FamilyModel getFamModel() {
		return famModel;
	}
	public void setFamModel(FamilyModel famModel) {
		this.famModel = famModel;
	}*/
	
	public String getMarried_statusid() {
		return married_statusid;
	}

	public void setMarried_statusid(String married_statusid) {
		this.married_statusid = married_statusid;
	}

	public String getMarried_statusname() {
		return married_statusname;
	}

	public void setMarried_statusname(String married_statusname) {
		this.married_statusname = married_statusname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public int getTel_id() {
		return tel_id;
	}

	public void setTel_id(int tel_id) {
		this.tel_id = tel_id;
	}

	public int getAddr_id() {
		return addr_id;
	}

	public void setAddr_id(int addr_id) {
		this.addr_id = addr_id;
	}


	public String getPre_name() {
		return pre_name;
	}

	public void setPre_name(String pre_name) {
		this.pre_name = pre_name;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getFam_id() {
		return fam_id;
	}

	public void setFam_id(int fam_id) {
		this.fam_id = fam_id;
	}

	public int getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}
	
	public String getPre_name_th() {
		return pre_name_th;
	}
	public void setPre_name_th(String pre_name_th) {
		this.pre_name_th = pre_name_th;
	}

	public String getPre_name_en() {
		return pre_name_en;
	}

	public void setPre_name_en(String pre_name_en) {
		this.pre_name_en = pre_name_en;
	}

	public String getEmpuser() {
		return empuser;
	}

	public String getEmppassword() {
		return emppassword;
	}

	public void setEmpuser(String empuser) {
		this.empuser = empuser;
	}

	public void setEmppassword(String emppassword) {
		this.emppassword = emppassword;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public String getHired_date() {
		return hired_date;
	}

	public void setHired_date(String hired_date) {
		this.hired_date = hired_date;
	}

	public String getIs_asistant() {
		return is_asistant;
	}

	public void setIs_asistant(String is_asistant) {
		this.is_asistant = is_asistant;
	}

	public String getTel_number() {
		return tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}

	public String getWork_status() {
		return work_status;
	}

	public void setWork_status(String work_status) {
		this.work_status = work_status;
	}

	public String getEmpname_th() {
		return empname_th;
	}

	public String getEmplastname_th() {
		return emplastname_th;
	}

	public void setEmpname_th(String empname_th) {
		this.empname_th = empname_th;
	}

	public void setEmplastname_th(String emplastname_th) {
		this.emplastname_th = emplastname_th;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducational_background() {
		return educational_background;
	}

	public void setEducational_background(String educational_background) {
		this.educational_background = educational_background;
	}

}
