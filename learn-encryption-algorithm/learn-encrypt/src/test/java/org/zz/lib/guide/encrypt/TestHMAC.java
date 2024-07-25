package org.zz.lib.guide.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.encrypt.algorithm.MacImpl;
import org.zz.lib.guide.encrypt.algorithm.impl.HmacImpl;
import org.zz.lib.guide.encrypt.enums.HMACEnum;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 测试网址：<a href="https://config.net.cn/tools/Sha.html">...</a>
 */
public class TestHMAC {
    public static final Logger logger = Logger.getLogger("TestHMAC");
    static final String T_PLAINTEXT = "123456";
    static final String T_KEY = "123456";

    @Test
    public void testSHA256() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        MacImpl<HMACEnum> mac = new HmacImpl(HMACEnum.SHA_256);

        String encryptHex = mac.encryptHex(T_PLAINTEXT, T_KEY);
        logger.log(Level.INFO, "sha-256 结果: {0}", new Object[]{encryptHex});

        String encryptHex10 = DigestUtils.sha256Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha256Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }
}
