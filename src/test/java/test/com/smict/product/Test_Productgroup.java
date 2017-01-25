package test.com.smict.product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
//import org.jgroup.*;
//import org.jgroup.Assert;
import org.junit.Test;

import com.smict.product.data.ProductgroupDB;
import com.smict.product.model.ProductgroupModel;

public class Test_Productgroup {
	
	static String productgroup_name = "Test group name" , updateproductgroup_name = "Update Test group name";
	static String create_by = "001";
	String update_by = "002";
	
	@Test
	public void Test_AddProductgroup(){
		String group_id = addProductgroup();
		try {
			Assert.assertTrue(new ProductgroupDB().Get_groupList(group_id, "Test").size() > 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void DeleteProductgroup(){
		String group_id = addProductgroup();
		ProductgroupDB groupdb = new ProductgroupDB();
		groupdb.Deletegroup(group_id);
		try {
			Assert.assertTrue(groupdb.Get_groupList(group_id, "Test").size() == 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void UpdateProductgroup(){
		String group_id = addProductgroup();
		ProductgroupDB groupdb = new ProductgroupDB();
		groupdb.Updategroup(group_id,updateproductgroup_name , update_by);
		List<ProductgroupModel> resultList = new ArrayList<ProductgroupModel>();
		try {
			resultList = groupdb.Get_groupList(group_id, "Test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ProductgroupModel proModel:resultList){
			Assert.assertEquals(proModel.getId(), group_id);
			Assert.assertEquals(proModel.getName(), updateproductgroup_name);
			Assert.assertEquals(proModel.getUpdate_by(), update_by);
		}
	}
	

	public static String addProductgroup(){
		String group_id = "";
		ProductgroupDB groupdb = new ProductgroupDB();
		try {
			group_id = groupdb.GetHighest_ProductgroupID();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		group_id = groupdb.PlusOneID_FormatID(group_id);
		groupdb.Addgroup(group_id, productgroup_name+group_id, create_by);
		return group_id;
	}
	
}
