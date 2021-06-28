package com.ntc.paas.common.util;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
 * AES对称加密和解密
*/
public class AESUtil {

	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";// 默认的加密算法
	private static final String KEY = "abcdefghijklmnopqrstuvwxyz0123456789"; // 默认key

	/**
	 * AES 加密操作
	 *
	 * @param content
	 * @return
	 */
	public static String encrypt(String content) {
		return encrypt(content, KEY);
	}

	/**
	 * AES 加密操作
	 *
	 * @param content
	 * @param decryptKey
	 * @return
	 */
	public static String encrypt(String content, String decryptKey) {

		try {
			KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
			keygen.init(128, new SecureRandom(decryptKey.getBytes()));
			SecretKey original_key = keygen.generateKey();
			byte[] raw = original_key.getEncoded();

			SecretKey key = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byte_encode = content.getBytes("utf-8");
			byte[] byte_AES = cipher.doFinal(byte_encode);
			String encode = new String(Base64.getEncoder().encodeToString(byte_AES));
			return encode;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @return
	 */
	public static String decrypt(String content) {
		return decrypt(content, KEY);
	}

	/**
	 * AES 解密操作
	 *
	 * @param content
	 * @param decryptKey
	 * @return
	 */
	public static String decrypt(String content, String decryptKey) {

		try {
			KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
			keygen.init(128, new SecureRandom(decryptKey.getBytes()));
			SecretKey original_key = keygen.generateKey();
			byte[] raw = original_key.getEncoded();
			SecretKey key = new SecretKeySpec(raw, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] byte_content = Base64.getDecoder().decode(content);
			byte[] byte_decode = cipher.doFinal(byte_content);
			String decode = new String(byte_decode, "utf-8");
			return decode;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {

		String content = String.valueOf(DateUtil.parseTimestamp());
		String et = encrypt(content);
		String dt = decrypt(et);
		System.out.println("加密:" + content);
		System.out.println("加密后的密文是:" + et);
		System.out.println("解密后的密文是:" + dt);

	}

}