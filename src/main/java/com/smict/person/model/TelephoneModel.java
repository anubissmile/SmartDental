package com.smict.person.model;

public class TelephoneModel {
	private String tel_number,tel_typename,tel_telgroupname,owners;
	private int tel_id,tel_typeid,tel_groupid;
	
	private String relevant_person, tel_relative;
	
	//Constructor
	public TelephoneModel(){}
	public TelephoneModel(int tel_id,String tel_number,int tel_typeid){
		this.tel_id = tel_id;
		this.tel_number = tel_number;
		this.tel_typeid = tel_typeid;
	}
	public TelephoneModel(String tel_typename,int tel_typeid){
		this.tel_typeid = tel_typeid;
		this.tel_typename = tel_typename;
	}
	public TelephoneModel(String tel_number, String tel_typename, String tel_telgroupname, String owners, int tel_id,
			int tel_typeid, int tel_groupid) {
		super();
		this.tel_number = tel_number;
		this.tel_typename = tel_typename;
		this.tel_telgroupname = tel_telgroupname;
		this.owners = owners;
		this.tel_id = tel_id;
		this.tel_typeid = tel_typeid;
		this.tel_groupid = tel_groupid;
	}
	public TelephoneModel(TelephoneModel telModel) {
		// TODO Auto-generated constructor stub
		this.tel_number = telModel.getTel_number();
		this.tel_typename = telModel.getTel_typename();
		this.tel_telgroupname = telModel.getTel_telgroupname();
		this.owners = telModel.getOwners();
		this.tel_id = telModel.getTel_id();
		this.tel_typeid = telModel.getTel_typeid();
		this.tel_groupid = telModel.getTel_groupid();
	}
	//Reset
	public void Reset_Telephone(){
		this.tel_number = "";
		this.tel_typename = "";
		this.tel_telgroupname = "";
		this.owners = "";
		this.tel_id = 0;
		this.tel_typeid = 0;
		this.tel_groupid = 0;
	}
	
	
	//Get Set
	public String getTel_typename() {
		return tel_typename;
	}
	public void setTel_typename(String tel_typename) {
		this.tel_typename = tel_typename;
	}
	public String getOwners() {
		return owners;
	}
	public void setOwners(String owners) {
		this.owners = owners;
	}
	public int getTel_groupid() {
		return tel_groupid;
	}
	public void setTel_groupid(int tel_groupid) {
		this.tel_groupid = tel_groupid;
	}
	public String getTel_telgroupname() {
		return tel_telgroupname;
	}
	public void setTel_telgroupname(String tel_telgroupname) {
		this.tel_telgroupname = tel_telgroupname;
	}
	public String getTel_number() {
		return tel_number;
	}
	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}
	public int getTel_id() {
		return tel_id;
	}
	public void setTel_id(int tel_id) {
		this.tel_id = tel_id;
	}
	public int getTel_typeid() {
		return tel_typeid;
	}
	public void setTel_typeid(int tel_typeid) {
		this.tel_typeid = tel_typeid;
	}
	public String getRelevant_person() {
		return relevant_person;
	}
	public void setRelevant_person(String relevant_person) {
		this.relevant_person = relevant_person;
	}
	public String getTel_relative() {
		return tel_relative;
	}
	public void setTel_relative(String tel_relative) {
		this.tel_relative = tel_relative;
	}
	
	
	
	
}
