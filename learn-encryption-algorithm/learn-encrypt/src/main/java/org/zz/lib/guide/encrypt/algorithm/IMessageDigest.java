package org.zz.lib.guide.encrypt.algorithm;

/**
 * MessageDigest加密的接口
 * 因为加密后的 byte[] 直接转字符串可能是乱码，所以大致分为2种
 * 1. base64 string
 * 2. hex string
 */
public interface IMessageDigest<E extends Enum<?>> {
	String encryptBase64(String content);
	String encryptBase64(String content, String salt);
	String encryptBase64(String content, E encryptType);
	String encryptBase64(String content, String salt, E encryptType);
	
	String encryptHex(String content);
	String encryptHex(String content, String salt);
	String encryptHex(String content, E encryptType);
	String encryptHex(String content, String salt, E encryptType);
}
