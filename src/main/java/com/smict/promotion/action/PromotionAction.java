package com.smict.promotion.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.person.data.BranchData;
import com.smict.person.model.BranchModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.Person;
import com.smict.promotion.data.PromotionDetailData;
import com.smict.promotion.data.Promotion_sub_contactdata;
import com.smict.promotion.data.Promotiondata;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;
import com.smict.promotion.model.Promotion_sub_contactModel;

import ldc.util.Auth;
import ldc.util.DateUtil;
import ldc.util.Validate;


public class PromotionAction extends ActionSupport {
	private List<PromotionModel> promotionModel;
	private PromotionModel protionModel;
	private List<Promotion_sub_contactModel> promotionsubcontactModel;
	private List<BranchModel> branchmodel;
	private List<PromotionDetailModel> promotiondetailModel;
	private HashMap<String, String> pDetailMap;
	private PromotionDetailModel promotiondetailmodel;
	private List<PromotionModel> getpromotionlist,getgiftcardlist;
	private PromotionModel giftcardModel;
	private List<PatientModel> patientlist;
	private PromotionDetailModel proDetailModel;
	private List<PromotionModel> proBranchList,proSubcontactList,proDayList;
	private List<String> listBranchValue,listSubvalue;
	DateUtil dateUtil = new DateUtil();	
	/**
	 * CONSTRUCTOR
	 */
	public PromotionAction(){
		Auth.authCheck(false);
	}

