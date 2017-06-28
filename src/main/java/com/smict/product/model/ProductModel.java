package com.smict.product.model;

public class ProductModel {
	String product_name, product_name_en,
	productgroup_id, productgroup_name, producttype_Id, producttype_name,
	productbrand_id, productbrand_name, productunit_id, productunit_name,beallergic_name_th,beallergic_name_en,product_isCheck;
	int product_id;
	double price;
	String product_phase_id,product_phase_name,product_phase_treatid,product_phase_proid;
	double product_phase_amount,product_phase_amountfree;
	private int[] product_id_arr, product_name_arr;
	private String[] str_product_id_arr;
	
	private int[] product_volumn, product_volumn_free;

	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_name_en() {
		return product_name_en;
	}
	public void setProduct_name_en(String product_name_en) {
		this.product_name_en = product_name_en;
	}
	public String getProductgroup_id() {
		return productgroup_id;
	}
	public void setProductgroup_id(String productgroup_id) {
		this.productgroup_id = productgroup_id;
	}
	public String getProductgroup_name() {
		return productgroup_name;
	}
	public void setProductgroup_name(String productgroup_name) {
		this.productgroup_name = productgroup_name;
	}
	public String getProducttype_Id() {
		return producttype_Id;
	}
	public void setProducttype_Id(String producttype_Id) {
		this.producttype_Id = producttype_Id;
	}
	public String getProducttype_name() {
		return producttype_name;
	}
	public void setProducttype_name(String producttype_name) {
		this.producttype_name = producttype_name;
	}
	public String getProductbrand_id() {
		return productbrand_id;
	}
	public void setProductbrand_id(String productbrand_id) {
		this.productbrand_id = productbrand_id;
	}
	public String getProductbrand_name() {
		return productbrand_name;
	}
	public void setProductbrand_name(String productbrand_name) {
		this.productbrand_name = productbrand_name;
	}
	public String getProductunit_id() {
		return productunit_id;
	}
	public void setProductunit_id(String productunit_id) {
		this.productunit_id = productunit_id;
	}
	public String getProductunit_name() {
		return productunit_name;
	}
	public void setProductunit_name(String productunit_name) {
		this.productunit_name = productunit_name;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getBeallergic_name_th() {
		return beallergic_name_th;
	}
	public String getBeallergic_name_en() {
		return beallergic_name_en;
	}
	public void setBeallergic_name_th(String beallergic_name_th) {
		this.beallergic_name_th = beallergic_name_th;
	}
	public void setBeallergic_name_en(String beallergic_name_en) {
		this.beallergic_name_en = beallergic_name_en;
	}
	public String getProduct_isCheck() {
		return product_isCheck;
	}
	public void setProduct_isCheck(String product_isCheck) {
		this.product_isCheck = product_isCheck;
	}
	public int[] getProduct_id_arr() {
		return product_id_arr;
	}
	public void setProduct_id_arr(int[] product_id_arr) {
		this.product_id_arr = product_id_arr;
	}
	public int[] getProduct_name_arr() {
		return product_name_arr;
	}
	public void setProduct_name_arr(int[] product_name_arr) {
		this.product_name_arr = product_name_arr;
	}
	public int[] getProduct_volumn() {
		return product_volumn;
	}
	public void setProduct_volumn(int[] product_volumn) {
		this.product_volumn = product_volumn;
	}
	public int[] getProduct_volumn_free() {
		return product_volumn_free;
	}
	public void setProduct_volumn_free(int[] product_volumn_free) {
		this.product_volumn_free = product_volumn_free;
	}
	public String[] getStr_product_id_arr() {
		return str_product_id_arr;
	}
	public void setStr_product_id_arr(String[] str_product_id_arr) {
		this.str_product_id_arr = str_product_id_arr;
	}
	public String getProduct_phase_id() {
		return product_phase_id;
	}
	public void setProduct_phase_id(String product_phase_id) {
		this.product_phase_id = product_phase_id;
	}
	public String getProduct_phase_name() {
		return product_phase_name;
	}
	public void setProduct_phase_name(String product_phase_name) {
		this.product_phase_name = product_phase_name;
	}
	public String getProduct_phase_treatid() {
		return product_phase_treatid;
	}
	public void setProduct_phase_treatid(String product_phase_treatid) {
		this.product_phase_treatid = product_phase_treatid;
	}
	public double getProduct_phase_amount() {
		return product_phase_amount;
	}
	public void setProduct_phase_amount(double product_phase_amount) {
		this.product_phase_amount = product_phase_amount;
	}
	public double getProduct_phase_amountfree() {
		return product_phase_amountfree;
	}
	public void setProduct_phase_amountfree(double product_phase_amountfree) {
		this.product_phase_amountfree = product_phase_amountfree;
	}
	public String getProduct_phase_proid() {
		return product_phase_proid;
	}
	public void setProduct_phase_proid(String product_phase_proid) {
		this.product_phase_proid = product_phase_proid;
	}

	
}
