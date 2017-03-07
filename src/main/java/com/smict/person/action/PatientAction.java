package com.smict.person.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.helpers.DateTimeDateFormat;
import org.apache.struts2.ServletActionContext;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.datetime.joda.JodaDateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.datetime.joda.JodaTimeContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ContypeModel;
import com.smict.all.model.ServicePatientModel;
import com.smict.person.data.AddressData;
import com.smict.person.data.CongenitalData;
import com.smict.person.data.FamilyData;
import com.smict.person.data.FileData;
import com.smict.person.data.PatContypeData;
import com.smict.person.data.PatientData;
import com.smict.person.data.PatientRecommendedData;
import com.smict.person.data.Pre_nameData;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.AddressModel;
import com.smict.person.model.CongenitalDiseaseModel;
import com.smict.person.model.FamilyModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.Pre_nameModel;
import com.smict.person.model.RecommendedModel;
import com.smict.person.model.TelephoneModel;
import com.smict.product.data.ProductData;
import com.smict.product.model.ProductModel;
import com.smict.product.model.ServiceModel;
import com.smict.treatment.action.TreatmentAction;
import com.sun.jersey.api.core.HttpRequestContext;

import ldc.util.DateUtil;
import ldc.util.GeneratePatientBranchID;
import ldc.util.Validate;

@SuppressWarnings("serial")
public class PatientAction extends ActionSupport {
	ServicePatientModel servicePatModel;
	AddressModel addrModel;
	PatientModel patModel;
	FamilyModel famModel;
	ContypeModel contModel;
	String birthdate_eng, birthdate_th, alertStatus, alertMessage;
	Map<String, String> map, mapTelehponetype, mapAddrType, mapPatientType, 
						mapRecomended, mapBrushTeeth, mapPregnant, mapReceiveDrug,
						mapTreatment, maphasHosOrDoctor, mapCongenital, mapStatusmarried,
						mapPrename;
	List<ProductModel> ListAllProduct;
	List<CongenitalDiseaseModel> ListAllCongen;
	List<String> listBeallergic, listCongen;
	List<PatientModel> patList = new ArrayList<PatientModel>();
	
	public String selectPatient(){
		return SUCCESS;
	}
	
	public String searchPatient(){
		HttpServletRequest request = ServletActionContext.getRequest();
		PatientData patData = new PatientData();
		Validate v = new Validate();
		
		if(v.Check_String_notnull_notempty(patModel.getSearchPat())){
			this.patList = patData.searchPatient(patModel);
			if(this.patList.size() > 0){
				request.setAttribute("alertMSG", null);
			}else{
				request.setAttribute("alertMSG", "ไม่พบข้อมูลคนไข้");
			}
		}else{
			request.setAttribute("alertMSG", "กรุณากรอกข้อมูลก่อนทำการค้นหา");
		}
		
		return SUCCESS;
	}

	
	/**
	 * Get user's HN from param.
	 * @author anubissmile
	 * @return String 
	 */
	private String userHN = "";
	
	/**
	 * Make patient session.
	 * @author anubissmile
	 * @return String | SUCCESS & INPUT
	 */
	public String makePatientSession(){

		if(new Validate().Check_String_notnull_notempty(userHN)){
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			PatientData patData = new PatientData();
			patModel = patData.selectPatientByHN(userHN);
			patModel.setHnFormat(GeneratePatientBranchID.hnFormat(patModel.getHn()));
			
			/**
			 * GET AGE BY BIRTH DATE.
			 */
			patModel.setAge(GeneratePatientBranchID.calculateAge(patModel.getBirth_date()));
			
			/**
			 * GET PATIENT'S PHONE NUMBER.
			 */
			patModel.setListTelModel(patData.getPatientPhone(userHN));

			/**
			 * GET PATIENT'S ADDRESS.
			 */
			patModel.setAddrModel(patData.getPatientAddr(userHN));
			
			/**
			 * GET PATIENT'S NEEDS.
			 */
			patModel.setPatneed_message(patData.getPatientNeed(userHN));
			
			
			servicePatModel = new ServicePatientModel(patModel);
			session.setAttribute("ServicePatientModel", servicePatModel);
		}
		return SUCCESS;
	}
	
