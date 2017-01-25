package test.com.smict.person;

import org.junit.Assert;
import org.junit.Test;

import com.smict.person.data.FamilyData;
import com.smict.person.model.FamilyModel;

public class Test_Family {
	FamilyData famData = new FamilyData();
	@Test
	public void add_Family_EMP(){
		FamilyModel famModel = add_Family();
		Assert.assertEquals(famModel.getFamily_id(), famData.select_Family_rModel(famModel).getFamily_id());
	}
	
	@Test
	public void update_Family(){
		FamilyModel famModel = add_Family();
		
		FamilyModel famModel_Update = new FamilyModel();
		famModel_Update.setFamily_id(famModel.getFamily_id());
		famModel_Update.setUser_type_id(1);
		famModel_Update.setRef_user("554812");
		famModel_Update.setFamily_user_status("สมาชิกครอบครัว");
		famData.update_Family(famModel_Update);
		
		FamilyModel ReturnModel = new FamilyModel();
		ReturnModel = famData.select_Family_rModel(famModel_Update);
		Assert.assertEquals(famModel_Update.getFamily_id(), ReturnModel.getFamily_id());
		Assert.assertEquals(famModel_Update.getRef_user(), ReturnModel.getRef_user());
		Assert.assertEquals(famModel_Update.getUser_type_id(), ReturnModel.getUser_type_id());
		Assert.assertEquals(famModel_Update.getFamily_user_status(), ReturnModel.getFamily_user_status());
	}
	
	@Test
	public void delete_Family(){
		FamilyModel famModel = add_Family();
		famData.Delete_Telephone(famModel);
		Assert.assertTrue(famData.select_Family(famModel).isEmpty());;
	}
	
	public FamilyModel add_Family(){
		FamilyModel famModel = new FamilyModel();
		famModel.setFamily_id(0);
		famModel.setRef_user("HN00159");
		famModel.setUser_type_id(2);
		famModel.setFamily_user_status("หัวหน้าครอบครัว");
		int family_id = famData.PlusOne(famData.Gethight_familyID());
		famModel.setFamily_id(family_id);
		famData.add_family(famModel);
		return famModel;
	}
}
