package com.co.dgc.uadmin.util;

import java.util.Base64;

public class PasswordUtil {
	
	public static String NUMEROS = "0123456789";
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	
	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}
 
	public static String getPassword() {
		return getPassword(8);
	}
 
	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}
 
	public static String getPassword(String key, int length) {
		String pswd = "";
		for (int i = 0; i < length; i++) {
			pswd+=(key.charAt((int)(Math.random() * key.length())));
		}
		return pswd;
	}
	
	public static String encodePassword(String input) {
		String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
		return encodedString;
	}
	
	public static String dencodePassword(String input) {
		byte[] result = Base64.getDecoder().decode(input);
		String dencodedString = new String(result);
		return dencodedString;
	}

}
