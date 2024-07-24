package org.zz.lib.guide.encrypt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.encrypt.algorithm.ICipherEncrypt;
import org.zz.lib.guide.encrypt.algorithm.impl.RSACipherEncryptImp;
import org.zz.lib.guide.encrypt.enums.RSAEnum;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://www.sojson.com/md5/">...</a>
 */
public class TestRSA {
    public static final Logger logger = Logger.getLogger("TestRSA");

    public static final String T_PLAINTEXT = "hello 世界";

    public static final String T_SALT = "12345678"; // 必须是16位
    public static final String T_KEY = "12345678"; // 必须是16位

    @Test
    public void t1() throws Exception {
        logger.log(Level.INFO, "原始内容: {0}, 长度:{1}", new Object[]{T_PLAINTEXT, T_PLAINTEXT.length()});

        ICipherEncrypt<RSAEnum> rsaCipherEncryptImp = new RSACipherEncryptImp();
        // 加密
        long lStart = System.currentTimeMillis();

        String encryptString = rsaCipherEncryptImp.encryptHex(T_PLAINTEXT, T_SALT, T_KEY, RSAEnum.ECB_PKCS1PADDING);
        logger.log(Level.INFO, "加密后的字符串: {0}", new Object[]{encryptString});

        long lUseTime = System.currentTimeMillis() - lStart;
        logger.log(Level.INFO, "加密耗时: {0} 毫秒", new Object[]{lUseTime});

        // 解密
        lStart = System.currentTimeMillis();
        String decryptString = rsaCipherEncryptImp.decryptHex(encryptString, T_SALT, T_KEY, RSAEnum.ECB_PKCS1PADDING);
        logger.log(Level.INFO, "解密后的字符串: {0}", new Object[]{decryptString});

        lUseTime = System.currentTimeMillis() - lStart;
        logger.log(Level.INFO, "解密耗时: {0} 毫秒", new Object[]{lUseTime});

        Assertions.assertEquals(decryptString, T_PLAINTEXT);
    }
}
