package org.zz.lib.guide.md5;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.md5.util.MD2Util;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://www.sojson.com/encrypt_md5.html">...</a>
 */
public class TestMd2 {

    static final String TEST_PLAINTEXT = "123456";

    @Test
    public void testMd2(){
        Logger logger = Logger.getLogger("md2 -> testMd2");

        logger.log(Level.INFO, "原始 string: {0}", new Object[]{TEST_PLAINTEXT});

        byte[] bytes = MD2Util.getInstance().md2(TEST_PLAINTEXT);

        logger.log(Level.INFO, "md2 string: {0}", new Object[]{Arrays.toString(bytes)});

        // byte[] 直接转成string可能有乱码，所以需要转成32位的16进制字符表示
        String md2LowerHexStr = MD2Util.getInstance().md2LowerHex(TEST_PLAINTEXT);
        logger.log(Level.INFO, "md2 lower hex string: {0}", new Object[]{md2LowerHexStr});

        String md2UpperHexStr = MD2Util.getInstance().md2UpperHex(TEST_PLAINTEXT);
        logger.log(Level.INFO, "md2 upper hex string: {0}", new Object[]{md2UpperHexStr});

        Assertions.assertEquals(md2LowerHexStr.toUpperCase(), md2UpperHexStr);
        Assertions.assertEquals(md2LowerHexStr, md2UpperHexStr.toLowerCase());

        String md2LowerHexStr2 = DigestUtils.md2Hex(TEST_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.md2Hex: {0}", new Object[]{md2LowerHexStr2});

        Assertions.assertEquals(md2LowerHexStr2, md2LowerHexStr);
    }
}
