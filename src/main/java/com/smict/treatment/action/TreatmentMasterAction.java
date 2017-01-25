package com.smict.treatment.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest; 
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ToothModel;
import com.smict.all.model.TreatmentMasterModel;
import com.smict.person.data.BrandData;
import com.smict.person.data.DoctorData;
import com.smict.person.model.BrandModel;
import com.smict.product.data.LabModeDB;
import com.smict.treatment.data.ToothMasterData;
import com.smict.treatment.data.TreatmentMasterData;  



public class TreatmentMasterAction extends ActionSupport{
	
	TreatmentMasterModel treatmentMasterModel;  
	ToothModel toothModel;
	public ToothModel getToothModel() {
		return toothModel;
	}
	public void setToothModel(ToothModel toothModel) {
		this.toothModel = toothModel;
	}
	public TreatmentMasterModel getTreatmentMasterModel() {
		return treatmentMasterModel;
	}
	public void setTreatmentMasterModel(TreatmentMasterModel treatmentMasterModel) {
		this.treatmentMasterModel = treatmentMasterModel;
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		 
		LabModeDB labModeDB = new LabModeDB();
		List labmodelist = labModeDB.Get_LabModeList("", "");
		request.setAttribute("labmodelist", labmodelist);
		
		DoctorData docData = new DoctorData();
		List docList = docData.Get_DoctorList(null);
		request.setAttribute("doctorList", docList); 
		
		ToothMasterData toothData= new ToothMasterData();
		List<ToothModel> toothPicList = toothData.select_tooth_pic();
		request.setAttribute("toothPicList", toothPicList);
		
		List<ToothModel> toothListUp = toothData.select_tooth_list_arch("upper");
		request.setAttribute("toothListUp", toothListUp); 
		
		List<ToothModel> toothListLow = toothData.select_tooth_list_arch("lower");
		request.setAttribute("toothListLow", toothListLow); 
		return SUCCESS;
	}
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		TreatmentMasterData treatmentdb = new TreatmentMasterData(); 
		  
		String save 	= 	request.getParameter("save");
		String rtt = ERROR;
		if(save!=null){ 
			
		/*	String treatment_code 			= treatmentMasterModel.getTreatment_code();
			String treatment_nameth 		= treatmentMasterModel.getTreatment_nameth();
			String treatment_nameen 		= treatmentMasterModel.getTreatment_nameen();
			int brand_id 					= treatmentMasterModel.getBrand_id();
			String doctor_revenue_sharing 	= treatmentMasterModel.getDoctor_revenue_sharing();
			int lab_percent 				= treatmentMasterModel.getLab_percent();*/
			String autohomecall 			= "2"; // no auto
			String type_tooth="0",type_surcace="0",type_mouth="0",type_quadrant="0",
					type_sextant="0",type_arch="0",type_tooth_range="0";
			if(treatmentMasterModel.getAutohomecall()==null) {
				 treatmentMasterModel.setAutohomecall(autohomecall);
			} // auto = 1
			if(toothModel.getType_tooth()==null) {
				toothModel.setType_tooth(type_tooth);
			}
			if(toothModel.getType_surface()==null) {
				toothModel.setType_surface(type_surcace);
			}
			if(toothModel.getType_mouth()==null) {
				toothModel.setType_mouth(type_mouth);
			}
			if(toothModel.getType_quadrant()==null) {
				toothModel.setType_quadrant(type_quadrant);
			}
			if(toothModel.getType_sextant()==null) {
				toothModel.setType_sextant(type_sextant);
			}
			if(toothModel.getType_arch()==null) {
				toothModel.setType_arch(type_arch);
			}
			if(toothModel.getType_tooth_rang()==null) {
				toothModel.setType_tooth_rang(type_tooth_range);
			}
			/*String recall_typeid 			= treatmentMasterModel.getRecall_typeid();
			String treatment_type 			= treatmentMasterModel.getTreatment_type();
			String price_standard 			= treatmentMasterModel.getPrice_standard();
			String price_benefit 			= treatmentMasterModel.getPrice_benefit();
			String tooth_pic_code           = treatmentMasterModel.getTooth_pic_code();
			String treatment_group_code     = treatmentMasterModel.getTreatment_group_code();
			String treatment_mode			= treatmentMasterModel.getSet_treatmant();*/
			if(request.getParameterValues("doctorid")!=null){ 
				String[] doctorid 	= request.getParameterValues("doctorid");
				
				for(int i=0; i<doctorid.length; i++){ 
					
					treatmentdb.AddTreatmentDoctor(treatmentMasterModel.getTreatment_code(), doctorid[i]);
				} 
					
			}
			if(request.getParameterValues("arProduct")!=null){ 
				String[] arProduct			= request.getParameterValues("arProduct");
				String[] product_id 		= request.getParameterValues("product_id");
				String[] product_transfer 	= request.getParameterValues("product_transfer");
				String[] product_free 		= request.getParameterValues("product_free");
				
				for(int a=0,b=0; a<arProduct.length; a++){ 
					b = Integer.parseInt(arProduct[a]);
					
					if(product_transfer[b]==null)  	product_transfer[b] = "0";
					if(product_free[b]==null)  		product_free[b] = "0";
					
					int treatment_product_id = treatmentdb.select_treatment_product_id();
					
					treatmentdb.AddTreatmentProductYa(treatment_product_id, treatmentMasterModel.getTreatment_code(), product_id[b], 
							Integer.parseInt(product_transfer[b]), Integer.parseInt(product_free[b]));
				} 
					
			}
			
			
			int rt = treatmentdb.AddTreatmentMaster(treatmentMasterModel,toothModel);
			
			if(rt==0){
				request.setAttribute("status_error", "เพิ่มข้อมูลไม่สำเร็จ");
				rtt = ERROR;
			}else{
				rtt = SUCCESS;
				request.setAttribute("status_success", "เพิ่มข้อมูลสำเร็จ");
			}
		}  
		
		LabModeDB labModeDB = new LabModeDB();
		List labmodelist = labModeDB.Get_LabModeList("", "");
		request.setAttribute("labmodelist", labmodelist);
		
		DoctorData docData = new DoctorData();
		List docList = docData.Get_DoctorList(null);
		request.setAttribute("doctorList", docList); 
		
		ToothMasterData toothData= new ToothMasterData();
		List<ToothModel> toothPicList = toothData.select_tooth_pic();
		request.setAttribute("toothPicList", toothPicList);
		
		List<ToothModel> toothListUp = toothData.select_tooth_list_arch("upper");
		request.setAttribute("toothListUp", toothListUp); 
		
		List<ToothModel> toothListLow = toothData.select_tooth_list_arch("lower");
		request.setAttribute("toothListLow", toothListLow); 
		return rtt;
	} 
	
}
