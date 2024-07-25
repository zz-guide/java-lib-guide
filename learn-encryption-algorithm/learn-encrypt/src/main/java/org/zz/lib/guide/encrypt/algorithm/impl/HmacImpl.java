package org.zz.lib.guide.encrypt.algorithm.impl;

import org.zz.lib.guide.encrypt.algorithm.MacImpl;
import org.zz.lib.guide.encrypt.enums.HMACEnum;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * HMAC加密
 */
public class HmacImpl extends MacImpl<HMACEnum> {

    public static final Logger logger = Logger.getLogger("HmacImpl");

    public HmacImpl(HMACEnum algorithm) {
        this.algorithm = algorithm;
        checkParams();
    }

    @Override
    protected byte[] encrypt(String content, String key, HMACEnum algorithm) {
        try {
            Mac mac = Mac.getInstance(algorithm.getAlgorithm());
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm.getAlgorithm());
            mac.init(secretKeySpec);

            return mac.doFinal(content.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            logger.log(Level.INFO, "HmacImpl encrypt error: {0}", new Object[]{e.getMessage()});
        }

        return null;
    }
}
