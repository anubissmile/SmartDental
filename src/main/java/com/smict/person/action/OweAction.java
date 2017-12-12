package com.smict.person.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
 
import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.FinanceModel;
import com.smict.all.model.ServicePatientModel;
import com.smict.auth.AuthModel;
import com.smict.finance.data.FinanceData;
import com.smict.person.data.DepositData;
import com.smict.person.data.OweData;
import com.smict.person.model.DepositModel;
import com.smict.treatment.model.TreatmentModel;

import ldc.util.Auth; 

public class OweAction extends ActionSupport{
	
	ServicePatientModel servicePatModel; 
	DepositModel depositModel;
	private TreatmentModel treatmentModel;
	private FinanceModel finanModel;
	private List<DepositModel> depositList;
	private List<FinanceModel> orderlist,orderlinelist,ordermedicinelist;
	DepositData depositdb = new DepositData();  
	OweData oweData = new OweData();  
	/**
	 * CONSTRUCTOR
	 */
	public OweAction(){
		Auth.authCheck(false);
	}
	 
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
		FinanceData financeData = new FinanceData();
		
		ServicePatientModel servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		if(session.getAttribute("ServicePatientModel")!=null){ 
			String hn	= servicePatModel.getHn();  
			request.setAttribute("depositList", depositdb.getDeposit(hn));
			
			String owe_id = request.getParameter("owe_id");
			setOrderlinelist(oweData.getOrder_list_treament(owe_id,"7"));
			setOrdermedicinelist(oweData.getOrder_list_product(owe_id,"1"));
			String patient_order_id = financeData.getPatientOrderID(hn);
			setFinanModel(financeData.getTreatmentPatientForFinance(patient_order_id));
			finanModel.setOwe_id(Integer.parseInt(owe_id));
		}  
		
