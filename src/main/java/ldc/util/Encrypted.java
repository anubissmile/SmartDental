package ldc.util;

import java.security.*;
import sun.misc.*;

public class Encrypted {
	public String encrypt(String x) throws Exception {		
		String storepass = "";		
		try {
			Security.addProvider(new sun.security.provider.Sun());
			MessageDigest lMessageDigest = MessageDigest.getInstance("SHA", "SUN");
			byte[] _result = lMessageDigest.digest(x.getBytes());
			storepass = new BASE64Encoder().encode(_result);

		} catch (NoSuchProviderException nspe) {
			
		}
		return storepass;
	}
}
