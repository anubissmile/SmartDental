package test.com.smict.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.smict.person.data.Pre_nameData;
import com.smict.person.model.Pre_nameModel;
import com.smict.product.data.ProductBrandDB;
import com.smict.product.model.ProductBrandModel;

public class Test_Pre_name
{
	static String pre_name_id = "Test type id" , updatepre_name_id = "Update Test type id";
	static String create_by = "01";
	String update_by = "02";
	
	/*@Test
	public void Test_add_pre_name(){
		String pre_name_Id = add_pre_name();
		try {
			Assert.assertTrue(new ProducttypeDB().Get_typeList(pre_name_Id, "Test").size() > 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public String addname()
	{
		String pre_name_id = "";
		String pre_name_th = "";
		String pre_name_en = "";
		
		Pre_nameData prenamedb = new Pre_nameData();
		//ProducttypeDB typedb = new ProducttypeDB();
		
		try
		{
			pre_name_id = prenamedb.GetHighest_add_pre_name();
			
			//System.out.println(pre_name_id);
			
			//pre_name_th = prenamedb.GetHighest_add_pre_name();
			//pre_name_en = prenamedb.GetHighest_add_pre_name();
		} 
		
		catch (Exception e) 
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pre_name_id = prenamedb.PlusOneID_FormatID(pre_name_id);
		
		//System.out.println(pre_name_id);
		//pre_name_th = prenamedb.PlusOneID_FormatNameTH(pre_name_th);
		//pre_name_en = prenamedb.PlusOneID_FormatNameEN(pre_name_en);
		Pre_nameModel model = new Pre_nameModel();
		
		model.setPre_name_id(pre_name_id);
		model.setPre_name_th("¹ÒÂ ¡¡¡");
		model.setPre_name_en("Mr.aaa");
		
		//System.out.println(pre_name_id);
		
		prenamedb.add_pre_name(model);
		
		
		return pre_name_id;
	
	}
	
	
	
	@Test
	public void add_pre_name()
	{
		String pre_name_id = "";
		String pre_name_th = "";
		String pre_name_en = "";
		
		Pre_nameData prenamedb = new Pre_nameData();
		//ProducttypeDB typedb = new ProducttypeDB();
		
		try
		{
			pre_name_id = prenamedb.GetHighest_add_pre_name();
			//pre_name_th = prenamedb.GetHighest_add_pre_name();
			//pre_name_en = prenamedb.GetHighest_add_pre_name();
		} 
		
		catch (Exception e) 
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pre_name_id = prenamedb.PlusOneID_FormatID(pre_name_id);
		//pre_name_th = prenamedb.PlusOneID_FormatNameTH(pre_name_th);
		//pre_name_en = prenamedb.PlusOneID_FormatNameEN(pre_name_en);
		Pre_nameModel model = new Pre_nameModel();
		
		model.setPre_name_id(pre_name_id);
		model.setPre_name_th("¹ÒÂ ¡¡¡");
		model.setPre_name_en("Mr.aaa");
		
		prenamedb.add_pre_name(model);
		
		try 
		{
			Assert.assertTrue(!prenamedb.select_pre_name(model.getPre_name_id(), model.getPre_name_th(), model.getPre_name_en()).isEmpty());
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
	public void DeletePre_name()
	{
		String pre_name_id = addname();
		Pre_nameData prenamedb = new Pre_nameData();
		prenamedb.DeletePre_name(pre_name_id);
		try 
		{
			Assert.assertTrue(prenamedb.select_pre_name(pre_name_id, "", "").size() == 0);
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
		String pre_name_id = addname();
		Pre_nameData prenamedb = new Pre_nameData();
		prenamedb.UpdatePre_name(pre_name_id, "¹ÒÂ ¢¢¢", "Mr.bbb");
		List<Pre_nameModel> resultList = new ArrayList<Pre_nameModel>();
		
		Pre_nameModel model = new Pre_nameModel();
		
		model.setPre_name_id(pre_name_id);
		model.setPre_name_th("¹ÒÂ ¢¢¢");
		model.setPre_name_en("Mr.bbb");
		
		
		
		try
		{
			Assert.assertTrue(!prenamedb.select_pre_name(model.getPre_name_id(), model.getPre_name_th(), model.getPre_name_en()).isEmpty());
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
		
		for(Pre_nameModel Pre_nameModel:resultList)
		{
			Assert.assertEquals(Pre_nameModel.getPre_name_id(), pre_name_id);
			Assert.assertEquals(Pre_nameModel.getPre_name_th(), "");
			Assert.assertEquals(Pre_nameModel.getPre_name_en(), "");
		}
	}
	
}
