package test.String;

import org.junit.Test;

public class StringTest {

	public void testSplit(){
		String str = "65456(#:)98789";
		String[] strSplit = str.split("(#:)");
		System.out.println(strSplit[0].substring(0, strSplit[0].length()-1));
		System.out.println(strSplit[1].substring(1));
		
		for(String s : str.split("(#:)")){
			System.out.println(s);
		}
		
	}
	
	@Test
	public void testSplit2(){
		String str = "65456:#:98789";
		String[] strSplit = str.split(":#:");
		System.out.println(strSplit[0]);
		System.out.println(strSplit[1]);
	}
}
