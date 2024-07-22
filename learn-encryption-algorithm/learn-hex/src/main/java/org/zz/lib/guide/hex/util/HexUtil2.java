package org.zz.lib.guide.hex.util;

import java.math.BigInteger;

public class HexUtil2 {
    public static String bytes2HexUpper(byte[] input) {
        return bytes2Hex(input).toUpperCase();
    }

    public static String bytes2HexLower(byte[] input) {
        return bytes2Hex(input).toLowerCase();
    }

    public static String bytes2Hex(byte[] input) {
        String hexString = new BigInteger(1, input).toString(16);

        // 计算需要补充的零数
        int zerosToPad = (input.length * 2) - hexString.length();

        // 如果需要补充零，则补充它们
        if (zerosToPad > 0) {
            hexString = String.format("%0" + zerosToPad + "d", 0) + hexString;
        }

        return hexString;
    }
}
