package com.smict.person.action;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ContypeModel;
import com.smict.all.model.ServicePatientModel;
import com.smict.auth.AuthModel;
import com.smict.document.data.DocumentData;
import com.smict.document.model.DocumentModel;
import com.smict.person.data.AddressData;
import com.smict.person.data.BranchData;
import com.smict.person.data.CongenitalData;
import com.smict.person.data.FamilyData;
import com.smict.person.data.PatContypeData;
import com.smict.person.data.PatientData;
import com.smict.person.data.PatientRecommendedData;
import com.smict.person.data.Pre_nameData;
import com.smict.person.data.TelephoneData;
import com.smict.person.model.AddressModel;
import com.smict.person.model.CongenitalDiseaseModel;
import com.smict.person.model.FamilyModel;
import com.smict.person.model.PatientFileIdModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.Pre_nameModel;
import com.smict.person.model.RecommendedModel;
import com.smict.person.model.TelephoneModel;
import com.smict.product.data.ProductData;
import com.smict.product.model.ProductModel;
import com.smict.treatment.action.TreatmentAction;

import ldc.util.Auth;
import ldc.util.DateUtil;
import ldc.util.Encrypted;
import ldc.util.GeneratePatientBranchID;
import ldc.util.Servlet;
import ldc.util.Storage;
import ldc.util.Validate;

@SuppressWarnings("serial")
public class PatientAction extends ActionSupport {
	ServicePatientModel servicePatModel;
	AddressModel addrModel;
	PatientModel patModel;
	FamilyModel famModel;
	ContypeModel contModel;
	AuthModel authModel;
	List<PatientFileIdModel> patBranchHnList;
	String birthdate_eng, birthdate_th, alertStatus, alertMessage;
	Map<String, String> map, mapTelehponetype, mapAddrType, mapPatientType, 
						mapRecomended, mapBrushTeeth, mapPregnant, mapReceiveDrug,
						mapTreatment, maphasHosOrDoctor, mapCongenital, mapStatusmarried,
						mapPrename;
	List<ProductModel> ListAllProduct;
	List<CongenitalDiseaseModel> ListAllCongen;
	List<String> listBeallergic, listCongen ,listdocuneed;
	List<PatientModel> patList = new ArrayList<PatientModel>();
	List<DocumentModel> docuList;
	List<FamilyModel> familyList;
	
	/**
	 * FILE UPLOADING
	 */
	private File picProfile;
	private String picProfileContentType;
	private String picProfileFileName;
	
	public List<PatientModel> beallergiclist;
	
	/**
	 * CONSTRUCTOR
	 */
	public PatientAction(){
		Auth.authCheck(false);
		
		/**
		 * FETCH SERVICE PATIENT MODEL FROM SESSION.
		 */
		makeServicePatModel();
	}

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
	 * @author anubissmile
	 * @return String
	 */
	public String family(){
		FamilyData famDB = new FamilyData();
		familyList = famDB.getFamilyListByHN(servicePatModel.getHn());
		return SUCCESS;
	}
	
	/**
	 * find person to add into familylist.
	 * @author anubissmile
	 * @return String
	 */
	public String findFamily(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String search = request.getParameter("search");
		FamilyData famDB = new FamilyData();
		patModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		familyList = famDB.findAnyPerson(search, patModel.getHn());
		return SUCCESS;
	}
	
	public String delFamily(){
		FamilyData famDB = new FamilyData();
		famDB.deleteFamilyUser(famModel);
		return SUCCESS;
	}
	
