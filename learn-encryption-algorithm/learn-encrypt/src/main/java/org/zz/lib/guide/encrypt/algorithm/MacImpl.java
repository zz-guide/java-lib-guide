package org.zz.lib.guide.encrypt.algorithm;

import org.apache.commons.codec.binary.Base64;
import org.zz.lib.guide.common.util.HexUtil2;

/**
 * 使用Mac进行加密
 */
public abstract class MacImpl<E extends Enum<?>> {
	/**
	 * 默认加密模式
	 */
	protected E algorithm = null;

	protected abstract byte[] encrypt(String content, String key, E algorithm);

	protected void checkParams() {
		if (algorithm == null) {
			throw new IllegalArgumentException("具体的算法缺失");
		}
	}

	public String encryptBase64(String content, String key) {
		return encryptBase64(content, key, algorithm);
	}

	public String encryptBase64(String content, String key, E algorithm) {
		byte[] result = encrypt(content, key, algorithm);
		return Base64.encodeBase64String(result);
	}

	public String encryptHex(String content, String key) {
		return encryptHex(content, key, algorithm);
	}


	public String encryptHex(String content, String key, E algorithm) {
		byte[] result = encrypt(content, key, algorithm);
		return HexUtil2.bytes2HexLower(result);
	}
}
