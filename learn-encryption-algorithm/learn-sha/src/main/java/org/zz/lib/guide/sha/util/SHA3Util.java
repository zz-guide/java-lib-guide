package org.zz.lib.guide.sha.util;

import org.zz.lib.guide.hex.util.HexUtil2;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA3Util {
    public static final String SHA3_224_ALGORITHM = "SHA3-224";
    public static final String SHA3_256_ALGORITHM = "SHA3-256";
    public static final String SHA3_384_ALGORITHM = "SHA3-384";
    public static final String SHA3_512_ALGORITHM = "SHA3-512";

    private static volatile SHA3Util instance = null;

    private SHA3Util() {
        super();
    }

    public static SHA3Util getInstance() {
        if (instance == null) {
            synchronized (SHA3Util.class) {
                if (instance == null) {
                    instance = new SHA3Util();
                }
            }
        }
        return instance;
    }

    private MessageDigest getMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] sha(final byte[] src, String algorithm) {
        if (src == null || algorithm == null) {
            return null;
        }

        MessageDigest md = getMessageDigest(algorithm);
        byte[] hash = md.digest(src);
        System.out.println(algorithm + " 摘要长度 字节数:" + md.getDigestLength());
        return hash;
    }

    public String sha3_224Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA3_224_ALGORITHM));
    }

    public String sha3_224Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA3_224_ALGORITHM));
    }

    public String sha3_256Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA3_256_ALGORITHM));
    }

    public String sha3_256Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA3_256_ALGORITHM));
    }

    public String sha3_512Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA3_512_ALGORITHM));
    }

    public String sha3_512Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA3_512_ALGORITHM));
    }

    public String sha3_384Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA3_384_ALGORITHM));
    }

    public String sha3_384Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA3_384_ALGORITHM));
    }
}
