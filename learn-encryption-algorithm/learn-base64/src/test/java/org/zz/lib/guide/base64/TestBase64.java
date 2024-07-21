package org.zz.lib.guide.base64;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://www.sojson.com/md5/">...</a>
 */
public class TestBase64 {

    static final String TEST_ENCODE = "123456";

    @Test
    public void testBase64Encode(){
        Logger logger = Logger.getLogger("base64 -> testBase64Encode");

        logger.log(Level.INFO, "original string: {0}", new Object[]{TEST_ENCODE});

    }

    @Test
    public void testBase64Decode(){
        Logger logger = Logger.getLogger("base64 -> testBase64Decode");

        logger.log(Level.INFO, "original string: {0}", new Object[]{TEST_ENCODE});
    }
}
