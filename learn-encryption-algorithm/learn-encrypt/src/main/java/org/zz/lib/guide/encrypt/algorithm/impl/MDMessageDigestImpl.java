package org.zz.lib.guide.encrypt.algorithm.impl;

import org.zz.lib.guide.encrypt.algorithm.MessageDigestImpl;
import org.zz.lib.guide.encrypt.enums.MDEnum;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MD加密工具类,例如 md2, md5
 */
public class MDMessageDigestImpl extends MessageDigestImpl<MDEnum> {

    public static final Logger logger = Logger.getLogger("MDMessageDigestImpl");

    public MDMessageDigestImpl(MDEnum algorithm, String salt) {
        this.algorithm = algorithm;
        this.configSalt = salt;
        checkParams();
    }

    public MDMessageDigestImpl(MDEnum algorithm) {
        this(algorithm, null);
    }

    @Override
    protected byte[] encrypt(String content, String salt, MDEnum encryptType) {
        try {
            String encryptContent = String.format("%s%s", content == null ? "" : content, salt == null ? "" : salt);
            MessageDigest messageDigest = MessageDigest.getInstance(encryptType.getAlgorithm());
            return messageDigest.digest(encryptContent.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.log(Level.INFO, "MDMessageDigestImpl encrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }
}
