package org.zz.lib.guide.encrypt.algorithm.impl;

import org.zz.lib.guide.encrypt.algorithm.MessageDigestImpl;
import org.zz.lib.guide.encrypt.enums.SHA3Enum;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SHA加密工具类,例如 sha-1,sha-128等等
 */
public class SHA3MessageDigestImpl extends MessageDigestImpl<SHA3Enum> {

    public static final Logger logger = Logger.getLogger("SHA3MessageDigestImpl");

    public SHA3MessageDigestImpl(SHA3Enum algorithm, String salt) {
        this.algorithm = algorithm;
        this.configSalt = salt;
        checkParams();
    }

    public SHA3MessageDigestImpl(SHA3Enum algorithm) {
        this(algorithm, null);
    }


    @Override
    protected byte[] encrypt(String content, String salt, SHA3Enum algorithm) {
        try {
            String encryptContent = String.format("%s%s", content == null ? "" : content, salt == null ? "" : salt);
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithm());
            return messageDigest.digest(encryptContent.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.log(Level.INFO, "SHA3MessageDigestImpl encrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }
}