	public String addPromotionInsert() throws IOException, Exception{
		
		  Promotiondata promoData = new Promotiondata();
		  if(!StringUtils.isEmpty(protionModel.getPro_amountbill())){
		  protionModel.setBillcostover(Double.parseDouble(protionModel.getPro_amountbill().replace(",", "")));
		  }
		  if(protionModel.getIs_allage().length() == 5){
			  protionModel.setIs_allage("1");
		  }
		  if(protionModel.getIs_birthmonth().length() == 5){
			  protionModel.setIs_birthmonth("0");
		  }
		  protionModel.setStart_date(dateUtil.convertDateSpecificationPattern("dd-MM-yyyy","yyyy-MM-dd",protionModel.getStart_date(),false));
		  protionModel.setEnd_date(dateUtil.convertDateSpecificationPattern("dd-MM-yyyy","yyyy-MM-dd",protionModel.getEnd_date(),false));			
		  /**
		   * insert header promotion
		   */
		  protionModel.setPromotion_id(promoData.addpromotioninsert(protionModel));
		  /**
		   * promotion branch
		   */
		  if(protionModel.getIs_allbranch().equals("0")){
			  promoData.addpromotionbranchinsert(protionModel);
		  }
		  /**
		   * promotion contact
		   */
		  if(protionModel.getIs_allsubcontact().equals("0")){
			  promoData.addpromotioncontactinsert(protionModel);
		  }
		  /**
		   * promotion day
		   */
		  if(protionModel.getIs_allday().equals("0")){
			  promoData.addpromotionDay(protionModel);
		  }
		  /**
		   * insert promotion manage
		   */
		  promoData.InsertORUpdatePromotionPoints(protionModel,1);
		  return SUCCESS;
		 }
	public void validateAddPromotionInsert(){
		Validate v = new Validate();
		if (!v.Check_String_notnull_notempty(protionModel.getName())){
			addFieldError("protionModel.name","please insert Promotion Name");
		}
		if (!v.Check_String_notnull_notempty(protionModel.getStart_date())){
			addFieldError("protionModel.start_date","please insert Start Date");
		}
		if (!v.Check_String_notnull_notempty(protionModel.getEnd_date())){
			addFieldError("protionModel.end_date","please insert End Date");
		}
	}
	public String addPromotion() throws IOException, Exception{
		
		Promotion_sub_contactdata prosubcontactData = new Promotion_sub_contactdata();
		setPromotionsubcontactModel(prosubcontactData.getListPromotion_sub_contact());
		BranchData branchdata = new BranchData();						
		setBranchmodel(branchdata.getListBranch());
		  return NONE;
		 }
	public String promotionManagement(){
		protionModel.getPromotion_id();		
		return SUCCESS;
	}
	public String promotionManagementPoints(){
		protionModel.getPromotion_id();
		Promotiondata promoData = new Promotiondata();
		setProtionModel(promoData.getManagePoints(protionModel.getPromotion_id()));		
		return SUCCESS;
	}
	public String addPromotionPoints(){
		Promotiondata promoData = new Promotiondata();
		if(protionModel.getPoints_type() == 1){
			protionModel.setPoints(Double.parseDouble(protionModel.getPoint().replace(",", "")));
		}else{
			protionModel.setPoints(0);
		}
		/**
		 * update promotion points
		 */
		promoData.InsertORUpdatePromotionPoints(protionModel,2);
		return SUCCESS;
	}
	public String promotionManagementdivideamount(){
		protionModel.getPromotion_id();
		Promotiondata promoData = new Promotiondata();
		setProtionModel(promoData.getManagePoints(protionModel.getPromotion_id()));		
		return SUCCESS;
	}
	public String addPromotiondivideamount(){
		Promotiondata promoData = new Promotiondata();
		if(protionModel.getType_cost() == 1){
			 if(!StringUtils.isEmpty(protionModel.getDocbaht())){
			protionModel.setDoctor_cost(Double.parseDouble(protionModel.getDocbaht().replace(",", "")));
			 }else{
				 protionModel.setDoctor_cost(0);
			 }
			 if(!StringUtils.isEmpty(protionModel.getCombaht())){
			protionModel.setCompany_cost(Double.parseDouble(protionModel.getCombaht().replace(",", "")));
			 }else{
				 protionModel.setCompany_cost(0);
			 }
		}else{
			 if(!StringUtils.isEmpty(protionModel.getDoctorCost())){
			protionModel.setDoctor_cost(Double.parseDouble(protionModel.getDoctorCost().replace(",", "")));
			 }else{
				 protionModel.setDoctor_cost(0);
			 }
			 if(!StringUtils.isEmpty(protionModel.getCompanyCost())){
			protionModel.setCompany_cost(Double.parseDouble(protionModel.getCompanyCost().replace(",", "")));
			 }else{
				 protionModel.setCompany_cost(0);
			 }
		}		
		/**
		 * update promotion points
		 */
		promoData.InsertORUpdatePromotionPoints(protionModel,3);
		return SUCCESS;
	}
	public String getpromotionlist(){

		Promotiondata promoData = new Promotiondata();
		setPromotionModel(promoData.getListPromotion());

		return NONE;
	}
	public String getPromotionEdit(){
		Promotiondata promoData = new Promotiondata();
		BranchData branchdata = new BranchData();	
		Promotion_sub_contactdata prosubcontactData = new Promotion_sub_contactdata();
		/**
		 * sub and branch
		 */
		setPromotionsubcontactModel(prosubcontactData.getListPromotion_sub_contact());							
		setBranchmodel(branchdata.getListBranch());
		/**
		 * promotion model
		 */
		setProtionModel(promoData.getPromotionEdit(protionModel.getPromotion_id()));

		if(protionModel.getIs_allsubcontact().equals("0")){
			setProSubcontactList(promoData.getPromotionsubcontactList(protionModel.getPromotion_id()));
			listSubvalue = new ArrayList<String>();
			for(PromotionModel promo : getProSubcontactList()){
				listSubvalue.add(promo.getSub_contactid());
			}		
		}
		if(protionModel.getIs_allbranch().equals("0")){
			setProBranchList(promoData.getPromotionBranchList(protionModel.getPromotion_id()));	
			listBranchValue = new ArrayList<String>();
			for(PromotionModel promo : getProBranchList()){
				listBranchValue.add(promo.getPro_branchID());
			}			
		}
		return SUCCESS;
	}
	public String UpdatePromotionByID() throws IOException, Exception{
		  Promotiondata promoData = new Promotiondata();
		  if(!StringUtils.isEmpty(protionModel.getPro_amountbill())){
		  protionModel.setBillcostover(Double.parseDouble(protionModel.getPro_amountbill().replace(",", "")));
		  }
		  if(StringUtils.isEmpty(protionModel.getIs_allage())){
			  protionModel.setIs_allage("1");
		  }
		  if(StringUtils.isEmpty(protionModel.getIs_birthmonth())){
			  protionModel.setIs_birthmonth("0");
		  }
		  protionModel.setStart_date(dateUtil.convertDateSpecificationPattern("dd-MM-yyyy","yyyy-MM-dd",protionModel.getStart_date(),false));
		  protionModel.setEnd_date(dateUtil.convertDateSpecificationPattern("dd-MM-yyyy","yyyy-MM-dd",protionModel.getEnd_date(),false));	
		  /**
		   * update promotion header
		   */
		  promoData.UpdatePromotionHeader(protionModel);
		  
		  /**
		   * promotion branch
		   */
		  if(protionModel.getIs_allbranch().equals("0")){
			  promoData.PromotionDeleteCondition(protionModel.getPromotion_id(),1,0,0);
			  promoData.addpromotionbranchinsert(protionModel);
		  }
		  /**
		   * promotion contact
		   */
		  if(protionModel.getIs_allsubcontact().equals("0")){
			  promoData.PromotionDeleteCondition(protionModel.getPromotion_id(),0,1,0);
			  promoData.addpromotioncontactinsert(protionModel);
		  }
		  /**
		   * promotion day
		   */
		  if(protionModel.getIs_allday().equals("0")){
			  promoData.PromotionDeleteCondition(protionModel.getPromotion_id(),0,0,1);
			  promoData.addpromotionDay(protionModel);
		  }		  
		
		return SUCCESS;
	}
	public String getPromotionDetailList(){
		PromotionDetailData proData = new PromotionDetailData();
		setPromotiondetailModel(proData.getListPromotionDetail(protionModel.getPromotion_id()));
		setProtionModel(proData.getNameDetail(protionModel.getPromotion_id()));		
		

	  return NONE;

	 }
	public String addPromotionDetailInsert() throws IOException, Exception{
		PromotionDetailData proData = new PromotionDetailData();
		if(proDetailModel.getDiscount_type() == 1){
			 if(!StringUtils.isEmpty(proDetailModel.getDis_amountbaht())){
				 proDetailModel.setDiscount_amount(Double.parseDouble(proDetailModel.getDis_amountbaht().replace(",", "")));
			 }else{
				 proDetailModel.setDiscount_amount(0);
			 }
		}else if(proDetailModel.getDiscount_type() == 2){
			if(!StringUtils.isEmpty(proDetailModel.getDis_amountpercent())){
				 proDetailModel.setDiscount_amount(Double.parseDouble(proDetailModel.getDis_amountpercent().replace(",", "")));
			 }else{
				 proDetailModel.setDiscount_amount(0);
			 }
		}else{
			proDetailModel.setDiscount_amount(0);
		}
		
		proData.addpromotiondetailinsert(proDetailModel);
		return SUCCESS;
	}
	public String PromotionDetailDel() throws IOException, Exception{
		PromotionDetailData proData = new PromotionDetailData();
		proData.PromotionDetailDelete(proDetailModel);
		
		return SUCCESS;
	}
	 public String PromotionDel() throws IOException, Exception{

		 Promotiondata promoData = new Promotiondata();
		 promoData.PromotionDelete(protionModel);
		  return SUCCESS;

		 }
	public String promotionStatusUpdate(){
		 Promotiondata promoData = new Promotiondata();
		 promoData.updatePromotionStatus(protionModel);
		return SUCCESS;
	}
	
