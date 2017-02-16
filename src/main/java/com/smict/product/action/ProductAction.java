package com.smict.product.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.smict.product.data.ProductBrandDB;
import com.smict.product.data.ProductData;
import com.smict.product.data.ProductUnitDB;
import com.smict.product.data.ProductgroupDB;
import com.smict.product.data.ProducttypeDB;
import com.smict.product.model.LabBranchModel;
import com.smict.product.model.ProductModel;

public class ProductAction extends ActionSupport {
	private List<ProductModel> proModel;
	private Map<String,String> progroupList;
	private Map<String,String> probrandList;
	private Map<String,String> protypeList;
	private Map<String,String> prounitList;
	private ProductModel productModel;
	
	
<<<<<<< HEAD
=======

	
>>>>>>> 2feddd5eec34c1c066f853947cce06e6e6c587eb
	 public String addProductInsert() throws IOException, Exception{
		  ProductData proData = new ProductData();
		  proData.addpdinsert(productModel);
		  ProductData proDate =new ProductData();
		  setProModel(proDate.getListProductModel());
		  return SUCCESS;
		 }
	
	public String addMaterialInsert() throws IOException, Exception{
		  ProductData proData = new ProductData();
		  proData.addmlinsert(productModel);
		  ProductData proDate =new ProductData();
		  setProModel(proDate.getListMaterial());
		  return SUCCESS;
		 }
	
	public String addMedicineInsert() throws IOException, Exception{
		  ProductData proData = new ProductData();
		  proData.addmcinsert(productModel);
		  ProductData proDate =new ProductData();
		  setProModel(proDate.getListMedicine());
		  return SUCCESS;
		 }
	
	
	
	public String addProduct() throws IOException, Exception{
		
		ProducttypeDB proTypeData = new ProducttypeDB();
		setProtypeList(proTypeData.Get_typeList());
		
		ProductgroupDB progroupData = new ProductgroupDB();
		setProgroupList(progroupData.Get_groupList());
		
		ProductBrandDB probrandData = new ProductBrandDB();
		setProbrandList(probrandData.Get_brandList());
		
		ProductUnitDB prounitData = new ProductUnitDB();
		setProunitList(prounitData.Get_unitList());
		
		
		return NONE;
	
		
	}
	
public String addMedicine() throws IOException, Exception{
		
		ProducttypeDB proTypeData = new ProducttypeDB();
		setProtypeList(proTypeData.Get_typeList());
		
		ProductgroupDB progroupData = new ProductgroupDB();
		setProgroupList(progroupData.Get_groupList());
		
		ProductBrandDB probrandData = new ProductBrandDB();
		setProbrandList(probrandData.Get_brandList());
		
		ProductUnitDB prounitData = new ProductUnitDB();
		setProunitList(prounitData.Get_unitList());
		
		
		return NONE;
	
		
	}
	
	
	public String addMaterial() throws IOException, Exception{
		
		ProducttypeDB proTypeData = new ProducttypeDB();
		setProtypeList(proTypeData.Get_typeList());
		
		ProductgroupDB progroupData = new ProductgroupDB();
		setProgroupList(progroupData.Get_groupList());
		
		ProductBrandDB probrandData = new ProductBrandDB();
		setProbrandList(probrandData.Get_brandList());
		
		ProductUnitDB prounitData = new ProductUnitDB();
		setProunitList(prounitData.Get_unitList());
		
			return NONE;
	}
	
	public String getMedicineList(){
		  ProductData proDate =new ProductData();
		  setProModel(proDate.getListMedicine());
		  
		  
		  return NONE;
	}
	public String getMaterialList(){
		  ProductData proDate =new ProductData();
		  setProModel(proDate.getListMaterial());
		  
		  
		  return NONE;
	}
	public String getProductDetail() throws IOException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String product_id = request.getParameter("pro_id").toString();
		ProductData proData = new ProductData();
		setProductModel(proData.getProductDetail(product_id));
		
		
		
		ProducttypeDB proTypeData = new ProducttypeDB();
		setProtypeList(proTypeData.Get_typeList());
		
		ProductgroupDB progroupData = new ProductgroupDB();
		setProgroupList(progroupData.Get_groupList());
		
		ProductBrandDB probrandData = new ProductBrandDB();
		setProbrandList(probrandData.Get_brandList());
		
		ProductUnitDB prounitData = new ProductUnitDB();
		setProunitList(prounitData.Get_unitList());
		
		return NONE;
	}
	
	public String getMedicineDetail() throws IOException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String product_id = request.getParameter("pro_id").toString();
		ProductData proData = new ProductData();
		setProductModel(proData.getMedicineDetail(product_id));
		
		
		
		ProducttypeDB proTypeData = new ProducttypeDB();
		setProtypeList(proTypeData.Get_typeList());
		
		ProductgroupDB progroupData = new ProductgroupDB();
		setProgroupList(progroupData.Get_groupList());
		
		ProductBrandDB probrandData = new ProductBrandDB();
		setProbrandList(probrandData.Get_brandList());
		
		ProductUnitDB prounitData = new ProductUnitDB();
		setProunitList(prounitData.Get_unitList());
		
		return NONE;
	}
	
	
	public String getMaterialDetail() throws IOException, Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String product_id = request.getParameter("pro_id").toString();
		ProductData proData = new ProductData();
		setProductModel(proData.getMaterialDetail(product_id));
		
		
		
		ProducttypeDB proTypeData = new ProducttypeDB();
		setProtypeList(proTypeData.Get_typeList());
		
		ProductgroupDB progroupData = new ProductgroupDB();
		setProgroupList(progroupData.Get_groupList());
		
		ProductBrandDB probrandData = new ProductBrandDB();
		setProbrandList(probrandData.Get_brandList());
		
		ProductUnitDB prounitData = new ProductUnitDB();
		setProunitList(prounitData.Get_unitList());
		
		return NONE;
	}
	
	
	 public String getProductList(){
	  ProductData proDate =new ProductData();
	  setProModel(proDate.getListProductModel());
	  
	  
	  return NONE;
	  
	  
	 }
	
	
	public Map<String,String> getProgroupList() {
		return progroupList;
	}
	public void setProgroupList(Map<String,String> progroupList) {
		this.progroupList = progroupList;
	}



	public Map<String, String> getProbrandList() {
		return probrandList;
	}



	public void setProbrandList(Map<String, String> probrandList) {
		this.probrandList = probrandList;
	}



	public Map<String, String> getProtypeList() {
		return protypeList;
	}



	public void setProtypeList(Map<String, String> protypeList) {
		this.protypeList = protypeList;
	}



	public Map<String, String> getProunitList() {
		return prounitList;
	}



	public void setProunitList(Map<String, String> prounitList) {
		this.prounitList = prounitList;
	}
	public List<ProductModel> getProModel() {
		return proModel;
	}
	public void setProModel(List<ProductModel> proModel) {
		this.proModel = proModel;
	}
	public ProductModel getProductModel() {
		return productModel;
	}
	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}
	
	

}
