package org.zz.lib.guide.sha;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.sha.util.SHA3Util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://config.net.cn/tools/Sha.html">...</a>
 */
public class TestSHA3 {

    static final String TEST_ENCRYPT = "123456";

    @Test
    public void testSHA3_224() {
        Logger logger = Logger.getLogger("sha3 -> testSHA3_224");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHA3Util.getInstance().sha3_224Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA3_224 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA3_224 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha3_224Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha3_224Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA3_256() {
        Logger logger = Logger.getLogger("sha3 -> testSHA3_256");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHA3Util.getInstance().sha3_256Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA3_256 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA3_256 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha3_256Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha3_256Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA3_384() {
        Logger logger = Logger.getLogger("sha3 -> testSHA3_384");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHA3Util.getInstance().sha3_384Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA3_384 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA3_384 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha3_384Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha3_384Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }

    @Test
    public void testSHA3_512() {
        Logger logger = Logger.getLogger("sha3 -> testSHA3_512");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_ENCRYPT});

        String shaLowerStr = SHA3Util.getInstance().sha3_512Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "testSHA3_512 lower string: {0}", new Object[]{shaLowerStr});
        logger.log(Level.INFO, "testSHA3_512 upper string: {0}", new Object[]{shaLowerStr.toUpperCase()});

        String shaLowerStr2 = DigestUtils.sha3_512Hex(TEST_ENCRYPT);
        logger.log(Level.INFO, "DigestUtils.sha3_512Hex lower string: {0}", new Object[]{shaLowerStr2});

        Assertions.assertEquals(shaLowerStr2, shaLowerStr);
    }
}