	 public String getMemberlist(){
		 Promotiondata promoData = new Promotiondata();
		 setPromotionModel(promoData.getmemberlist());
		 return SUCCESS; 
	 }
	 public String addMember() throws IOException, Exception{
		 Promotiondata promoData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();
		 String total = request.getParameter("totalamount");
		 String defaultamount = request.getParameter("subcontactamount");
		 if(total!= ""){
			 protionModel.setTotal_amount(Double.parseDouble(total.replace(",", "")));
		 } 
		 if(defaultamount!=""){
			 protionModel.setSub_contact_amount(Double.parseDouble(defaultamount.replace(",", "")));
		 }
				 
		int keepgenkey = promoData.insertMember(protionModel);
		if(!StringUtils.isEmpty(protionModel.getSub_contact_type_id())){
		 if(protionModel.getSub_contact_type_id().equals("2")){
			 promoData.insertsubcontactWallet(keepgenkey,protionModel.getTotal_amount(),null);
		 }
		} 
		 return SUCCESS; 
	 }
	 public String updatestatusChange(){
		 Promotiondata promoData = new Promotiondata();
		 promoData.updateStatusSubcontact(protionModel);
		 return SUCCESS;
	 }
	 public String getEditMember(){
		 Promotiondata promoData = new Promotiondata();
		 setProtionModel(promoData.getMemberModel(Integer.parseInt(protionModel.getSub_contactid())
				 			,Integer.parseInt(protionModel.getSub_contact_type_id())));
		 return SUCCESS;
	 }
	 public String updateMember(){
		 Promotiondata promoData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();
		 String total = request.getParameter("totalamount");
		 String defaultamount = request.getParameter("subcontactamount");
		 if(total!= "" && total != null){
			 protionModel.setTotal_amount(Double.parseDouble(total.replace(",", "")));
		 } 
		 if(defaultamount!="" && total != null){
			 protionModel.setSub_contact_amount(Double.parseDouble(defaultamount.replace(",", "")));
		 }
		 
		 if(promoData.IsSameMembertype(protionModel.getSub_contactid())&&promoData.IsSameWallet(protionModel.getSub_contactid())){
			 promoData.updateSubcontact(protionModel);
			 if(protionModel.getSub_contact_type_id()!=null){
				 if(protionModel.getSub_contact_type_id().equals("2")){
					 promoData.insertsubcontactWallet(Integer.parseInt(protionModel.getSub_contactid()),protionModel.getTotal_amount(),null);
				 } 
			 }
			  
		 }else{			 
			 promoData.updateSubcontact(protionModel);
			 if(protionModel.getSub_contact_type_id()!=null){
				 if(protionModel.getSub_contact_type_id().equals("2")){
					 promoData.updateisStatusSubcontactWallet(protionModel.getSub_contactid(),"t");
				 }else{
					promoData.updateisStatusSubcontactWallet(protionModel.getSub_contactid(),"f");
				 }
			 }
		 }
		 
		 
		 return SUCCESS;
	 }
	 public String getcompanyMember(){
		 Promotiondata promoData = new Promotiondata();
		 setProtionModel(promoData.getMemberModel(Integer.parseInt(protionModel.getSub_contactid())
				 			,Integer.parseInt(protionModel.getSub_contact_type_id())));
		 if(protionModel.getSub_contact_type_id().equals("2")){
			setGetpromotionlist(promoData.getSubcontactwalletLinelist(protionModel.getSub_contact_walletid()));
			return SUCCESS; 
		 }else if(protionModel.getSub_contact_type_id().equals("3")){
			 
			 return NONE; 
		 }else{
			 
			 return INPUT;
		 }
	 }
	 public String adjustmoneyCompany(){
		 protionModel.getSub_contactid();
		 protionModel.getSub_contact_type_id();
		 Promotiondata promoData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();		
		 String totalall = request.getParameter("totalamountall");
		 String adjustamount = request.getParameter("adjustamount");
		 String checkAddOrDel = request.getParameter("checktypeis");
		 if(totalall!= "" && totalall != null){
			 protionModel.setTotal_amount(Double.parseDouble(totalall.replace(",", "")));
		 } 		 
		 if(adjustamount!= "" && adjustamount != null){
			 protionModel.setAmount(Double.parseDouble(adjustamount.replace(",", "")));		 
		 }
		 double endamount = 0;
		 if(checkAddOrDel.equals("1")){			 
			 promoData.insertSubcontactWalletline(protionModel.getSub_contact_walletid(),protionModel.getAmount(),2);
			 endamount =  protionModel.getAmount() + protionModel.getTotal_amount();
		 }else{
			 promoData.insertSubcontactWalletline(protionModel.getSub_contact_walletid(),protionModel.getAmount(),3);
			 endamount =  protionModel.getTotal_amount() - protionModel.getAmount(); 
		 }		  
		 promoData.AdjustSubcontactWallet(protionModel.getSub_contact_walletid(),endamount);
		 
		 
		 
		 return SUCCESS;
	 }
	 public String updatedefaultmoneyCompany(){
		 protionModel.getSub_contactid();
		 protionModel.getSub_contact_type_id();
		 Promotiondata promoData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();		
		 String totalall = request.getParameter("totalamountall");
		 if(totalall!= "" && totalall != null){
			 protionModel.setSub_contact_amount(Double.parseDouble(totalall.replace(",", "")));
			 promoData.updateDefaultmoneySubcontact(protionModel);
		 }  
		 
		 
		 return SUCCESS;
	 }
	 public String getGiftCardlist(){
		 Promotiondata giftData = new Promotiondata();
		 setGetgiftcardlist(giftData.getGiftCardList(0));
		 return SUCCESS;
	 }
	 public String getGiftCardline(){
		 Promotiondata giftData = new Promotiondata();
		 setGetgiftcardlist(giftData.getGiftCardList(giftcardModel.getGiftcard_id()));
		 return SUCCESS;
	 }
	 public String addGiftCard(){
		 Promotiondata giftData = new Promotiondata();
		 HttpServletRequest request =  ServletActionContext.getRequest();
		 String defaultamount = request.getParameter("giftcarddefaultamount");
		 if(defaultamount!= "" && defaultamount != null){
			 giftcardModel.setGiftcard_default_amount(Double.parseDouble(defaultamount.replace(",", "")));
		 } 
			String startdate_eg = request.getParameter("startdate_eg");
			String startdate_th = request.getParameter("startdate_th");
			String startdate="";
			
			if(!startdate_eg.equals("")){
				String[] parts = startdate_eg.split("-");
				startdate = parts[2]+"-"+parts[1]+"-"+parts[0];
			}else if(!startdate_th.equals("")){
				String[] parts = startdate_th.split("-");
				int convertDate =  Integer.parseInt(parts[2]);
				convertDate -= 543;
				startdate = convertDate+"-"+parts[1]+"-"+parts[0];
			}
			giftcardModel.setGiftcard_start_date(startdate);
			String expiredate_eg = request.getParameter("expiredate_eg");
			String expiredate_th = request.getParameter("expiredate_th");
			String expiredate="";
			
			if(!expiredate_eg.equals("")){
				String[] parts = expiredate_eg.split("-");
				expiredate = parts[2]+"-"+parts[1]+"-"+parts[0];
			}else if(!expiredate_th.equals("")){
				String[] parts = expiredate_th.split("-");
				int convertDate =  Integer.parseInt(parts[2]);
				convertDate -= 543;
				expiredate = convertDate+"-"+parts[1]+"-"+parts[0];
			}
			giftcardModel.setGiftcard_expiredate(expiredate);		 
			giftcardModel.setGiftcard_id(giftData.addgiftcard(giftcardModel));	
			if(giftcardModel.getGiftcard_id()!= 0){
				 
				int i = 0;
				String number = Integer.toString(giftcardModel.getGiftcard_start_number());
				String allnum = null;
				for(int k = 0;k<giftcardModel.getGiftcard_run_count();k++){
						if(i>0){	
							number = String.valueOf(Integer.parseInt(number)+1);
						}
					int num = number.length();
						
					for(;num<giftcardModel.getGiftcard_numberlenght();num++){
						number = "0"+number;
					}
						if(i>0){
							allnum += ","+number;
						}else{
							allnum = number;
						}
										
					i++;
				}
					giftData.addgiftcardline(giftcardModel,allnum);
			}
		 return SUCCESS;
	 }
	public String changeStatusGiftCard(){
		Promotiondata giftData = new Promotiondata();
		giftData.changeStatusgift(giftcardModel);
		return SUCCESS;
	}
	public String delGiftCard(){
		Promotiondata giftData = new Promotiondata();
		giftData.deletegiftcardANDline(giftcardModel);
		return SUCCESS;
	}
	public String updateGiftCard(){
		Promotiondata giftData = new Promotiondata();
		giftData.updategift(giftcardModel);
		return SUCCESS;
	}
	public String getgiftlinewithpatient(){
		Promotiondata giftData = new Promotiondata();
		giftcardModel.getGiftcard_id();
		setGetgiftcardlist(giftData.getGiftCardlineWithpatientList(giftcardModel.getGiftcard_line_id()));
		setPatientlist(giftData.getpatientList());
		return SUCCESS;
	}
	public String addgiftlinewithpatient(){
		Promotiondata giftData = new Promotiondata();
		giftcardModel.getGiftcard_id();
		giftcardModel.getGiftcard_line_id();
		giftData.addgiftcardwithpatient(giftcardModel);
		
		return SUCCESS;
		
	}
	public String delGiftwithpatient(){
		Promotiondata giftData = new Promotiondata();
		giftcardModel.getGiftcard_id();
		giftcardModel.getGiftcard_line_id();
		giftData.deletegiftcardwithpatient(giftcardModel);
		return SUCCESS;
	}
	
