package com.cookapp.cookapp.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Helper {
	/**
	 * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	protected static  MessageDigest messagedigest = null;

	{
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static String getFileMD5String(File file) throws IOException {
		InputStream fis;
		fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int numRead = 0;
		while ((numRead = fis.read(buffer)) > 0) {
			messagedigest.update(buffer, 0, numRead);
		}
		fis.close();
		return bufferToHex(messagedigest.digest());
	}

	public String getFileMD5String(InputStream in) throws IOException {
		byte[] buffer = new byte[1024];
		int numRead = 0;
		while ((numRead = in.read(buffer)) > 0) {
			messagedigest.update(buffer, 0, numRead);
		}
		in.close();
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换
		// 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
		char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static String stringToMD5(String string) {  
		byte[] hash;  

		try {  
			hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));  
		} catch (NoSuchAlgorithmException e) {  
			e.printStackTrace();  
			return null;  
		} catch (UnsupportedEncodingException e) {  
			e.printStackTrace();  
			return null;  
		}  

		StringBuilder hex = new StringBuilder(hash.length * 2);  
		for (byte b : hash) {  
			if ((b & 0xFF) < 0x10)  
				hex.append("0");  
			hex.append(Integer.toHexString(b & 0xFF));  
		}  

		return hex.toString();  
	}  

}
