package com.example.demo.untils;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptUtil {
	public static String encrypt(String origin) {
		return BCrypt.hashpw(origin, BCrypt.gensalt());
	}
	
	public static boolean check(String origin,
		String encrypted) {
		return BCrypt.checkpw(origin, encrypted);
	}
}
