package org.zz.lib.guide.encrypt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RSAEnum {
	/**
	 * 无向量加密模式, PKCS1Padding模式填充
	 */
	ECB_PKCS1PADDING("RSA/ECB/PKCS1Padding"),
	/**
	 * 无向量加密模式, SHA-1摘要 + MGF1方式填充
	 */
	ECB_OAEP_WITH_SHA1_AND_MGF_1PADDING("RSA/ECB/OAEPWithSHA-1AndMGF1Padding"),
	/**
	 * 无向量加密模式, SHA-256摘要 + MGF1方式填充
	 */
	ECB_OAEP_WITH_SHA256_AND_MGF_1PADDING("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

	private final String algorithm;
}
