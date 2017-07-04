package com.smict.treatment.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.smict.all.model.ToothModel;
import com.smict.all.model.TreatmentMasterModel;
import com.smict.person.data.BrandData;
import com.smict.person.model.BrandModel;
import com.smict.product.model.ProductModel;
import com.smict.treatment.data.ToothMasterData;
import com.smict.treatment.data.TreatmentData;
import com.smict.treatment.data.TreatmentMasterData;
import com.smict.treatment.model.TreatmentModel;
import ldc.util.Auth;  

@SuppressWarnings("serial")
public class TreatmentMasterAction extends ActionSupport{
	private TreatmentMasterModel treatmentMasterModel;  
	private TreatmentModel treatmentModel;
	private ProductModel productModel;
	private ToothModel toothModel;
	private BrandModel brandModel;
	private List<BrandModel> brandList;
	private List<ProductModel> productList;
	private HashMap<String, String> brandMap;
	private List<TreatmentModel> treatmentList;
	private List<TreatmentModel> treatmentList2;
	private List<TreatmentModel> treatmentContinuousList;
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
	
	/**
	 * Edit treatment by treatment ID.
	 * @author anubi | wesarut.khm@gmail.com
	 * @return String | Action result string.
	 */
	public String editTreatmentByID(){
		HttpServletRequest request = ServletActionContext.getRequest();
		/**
		 * Fetch brand.
		 */
		this.fetchBrand();
		
		/**
		 * Fetch treatment group
		 */
		this.fetchTreatmentGroup();
		
		/**
		 * Fetch tooth picture.
		 */
		this.fetchToothFormat();
		
		/**
		 * Fetch tooth type.
		 */
		this.fetchToothType(0);

		/**
		 * Fetch treatment credential.
		 */
		String[] treatmentConditions = {"id", String.valueOf(treatmentModel.getTreatmentID())};
		this.selectTreatmentByID(treatmentConditions);
		
		/**
		 * Fetch treatment pricelist.
		 */
		String[] pricelistConditions = {"treatment_id", String.valueOf(treatmentModel.getTreatmentID())};
		this.fetchTreatmentPriceList(pricelistConditions);
		
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
	 * Get all treatment list filter by continuous type.
	 * @author anubi | wesarut.khm@gmail.com
	 * @return String | Struts action result.
	 */
	public String getTreatmentListFilterByContinuous(){
		TreatmentMasterData tMasterData = new TreatmentMasterData();
		treatmentList = tMasterData.getTreatmentByContinuousType(false);
		treatmentContinuousList = tMasterData.getTreatmentByContinuousType(true);
		return SUCCESS;
	}
	
	/**
	 * Add treatment continuous preference.
	 * @author anubi | wesarut.khm@gmail.com
	 * @return String | Action result.
	 */
	public String addTreatmentContinuousPreference(){
		/**
		 * Looping for add treatment continuous 
		 */
		TreatmentMasterData tMastereData = new TreatmentMasterData();
		List<Integer> resultList = new ArrayList<Integer>();
 	 	int phaseCount = treatmentModel.getRound().length;
 	 	int resultLength;
 		for(int i=0; i<phaseCount; i++){
 			HashMap<String, Integer> resultMap = tMastereData.addTreatmentContinuous(
				treatmentModel.getTreatmentID(), 
				i + 1, 
				treatmentModel.getRound()[i], 
				treatmentModel.getPhasePrice()[i], 
				treatmentModel.getStartPriceRange()[i], 
 				treatmentModel.getEndPriceRange()[i]
			);
 			resultLength = resultMap.size();
 			if(resultLength > 1){
 				for(int iterate=1; iterate<resultLength; iterate++){
 					StringBuilder sb = new StringBuilder();
 					resultList.add(
						resultMap.get(
							sb.append("ID").append(String.valueOf(iterate)).toString()
						)
					);
 				}
 			}
 		}
 		
 		/**
 		 * Add treatment phase detail.
 		 */
 		List<String> treatmentValList = new ArrayList<String>();
 		for(String tID : treatmentModel.getStrTreatmentID()){
 			String[] val = tID.split(":#:");
 			StringBuilder sb = new StringBuilder();
 			treatmentValList.add(
 				// Build str to ('5', '5', '5', '5') form.
 				sb.append("(")
 					//Treatment continuous phase id.
					.append("'").append(String.valueOf(resultList.get(Integer.valueOf(val[0])))).append("'").append(", ")
					//Treatment id.
 					.append("'").append(String.valueOf(val[1])).append("'").append(", ")
 					//Timestamps.
 					.append("NOW()").append(", ")
 					.append("NOW()")
 					.append(")").toString()
 			);
 		}
 		int treatRec = tMastereData.addTreatmentContinuousDetail(StringUtils.join(treatmentValList, ','));
 		
 		/**
 		 * Add product phase detail.
 		 */
 		List<String> productValList = new ArrayList<String>();
 		int i=0;
 		for(String pID : productModel.getStr_product_id_arr()){
 			String[] val = pID.split(":#:");
 			StringBuilder sb = new StringBuilder();
 			productValList.add(
				// Build str to ('4', '4', '4', '4', '4', '4') form.
				sb.append("(")
					//Treatment continuous phase id.
					.append("'").append(String.valueOf(resultList.get(Integer.valueOf(val[0])))).append("'").append(", ")
					//Medicine id.
					.append("'").append(String.valueOf(val[1])).append("'").append(", ")
					//Medicine's volumns.
					.append("'").append(String.valueOf(productModel.getProduct_volumn()[i])).append("'").append(", ")
					//Medicine's free volumns.
					.append("'").append(String.valueOf(productModel.getProduct_volumn_free()[i])).append("'").append(", ")
					//Timestamp
					.append("NOW()").append(", ")
					.append("NOW()")
					.append(")").toString()
			);
 			++i;
 		}
 		int productRec = tMastereData.addMedicineTreatmentContinuousDetail(StringUtils.join(productValList, ','));
 		
 		return SUCCESS;
	}
	
	/**
	 * Index of preference of treatment continuous.
	 * @author anubi | wesarut.khm@gmail.com
	 * @return String | Action result
	 */
	public String treatmentContinuousPreference(){
		
		/**
		 * Get medicine list.
		 */
		productList = this.getMedicineAndProductByTreatmentID(treatmentModel);
		
		/**
		 * Get treatment(non-continuous) list.
		 */
		treatmentList = this.getTreatmentContinuous(treatmentModel, true);
		return SUCCESS;
	}
	
	/**
	 * Adding product & med into the treatment master list.
	 * @author anubissmile | wesarut.khm@gmail.com
	 * @return String | Action result.
	 */
	public String addTreatmentMedicineExecute(){
		/**
		 * Adding med into treatment_product table.
		 */
		TreatmentMasterData tMasterData = new TreatmentMasterData();
		tMasterData.addMedIntoTreatmentMaster(treatmentModel, productModel);
		
		return SUCCESS;
	}
	
	/**
	 * Matching medicine list and product list into treatment.
	 * @author anubissmile | wesarut.khm@gmail.com
	 * @return String  | Action result.
	 */
	public String addTreatmentMedicine(){
		
		/**
		 * Get medicine and product for put into treatment.
		 */
		productList = this.getMedicineAndProductByTreatmentID(treatmentModel);
		return SUCCESS;
	}
	
	/**
	 * Get medicine and product by treatment id.
	 * @author anubissmile | wesarut.khm@gmail.com
	 * @param TreatmentModel tModel |
	 * @return List<ProductModel>
	 */
	public List<ProductModel> getMedicineAndProductByTreatmentID(TreatmentModel tModel){
		TreatmentMasterData treatmentMData = new TreatmentMasterData();
		return  treatmentMData.getMedicineAndProductByTreatmentID(tModel);
	}
	
	/**
	 * Get treatment list by type of continuous or non-continuous
	 * @param TreatmentModel tModel
	 * @param boolean isContinuous | (true : continuous , false : none-continuous)
	 * @return List<TreatmentModel>
	 */
	public List<TreatmentModel> getTreatmentContinuous(TreatmentModel tModel, boolean isContinuous){
		TreatmentMasterData treatmentMData = new TreatmentMasterData();
		return  treatmentMData.getTreatmentContinuous(tModel, isContinuous);
	}
	
	public String begin() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		/**
		 * Fetch brand.
		 */
		this.fetchBrand();
		
		/**
		 * Fetch treatment group
		 */
		this.fetchTreatmentGroup();
		
		/**
		 * Fetch tooth picture.
		 */
		this.fetchToothFormat();
		
		/**
		 * Fetch tooth type.
		 */
		this.fetchToothType(0);
		
		/**
		 * Treatment format & Tooth picture.
		 */
		ToothMasterData toothData = new ToothMasterData();
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
			
			/**
			 * Checking for continuous treatment.
			 */
			if(treatmentModel.getIsContinue() == 1){
				strReturn = SUCCESS;
			}else if(treatmentModel.getIsContinue() == 2){
				strReturn = "CONTINUOUS";
			}
			treatmentModel.setTreatmentID(rec[2]);
		}else{
			addActionError("Adding data goes wrong. Please try again or ensuring that your form is completed.");
			strReturn = INPUT;
			begin();
		}
		return strReturn;
	} 
	
	
	/**
	 * PRIVATE ZONE.
	 */
	
	/**
	 * Fetching brand and put into List<> brandList and HashMap<String, String> brandMap
	 * @author anubi
	 * @return void
	 */
	private void fetchBrand(){
		BrandData brandData = new BrandData();
		brandList = brandData.chunkBrand();
		brandMap = new HashMap<String, String>();
		for(BrandModel bModel : brandList){
			brandMap.put(
				String.valueOf(bModel.getBrand_id()),
				bModel.getBrand_name()
			);
		}
	}
	
	/**
	 * Fetching treatment group and put into List<> treatmentList and HashMap<> treatmentMap.
	 * @author anubi
	 * @return void
	 */
	private void fetchTreatmentGroup(){
		TreatmentData treatmentData = new TreatmentData();
		treatmentList = treatmentData.getTreatmentGroup(0);
		treatmentMap = new HashMap<String, String>();
		for(TreatmentModel treatModel : treatmentList){
			treatmentMap.put(
				String.valueOf(treatModel.getTreatmentGroupID()),
				treatModel.getTreatmentGroupName()
			);
		}
	}

	/**
	 * Fetching tooth format and put into List<> treatmentList and HashMap<> toothPicMap
	 * @author anubi
	 * @return void
	 */
	private void fetchToothFormat(){
		TreatmentData treatmentData = new TreatmentData();
		treatmentList = treatmentData.getToothPicture("");
		toothPicMap = new HashMap<String, String>();
		for(TreatmentModel tModel : treatmentList){
			toothPicMap.put(
				tModel.getToothPicCode(),
				tModel.getToothPicName()
			);
		}
	}
	
	/**
	 * Fetching tooth type and put into List<> treatmentList
	 * @author anubi
	 * @param int id | Tooth type id.
	 * @return void
	 */
	private void fetchToothType(Integer id){
		if(id == null){
			id = 0;
		}
		TreatmentData treatmentData = new TreatmentData();
		treatmentList = treatmentData.getToothType(0);
	}
	
	/**
	 * Select treatment by ID 
	 * <pre>
	 * - String[] conditions = {"field name", "val"}
	 * - String[] conditions = {"field name", "<>", "val"}
	 * - String[] conditions = {"field name", ">=", "val"}
	 * - String[] conditions = {"field name", "<=", "val"}
	 * </pre>
	 * @author anubi
	 * @param String[] conditions | Where clause conditions.
	 * @return void 
	 */
	private void selectTreatmentByID(String[] conditions){
		TreatmentMasterData tMasterData = new TreatmentMasterData();
		treatmentList2 = tMasterData.selectTreatmentWhere(conditions);
		/**
		 * Set to Model(treatmentModel)
		 */
		if(treatmentList2.size() == 1){
			for(TreatmentModel tModel : treatmentList){
				treatmentModel.setTreatmentID(tModel.getTreatmentID());
				treatmentModel.setTreatmentCode(tModel.getTreatmentCode());
				treatmentModel.setTreatmentNameTH(tModel.getTreatmentNameTH());
				treatmentModel.setTreatmentNameEN(tModel.getTreatmentNameEN());
				treatmentModel.setAutoHomeCall(tModel.getAutoHomeCall());
				treatmentModel.setRecall(tModel.getRecall());
				treatmentModel.setIsContinue(tModel.getIsContinue());
				treatmentModel.setIsRepeat(tModel.getIsRepeat());
				treatmentModel.setTreatmentMode(tModel.getTreatmentMode());
				treatmentModel.setTreatmentCategoryID(tModel.getTreatmentCategoryID());
				treatmentModel.setToothPicCode(tModel.getToothPicCode());
			}
		}
	}
	
	/**
	 * Fetching treatment's price list filter by brand.
	 * <pre>
	 * - String[] conditions = {"field name", "val"}
	 * - String[] conditions = {"field name", "<>", "val"}
	 * - String[] conditions = {"field name", ">=", "val"}
	 * - String[] conditions = {"field name", "<=", "val"}
	 * </pre>
	 * @author anubi
	 * @param String[] conditions | Where clause conditions.
	 * @return void
	 */
	private void fetchTreatmentPriceList(String[] conditions){
		TreatmentMasterData tMasterData = new TreatmentMasterData();
		treatmentModel.setPriceListModel(tMasterData.selectTreatmentPricelist(conditions));
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

	public List<ProductModel> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductModel> productList) {
		this.productList = productList;
	}

	public ProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}

	public List<TreatmentModel> getTreatmentContinuousList() {
		return treatmentContinuousList;
	}

	public void setTreatmentContinuousList(List<TreatmentModel> treatmentContinuousList) {
		this.treatmentContinuousList = treatmentContinuousList;
	}

	public List<TreatmentModel> getTreatmentList2() {
		return treatmentList2;
	}

	public void setTreatmentList2(List<TreatmentModel> treatmentList2) {
		this.treatmentList2 = treatmentList2;
	}
	
}
