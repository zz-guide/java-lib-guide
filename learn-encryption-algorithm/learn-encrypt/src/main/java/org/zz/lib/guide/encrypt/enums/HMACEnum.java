package org.zz.lib.guide.encrypt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HMACEnum {

	SHA_256("HmacSHA256");
	
	private final String algorithm;
}
