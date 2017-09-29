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

import com.google.gson.JsonObject;
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
	private List<FinanceModel> orderlist,orderlinelist,conList;
	private List<PromotionDetailModel> prodetailList;
	/**
	 * fine the best promotion
	 */
	double sumamount = 0;
	double dissum = 0;
	double sumall = 0;
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
	public void ajax_json_calcuall() {

		try {
		JSONObject allpromotion = new JSONObject("{\"dissum\":0,\"index\":0}");
		JSONArray findPromotion = new JSONArray();
		HttpServletRequest request = ServletActionContext.getRequest();	
		FinanceData financeData = new FinanceData();
		JSONArray treatment = new JSONArray();
		JSONArray medicine = new JSONArray();
		JSONArray product = new JSONArray();
		JSONArray promotion = new JSONArray();
		JSONArray contype = new JSONArray();
		JSONArray freeproduct = new JSONArray();
		PromotionDetailData prode = new PromotionDetailData();
		String obj = ""; 
		if(request.getParameter("productobj") != null){
			obj = request.getParameter("productobj").toString();
		}

			JSONObject newObj = new JSONObject(obj);
			treatment = newObj.getJSONArray("treatment");
			medicine = newObj.getJSONArray("medicine");
			product = newObj.getJSONArray("product");
			/*freeproduct = newObj.getJSONArray("freeproduct");
			contype = newObj.getJSONArray("contype");*/
			promotion = newObj.getJSONArray("promotion");
			if(newObj.getInt("chang_promotion") == 0){
				if( promotion.length() != 0){
					for(int i = 0 ;i<promotion.length();i++){
						JSONObject promotionbj = promotion.getJSONObject(i);
						sumamount =0;
						dissum = 0;
						sumall = 0;
						String promotionID = promotionbj.get("promotionID").toString();
	
						/**
						 * select promotion detail
						 */
						setProdetailList(prode.getListPromotionDetail(Integer.parseInt(promotionID)));	
						/**
						 * treatment
						 */
						treatment = findTheBestPromotionFromTreatment(treatment);
						medicine  =	findtheBestPromotionFromMedicine(medicine);
						product   = findtheBestPromotionFromProduct(product);
						if(Double.parseDouble(allpromotion.getString("dissum")) < dissum ){
							allpromotion.put("dissum",dissum);
							allpromotion.put("proID",promotionID);
							allpromotion.put("index",i);						
						}
						promotionbj.put("sumamount", sumall - dissum);
						promotionbj.put("sumdiscount", dissum);
						promotionbj.put("sumtotal", sumall);
						newObj.put("theBest", promotionID);
						newObj.put("sumamount", sumall - dissum);
						newObj.put("sumdiscount", dissum);
						newObj.put("sumtotal", sumall);
						findPromotion.put(new JSONObject(newObj.toString()));
						
					}				
					newObj = findPromotion.getJSONObject(allpromotion.getInt("index"));
					setProdetailList(prode.getListPromotionDetail(allpromotion.getInt("proID")));
					for(PromotionDetailModel pdmodel  : getProdetailList()){
						if(pdmodel.getDiscount_type() == 3){
							JSONObject freeproductobj = new JSONObject(); 
							freeproductobj.put("freeID", pdmodel.getProduct_id());
							freeproductobj.put("freename", pdmodel.getTname());
							freeproductobj.put("freetype",pdmodel.getProduct_type());
							freeproductobj.put("qty",1);
							freeproduct.put(freeproductobj);
						}
					}
					setConList(financeData.getContactFromPatAndPro(newObj.getString("hn"),allpromotion.getString("proID")));	
				}else{
					setConList(financeData.getContactFromPatAndPro(newObj.getString("hn"),null));
					treatment = findTheBestPromotionFromTreatment(treatment);
					medicine  =	findtheBestPromotionFromMedicine(medicine);
					product   = findtheBestPromotionFromProduct(product);
					newObj.put("sumamount", sumall - dissum);
					newObj.put("sumdiscount", dissum);
					newObj.put("sumtotal", sumall);
					
				}				
				for(FinanceModel conmodel  : getConList()){
					JSONObject contypeobj = new JSONObject(); 
					contypeobj.put("conID", conmodel.getContact_id());
					contypeobj.put("conname", conmodel.getContactName());
					contype.put(contypeobj);
				}
				newObj.put("contype", contype);
				newObj.put("freeproduct", freeproduct);
				
			}else{
					setProdetailList(prode.getListPromotionDetail(newObj.getInt("chang_promotion")));	
					/**
					 * treatment
					 */
					treatment = findTheBestPromotionFromTreatment(treatment);
					medicine  =	findtheBestPromotionFromMedicine(medicine);
					product   = findtheBestPromotionFromProduct(product);

					newObj.put("theBest", newObj.getInt("chang_promotion"));
					newObj.put("sumamount", sumall - dissum);
					newObj.put("sumdiscount", dissum);
					newObj.put("sumtotal", sumall);
					findPromotion.put(new JSONObject(newObj.toString()));
			
				setProdetailList(prode.getListPromotionDetail(newObj.getInt("chang_promotion")));
				for(PromotionDetailModel pdmodel  : getProdetailList()){
					if(pdmodel.getDiscount_type() == 3){
						JSONObject freeproductobj = new JSONObject(); 
						freeproductobj.put("freeID", pdmodel.getProduct_id());
						freeproductobj.put("freename", pdmodel.getTname());
						freeproductobj.put("freetype",pdmodel.getProduct_type());
						freeproductobj.put("qty",1);
						freeproduct.put(freeproductobj);
					}
				}
				setConList(financeData.getContactFromPatAndPro(newObj.getString("hn"),newObj.getString("chang_promotion")));
				for(FinanceModel conmodel  : getConList()){
					JSONObject contypeobj = new JSONObject(); 
					contypeobj.put("conID", conmodel.getContact_id());
					contypeobj.put("conname", conmodel.getContactName());
					contype.put(contypeobj);
				}
				newObj.put("contype", contype);
				newObj.put("freeproduct", freeproduct);
				
			}
		

		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
		 
			response.getWriter().write(newObj.toString());
		 } catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public JSONArray findTheBestPromotionFromTreatment(JSONArray treatment){
		for(int i = 0 ;i<treatment.length();i++){			
			try {
				JSONObject treatObj = treatment.getJSONObject(i);
				String treatID = treatObj.get("treatID").toString();
				String groupID = treatObj.get("groupID").toString();
				String catID = treatObj.get("catID").toString();
				String treat_price = treatObj.get("treat_price").toString();
				String treat_dis = treatObj.get("treat_dis").toString();
				String treat_total = treatObj.get("treat_total").toString();
				double amount = 0;
				int amounttype=0;
				if(getProdetailList() != null){
				for(PromotionDetailModel pdmodel  : getProdetailList()){
					if(pdmodel.getProduct_type() == 4){
						amount = pdmodel.getDiscount_amount();
						amounttype= pdmodel.getDiscount_type();
						break;
					}else if(pdmodel.getProduct_type() == 5 && Integer.parseInt(groupID) == pdmodel.getProduct_id() ){
						amount = pdmodel.getDiscount_amount();
						amounttype= pdmodel.getDiscount_type();
						break;
					}else if(pdmodel.getProduct_type() == 6 && Integer.parseInt(catID) == pdmodel.getProduct_id()){
						amount = pdmodel.getDiscount_amount();
						amounttype= pdmodel.getDiscount_type();
						break;
					}else if(pdmodel.getProduct_type() == 7 && Integer.parseInt(treatID) == pdmodel.getProduct_id()){
						amount = pdmodel.getDiscount_amount();
						amounttype= pdmodel.getDiscount_type();
						break;
					}							
				}
				if(amounttype == 1){
					/*sumamount += Double.parseDouble(treat_price) - amount;*/
					dissum += amount;
					treatObj.put("treat_dis",Double.toString(amount) );
					treatObj.put("treat_total",Double.toString(Double.parseDouble(treat_price) - amount) );
				}else if(amounttype == 2){
					double per = (Double.parseDouble(treat_price) * amount)/100;
					/*sumamount += Double.parseDouble(treat_price) - per;*/
					dissum += per;
					treatObj.put("treat_dis",Double.toString(per) );
					treatObj.put("treat_total",Double.toString(Double.parseDouble(treat_price) - per));
				}else if(amount == 0 && amounttype ==0){
					treatObj.put("treat_dis",Double.toString(0) );
					treatObj.put("treat_total",Double.toString(Double.parseDouble(treat_price)));
				}
				}
				sumall += Double.parseDouble(treat_price);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return treatment;
	}
	public JSONArray findtheBestPromotionFromMedicine(JSONArray medicine){
		for(int i = 0 ;i<medicine.length();i++){			

				try {
					JSONObject medicineObj = medicine.getJSONObject(i);
					String medID = medicineObj.get("medID").toString();
					String price_per_unit = medicineObj.get("price_per_unit").toString();
					String freeMed = medicineObj.get("freeMed").toString();
					String qty = medicineObj.get("qty").toString();
					double amount = 0;
					int amounttype=0;
					double summede = 0;
					if(getProdetailList() != null){
					for(PromotionDetailModel pdmodel  : getProdetailList()){
						 if(pdmodel.getProduct_type() == 1 && Integer.parseInt(medID) == pdmodel.getProduct_id() ){
							amount = pdmodel.getDiscount_amount();
							amounttype= pdmodel.getDiscount_type();
							break;
						}							
					}
						if(amounttype == 1){
							if( Double.parseDouble(price_per_unit) < amount){
								summede = 0;
							}else{
								summede = Double.parseDouble(price_per_unit) - amount;
							}									
							/*sumamount +=  ((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * summede );*/
							dissum +=  ((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * amount);
							medicineObj.put("med_dis",Double.toString(((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * amount)) );
							medicineObj.put("med_total",Double.toString(((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * summede )) );
						}else if(amounttype == 2){
							double per = (Double.parseDouble(price_per_unit) * amount)/100;
							summede = Double.parseDouble(price_per_unit) - per;
							/*sumamount +=  ((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * summede );*/
							dissum += ((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * per);
							medicineObj.put("med_dis",Double.toString(((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * per)) );
							medicineObj.put("med_total",Double.toString(((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * summede )) );
						}else if(amount == 0 && amounttype ==0){
							medicineObj.put("med_dis",Double.toString(0) );
							medicineObj.put("med_total",Double.toString(((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * Double.parseDouble(price_per_unit) )));
						}
					}
						sumall += ((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * Double.parseDouble(price_per_unit) );
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
		return medicine;
	}
	public JSONArray findtheBestPromotionFromProduct(JSONArray product){
		for(int i = 0 ;i<product.length();i++){			

				try {
					JSONObject productObj = product.getJSONObject(i);
					String proID = productObj.get("proID").toString();
					String price_per_unit = productObj.get("price_per_unit").toString();
					String qty = productObj.get("qty").toString();
					double amount = 0;
					int amounttype=0;
					double summede = 0;
					if(getProdetailList() != null){
					for(PromotionDetailModel pdmodel  : getProdetailList()){
						 if(pdmodel.getProduct_type() == 2 && Integer.parseInt(proID) == pdmodel.getProduct_id() ){
							amount = pdmodel.getDiscount_amount();
							amounttype= pdmodel.getDiscount_type();
							break;
						}							
					}
						if(amounttype == 1){
							if( Double.parseDouble(price_per_unit) < amount){
								summede = 0;
							}else{
								summede = Double.parseDouble(price_per_unit) - amount;
							}									
							/*sumamount +=  ((Double.parseDouble(qty)) * summede );*/
							dissum +=  ((Double.parseDouble(qty)) * amount);
							productObj.put("pro_dis",Double.toString(((Double.parseDouble(qty)) * amount)) );
							productObj.put("pro_total",Double.toString(((Double.parseDouble(qty)) * summede )) );
						}else if(amounttype == 2){
							double per = (Double.parseDouble(price_per_unit) * amount)/100;
							summede = Double.parseDouble(price_per_unit) - per;
							/*sumamount +=  ((Double.parseDouble(qty)) * summede );*/
							dissum += ((Double.parseDouble(qty)) * per);
							productObj.put("pro_dis",Double.toString(((Double.parseDouble(qty)) * per)) );
							productObj.put("pro_total",Double.toString(((Double.parseDouble(qty)) * summede )) );
						}else if(amount == 0 && amounttype ==0){
							productObj.put("pro_dis",Double.toString(0) );
							productObj.put("pro_total",Double.toString(((Double.parseDouble(qty)) * Double.parseDouble(price_per_unit) )));
						}
					}
						sumall += ((Double.parseDouble(qty)) * Double.parseDouble(price_per_unit) );
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
		return product;
	}
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
	/*	servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");*/
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


	public List<FinanceModel> getConList() {
		return conList;
	}


	public void setConList(List<FinanceModel> conList) {
		this.conList = conList;
	}


	
}
