package org.zz.lib.guide.md5.util;

import org.zz.lib.guide.hex.util.HexUtil2;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static volatile MD5Util instance = null;

    private MD5Util() {
        super();
    }

    public static MD5Util getInstance() {
        if (instance == null) {
            synchronized (MD5Util.class) {
                if (instance == null) {
                    instance = new MD5Util();
                }
            }
        }
        return instance;
    }

    private MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] md5(final byte[] original) {
        if (original == null) {
            return null;
        }

        MessageDigest md = getMessageDigest();
        byte[] res = md.digest(original);
        System.out.println("md5 摘要长度 getDigestLength() 字节数:"+md.getDigestLength());
        return res;
    }

    public byte[] md5(final String original, final Charset charset) {
        if (original == null) {
            return null;
        }
        return md5(original.getBytes(charset));
    }

    public byte[] md5(final String original) {
        if (original == null) {
            return null;
        }
        return md5(original.getBytes(StandardCharsets.UTF_8));
    }

    public String md5LowerHex(final String original, final Charset charset) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(md5(original.getBytes(charset)));
    }

    public String md5LowerHex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(md5(original.getBytes(StandardCharsets.UTF_8)));
    }

    public String md5UpperHex(final String original, final Charset charset) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexUpper(md5(original.getBytes(charset)));
    }

    public String md5UpperHex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexUpper(md5(original.getBytes(StandardCharsets.UTF_8)));
    }
}
