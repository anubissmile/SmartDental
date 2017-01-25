/*package test.com.smict.person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.smict.person.data.AddressData;
import com.smict.person.model.AddressModel;

import ldc.util.CalculateNumber;

public class Test_Address 
{
	String addr_id = "Test type id" , updatepre_name_id = "Update Test type id";
	String create_by = "01";
	String update_by = "02";
	
	public List<AddressModel> addListAddress(List<AddressModel> ListAddressModel){
		
		AddressData addrdb = new AddressData();
		
		List<AddressModel> returnListAddressModel = new ArrayList<AddressModel>();
		for(AddressModel model : ListAddressModel){
			try {
				Assert.assertTrue(!addrdb.select_address(model.getAddr_id(), model.getAddr_no(), model.getAddr_bloc(),
						model.getAddr_village(), model.getAddr_alley(), model.getAddr_road(), model.getAddr_provinceid(), 
						model.getAddr_aumphurid(), model.getAddr_districtid(), model.getAddr_zipcode(),model.getAddr_typeid(),
						model.getAddr_typename()).isEmpty());
				returnListAddressModel.add(model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return returnListAddressModel;
	}
	
	@Test
	public void addListAddress(){
		List<AddressModel> ListAddressModel = new ArrayList<AddressModel>();
		AddressData addrdb = new AddressData();
		ListAddressModel.add(add_name());
		ListAddressModel.add(add_name());
		ListAddressModel.add(add_name());
		for(AddressModel model : ListAddressModel){
			try {
				Assert.assertTrue(!addrdb.select_address(model.getAddr_id(), model.getAddr_no(), model.getAddr_bloc(),
						model.getAddr_village(), model.getAddr_alley(), model.getAddr_road(), model.getAddr_provinceid(), 
						model.getAddr_aumphurid(), model.getAddr_districtid(), model.getAddr_zipcode(),model.getAddr_typeid(),
						model.getAddr_typename()).isEmpty());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Add Name
	public AddressModel add_name()
	{
		String addr_id = "";
		String addr_no = "";
		String addr_bloc = "";
		String addr_village = "";
		String addr_alley = "";
		String addr_road = "";
		String addr_province = "";
		String addr_aumphur = "";
		String addr_district = "";
		String addr_zipcode = "";
		
		
		AddressData addrdb = new AddressData();
		//ProducttypeDB typedb = new ProducttypeDB();
		
		try
		{
			addr_id = addrdb.GetHighest_add_address();
		} 
		
		catch (Exception e) 
		
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addr_id = addrdb.PlusOneID_FormatID(addr_id);
		
		
		AddressModel model = new AddressModel();
		
		model.setAddr_id(addr_id);
		model.setAddr_no("59/5");
		model.setAddr_bloc("5");
		model.setAddr_village("Sucha");
		model.setAddr_alley("-");
		model.setAddr_road("Taling Chan - Suphanburi");
		model.setAddr_provinceid("1");
		model.setAddr_aumphurid("9");
		model.setAddr_districtid("61");
		model.setAddr_zipcode("11110");
		model.setAddr_typeid("1");
		model.setAddr_groupid(1);
		model.setAddr_groupname("");
		addrdb.add_address(model);
		
		
		return model;
	
	}
	
	
	@Test
	public void addname()
	{
		
		AddressModel model = new AddressModel();
		model =add_name();
		AddressData addrdb = new AddressData();
		
		try 
		{
			Assert.assertTrue(!addrdb.select_address(model.getAddr_id(), model.getAddr_no(), model.getAddr_bloc(),
					model.getAddr_village(), model.getAddr_alley(), model.getAddr_road(), model.getAddr_provinceid(), 
					model.getAddr_aumphurid(), model.getAddr_districtid(), model.getAddr_zipcode(),model.getAddr_typeid(),
					model.getAddr_typename()).isEmpty());
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
	public void DeleteAddress()
	{
		String addr_id = add_name().getAddr_id();
		AddressData addrdb = new AddressData();
		addrdb.DeleteAddress(addr_id);
		try 
		{
			Assert.assertTrue(addrdb.select_address(addr_id, "", "", "", "", "", "", "", "", "","","").size() == 0);
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
	public void UpdateAddress()
	{
		String addr_id = add_name().getAddr_id();
		AddressData addrdb = new AddressData();
		addrdb.UpdateAddress(addr_id, "21/7", "9", "-", "-", "Phetkaseam", "23", "330", "3024", "10160","2");
		List<AddressModel> resultList = new ArrayList<AddressModel>();
		
		AddressModel model = new AddressModel();
		
		model.setAddr_id(addr_id);
		model.setAddr_no("21/7");
		model.setAddr_bloc("9");
		model.setAddr_village("-");
		model.setAddr_alley("-");
		model.setAddr_road("Phetkaseam");
		model.setAddr_provinceid("23");
		model.setAddr_aumphurid("330");
		model.setAddr_districtid("3024");
		model.setAddr_zipcode("10160");
		model.setAddr_typeid("2");
		
		try
		{
			Assert.assertTrue(!addrdb.select_address(model.getAddr_id(), model.getAddr_no(), model.getAddr_bloc(),
					model.getAddr_village(), model.getAddr_alley(), model.getAddr_road(), model.getAddr_provinceid(), 
					model.getAddr_aumphurid(), model.getAddr_districtid(), model.getAddr_zipcode(),model.getAddr_typeid(),
					model.getAddr_typename()).isEmpty());
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
		
		for(AddressModel AddressModel:resultList)
		{
			Assert.assertEquals(AddressModel.getAddr_id(), addr_id);
			Assert.assertEquals(AddressModel.getAddr_no(), "");
			Assert.assertEquals(AddressModel.getAddr_bloc(), "");
			Assert.assertEquals(AddressModel.getAddr_village(), "");
			Assert.assertEquals(AddressModel.getAddr_alley(), "");
			Assert.assertEquals(AddressModel.getAddr_road(), "");
			Assert.assertEquals(AddressModel.getAddr_provinceid(), "");
			Assert.assertEquals(AddressModel.getAddr_aumphurid(), "");
			Assert.assertEquals(AddressModel.getAddr_districtid(), "");
			Assert.assertEquals(AddressModel.getAddr_zipcode(), "");
		}
	}
	
	@Test
	public void AddAddress_Type(){
		AddressData addrData = new AddressData();
		
		AddressModel addrModel = new AddressModel();
		String highest_addrTypeID = new CalculateNumber().plusInt(addrData.GetHighest_AddrTypeID(), 1);
		addrModel.setAddr_typeid(highest_addrTypeID);
		addrModel.setAddr_typename("Test AddrTypeName "+highest_addrTypeID);
		addAddressType(addrModel);
		
		Assert.assertEquals("ค้นหาข้อมูลประเภทที่อยู่ไม่พบ",true, !addrData.getAddress_type(addrModel).isEmpty());
	}
	
	@Test
	public void TestPlusInt(){
		AddressData addrData = new AddressData();
		String highest_addrtypeID = addrData.GetHighest_AddrTypeID();
		Assert.assertEquals("บวกเลขแล้วผลลัพทธ์ที่ตอบกลับมาไม่ถูกต้อง",String.valueOf(Integer.parseInt(highest_addrtypeID)+1),new CalculateNumber().plusInt(highest_addrtypeID, 1));
		
	}
	
	public void addAddressType(AddressModel addr){
		new AddressData().addAddress_type(addr);
	}
	
	
}
*/