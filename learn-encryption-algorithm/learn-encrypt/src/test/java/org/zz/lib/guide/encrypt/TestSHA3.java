package org.zz.lib.guide.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.encrypt.algorithm.IMessageDigest;
import org.zz.lib.guide.encrypt.algorithm.impl.SHA3MessageDigestImpl;
import org.zz.lib.guide.encrypt.enums.SHA3Enum;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://config.net.cn/tools/Sha.html">...</a>
 */
public class TestSHA3 {
    public static final Logger logger = Logger.getLogger("TestSHA3");
    static final String T_PLAINTEXT = "123456";

    @Test
    public void testSHA3_224() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHA3Enum> messageDigest = new SHA3MessageDigestImpl(SHA3Enum._224);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha3_224Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha3_224Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA3_256() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHA3Enum> messageDigest = new SHA3MessageDigestImpl(SHA3Enum._256);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha3_256Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha3_256Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA3_384() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHA3Enum> messageDigest = new SHA3MessageDigestImpl(SHA3Enum._384);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha3_384Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha3_384Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA3_512() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHA3Enum> messageDigest = new SHA3MessageDigestImpl(SHA3Enum._512);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha3_512Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha3_512Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }
}