	/**
	 * GETTER & SETTER ZONE
	 */
	public List<String> getListBeallergic() {
		return listBeallergic;
	}

	public void setListBeallergic(List<String> listBeallergic) {
		this.listBeallergic = listBeallergic;
	}

	public String getBirthdate_eng() {
		return birthdate_eng;
	}

	public void setBirthdate_eng(String birthdate_eng) {
		this.birthdate_eng = birthdate_eng;
	}

	public String getBirthdate_th() {
		return birthdate_th;
	}

	public void setBirthdate_th(String birthdate_th) {
		this.birthdate_th = birthdate_th;
	}

	public ServicePatientModel getServicePatModel() {
		return servicePatModel;
	}

	public void setServicePatModel(ServicePatientModel servicePatModel) {
		this.servicePatModel = servicePatModel;
	}
	
	public FamilyModel getFamModel() {
		return famModel;
	}

	public void setFamModel(FamilyModel famModel) {
		this.famModel = famModel;
	}

	public PatientModel getPatModel() {
		return patModel;
	}

	public void setPatModel(PatientModel patModel) {
		this.patModel = patModel;
	}
	
	public AddressModel getAddrModel() {
		return addrModel;
	}

	public void setAddrModel(AddressModel addrModel) {
		this.addrModel = addrModel;
	}
	
	public ContypeModel getContModel() {
		return contModel;
	}

	public void setContModel(ContypeModel contModel) {
		this.contModel = contModel;
	}

	public Map<String, String> getMapAddrType() {
		return mapAddrType;
	}