		return SUCCESS;
	}
	public String owe_add() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
		HashMap<String, AuthModel> newMap = (HashMap<String, AuthModel>) session.getAttribute("userSession"); 
		AuthModel authModel = newMap.get("userEmployee");
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");

		FinanceData financeData = new FinanceData();
		
		String hn = finanModel.getOrder_Hn();
		int order_id = Integer.parseInt(request.getParameter("order_id")); 
		int receiptId = financeData.addOrderOrderReceipt(finanModel, order_id); 
		int order_doctor_id = finanModel.getOrder_docID(); 
		int oweId = 0;
		int owe_id = finanModel.getOwe_id();
		
		String paylast_type = request.getParameter("pay_type");
		if(request.getParameter("pay_type")==null) paylast_type = "f";
		finanModel.setPaylast_type(paylast_type);
		
		if(request.getParameterValues("treatmentcheckbok")!=null) {
			String[] treatmentcheckbok_ar = request.getParameterValues("treatmentcheckbok");
			String[] orderline_id = request.getParameterValues("orderLine_ID");
			String[] treatment_pay_ar = request.getParameterValues("treatment_pay"); 
			
			String[] treatment_id = request.getParameterValues("treat_id"); 
			String[] treatprice = request.getParameterValues("orderline_price"); 
			
			//String[] treat_paid_amount = request.getParameterValues("treat_paid_amount");  
			
			//String[] treat_pay_sso = request.getParameterValues("treat_pay_sso"); 
			String[] treatsurf = request.getParameterValues("treatsurf"); 
			String[] treattooth = request.getParameterValues("treattooth"); 
			String[] treattooth_type_id = request.getParameterValues("treattooth_type_id"); 
			String[] disdoctorall = request.getParameterValues("disdoctorall"); 
			String[] disbranchall = request.getParameterValues("disbranchall"); 
			String[] treatdis = request.getParameterValues("treat_dis"); 
			
			String[] treathomecall = request.getParameterValues("treathomecall"); 
			String[] treatrecall = request.getParameterValues("treatrecall"); 
			
			String[] treatment_can_payment = request.getParameterValues("treatment_can_payment");
			String[] receiptid_old = request.getParameterValues("receipt_id");
			
			for(int i=0; i<treatmentcheckbok_ar.length; i++) {
				 
				int c = Integer.parseInt(treatmentcheckbok_ar[i]);
				
				if(treatment_pay_ar[c].equals("")||treatment_pay_ar[c]==null) treatment_pay_ar[c] = "0";
				
				finanModel.setOrder_ID(order_id);
				finanModel.setOrderLine_ID(Integer.parseInt(orderline_id[c]));
				finanModel.setProduct_id(treatment_id[c]);
				finanModel.setOr_qty(1);
				finanModel.setPay_amount_total(treatment_pay_ar[c].replace(",", ""));
				finanModel.setOrderLine_price(0);
				finanModel.setPay_sso(0);
				finanModel.setOr_amount_untaxed(0);
				finanModel.setOr_amount_tax(0);
				finanModel.setOr_amount_total(0);
				finanModel.setPaid_amount(Double.parseDouble(treatment_can_payment[c].replace(",", "")));
				finanModel.setOr_doctor_disbaht_total(Double.parseDouble(disdoctorall[c].replace(",", "")));
				finanModel.setOr_branch_disbaht_total(Double.parseDouble(disbranchall[c].replace(",", "")));
				finanModel.setOr_discount_total(Double.parseDouble(treatdis[c].replace(",", "")));
				finanModel.setOrderLine_surf(treatsurf[c]);
				finanModel.setOrderLine_tooth(treattooth[c]);
				finanModel.setOrderLine_toothTypeID(Integer.parseInt(treattooth_type_id[c]));
				finanModel.setOrderLine_homecall(treathomecall[c]);
				finanModel.setOrderLine_recall(treatrecall[c]);
				 
			 	int receiptLineID = financeData.addOrderReceiptline(finanModel,7,0,receiptId,"2");
			 	
			 	double amountline = Double.parseDouble(finanModel.getPay_amount_total());
				double amount_doctor = amountline, amount_brach = 0;
				String type_payment_doctor = "doc", type_payment_brach = "bra";
				
				amount_doctor = financeData.getOrderDoctorPrice(Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, amount_doctor);
				financeData.addOrderPaymentPrice(order_id, receiptId, Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, 0, amount_doctor, type_payment_doctor);	// df doctor
					
				amount_brach = amountline-amount_doctor;
				financeData.addOrderPaymentPrice(order_id, receiptId, Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, 0, amount_brach, type_payment_brach);	// brach
				
				//add order_payment_owe
				financeData.addOrderPaymentOwe(hn,Integer.parseInt(receiptid_old[c]), Integer.parseInt(orderline_id[c]), amountline, Double.parseDouble(treatment_can_payment[i].replace(",", ""))-amountline);
				
				//add owe if payment again
				if(Double.parseDouble(treatment_can_payment[i].replace(",", ""))>amountline) {  
					if(financeData.checkOweReceipt(receiptId)==0) { 
						oweId = financeData.addOrderReceiptOwe(finanModel, receiptId);
					}  
						double owe = Double.parseDouble(treatment_can_payment[i].replace(",", ""))-amountline;
						//financeData.addOrderOwe(order_id,Integer.parseInt(orderline_id[c]),owe); 
						
						finanModel.setOr_owe(owe);
						finanModel.setReceipt_id(receiptId);
						financeData.addOrderReceiptOweline(finanModel,7,0,oweId);
				}  
			}
			
		}
		if(request.getParameterValues("medicinecheckbok")!=null) {
			String[] medicinecheckbok_ar = request.getParameterValues("medicinecheckbok");
			String[] orderline_id = request.getParameterValues("orderLine_ID");
			String[] medicine_pay_ar = request.getParameterValues("medicine_pay"); 
			
			String[] product_id = request.getParameterValues("product_id"); 
			String[] treatprice = request.getParameterValues("orderline_price"); 
			
			//String[] treat_paid_amount = request.getParameterValues("treat_paid_amount");  
			
			//String[] treat_pay_sso = request.getParameterValues("treat_pay_sso"); 
			String[] treatsurf = request.getParameterValues("treatsurf"); 
			String[] treattooth = request.getParameterValues("treattooth"); 
			String[] treattooth_type_id = request.getParameterValues("treattooth_type_id"); 
			String[] disdoctorall = request.getParameterValues("disdoctorall"); 
			String[] disbranchall = request.getParameterValues("disbranchall"); 
			String[] meddis = request.getParameterValues("med_dis"); 
			
			String[] treathomecall = request.getParameterValues("treathomecall"); 
			String[] treatrecall = request.getParameterValues("treatrecall"); 
			
			String[] medicine_can_payment = request.getParameterValues("medicine_can_payment");
			String[] receiptid_old = request.getParameterValues("receipt_id");
			
			for(int i=0; i<medicinecheckbok_ar.length; i++) {
				 
				int c = Integer.parseInt(medicinecheckbok_ar[i]);
				
				if(medicine_pay_ar[c].equals("")||medicine_pay_ar[c]==null) medicine_pay_ar[c] = "0";
				
				finanModel.setOrder_ID(order_id);
				finanModel.setOrderLine_ID(Integer.parseInt(orderline_id[c]));
				finanModel.setProduct_id(product_id[c]);
				finanModel.setOr_qty(1);
				finanModel.setPay_amount_total(medicine_pay_ar[c].replace(",", ""));
				finanModel.setOrderLine_price(0);
				finanModel.setPay_sso(0);
				finanModel.setOr_amount_untaxed(0);
				finanModel.setOr_amount_tax(0);
				finanModel.setOr_amount_total(0);
				finanModel.setPaid_amount(Double.parseDouble(medicine_can_payment[c].replace(",", "")));
				finanModel.setOr_doctor_disbaht_total(Double.parseDouble(disdoctorall[c].replace(",", "")));
				finanModel.setOr_branch_disbaht_total(Double.parseDouble(disbranchall[c].replace(",", "")));
				finanModel.setOr_discount_total(Double.parseDouble(meddis[c].replace(",", "")));
				finanModel.setOrderLine_surf(treatsurf[c]);
				finanModel.setOrderLine_tooth(treattooth[c]);
				finanModel.setOrderLine_toothTypeID(Integer.parseInt(treattooth_type_id[c]));
				finanModel.setOrderLine_homecall(treathomecall[c]);
				finanModel.setOrderLine_recall(treatrecall[c]);
				 
			 	int receiptLineID = financeData.addOrderReceiptline(finanModel,1,0,receiptId,"2");
			 	
			 	double amountline = Double.parseDouble(finanModel.getPay_amount_total());
				double amount_doctor = amountline, amount_brach = 0;
				String type_payment_doctor = "doc", type_payment_brach = "bra";
				
				amount_doctor = financeData.getOrderDoctorPrice(Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, amount_doctor);
				financeData.addOrderPaymentPrice(order_id, receiptId, Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, 0, amount_doctor, type_payment_doctor);	// df doctor
					
				amount_brach = amountline-amount_doctor;
				financeData.addOrderPaymentPrice(order_id, receiptId, Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, 0, amount_brach, type_payment_brach);	// brach
				
				//add order_payment_owe
				financeData.addOrderPaymentOwe(hn,Integer.parseInt(receiptid_old[c]), Integer.parseInt(orderline_id[c]), amountline, Double.parseDouble(medicine_can_payment[i].replace(",", ""))-amountline);
				
				//add owe if payment again
				if(Double.parseDouble(medicine_can_payment[i].replace(",", ""))>amountline) {  
					if(financeData.checkOweReceipt(receiptId)==0) { 
						oweId = financeData.addOrderReceiptOwe(finanModel, receiptId);
					}  
						double owe = Double.parseDouble(medicine_can_payment[i].replace(",", ""))-amountline;
						//financeData.addOrderOwe(order_id,Integer.parseInt(orderline_id[c]),owe); 
						
						finanModel.setOr_owe(owe);
						finanModel.setReceipt_id(receiptId);
						financeData.addOrderReceiptOweline(finanModel,1,0,oweId);
				}  
			}
			
		}
		financeData.updateOwe_StatusPayment(owe_id);
		
		return SUCCESS;
	}

	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}

	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	}

	public List<DepositModel> getDepositList() {
		return depositList;
	}

	public void setDepositList(List<DepositModel> depositList) {
		this.depositList = depositList;
	}

	public DepositModel getDepositModel() {
		return depositModel;
	}

	public void setDepositModel(DepositModel depositModel) {
		this.depositModel = depositModel;
	}

	public List<FinanceModel> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<FinanceModel> orderlist) {
		this.orderlist = orderlist;
	}

	public List<FinanceModel> getOrderlinelist() {
		return orderlinelist;
	}

	public void setOrderlinelist(List<FinanceModel> orderlinelist) {
		this.orderlinelist = orderlinelist;
	}

	public FinanceModel getFinanModel() {
		return finanModel;
	}

	public void setFinanModel(FinanceModel finanModel) {
		this.finanModel = finanModel;
	}

	public TreatmentModel getTreatmentModel() {
		return treatmentModel;
	}

	public void setTreatmentModel(TreatmentModel treatmentModel) {
		this.treatmentModel = treatmentModel;
	}

	public List<FinanceModel> getOrdermedicinelist() {
		return ordermedicinelist;
	}

	public void setOrdermedicinelist(List<FinanceModel> ordermedicinelist) {
		this.ordermedicinelist = ordermedicinelist;
	} 
	
}
