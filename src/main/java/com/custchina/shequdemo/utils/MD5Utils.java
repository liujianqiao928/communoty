package com.custchina.shequdemo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

//import org.apache.commons.codec.binary.Hex;

/**
 * @author hh@163.com:
 * @version 创建时间：2018-3-20 下午2:40:13
 * @introduction
 */
public class MD5Utils {

	private static String Salt = null;

	/**
	 * 基本的MD5加密方式,不常用
	 */
	public static String getStrMD5(String inStr) {
		// 获取MD5实例
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println(e.toString());
			return "";
		}

		// 将加密字符串转换为字符数组
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		// 开始加密
		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] digest = md5.digest(byteArray);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			int var = digest[i] & 0xff;
			if (var < 16)
				sb.append("0");
			sb.append(Integer.toHexString(var));
		}
		return sb.toString();
	}

	/**
	 * MD5+自定义盐加密方式.
	 */
	public static String getStrrMD5(String password) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte strTemp[] = password.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte md[] = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 15];
				str[k++] = hexDigits[byte0 & 15];
			}

			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * MD5双重解密
	 */
	public static String getconvertMD5(String inStr) {
		char[] charArray = inStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			charArray[i] = (char) (charArray[i] ^ 't');
		}
		String str = String.valueOf(charArray);
		return str;
	}

	/**
//	 * 使用Apache的Hex类实现Hex(16进制字符串和)和字节数组的互转
//	 */
//	@SuppressWarnings("unused")
//	private static String md5Hex(String str) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			byte[] digest = md.digest(str.getBytes());
//			return new String(new Hex().encode(digest));
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.toString());
//			return "";
//		}
//	}

	/**
	 * MD5+Salt方式.加盐
	 */
//	public static String getSaltMD5(String password) {
//		// 生成一个16位的随机数
//		Random random = new Random();
//		StringBuilder sBuilder = new StringBuilder(16);
//		sBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
//		int len = sBuilder.length();
//		if (len < 16) {
//			for (int i = 0; i < 16 - len; i++) {
//				sBuilder.append("0");
//			}
//		}
//		// 生成最终的加密盐
//		Salt = sBuilder.toString();
//
//		System.out.println(Salt + "====生成最终的盐");
//
//		password = md5Hex(password + Salt);
//		char[] cs = new char[48];
//		for (int i = 0; i < 48; i += 3) {
//			cs[i] = password.charAt(i / 3 * 2);
//			char c = Salt.charAt(i / 3);
//			cs[i + 1] = c;
//			cs[i + 2] = password.charAt(i / 3 * 2 + 1);
//		}
//		return String.valueOf(cs);
//	}

	/**
	 * 验证加盐后是否和原文一致
	 */
//	public static boolean getSaltverifyMD5(String password, String md5str) {
//		char[] cs1 = new char[32];
//		char[] cs2 = new char[16];
//		for (int i = 0; i < 48; i += 3) {
//			cs1[i / 3 * 2] = md5str.charAt(i);
//			cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
//			cs2[i / 3] = md5str.charAt(i + 1);
//		}
//		String Salt = new String(cs2);
//		System.out.println(Salt + "====盐");
//		return md5Hex(password + Salt).equals(String.valueOf(cs1));
//	}
//
/**
	 * 获取盐
	 * 
	 * @return
	 */
	public static String getSalt() {
		return Salt;
	}

	public static void main(String[] args) {
//		MD5Utils md = new MD5Utils();
//		String strMD5 = new String("12345");
//
//		System.out.println("原始：" + strMD5);
//		System.out.println("东东的：" + md.getStrrMD5(strMD5));
//		System.out.println("MD5后：" + md.getStrMD5(strMD5));
//		System.out.println("加密的：" + md.getconvertMD5(strMD5));
//		System.out.println("解密的：" + md.getconvertMD5(md.getconvertMD5(strMD5)));
//
//		System.out.println("\t\t=======================================");
//		// 原文
//		String plaintext = "123";
//		// plaintext = "123456";
//		System.out.println("原始：" + plaintext);
//		System.out.println("普通MD5后：" + MD5Utils.getStrMD5(plaintext));
//		// 获取加盐后的MD5值
//		String ciphertext = MD5Utils.getSaltMD5(plaintext);
//		System.out.println("加盐后MD5：" + ciphertext);
//		System.out.println("是否是同一字符串:" + MD5Utils.getSaltverifyMD5(plaintext, ciphertext));
//		System.out.println("盐" + Salt);

	}

}