package test.com.smict.product;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({Test_ProductUnit.class,Test_ProductBrand.class,Test_Productgroup.class,Test_Producttype.class})
public class RunAllTest {

}