	public void setMapAddrType(Map<String, String> mapAddrType) {
		this.mapAddrType = mapAddrType;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Map<String, String> getMapTelehponetype() {
		return mapTelehponetype;
	}

	public void setMapTelehponetype(Map<String, String> mapTelehponetype) {
		this.mapTelehponetype = mapTelehponetype;
	}

	public List<ProductModel> getListAllProduct() {
		return ListAllProduct;
	}

	public void setListAllProduct(List<ProductModel> listAllProduct) {
		ListAllProduct = listAllProduct;
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
	
	public Map<String, String> getMapPatientType() {
		return mapPatientType;
	}

	public void setMapPatientType(Map<String, String> mapPatientType) {
		this.mapPatientType = mapPatientType;
	}

	public Map<String, String> getMapRecomended() {
		return mapRecomended;
	}

	public void setMapRecomended(Map<String, String> mapRecomended) {
		this.mapRecomended = mapRecomended;
	}

	public Map<String, String> getMapBrushTeeth() {
		return mapBrushTeeth;
	}

	public void setMapBrushTeeth(Map<String, String> mapBrushTeeth) {
		this.mapBrushTeeth = mapBrushTeeth;
	}

	public Map<String, String> getMapPregnant() {
		return mapPregnant;
	}

	public void setMapPregnant(Map<String, String> mapPregnant) {
		this.mapPregnant = mapPregnant;
	}

	public Map<String, String> getMapReceiveDrug() {
		return mapReceiveDrug;
	}

	public void setMapReceiveDrug(Map<String, String> mapReceiveDrug) {
		this.mapReceiveDrug = mapReceiveDrug;
	}

	public Map<String, String> getMapTreatment() {
		return mapTreatment;
	}

	public void setMapTreatment(Map<String, String> mapTreatment) {
		this.mapTreatment = mapTreatment;
	}

	public Map<String, String> getMaphasHosOrDoctor() {
		return maphasHosOrDoctor;
	}

	public void setMaphasHosOrDoctor(Map<String, String> maphasHosOrDoctor) {
		this.maphasHosOrDoctor = maphasHosOrDoctor;
	}

	public Map<String, String> getMapCongenital() {
		return mapCongenital;
	}

	public void setMapCongenital(Map<String, String> mapCongenital) {
		this.mapCongenital = mapCongenital;
	}

	public List<String> getListCongen() {
		return listCongen;
	}

	public void setListCongen(List<String> listCongen) {
		this.listCongen = listCongen;
	}

	public List<CongenitalDiseaseModel> getListAllCongen() {
		return ListAllCongen;
	}

	public void setListAllCongen(List<CongenitalDiseaseModel> listAllCongen) {
		ListAllCongen = listAllCongen;
	}

	public Map<String, String> getMapStatusmarried() {
		return mapStatusmarried;
	}

	public void setMapStatusmarried(Map<String, String> mapStatusmarried) {
		this.mapStatusmarried = mapStatusmarried;
	}

	public Map<String, String> getMapPrename() {
		return mapPrename;
	}

	public void setMapPrename(Map<String, String> mapPrename) {
		this.mapPrename = mapPrename;
	}

	public String beginAddPatient(){

		patModel = new PatientModel();
		addrModel = new AddressModel();
		
		/*map = new HashMap<String, String>();
		map.put("asd", "111");
		map.put("sdf", "222");*/
		
		return SUCCESS;
	}

	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		
		/*System.out.println(famModel.getTel_number());
		System.out.println(famModel.getTel_typeid());*/
		//PatientData patDB = new PatientData();
		//System.out.println(patModel.toString());
		//patModel.setHn("w8w5w5");
		
		List <ProductModel> be_allergicList = new ArrayList<ProductModel>();
		
		PatientData patData = new PatientData();
		AddressData addrData = new AddressData();
		TelephoneData telData = new TelephoneData();
		FamilyData famData = new FamilyData();
		Validate classvalidate = new Validate();
		PatContypeData aPatConData = new PatContypeData();
		FileData aFileData = new FileData();
		
		List <AddressModel> addrlist = addrData.buildListAddress(request);
		patModel.setAddr_id(addrData.add_multi_address(addrlist));
		
		List <TelephoneModel> tellist = telData.buildTelephoneList(request);
		patModel.setTel_id(telData.add_multi_telephone(tellist));


		
		
		String[] be_allergicParm = request.getParameterValues("be_allergic");
		if(be_allergicParm != null){
			
			for(String be_allergic : be_allergicParm){
				ProductModel prodModel = new ProductModel();
				prodModel.setProduct_id(Integer.parseInt(be_allergic));
				be_allergicList.add(prodModel);
			}
			
			patModel.setBe_allergic_id(patData.add_multi_BeAllergic(be_allergicList));
		}else{
			patModel.setBe_allergic_id(0);
		}
		
		patModel.setBirth_date(cvtdateToBirth_Date());
		
		patModel.setPatneed_id(patData.add_multi_Patneed(patModel));
		
		
		String[] congenitalprm = request.getParameterValues("congenital_disease");
		String congen_name_other = request.getParameter("other_congenital_disease");
		
		List<CongenitalDiseaseModel> congenList = new ArrayList<CongenitalDiseaseModel>();
		
		if(congenitalprm != null){
			
			for(String congendisease : congenitalprm){
				CongenitalDiseaseModel congenModel = new CongenitalDiseaseModel();
				
				String congenital_diseaseid = congendisease.split("_")[0], 
						congenital_name_th = congendisease.split("_")[1], 
						congenital_name_en = congendisease.split("_")[2];
				congenModel.setCongenital_id(Integer.parseInt(congenital_diseaseid));
				if(congenital_diseaseid.equals("100")){
					congenital_name_th = congen_name_other;
				}
				congenModel.setCongenital_name_th(congenital_name_th);
				congenModel.setCongenital_name_en(congenital_name_en);
				congenList.add(congenModel);
			}
			
			patModel.setPat_congenital_disease_id(patData.add_multi_congenID(congenList));
			
		}else{
			
			patModel.setPat_congenital_disease_id(0);
			
		}
		
		String forwardText ="";
		String hn = patData.Add_Patient(patModel, "1113", "NRT");
		patModel.setHn(hn);
		aFileData.addPatFileID(hn, "NRT");
		String family_id = request.getParameter("family_id");
		
		if(!hn.equals("")){
			//Create patient success
			
			if(classvalidate.Check_String_notnull_notempty(family_id)){
				//He has Family
				famModel.setFamily_id(Integer.parseInt(family_id));
				famModel.setFamily_user_status("2");
				
			}else{
				//He does't has Family and create family your self
				famModel.setFamily_id(famData.PlusOne(famData.Gethight_familyID()));
				famModel.setFamily_user_status("1");
				
			}
			
			famModel.setRef_user(hn);
			famModel.setUser_type_id(2);
			famData.add_family(famModel);
			
			famData.addFamilyTelephone(famModel);
			
			patModel.setFam_id(famModel.getFamily_id());
			
			aPatConData.addPatContype(hn, contModel.getSub_contact_id());
			
			HttpSession session = request.getSession();
			
			servicePatModel = new ServicePatientModel(patData.getPatModel_patient(patModel));
			
			session.setAttribute("ServicePatientModel", servicePatModel);
			
			
			
			forwardText ="success";
		}else{
			forwardText ="failed";
		}
		
	return forwardText;
	}
	
	public String window() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		PatientData patData = new PatientData();
		List<PatientModel> patientlist = patData.select_patient(patModel);
		request.setAttribute("patientlist", patientlist);
		
		return SUCCESS;
	}
	
