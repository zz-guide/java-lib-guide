package org.zz.lib.guide.common.util;

public class HexUtil4 {

    private static final char[] HEX_DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] HEX_DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String bytes2Hex(byte[] input, char[] HEX_DIGITS) {
        if (input == null) {
            return null;
        }

        char[] hexChars = new char[input.length * 2];
        for (int j = 0; j < input.length; j++) {
            int v = input[j] & 0xFF;
            hexChars[j * 2] = HEX_DIGITS[v >>> 4];
            hexChars[j * 2 + 1] = HEX_DIGITS[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String bytes2HexUpper(byte[] input) {
        return bytes2Hex(input, HEX_DIGITS_UPPER);
    }

    public static String bytes2HexLower(byte[] input) {
        return bytes2Hex(input, HEX_DIGITS_LOWER);
    }

    /**
     * 十六进制转化为二进制
     */
    public static byte[] hexStrToByteArray(String hexString) {
        if (hexString == null) {
            return null;
        }
        if (hexString.isEmpty()) {
            return new byte[0];
        }

        byte[] byteArray = new byte[hexString.length() / 2];
        for (int i = 0; i < byteArray.length; i++) {
            String subStr = hexString.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }
}
