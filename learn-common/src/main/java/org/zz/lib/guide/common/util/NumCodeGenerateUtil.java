package org.zz.lib.guide.common.util;

import java.util.Random;

public class NumCodeGenerateUtil {
    private static final String SYMBOLS =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateNonceStr(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length必须大于0");
        }
        Random random = new Random();
        char[] nonceChars = new char[length];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(random.nextInt(0, SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
}
