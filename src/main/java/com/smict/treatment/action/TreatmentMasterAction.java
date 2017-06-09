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
import com.smict.person.model.BrandModel;
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
	private BrandModel brandModel;
	private List<BrandModel> brandList;
	private HashMap<String, String> brandMap;
	private List<TreatmentModel> treatmentList;
	private HashMap<String, String> treatmentMap;
	private HashMap<String, String> toothPicMap;
	private HashMap<String, String> toothTypeMap;
	private String alertSuccess, alertError, alertMSG;
	
	
	/**
	 * CONSTRUCTOR
	 */
	public TreatmentMasterAction(){
		Auth.authCheck(false);
	}
	
	public String addTreatmentMedicine(){
		return  SUCCESS;
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
		return null;
	}
	
	
	/**
	 * Add new treatment master.
	 * @author anubissmile
	 * @return String | Action result.
	 */
	public String execute() throws Exception{
		TreatmentMasterData treatmentData = new TreatmentMasterData();
		String strReturn = SUCCESS;
		
		/**
		 * Insert new treatment.
		 * - int[] rec = {count of row treatment_master, count of row treatment_tooth_type, treatment_master_id, count of row treatment_pricelist}
		 */
		int[] rec = treatmentData.addTreatmentMaster(treatmentModel, brandModel);
		
		if(rec[0] > 0 && rec[3] > 0){
			alertSuccess = "Adding new treatment successful.";
			strReturn = SUCCESS;
		}else{
			addActionError("Adding data goes wrong. Please try again or ensuring that your form is completed.");
			strReturn = INPUT;
			begin();
		}
		return strReturn;
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

	public BrandModel getBrandModel() {
		return brandModel;
	}

	public void setBrandModel(BrandModel brandModel) {
		this.brandModel = brandModel;
	}

	public String getAlertSuccess() {
		return alertSuccess;
	}

	public void setAlertSuccess(String alertSuccess) {
		this.alertSuccess = alertSuccess;
	}

	public String getAlertError() {
		return alertError;
	}

	public void setAlertError(String alertError) {
		this.alertError = alertError;
	}

	public String getAlertMSG() {
		return alertMSG;
	}

	public void setAlertMSG(String alertMSG) {
		this.alertMSG = alertMSG;
	}
	
}
