package com.xifeng.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	private static final String ENCRYPTION_ALGORITHM_ = "AES";
	private static final String HASH_ALGORITHM_ = "SHA1PRNG";
	private static final String MSG_ENCODE = "utf-8";
	private static final int KEY_LENGTH = 128;
	private static final String SALT = "$#@Sfdf43232/.xnyZio3$324l234JFDWW1!FFqwe";
	
	
	/*    
     * 转换密钥< br>    
     * @param password    
     * @return    
     * @throws Exception    
     */    
    private static SecretKeySpec toKey(String key) throws Exception { 
        key = key + SALT;
    	KeyGenerator kgen = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM_);
		SecureRandom secureRandom = SecureRandom.getInstance(HASH_ALGORITHM_);     
        secureRandom.setSeed(key.getBytes());     
		kgen.init(KEY_LENGTH, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, ENCRYPTION_ALGORITHM_);
		return secretKeySpec;
    }
	

	/**
	 * 加密
	 * 
	 * @param msg 需要加密的内容
	 * @param key 加密密钥
	 * @return byte[] 加密后的字节数组
	 */
	public static byte[] encrypt(String msg, String key) {
		try {
			SecretKeySpec secretKeySpec = toKey(key);
			Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_);// 创建密码器
			byte[] byteContent = msg.getBytes(MSG_ENCODE);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);	//初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] encryptNotSalt(String msg, String key) {
		try {
			SecretKeySpec secretKeySpec =  new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// 创建密码器
			byte[] byteContent = msg.getBytes(MSG_ENCODE);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);	//初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param content需要加密的内容
	 * @param password加密密钥
	 * @return String 加密后的字符串
	 */
	public static String encryptToStr(String content, String password) {
		return parseByte2HexStr(encrypt(content, password));
	}
	
	public static String encryptToStrNotSalt(String content, String password) {
		return parseByte2HexStr(encryptNotSalt(content, password));
	}
	
	/**
     * @param content需要加密的内容
     * @param password加密密钥
     * @return String 加密后的字符串
     */
    public static String encryptToBase64(String content, String password) {
        String s =  encryptToStr(content,password);
        return Base64Coder.encodeString(s);
    }

	/**
	 * 解密
	 * 
	 * @param content待解密内容
	 * @param keyWord解密密钥
	 * @return byte[]
	 */
	public static byte[] decrypt(byte[] msg, String key) {
		try {
			SecretKeySpec secretKeySpec = toKey(key);
			Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM_);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
			byte[] result = cipher.doFinal(msg);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] decryptNotSalt(byte[] msg, String key) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);// 初始化
			byte[] result = cipher.doFinal(msg);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param content
	 *            待解密内容(字符串)
	 * @param keyWord
	 *            解密密钥
	 * @return byte[]
	 */
	public static byte[] decrypt(String content, String keyWord) {
		return decrypt(parseHexStr2Byte(content), keyWord);
	}
	
	public static byte[] decryptNotSalt(String content, String keyWord) {
		return decryptNotSalt(parseHexStr2Byte(content), keyWord);
	}
	
	/**
     * @param content
     *            待解密内容(字符串)
     * @param keyWord
     *            解密密钥
     * @return byte[]
     */
    public static String decryptToStr(String content, String keyWord) {
        return new String(decrypt(content, keyWord));
    }
	
    public static String decryptToStrNotSalt(String content, String keyWord) {
        return new String(decryptNotSalt(content, keyWord));
    }
	
	
	/**
     * @param content
     *            待解密内容(字符串)
     * @param keyWord
     *            解密密钥
     * @return byte[]
     */
    public static String decryptToStrFromBase64(String content, String keyWord) {
        String msg = Base64Coder.decodeString(content);
        return decryptToStr(msg, keyWord);
    }

	/**
	 * 将二进制转换成16进制
	 * @param buf
	 * @return String
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * @param hexStr
	 * @return byte[]
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) {
	
	    String content = "xiezhb手动";  
	    String password = "ddddd333";
	    //加密  
	    System.out.println("加密前：" + content);  
	    byte[] encryptResult = encrypt(content, password);  
	    String encryptResultStr = parseByte2HexStr(encryptResult);  
	    System.out.println("加密后：" + encryptResultStr);  
	    //解密  
	    byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);  
	    byte[] decryptResult = decrypt(decryptFrom,password);  
	    System.out.println("解密后：" + new String(decryptResult));  
	    
	    String en = encryptToBase64(content, password);
	    System.out.println("加密后：" + en);  
	    System.out.println("解密后：" + decryptToStrFromBase64(en, password));  
	}
}
