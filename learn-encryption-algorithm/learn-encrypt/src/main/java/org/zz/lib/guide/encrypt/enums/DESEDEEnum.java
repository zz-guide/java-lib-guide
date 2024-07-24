package org.zz.lib.guide.encrypt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DESEDEEnum {
	/**
	 * 有向量加密模式, 不足8位用0补足8位, 需代码给加密内容添加0, 如{65,65,65,0,0,0,0,0}
	 */
	CBC_NO_PADDING("DESede/CBC/NoPadding"),
	/**
	 * 有向量加密模式, 不足8位用余位数补足8位, 如{65,65,65,5,5,5,5,5}或{97,97,97,97,97,97,2,2};
	 * 刚好8位补8位8
	 */
	CBC_PKCS5PADDING("DESede/CBC/PKCS5Padding"),
	/**
	 * 无向量加密模式, 不足8位用0补足8位, 需代码给加密内容添加0
	 */
	ECB_NO_PADDING("DESede/ECB/NoPadding"),
	/**
	 * 无向量加密模式, 不足8位用余位数补足8位
	 */
	ECB_PKCS5PADDING("DESede/ECB/PKCS5Padding");

	private final String algorithm;
}
