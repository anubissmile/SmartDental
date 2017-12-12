package com.smict.all.model;

import java.util.List;

import com.smict.promotion.model.PromotionModel;

public class FinanceModel {
	private String treatment_id,contact_id,contactName;
	private String product_id,product_name,product_free,product_transfer,amount,amountTotal; 
	private List<ContypeModel> contypeList;
	private List<PromotionModel> promoList;
	private ContypeModel contypeModel;
	/**
	 * order
	 */
	private int order_ID,order_docID,order_discountType,order_SubcontactID,lastPromotionID,order_treatpatID,protyep;
	private String order_Hn,order_pat_pname,order_pat_FnameTh,order_pat_FnameEn,order_pat_LnameTh,order_pat_LnameEn,
	order_doc_pname,order_doc_FnameTh,order_doc_FnameEn,order_doc_LnameTh,order_doc_LnameEn,
	order_empID,order_emp_pname,order_emp_FnameTh,order_emp_FnameEn,order_emp_LnameTh,order_emp_LnameEn;
	private String order_branchID,order_discount_ref,order_roomName,or_giftcnum,orgiftvnum;
	
	private double or_amount_untaxed,or_amount_tax,or_amount_total,or_doctor_disbaht_total
	,or_branch_disbaht_total,or_discount_total,or_pay_amount_total,or_remain_amount_total,or_qty,med_total,or_owe,pay_sso
	,can_payment,paid_amount;
	private String order_amount,pay_amount_total,remain_amount_total,channel_id,ref1,paylast_type,amount_channel;
	private String [] recall,homecall;
	/**
	 * order line
	 */
	/**
	 * order assistant
	 */
	private int os_id;
	private String os_empid,os_emp_pname,os_emp_FnameTh,os_emp_FnameEn,os_emp_LnameTh,os_emp_LnameEn;
	
	private int orderLine_ID,orderLine_TreatID,orderLine_treatPatID,orderLine_toothTypeID,orderLine_plandetailID,orderLine_catID,orderLine_groupID;
	private String orderLine_surf,orderLine_tooth,orderLine_treatName,orderLine_homecall,orderLine_recall;
	private double orderLine_price,sumallamount,sumallwithdis,sumalldis,ol_df,channel_amount,discount,disdoc_disbaht,branch_disbaht;
	/**
	 * gift voucher
	 */
	private int gv_type,gv_proID,gv_protype;
	private double gv_amount;
	
	
	/* receipt */
	private int countrow,receipt_id;
	private String receipt_typename;
	private double [] treatment_pay,medicine_pay;
	
	private int owe_id;
	
