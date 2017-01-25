package test.com.smict.product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.Assert;

import com.smict.product.data.ProducttypeDB;
import com.smict.product.model.ProducttypeModel;

public class Test_Producttype {
	
	static String producttype_name = "Test type name" , updateproducttype_name = "Update Test type name";
	static String create_by = "001";
	String update_by = "002";
	
	@Test
	public void Test_AddProducttype(){
		String producttype_Id = addtype();
		try {
			Assert.assertTrue(new ProducttypeDB().Get_typeList(producttype_Id, "Test").size() > 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void Deletetype(){
		String producttype_Id = addtype();
		ProducttypeDB typedb = new ProducttypeDB();
		typedb.Deletetype(producttype_Id);
		try {
			Assert.assertTrue(typedb.Get_typeList(producttype_Id , "Test").size() == 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void Updatetype(){
		String type_id = addtype();
		ProducttypeDB typedb = new ProducttypeDB();
		typedb.Updatetype(type_id,updateproducttype_name , update_by);
		List<ProducttypeModel> resultList = new ArrayList<ProducttypeModel>();
		try {
			resultList = typedb.Get_typeList(type_id, "Test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ProducttypeModel proModel:resultList){
			Assert.assertEquals(proModel.getId(), type_id);
			Assert.assertEquals(proModel.getName(), updateproducttype_name);
			Assert.assertEquals(proModel.getUpdate_by(), update_by);
		}
	}
	

	public static String addtype(){
		String type_id = "";
		ProducttypeDB typedb = new ProducttypeDB();
		try {
			type_id = typedb.GetHighest_ProducttypeID();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		type_id = typedb.PlusOneID_FormatID(type_id);
		typedb.Addtype(type_id, producttype_name+type_id, create_by);
		return type_id;
	}
	
}
