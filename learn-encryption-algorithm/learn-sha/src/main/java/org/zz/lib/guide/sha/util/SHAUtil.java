package org.zz.lib.guide.sha.util;

import org.zz.lib.guide.hex.util.HexUtil2;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
    public static final String SHA_1_ALGORITHM = "SHA-1";
    public static final String SHA_224_ALGORITHM = "SHA-224";
    public static final String SHA_256_ALGORITHM = "SHA-256";

    public static final String SHA_384_ALGORITHM = "SHA-384";
    public static final String SHA_512_ALGORITHM = "SHA-512";
    public static final String SHA_512_224_ALGORITHM = "SHA-512/224";
    public static final String SHA_512_256_ALGORITHM = "SHA-512/256";

    private static volatile SHAUtil instance = null;

    private SHAUtil() {
        super();
    }

    public static SHAUtil getInstance() {
        if (instance == null) {
            synchronized (SHAUtil.class) {
                if (instance == null) {
                    instance = new SHAUtil();
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

    public String sha1Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA_1_ALGORITHM));
    }

    public String sha1Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA_1_ALGORITHM));
    }

    public String sha224Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA_224_ALGORITHM));
    }

    public String sha224Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA_224_ALGORITHM));
    }


    public String sha256Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA_256_ALGORITHM));
    }

    public String sha256Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA_256_ALGORITHM));
    }

    public String sha384Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA_384_ALGORITHM));
    }

    public String sha384Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA_384_ALGORITHM));
    }

    public String sha512Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA_512_ALGORITHM));
    }

    public String sha512Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA_512_ALGORITHM));
    }

    public String sha_512_224_Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA_512_224_ALGORITHM));
    }

    public String sha_512_224_Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA_512_224_ALGORITHM));
    }

    public String sha_512_256_Hex(final byte[] original) {
        if (original == null) {
            return null;
        }

        return HexUtil2.bytes2HexLower(sha(original, SHA_512_256_ALGORITHM));
    }

    public String sha_512_256_Hex(final String original) {
        if (original == null) {
            return null;
        }
        return HexUtil2.bytes2HexLower(sha(original.getBytes(StandardCharsets.UTF_8), SHA_512_256_ALGORITHM));
    }
}
