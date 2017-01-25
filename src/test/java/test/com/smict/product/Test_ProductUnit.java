package test.com.smict.product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.Assert;

import com.smict.product.data.ProductUnitDB;
import com.smict.product.model.ProductUnitModel;

public class Test_ProductUnit {
	
	static String productunit_name = "Test Unit name" , updateproductunit_name = "Update Test Unit name";
	static String create_by = "001";
	String update_by = "002";
	
	@Test
	public void Test_AddProductUnit(){
		String unit_id = addUnit();
		try {
			Assert.assertTrue(new ProductUnitDB().Get_UnitList(unit_id, "Test").size() > 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void DeleteUnit(){
		String unit_id = addUnit();
		ProductUnitDB unitdb = new ProductUnitDB();
		unitdb.DeleteUnit(unit_id);
		try {
			Assert.assertTrue(unitdb.Get_UnitList(unit_id, "Test").size() == 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void UpdateUnit(){
		String unit_id = addUnit();
		ProductUnitDB unitdb = new ProductUnitDB();
		unitdb.UpdateUnit(unit_id,updateproductunit_name , update_by);
		List<ProductUnitModel> resultList = new ArrayList<ProductUnitModel>();
		try {
			resultList = unitdb.Get_UnitList(unit_id, "Test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ProductUnitModel proModel:resultList){
			Assert.assertEquals(proModel.getId(), unit_id);
			Assert.assertEquals(proModel.getName(), updateproductunit_name);
			Assert.assertEquals(proModel.getUpdate_by(), update_by);
		}
	}
	

	public static String addUnit(){
		String unit_id = "";
		ProductUnitDB unitdb = new ProductUnitDB();
		try {
			unit_id = unitdb.GetHighest_ProductUnitID();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		unit_id = unitdb.PlusOneID_FormatID(unit_id);
		unitdb.AddUnit(unit_id, productunit_name+unit_id, create_by);
		return unit_id;
	}
	
}