	public FinanceModel(){ 
		super();
	}
	public FinanceModel(String product_id, String product_name, String product_free, String product_transfer,
			String amount, String amountTotal) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_free = product_free;
		this.product_transfer = product_transfer;
		this.amount = amount;
		this.amountTotal = amountTotal;
	}

	public String getTreatment_id() {
		return treatment_id;
	}  
	public void setTreatment_id(String treatment_id) {
		this.treatment_id = treatment_id;
	} 
	public String getProduct_id() {
		return product_id;
	} 
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	} 
	public String getProduct_name() {
		return product_name;
	} 
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	} 
	public String getProduct_free() {
		return product_free;
	} 
	public void setProduct_free(String product_free) {
		this.product_free = product_free;
	} 
	public String getProduct_transfer() {
		return product_transfer;
	} 
	public void setProduct_transfer(String product_transfer) {
		this.product_transfer = product_transfer;
	} 
	public String getAmount() {
		return amount;
	} 
	public void setAmount(String amount) {
		this.amount = amount;
	} 
	public String getAmountTotal() {
		return amountTotal;
	} 
	public void setAmountTotal(String amountTotal) {
		this.amountTotal = amountTotal;
	}
	public int getOrder_ID() {
		return order_ID;
	}
	public int getOrder_docID() {
		return order_docID;
	}
	public int getOrder_discountType() {
		return order_discountType;
	}
	public int getOrder_SubcontactID() {
		return order_SubcontactID;
	}
	public String getOrder_Hn() {
		return order_Hn;
	}
	public String getOrder_pat_pname() {
		return order_pat_pname;
	}
	public String getOrder_pat_FnameTh() {
		return order_pat_FnameTh;
	}
	public String getOrder_pat_FnameEn() {
		return order_pat_FnameEn;
	}
	public String getOrder_pat_LnameTh() {
		return order_pat_LnameTh;
	}
	public String getOrder_pat_LnameEn() {
		return order_pat_LnameEn;
	}
	public String getOrder_doc_pname() {
		return order_doc_pname;
	}
	public String getOrder_doc_FnameTh() {
		return order_doc_FnameTh;
	}
	public String getOrder_doc_FnameEn() {
		return order_doc_FnameEn;
	}
	public String getOrder_doc_LnameTh() {
		return order_doc_LnameTh;
	}
	public String getOrder_doc_LnameEn() {
		return order_doc_LnameEn;
	}
	public String getOrder_empID() {
		return order_empID;
	}
	public String getOrder_emp_pname() {
		return order_emp_pname;
	}
	public String getOrder_emp_FnameTh() {
		return order_emp_FnameTh;
	}
	public String getOrder_emp_FnameEn() {
		return order_emp_FnameEn;
	}
	public String getOrder_emp_LnameTh() {
		return order_emp_LnameTh;
	}
	public String getOrder_emp_LnameEn() {
		return order_emp_LnameEn;
	}
	public String getOrder_branchID() {
		return order_branchID;
	}
	public String getOrder_discount_ref() {
		return order_discount_ref;
	}
	public void setOrder_ID(int order_ID) {
		this.order_ID = order_ID;
	}
	public void setOrder_docID(int order_docID) {
		this.order_docID = order_docID;
	}
	public void setOrder_discountType(int order_discountType) {
		this.order_discountType = order_discountType;
	}
	public void setOrder_SubcontactID(int order_SubcontactID) {
		this.order_SubcontactID = order_SubcontactID;
	}
	public void setOrder_Hn(String order_Hn) {
		this.order_Hn = order_Hn;
	}
	public void setOrder_pat_pname(String order_pat_pname) {
		this.order_pat_pname = order_pat_pname;
	}
	public void setOrder_pat_FnameTh(String order_pat_FnameTh) {
		this.order_pat_FnameTh = order_pat_FnameTh;
	}
	public void setOrder_pat_FnameEn(String order_pat_FnameEn) {
		this.order_pat_FnameEn = order_pat_FnameEn;
	}
	public void setOrder_pat_LnameTh(String order_pat_LnameTh) {
		this.order_pat_LnameTh = order_pat_LnameTh;
	}
	public void setOrder_pat_LnameEn(String order_pat_LnameEn) {
		this.order_pat_LnameEn = order_pat_LnameEn;
	}
	public void setOrder_doc_pname(String order_doc_pname) {
		this.order_doc_pname = order_doc_pname;
	}
	public void setOrder_doc_FnameTh(String order_doc_FnameTh) {
		this.order_doc_FnameTh = order_doc_FnameTh;
	}
	public void setOrder_doc_FnameEn(String order_doc_FnameEn) {
		this.order_doc_FnameEn = order_doc_FnameEn;
	}
	public void setOrder_doc_LnameTh(String order_doc_LnameTh) {
		this.order_doc_LnameTh = order_doc_LnameTh;
	}
	public void setOrder_doc_LnameEn(String order_doc_LnameEn) {
		this.order_doc_LnameEn = order_doc_LnameEn;
	}
	public void setOrder_empID(String order_empID) {
		this.order_empID = order_empID;
	}
	public void setOrder_emp_pname(String order_emp_pname) {
		this.order_emp_pname = order_emp_pname;
	}
	public void setOrder_emp_FnameTh(String order_emp_FnameTh) {
		this.order_emp_FnameTh = order_emp_FnameTh;
	}
	public void setOrder_emp_FnameEn(String order_emp_FnameEn) {
		this.order_emp_FnameEn = order_emp_FnameEn;
	}
	public void setOrder_emp_LnameTh(String order_emp_LnameTh) {
		this.order_emp_LnameTh = order_emp_LnameTh;
	}
	public void setOrder_emp_LnameEn(String order_emp_LnameEn) {
		this.order_emp_LnameEn = order_emp_LnameEn;
	}
	public void setOrder_branchID(String order_branchID) {
		this.order_branchID = order_branchID;
	}
	public void setOrder_discount_ref(String order_discount_ref) {
		this.order_discount_ref = order_discount_ref;
	}
	public String getOrder_roomName() {
		return order_roomName;
	}
	public void setOrder_roomName(String order_roomName) {
		this.order_roomName = order_roomName;
	}
	public int getOrderLine_ID() {
		return orderLine_ID;
	}
	public int getOrderLine_TreatID() {
		return orderLine_TreatID;
	}
	public int getOrderLine_treatPatID() {
		return orderLine_treatPatID;
	}
	public int getOrderLine_toothTypeID() {
		return orderLine_toothTypeID;
	}
	public int getOrderLine_plandetailID() {
		return orderLine_plandetailID;
	}
	public String getOrderLine_surf() {
		return orderLine_surf;
	}
	public String getOrderLine_tooth() {
		return orderLine_tooth;
	}
	public String getOrderLine_treatName() {
		return orderLine_treatName;
	}
	public String getOrderLine_homecall() {
		return orderLine_homecall;
	}
	public String getOrderLine_recall() {
		return orderLine_recall;
	}
	public double getOrderLine_price() {
		return orderLine_price;
	}
	public void setOrderLine_ID(int orderLine_ID) {
		this.orderLine_ID = orderLine_ID;
	}
	public void setOrderLine_TreatID(int orderLine_TreatID) {
		this.orderLine_TreatID = orderLine_TreatID;
	}
	public void setOrderLine_treatPatID(int orderLine_treatPatID) {
		this.orderLine_treatPatID = orderLine_treatPatID;
	}
	public void setOrderLine_toothTypeID(int orderLine_toothTypeID) {
		this.orderLine_toothTypeID = orderLine_toothTypeID;
	}
	public void setOrderLine_plandetailID(int orderLine_plandetailID) {
		this.orderLine_plandetailID = orderLine_plandetailID;
	}
	public void setOrderLine_surf(String orderLine_surf) {
		this.orderLine_surf = orderLine_surf;
	}
	public void setOrderLine_tooth(String orderLine_tooth) {
		this.orderLine_tooth = orderLine_tooth;
	}
	public void setOrderLine_treatName(String orderLine_treatName) {
		this.orderLine_treatName = orderLine_treatName;
	}
	public void setOrderLine_homecall(String orderLine_homecall) {
		this.orderLine_homecall = orderLine_homecall;
	}
	public void setOrderLine_recall(String orderLine_recall) {
		this.orderLine_recall = orderLine_recall;
	}
	public void setOrderLine_price(double orderLine_price) {
		this.orderLine_price = orderLine_price;
	}
	public List<ContypeModel> getContypeList() {
		return contypeList;
	}
	public void setContypeList(List<ContypeModel> contypeList) {
		this.contypeList = contypeList;
	}
	public List<PromotionModel> getPromoList() {
		return promoList;
	}
	public void setPromoList(List<PromotionModel> promoList) {
		this.promoList = promoList;
	}
	public int getOrderLine_catID() {
		return orderLine_catID;
	}
	public void setOrderLine_catID(int orderLine_catID) {
		this.orderLine_catID = orderLine_catID;
	}
	public int getOrderLine_groupID() {
		return orderLine_groupID;
	}
	public void setOrderLine_groupID(int orderLine_groupID) {
		this.orderLine_groupID = orderLine_groupID;
	}
	public int getLastPromotionID() {
		return lastPromotionID;
	}
	public void setLastPromotionID(int lastPromotionID) {
		this.lastPromotionID = lastPromotionID;
	}
	public double getSumallamount() {
		return sumallamount;
	}
	public void setSumallamount(double sumallamount) {
		this.sumallamount = sumallamount;
	}
	public double getSumallwithdis() {
		return sumallwithdis;
	}
	public void setSumallwithdis(double sumallwithdis) {
		this.sumallwithdis = sumallwithdis;
	}
	public double getSumalldis() {
		return sumalldis;
	}
	public void setSumalldis(double sumalldis) {
		this.sumalldis = sumalldis;
	}
	public String getContact_id() {
		return contact_id;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public int getGv_type() {
		return gv_type;
	}
	public int getGv_proID() {
		return gv_proID;
	}
	public int getGv_protype() {
		return gv_protype;
	}
	public double getGv_amount() {
		return gv_amount;
	}
	public void setGv_type(int gv_type) {
		this.gv_type = gv_type;
	}
	public void setGv_proID(int gv_proID) {
		this.gv_proID = gv_proID;
	}
	public void setGv_protype(int gv_protype) {
		this.gv_protype = gv_protype;
	}
	public void setGv_amount(double gv_amount) {
		this.gv_amount = gv_amount;
	}
	public ContypeModel getContypeModel() {
		return contypeModel;
	}
	public void setContypeModel(ContypeModel contypeModel) {
		this.contypeModel = contypeModel;
	}
	public int getOrder_treatpatID() {
		return order_treatpatID;
	}
	public void setOrder_treatpatID(int order_treatpatID) {
		this.order_treatpatID = order_treatpatID;
	}
	public int getOs_id() {
		return os_id;
	}
	public void setOs_id(int os_id) {
		this.os_id = os_id;
	}
	public String getOs_empid() {
		return os_empid;
	}
	public void setOs_empid(String os_empid) {
		this.os_empid = os_empid;
	}
	public String getOs_emp_pname() {
		return os_emp_pname;
	}
	public void setOs_emp_pname(String os_emp_pname) {
		this.os_emp_pname = os_emp_pname;
	}
	public String getOs_emp_FnameTh() {
		return os_emp_FnameTh;
	}
	public void setOs_emp_FnameTh(String os_emp_FnameTh) {
		this.os_emp_FnameTh = os_emp_FnameTh;
	}
	public String getOs_emp_FnameEn() {
		return os_emp_FnameEn;
	}
	public void setOs_emp_FnameEn(String os_emp_FnameEn) {
		this.os_emp_FnameEn = os_emp_FnameEn;
	}
	public String getOs_emp_LnameTh() {
		return os_emp_LnameTh;
	}
	public void setOs_emp_LnameTh(String os_emp_LnameTh) {
		this.os_emp_LnameTh = os_emp_LnameTh;
	}
	public String getOs_emp_LnameEn() {
		return os_emp_LnameEn;
	}
	public void setOs_emp_LnameEn(String os_emp_LnameEn) {
		this.os_emp_LnameEn = os_emp_LnameEn;
	}
	public double getOr_amount_untaxed() {
		return or_amount_untaxed;
	}
	public void setOr_amount_untaxed(double or_amount_untaxed) {
		this.or_amount_untaxed = or_amount_untaxed;
	}
	public double getOr_amount_tax() {
		return or_amount_tax;
	}
	public void setOr_amount_tax(double or_amount_tax) {
		this.or_amount_tax = or_amount_tax;
	}
	public double getOr_amount_total() {
		return or_amount_total;
	}
	public void setOr_amount_total(double or_amount_total) {
		this.or_amount_total = or_amount_total;
	}
	public double getOr_doctor_disbaht_total() {
		return or_doctor_disbaht_total;
	}
	public void setOr_doctor_disbaht_total(double or_doctor_disbaht_total) {
		this.or_doctor_disbaht_total = or_doctor_disbaht_total;
	}
	public double getOr_branch_disbaht_total() {
		return or_branch_disbaht_total;
	}
	public void setOr_branch_disbaht_total(double or_branch_disbaht_total) {
		this.or_branch_disbaht_total = or_branch_disbaht_total;
	}
	public double getOr_discount_total() {
		return or_discount_total;
	}
	public void setOr_discount_total(double or_discount_total) {
		this.or_discount_total = or_discount_total;
	}
	public double getOr_pay_amount_total() {
		return or_pay_amount_total;
	}
	public void setOr_pay_amount_total(double or_pay_amount_total) {
		this.or_pay_amount_total = or_pay_amount_total;
	}
	public double getOr_remain_amount_total() {
		return or_remain_amount_total;
	}
	public void setOr_remain_amount_total(double or_remain_amount_total) {
		this.or_remain_amount_total = or_remain_amount_total;
	}
	public String getOr_giftcnum() {
		return or_giftcnum;
	}
	public void setOr_giftcnum(String or_giftcnum) {
		this.or_giftcnum = or_giftcnum;
	}
	public String getOrgiftvnum() {
		return orgiftvnum;
	}
	public void setOrgiftvnum(String orgiftvnum) {
		this.orgiftvnum = orgiftvnum;
	}
	public String getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	public String getPay_amount_total() {
		return pay_amount_total;
	}
	public void setPay_amount_total(String pay_amount_total) {
		this.pay_amount_total = pay_amount_total;
	}
	public String getRemain_amount_total() {
		return remain_amount_total;
	}
	public void setRemain_amount_total(String remain_amount_total) {
		this.remain_amount_total = remain_amount_total;
	}
	public String[] getRecall() {
		return recall;
	}
	public void setRecall(String[] recall) {
		this.recall = recall;
	}
	public String[] getHomecall() {
		return homecall;
	}
	public void setHomecall(String[] homecall) {
		this.homecall = homecall;
	}
	public int getProtyep() {
		return protyep;
	}
	public void setProtyep(int protyep) {
		this.protyep = protyep;
	}
	public double getOr_qty() {
		return or_qty;
	}
	public void setOr_qty(double or_qty) {
		this.or_qty = or_qty;
	}
	public double getOl_df() {
		return ol_df;
	}
	public void setOl_df(double ol_df) {
		this.ol_df = ol_df;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public double getChannel_amount() {
		return channel_amount;
	}
	public void setChannel_amount(double channel_amount) {
		this.channel_amount = channel_amount;
	}
	public String getRef1() {
		return ref1;
	}
	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getDisdoc_disbaht() {
		return disdoc_disbaht;
	}
	public void setDisdoc_disbaht(double disdoc_disbaht) {
		this.disdoc_disbaht = disdoc_disbaht;
	}
	public double getBranch_disbaht() {
		return branch_disbaht;
	}
	public void setBranch_disbaht(double branch_disbaht) {
		this.branch_disbaht = branch_disbaht;
	}
	public double getMed_total() {
		return med_total;
	}
	public void setMed_total(double med_total) {
		this.med_total = med_total;
	}
	public double getOr_owe() {
		return or_owe;
	}
	public void setOr_owe(double or_owe) {
		this.or_owe = or_owe;
	}
	public double getPay_sso() {
		return pay_sso;
	}
	public void setPay_sso(double pay_sso) {
		this.pay_sso = pay_sso;
	}
	public double getCan_payment() {
		return can_payment;
	}
	public void setCan_payment(double can_payment) {
		this.can_payment = can_payment;
	}
	public double[] getTreatment_pay() {
		return treatment_pay;
	}
	public void setTreatment_pay(double[] treatment_pay) {
		this.treatment_pay = treatment_pay;
	}
	public double[] getMedicine_pay() {
		return medicine_pay;
	}
	public void setMedicine_pay(double[] medicine_pay) {
		this.medicine_pay = medicine_pay;
	}
	public int getCountrow() {
		return countrow;
	}
	public void setCountrow(int countrow) {
		this.countrow = countrow;
	}
	public String getReceipt_typename() {
		return receipt_typename;
	}
	public void setReceipt_typename(String receipt_typename) {
		this.receipt_typename = receipt_typename;
	}
	public int getReceipt_id() {
		return receipt_id;
	}
	public void setReceipt_id(int receipt_id) {
		this.receipt_id = receipt_id;
	}
	public double getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}
	public String getPaylast_type() {
		return paylast_type;
	}
	public void setPaylast_type(String paylast_type) {
		this.paylast_type = paylast_type;
	}
	public String getAmount_channel() {
		return amount_channel;
	}
	public void setAmount_channel(String amount_channel) {
		this.amount_channel = amount_channel;
	}
	public int getOwe_id() {
		return owe_id;
	}
	public void setOwe_id(int owe_id) {
		this.owe_id = owe_id;
	} 
	 
}