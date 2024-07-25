package org.zz.lib.guide.encrypt.algorithm.impl;

import org.zz.lib.guide.encrypt.algorithm.MessageDigestImpl;
import org.zz.lib.guide.encrypt.enums.SHAEnum;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SHA加密工具类,例如 sha-1,sha-128等等
 */
public class SHAMessageDigestImpl extends MessageDigestImpl<SHAEnum> {

    public static final Logger logger = Logger.getLogger("SHAMessageDigestImpl");

    public SHAMessageDigestImpl(SHAEnum algorithm, String salt) {
        this.algorithm = algorithm;
        this.configSalt = salt;
        checkParams();
    }

    public SHAMessageDigestImpl(SHAEnum algorithm) {
        this(algorithm, null);
    }

    @Override
    protected byte[] encrypt(String content, String salt, SHAEnum algorithm) {
        try {
            String encryptContent = String.format("%s%s", content == null ? "" : content, salt == null ? "" : salt);
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm.getAlgorithm());
            return messageDigest.digest(encryptContent.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.log(Level.INFO, "SHAMessageDigestImpl encrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }
}
