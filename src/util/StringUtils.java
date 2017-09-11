/**
 * Script Name   : <b>StringUtils</b>
 * Module Name   : <b>Common</b>
 * Generated     : <b>Feb 11, 2008 1:49:17 PM</b>
 * Description   : Functional Test Script
 * Original Host : WinNT Version 5.0  Build 2195 (S)
 *
 * @since 2008/02/11
 * @author Srinivas.Kyahtam <E-mail : srinivas.kyatham@citi.com>
 **/
package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

/**
 * StringUtils
 */
public class StringUtils {
	/**
	 * compare
	 * 
	 * @param str1
	 *            String
	 * @param str2
	 *            String
	 * 
	 * @return boolean
	 */
	public static boolean compare(String str1, String str2) {
		if (str1.compareTo(str2) == 0) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean compareIgnoreCase(String str1, String str2) {
		if (str1.compareToIgnoreCase(str2) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * removeCommaFromString
	 * 
	 * @param sSring
	 *            String
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public static String removeCommaFromString(String sSring) throws Exception {
		StringBuffer sb = new StringBuffer(sSring);

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == ',') {
				sb.deleteCharAt(i);
			}
		}

		return sb.toString().trim();
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param length
	 *            DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public static String generateRandomString(int length) {
		String sKey = "";
		Random random = new Random(System.currentTimeMillis());
		long r1 = random.nextLong();
		long r2 = random.nextLong();
		String hash1 = Long.toHexString(r1);
		String hash2 = Long.toHexString(r2);
		sKey = hash1 + hash2;

		if (sKey.length() > length) {
			sKey = sKey.substring(0, length);
		}

		return sKey.toUpperCase();
	}

	public static String generateRandomNumber(int length) {
		String sKey = "";
		Random random = new Random();
		long r1 = random.nextLong();

		r1 = Math.abs(r1);
		sKey = String.valueOf(r1);
		if (sKey.length() > length) {
			sKey = sKey.substring(0, length);
		} else if (sKey.length() < length) {
			sKey = sKey + sKey;
			sKey = sKey.substring(0, length);
		}

		return sKey.toString();

	}
	public static String getStatus(boolean bBool){
		if(bBool==true){
			return "PASS";
		}
		else
		{
			return "FAIL";
		}
	}
	public static String getKeyValue(String sFilePath, String sKey) throws IOException{
		FileInputStream inputstream = new FileInputStream(sFilePath);
		Properties pro = new Properties();
		pro.load(inputstream);
		String sValue =pro.getProperty(sKey);
		return sValue.trim();
	}
	
	public static void  pritnAllKeyValues(String sFilePath) throws IOException{
		FileInputStream inputstream = new FileInputStream(sFilePath);
		Properties pro = new Properties();
		pro.load(inputstream);
		Set<Object> keys = pro.keySet();
//		String sValue =pro.getProperty(sKey);
		 
	        for(Object k:keys){
	            String key = (String)k;
	            System.out.println(key+": "+pro.getProperty(key));
	        }
	}
	
	

}
