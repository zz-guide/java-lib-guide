package org.zz.lib.guide.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.encrypt.algorithm.IMessageDigest;
import org.zz.lib.guide.encrypt.algorithm.impl.MDMessageDigestImpl;
import org.zz.lib.guide.encrypt.enums.MDEnum;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  测试网址：<a href="https://www.sojson.com/encrypt_md5.html">...</a>
 */
public class TestMD2 {
    public static final Logger logger = Logger.getLogger("TestMD2");
    static final String T_PLAINTEXT = "123456";
    static final String T_SALT = "123456789";

    @Test
    public void md2(){
        logger.log(Level.INFO, "原始字符串: {0}", new Object[]{T_PLAINTEXT});

        IMessageDigest<MDEnum> messageDigest = new MDMessageDigestImpl(MDEnum.MD2);

        String encryptHexString = messageDigest.encryptHex(T_PLAINTEXT);

        logger.log(Level.INFO, "md2(32位小写) 结果: {0}", new Object[]{encryptHexString});
        logger.log(Level.INFO, "md2(32位大写) 结果: {0}", new Object[]{encryptHexString.toUpperCase()});

        String hexString_16Bit = encryptHexString.substring(8, 24);
        logger.log(Level.INFO, "md2(16位小写) hex string: {0}", new Object[]{hexString_16Bit});
        logger.log(Level.INFO, "md2(16位大写) hex string: {0}", new Object[]{hexString_16Bit.toUpperCase()});

        String encryptHexString10 = DigestUtils.md2Hex(T_PLAINTEXT);
        logger.log(Level.INFO, "DigestUtils.md2Hex 结果: {0}", new Object[]{encryptHexString10});

        Assertions.assertEquals(encryptHexString, encryptHexString10);
    }

    @Test
    public void md2WithSalt(){
        logger.log(Level.INFO, "原始字符串: {0}, salt: {1}", new Object[]{T_PLAINTEXT, T_SALT});

        System.out.println(MDEnum.MD2.getAlgorithm());
        IMessageDigest<MDEnum> messageDigest = new MDMessageDigestImpl(MDEnum.MD2, T_SALT);

        String encryptHexString = messageDigest.encryptHex(T_PLAINTEXT);

        logger.log(Level.INFO, "md2(32位小写) 结果: {0}", new Object[]{encryptHexString});
        logger.log(Level.INFO, "md2(32位大写) 结果: {0}", new Object[]{encryptHexString.toUpperCase()});

        String hexString_16Bit = encryptHexString.substring(8, 24);
        logger.log(Level.INFO, "md2(16位小写) hex string: {0}", new Object[]{hexString_16Bit});
        logger.log(Level.INFO, "md2(16位大写) hex string: {0}", new Object[]{hexString_16Bit.toUpperCase()});

        String encryptHexString10 = DigestUtils.md2Hex(T_PLAINTEXT+T_SALT);
        logger.log(Level.INFO, "DigestUtils.md2Hex 结果: {0}", new Object[]{encryptHexString10});

        Assertions.assertEquals(encryptHexString, encryptHexString10);
    }
}
