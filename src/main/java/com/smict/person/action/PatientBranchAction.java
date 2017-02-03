package com.smict.person.action;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.model.BranchModel;

public class PatientBranchAction extends ActionSupport {
	BranchModel BranchModel;
	
	public String execute(){
		return SUCCESS;
	}
}