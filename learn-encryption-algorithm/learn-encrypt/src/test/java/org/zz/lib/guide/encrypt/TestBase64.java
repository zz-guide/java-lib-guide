package org.zz.lib.guide.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.encrypt.util.Base64Util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBase64 {
    public static final Logger logger = Logger.getLogger("TestBase64");
    static final String T_PLAINTEXT = "hello 世界";
    static final String T_URL_PLAINTEXT = "http://www.baidu.com/a?id=1&name=用户+#.?!,";

    @Test
    public void t1() {
        logger.log(Level.INFO, "原始字符串: {0}", new Object[]{T_PLAINTEXT});

        String encode = Base64Util.encodeToString(T_PLAINTEXT);
        logger.log(Level.INFO, "普通字符串 encodeToString: {0}", new Object[]{encode});

        String encode10 = Base64.encodeBase64String(T_PLAINTEXT.getBytes());
        logger.log(Level.INFO, "Base64.encodeBase64: {0}", new Object[]{encode10});

        Assertions.assertEquals(encode, encode10);

        String decode = new String(Base64Util.decodeFromString(encode));
        logger.log(Level.INFO, "普通字符串 decodeFromString: {0}", new Object[]{decode});

        String decode10 = new String(Base64.decodeBase64(encode));
        logger.log(Level.INFO, "Base64.encodeBase64: {0}", new Object[]{decode10});

        Assertions.assertEquals(encode, encode10);
    }

    @Test
    public void t2() {
        logger.log(Level.INFO, "原始字符串: {0}", new Object[]{T_PLAINTEXT});

        String encode = Base64Util.encodeToUrlSafeString(T_PLAINTEXT);
        logger.log(Level.INFO, "普通字符串 encodeToUrlSafeString: {0}", new Object[]{encode});

        String encode10 = Base64.encodeBase64URLSafeString(T_PLAINTEXT.getBytes());
        logger.log(Level.INFO, "Base64.encodeBase64URLSafeString: {0}", new Object[]{encode10});

        Assertions.assertEquals(encode, encode10);

        String decode = new String(Base64Util.decodeFromUrlSafeString(encode));
        logger.log(Level.INFO, "普通字符串 decodeFromString: {0}", new Object[]{decode});

        String decode10 = new String(Base64.decodeBase64(encode));
        logger.log(Level.INFO, "Base64.encodeBase64: {0}", new Object[]{decode10});

        Assertions.assertEquals(encode, encode10);
    }

    @Test
    public void t3() {
        logger.log(Level.INFO, "原始字符串: {0}", new Object[]{T_URL_PLAINTEXT});

        String encode = Base64Util.encodeToString(T_URL_PLAINTEXT);
        logger.log(Level.INFO, "url字符串 encodeToString: {0}", new Object[]{encode});

        String encode10 = Base64.encodeBase64String(T_URL_PLAINTEXT.getBytes());
        logger.log(Level.INFO, "Base64.encodeBase64: {0}", new Object[]{encode10});

        Assertions.assertEquals(encode, encode10);

        String decode = new String(Base64Util.decodeFromString(encode));
        logger.log(Level.INFO, "url字符串 decodeFromString: {0}", new Object[]{decode});

        String decode10 = new String(Base64.decodeBase64(encode));
        logger.log(Level.INFO, "Base64.encodeBase64: {0}", new Object[]{decode10});

        Assertions.assertEquals(encode, encode10);
    }

    @Test
    public void t4() {
        logger.log(Level.INFO, "原始字符串: {0}", new Object[]{T_URL_PLAINTEXT});

        String encode = Base64Util.encodeToUrlSafeString(T_URL_PLAINTEXT);
        logger.log(Level.INFO, "url字符串 encodeToUrlSafeString: {0}", new Object[]{encode});

        String encode10 = Base64.encodeBase64URLSafeString(T_URL_PLAINTEXT.getBytes());
        logger.log(Level.INFO, "Base64.encodeBase64URLSafeString: {0}", new Object[]{encode10});

        Assertions.assertEquals(encode, encode10);

        String decode = new String(Base64Util.decodeFromUrlSafeString(encode));
        logger.log(Level.INFO, "url字符串 decodeFromString: {0}", new Object[]{decode});

        String decode10 = new String(Base64.decodeBase64(encode));
        logger.log(Level.INFO, "Base64.encodeBase64: {0}", new Object[]{decode10});

        Assertions.assertEquals(encode, encode10);
    }
}
