package com.test168;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class NumberTelephone {
	 
	 
/*	public static void main(String args[]) {
		
		String noTelephone = "0866588345";
		int index_i = 2, index_j = 5;
		String[] forNum = {"123","234","345","456","567","678","789"};
		List<String> forNo = new ArrayList<>();
		for(int c=0; c<6; c++) {
			
			forNo.add(noTelephone.substring(index_i, index_j)); 
			
			index_i++;
			index_j++;
		} 
		
		boolean check_no_sort = false;
		for(String telno : forNo) {
			 
			for(String ctelno : forNum) {
				if(telno.equals(ctelno)) {
					check_no_sort = true; 
					break;
				} 
			}  
			if(check_no_sort) {
				break;
			}
		}
		
		System.out.println(check_no_sort);
		
	} */
	
	/*public String triple (String args[]) {
		
		String noTelephone = "0861234567";
		int index_i = 3, index_j = 5;
		String[] forNum = {"111","222","333","444","555","666","777","888","999","000"};
		List<String> forNo = new ArrayList<>();
		for(int c=0; c==7; c++) {
			
			forNo.add(noTelephone.substring(index_i, index_j));
			
			index_i++;
			index_j++;
		}
		
		
		for(String telno : forNo) {
			
		}
		
		return noTelephone;
		
	}*/
	/*@Test
	public static void main( String args[] ) {
		
		String str = "0658950125";
	      
	    StringBuilder sb = new StringBuilder();
	    boolean found = false;
	    for(char c : str.toCharArray()){
	        if(Character.isDigit(c)){
	            sb.append(c);
	            found = true;
	        } else if(found){
	            // If we already found a digit before and this char is not a digit, stop looping
	            break;                
	        }
	    }

	    System.out.println("Found value: " + found );
	}*/
	
//	@Test
	public static void main( String args[] ) {
	     //List<String> listItem = 
	      String line = "085";
	      String pattern = "[1]{3,}|[2]{3,}|[3]{3,}|[4]{3,}|[5]{3,}|[6]{3,}|[7]{3,}|[8]{3,}|[9]{3,}|[0]{3,}";
  
	      Pattern p = Pattern.compile(pattern);
	      Matcher matcher = p.matcher(line);
	      if (matcher.find()) {
	          System.out.println(matcher.group(0));
	      }else {
	    	  System.out.println("NO MATCH");
	      }
	     
	   }
	/*public static void main( String args[] ) {
	     //List<String> listItem = 
	      String line = "0892463789";
	      String pattern = "[1&2&3]{1,}";
 
	      Pattern p = Pattern.compile(pattern);
	      Matcher matcher = p.matcher(line);
	      if (matcher.find()) {
	          System.out.println(matcher.group(0));
	      }else {
	    	  System.out.println("NO MATCH");
	      }
	     
	   }
	*/
}
