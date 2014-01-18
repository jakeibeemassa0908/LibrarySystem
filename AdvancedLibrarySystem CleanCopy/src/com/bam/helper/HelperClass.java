package com.bam.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringEscapeUtils.*;

public class HelperClass {

	public static ArrayList<String> validate(Map <String, String[]> map){
		ArrayList<String> error= new ArrayList<String>();
		for (Map.Entry<String, String[]> entry : map.entrySet())
		{
			if(entry.getValue()[0].isEmpty() || entry.getValue()[0].equals("None")){
				error.add("This Field can't be left empty: "+entry.getKey());
			}
		}
		return error;
	}
	
	public static boolean matchPasswords(String password, String repassword){
		if(password.equals(repassword)){
			return true;
		}
		return false;
	}
	
	public  static String byteArrayToHexString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}
	
	
	public static String toSHA1(byte[] convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return byteArrayToHexString(md.digest(convertme));
	}
	
	public static  String escapeHtml(String toEscape){
		String escaped= escapeHtml4(toEscape);
		return escaped;
	}
	
	public static String generateRandomWord()
	{
	    String randomString = new String();
	    Random random = new Random();
	        char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
	        for(int j = 0; j < word.length; j++)
	        {
	            word[j] = (char)('a' + random.nextInt(26));
	        }
	        randomString = new String(word);
	        
	    return randomString;
	}
	
	public static Date getDateAhead(int hours){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY,hours);
		return cal.getTime();
	}
	
	public static String generateSaltedEncrypted(String password,String salt){
		String saltedPassword=HelperClass.toSHA1((password+salt).getBytes());
		return saltedPassword;
	}
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		public static boolean validateEmail(String emailStr) {
		        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		        return matcher.find();
		}
}