	public PromotionModel getProtionModel() {
		return protionModel;
	}

	public void setProtionModel(PromotionModel protionModel) {
		this.protionModel = protionModel;
	}
	public List<PromotionModel> getPromotionModel() {
		return promotionModel;
	}
	public void setPromotionModel(List<PromotionModel> promotionModel) {
		this.promotionModel = promotionModel;
	}
	public List<Promotion_sub_contactModel> getPromotionsubcontactModel() {
		return promotionsubcontactModel;
	}
	public void setPromotionsubcontactModel(List<Promotion_sub_contactModel> promotionsubcontactModel) {
		this.promotionsubcontactModel = promotionsubcontactModel;
	}
	public List<BranchModel> getBranchmodel() {
		return branchmodel;
	}
	public void setBranchmodel(List<BranchModel> branchmodel) {
		this.branchmodel = branchmodel;
	}
	public List<PromotionDetailModel> getPromotiondetailModel() {
		return promotiondetailModel;
	}
	public void setPromotiondetailModel(List<PromotionDetailModel> promotiondetailModel) {
		this.promotiondetailModel = promotiondetailModel;
	}
	public PromotionDetailModel getPromotiondetailmodel() {
		return promotiondetailmodel;
	}
	public void setPromotiondetailmodel(PromotionDetailModel promotiondetailmodel) {
		this.promotiondetailmodel = promotiondetailmodel;
	}
	public HashMap<String, String> getpDetailMap() {
		return pDetailMap;
	}
	public void setpDetailMap(HashMap<String, String> pDetailMap) {
		this.pDetailMap = pDetailMap;
	}


