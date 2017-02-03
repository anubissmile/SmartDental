package com.smict.person.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.BranchData;
import com.smict.person.model.BranchModel;
import com.smict.treatment.action.TreatmentAction;

@SuppressWarnings("serial")
public class PatientBranchAction extends ActionSupport {
	BranchModel branchModel;
	ServicePatientModel servicePatModel;
    List<BranchModel> chunkBranch;

	
	public String execute() throws Exception{
		
		/**
		 * GET SESSION PATIENT MODEL.
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
	    TreatmentAction treatAction = new TreatmentAction();
	    treatAction.setToothList(request);
	    
	    /**
	     * CHUNKING BRANCH DATA.
	     */
	    BranchData branchData = new BranchData();
	    chunkBranch = branchData.chunkBranch();
//	    request.setAttribute("chunkBranch", chunkBranch);

		return SUCCESS;	
	}



	/**
	 * SETTER GETTER ZONE
	 */	
	
	public List<BranchModel> getChunkBranch() {
		return chunkBranch;
	}

	public void setChunkBranch(List<BranchModel> chunkBranch) {
		this.chunkBranch = chunkBranch;
	}
	
	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}

	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	}

	public BranchModel getBranchModel() {
		return branchModel;
	}



	public void setBranchModel(BranchModel branchModel) {
		this.branchModel = branchModel;
	}
}




















