package com.smict.treatment.model;

import java.util.List;

import com.smict.all.model.TreatmentMasterModel;
import com.smict.product.model.ProductModel;

public class TreatmentModel {
	/**
	 * Loop iterator
	 */
	private int iterator;
	
	/**
	 * Treatment  continuous
	 */
	private String treatment_con_id,treatment_con_treatID,treatment_con_phase,treatment_con_countno
	,treatment_con_createdate,treatment_con_updatedate;
	private double treatment_con_price,treatment_con_startprice,treatment_con_endprice;
	public List<ProductModel> proModel;
	public List<TreatmentMasterModel> treatMasterModel;
	
	/**
	 * Treatment patient 
	 */	
	private String treatment_patient_ID,treatment_patient_roomID,treatment_patient_docID
		,treatment_patient_status,treatment_patient_startTime,treatment_patient_hn,treatment_patient_roomName;
	
	/**
	 * Treatment patient line
	 */
	private String  surface,surface_tooth,mouth,quadrant,sextant,arch,toothRange,tooth,treatment_ID,tooth_types,treatMent_name
			,treatMent_code,treat_line_iscon,treatmentplandetailid;
	private int treatpatLine_id,treatment_patient_id,tooth_type_id;
	private double treatment_price;
	
	/**
	 * Treatment Product
	 */
	String treatPro_id,pro_id,treatPro_treatID,treatPro_name;
	int treatPro_amount,treatPro_amountfree;
	
	/**
	 * Treatment Patient Medicine
	 */
	String treatPatMedicine_id,treatPatMedicine_ProID,isCheck,prounitname;
	int treatPatMedicine_amount,treatPatMedicine_amountfree;
	
	/**
	 * Treatment
	 */
	private int treatmentID;
	private String[] strTreatmentID;
	private String treatmentCode;
	private String treatmentNameTH, treatmentNameEN;
	private int autoHomeCall, recall, isContinue, treatmentMode, isRepeat;

	/**
	 * Phase treatment.
	 */
	private int[] totalPhase;
	private int[] round;
	private int[] phasePrice;
	private int[] startPriceRange, endPriceRange;
	

	/**
	 * Treatment group
	 */
	private int treatmentGroupID;
	private String treatmentGroupCode, treatmentGroupName;
	
	/**
	 * Treatment category
	 */
	private int treatmentCategoryID;
	private String treatmentCategoryName, treatmentCategoryCode;
	
	/**
	 * Tooth picture
	 */
	private String toothPicCode, toothPicName;
	
	/**
	 * Tooth type
	 */
	private int toothTypeID;
	private int[] toothTypeIDArr;
	private String toothTypeNameTH, toothTypeNameEN;

	/**
	 * Patient
	 */
	private String preName, firstNameTH, lastNameTH, firstNameEN, lastNameEN, hn;
	
	/**
	 * Branch
	 */
	private String branchCode, branchId, branchName;
	
	/**
	 * Price list.
	 */
	private double[] amountPrice, welfarePrice;
	private int[] amountPriceType, welfarePriceType;
	private int priceListTreatID, priceListID, brandID, priceTypeID;
	private double amountP, welfareP;
	private int amountPType, welfarePType;
	private List<TreatmentModel> priceListModel;
	
	/**
	 * Queue status.
	 */
	private int qstatusKey, queueId;
	private String qstatusDescription;
	
	/**
	 * DATETIME 
	 */
	private String createdAt, updatedAt;
	
	/**
	 * Doctor workday
	 */
	private int workdayId;
	
	public TreatmentModel() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * GETTER & SETTER ZONE
	 */
	
	public String getPreName() {
		return preName;
	}



	public void setPreName(String preName) {
		this.preName = preName;
	}



	public String getFirstNameTH() {
		return firstNameTH;
	}



	public void setFirstNameTH(String firstNameTH) {
		this.firstNameTH = firstNameTH;
	}



	public String getLastNameTH() {
		return lastNameTH;
	}



	public void setLastNameTH(String lastNameTH) {
		this.lastNameTH = lastNameTH;
	}



	public String getFirstNameEN() {
		return firstNameEN;
	}



	public void setFirstNameEN(String firstNameEN) {
		this.firstNameEN = firstNameEN;
	}



	public String getLastNameEN() {
		return lastNameEN;
	}



