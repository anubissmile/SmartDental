package ldc.util;

public class Encrypted {
	public String encrypt(String x) throws Exception {		
		String storepass = "";		
		try {
			java.security.Security.addProvider(new sun.security.provider.Sun());
			java.security.MessageDigest lMessageDigest = java.security.MessageDigest.getInstance("SHA", "SUN");
			byte[] _result = lMessageDigest.digest(x.getBytes());
			storepass = new sun.misc.BASE64Encoder().encode(_result);

		} catch (java.security.NoSuchProviderException nspe) {
			
		}
		return storepass;
	}
}
