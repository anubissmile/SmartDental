package com.smict.person.model;

public class FamilyModel {
	public int family_id, user_type_id, tel_typeid;
	public String ref_user, family_user_status, tel_number, tel_typename,
					firstname_th, lastname_th, firstname_en,
					lastname_en, user_type_name;
	
	private String famPatientHN, famIdentication;
	private String relativeDescription;
	//constructor
	public FamilyModel(int family_id, int user_type_id, String firstname_th, String lastname_th,
			 String firstname_en,String lastname_en,String user_type_name) {
		super();
		this.family_id = family_id;
		this.user_type_id = user_type_id;
		this.firstname_th = firstname_th;
		this.lastname_th = lastname_th;
		this.firstname_en = firstname_en;
		this.lastname_en = lastname_en;
		this.user_type_name = user_type_name;
	}
	
	public FamilyModel(int family_id, int user_type_id, String ref_user, String family_user_status) {
		super();
		this.family_id = family_id;
		this.user_type_id = user_type_id;
		this.ref_user = ref_user;
		this.family_user_status = family_user_status;
	}
	
	public FamilyModel(){
		
	}

	//reset
	public void Reset_FamilyModel(){
		this.ref_user = "";
		this.family_user_status = "";
	}
	// Get Set
	public int getFamily_id() {
		return family_id;
	}
	public void setFamily_id(int family_id) {
		this.family_id = family_id;
	}
	public int getUser_type_id() {
		return user_type_id;
	}
	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}
	public String getRef_user() {
		return ref_user;
	}
	public void setRef_user(String ref_user) {
		this.ref_user = ref_user;
	}
	public String getFamily_user_status() {
		return family_user_status;
	}
	public void setFamily_user_status(String family_user_status) {
		this.family_user_status = family_user_status;
	}

	public int getTel_typeid() {
		return tel_typeid;
	}

	public void setTel_typeid(int tel_typeid) {
		this.tel_typeid = tel_typeid;
	}

	public String getTel_number() {
		return tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}

	public String getTel_typename() {
		return tel_typename;
	}

	public void setTel_typename(String tel_typename) {
		this.tel_typename = tel_typename;
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

	public String getUser_type_name() {
		return user_type_name;
	}

	public void setUser_type_name(String user_type_name) {
		this.user_type_name = user_type_name;
	}

	public String getFamPatientHN() {
		return famPatientHN;
	}

	public void setFamPatientHN(String famPatientHN) {
		this.famPatientHN = famPatientHN;
	}

	public String getFamIdentication() {
		return famIdentication;
	}

	public void setFamIdentication(String famIdentication) {
		this.famIdentication = famIdentication;
	}

	public String getRelativeDescription() {
		return relativeDescription;
	}

	public void setRelativeDescription(String relativeDescription) {
		this.relativeDescription = relativeDescription;
	}
	
	
}