	public void setLastNameEN(String lastNameEN) {
		this.lastNameEN = lastNameEN;
	}



	public String getHn() {
		return hn;
	}



	public void setHn(String hn) {
		this.hn = hn;
	}



	public String getBranchCode() {
		return branchCode;
	}



	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}



	public String getBranchId() {
		return branchId;
	}



	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}



	public String getBranchName() {
		return branchName;
	}



	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public int getQstatusKey() {
		return qstatusKey;
	}


	public void setQstatusKey(int qstatusKey) {
		this.qstatusKey = qstatusKey;
	}

	public String getQstatusDescription() {
		return qstatusDescription;
	}


	public void setQstatusDescription(String qstatusDescription) {
		this.qstatusDescription = qstatusDescription;
	}


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public String getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}


	public int getWorkdayId() {
		return workdayId;
	}


	public void setWorkdayId(int workdayId) {
		this.workdayId = workdayId;
	}


	/**
	 * @return the queueId
	 */
	public int getQueueId() {
		return queueId;
	}


	/**
	 * @param queueId the queueId to set
	 */
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}


	public int getTreatmentGroupID() {
		return treatmentGroupID;
	}


	public void setTreatmentGroupID(int treatmentGroupID) {
		this.treatmentGroupID = treatmentGroupID;
	}


	public String getTreatmentGroupCode() {
		return treatmentGroupCode;
	}


	public void setTreatmentGroupCode(String treatmentGroupCode) {
		this.treatmentGroupCode = treatmentGroupCode;
	}


	public String getTreatmentGroupName() {
		return treatmentGroupName;
	}


	public void setTreatmentGroupName(String treatmentGroupName) {
		this.treatmentGroupName = treatmentGroupName;
	}


	public int getTreatmentCategoryID() {
		return treatmentCategoryID;
	}


	public void setTreatmentCategoryID(int treatmentCategoryID) {
		this.treatmentCategoryID = treatmentCategoryID;
	}


	public String getTreatmentCategoryName() {
		return treatmentCategoryName;
	}


	public void setTreatmentCategoryName(String treatmentCategoryName) {
		this.treatmentCategoryName = treatmentCategoryName;
	}


	public String getTreatmentCategoryCode() {
		return treatmentCategoryCode;
	}


	public void setTreatmentCategoryCode(String treatmentCategoryCode) {
		this.treatmentCategoryCode = treatmentCategoryCode;
	}


	public String getToothPicCode() {
		return toothPicCode;
	}


	public void setToothPicCode(String toothPicCode) {
		this.toothPicCode = toothPicCode;
	}


	public String getToothPicName() {
		return toothPicName;
	}


	public void setToothPicName(String toothPicName) {
		this.toothPicName = toothPicName;
	}


	public int getToothTypeID() {
		return toothTypeID;
	}


	public void setToothTypeID(int toothTypeID) {
		this.toothTypeID = toothTypeID;
	}


	public String getToothTypeNameTH() {
		return toothTypeNameTH;
	}


	public void setToothTypeNameTH(String toothTypeNameTH) {
		this.toothTypeNameTH = toothTypeNameTH;
	}


	public String getToothTypeNameEN() {
		return toothTypeNameEN;
	}


	public void setToothTypeNameEN(String toothTypeNameEN) {
		this.toothTypeNameEN = toothTypeNameEN;
	}


	public String getSurface() {
		return surface;
	}


	public void setSurface(String surface) {
		this.surface = surface;
	}


	public String getMouth() {
		return mouth;
	}


	public void setMouth(String mouth) {
		this.mouth = mouth;
	}


	public String getQuadrant() {
		return quadrant;
	}


	public void setQuadrant(String quadrant) {
		this.quadrant = quadrant;
	}


	public String getSextant() {
		return sextant;
	}


	public void setSextant(String sextant) {
		this.sextant = sextant;
	}


	public String getArch() {
		return arch;
	}


	public void setArch(String arch) {
		this.arch = arch;
	}


	public String getToothRange() {
		return toothRange;
	}


	public void setToothRange(String toothRange) {
		this.toothRange = toothRange;
	}


	public int getTreatpatLine_id() {
		return treatpatLine_id;
	}


	public void setTreatpatLine_id(int treatpatLine_id) {
		this.treatpatLine_id = treatpatLine_id;
	}


	public int getTreatment_patient_id() {
		return treatment_patient_id;
	}


	public void setTreatment_patient_id(int treatment_patient_id) {
		this.treatment_patient_id = treatment_patient_id;
	}


	public int getTooth_type_id() {
		return tooth_type_id;
	}


	public void setTooth_type_id(int tooth_type_id) {
		this.tooth_type_id = tooth_type_id;
	}


	public double getTreatment_price() {
		return treatment_price;
	}


	public void setTreatment_price(double treatment_price) {
		this.treatment_price = treatment_price;
	}


	public String getTooth() {
		return tooth;
	}


	public void setTooth(String tooth) {
		this.tooth = tooth;
	}


	public String getTreatment_ID() {
		return treatment_ID;
	}


	public void setTreatment_ID(String treatment_ID) {
		this.treatment_ID = treatment_ID;
	}


	public String getTreatment_patient_ID() {
		return treatment_patient_ID;
	}


	public void setTreatment_patient_ID(String treatment_patient_ID) {
		this.treatment_patient_ID = treatment_patient_ID;
	}


	public String getTreatment_patient_roomID() {
		return treatment_patient_roomID;
	}


	public void setTreatment_patient_roomID(String treatment_patient_roomID) {
		this.treatment_patient_roomID = treatment_patient_roomID;
	}


	public String getTreatment_patient_docID() {
		return treatment_patient_docID;
	}


	public void setTreatment_patient_docID(String treatment_patient_docID) {
		this.treatment_patient_docID = treatment_patient_docID;
	}


	public String getTreatment_patient_status() {
		return treatment_patient_status;
	}


	public void setTreatment_patient_status(String treatment_patient_status) {
		this.treatment_patient_status = treatment_patient_status;
	}


	public String getTreatment_patient_startTime() {
		return treatment_patient_startTime;
	}


	public void setTreatment_patient_startTime(String treatment_patient_startTime) {
		this.treatment_patient_startTime = treatment_patient_startTime;
	}


	public String getTreatment_patient_hn() {
		return treatment_patient_hn;
	}


	public void setTreatment_patient_hn(String treatment_patient_hn) {
		this.treatment_patient_hn = treatment_patient_hn;
	}


	public String getTreatment_patient_roomName() {
		return treatment_patient_roomName;
	}


	public void setTreatment_patient_roomName(String treatment_patient_roomName) {
		this.treatment_patient_roomName = treatment_patient_roomName;
	}


	public String getSurface_tooth() {
		return surface_tooth;
	}


	public void setSurface_tooth(String surface_tooth) {
		this.surface_tooth = surface_tooth;
	}


	public String getTooth_types() {
		return tooth_types;
	}


	public void setTooth_types(String tooth_types) {
		this.tooth_types = tooth_types;
	}


	public String getTreatMent_name() {
		return treatMent_name;
	}


	public void setTreatMent_name(String treatMent_name) {
		this.treatMent_name = treatMent_name;
	}


	public String getTreatMent_code() {
		return treatMent_code;
	}


	public void setTreatMent_code(String treatMent_code) {
		this.treatMent_code = treatMent_code;
	}


	public String getTreat_line_iscon() {
		return treat_line_iscon;
	}


	public void setTreat_line_iscon(String treat_line_iscon) {
		this.treat_line_iscon = treat_line_iscon;
	}


	public String getTreatPro_id() {
		return treatPro_id;
	}


	public void setTreatPro_id(String treatPro_id) {
		this.treatPro_id = treatPro_id;
	}


	public String getPro_id() {
		return pro_id;
	}


	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}

	public String getTreatPro_treatID() {
		return treatPro_treatID;
	}


	public void setTreatPro_treatID(String treatPro_treatID) {
		this.treatPro_treatID = treatPro_treatID;
	}


	public String getTreatPatMedicine_id() {
		return treatPatMedicine_id;
	}


	public void setTreatPatMedicine_id(String treatPatMedicine_id) {
		this.treatPatMedicine_id = treatPatMedicine_id;
	}


	public String getTreatPatMedicine_ProID() {
		return treatPatMedicine_ProID;
	}


	public void setTreatPatMedicine_ProID(String treatPatMedicine_ProID) {
		this.treatPatMedicine_ProID = treatPatMedicine_ProID;
	}


	public int getTreatPro_amount() {
		return treatPro_amount;
	}


	public void setTreatPro_amount(int treatPro_amount) {
		this.treatPro_amount = treatPro_amount;
	}


	public int getTreatPro_amountfree() {
		return treatPro_amountfree;
	}


	public void setTreatPro_amountfree(int treatPro_amountfree) {
		this.treatPro_amountfree = treatPro_amountfree;
	}


	public int getTreatPatMedicine_amount() {
		return treatPatMedicine_amount;
	}


	public void setTreatPatMedicine_amount(int treatPatMedicine_amount) {
		this.treatPatMedicine_amount = treatPatMedicine_amount;
	}


	public int getTreatPatMedicine_amountfree() {
		return treatPatMedicine_amountfree;
	}


	public void setTreatPatMedicine_amountfree(int treatPatMedicine_amountfree) {
		this.treatPatMedicine_amountfree = treatPatMedicine_amountfree;
	}


	public String getTreatPro_name() {
		return treatPro_name;
	}


	public void setTreatPro_name(String treatPro_name) {
		this.treatPro_name = treatPro_name;
	}


	public String getIsCheck() {
		return isCheck;
	}


	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}


	public String getProunitname() {
		return prounitname;
	}


	public void setProunitname(String prounitname) {
		this.prounitname = prounitname;
	}

	public int getTreatmentID() {
		return treatmentID;
	}


	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}


	public String getTreatmentCode() {
		return treatmentCode;
	}


	public void setTreatmentCode(String treatmentCode) {
		this.treatmentCode = treatmentCode;
	}


	public String getTreatmentNameTH() {
		return treatmentNameTH;
	}


	public void setTreatmentNameTH(String treatmentNameTH) {
		this.treatmentNameTH = treatmentNameTH;
	}


	public String getTreatmentNameEN() {
		return treatmentNameEN;
	}


	public void setTreatmentNameEN(String treatmentNameEN) {
		this.treatmentNameEN = treatmentNameEN;
	}


	public int getAutoHomeCall() {
		return autoHomeCall;
	}


	public void setAutoHomeCall(int autoHomeCall) {
		this.autoHomeCall = autoHomeCall;
	}


	public int getRecall() {
		return recall;
	}


	public void setRecall(int recall) {
		this.recall = recall;
	}


	public int getIsContinue() {
		return isContinue;
	}


	public void setIsContinue(int isContinue) {
		this.isContinue = isContinue;
	}


	public int[] getToothTypeIDArr() {
		return toothTypeIDArr;
	}


	public void setToothTypeIDArr(int[] toothTypeIDArr) {
		this.toothTypeIDArr = toothTypeIDArr;
	}


	public int getTreatmentMode() {
		return treatmentMode;
	}


	public void setTreatmentMode(int treatmentMode) {
		this.treatmentMode = treatmentMode;
	}


	public double[] getAmountPrice() {
		return amountPrice;
	}


	public void setAmountPrice(double[] amountPrice) {
		this.amountPrice = amountPrice;
	}


	public int[] getAmountPriceType() {
		return amountPriceType;
	}


	public void setAmountPriceType(int[] amountPriceType) {
		this.amountPriceType = amountPriceType;
	}


	public double[] getWelfarePrice() {
		return welfarePrice;
	}


	public void setWelfarePrice(double[] welfarePrice) {
		this.welfarePrice = welfarePrice;
	}


	public int[] getWelfarePriceType() {
		return welfarePriceType;
	}


	public void setWelfarePriceType(int[] welfarePriceType) {
		this.welfarePriceType = welfarePriceType;
	}


	public int getIsRepeat() {
		return isRepeat;
	}


	public void setIsRepeat(int isRepeat) {
		this.isRepeat = isRepeat;
	}



	public String getTreatmentplandetailid() {
		return treatmentplandetailid;
	}


	public void setTreatmentplandetailid(String treatmentplandetailid) {
		this.treatmentplandetailid = treatmentplandetailid;
	}
	public String[] getStrTreatmentID() {
		return strTreatmentID;
	}


	public void setStrTreatmentID(String[] strTreatmentID) {
		this.strTreatmentID = strTreatmentID;
	}


	public int[] getTotalPhase() {
		return totalPhase;
	}


	public int[] getRound() {
		return round;
	}


	public int[] getPhasePrice() {
		return phasePrice;
	}


	public void setTotalPhase(int[] totalPhase) {
		this.totalPhase = totalPhase;
	}


	public void setRound(int[] round) {
		this.round = round;
	}


	public void setPhasePrice(int[] phasePrice) {
		this.phasePrice = phasePrice;
	}


	public int[] getStartPriceRange() {
		return startPriceRange;
	}


	public int[] getEndPriceRange() {
		return endPriceRange;
	}


	public void setStartPriceRange(int[] startPriceRange) {
		this.startPriceRange = startPriceRange;
	}


	public void setEndPriceRange(int[] endPriceRange) {
		this.endPriceRange = endPriceRange;

	}


	public String getTreatment_con_id() {
		return treatment_con_id;
	}


	public void setTreatment_con_id(String treatment_con_id) {
		this.treatment_con_id = treatment_con_id;
	}


	public String getTreatment_con_treatID() {
		return treatment_con_treatID;
	}


	public void setTreatment_con_treatID(String treatment_con_treatID) {
		this.treatment_con_treatID = treatment_con_treatID;
	}


	public String getTreatment_con_phase() {
		return treatment_con_phase;
	}


	public void setTreatment_con_phase(String treatment_con_phase) {
		this.treatment_con_phase = treatment_con_phase;
	}


	public String getTreatment_con_countno() {
		return treatment_con_countno;
	}


	public void setTreatment_con_countno(String treatment_con_countno) {
		this.treatment_con_countno = treatment_con_countno;
	}


	public String getTreatment_con_createdate() {
		return treatment_con_createdate;
	}


	public void setTreatment_con_createdate(String treatment_con_createdate) {
		this.treatment_con_createdate = treatment_con_createdate;
	}


	public String getTreatment_con_updatedate() {
		return treatment_con_updatedate;
	}


	public void setTreatment_con_updatedate(String treatment_con_updatedate) {
		this.treatment_con_updatedate = treatment_con_updatedate;
	}


	public double getTreatment_con_price() {
		return treatment_con_price;
	}


	public void setTreatment_con_price(double treatment_con_price) {
		this.treatment_con_price = treatment_con_price;
	}


	public double getTreatment_con_startprice() {
		return treatment_con_startprice;
	}


	public void setTreatment_con_startprice(double treatment_con_startprice) {
		this.treatment_con_startprice = treatment_con_startprice;
	}


	public double getTreatment_con_endprice() {
		return treatment_con_endprice;
	}


	public void setTreatment_con_endprice(double treatment_con_endprice) {
		this.treatment_con_endprice = treatment_con_endprice;
	}


	public List<ProductModel> getProModel() {
		return proModel;
	}


	public void setProModel(List<ProductModel> proModel) {
		this.proModel = proModel;
	}


	public List<TreatmentMasterModel> getTreatMasterModel() {
		return treatMasterModel;
	}


	public void setTreatMasterModel(List<TreatmentMasterModel> treatMasterModel) {
		this.treatMasterModel = treatMasterModel;
	}


	public int getIterator() {
		return iterator;
	}


	public void setIterator(int iterator) {
		this.iterator = iterator;
	}



	public List<TreatmentModel> getPriceListModel() {
		return priceListModel;
	}


	public void setPriceListModel(List<TreatmentModel> priceListModel) {
		this.priceListModel = priceListModel;
	}


	public int getPriceListTreatID() {
		return priceListTreatID;
	}


	public int getPriceListID() {
		return priceListID;
	}


	public int getBrandID() {
		return brandID;
	}


	public int getPriceTypeID() {
		return priceTypeID;
	}


	public double getAmountP() {
		return amountP;
	}


	public double getWelfareP() {
		return welfareP;
	}


	public int getAmountPType() {
		return amountPType;
	}


	public int getWelfarePType() {
		return welfarePType;
	}


	public void setPriceListTreatID(int priceListTreatID) {
		this.priceListTreatID = priceListTreatID;
	}


	public void setPriceListID(int priceListID) {
		this.priceListID = priceListID;
	}


	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}


	public void setPriceTypeID(int priceTypeID) {
		this.priceTypeID = priceTypeID;
	}


	public void setAmountP(double amountP) {
		this.amountP = amountP;
	}


	public void setWelfareP(double welfareP) {
		this.welfareP = welfareP;
	}


	public void setAmountPType(int amountPType) {
		this.amountPType = amountPType;
	}


	public void setWelfarePType(int welfarePType) {
		this.welfarePType = welfarePType;
	}




}
