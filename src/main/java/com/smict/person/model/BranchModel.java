package com.smict.person.model;

public class BranchModel{
	
	private int brand_id;
	private String brand_name; 
	private String socialSecurity;
	private String branch_id;
	private String branch_name;
	private String branch_code;

	private int next_number;
	private String addr_id;
	
	private String tel_id;
	private String tels_id;
	private String tel;
	private String tels;
	
	private int doctor_id;
	private String doctor_name;
	private int price_doctor;
	
	// address
	private String addr_no;
	private String addr_bloc;
	private String addr_village;
	private String addr_alley;
	private String addr_road;
	private String addr_provinceid;
	private String addr_aumphurid;
	private String addr_districtid;
	private String addr_zipcode;
	private String addr_typeid;
	
	// search
	private String s_brand_name;
	private String s_branch_id;
	private String s_branch_name;
	private String s_docter_name;
	
	// Contructor
	public BranchModel(){ 
		 
	} 
	
	public BranchModel(int brand_id, String brand_name, String branch_id, String branch_name, 
			String doctor_name, String tel_id, String tels_id, String branch_code) 
	{  
		this.brand_id		= brand_id;
		this.brand_name 	= brand_name;
		this.branch_id 		= branch_id;
		this.branch_code	= branch_code;
		this.branch_name 	= branch_name;
		this.doctor_name 	= doctor_name;
		this.tel_id 		= tel_id;
		this.tels_id 		= tels_id;
	}  
	public BranchModel(int brand_id, String brand_name, String branch_id, String branch_name, int price_doctor, int doctor_id, String doctor_name, String tel_id, String tels_id,
			String addr_no, String addr_bloc, String addr_village, String addr_alley,String addr_road, String addr_provinceid, String addr_aumphurid, 
			String addr_districtid, String addr_zipcode) 
	{ 
		this.brand_id		= brand_id;
		this.brand_name 	= brand_name;
		this.branch_id 		= branch_id;
		this.branch_name 	= branch_name;
		this.price_doctor	= price_doctor;
		this.doctor_id 		= doctor_id;
		this.doctor_name 	= doctor_name;
		this.tel_id 		= tel_id;
		this.tels_id 		= tels_id;
		
		this.addr_no			= addr_no;
		this.addr_bloc 			= addr_bloc;
		this.addr_village 		= addr_village;
		this.addr_alley 		= addr_alley;
		this.addr_road 			= addr_road;
		this.addr_provinceid 	= addr_provinceid;
		this.addr_aumphurid 	= addr_aumphurid;
		this.addr_districtid 	= addr_districtid;
		this.addr_zipcode 		= addr_zipcode;
	}  
	
	public void setBranchDetail(int brand_id, String brand_name, String branch_id, String branch_code, String branch_name, int price_doctor, int doctor_id, String doctor_name, String tel_id, String tels_id,
			String addr_no, String addr_bloc, String addr_village, String addr_alley,String addr_road, String addr_provinceid, String addr_aumphurid, 
			String addr_districtid, String addr_zipcode){
		
		this.brand_id		= brand_id;
		this.brand_name 	= brand_name;
		this.branch_id 		= branch_id;
		this.branch_code 	= branch_code;
		this.branch_name 	= branch_name;
		this.price_doctor	= price_doctor;
		this.doctor_id 		= doctor_id;
		this.doctor_name 	= doctor_name;
		this.tel_id 		= tel_id;
		this.tels_id 		= tels_id;
		
		this.addr_no			= addr_no;
		this.addr_bloc 			= addr_bloc;
		this.addr_village 		= addr_village;
		this.addr_alley 		= addr_alley;
		this.addr_road 			= addr_road;
		this.addr_provinceid 	= addr_provinceid;
		this.addr_aumphurid 	= addr_aumphurid;
		this.addr_districtid 	= addr_districtid;
		this.addr_zipcode 		= addr_zipcode;
	}

	//Reset Form
	public void ResetForm()
	{
		this.brand_id = 0;
		this.branch_id = "";
		this.branch_name = "";
		this.addr_id =  "";
		this.tels_id = "";
		this.tel_id = "";
		this.doctor_id = 0;
		this.price_doctor = 0;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = 
				brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getAddr_id() {
		return addr_id;
	}
	public void setAddr_id(String addr_id) {
		this.addr_id = addr_id;
	}
	 
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public int getPrice_doctor() {
		return price_doctor;
	}
	public void setPrice_doctor(int price_doctor) {
		this.price_doctor = price_doctor;
	}
	public String getAddr_no() {
		return addr_no;
	}
	public void setAddr_no(String addr_no) {
		this.addr_no = addr_no;
	}
	public String getAddr_bloc() {
		return addr_bloc;
	}
	public void setAddr_bloc(String addr_bloc) {
		this.addr_bloc = addr_bloc;
	}
	public String getAddr_village() {
		return addr_village;
	}
	public void setAddr_village(String addr_village) {
		this.addr_village = addr_village;
	}
	public String getAddr_alley() {
		return addr_alley;
	}
	public void setAddr_alley(String addr_alley) {
		this.addr_alley = addr_alley;
	}
	public String getAddr_road() {
		return addr_road;
	}
	public void setAddr_road(String addr_road) {
		this.addr_road = addr_road;
	}
	public String getAddr_provinceid() {
		return addr_provinceid;
	}
	public void setAddr_provinceid(String addr_provinceid) {
		this.addr_provinceid = addr_provinceid;
	}
	public String getAddr_aumphurid() {
		return addr_aumphurid;
	}
	public void setAddr_aumphurid(String addr_aumphurid) {
		this.addr_aumphurid = addr_aumphurid;
	}
	public String getAddr_districtid() {
		return addr_districtid;
	}
	public void setAddr_districtid(String addr_districtid) {
		this.addr_districtid = addr_districtid;
	}
	public String getAddr_zipcode() {
		return addr_zipcode;
	}
	public void setAddr_zipcode(String addr_zipcode) {
		this.addr_zipcode = addr_zipcode;
	}
	public String getAddr_typeid() {
		return addr_typeid;
	}
	public void setAddr_typeid(String addr_typeid) {
		this.addr_typeid = addr_typeid;
	}
	public String getS_brand_name() {
		return s_brand_name;
	}
	public void setS_brand_name(String s_brand_name) {
		this.s_brand_name = s_brand_name;
	}
	public String getS_branch_id() {
		return s_branch_id;
	}
	public void setS_branch_id(String s_branch_id) {
		this.s_branch_id = s_branch_id;
	}
	public String getS_branch_name() {
		return s_branch_name;
	}
	public void setS_branch_name(String s_branch_name) {
		this.s_branch_name = s_branch_name;
	}
	public String getS_docter_name() {
		return s_docter_name;
	}
	public void setS_docter_name(String s_docter_name) {
		this.s_docter_name = s_docter_name;
	}
	public String getTel_id() {
		return tel_id;
	}
	public void setTel_id(String tel_id) {
		this.tel_id = tel_id;
	}
	
	public String getTels_id() {
		return tels_id;
	}
	public void setTels_id(String tels_id) {
		this.tels_id = tels_id;
	}

	
	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public void setNext_number(int next_number) {
		this.next_number = next_number;
	}

	public int getNext_number() {
		return next_number;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTels() {
		return tels;
	}

	public void setTels(String tels) {
		this.tels = tels;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	//Get Set Form 
	
	
}