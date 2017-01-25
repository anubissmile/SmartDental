package com.smict.person.model;

public class AddressModel
{
	private int addr_pk,new_addr_id;
	private String[] addr_id;
	private String addr_no;
	private String addr_bloc;
	private String addr_village;
	private String addr_alley;
	private String addr_road;
	private String addr_provinceid,addr_province_name;
	private String addr_aumphurid,addr_aumphur_name;
	private String addr_districtid,addr_district_name;
	private String addr_zipcode,addr_typeid,addr_typename,addr_groupname,owners;
	private int addr_groupid;
	
		
	
	// Contructor
	public AddressModel()
	{
		super();
		// TODO Auto-generated constructor stub
	}
		
	
	
	
	//Getter & Setter



	public String getOwners() {
		return owners;
	}



	public void setOwners(String owners) {
		this.owners = owners;
	}



	public int getAddr_groupid() {
		return addr_groupid;
	}



	public void setAddr_groupid(int addr_groupid) {
		this.addr_groupid = addr_groupid;
	}



	public String getAddr_groupname() {
		return addr_groupname;
	}



	public void setAddr_groupname(String addr_groupname) {
		this.addr_groupname = addr_groupname;
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



	public String getAddr_typeid() {
		return addr_typeid;
	}



	public void setAddr_typeid(String addr_typeid) {
		this.addr_typeid = addr_typeid;
	}



	public String getAddr_typename() {
		return addr_typename;
	}



	public void setAddr_typename(String addr_typename) {
		this.addr_typename = addr_typename;
	}



	public String getAddr_no() 
	{
		return addr_no;
	}



	public void setAddr_no(String addr_no) 
	{
		this.addr_no = addr_no;
	}



	public String getAddr_bloc() 
	{
		return addr_bloc;
	}



	public void setAddr_bloc(String addr_bloc) 
	{
		this.addr_bloc = addr_bloc;
	}



	public String getAddr_village() 
	{
		return addr_village;
	}



	public void setAddr_village(String addr_village) 
	{
		this.addr_village = addr_village;
	}



	public String getAddr_alley() 
	{
		return addr_alley;
	}
	public void setAddr_alley(String addr_alley) 
	{
		this.addr_alley = addr_alley;
	}

	public String getAddr_road() 
	{
		return addr_road;
	}

	public void setAddr_road(String addr_road) 
	{
		this.addr_road = addr_road;
	}

	public String getAddr_zipcode() 
	{
		return addr_zipcode;
	}



	public void setAddr_zipcode(String addr_zipcode) 
	{
		this.addr_zipcode = addr_zipcode;
	}

	public int getAddr_pk() {
		return addr_pk;
	}



	public void setAddr_pk(int addr_pk) {
		this.addr_pk = addr_pk;
	}



	public int getNew_addr_id() {
		return new_addr_id;
	}



	public void setNew_addr_id(int new_addr_id) {
		this.new_addr_id = new_addr_id;
	}
	public String getAddr_province_name() {
		return addr_province_name;
	}



	public void setAddr_province_name(String addr_province_name) {
		this.addr_province_name = addr_province_name;
	}



	public String getAddr_aumphur_name() {
		return addr_aumphur_name;
	}



	public void setAddr_aumphur_name(String addr_aumphur_name) {
		this.addr_aumphur_name = addr_aumphur_name;
	}



	public String getAddr_district_name() {
		return addr_district_name;
	}



	public void setAddr_district_name(String addr_district_name) {
		this.addr_district_name = addr_district_name;
	}



	public String[] getAddr_id() {
		return addr_id;
	}



	public void setAddr_id(String[] addr_id) {
		this.addr_id = addr_id;
	}


}
