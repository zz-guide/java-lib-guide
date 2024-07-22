package org.zz.lib.guide.md5;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.md5.util.MD5Util;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://www.sojson.com/encrypt_md5.html">...</a>
 */
public class TestMd5 {

    static final String TEST_PLAINTEXT = "123456";

    @Test
    public void testMd5(){
        Logger logger = Logger.getLogger("md5 -> testMd5");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_PLAINTEXT});

        byte[] bytes = MD5Util.getInstance().md5(TEST_PLAINTEXT);

        logger.log(Level.INFO, "md5 string: {0}", new Object[]{Arrays.toString(bytes)});

        // byte[] 直接转成string可能有乱码，所以需要转成32位的16进制字符表示
        String md5LowerHexStr = MD5Util.getInstance().md5LowerHex(TEST_PLAINTEXT);
        logger.log(Level.INFO, "32 位 md5 lower hex string: {0}", new Object[]{md5LowerHexStr});

        String md5UpperHexStr = MD5Util.getInstance().md5UpperHex(TEST_PLAINTEXT);
        logger.log(Level.INFO, "32 位 md5 upper hex string: {0}", new Object[]{md5UpperHexStr});

        Assertions.assertEquals(md5LowerHexStr.toUpperCase(), md5UpperHexStr);
        Assertions.assertEquals(md5LowerHexStr, md5UpperHexStr.toLowerCase());

        String md5LowerHexStr2 = DigestUtils.md5Hex(TEST_PLAINTEXT);
        logger.log(Level.INFO, "32 位 DigestUtils.md5Hex: {0}", new Object[]{md5LowerHexStr2});

        Assertions.assertEquals(md5LowerHexStr, md5LowerHexStr2);

        logger.log(Level.INFO, "16 位 md5 lower hex string: {0}", new Object[]{md5LowerHexStr.substring(8, 24)});
        logger.log(Level.INFO, "16 位 md5 upper hex string: {0}", new Object[]{md5UpperHexStr.substring(8, 24)});
    }
}
