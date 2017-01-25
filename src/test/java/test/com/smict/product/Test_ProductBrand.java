package test.com.smict.product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.Assert;

import com.smict.product.data.ProductBrandDB;
import com.smict.product.model.ProductBrandModel;



public class Test_ProductBrand {
	
	static String productbrand_name = "Test Brand name" , updateproductbrand_name = "Update Test Brand name";
	static String create_by = "001";
	String update_by = "002";
	
	@Test
	public void Test_AddProductBrand(){
		String brand_id = addBrand();
		try {
			Assert.assertTrue(new ProductBrandDB().Get_BrandList(brand_id, "Test").size() > 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void DeleteBrand(){
		String brand_id = addBrand();
		ProductBrandDB branddb = new ProductBrandDB();
		branddb.DeleteBrand(brand_id);
		try {
			Assert.assertTrue(branddb.Get_BrandList(brand_id, "Test").size() == 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void UpdateBrand(){
		String brand_id = addBrand();
		ProductBrandDB Branddb = new ProductBrandDB();
		Branddb.UpdateBrand(brand_id,updateproductbrand_name , update_by);
		List<ProductBrandModel> resultList = new ArrayList<ProductBrandModel>();
		try {
			resultList = Branddb.Get_BrandList(brand_id, "Test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ProductBrandModel proModel:resultList){
			Assert.assertEquals(proModel.getId(), brand_id);
			Assert.assertEquals(proModel.getName(), updateproductbrand_name);
			Assert.assertEquals(proModel.getUpdate_by(), update_by);
		}
	}
	

	public static String addBrand(){
		String brand_id = "";
		ProductBrandDB branddb = new ProductBrandDB();
		try {
			brand_id = branddb.GetHighest_productbrand_id();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		brand_id = branddb.PlusOneID_FormatID(brand_id);
		branddb.AddBrand(brand_id, productbrand_name+brand_id, create_by);
		return brand_id;
	}
	
}
