package org.zz.lib.guide.aes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.aes.util.AES128CBCOperator;
import org.zz.lib.guide.aes.util.AESDecryptOperator;
import org.zz.lib.guide.aes.util.AESEncryptOperator;
import org.zz.lib.guide.aes.util.AESOperator;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://www.sojson.com/md5/">...</a>
 */
public class TestAES {
    public static final String TEST_KEY = "1234567890abcdef"; // 必须是16位
    public static final String TEST_IV = "fedcba0987654321";
    public static final String TEST_PLAINTEXT = "hello 世界";

    @Test
    public void testAES128CBC(){
        Logger logger = Logger.getLogger("AES -> testAES128CBC");

        logger.log(Level.INFO, "原始内容: {0}, 长度:{1}", new Object[]{TEST_PLAINTEXT, TEST_PLAINTEXT.length()});

        // 首先构造 AESOperator 对象，用来保存算法所需要的一切参数
        AESOperator aesOperator = new AES128CBCOperator(TEST_KEY, TEST_IV);
        AESEncryptOperator aesEncryptOperator = new AESEncryptOperator(aesOperator);
        // 加密
        long lStart = System.currentTimeMillis();

        String encryptString = aesEncryptOperator.encrypt(TEST_PLAINTEXT);
        logger.log(Level.INFO, "加密后的字符串: {0}", new Object[]{encryptString});

        long lUseTime = System.currentTimeMillis() - lStart;
        logger.log(Level.INFO, "加密耗时: {0} 毫秒", new Object[]{lUseTime});

        // 解密
        AESDecryptOperator aesDecryptOperator = new AESDecryptOperator(aesOperator);
        lStart = System.currentTimeMillis();
        String decryptString = aesDecryptOperator.decrypt(encryptString);
        logger.log(Level.INFO, "解密后的字符串: {0}", new Object[]{decryptString});

        lUseTime = System.currentTimeMillis() - lStart;
        logger.log(Level.INFO, "解密耗时: {0} 毫秒", new Object[]{lUseTime});

        Assertions.assertEquals(decryptString, TEST_PLAINTEXT);
    }
}
