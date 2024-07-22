package org.zz.lib.guide.md5.util;

import org.zz.lib.guide.hex.util.HexUtil2;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD2Util {
    private static volatile MD2Util instance = null;

    private MD2Util() {
        super();
    }

    public static MD2Util getInstance() {
        if (instance == null) {
            synchronized (MD2Util.class) {
                if (instance == null) {
                    instance = new MD2Util();
                }
            }
        }
        return instance;
    }

    private MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance("MD2");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] md2(final byte[] original) {
        if (original == null) {
            return null;
        }

        MessageDigest md = getMessageDigest();
        byte[] res = md.digest(original);
        System.out.println("md2 摘要长度 getDigestLength() 字节数:"+md.getDigestLength());
        return res;
    }

    public byte[] md2(final String original, final Charset charset) {
        if (original == null) {
            return null;
        }
        return md2(original.getBytes(charset));
    }

    public byte[] md2(final String original) {
        if (original == null) {
            return null;
        }
        return md2(original.getBytes(StandardCharsets.UTF_8));
    }

    public String md2LowerHex(final String original, final Charset charset) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(md2(original.getBytes(charset)));
    }

    public String md2LowerHex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(md2(original.getBytes(StandardCharsets.UTF_8)));
    }

    public String md2UpperHex(final String original, final Charset charset) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexUpper(md2(original.getBytes(charset)));
    }

    public String md2UpperHex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexUpper(md2(original.getBytes(StandardCharsets.UTF_8)));
    }
}
