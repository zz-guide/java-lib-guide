package org.zz.lib.guide.encrypt.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SHAEnum {
	_1("SHA-1"),
	_224("SHA-224"),
	_256("SHA-256"),
	_384("SHA-384"),
	_512("SHA-512"),
	_512_224("SHA-512/224"),
	_512_256("SHA-512/256");

	private final String algorithm;
}
