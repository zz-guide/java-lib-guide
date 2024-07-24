package org.zz.lib.guide.encrypt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MDEnum {
	MD2("MD2"),
	MD5("MD5");

	private final String algorithm;
}
