package com.Asika.demo.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordMD5 {
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		int hashInteractions = 1024;
		String salt="1";
		String pwd = "maodian1234";
		String result = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt), hashInteractions).toHex();
		System.out.println(result);
	}
	public static String passwordEncipher(String userId,String passWord) {
		String hashAlgorithmName = "MD5";
		int hashInteractions = 1024;
		String salt="1";
		String pwd = "maodian1234";
		String result = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt), hashInteractions).toHex();
		return result;
	}
}
