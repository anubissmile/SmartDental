package com.smict.finance.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.smict.auth.AuthModel;
import com.smict.df.DFDB;
import com.smict.finance.data.FinanceData;
import com.smict.person.data.DepositData;
import com.smict.person.data.PatContypeData;
import com.smict.person.data.PatientData;
import com.smict.person.model.DepositModel;
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
	private List<FinanceModel> orderlist,orderlinelist,conList,listgiftvoucher,listgetAsistant,ordermedicinelist,orderproductlist,orderreceiptlist,channelpaylist;
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
	public String addFinanceToOrder() throws IOException, Exception {
		FinanceData financeData = new FinanceData();
		HttpServletRequest request = ServletActionContext.getRequest();
		if(finanModel.getOrder_discountType() == 1) {
			finanModel.setOrder_discount_ref(Integer.toString(finanModel.getLastPromotionID()));
		}else if(financeModel.getOrder_discountType() == 2) {
			finanModel.setOrder_discount_ref(finanModel.getOr_giftcnum());
		}else {
			finanModel.setOrder_discount_ref(finanModel.getOrgiftvnum());
		}
/*		String amount_tax = request.getParameter("discount");*/
		String amount_total = request.getParameter("net");
		String amount_untaxed = request.getParameter("net");
		String amount_paid = request.getParameter("amount_paid").replace(",", "");
		String owe = request.getParameter("owe");
		finanModel.setOr_amount_untaxed(Double.parseDouble(amount_untaxed.replace(",", "")));
		finanModel.setOr_amount_tax(0);
		finanModel.setOr_amount_total(Double.parseDouble(amount_total.replace(",", "")));
		finanModel.setOr_pay_amount_total(Double.parseDouble(amount_paid.replace(",", "")));
		finanModel.setOr_remain_amount_total(Double.parseDouble(owe.replace(",", "")));
		 
		/**
		 * add order
		 */
		String[] disdoctorall = request.getParameterValues("disdoctorall");
		String[] disbranchall = request.getParameterValues("disbranchall");
		String[] treatdis = request.getParameterValues("treatdis");
		String[] pro_dis = request.getParameterValues("pro_dis");
		String[] disproductall = request.getParameterValues("disproductall");
		String[] med_dis = request.getParameterValues("med_dis");
		String[] dismedicineall = request.getParameterValues("dismedicineall");
		int a=0;
		double dd = 0,db = 0,dis =0;
		if(treatdis!=null) {
			for(String tdis: treatdis) {
					dis += Double.parseDouble(tdis.replace(",", ""));
					dd +=Double.parseDouble(disdoctorall[a].replace(",", ""));
					db +=Double.parseDouble(disbranchall[a].replace(",", ""));
				a++;
			}
		}
		int md = 0;
		if(med_dis!=null) {
			for(String mdis: med_dis) {
				dis += Double.parseDouble(mdis.replace(",", ""));
				db +=Double.parseDouble(dismedicineall[md].replace(",", ""));
			md++;
			}
		}
		int pd = 0;
		if(pro_dis!=null) {
			for(String pdis: pro_dis) {
				dis += Double.parseDouble(pdis.replace(",", ""));
				db +=Double.parseDouble(disproductall[pd].replace(",", ""));
			pd++;
			}
		}
		finanModel.setOr_discount_total(dis);
		finanModel.setOr_branch_disbaht_total(db);
		finanModel.setOr_doctor_disbaht_total(dd);
		int orderId = financeData.addOrderOrder(finanModel); 
		int order_doctor_id = finanModel.getOrder_docID();
		//int receiptId = financeData.addOrderOrderReceipt(finanModel, orderId);
		finanModel.setOrder_ID(orderId);		
		String[] treatIDall = request.getParameterValues("treatIDall");
		String[] treatsurf = request.getParameterValues("treatsurf");
		String[] treattooth = request.getParameterValues("treattooth");
		String[] treattooth_type_id = request.getParameterValues("treattooth_type_id");				
		String[] treatprice = request.getParameterValues("treatprice");
		
		int all = 0;
		String[] eveyamount = request.getParameterValues("eveyamount"); 
		 
		/**
		 * add order line treatment
		 */
		int i = 0;
		boolean use_sso = true;
		 
		if(treatIDall!=null) {
			for(String tid : treatIDall) {
				String treathomecall = request.getParameter("treathomecall"+tid);
				String treatrecall = request.getParameter("treatrecall"+tid);
					if(treatrecall == null)treatrecall = "0";
					if(treathomecall == null)treathomecall="0";
					finanModel.setOrderLine_recall(treatrecall);
					finanModel.setOrderLine_homecall(treathomecall);
					finanModel.setProduct_id(tid);
					finanModel.setOr_qty(1);
					finanModel.setOrderLine_price(Double.parseDouble(treatprice[i].replace(",", "")));
					finanModel.setOr_amount_untaxed(Double.parseDouble(eveyamount[all].replace(",", "")));
					finanModel.setOr_amount_tax(0);
					finanModel.setOr_amount_total(Double.parseDouble(eveyamount[all].replace(",", "")));
					finanModel.setOr_doctor_disbaht_total(Double.parseDouble(disdoctorall[i].replace(",", "")));
					finanModel.setOr_branch_disbaht_total(Double.parseDouble(disbranchall[i].replace(",", "")));
					finanModel.setOr_discount_total(Double.parseDouble(treatdis[i].replace(",", "")));
					finanModel.setOrderLine_surf(treatsurf[i]);
					finanModel.setOrderLine_tooth(treattooth[i]);
					finanModel.setOrderLine_toothTypeID(Integer.parseInt(treattooth_type_id[i]));
					 
					int orderline_id = financeData.addOrderline(finanModel,7,0);
					financeData.updateTreatmentLine_StatusPayment(finanModel);
					
					double amountline = Double.parseDouble(eveyamount[all].replace(",", ""));
					/*double amount_doctor = amountline, amount_brach = 0;
					String type_payment_doctor = "doc", type_payment_brach = "bra";
					
					amount_doctor = financeData.getOrderDoctorPrice(Integer.parseInt(tid), order_doctor_id, amount_doctor);
					financeData.addOrderPaymentPrice(orderId, Integer.parseInt(tid), order_doctor_id, 0, amount_doctor, type_payment_doctor);	// df doctor
					
					amount_brach = amountline-amount_doctor;
					financeData.addOrderPaymentPrice(orderId, Integer.parseInt(tid), order_doctor_id, 0, amount_brach, type_payment_brach);	// brach*/
					
					if(use_sso){
						if(request.getParameter("tik")!=null) {
							 
							String[] tik_sso = request.getParameterValues("tik");
							for(String channelid : tik_sso) {
								if(channelid.equals("7")) {
							
								List<Object> treatmentID = new ArrayList<>();
								boolean checkTreatmentID = false;
								  
								for(int s = 0;s<treatIDall.length;s++){ 
									treatmentID.add(treatIDall[s]);  
								}
								int getTreatmentID = financeData.getTreatment_SocialSecurityTreatment(treatmentID); 
								double sso = 0; 
							
								sso = Double.parseDouble(request.getParameter("sso").replace(",", ""));
								finanModel.setOr_pay_amount_total(sso);
								finanModel.setPay_amount_total("0");
								
								finanModel.setOrderLine_ID(orderline_id);
								int receiptId = financeData.addOrderOrderReceipt(finanModel, orderId);
								String receipt_type = "1", paylast_type = "f"; // sso
								finanModel.setPaylast_type(paylast_type);
								financeData.addOrderReceiptline(finanModel,7,0,receiptId,receipt_type);
								
								amountline = amountline-sso;
								amount_paid = String.valueOf(Double.parseDouble(amount_paid.replace(",", ""))-sso);
								use_sso = false;
								} 
							}
						}
					}
					if(Double.parseDouble(owe.replace(",", ""))<=amountline) {
						if(Double.parseDouble(owe.replace(",", ""))>0) {
							financeData.addOrderOwe(orderId,orderline_id,Double.parseDouble(owe.replace(",", "")));
							owe = String.valueOf(Double.parseDouble(owe.replace(",", ""))-amountline);  
						}
					}else if(Double.parseDouble(owe.replace(",", ""))>amountline) {  
						financeData.addOrderOwe(orderId,orderline_id,amountline);
						owe = String.valueOf(Double.parseDouble(owe.replace(",", ""))-amountline);   
					}
					if(Double.parseDouble(amount_paid.replace(",", ""))>=amountline) {
						amount_paid = String.valueOf(Double.parseDouble(amount_paid)-amountline);
					}else {
						amount_paid = "0";
					}
					
					finanModel.setOr_pay_amount_total(Double.parseDouble(amount_paid));
					
				i++;
				all++;
			}				
		}
		//owe = String.valueOf(Double.parseDouble(request.getParameter("amount_paid").replace(",", ""))-Double.parseDouble(owe.replace(",", "")));
		/**
		 * add order line medicine & product 
		 */
		String[] price_per_unit = request.getParameterValues("price_per_unit");
		String[] total_price_med = request.getParameterValues("total_price_med");
		String[] medID = request.getParameterValues("medID");

		int m = 0;
		if(medID!=null) {
			for(String mid : medID) {
					finanModel.setProduct_id(mid);
					finanModel.setOr_qty(Double.parseDouble(total_price_med[m].replace(",", ""))/Double.parseDouble(price_per_unit[m].replace(",", "")));
					finanModel.setOrderLine_price(Double.parseDouble(price_per_unit[m].replace(",", "")));
					finanModel.setOr_amount_untaxed(Double.parseDouble(eveyamount[all].replace(",", "")));
					finanModel.setOr_amount_tax(0);
					finanModel.setOr_amount_total(Double.parseDouble(eveyamount[all].replace(",", "")));
					finanModel.setOr_branch_disbaht_total(Double.parseDouble(dismedicineall[m].replace(",", "")));
					finanModel.setOr_discount_total(Double.parseDouble(med_dis[m].replace(",", "")));
					
					int orderline_id = financeData.addOrderline(finanModel,1,0);
					financeData.updateProductLine_StatusPayment(finanModel); 
					
					double amountline = Double.parseDouble(eveyamount[all].replace(",", ""));
					 
					if(Double.parseDouble(owe.replace(",", ""))<=amountline) {
						if(Double.parseDouble(owe.replace(",", ""))>0) {
							financeData.addOrderOwe(orderId,orderline_id,Double.parseDouble(owe.replace(",", "")));
							owe = String.valueOf(Double.parseDouble(owe.replace(",", ""))-amountline); 
						}
					}else if(Double.parseDouble(owe.replace(",", ""))>amountline) {  
						financeData.addOrderOwe(orderId,orderline_id,amountline);
						owe = String.valueOf(Double.parseDouble(owe.replace(",", ""))-amountline);   
					}
					
					if(Double.parseDouble(amount_paid.replace(",", ""))>=amountline) {
						amount_paid = String.valueOf(Double.parseDouble(amount_paid)-amountline);
					}else {
						amount_paid = "0";
					} 
					finanModel.setOr_pay_amount_total(Double.parseDouble(amount_paid.replace(",", "")));
					
					
				m++;
				all++;
			}
		}
		/**
		 * p-t = q
		 */
		String[] pdID = request.getParameterValues("pdID");
		
		String[] priceperunit = request.getParameterValues("priceperunit");
		String[] totalpricepro = request.getParameterValues("totalpricepro");

		int p = 0;
		if(pdID!=null) {
			for(String pid : pdID) {
					finanModel.setProduct_id(pid);
					finanModel.setOr_qty(Double.parseDouble(totalpricepro[p].replace(",", ""))/Double.parseDouble(priceperunit[p].replace(",", "")));
					finanModel.setOrderLine_price(Double.parseDouble(priceperunit[p].replace(",", "")));
					finanModel.setOr_amount_untaxed(Double.parseDouble(eveyamount[all].replace(",", "")));
					finanModel.setOr_amount_tax(0);
					finanModel.setOr_amount_total(Double.parseDouble(eveyamount[all].replace(",", "")));
					finanModel.setOr_branch_disbaht_total(Double.parseDouble(disproductall[p].replace(",", "")));
					finanModel.setOr_discount_total(Double.parseDouble(pro_dis[p].replace(",", "")));
					
					int orderline_id = financeData.addOrderline(finanModel,2,0);
					financeData.updateProductLine_StatusPayment(finanModel);
					
					double amountline = Double.parseDouble(eveyamount[all].replace(",", ""));
					 
					if(Double.parseDouble(owe.replace(",", ""))<=amountline) {
						if(Double.parseDouble(owe.replace(",", ""))>0) {
							financeData.addOrderOwe(orderId,orderline_id,Double.parseDouble(owe.replace(",", "")));
							owe = String.valueOf(Double.parseDouble(owe.replace(",", ""))-amountline);
						}
					}else if(Double.parseDouble(owe.replace(",", ""))>amountline) {  
						financeData.addOrderOwe(orderId,orderline_id,amountline);
						owe = String.valueOf(Double.parseDouble(owe.replace(",", ""))-amountline);   
					}
					
					if(Double.parseDouble(amount_paid.replace(",", ""))>=amountline) {
						amount_paid = String.valueOf(Double.parseDouble(amount_paid)-amountline);
					}else {
						amount_paid = "0";
					}
					finanModel.setOr_pay_amount_total(Double.parseDouble(amount_paid.replace(",", "")));
				p++;
				all++;
			}
		}
		
		String[] freeID = request.getParameterValues("freeID");
		String[] freetype = request.getParameterValues("freetype");
		String[] qtyfree = request.getParameterValues("qtyfree");
		int f = 0;
		if(freeID != null) {
			for(String fid : freeID) {
					finanModel.setProduct_id(fid);
					finanModel.setOr_qty(Double.parseDouble(qtyfree[f].replace(",", "")));
					financeData.addOrderline(finanModel,Integer.parseInt(freetype[f]),1);
				f++;
				all++;
			}
		}
		/**
		 * add assistant
		 */
		financeData.addOrderAssistant(finanModel);
		/**
		 * channel order
		 */
		String[] tik = request.getParameterValues("tik");
		if(tik != null) {
			for(String channelid : tik) {
				finanModel.setChannel_id(channelid);
				if(Integer.parseInt(channelid) == 1) {
					finanModel.setChannel_amount(Double.parseDouble(request.getParameter("money").replace(",", "")));
					finanModel.setRef1("");
				}else if(Integer.parseInt(channelid) == 2) {
					finanModel.setChannel_amount(Double.parseDouble(request.getParameter("credit_card").replace(",", "")));
					String ref = request.getParameter("subcontype");
					if(ref.equals("1") ) {
						ref = "Visa Master Card";
						finanModel.setRef1(ref);
					}
				}
				else if(Integer.parseInt(channelid) == 3) {
					finanModel.setChannel_amount(Double.parseDouble(request.getParameter("line_pay").replace(",", "")));
					finanModel.setRef1("");
				}
				else if(Integer.parseInt(channelid) == 4) {
					finanModel.setChannel_amount(Double.parseDouble(request.getParameter("deposit").replace(",", "")));
					finanModel.setRef1("");
					 
					HttpSession session = request.getSession(); 
					HashMap<String, AuthModel> newMap = (HashMap<String, AuthModel>) session.getAttribute("userSession"); 
					AuthModel authModel = newMap.get("userEmployee");
					servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
					if(session.getAttribute("userSession")!=null){
						 
						DepositModel depositModel = new DepositModel();
						DepositData depositdb = new DepositData();  
						
						depositModel.setHn(servicePatModel.getHn());
						depositModel.setBranch_id(authModel.getBranchID()); 
						depositModel.setDeposit_type("D");
						depositModel.setDeposit_by(authModel.getEmpPWD());
						double old_money = depositdb.GetOldMoney(servicePatModel.getHn());
						double transfer_money = Double.parseDouble(String.valueOf(request.getParameter("deposit")).replace(",", ""));
						depositModel.setTransfer_money(transfer_money);
						depositModel.setOld_money(old_money);
						depositModel.setTotal_money(old_money-transfer_money);
						depositModel.setType_money("PAY");
						
						depositdb.addDeposit(depositModel);
						
						request.setAttribute("depositList", depositdb.getDeposit(servicePatModel.getHn()));
					}  
					
				}
				else if(Integer.parseInt(channelid) == 5) {
					finanModel.setChannel_amount(Double.parseDouble(request.getParameter("giftcard").replace(",", "")));
					finanModel.setRef1("");
				}
				else if(Integer.parseInt(channelid) == 6) {
					finanModel.setChannel_amount(Double.parseDouble(request.getParameter("giftv").replace(",", "")));
					finanModel.setRef1("");
				}
				else if(Integer.parseInt(channelid) == 7) {
					finanModel.setChannel_amount(Double.parseDouble(request.getParameter("sso").replace(",", "")));
					finanModel.setRef1("");
				}else if(Integer.parseInt(channelid) == 8) {
					finanModel.setChannel_amount(Double.parseDouble(request.getParameter("contact").replace(",", "")));
					String ref = request.getParameter("subcontype");
					if(ref.equals("1") ) {
						ref = "วางบิล";
						finanModel.setRef1(ref);
					}else if(ref.equals("2")){
						ref = "วงเงินทั้งบริษัท";
						finanModel.setRef1(ref);
					}else {
						ref = "วงเงินต่อบุคคล";
						finanModel.setRef1(ref);
					}
									
				}
				financeData.addOrderChannel(finanModel);
			}
		}
		String order_id = financeData.getOrderID(finanModel.getOrder_Hn());
		setOrderlinelist(financeData.getOrder_list_treament(order_id));
		setOrdermedicinelist(financeData.getOrder_list_medicine(order_id));
		setOrderproductlist(financeData.getOrder_list_product(order_id));
		setFinanModel(financeData.getTreatmentPatientForFinance(treatmentModel.getTreatment_patient_ID()));
		setOrderreceiptlist(financeData.getOrder_list_receipt(String.valueOf(order_id)));
		setChannelpaylist(financeData.getChannel_Pay(String.valueOf(order_id)));
		
		return SUCCESS;
	} 
	public String addFinanceReceipt() throws IOException, Exception {
		  
	/*	int order_id = finanModel.getOrder_ID();
		int orderline_id = finanModel.getOrderLine_ID();
		double treatment_pay = finanModel.getTreatment_pay();*/
		HttpServletRequest request = ServletActionContext.getRequest();
		FinanceData financeData = new FinanceData();
		
		int order_id = Integer.parseInt(request.getParameter("order_id")); 
		int receiptId = financeData.addOrderOrderReceipt(finanModel, order_id);  
		int order_doctor_id = finanModel.getOrder_docID();
		String receipt_type = "2"; // normal
		
		String paylast_type = request.getParameter("pay_type");
		if(request.getParameter("pay_type")==null) paylast_type = "f";
		finanModel.setPaylast_type(paylast_type);  
		
		int oweId = 0;
		// update status 1 to treatment_patient
		if(paylast_type.equals("t")) {
			if(financeData.checkOweOrder(order_id)) {
				int treatment_patient_ID = finanModel.getOrder_treatpatID();  
				financeData.updateTreatment_StatusWork(treatment_patient_ID); 
				
				oweId = financeData.addOrderReceiptOwe(finanModel, receiptId);
			} 
		}
		// update status 1 to treatment_patient
		
		if(request.getParameterValues("treatmentcheckbok")!=null) {
			String[] treatmentcheckbok_ar = request.getParameterValues("treatmentcheckbok");
			String[] orderline_id = request.getParameterValues("orderLine_ID");
			String[] treatment_pay_ar = request.getParameterValues("treatment_pay"); 
			
			String[] treatment_id = request.getParameterValues("treat_id"); 
			String[] treatprice = request.getParameterValues("orderline_price"); 
			
			String[] pay_paid = request.getParameterValues("or_branch_disbaht_total"); 
			String[] treat_paid_amount = request.getParameterValues("treat_paid_amount");  
			
			String[] treat_pay_sso = request.getParameterValues("treat_pay_sso"); 
			String[] treatsurf = request.getParameterValues("treatsurf"); 
			String[] treattooth = request.getParameterValues("treattooth"); 
			String[] treattooth_type_id = request.getParameterValues("treattooth_type_id"); 
			String[] disdoctorall = request.getParameterValues("disdoctorall"); 
			String[] disbranchall = request.getParameterValues("disbranchall"); 
			String[] treatdis = request.getParameterValues("treat_dis"); 
			
			String[] treathomecall = request.getParameterValues("treathomecall"); 
			String[] treatrecall = request.getParameterValues("treatrecall"); 
			
			String[] treatment_can_payment = request.getParameterValues("treatment_can_payment");   
			
			for(int i=0; i<treatmentcheckbok_ar.length; i++) {
				 
				int c = Integer.parseInt(treatmentcheckbok_ar[i]);
				
				if(Double.parseDouble(treatment_can_payment[c])>0.00||paylast_type.equals("t")) {
					if(treatment_pay_ar[c].equals("")||treatment_pay_ar[c]==null) treatment_pay_ar[c] = "0";
					
					finanModel.setOrder_ID(order_id);
					finanModel.setOrderLine_ID(Integer.parseInt(orderline_id[c]));
					finanModel.setProduct_id(treatment_id[c]);
					finanModel.setOr_qty(1);
					finanModel.setPay_amount_total(treatment_pay_ar[c].replace(",", ""));
					finanModel.setOrderLine_price(Double.parseDouble(treatprice[c].replace(",", "")));
					finanModel.setPay_sso(Double.parseDouble(treat_pay_sso[c].replace(",", "")));
					finanModel.setOr_amount_untaxed(0);
					finanModel.setOr_amount_tax(0);
					finanModel.setOr_amount_total(0);
					finanModel.setPaid_amount(Double.parseDouble(pay_paid[c].replace(",", ""))-Double.parseDouble(treat_paid_amount[c].replace(",", "")));
					finanModel.setOr_doctor_disbaht_total(Double.parseDouble(disdoctorall[c].replace(",", "")));
					finanModel.setOr_branch_disbaht_total(Double.parseDouble(disbranchall[c].replace(",", "")));
					finanModel.setOr_discount_total(Double.parseDouble(treatdis[c].replace(",", "")));
					finanModel.setOrderLine_surf(treatsurf[c]);
					finanModel.setOrderLine_tooth(treattooth[c]);
					finanModel.setOrderLine_toothTypeID(Integer.parseInt(treattooth_type_id[c]));
					finanModel.setOrderLine_homecall(treathomecall[c]);
					finanModel.setOrderLine_recall(treatrecall[c]);
					 
				 	financeData.addOrderReceiptline(finanModel,7,0,receiptId,receipt_type);
				 	
				 	double amountline = Double.parseDouble(finanModel.getPay_amount_total());
					double amount_doctor = amountline, amount_brach = 0;
					String type_payment_doctor = "doc", type_payment_brach = "bra";
					
					amount_doctor = financeData.getOrderDoctorPrice(Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, amount_doctor);
					financeData.addOrderPaymentPrice(order_id, receiptId, Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, 0, amount_doctor, type_payment_doctor);	// df doctor
						
					amount_brach = amountline-amount_doctor;
					financeData.addOrderPaymentPrice(order_id, receiptId, Integer.parseInt(finanModel.getProduct_id()), order_doctor_id, 0, amount_brach, type_payment_brach);	// brach
					
					// owe สร้างรายการค้างชำระ
					if(paylast_type.equals("t")) { 
						if(request.getParameterValues("total_owe")!=null) {
							String[] total_owe = request.getParameterValues("total_owe");
							if(Double.parseDouble(total_owe[c].replace(",", ""))>0) {
								 
								finanModel.setOr_owe(Double.parseDouble(total_owe[c].replace(",", "")));
								finanModel.setReceipt_id(receiptId);
								financeData.addOrderReceiptOweline(finanModel,7,0,oweId);
							} 
						}  
					}
				} 
				
			}
			
		}
		
		/**
		 * add order line medicine 
		 */ 
		if(request.getParameterValues("medicinecheckbok")!=null) {
			String[] medicinecheckbok_ar = request.getParameterValues("medicinecheckbok");
			String[] med_orderline_id = request.getParameterValues("med_orderLine_ID");
			String[] product_id = request.getParameterValues("product_id");
			String[] or_qty = request.getParameterValues("or_qty");
			String[] medicine_pay_ar = request.getParameterValues("medicine_pay"); 
			String[] med_paid_amount = request.getParameterValues("med_paid_amount");  
			String[] price_per_unit = request.getParameterValues("price_per_unit");
			String[] med_dis = request.getParameterValues("med_dis");
			String[] med_dis_branch = request.getParameterValues("med_dis_branch");
			String[] medicine_can_payment = request.getParameterValues("medicine_can_payment");   
			String[] pay_paid = request.getParameterValues("or_branch_disbaht_total");  
			
			for(int i=0; i<medicinecheckbok_ar.length; i++) {
				 
				int c = Integer.parseInt(medicinecheckbok_ar[i]);
				
				if(Double.parseDouble(medicine_can_payment[c])>0.00||paylast_type.equals("t")) {
					if(medicine_pay_ar[c].equals("")||medicine_pay_ar[c]==null) medicine_pay_ar[c] = "0";
					
					finanModel.setOrder_ID(order_id);
					finanModel.setOrderLine_ID(Integer.parseInt(med_orderline_id[c]));
					finanModel.setProduct_id(product_id[c]);
					finanModel.setOr_qty(Double.parseDouble(or_qty[c].replace(",", "")));
					finanModel.setPay_amount_total(medicine_pay_ar[c].replace(",", ""));
					finanModel.setOrderLine_price(Double.parseDouble(price_per_unit[c].replace(",", "")));
					finanModel.setPay_sso(0);
					finanModel.setPaid_amount(Double.parseDouble(pay_paid[c].replace(",", ""))-Double.parseDouble(med_paid_amount[c].replace(",", "")));
					finanModel.setOr_amount_untaxed(0);
					finanModel.setOr_amount_tax(0);
					finanModel.setOr_amount_total(0);
					finanModel.setOr_doctor_disbaht_total(Double.parseDouble(med_dis[c].replace(",", "")));
					finanModel.setOr_branch_disbaht_total(Double.parseDouble(med_dis_branch[c].replace(",", "")));
					finanModel.setOr_discount_total(0);
					finanModel.setOrderLine_homecall("0");
					finanModel.setOrderLine_recall("0");
					
					financeData.addOrderReceiptline(finanModel,1,0,receiptId,receipt_type);
					
					double amountline = Double.parseDouble(finanModel.getPay_amount_total());
					double amount_mediine = amountline;
					String type_payment_brach = "bra";
					financeData.addOrderPaymentPrice(order_id, receiptId, 0, 0, Integer.parseInt(finanModel.getProduct_id()), amount_mediine, type_payment_brach);	// brach
				}
			}
			
		}  
		/**
		 * add order line product 
		 */ 
		if(request.getParameterValues("productcheckbok")!=null) {
			String[] productcheckbok_ar = request.getParameterValues("productcheckbok");
			String[] pro_orderline_id = request.getParameterValues("pro_orderLine_ID");
			String[] product_id = request.getParameterValues("product_id");
			String[] or_qty = request.getParameterValues("or_qty");
			String[] product_pay_ar = request.getParameterValues("product_pay"); 
			String[] pro_paid_amount = request.getParameterValues("pro_paid_amount");
			String[] price_per_unit = request.getParameterValues("price_per_unit");
			String[] pro_dis = request.getParameterValues("pro_dis");
			String[] pro_dis_branch = request.getParameterValues("pro_dis_branch");
			String[] pay_paid = request.getParameterValues("or_branch_disbaht_total");  
			String[] product_can_payment = request.getParameterValues("product_can_payment"); 
			
			for(int i=0; i<productcheckbok_ar.length; i++) {
				 
				int c = Integer.parseInt(productcheckbok_ar[i]);
				if(Double.parseDouble(product_can_payment[c])>0.00||paylast_type.equals("t")) {
					if(product_pay_ar[c].equals("")||product_pay_ar[c]==null) product_pay_ar[c] = "0";
					
					finanModel.setOrder_ID(order_id);
					finanModel.setOrderLine_ID(Integer.parseInt(pro_orderline_id[c]));
					finanModel.setProduct_id(product_id[c]);
					finanModel.setOr_qty(Double.parseDouble(or_qty[c].replace(",", "")));
					finanModel.setPay_amount_total(product_pay_ar[c].replace(",", ""));
					finanModel.setOrderLine_price(Double.parseDouble(price_per_unit[c].replace(",", "")));
					finanModel.setPay_sso(0);
					finanModel.setPaid_amount(Double.parseDouble(pay_paid[c].replace(",", ""))-Double.parseDouble(pro_paid_amount[c].replace(",", "")));
					finanModel.setOr_amount_untaxed(0);
					finanModel.setOr_amount_tax(0);
					finanModel.setOr_amount_total(0);
					finanModel.setOr_doctor_disbaht_total(Double.parseDouble(pro_dis[c].replace(",", "")));
					finanModel.setOr_branch_disbaht_total(Double.parseDouble(pro_dis_branch[c].replace(",", "")));
					finanModel.setOr_discount_total(0);
					finanModel.setOrderLine_homecall("0");
					finanModel.setOrderLine_recall("0");
					
					financeData.addOrderReceiptline(finanModel,1,0,receiptId,receipt_type);
					
					double amountline = Double.parseDouble(finanModel.getPay_amount_total());
					double amount_product = amountline;
					String type_payment_brach = "bra";
					financeData.addOrderPaymentPrice(order_id, receiptId, 0, 0, Integer.parseInt(finanModel.getProduct_id()), amount_product, type_payment_brach);	// brach
				}
			}
		}
		
		if(request.getParameter("channel_money")!=null) {
			String channel_id = "1";
			String amount_money = request.getParameter("channel_money").replace(",", "");
			if(!amount_money.equals("")&&!amount_money.equals("0")) {
				financeData.addReceiptChannel(order_id, receiptId, channel_id, Double.parseDouble(amount_money));
			}
		}
		if(request.getParameter("channel_credit")!=null) {
			String channel_id = "2";
			String amount_money = request.getParameter("channel_credit").replace(",", "");
			if(!amount_money.equals("")&&!amount_money.equals("0")) {
				financeData.addReceiptChannel(order_id, receiptId, channel_id, Double.parseDouble(amount_money));
			}
		}
		if(request.getParameter("channel_deposit")!=null) {
			String channel_id = "4";
			String amount_money = request.getParameter("channel_deposit").replace(",", "");
			if(!amount_money.equals("")&&!amount_money.equals("0")) {
				financeData.addReceiptChannel(order_id, receiptId, channel_id, Double.parseDouble(amount_money));
			} 
		}
 
		setOrderlinelist(financeData.getOrder_list_treament(String.valueOf(order_id)));
		setOrdermedicinelist(financeData.getOrder_list_medicine(String.valueOf(order_id)));
		setOrderproductlist(financeData.getOrder_list_product(String.valueOf(order_id)));
		setFinanModel(financeData.getTreatmentPatientForFinance(treatmentModel.getTreatment_patient_ID()));
		setOrderreceiptlist(financeData.getOrder_list_receipt(String.valueOf(order_id)));
		setChannelpaylist(financeData.getChannel_Pay(String.valueOf(order_id)));
		
		return SUCCESS;
	} 
	public void ajax_json_getdeposit(){
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		FinanceData financeData = new FinanceData();
		JSONObject jsonResponse = new JSONObject(); 
		String hn = "";	 
		if(request.getParameter("hn") != null) hn = request.getParameter("hn").toString();
		 	
		try { 
				 
		double amountDeposit = 	financeData.getDepositamount(hn);
			
		jsonResponse.put("totalamountall", amountDeposit);
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
	
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	public void ajax_json_getsubcontact(){
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		FinanceData financeData = new FinanceData();
		JSONObject jsonResponse = new JSONObject();
		JSONObject json = new JSONObject();
		String conid = "";  
		String hn = "";				
		if(request.getParameter("conid") != null) conid = request.getParameter("conid").toString();
		if(request.getParameter("hn") != null) hn = request.getParameter("hn").toString();
		jsonResponse = financeData.checkAndgetContact(conid);		
		try { 
				if(jsonResponse.getInt("conID") == 2){
					jsonResponse.put("check", true);
					if(jsonResponse.getInt("subContypeID") == 2 ){
						json = financeData.getContactamount(conid,"null");
					}else if(jsonResponse.getInt("subContypeID") == 3){
						json = financeData.getContactamount(conid,hn);
					}
				}else{					
					jsonResponse.put("check", false);
					json.put("totalamountall", 0);
				}
				jsonResponse.put("totalamountall", json.getDouble("totalamountall"));
		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
	
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ajax_json_promotionDetail() {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		FinanceData financeData = new FinanceData();
		JSONArray jsonResponse = new JSONArray();
		
		String proid = "";  

		if(request.getParameter("proid") != null) proid = request.getParameter("proid").toString();
		jsonResponse = financeData.getJsonArrayListPromotiondetail(proid);  	
		try { 

		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
	
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void ajax_json_checksocialSecurity() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		FinanceData financeData = new FinanceData();
		JSONObject jsonResponse = new JSONObject();
		
		String obj = request.getParameter("productobj").toString(); 
		
		JSONObject newObj = new JSONObject(obj);
		JSONArray treatment = newObj.getJSONArray("treatment"); 
		List<Object> treatmentID = new ArrayList<>();
		boolean treatmentlist = false;
		
		for(int i = 0 ;i<treatment.length();i++){
			JSONObject treatmentbj = treatment.getJSONObject(i); 
			treatmentID.add(treatmentbj.get("treatID"));  
		}
		treatmentlist = financeData.checkSocialSecurityTreatment(treatmentID);
		
		if(treatmentlist) {
			jsonResponse = financeData.checksocialSecurity(Auth.user().getBranchID()); 
		}else {
			jsonResponse.put("check", false);
		}
		 	
		try {  

		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
	
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
	}
	public void ajax_json_giftvCheck() {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		FinanceData financeData = new FinanceData();
		JSONObject jsonResponse = new JSONObject();
		
		String giftnum = "";  

		if(request.getParameter("giftnum") != null) giftnum = request.getParameter("giftnum").toString();
		jsonResponse = financeData.getJsonArrayListProduct(giftnum);  	
		try { 
			int check = 0;
			setListgiftvoucher(financeData.getgiftVoucher(giftnum));
			if(getListgiftvoucher() != null){
				check = 1;
				for(FinanceModel fmodel : getListgiftvoucher()){
					if(fmodel.getGv_type() == 2){
						check = 2;
						jsonResponse.put("amountGV", fmodel.getGv_amount());
						break;
					}
				}
				jsonResponse.put("type", check);
			}else{
				
			}

		HttpServletResponse response = ServletActionContext.getResponse();
		 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		response.setHeader("cache-control", "no-cache");
	
			response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}   
	}
	public void ajax_json_giftcardCheck() {
		
		HttpServletRequest request = ServletActionContext.getRequest();	
		FinanceData financeData = new FinanceData();
		JSONObject jsonResponse = new JSONObject();
		
		String giftnum = "";  

		if(request.getParameter("giftnum") != null) giftnum = request.getParameter("giftnum").toString();
		jsonResponse = financeData.getJsonArrayListProduct(giftnum);  	
		  
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
		if(newObj.getInt("chang_privilege") == 1){
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
						treatment = findTheBestPromotionFromTreatment(treatment,newObj.getInt("chang_privilege"));
						medicine  =	findtheBestPromotionFromMedicine(medicine,newObj.getInt("chang_privilege"));
						product   = findtheBestPromotionFromProduct(product,newObj.getInt("chang_privilege"));
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
							freeproductobj.put("qty",pdmodel.getQty());
							freeproduct.put(freeproductobj);
						}
					}
					setConList(financeData.getContactFromPatAndPro(newObj.getString("hn"),allpromotion.getString("proID")));
					
				}else{ 
					setConList(financeData.getContactFromPatAndPro(newObj.getString("hn"),null));
					treatment = findTheBestPromotionFromTreatment(treatment,newObj.getInt("chang_privilege"));
					medicine  =	findtheBestPromotionFromMedicine(medicine,newObj.getInt("chang_privilege"));
					product   = findtheBestPromotionFromProduct(product,newObj.getInt("chang_privilege"));
					newObj.put("sumamount", sumall - dissum);
					newObj.put("sumdiscount", dissum);
					newObj.put("sumtotal", sumall);
					
				}				
				/*for(FinanceModel conmodel  : getConList()){
					JSONObject contypeobj = new JSONObject(); 
					contypeobj.put("conID", conmodel.getContact_id());
					contypeobj.put("conname", conmodel.getContactName());
					contype.put(contypeobj);
				}
				newObj.put("contype", contype);*/
				newObj.put("freeproduct", freeproduct);
				
			}else if(newObj.getInt("chang_promotion") == 99999){
				setConList(financeData.getContactFromPatAndPro(newObj.getString("hn"),null));
				treatment = findTheBestPromotionFromTreatment(treatment,newObj.getInt("chang_privilege"));
				medicine  =	findtheBestPromotionFromMedicine(medicine,newObj.getInt("chang_privilege"));
				product   = findtheBestPromotionFromProduct(product,newObj.getInt("chang_privilege"));

				newObj.put("theBest", 99999);
				newObj.put("sumamount", sumall - dissum);
				newObj.put("sumdiscount", dissum); 
				newObj.put("sumtotal", sumall);
				newObj.put("finaldiscount", 0);
				newObj.put("finalnet", sumall);
				newObj.put("freeproduct", freeproduct);
			}else{
					setProdetailList(prode.getListPromotionDetail(newObj.getInt("chang_promotion")));	
					/**
					 * treatment
					 */
					treatment = findTheBestPromotionFromTreatment(treatment,newObj.getInt("chang_privilege"));
					medicine  =	findtheBestPromotionFromMedicine(medicine,newObj.getInt("chang_privilege"));
					product   = findtheBestPromotionFromProduct(product,newObj.getInt("chang_privilege"));

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
						freeproductobj.put("qty",pdmodel.getQty());
						freeproduct.put(freeproductobj);
					}
				}
				/*setConList(financeData.getContactFromPatAndPro(newObj.getString("hn"),newObj.getString("chang_promotion")));
				for(FinanceModel conmodel  : getConList()){
					JSONObject contypeobj = new JSONObject(); 
					contypeobj.put("conID", conmodel.getContact_id());
					contypeobj.put("conname", conmodel.getContactName());
					contype.put(contypeobj);
				}
				newObj.put("contype", contype);*/
				newObj.put("freeproduct", freeproduct);
				
			}		
		}else if(newObj.getInt("chang_privilege") == 2){
			treatment = findTheBestPromotionFromTreatment(treatment,newObj.getInt("chang_privilege"));
			medicine  =	findtheBestPromotionFromMedicine(medicine,newObj.getInt("chang_privilege"));
			product   = findtheBestPromotionFromProduct(product,newObj.getInt("chang_privilege"));
			newObj.put("sumamount", sumall - dissum);
			newObj.put("sumdiscount", dissum);
			newObj.put("sumtotal", sumall);
			newObj.put("finaldiscount", 0);
			newObj.put("finalnet", sumall);
			newObj.put("freeproduct", freeproduct);
		}else if(newObj.getInt("chang_privilege") == 3){
			if(!newObj.getString("giftVoucher").equals("") && newObj.getInt("gvtype") == 1){
				setListgiftvoucher(financeData.getgiftVoucher(newObj.getString("giftVoucher")));				
			}
			
			
			treatment = findTheBestPromotionFromTreatment(treatment,newObj.getInt("chang_privilege"));
			medicine  =	findtheBestPromotionFromMedicine(medicine,newObj.getInt("chang_privilege"));
			product   = findtheBestPromotionFromProduct(product,newObj.getInt("chang_privilege"));
			newObj.put("sumamount", sumall - dissum);
			newObj.put("sumdiscount", dissum);
			newObj.put("sumtotal", sumall);
			newObj.put("finaldiscount", 0);
			newObj.put("finalnet", sumall);
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
	public JSONArray findTheBestPromotionFromTreatment(JSONArray treatment,int bigtype){
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
				if(getProdetailList() != null && bigtype == 1) {
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
					if( Double.parseDouble(treat_price) < amount){
						amount = Double.parseDouble(treat_price);
						dissum += amount;
						treatObj.put("treat_dis",Double.toString(amount) );
						treatObj.put("treat_total",Double.toString(Double.parseDouble(treat_price) - amount) );
					}else{
						dissum += amount;
						treatObj.put("treat_dis",Double.toString(amount) );
						treatObj.put("treat_total",Double.toString(Double.parseDouble(treat_price) - amount) );
					}		
					
					
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
			}else if(bigtype == 1){
				treatObj.put("treat_dis",Double.toString(0) );
				treatObj.put("treat_total",Double.toString(Double.parseDouble(treat_price)));
			}else if(bigtype == 2){
					treatObj.put("treat_dis",Double.toString(0) );
					treatObj.put("treat_total",Double.toString(Double.parseDouble(treat_price)));
			}else if(bigtype == 3){
				if(getListgiftvoucher() != null){
					for(FinanceModel fmodel : getListgiftvoucher()){
						if(fmodel.getGv_type() == 1){
							if(fmodel.getGv_protype() == 5 && Integer.parseInt(groupID) == fmodel.getGv_proID() ){
								amount = fmodel.getGv_amount();
								break;
							}else if(fmodel.getGv_protype() == 6 && Integer.parseInt(catID) == fmodel.getGv_proID()){
								amount = fmodel.getGv_amount();
								break;
							}else if(fmodel.getGv_protype() == 7 && Integer.parseInt(treatID) == fmodel.getGv_proID()){
								amount = fmodel.getGv_amount();
								break;
							}
						}else if(fmodel.getGv_type() == 2){							
							break;
						}
					}
					treatObj.put("treat_dis",Double.toString(Double.parseDouble(treat_price) - amount) );
					treatObj.put("treat_total",Double.toString(amount));
					
				}else{
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
	public JSONArray findtheBestPromotionFromMedicine(JSONArray medicine,int bigtype){
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
					}else{
						medicineObj.put("med_dis",Double.toString(0) );
						medicineObj.put("med_total",Double.toString(((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * Double.parseDouble(price_per_unit) )));
					}
						sumall += ((Double.parseDouble(qty) - Double.parseDouble(freeMed)) * Double.parseDouble(price_per_unit) );
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}		
		return medicine;
	}
	public JSONArray findtheBestPromotionFromProduct(JSONArray product,int bigtype){
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
					else{
						productObj.put("pro_dis",Double.toString(0) );
						productObj.put("pro_total",Double.toString(((Double.parseDouble(qty)) * Double.parseDouble(price_per_unit) )));
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
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
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
			 * select assistant
			 */
			/*setListgetAsistant(financeData.getAssistant(treatmentModel.getTreatment_patient_ID()));*/
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
				
				finanModel.setContypeModel(patContypeData.getContype(finanModel.getOrder_Hn(),1,1));
				/**
				 * select promotion
				 */
				finanModel.setPromoList(financeData.getPromotion(finanModel.getOrder_Hn(),finanModel.getContypeModel().getSub_contact_id()));

			
		}else{
			if(session.getAttribute("ServicePatientModel")!=null){

				setListtreatpatmedicine(treatData.getTreatmentpatMedicine("0"));
				
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
	public List<FinanceModel> getListgiftvoucher() {
		return listgiftvoucher;
	}
	public void setListgiftvoucher(List<FinanceModel> listgiftvoucher) {
		this.listgiftvoucher = listgiftvoucher;
	}
	public List<FinanceModel> getListgetAsistant() {
		return listgetAsistant;
	}
	public void setListgetAsistant(List<FinanceModel> listgetAsistant) {
		this.listgetAsistant = listgetAsistant;
	}
	public List<FinanceModel> getOrdermedicinelist() {
		return ordermedicinelist;
	}
	public void setOrdermedicinelist(List<FinanceModel> ordermedicinelist) {
		this.ordermedicinelist = ordermedicinelist;
	}
	public List<FinanceModel> getOrderproductlist() {
		return orderproductlist;
	}
	public void setOrderproductlist(List<FinanceModel> orderproductlist) {
		this.orderproductlist = orderproductlist;
	}
	public List<FinanceModel> getOrderreceiptlist() {
		return orderreceiptlist;
	}
	public void setOrderreceiptlist(List<FinanceModel> orderreceiptlist) {
		this.orderreceiptlist = orderreceiptlist;
	}
	public List<FinanceModel> getChannelpaylist() {
		return channelpaylist;
	}
	public void setChannelpaylist(List<FinanceModel> channelpaylist) {
		this.channelpaylist = channelpaylist;
	}


	
}
