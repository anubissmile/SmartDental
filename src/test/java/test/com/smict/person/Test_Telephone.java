package test.com.smict.person;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.Assert;

import com.smict.person.data.TelephoneData;
import com.smict.person.model.TelephoneModel;

public class Test_Telephone {

	//@Test
	public void Add_Telephone(){
		TelephoneModel telModel = new TelephoneModel(0,"0891744898",1);
		int tel_id = addTelephone(telModel).getTel_id();
		telModel.setTel_id(tel_id);
		telModel.setTel_number("");
		telModel.setTel_typeid(1);
		Assert.assertEquals(true, new TelephoneData().select_telephone(telModel).size() > 0);
	}
	@Test
	public void Add_ListTelephone(){
		List<TelephoneModel> ListTelModel = new ArrayList<TelephoneModel>();
		TelephoneModel telModel = new TelephoneModel(0,"0822232321",1);
		telModel.setOwners("HN000000134");
		telModel.setTel_groupid(1);
		ListTelModel.add(telModel);
		telModel = new TelephoneModel(0,"0822232322",1);
		telModel.setTel_groupid(1);
		telModel.setOwners("HN000000134");
		ListTelModel.add(telModel);
		List<TelephoneModel> return_ListTelModel = addListTelephone(ListTelModel);
		for(TelephoneModel  retrive_telModel: return_ListTelModel){
			Assert.assertEquals(true, new TelephoneData().select_telephone(retrive_telModel).size() > 0);
		}
	}
	
	//@Test
	public void update_Telephone(){
		TelephoneModel telModel = new TelephoneModel(0,"0891744898",1);

		telModel.setTel_id(addTelephone(telModel).getTel_id());
		telModel.setTel_number("0888685197");
		TelephoneData telDB = new TelephoneData();
		telDB.update_telephone(telModel);
		Assert.assertEquals("0888685197", telDB.select_telephone_rModel(telModel).getTel_number()); 
	}
	
	//@Test
	public void delete_Telephone(){
		TelephoneModel telModel = new TelephoneModel(0,"0891744898",1);
		telModel.setTel_id(addTelephone(telModel).getTel_id());
		TelephoneData telDB = new TelephoneData();
		telDB.Delete_Telephone(telModel);
		Assert.assertEquals(null, telDB.select_telephone_rModel(telModel).getTel_number());
	}
	
	//@Test
	public void Add_Multiple_Patient_Telephone(){
		TelephoneModel telModel = new TelephoneModel("0833214567", "", "", "hn123456789", 
				0, 1, 1);
		addPatient_Telephone(telModel);
		TelephoneData teldb = new TelephoneData();
		Assert.assertTrue("ไม่พบข้อมูลเบอร์ของคนไข้ที่ค้นหา", !teldb.getMultiple_Telephone(telModel).isEmpty());
	}
	
	public TelephoneModel addTelephone(TelephoneModel telModel){
		TelephoneData teldb = new TelephoneData();
		int tel_id = teldb.Gethight_telID();
		tel_id = teldb.PlusOne(tel_id);
		telModel.setTel_id(tel_id);
		teldb.add_telephone(telModel);
		
		return telModel;
	}
	
	public List<TelephoneModel> addListTelephone(List<TelephoneModel> listTelModel){
		TelephoneData teldb = new TelephoneData();
		
		List<TelephoneModel> return_listTelModel = new ArrayList<TelephoneModel>();
		
		for(TelephoneModel telModel:listTelModel){
			int tel_id = teldb.Gethight_telID();
			tel_id = teldb.PlusOne(tel_id);
			telModel.setTel_id(tel_id);
			teldb.add_telephone(telModel);
			teldb.addMultiple_Telephone(telModel);
			return_listTelModel.add(telModel);
		}
		
		return return_listTelModel;
	}
	
	
	public TelephoneModel addPatient_Telephone(TelephoneModel telModel){
		int tel_id = addTelephone(telModel).getTel_id();
		telModel.setTel_id(tel_id);
		TelephoneData teldb = new TelephoneData();
		teldb.addMultiple_Telephone(telModel);
		
		return telModel;
	}
}