	public String addFamily(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		patModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		
		String[] famAddRequest = request.getParameterValues("famIndex");
		FamilyData famDB = new FamilyData();
		for(String famDetail : famAddRequest){
			String[] splitFamDetail = famDetail.split("-");
			
			String famMemberIdent = splitFamDetail[0];
			int famTypeId = Integer.parseInt(splitFamDetail[1]);
			famDB.add_family(patModel.getHn(), famMemberIdent, famTypeId);
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
	 * Get patient's branch hn list.
	 * @author anubissmile
	 * @return String
	 */
	public String getBranchHNList(){
		/**
		 * FETCH PATIENT CAPITAL HN
		 */
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		ServicePatientModel pModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
		
		/**
		 * GET BRANCH HN LIST.
		 */
		PatientData patData = new PatientData();
		setPatBranchHnList((List<PatientFileIdModel>) patData.getBranchHNList(pModel.getHn()));
		return SUCCESS;
	}
		
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
			BranchData branchData = new BranchData();
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
			 * GET PATIENT'S EMERGENCY PHONE NUMBER.
			 */
			TelephoneData tData = new TelephoneData();
			List<TelephoneModel> telList = tData.getEmergencyTelByHN(patModel.getHn());
			for(TelephoneModel tModel : telList){
				patModel.setEmTellID(tModel.getTel_id());
				patModel.setEmTellRelevantPerson(tModel.getRelevant_person());
				patModel.setEmTellNumber(tModel.getTel_number());
				patModel.setEmRelative(tModel.getTel_relative());
			}
			
			/**
			 * GET PATIENT'S ADDRESS.
			 */
			patModel.setAddrModel(patData.getPatientAddr(userHN));
			
			/**
			 * GET PATIENT'S NEEDS.
			 */
			patModel.setPatneed_message(patData.getPatientNeed(userHN));
			
			/**
			 * GET PATIENT'S ALLERGIC.
			 */
			patModel.setBeallergic(patData.getListBeallergic(userHN));

			/**
			 * GET Document Need.
			 */
			patModel.setDocumentneed(patData.getListDocument(userHN));
			PatContypeData aPatContypeData = new PatContypeData();
			patModel.setContypeList(aPatContypeData.getListContype(userHN, 0));
			/**
			 * GET PATIENT'S CONGENITAL DISEASE.
			 */
			patModel.setCongenital_disease(patData.getPatientCongenitalDisease(userHN));
			
			List<CongenitalDiseaseModel> congenList = new ArrayList<CongenitalDiseaseModel>();
			
			for(String name_th :patModel.getCongenital_disease()){
				CongenitalDiseaseModel conMo = new CongenitalDiseaseModel();
				conMo.setCongenital_name_th(name_th);
				congenList.add(conMo);
			}
			patModel.setCongenList(congenList);
			
			/**
			 * GET PATIENT'S CONTYPE.
			 */
			PatContypeData patContypeData = new PatContypeData();
			patModel.setContypeList(patContypeData.getListContype(userHN, 1));
			
			/**
			 * GET BRANCH HN CODE.
			 */
			@SuppressWarnings("unchecked")
			HashMap<String, AuthModel> userSession = (HashMap<String, AuthModel>)session.getAttribute("userSession");
			AuthModel authModel = userSession.get("userEmployee");
			String branchID = branchData.getBranchHNExist(patModel.getHn(), authModel.getBranchID());
			if(branchID != null){
				patModel.setHnBranch(branchID);
			}
			
			servicePatModel = new ServicePatientModel(patModel);	
			session.setAttribute("ServicePatientModel", servicePatModel);
			session.setAttribute("patBranchHnList", patBranchHnList);
		}
		return SUCCESS;
	}

