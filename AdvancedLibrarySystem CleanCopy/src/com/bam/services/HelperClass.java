package com.bam.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.apache.commons.lang3.StringEscapeUtils.*;

public class HelperClass {

	public ArrayList<String> validate(Map <String, String[]> map){
		
		ArrayList<String> error= new ArrayList<String>();
		
		for (Map.Entry<String, String[]> entry : map.entrySet())
		{
			if(entry.getValue()[0].isEmpty() || entry.getValue()[0].equals("None")){
				error.add("This Field can't be left empty: "+entry.getKey());
			}
		}
		return error;
	}
	
	public boolean matchPasswords(String password, String repassword){
		if(password.equals(repassword)){
			return true;
		}
		return false;
	}
	
	public  String byteArrayToHexString(byte[] b) {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  return result;
		}
	
	
	public  String toSHA1(byte[] convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return byteArrayToHexString(md.digest(convertme));
	}
	public String escapeHtml(String toEscape){
		
		String escaped= escapeHtml4(toEscape);
		return escaped;
	}
	
}
