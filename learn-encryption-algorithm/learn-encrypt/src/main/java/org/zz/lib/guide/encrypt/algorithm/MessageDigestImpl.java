package org.zz.lib.guide.encrypt.algorithm;

import org.apache.commons.codec.binary.Base64;
import org.zz.lib.guide.common.util.HexUtil2;

/**
 * 使用MessageDigest进行加密
 */
public abstract class MessageDigestImpl<E extends Enum<?>> implements IMessageDigest<E> {
	/**
	 * 配置文件配置的盐
	 */
	protected String configSalt = null;
	/**
	 * 默认加密模式
	 */
	protected E algorithm = null;

	protected abstract byte[] encrypt(String content, String salt, E algorithm);

	protected void checkParams() {
		if (algorithm == null) {
			throw new IllegalArgumentException("具体的算法缺失");
		}
	}

	@Override
	public String encryptBase64(String content) {
		return encryptBase64(content, configSalt, algorithm);
	}

	@Override
	public String encryptBase64(String content, String salt) {
		return encryptBase64(content, salt, algorithm);
	}

	@Override
	public String encryptBase64(String content, E encryptType) {
		return encryptBase64(content, configSalt, encryptType);
	}

	@Override
	public String encryptBase64(String content, String salt, E encryptType) {
		byte[] result = encrypt(content, salt, encryptType);
		return Base64.encodeBase64String(result);
	}

	@Override
	public String encryptHex(String content) {
		return encryptHex(content, configSalt, algorithm);
	}

	@Override
	public String encryptHex(String content, String salt) {
		return encryptHex(content, salt, algorithm);
	}

	@Override
	public String encryptHex(String content, E encryptType) {
		return encryptHex(content, configSalt, encryptType);
	}

	@Override
	public String encryptHex(String content, String salt, E encryptType) {
		byte[] result = encrypt(content, salt, encryptType);
		return HexUtil2.bytes2HexLower(result);
	}
}
