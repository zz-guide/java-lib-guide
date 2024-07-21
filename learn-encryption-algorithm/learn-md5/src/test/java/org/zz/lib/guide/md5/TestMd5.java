package org.zz.lib.guide.md5;

import org.junit.jupiter.api.Test;
import org.zz.lib.guide.md5.utils.MD5Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://www.sojson.com/md5/">...</a>
 */
public class TestMd5 {

    static final String TEST_ENCRYPT = "123456";

    @Test
    public void testMd5(){
        Logger logger = Logger.getLogger("md5 -> testMd5");

        logger.log(Level.INFO, "original string: {0}", new Object[]{TEST_ENCRYPT});

        logger.log(Level.INFO, "md5 string: {0}", new Object[]{MD5Utils.getInstance().md5(TEST_ENCRYPT)});

        // 默认 32位 小写
        logger.log(Level.INFO, "md5 hex string: {0}", new Object[]{MD5Utils.getInstance().md5hex(TEST_ENCRYPT)});
    }
}
