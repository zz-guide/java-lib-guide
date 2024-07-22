package org.zz.lib.guide.sha;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.sha.util.SHAUtil;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 测试网址：<a href="https://config.net.cn/tools/Sha.html">...</a>
 */
public class TestSHA {

    static final String TEST_ENCRYPT = "123456";

    @Test
    public void testSHA1() {
        Logger logger = Logger.getLogger("sha -> testSHA1");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHAUtil.getInstance().sha1Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA1 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA1 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha1Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha1Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA224() {
        Logger logger = Logger.getLogger("sha -> testSHA224");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHAUtil.getInstance().sha224Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA224 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA224 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = Hex.encodeHexString(DigestUtils.getDigest("SHA-224").digest(TEST_ENCRYPT.getBytes(StandardCharsets.UTF_8)));
        logger.log(Level.INFO, "DigestUtils.sha3_224Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA256() {
        Logger logger = Logger.getLogger("sha -> testSHA256");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHAUtil.getInstance().sha256Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA256 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA256 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha256Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha256Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA384() {
        Logger logger = Logger.getLogger("sha -> testSHA384");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHAUtil.getInstance().sha384Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA384 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA384 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha384Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha384Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA512() {
        Logger logger = Logger.getLogger("sha -> testSHA512");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHAUtil.getInstance().sha512Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA512 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA512 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha512Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha512Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA512_224() {
        Logger logger = Logger.getLogger("sha -> testSHA512_224");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHAUtil.getInstance().sha_512_224_Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA512_224 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA512_224 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha512_224Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha512_224Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA512_256() {
        Logger logger = Logger.getLogger("sha -> testSHA512_256");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHAUtil.getInstance().sha_512_256_Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "sha_512_256_Hex lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "sha_512_256_Hex upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha512_256Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha512_256Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }
}
