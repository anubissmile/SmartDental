package com.smict.finance.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ContypeModel;
import com.smict.all.model.FinanceModel;
import com.smict.all.model.ServicePatientModel;
import com.smict.df.DFDB;
import com.smict.finance.data.FinanceData;
import com.smict.person.data.PatContypeData;
import com.smict.person.data.PatientData;
import com.smict.promotion.data.PromotionDetailData;
import com.smict.promotion.model.PromotionDetailModel;
import com.smict.promotion.model.PromotionModel;
import com.smict.treatment.data.TreatmentData;
import com.smict.treatment.model.TreatmentModel;

import ldc.util.Auth;
import ldc.util.DateUtil;
 
 
public class FinanceAction extends ActionSupport{
	FinanceModel financeModel;
	ServicePatientModel servicePatModel; 
	String alertStatus, alertMessage; 
	private TreatmentModel treatmentModel;
	private List<TreatmentModel> treatmentlist,treatmentlinelist,listtreatpatmedicine;
	private FinanceModel finanModel;
	private List<FinanceModel> orderlist,orderlinelist;
	private List<PromotionDetailModel> prodetailList;
	/**
	 * CONSTRUCTOR
	 */
	public FinanceAction(){
		Auth.authCheck(false);
	}

	
	public void ajax_json_product() {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		FinanceData financeData = new FinanceData();
		JSONArray jsonResponse = new JSONArray();
		
		String proID = "";  
		String protype = "";
		String hn = "";
		if(request.getParameter("proID") != null) proID = request.getParameter("proID").toString();
		if(request.getParameter("protype") != null) protype = request.getParameter("protype").toString();
		if(request.getParameter("hn") != null) hn = request.getParameter("hn").toString();
		jsonResponse = financeData.getJsonArrayListProduct(hn,proID,protype);  	
		  
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
		try { 
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
			/* treatmentModel.getTreatment_patient_ID();*/
		FinanceData financeData = new FinanceData();
		TreatmentData treatData = new TreatmentData();
		PatContypeData patContypeData = new PatContypeData();
		PromotionDetailData prode = new PromotionDetailData();
		if(treatmentModel!=null){
			
			/**
			 * select treatment patient for insert order 
			 */
			setFinanModel(financeData.getTreatmentPatientForFinance(treatmentModel.getTreatment_patient_ID()));
			/**
			 * select treatment patient Line for insert order Line  
			 */
			setOrderlinelist(financeData.getTreatmentLineForFinance(treatmentModel.getTreatment_patient_ID()));
			/**
			 * select medicine
			 */			
			setListtreatpatmedicine(treatData.getTreatmentpatMedicine(treatmentModel.getTreatment_patient_ID()));
			/**
			 * select contype list
			 */
			finanModel.setContypeList(patContypeData.getListContype(finanModel.getOrder_Hn(),1));
			if(finanModel.getContypeList()!=null){
				/**
				 * select promotion
				 */
				String conty = null;
				int i = 0;
				for(ContypeModel conmodel  : finanModel.getContypeList()){
					if(i>1){
						conty += ","+Integer.toString(conmodel.getSub_contact_id());
					}else{
						conty = Integer.toString(conmodel.getSub_contact_id());
					}					
					i++;
				}
				/**
				 * select promotion
				 */
				finanModel.setPromoList(financeData.getPromotion(finanModel.getOrder_Hn(),conty));
				/**
				 * fine best promotion
				 */	
/*				int k = 0;				
				int allpro[] = new int[50];
				double allamount [] = new double[50];
				double sumall [] = new double[50];				
				if(finanModel.getPromoList() != null){
				for(PromotionModel prmodel  : finanModel.getPromoList()){					
					boolean check = false;
					boolean check1 = false;
					double sumamount = 0;
					double sumalltreat = 0;
					*//**
					 * select promotion line
					 *//*					
					setProdetailList(prode.getListPromotionDetail(prmodel.getPromotion_id()));
									
					for(FinanceModel finmodel  : getOrderlinelist()){						
						double amount = 0;
						int amounttype=0;
						finmodel.getOrderLine_TreatID();
						finmodel.getOrderLine_groupID();
						finmodel.getOrderLine_catID();
						finmodel.getOrderLine_price();
						sumalltreat += finmodel.getOrderLine_price();
						for(PromotionDetailModel pdmodel  : getProdetailList()){
							if(pdmodel.getProduct_type() == 4){
								amount = pdmodel.getDiscount_amount();
								amounttype= pdmodel.getDiscount_type();
								check = true;
								break;
							}else if(pdmodel.getProduct_type() == 5 && finmodel.getOrderLine_groupID() == pdmodel.getProduct_id() ){
								amount = pdmodel.getDiscount_amount();
								amounttype= pdmodel.getDiscount_type();
								check = true;
								break;
							}else if(pdmodel.getProduct_type() == 6 && finmodel.getOrderLine_catID() == pdmodel.getProduct_id()){
								amount = pdmodel.getDiscount_amount();
								amounttype= pdmodel.getDiscount_type();
								check = true;
								break;
							}else if(pdmodel.getProduct_type() == 7 && finmodel.getOrderLine_TreatID() == pdmodel.getProduct_id()){
								amount = pdmodel.getDiscount_amount();
								amounttype= pdmodel.getDiscount_type();
								check = true;
								break;
							}							
						}
						if(check){
							if(amounttype == 1){
								sumamount += finmodel.getOrderLine_price() - amount;
							}else if(amounttype == 2){
								double per = (finmodel.getOrderLine_price() * amount)/100;
								sumamount += finmodel.getOrderLine_price() - per;
							}
							check = false;
							check1 = true;
						}
					}
					for(TreatmentModel medmodel  : getListtreatpatmedicine()){
						
						if(!medmodel.getIsCheck().equals("nu")){
							sumalltreat += ((medmodel.getTreatPatMedicine_amount() - medmodel.getTreatPatMedicine_amountfree()) * medmodel.getPro_price());
							double amount = 0;
							int amounttype=0;
							double summede = 0;
							boolean check2 = false;
							for(PromotionDetailModel pdmodel  : getProdetailList()){
								 if(pdmodel.getProduct_type() == 1 && Integer.parseInt(medmodel.getTreatPatMedicine_ProID()) == pdmodel.getProduct_id() ){
									amount = pdmodel.getDiscount_amount();
									amounttype= pdmodel.getDiscount_type();
									check2 = true;
									break;
								}							
							}
							if(check2){
								if(amounttype == 1){
									if( medmodel.getPro_price() < amount){
										summede = 0;
									}else{
										summede = medmodel.getPro_price() - amount;
									}									
									sumamount +=  ((medmodel.getTreatPatMedicine_amount() - medmodel.getTreatPatMedicine_amountfree()) * summede );
								}else if(amounttype == 2){
									double per = (medmodel.getPro_price() * amount)/100;
									summede = medmodel.getPro_price() - per;
									sumamount +=  ((medmodel.getTreatPatMedicine_amount() - medmodel.getTreatPatMedicine_amountfree()) * summede );
								}
								check2 = false;
								check1 = true;
							}
						}
					}
					if(check1){
						
						allamount[k]= sumamount;
						allpro[k] =prmodel.getPromotion_id();
						k++;
					}					
					allamount[k]= sumamount;
					allpro[k] =prmodel.getPromotion_id();
					sumall[k] = sumalltreat;
					k++;
				}
			}
				int p =0;
				int lastPro = 0;
				double lastamount = 0;
				double sumalltreat = 0;
				if(allpro != null){
					for(int id : allpro){
						double checkamount = allamount[p];
						if(p==0 ){
							lastamount = checkamount;
							lastPro = id;
							sumalltreat = sumall[p];
						}else{
							if(lastamount > checkamount && id != 0){
								lastamount = checkamount;
								lastPro = id;
								sumalltreat = sumall[p];
							}
						}
						
						p++;
						if(id == 0){
							break;
						}
					}
				}
				finanModel.setSumallamount(sumalltreat);
				finanModel.setSumalldis(sumalltreat - lastamount);
				finanModel.setSumallwithdis(lastamount);
				finanModel.setLastPromotionID(lastPro);
				setProdetailList(prode.getListPromotionDetail(lastPro));
				
				*/
			}
			
		}else{
			if(session.getAttribute("ServicePatientModel")!=null){
				TreatmentData treatmentdb = new TreatmentData(); 
				String hn			= servicePatModel.getHn();
				int treatment_id 	= treatmentdb.Select_Treatment_ID(hn);
				
				/*List transectionTreatmentList = treatmentdb.transectionTreatment(hn, treatment_id);
				request.setAttribute("transectionTreatmentList", transectionTreatmentList); 
				*/
				List drugList = financeData.getDrug(treatment_id);
				request.setAttribute("drugList", drugList); 
				
				List productList = financeData.getProduct(treatment_id);
				request.setAttribute("productList", productList);
			}else{
				alertStatus = "danger";
				alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
				return "getCustomer"; 
			} 
		}
		
		
		return SUCCESS;
	}
	public String print() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		if(session.getAttribute("ServicePatientModel")!=null){
			DateUtil dateUtil = new DateUtil();
			FinanceData financeData = new FinanceData();
			String hn			= servicePatModel.getHn();
			TreatmentData treatmentdb = new TreatmentData(); 
			int treatment_id 	= treatmentdb.Select_Treatment_ID(hn);
			String report_no = "";
			
			String year = dateUtil.curYear();
			boolean checkReportNo = financeData.checkReportNo(year, treatment_id);
			if(checkReportNo==false){
				report_no = financeData.getReportNo_Running(year);
				financeData.insertReportNo(year, treatment_id, report_no);
			}else{
				report_no = financeData.getReportNo(year, treatment_id);
			}
			financeData.updateTreatmentidfornull(hn);
			servicePatModel = new ServicePatientModel(new PatientData().getPatModel_patient(servicePatModel));
			servicePatModel.setStatus(new PatientData().getPatStatus(hn));  // working teartment button 
			session.setAttribute("ServicePatientModel", servicePatModel);
			 
			request.setAttribute("hn", hn);
			request.setAttribute("treatment_id", treatment_id);
			request.setAttribute("report_no", "SN"+dateUtil.curTHYear().substring(2, 4)+"/"+report_no);
			request.setAttribute("date", dateUtil.curDateTH().replace("-", "/"));
			request.setAttribute("time", dateUtil.curTime()+" น.");
			request.setAttribute("amounttotal", request.getParameter("amounttotal").toString());
			request.setAttribute("discount", request.getParameter("discount").toString());
			request.setAttribute("net", request.getParameter("net").toString());
			request.setAttribute("owe", request.getParameter("owe").toString());
		}else{
			alertStatus = "danger";
			alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
			return "getCustomer"; 
		} 
		return SUCCESS;
	}
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		 
		  
		 
		return SUCCESS;
	}

	
	
	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}
	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	} 
	public String getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}
	public String getAlertMessage() {
		return alertMessage;
	}
	public void setAlertMessage(String alertMessage) {
		this.alertMessage = alertMessage;
	}
	public FinanceModel getFinanceModel() {
		return financeModel;
	}
	public void setFinanceModel(FinanceModel financeModel) {
		this.financeModel = financeModel;
	}

	public TreatmentModel getTreatmentModel() {
		return treatmentModel;
	}

	public void setTreatmentModel(TreatmentModel treatmentModel) {
		this.treatmentModel = treatmentModel;
	}

	public List<TreatmentModel> getTreatmentlist() {
		return treatmentlist;
	}

	public void setTreatmentlist(List<TreatmentModel> treatmentlist) {
		this.treatmentlist = treatmentlist;
	}

	public List<TreatmentModel> getTreatmentlinelist() {
		return treatmentlinelist;
	}

	public void setTreatmentlinelist(List<TreatmentModel> treatmentlinelist) {
		this.treatmentlinelist = treatmentlinelist;
	}

	public FinanceModel getFinanModel() {
		return finanModel;
	}

	public void setFinanModel(FinanceModel finanModel) {
		this.finanModel = finanModel;
	}

	public List<FinanceModel> getOrderlist() {
		return orderlist;
	}

	public List<FinanceModel> getOrderlinelist() {
		return orderlinelist;
	}

	public void setOrderlist(List<FinanceModel> orderlist) {
		this.orderlist = orderlist;
	}

	public void setOrderlinelist(List<FinanceModel> orderlinelist) {
		this.orderlinelist = orderlinelist;
	}

	public List<TreatmentModel> getListtreatpatmedicine() {
		return listtreatpatmedicine;
	}

	public void setListtreatpatmedicine(List<TreatmentModel> listtreatpatmedicine) {
		this.listtreatpatmedicine = listtreatpatmedicine;
	}

	public List<PromotionDetailModel> getProdetailList() {
		return prodetailList;
	}

	public void setProdetailList(List<PromotionDetailModel> prodetailList) {
		this.prodetailList = prodetailList;
	}


	
}
