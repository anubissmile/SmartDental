package com.smict.all.model;

public class TreatmentMasterModel
{
	private String treatment_code;
	private String treatment_nameth;
	private String treatment_nameen;
	private int brand_id;
	private String doctor_revenue_sharing;
	private int lab_percent;
	private String autohomecall;
	private String recall_typeid;
	private String treatment_type;
	private String price_standard;
	private String price_benefit;
	private String treatment_mode;
	private String set_treatmant;
	private String product_id;
	private String product_name;
	private String price;
	private String product_transfer;
	private String product_free;
	/**
	 * treatment category
	 */
	private String treatCategory_id,treatCategory_code,treatCategory_name,treatCategory_groupid;
	private String  treatment_group_code,labmode_id,treatment_group_name;
	
	private String tooth_pic_code;
	
	private String type_tooth, type_surface, type_mouth, type_quadrant, type_sextant, type_arch, type_tooth_range;
	public TreatmentMasterModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TreatmentMasterModel(String treatment_code, String treatment_nameth, String treatment_nameen, int brand_id,
			String doctor_revenue_sharing, int lab_percent, String autohomecall, String recall_typeid,
			String treatment_type, String price_standard, String price_benefit, String treatment_mode) {
		super();
		this.treatment_code = treatment_code;
		this.treatment_nameth = treatment_nameth;
		this.treatment_nameen = treatment_nameen;
		this.brand_id = brand_id;
		this.doctor_revenue_sharing = doctor_revenue_sharing;
		this.lab_percent = lab_percent;
		this.autohomecall = autohomecall;
		this.recall_typeid = recall_typeid;
		this.treatment_type = treatment_type;
		this.price_standard = price_standard;
		this.price_benefit = price_benefit;
		this.treatment_mode = treatment_mode;
	}  

	public TreatmentMasterModel(String product_id, String product_name, String price, String product_transfer, String product_free) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.price = price;
		this.product_transfer = product_transfer;
		this.product_free = product_free;
	}

	public String getTreatment_code() {
		return treatment_code;
	}
	public void setTreatment_code(String treatment_code) {
		this.treatment_code = treatment_code;
	}
	public String getTreatment_nameth() {
		return treatment_nameth;
	}
	public void setTreatment_nameth(String treatment_nameth) {
		this.treatment_nameth = treatment_nameth;
	}
	public String getTreatment_nameen() {
		return treatment_nameen;
	}
	public void setTreatment_nameen(String treatment_nameen) {
		this.treatment_nameen = treatment_nameen;
	}
	public int getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(int brand_id) {
		this.brand_id = brand_id;
	}
	public String getDoctor_revenue_sharing() {
		return doctor_revenue_sharing;
	}
	public void setDoctor_revenue_sharing(String doctor_revenue_sharing) {
		this.doctor_revenue_sharing = doctor_revenue_sharing;
	}
	public int getLab_percent() {
		return lab_percent;
	}
	public void setLab_percent(int lab_percent) {
		this.lab_percent = lab_percent;
	}
	public String getAutohomecall() {
		return autohomecall;
	}
	public void setAutohomecall(String autohomecall) {
		this.autohomecall = autohomecall;
	}
	public String getRecall_typeid() {
		return recall_typeid;
	}
	public void setRecall_typeid(String recall_typeid) {
		this.recall_typeid = recall_typeid;
	}
	public String getTreatment_type() {
		return treatment_type;
	}
	public void setTreatment_type(String treatment_type) {
		this.treatment_type = treatment_type;
	}
	public String getPrice_standard() {
		return price_standard;
	}
	public void setPrice_standard(String price_standard) {
		this.price_standard = price_standard;
	}
	public String getPrice_benefit() {
		return price_benefit;
	}
	public void setPrice_benefit(String price_benefit) {
		this.price_benefit = price_benefit;
	}

	public String getTreatment_mode() {
		return treatment_mode;
	}

	public void setTreatment_mode(String treatment_mode) {
		this.treatment_mode = treatment_mode;
	}

	public String getProduct_transfer() {
		return product_transfer;
	}

	public void setProduct_transfer(String product_transfer) {
		this.product_transfer = product_transfer;
	}

	public String getProduct_free() {
		return product_free;
	}

	public void setProduct_free(String product_free) {
		this.product_free = product_free;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTooth_pic_code() {
		return tooth_pic_code;
	}

	public void setTooth_pic_code(String tooth_pic_code) {
		this.tooth_pic_code = tooth_pic_code;
	}

	public String getTreatment_group_code() {
		return treatment_group_code;
	}

	public void setTreatment_group_code(String treatment_group_code) {
		this.treatment_group_code = treatment_group_code;
	}

	public String getLabmode_id() {
		return labmode_id;
	}

	public void setLabmode_id(String labmode_id) {
		this.labmode_id = labmode_id;
	}

	public String getTreatment_group_name() {
		return treatment_group_name;
	}

	public void setTreatment_group_name(String treatment_group_name) {
		this.treatment_group_name = treatment_group_name;
	}

	public String getSet_treatmant() {
		return set_treatmant;
	}

	public void setSet_treatmant(String set_treatmant) {
		this.set_treatmant = set_treatmant;
	}

	public String getType_tooth() {
		return type_tooth;
	}

	public void setType_tooth(String type_tooth) {
		this.type_tooth = type_tooth;
	}

	public String getType_surface() {
		return type_surface;
	}

	public void setType_surface(String type_surface) {
		this.type_surface = type_surface;
	}

	public String getType_mouth() {
		return type_mouth;
	}

	public void setType_mouth(String type_mouth) {
		this.type_mouth = type_mouth;
	}

	public String getType_quadrant() {
		return type_quadrant;
	}

	public void setType_quadrant(String type_quadrant) {
		this.type_quadrant = type_quadrant;
	}

	public String getType_sextant() {
		return type_sextant;
	}

	public void setType_sextant(String type_sextant) {
		this.type_sextant = type_sextant;
	}

	public String getType_arch() {
		return type_arch;
	}

	public void setType_arch(String type_arch) {
		this.type_arch = type_arch;
	}

	public String getType_tooth_range() {
		return type_tooth_range;
	}

	public void setType_tooth_range(String type_tooth_range) {
		this.type_tooth_range = type_tooth_range;
	}

	public String getTreatCategory_id() {
		return treatCategory_id;
	}

	public void setTreatCategory_id(String treatCategory_id) {
		this.treatCategory_id = treatCategory_id;
	}

	public String getTreatCategory_code() {
		return treatCategory_code;
	}

	public void setTreatCategory_code(String treatCategory_code) {
		this.treatCategory_code = treatCategory_code;
	}

	public String getTreatCategory_name() {
		return treatCategory_name;
	}

	public void setTreatCategory_name(String treatCategory_name) {
		this.treatCategory_name = treatCategory_name;
	}

	public String getTreatCategory_groupid() {
		return treatCategory_groupid;
	}

	public void setTreatCategory_groupid(String treatCategory_groupid) {
		this.treatCategory_groupid = treatCategory_groupid;
	}

	 
	 
}