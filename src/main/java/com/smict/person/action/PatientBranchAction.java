package com.smict.person.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.BranchData;
import com.smict.person.model.BranchModel;
import com.smict.treatment.action.TreatmentAction;

import ldc.util.Auth;

@SuppressWarnings("serial")
public class PatientBranchAction extends ActionSupport {
	private BranchModel branchModel, bm;
	private ServicePatientModel servicePatModel;
    private HashMap<String, String> chunkBranch = new HashMap<String, String>();
    private BranchData branchData = new BranchData();
    
    /**
     * CONSTRUCTOR.
     */
    public PatientBranchAction(){
    	Auth.authCheck(false);
    }

	
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
	    chunkBranch = branchData.chunkBranch();
	    
	    /**
	     * CHUNKING PATIENT'S BRANCH
	     */
	    

		return SUCCESS;	
	}


	/**
	 * SETTER GETTER ZONE
	 */	
	
	public HashMap<String, String> getChunkBranch() {
		return chunkBranch;
	}

	public void setChunkBranch(HashMap<String, String> chunkBranch) {
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



	public BranchModel getBm() {
		return bm;
	}



	public void setBm(BranchModel bm) {
		this.bm = bm;
	}
}




















