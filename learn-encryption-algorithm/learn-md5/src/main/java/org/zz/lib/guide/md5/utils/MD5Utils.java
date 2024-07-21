package org.zz.lib.guide.md5.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    private static volatile MD5Utils instance = null;

    private MD5Utils() {
        super();
    }

    public static MD5Utils getInstance() {
        if (instance == null) {
            synchronized (MD5Utils.class) {
                if (instance == null) {
                    instance = new MD5Utils();
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
        System.out.println("md getDigestLength():"+md.getDigestLength());
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

    public String md5hex(final byte[] original) {
        return new BigInteger(1, md5(original)).toString(16);
    }

    public String md5hex(final String original, final Charset charset) {
        if (original == null) {
            return null;
        }
        return md5hex(original.getBytes(charset));
    }

    public String md5hex(final String original) {
        if (original == null) {
            return null;
        }
        return md5hex(original.getBytes(StandardCharsets.UTF_8));
    }

    public byte[] md5(final InputStream original) throws IOException {
        if (original == null) {
            return null;
        }
        final byte[] buffer = new byte[1024];
        MessageDigest digest = getMessageDigest();
        try (InputStream input = new BufferedInputStream(original)) {
            int read;
            while ((read = input.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
        }
        return digest.digest();
    }

    public String md5hex(final InputStream original) throws IOException {
        byte[] bytes = md5(original);
        if (bytes == null) {
            return null;
        }
        return md5hex(bytes);
    }

}