	public List<PromotionModel> getGetpromotionlist() {
		return getpromotionlist;
	}


	public void setGetpromotionlist(List<PromotionModel> getpromotionlist) {
		this.getpromotionlist = getpromotionlist;
	}


	public PromotionModel getGiftcardModel() {
		return giftcardModel;
	}


	public void setGiftcardModel(PromotionModel giftcardModel) {
		this.giftcardModel = giftcardModel;
	}


	public List<PromotionModel> getGetgiftcardlist() {
		return getgiftcardlist;
	}


	public void setGetgiftcardlist(List<PromotionModel> getgiftcardlist) {
		this.getgiftcardlist = getgiftcardlist;
	}


	public List<PatientModel> getPatientlist() {
		return patientlist;
	}


	public void setPatientlist(List<PatientModel> patientlist) {
		this.patientlist = patientlist;
	}

	public PromotionDetailModel getProDetailModel() {
		return proDetailModel;
	}

	public void setProDetailModel(PromotionDetailModel proDetailModel) {
		this.proDetailModel = proDetailModel;
	}

	public List<PromotionModel> getProBranchList() {
		return proBranchList;
	}

	public List<PromotionModel> getProSubcontactList() {
		return proSubcontactList;
	}

	public List<PromotionModel> getProDayList() {
		return proDayList;
	}

	public void setProBranchList(List<PromotionModel> proBranchList) {
		this.proBranchList = proBranchList;
	}

	public void setProSubcontactList(List<PromotionModel> proSubcontactList) {
		this.proSubcontactList = proSubcontactList;
	}

	public void setProDayList(List<PromotionModel> proDayList) {
		this.proDayList = proDayList;
	}

	public List<String> getListBranchValue() {
		return listBranchValue;
	}

	public List<String> getListSubvalue() {
		return listSubvalue;
	}

	public void setListBranchValue(List<String> listBranchValue) {
		this.listBranchValue = listBranchValue;
	}

	public void setListSubvalue(List<String> listSubvalue) {
		this.listSubvalue = listSubvalue;
	}





}

