package org.zz.lib.guide.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.encrypt.algorithm.IMessageDigest;
import org.zz.lib.guide.encrypt.algorithm.impl.SHAMessageDigestImpl;
import org.zz.lib.guide.encrypt.enums.SHAEnum;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 测试网址：<a href="https://config.net.cn/tools/Sha.html">...</a>
 */
public class TestSHA {
    public static final Logger logger = Logger.getLogger("TestSHA");
    static final String T_PLAINTEXT = "123456";

    @Test
    public void testSHA1() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHAEnum> messageDigest = new SHAMessageDigestImpl(SHAEnum._1);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha1Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha1Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA224() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHAEnum> messageDigest = new SHAMessageDigestImpl(SHAEnum._224);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = Hex.encodeHexString(DigestUtils.getDigest(SHAEnum._224.getAlgorithm()).digest(T_PLAINTEXT.getBytes(StandardCharsets.UTF_8)));
        logger.log(Level.INFO, "DigestUtils.getDigest('SHA-224'): {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA256() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHAEnum> messageDigest = new SHAMessageDigestImpl(SHAEnum._256);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha256Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha256Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA384() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHAEnum> messageDigest = new SHAMessageDigestImpl(SHAEnum._384);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha384Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha384Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA512() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHAEnum> messageDigest = new SHAMessageDigestImpl(SHAEnum._512);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha512Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha512Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA512_224() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHAEnum> messageDigest = new SHAMessageDigestImpl(SHAEnum._512_224);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha512_224Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha512_224Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }

    @Test
    public void testSHA512_256() {
        logger.log(Level.INFO, "原始 string: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<SHAEnum> messageDigest = new SHAMessageDigestImpl(SHAEnum._512_256);

        String encryptHex = messageDigest.encryptHex(T_PLAINTEXT);
        logger.log(Level.INFO, "16进制小写 结果: {0}", new Object[]{encryptHex});
        logger.log(Level.INFO, "16进制大写 结果: {0}", new Object[]{encryptHex.toUpperCase()});

        String encryptHex10 = DigestUtils.sha512_256Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.sha512_256Hex: {0}", new Object[]{encryptHex10});

        Assertions.assertEquals(encryptHex, encryptHex10);
    }
}
