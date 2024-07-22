package org.zz.lib.guide.hex.util;

public class HexUtil3 {
    public static String bytes2Hex(byte[] input) {
        StringBuilder stringBuffer = new StringBuilder();

        for (byte b : input) {
            stringBuffer.append(String.format("%02X", b));
        }

        return stringBuffer.toString();
    }

    public static String bytes2HexUpper(byte[] input) {
        return bytes2Hex(input).toUpperCase();
    }

    public static String bytes2HexLower(byte[] input) {
        return bytes2Hex(input).toLowerCase();
    }
}
