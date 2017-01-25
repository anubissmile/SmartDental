/*package test.com.smict.person;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;
import com.smict.person.data.AddressData;
import com.smict.person.data.MultiAddressData;
import com.smict.person.data.PatientData;
import com.smict.person.model.AddressModel;
import com.smict.person.model.FamilyModel;
import com.smict.person.model.PatientModel;
import com.smict.person.model.TelephoneModel;
import com.smict.product.model.ProductModel;

import ldc.util.CalculateNumber;

public class Test_Patient {
	
	public static void main(String[] args){
		PatientModel patMultiModel = add_Patient_model();
		
		List<PatientModel> listPatModel = new ArrayList<PatientModel>();
		listPatModel =  new PatientData().select_patient(patMultiModel);
		for(PatientModel patModel :listPatModel){
			
			List<TelephoneModel> listTelModel = patModel.getListTelModel();
			for(TelephoneModel telModel : listTelModel){
				System.out.println("Owners "+telModel.getOwners());
				System.out.println("GroupName "+telModel.getTel_telgroupname());
			}
			
			List<AddressModel> ListAddressModel = patModel.getAddrModel();
			for(AddressModel addrModel : ListAddressModel){
				System.out.println("_____________________");
				System.out.println("Addr ID :"+addrModel.getAddr_id());
				System.out.println("Addr Province :"+addrModel.getAddr_provinceid());
			}
			
		}
	}
	
	//@Test
	public void Add_Patient(){
		
		PatientModel patMultiModel = add_Patient_model();
		
		List<PatientModel> listPatModel = new ArrayList<PatientModel>();
		listPatModel =  new PatientData().select_patient(patMultiModel);
		for(PatientModel patModel :listPatModel){
			
			List<TelephoneModel> listTelModel = patModel.getListTelModel();
			for(TelephoneModel telModel : listTelModel){
				System.out.println("Owners "+telModel.getOwners());
				System.out.println("GroupName "+telModel.getTel_telgroupname());
			}
			
			List<AddressModel> ListAddressModel = patModel.getAddrModel();
			for(AddressModel addrModel : ListAddressModel){
				System.out.println("_____________________");
				System.out.println("Addr ID :"+addrModel.getAddr_id());
				System.out.println("Addr Province :"+addrModel.getAddr_provinceid());
			}
			
		}
		
		Assert.assertTrue(!new PatientData().select_patient(patMultiModel).isEmpty());
		
	}
	
	//@Test
	public void Delete_Patient(){
		PatientModel patModel = add_Patient_model();
		new PatientData().Delete_Patient(patModel);
		Add_Multi_Address_Patient(patModel);
		Delete_MultiAddr_(patModel);
		Assert.assertTrue(new PatientData().select_patient(patModel).isEmpty());
	}
	
	//@Test
	public void Update_Patient(){
		PatientModel patModel = add_Patient_model();
		
		PatientModel updateModel = new PatientModel(patModel.getHn(), "00012", 300.98, 8000.16,
				"12", "วันดี", "ศรีสมร", "Wandee", 
				"Srisamon", "2012-05-07", "1100500506962", "1", 
				"Usability Test", "techture",patModel.getTelModel(), 
				new AddressModel(), 
				new FamilyModel(patModel.getFamModel().getFamily_id(), patModel.getFamModel().getUser_type_id(), patModel.getFamModel().getRef_user(), patModel.getFamModel().getFamily_user_status()));
		new PatientData().Update_Patient(updateModel);
		Assert.assertTrue(new PatientData().select_patient(patModel).isEmpty());
	}
	
	//@Test
	public void addPatientModel_getPatientModelFromadd(){
		PatientModel patModel = add_Patient_model();
		Add_Multi_Address_Patient(patModel);
	}
	
	public static PatientModel add_Patient_model(){
		PatientModel patModel = new PatientModel();
		
		TelephoneModel telModel = new TelephoneModel();
		List<TelephoneModel> listTelModel = new ArrayList<TelephoneModel>(); 
		
		
		
		PatientData patData = new PatientData();
		patModel.setHn(patData.PlusOneID_FormatID(patData.GetHighest_HN()));
		telModel = new TelephoneModel("0891597536", "", "", patModel.getHn(), 
				0, 1, 1);
		listTelModel.add(telModel);
		telModel = new TelephoneModel("0832156487", "", "", patModel.getHn(), 
				0, 1, 1);
		
		AddressData addrdb = new AddressData();
		List<AddressModel> ListAddressModel = new ArrayList<AddressModel>();
		ListAddressModel.add(new Test_Address().add_name());
		ListAddressModel.add(new Test_Address().add_name());
		ListAddressModel.add(new Test_Address().add_name());
		
		
		listTelModel.add(telModel);
		patModel.setRelation_emp("1154");
		patModel.setDeposit_money(2500.99);
		patModel.setFirstname_th("เศษฐพงษ์");
		patModel.setFirstname_en("Setthapong");
		patModel.setLastname_th("วงษ์กิจไพบูรณ์");
		patModel.setLastname_en("Wongkitpaisarn");
		patModel.setBirth_date("2005-05-21");
		patModel.setIdentification("1100500506962");
		patModel.setIdentification_type("1");
		patModel.setRemark("รวยถือว่าเป็นคนดี");
		patModel.setProfile_pic("/pic/HN00125");
		patModel.setAddrModel(new Test_Address().addListAddress(ListAddressModel));
		Add_Multi_Address_Patient(patModel);
		patModel.setListTelModel(new Test_Telephone().addListTelephone(listTelModel));
		//patModel.setFamModel(new Test_Family().add_Family());
		new PatientData().Add_Patient(patModel,"asdfghj");
		return patModel;
	}
	
	public static void Add_Multi_Address_Patient(PatientModel patModel){
		AddressData multiAddress = new AddressData();
		multiAddress.Add_multi_Address(patModel);
	}
	
	public void Delete_MultiAddr_(PatientModel patModel){
		AddressData multiAddress = new AddressData();
		multiAddress.Delete_multi_Address(patModel);
	}
	
	@Test
	public void addPatientBeAllergic(){
		PatientData patdata = new PatientData();
		ProductModel proModel = new ProductModel();
		List<ProductModel> productList = new ArrayList<ProductModel>();
		proModel.setProduct_id(1);
		productList.add(proModel);
		
		proModel = new ProductModel();
		proModel.setProduct_id(5);
		productList.add(proModel);
		
		
		Assert.assertEquals("ID ของการแพ้ยาไม่ตรงกับข้อมูลที่เพิ่มลง Database",new CalculateNumber().plusOneInt(patdata.getMaxPatient_Beallergic(), 1), patdata.add_multi_BeAllergic(productList));
		
	}
}
*/