	/**
	 * Generating patient's branch hn from patient session.
	 * @author anubissmile
	 * @return String
	 */
	public String generateHNBranch(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		HashMap<String, String> branchCode = new HashMap<String, String>();
		String[] resultID = null;
		servicePatModel = (ServicePatientModel)session.getAttribute("ServicePatientModel");
		
		/**
		 * FETCH BRANCH CODE & CONCAT TO IN FORMAT 431-6-CMI (branchID-nextNumber-branchCode)
		 */
		@SuppressWarnings("unchecked")
		HashMap<String, AuthModel> userSession = (HashMap<String, AuthModel>)session.getAttribute("userSession");
		authModel = userSession.get("userEmployee");
		BranchData branchData = new BranchData();
		branchCode = branchData.getBranchCode(authModel.getBranchID());
		
		String branchID = branchData.getBranchHNExist(servicePatModel.getHn(), branchCode.get("branch_id"));
		if(branchID == null){
			/**
			 * GENERATE NEW BRANCH ID
			 */
			GeneratePatientBranchID genBranchID = new GeneratePatientBranchID();
			try {
				genBranchID.generateBranchHN(branchCode.get("branch_code") + "-" + branchCode.get("next_number") + "-" + branchCode.get("branch_id"));
				resultID = genBranchID.getResultID();
				/*THEN RETURN [431-60-0000006, 7, CMI]*/
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			/**
			 * UPDATE NEXT NUMBER.
			 */
			branchData.updateBranchNextNumber(Integer.parseInt(resultID[1]), resultID[2]);
			
			/**
			 * INSERT PATIENT'S BRANCH HN CODE.
			 */
			branchData.insertBranchHN(servicePatModel.getHn(), resultID[0], resultID[2]);

			/**
			 * SET SESSION.
			 */
			servicePatModel.setHnBranch(resultID[0]);
			session.setAttribute("ServicePatientModel", servicePatModel);
		}else{
			servicePatModel.setHnBranch(branchID);
			session.setAttribute("ServicePatientModel", servicePatModel);
		}
		
		
		return SUCCESS;
	}
	
	

	public String beginAddPatient(){

		patModel = new PatientModel();
		addrModel = new AddressModel();
		DocumentData docuData = new DocumentData();
		setDocuList(docuData.getListDocument());
		
		return SUCCESS;
	}

	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		
//		List <ProductModel> be_allergicList = new ArrayList<ProductModel>(); // Deprecate field.
		
		PatientData patData = new PatientData();
		AddressData addrData = new AddressData();
		TelephoneData telData = new TelephoneData();
		FamilyData famData = new FamilyData();
		Validate classvalidate = new Validate();
		PatContypeData aPatConData = new PatContypeData();
//		FileData aFileData = new FileData(); //unused objects
		
		List <AddressModel> addrlist = addrData.buildListAddress(request);
		patModel.setAddr_id(addrData.add_multi_address(addrlist));
		
		List <TelephoneModel> tellist = telData.buildTelephoneList(request);
		patModel.setTel_id(telData.add_multi_telephone(tellist));

		patModel.setBirth_date(cvtdateToBirth_Date());
		
		//patneed
		patModel.setPatneed_id(patData.add_multi_Patneed(patModel));
		patData.Delete_patneedIsEmpty(patModel);
		patData.UpdateRunning_Patneed_id(patModel);
		//patneed end
		String[] congenitalprm = request.getParameterValues("congenital_disease");
		String congen_name_other = request.getParameter("other_congenital_disease");
		
		/**
		 * UPLOAD PICTURE FILE.
		 */
		if(getPicProfileFileName() != null){
			String time = new DateUtil().curTime();
			String fName = new Encrypted().encrypt(patModel.getFirstname_en() + "-" + patModel.getLastname_en() + "-" + time).replaceAll("[-+.^:=/\\,]","");
			patModel.setProfile_pic(
					new Storage().file(getPicProfile(), getPicProfileContentType(), getPicProfileFileName())
						.storeAs("../Document/picture/profile/", fName)
						.getDestPath()
			);
		}
		
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
			patData.Update_Running_CongenID();
			
		}else{
			patModel.setPat_congenital_disease_id(patData.add_multi_congenID(congenList));
			patData.Update_Running_CongenID();
			patData.Delete_CongenIsEmpty(patModel);
			
		}
		
		String forwardText ="";
		String hn = patData.Add_Patient(patModel, Auth.user().getEmpUsr(), Auth.user().getBranchID());
		patModel.setHn(hn);
		if(patModel.getBe_allergic()!=null){
			if(patModel.getBe_allergic().length>0){
				String Other_beallergic = request.getParameter("other_beallergic");
				for(String beallergic : patModel.getBe_allergic()){
					String product_id = beallergic.split("_")[0],
							beallergic_name_th = beallergic.split("_")[1], 
							beallergic_name_en = beallergic.split("_")[2];
					if(product_id.equals("1")){
						beallergic_name_th = Other_beallergic;
					}
					patModel.setProduct_id(product_id);
					patModel.setBeallergic_name_th(beallergic_name_th);
					patModel.setBeallergic_name_en(beallergic_name_en);
					patData.addmutiallergic(patModel);
					
				}
			}
		}
		//Document_need
		if(patModel.getDocument_need()!=null){			
			patData.document_need_addmuti(patModel);
		}
		//Document_need end
		
