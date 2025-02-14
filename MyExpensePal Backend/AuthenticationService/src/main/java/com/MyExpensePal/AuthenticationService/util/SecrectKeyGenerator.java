package com.MyExpensePal.AuthenticationService.util;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;


//Use This class to get secret key only once and store it in your application.properties
//If it changed the previously created JWT gets invalid
public class SecrectKeyGenerator {

	public static void generateSecretKey() {
		
		String secretKey="";
		try {
			//keyGenerator.generateKey() return binary object
			//.getEncoded() makes it into byte array
			//encodeToString makes it into actual text
			KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
			secretKey = Base64.getEncoder().encodeToString(generator.generateKey().getEncoded());
			System.out.println(secretKey);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		generateSecretKey();
	}

}
