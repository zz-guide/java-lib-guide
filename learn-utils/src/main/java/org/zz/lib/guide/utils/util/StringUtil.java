package org.zz.lib.guide.utils.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringUtil {
    public static byte[] getBytes(String src) {
        if (src == null) {
            return null;
        }
        return src.getBytes();
    }


    public static byte[] getBytes(String src, String charset) {
        return getBytes(src, Charset.forName(charset));
    }

    public static byte[] getBytes(String src, Charset charset) {
        if (src == null) {
            return null;
        }
        return src.getBytes(charset);
    }

    public static byte[] getUTF8Bytes(String src) {
        return getBytes(src, StandardCharsets.UTF_8);
    }
}