		if(!hn.equals("")){
			//Create patient success
			aPatConData.addPatContype(hn, contModel.getSub_contact_id());
			
			HttpSession session = request.getSession();
			
			servicePatModel = new ServicePatientModel(patData.getPatModel_patient(patModel));
			
			session.setAttribute("ServicePatientModel", servicePatModel);
			
			
			new Servlet().redirect(request, response, "generate-hn-branch");
			forwardText ="success";
		}else{
			forwardText ="failed";
		}
		
		/**
		 * Set teeth picture list.
		 */
		TreatmentAction treatAction = new TreatmentAction();
		treatAction.setToothList(request);
		
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
		DocumentData docuData = new DocumentData();
		PatientRecommendedData patRecomData = new PatientRecommendedData();
		CongenitalData congenData = new CongenitalData();

		patModel = new PatientModel(servicePatModel);
		
		/**
		 * GET EMERGENCY CALL NUMBER.
		 */
		List<TelephoneModel> telList = tData.getEmergencyTelByHN(patModel.getHn());
		for(TelephoneModel tModel : telList){
			patModel.setEmTellID(tModel.getTel_id());
			patModel.setEmTellRelevantPerson(tModel.getRelevant_person());
			patModel.setEmTellNumber(tModel.getTel_number());
			patModel.setEmRelative(tModel.getTel_relative());
		}
		
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
			listBeallergic.add(String.valueOf(productModel.getProduct_id()));
			if(productModel.getProduct_id() == 1){
				patModel.setOther_beallergic_name_th(productModel.getBeallergic_name_th());
			}
		}
		ListAllProduct = proData.getListProductModel(new ProductModel());
	//	setListAllProduct(patData.getModelListBeallergic(patModel));
		//Beallergic Scope		
		//Document need
		Iterator<DocumentModel> itera = patModel.getDocumentneed().iterator();
		listdocuneed = new ArrayList<String>();
		while(itera.hasNext()){
			DocumentModel docu = (DocumentModel) itera.next();
			listdocuneed.add(String.valueOf(docu.getDocument_id()));
		}
		docuList = docuData.getListDocumentneed(new DocumentModel()); 
		//Document Scope
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
	
	public String editPatient() throws IOException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		/*String emp_id = session.getAttribute("emp_id").toString();*/
		String emp_id = Auth.user().getEmpUsr();
		PatientData patData = new PatientData();
		FamilyData famDB = new FamilyData();
		PatientModel IdPatReferenceModel = new PatientModel();
		AddressData addrDB = new AddressData();
		
		IdPatReferenceModel = patData.getIdPatientReference(patModel.getHn());
		
		/**
		 * UPLOAD PICTURE FILE.
		 */
		if(getPicProfileFileName() != null){
			String time = new DateUtil().curTime();
			String fName = new Encrypted().encrypt(patModel.getFirstname_en() + "-" + patModel.getLastname_en() + "-" + time).replaceAll("[-+.^:=/\\,]","");
			patModel.setProfile_pic(
					new Storage().file(getPicProfile(), getPicProfileContentType(), getPicProfileFileName())
						.storeAs("../Document/picture/profile/", fName)
						.getDestPath()
			);
			
		}
		
		/**
		 * DELETE OLD FILE PICTURE WHEN HAVE NEW PROFILE PICTURE.
		 */
		if(!patModel.getProfile_pic().equals(IdPatReferenceModel.getProfile_pic())){
			// Delete old file
			new Storage().delete(IdPatReferenceModel.getProfile_pic());
		}
		
		patModel.setAddr_id(IdPatReferenceModel.getAddr_id());
		patModel.setPatneed_id(IdPatReferenceModel.getPatneed_id());
		patModel.setPat_congenital_disease_id(IdPatReferenceModel.getPat_congenital_disease_id());
		//be_allergic
		if(patModel.getBe_allergic()!=null){
	//	if(patModel.getBe_allergic().length>0){
		patData.allergicupdate(patModel);
			
			for(String beallergic : patModel.getBe_allergic()){
				
				if(patData.isNewAllergic(patModel, beallergic)){
					patData.addIsNewAllergic(patModel,beallergic);
				}else{
					if(beallergic.equals("1")){
						patData.addIsNewAllergicUpdate(patModel);
					}
				}
				
			}
	//	}
		}
		//end_be_allergic
		//document_need
		if(patModel.getDocument_need()!=null){
				patData.documentNeedDel(patModel);
				for(String docuNeed : patModel.getDocument_need()){
					
					if(patData.isNewDocuNeed(patModel, docuNeed)){
						patData.IsNewAddDocuNeed(patModel, docuNeed);
					}
				}
			
		}		
		//document_need end
		//Address
		addrDB.del_multi_address(patModel.getAddr_id());
		List <AddressModel> addrlist = addrDB.buildListAddress(request);
		if(addrlist.size() > 1){
			addrDB.add_multi_address(addrlist, patModel.getAddr_id(),1);
		}
		//Address
		
		
		//Telephone
		TelephoneData telData = new TelephoneData();
		telData.del_multi_telephone(IdPatReferenceModel.getTel_id());
		List <TelephoneModel> tellist = telData.buildTelephoneList(request);
		if(tellist.size() > 1){
			telData.add_multi_telephone(tellist, IdPatReferenceModel.getTel_id(), 1);
		}
		//Telephone
		
		
		//Patneed
		patData.Delete_patneed(patModel);
		patData.addmulti_Patneed(patModel);
		//Patneed
		CongenitalData congenData = new CongenitalData();
		congenData.removePatCongen(patModel);
		if(patModel.getCongenital_disease() != null && patModel.getCongenital_disease().length > 0){
			congenData.addMultiPatCongen(patModel);
		}
		
		patModel.setBirth_date(cvtdateToBirth_Date());
		patData.hasEditPatientDetail(patModel, emp_id);
