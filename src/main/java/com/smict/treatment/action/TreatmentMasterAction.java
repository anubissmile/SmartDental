package com.smict.treatment.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ToothModel;
import com.smict.all.model.TreatmentMasterModel;
import com.smict.person.data.BrandData;
import com.smict.person.data.DoctorData;
import com.smict.person.model.BrandModel;
import com.smict.product.data.LabModeDB;
import com.smict.treatment.data.ToothMasterData;
import com.smict.treatment.data.TreatmentData;
import com.smict.treatment.data.TreatmentMasterData;
import com.smict.treatment.model.TreatmentModel;
import ldc.util.Auth;  



@SuppressWarnings("serial")
public class TreatmentMasterAction extends ActionSupport{
	
	private TreatmentMasterModel treatmentMasterModel;  
	private TreatmentModel treatmentModel;
	private ToothModel toothModel;
	private List<BrandModel> brandList;
	private HashMap<String, String> brandMap;
	private List<TreatmentModel> treatmentList;
	private HashMap<String, String> treatmentMap;
	private HashMap<String, String> toothPicMap;
	private HashMap<String, String> toothTypeMap;
	
	
	/**
	 * CONSTRUCTOR
	 */
	public TreatmentMasterAction(){
		Auth.authCheck(false);
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		BrandData brandData = new BrandData();
		TreatmentData treatmentData = new TreatmentData();
		
		/**
		 * Fetch brand.
		 */
		brandList = brandData.chunkBrand();
		brandMap = new HashMap<String, String>();
		for(BrandModel bModel : brandList){
			brandMap.put(
				String.valueOf(bModel.getBrand_id()),
				bModel.getBrand_name()
			);
		}
		
		/**
		 * Fetch treatment group
		 */
		treatmentList = treatmentData.getTreatmentGroup(0);
		treatmentMap = new HashMap<String, String>();
		for(TreatmentModel treatModel : treatmentList){
			treatmentMap.put(
				String.valueOf(treatModel.getTreatmentGroupID()),
				treatModel.getTreatmentGroupName()
			);
		}
		
		/**
		 * Fetch tooth picture.
		 */
		treatmentList = treatmentData.getToothPicture("");
		toothPicMap = new HashMap<String, String>();
		for(TreatmentModel tModel : treatmentList){
			toothPicMap.put(
				tModel.getToothPicCode(),
				tModel.getToothPicName()
			);
		}
		
		/**
		 * Fetch tooth type.
		 */
		treatmentList = treatmentData.getToothType(0);
		
		
		/**
		 * Treatment format & Tooth picture.
		 */
		ToothMasterData toothData= new ToothMasterData();
		List<ToothModel> toothPicList = toothData.select_tooth_pic();
		request.setAttribute("toothPicList", toothPicList);

		List<ToothModel> toothListUp = toothData.select_tooth_list_arch("upper");
		request.setAttribute("toothListUp", toothListUp); 
		
		List<ToothModel> toothListLow = toothData.select_tooth_list_arch("lower");
		request.setAttribute("toothListLow", toothListLow); 
		
		
		return SUCCESS;
	}
	
	
	/**
	 * Get treatment category by group id from ajax request.
	 * @author anubissmile
	 * @return String Action result.
	 */
	public String ajaxTreatmentCategory(){
		TreatmentData treatmentData = new TreatmentData();
		JSONArray jsonArr = new JSONArray();
		List<TreatmentModel> treatList = new ArrayList<TreatmentModel>();
		
		/**
		 * Get treatment category
		 */
		if(String.valueOf(treatmentModel.getTreatmentGroupID()) != null && treatmentModel.getTreatmentGroupID() > 0){
			treatList = treatmentData.getTreatmentCategory(treatmentModel.getTreatmentGroupID());
			for(TreatmentModel tModel : treatList){
				JSONObject jsonObj = new JSONObject();
				/*tModel.setTreatmentCategoryID(rs.getInt("category_id"));
				tModel.setTreatmentCategoryName(rs.getString("category_name"));
				tModel.setTreatmentCategoryCode(rs.getString("category_code"));
				tModel.setTreatmentGroupID(rs.getInt("group_id"));
				tModel.setTreatmentGroupCode(rs.getString("group_code"));
				tModel.setTreatmentGroupName(rs.getString("group_name"));*/
				try {
					jsonObj.put("category_id", tModel.getTreatmentCategoryID())
						.put("category_name", tModel.getTreatmentCategoryName())
						.put("category_code", tModel.getTreatmentCategoryCode())
						.put("group_id", tModel.getTreatmentGroupID())
						.put("group_code", tModel.getTreatmentGroupCode())
						.put("group_name", tModel.getTreatmentGroupName());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jsonArr.put(jsonObj);
			}
			
			/**
			 * Return the response as JSON.
			 */
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				response.getWriter().write(jsonArr.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
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
	
	
	/**
	 * GETTER & SETTER
	 */
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

	public List<BrandModel> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<BrandModel> brandList) {
		this.brandList = brandList;
	}

	public HashMap<String, String> getBrandMap() {
		return brandMap;
	}

	public void setBrandMap(HashMap<String, String> brandMap) {
		this.brandMap = brandMap;
	}

	public List<TreatmentModel> getTreatmentList() {
		return treatmentList;
	}

	public void setTreatmentList(List<TreatmentModel> treatmentList) {
		this.treatmentList = treatmentList;
	}

	public HashMap<String, String> getTreatmentMap() {
		return treatmentMap;
	}

	public void setTreatmentMap(HashMap<String, String> treatmentMap) {
		this.treatmentMap = treatmentMap;
	}

	public TreatmentModel getTreatmentModel() {
		return treatmentModel;
	}

	public void setTreatmentModel(TreatmentModel treatmentModel) {
		this.treatmentModel = treatmentModel;
	}

	public HashMap<String, String> getToothPicMap() {
		return toothPicMap;
	}

	public void setToothPicMap(HashMap<String, String> toothPicMap) {
		this.toothPicMap = toothPicMap;
	}

	public HashMap<String, String> getToothTypeMap() {
		return toothTypeMap;
	}

	public void setToothTypeMap(HashMap<String, String> toothTypeMap) {
		this.toothTypeMap = toothTypeMap;
	}
	
}