	public String ShowPatientDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();  
		
		PatientData patData = new PatientData();
		PatientModel patModel = new PatientModel();
		
		
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		if(servicePatModel == null){
			alertStatus = "danger";
			alertMessage = "กรุณาเลือกคนไข้ก่อนทำรายการ";
			return "getCustomer";
		}
		
		patModel = new PatientModel(servicePatModel);
		
		
		TreatmentAction treatAction = new TreatmentAction();
		
		request.setAttribute("ServicePatientModel", servicePatModel);
		treatAction.setToothList(request);
		
		return SUCCESS;
	}
	
	public String entranchEditPatient(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		DateUtil dUtil = new DateUtil();
		TelephoneData tData = new TelephoneData();
		AddressData addrData = new AddressData();
		PatientData patData = new PatientData();
		ProductData proData = new ProductData();
		FamilyData famData = new FamilyData();
		PatientRecommendedData patRecomData = new PatientRecommendedData();
		CongenitalData congenData = new CongenitalData();
		
		patModel = new PatientModel(servicePatModel);
		
		if(!patModel.getBirth_date().equals("")){
			birthdate_th = dUtil.CvtYYYYMMDD_To_DDMMYYYY_PlusYear(patModel.getBirth_date(), "-", "-", 543);
			birthdate_eng = dUtil.CvtYYYYMMDD_To_DDMMYYYY(patModel.getBirth_date(), "-", "-");
		}
		
		mapTelehponetype = tData.get_MapTeltype();
		mapAddrType = addrData.get_MapAddrType();
		patModel.setPatneed_message(patData.getPatneedMessage(patModel.getPatneed_id()));
		mapRecomended = new HashMap<String, String>(patRecomData.getMapRecommended(new RecommendedModel()));
		mapPatientType = new HashMap<String, String>();
		mapPatientType.put("1", "จัดฟัน");
		mapPatientType.put("2", "ทั่วไป");
		
		
		
		//Beallergic Scope
		Iterator<ProductModel> iter = patModel.getBeallergic().iterator();
		listBeallergic = new ArrayList<String>();
		while (iter.hasNext()) {
			ProductModel productModel = (ProductModel) iter.next();
			listBeallergic.add(String.valueOf(productModel.getProduct_id()) ); 
		}
		ListAllProduct = proData.getListProductModel(new ProductModel());
		//Beallergic Scope		
		
		//Congen Scope
		patModel.setCongenList(congenData.getConginentalDisease(new CongenitalDiseaseModel(0,patModel.getPat_congenital_disease_id(),"","")));
		Iterator<CongenitalDiseaseModel> iterCongen = patModel.getCongenList().iterator();
		listCongen = new ArrayList<String>();
		while (iterCongen.hasNext()) {
			CongenitalDiseaseModel congenitalDiseaseModel = (CongenitalDiseaseModel) iterCongen.next();
			listCongen.add(String.valueOf(congenitalDiseaseModel.getCongenital_id()));
			
			if(congenitalDiseaseModel.getCongenital_id() == 100){
				patModel.setOther_congenital_disease(congenitalDiseaseModel.getCongenital_name_th());
			}
		}
		ListAllCongen = congenData.getMasterConginentalDisease(new CongenitalDiseaseModel());
		//Congen Scope
		
		//Family Scope
		famModel = new FamilyModel();
		famModel.setFamily_id(famData.getPatFamilyID(servicePatModel.getHn(), Integer.parseInt("2")));
		List<String> listFamTel = famData.getPatFamilyTel(famModel.getFamily_id());
		if(!listFamTel.isEmpty()){
			famModel.setTel_number(listFamTel.get(0));
			famModel.setTel_typename(listFamTel.get(1));
		}
		//Family Scope End
		PatientModel patModelForConfirmHistory = new PatientModel();
		patModelForConfirmHistory = patData.getPatConfirmHistory(patModel.getHn());
		
		patModel.setConfirm_brush_teeth(patModelForConfirmHistory.getConfirm_brush_teeth());
		patModel.setConfirm_pregnant(patModelForConfirmHistory.getConfirm_pregnant());
		patModel.setWeek_of_pregent(patModelForConfirmHistory.getWeek_of_pregent());
		patModel.setConfirm_now_receive_drug(patModelForConfirmHistory.getConfirm_now_receive_drug());
		patModel.setDrug_name(patModelForConfirmHistory.getDrug_name());
		patModel.setConfirm_now_treatment(patModelForConfirmHistory.getConfirm_now_treatment());
		patModel.setConfirm_hospital_doctor_now_treatment(patModelForConfirmHistory.getConfirm_hospital_doctor_now_treatment());
		patModel.setDoctor_hospital_name(patModelForConfirmHistory.getDoctor_hospital_name());
		patModel.setConfirm_congenital(patModelForConfirmHistory.getConfirm_congenital());
		
		setConfirmDefault();
		
		return SUCCESS;
	}
	
	public String editPatient(){
		HttpServletRequest request = ServletActionContext.getRequest(); 
		/*String emp_id = session.getAttribute("emp_id").toString();*/
		String emp_id = "11173";
		PatientData patData = new PatientData();
		FamilyData famDB = new FamilyData();
		PatientModel IdPatReferenceModel = new PatientModel();
		AddressData addrDB = new AddressData();
		
		IdPatReferenceModel = patData.getIdPatientReference(patModel.getHn());
		patModel.setTel_id(IdPatReferenceModel.getTel_id());
		patModel.setAddr_id(IdPatReferenceModel.getAddr_id());
		patModel.setBe_allergic_id(IdPatReferenceModel.getBe_allergic_id());
		patModel.setPatneed_id(IdPatReferenceModel.getPatneed_id());
		patModel.setPat_congenital_disease_id(IdPatReferenceModel.getPat_congenital_disease_id());
		
		famModel.setRef_user(patModel.getHn());
		famModel.setFamily_user_status("1");
		famModel.setUser_type_id(2);
		if(famDB.canJoinFamily(famModel)){
			famDB.updateFamilyByUser(famModel);
		}
		
		//Address
		addrDB.del_multi_address(patModel.getAddr_id());
		List <AddressModel> addrlist = addrDB.buildListAddress(request);
		if(addrlist.size() > 1){
			addrDB.add_multi_address(addrlist, patModel.getAddr_id(),1);
		}
		//Address
		
		
		//Telephone
		TelephoneData telData = new TelephoneData();
		telData.del_multi_telephone(patModel.getTel_id());
		List <TelephoneModel> tellist = telData.buildTelephoneList(request);
		if(tellist.size() > 1){
			telData.add_multi_telephone(tellist, patModel.getTel_id(), 1);
		}
		//Telephone
		
		CongenitalData congenData = new CongenitalData();
		congenData.removePatCongen(patModel);
		if(patModel.getCongenital_disease() != null && patModel.getCongenital_disease().length > 0){
			congenData.addMultiPatCongen(patModel);
		}
		
		patModel.setBirth_date(cvtdateToBirth_Date());
		
		patData.hasEditPatientDetail(patModel, emp_id);
		getServiceModelNewData(request);
		return SUCCESS;
	}
	
	public String cvtdateToBirth_Date(){
		String birthDateEn = birthdate_eng;
		String birthDateTh = birthdate_th;
		String BirthDate="";
		DateUtil dUtil = new DateUtil();
		if(!birthDateTh.equals("")){
			
			BirthDate = dUtil.CvtDDMMYYYY_To_YYYYMMDD_MinusYear(birthDateTh, "-", "-", 543);
		}else if(!birthDateEn.equals("")){
			
			BirthDate = dUtil.CvtDDMMYYYY_To_YYYYMMDD(birthDateEn, "-", "-");
		}
		
		return BirthDate;
	}
	
	public void getServiceModelNewData(HttpServletRequest request){
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		
		PatientData patData = new PatientData();
		FamilyData famDB = new FamilyData();
		patModel = new PatientModel();
		patModel.setHn(servicePatModel.getHn());
		patModel.setAddr_id(servicePatModel.getAddr_id());
		patModel.setTel_id(servicePatModel.getTel_id());
		patModel.setPatneed_id(servicePatModel.getPatneed_id());
		patModel.setBe_allergic_id(servicePatModel.getBe_allergic_id());
		patModel.setPat_congenital_disease_id(servicePatModel.getPat_congenital_disease_id());
		patModel.setFam_id(famDB.getPatFamilyID(servicePatModel.getHn(), Integer.parseInt("2")));
		servicePatModel = new ServicePatientModel(patData.getPatModel_patient(patModel));
		
		session.setAttribute("ServicePatientModel", servicePatModel);
	}
	
	public void setConfirmDefault(){
		
		mapBrushTeeth = new HashMap<String, String>();
		mapBrushTeeth.put("1","ไม่ใช่");
		mapBrushTeeth.put("2","ใช่");
		
		mapPregnant = new HashMap<String, String>();
		mapPregnant.put("1","ไม่ใช่");
		mapPregnant.put("2","ใช่");
		
		mapReceiveDrug = new HashMap<String, String>();
		mapReceiveDrug.put("1","ไม่มี");
		mapReceiveDrug.put("2","มี");
		
		mapTreatment = new HashMap<String, String>();
		mapTreatment.put("1","ไม่ใช่");
		mapTreatment.put("2","ใช่");
		
		maphasHosOrDoctor = new HashMap<String, String>();
		maphasHosOrDoctor.put("1","ไม่มี");
		maphasHosOrDoctor.put("2","มี");
		
		mapCongenital = new HashMap<String, String>();
		mapCongenital.put("1","ไม่มี");
		mapCongenital.put("2","ไม่ทราบ");
		mapCongenital.put("3","ทราบ");
		
		mapStatusmarried = new HashMap<String, String>();
		mapStatusmarried.put("1", "โสด");
		mapStatusmarried.put("2", "แต่งงาน");
		mapStatusmarried.put("3", "หย่าร้าง");
		
		mapPrename = new HashMap<String, String>(); 
		Pre_nameData PreNameData = new Pre_nameData();
		List<Pre_nameModel> prenameModel;
		try {
			prenameModel = PreNameData.select_pre_name("", "", "");
			for(Pre_nameModel pnmd : prenameModel){
				mapPrename.put(pnmd.getPre_name_id(), pnmd.getPre_name_th());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*, mapPregnant, mapReceiveDrug,
		mapTreatment, maphasHosOrDoctor, mapCongenital*/
	}

	public List<PatientModel> getPatList() {
		return patList;
	}

	public void setPatList(List<PatientModel> patList) {
		this.patList = patList;
	}

	public String getUserHN() {
		return userHN;
	}

	public void setUserHN(String userHN) {
		this.userHN = userHN;
	}
	
	
}
