package test.com.smict.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.smict.person.data.BrandData;
import com.smict.person.model.BrandModel;

public class Test_Brand
{
	int brand_id = 0;
	String updatebrand_name = "Update Test type name";
	String create_by = "01";
	String update_by = "02";
	
	
	//Add Name
		public BrandModel add_brand()
		{
			int brand_id = 0;
			String brand_name = "";
			
			
			BrandData branddb = new BrandData();
			//ProducttypeDB typedb = new ProducttypeDB();
			
			try
			{
				brand_id = branddb.GetHighest_add_brand();
			} 
			
			catch (Exception e) 
			
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			brand_id = branddb.PlusOneID_FormatID(brand_id);
			
			
			BrandModel model = new BrandModel();
			
			model.setBrand_id(brand_id);
			model.setBrand_name("aaa");
			
			
			
			branddb.add_brand(model);
			
			return model;
		
		}
	
		
		
	@Test
		public void addbrand()
		{
			
			BrandModel model = new BrandModel();
			model = add_brand();
			BrandData branddb = new BrandData();
			
			try 
			{
				Assert.assertTrue(!branddb.select_brand(model.getBrand_id(), model.getBrand_name()).isEmpty());
			}
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
	@Test
	public void DeleteBrand()
	{
		int brand_id = add_brand().getBrand_id();
		BrandData branddb = new BrandData();
		branddb.DeleteBrand(brand_id);
		try 
		{
			Assert.assertTrue(branddb.select_brand(brand_id, "").size() == 0);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	
	public void UpdateBrand()
	{
		int brand_id = add_brand().getBrand_id();
		BrandData branddb = new BrandData();
		branddb.UpdateBrand(brand_id, "bbb");
		List<BrandModel> resultList = new ArrayList<BrandModel>();
		
		BrandModel model = new BrandModel();
		
		model.setBrand_id(brand_id);
		model.setBrand_name("bbb");
		

		try
		{
			Assert.assertTrue(!branddb.select_brand(model.getBrand_id(), model.getBrand_name()).isEmpty());
		} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(BrandModel BrandModel:resultList)
		{
			Assert.assertEquals(BrandModel.getBrand_id(), brand_id);
			Assert.assertEquals(BrandModel.getBrand_name(), "");
		}
	}
	
	
	
	
}