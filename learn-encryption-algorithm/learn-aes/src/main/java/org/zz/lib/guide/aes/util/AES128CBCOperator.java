package org.zz.lib.guide.aes.util;

import org.zz.lib.guide.utils.util.RandomUtil;

/**
 * AES-128-CBC 加密模式
 * 加密用的Key需要为16位,可以用26个字母和数字组成.
 */

public class AES128CBCOperator extends AESOperator{
    private final static int KEY_LENGTH = 16;
    private final static String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public AES128CBCOperator() {
        super(TRANSFORMATION, RandomUtil.randomAlphanumeric(KEY_LENGTH), KEY_LENGTH, RandomUtil.randomAlphanumeric(KEY_LENGTH));
    }

    public AES128CBCOperator(String key, String ivv) {
        super(TRANSFORMATION, key, KEY_LENGTH, ivv);
    }
}
