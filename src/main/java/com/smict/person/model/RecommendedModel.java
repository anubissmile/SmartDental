package com.smict.person.model;

public class RecommendedModel {
	private String typerecommended_name;
	private int typerecommended;
	
	public RecommendedModel(){
		
	}
	public RecommendedModel(int typerecommended,String typerecommended_name){
		this.typerecommended = typerecommended;
		this.typerecommended_name = typerecommended_name;
	}


	public int getTyperecommended() {
		return typerecommended;
	}
	public void setTyperecommended(int typerecommended) {
		this.typerecommended = typerecommended;
	}
	public String getTyperecommended_name() {
		return typerecommended_name;
	}

	public void setTyperecommended_name(String typerecommended_name) {
		this.typerecommended_name = typerecommended_name;
	}
}