//		getServiceModelNewData(request);
		new Servlet().redirect(request, response, "selectPatient/view/" + patModel.getHn());
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
				String Prename =  pnmd.getPre_name_th()+"/"+pnmd.getPre_name_en();
				mapPrename.put(pnmd.getPre_name_id(), Prename);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Fetch service patient model from session.
	 * @author anubissmile
	 * @return void
	 */
	public void makeServicePatModel(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		servicePatModel = (ServicePatientModel) session.getAttribute("ServicePatientModel");
	}

	/**
	 * GETTER & SETTER ZONE
	 */
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

	public AuthModel getAuthModel() {
		return authModel;
	}

	public void setAuthModel(AuthModel authModel) {
		this.authModel = authModel;
	}

	public File getPicProfile() {
		return picProfile;
	}

	public void setPicProfile(File picProfile) {
		this.picProfile = picProfile;
	}

	public String getPicProfileContentType() {
		return picProfileContentType;
	}

	public void setPicProfileContentType(String picProfileContentType) {
		this.picProfileContentType = picProfileContentType;
	}

	public String getPicProfileFileName() {
		return picProfileFileName;
	}

	public void setPicProfileFileName(String picProfileFileName) {
		this.picProfileFileName = picProfileFileName;
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

	public List<PatientModel> getBeallergiclist() {
		return beallergiclist;
	}

	public void setBeallergiclist(List<PatientModel> beallergiclist) {
		this.beallergiclist = beallergiclist;
	}
	public List<DocumentModel> getDocuList() {
		return docuList;
	}

	public void setDocuList(List<DocumentModel> docuList) {
		this.docuList = docuList;
	}

	public List<String> getListdocuneed() {
		return listdocuneed;
	}

	public void setListdocuneed(List<String> listdocuneed) {
		this.listdocuneed = listdocuneed;
	}
	public List<PatientFileIdModel> getPatBranchHnList() {
		return patBranchHnList;
	}

	public void setPatBranchHnList(List<PatientFileIdModel> patBranchHnList) {
		this.patBranchHnList = patBranchHnList;
	}

	public List<FamilyModel> getFamilyList() {
		return familyList;
	}

	public void setFamilyList(List<FamilyModel> familyList) {
		this.familyList = familyList;
	}

}
