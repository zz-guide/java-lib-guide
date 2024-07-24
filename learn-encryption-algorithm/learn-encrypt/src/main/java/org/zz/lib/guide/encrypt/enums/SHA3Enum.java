package org.zz.lib.guide.encrypt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SHA3Enum {
	_224("SHA3-224"),
	_256("SHA3-256"),
	_384("SHA3-384"),
	_512("SHA3-512");

	private final String algorithm;
}
