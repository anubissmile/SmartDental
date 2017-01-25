package com.smict.person.model;

public class CongenitalDiseaseModel {
	int congenital_id, pat_congenital_disease_id;
	
	String congenital_name_th, congenital_name_en;
	
	
	//
	public CongenitalDiseaseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CongenitalDiseaseModel(int congenital_id, int pat_congenital_disease_id, String congenital_name_th,
			String congenital_name_en) {
		super();
		this.congenital_id = congenital_id;
		this.pat_congenital_disease_id = pat_congenital_disease_id;
		this.congenital_name_th = congenital_name_th;
		this.congenital_name_en = congenital_name_en;
	}

	//
	public void resetConginentalDisease(){
		this.pat_congenital_disease_id = 0;
		this.congenital_id = 0;
		this.congenital_name_th = "";
		this.congenital_name_en = "";
	}
	public int getPat_congenital_disease_id() {
		return pat_congenital_disease_id;
	}

	public void setPat_congenital_disease_id(int pat_congenital_disease_id) {
		this.pat_congenital_disease_id = pat_congenital_disease_id;
	}
	public int getCongenital_id() {
		return congenital_id;
	}
	
	public void setCongenital_id(int congenital_id) {
		this.congenital_id = congenital_id;
	}
	public String getCongenital_name_th() {
		return congenital_name_th;
	}
	public void setCongenital_name_th(String congenital_name_th) {
		this.congenital_name_th = congenital_name_th;
	}
	public String getCongenital_name_en() {
		return congenital_name_en;
	}
	public void setCongenital_name_en(String congenital_name_en) {
		this.congenital_name_en = congenital_name_en;
	}
	
	
}
