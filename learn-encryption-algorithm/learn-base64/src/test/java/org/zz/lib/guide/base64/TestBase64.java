package org.zz.lib.guide.base64;

import org.junit.jupiter.api.Test;
import org.zz.lib.guide.base64.util.Base64Util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBase64 {

    static final String TEST_PLAINTEXT = "hello 世界";
    static final String TEST_URL_PLAINTEXT = "http://www.baidu.com/a?id=1&name=用户";

    @Test
    public void testBase64() {
        Logger logger = Logger.getLogger("base64 -> testBase64Encode");

        String encode = Base64Util.encodeToString(TEST_PLAINTEXT.getBytes());

        logger.log(Level.INFO, "普通字符串 encode: {0}", new Object[]{encode});

        String decode = new String(Base64Util.decodeFromString(encode));
        logger.log(Level.INFO, "普通字符串 decode: {0}", new Object[]{decode});

        logger.log(Level.INFO, "=== urlEncode ===");

        String urlEncode = Base64Util.encodeToUrlSafeString(TEST_PLAINTEXT.getBytes());
        logger.log(Level.INFO, "普通字符串 urlEncode: {0}", new Object[]{urlEncode});

        String urlDecode = new String(Base64Util.decodeFromUrlSafeString(urlEncode));
        logger.log(Level.INFO, "普通字符串 urlDecode: {0}", new Object[]{urlDecode});
    }

    @Test
    public void testUrlBase64() {
        Logger logger = Logger.getLogger("base64 -> testUrlBase64");

        String encode = Base64Util.encodeToString(TEST_URL_PLAINTEXT.getBytes());

        logger.log(Level.INFO, "url 字符串 encode: {0}", new Object[]{encode});

        String decode = new String(Base64Util.decodeFromString(encode));
        logger.log(Level.INFO, "url 字符串 decode: {0}", new Object[]{decode});

        logger.log(Level.INFO, "=== urlEncode ===");

        String urlEncode = Base64Util.encodeToUrlSafeString(TEST_URL_PLAINTEXT.getBytes());
        logger.log(Level.INFO, "url 字符串 urlEncode: {0}", new Object[]{urlEncode});

        String urlDecode = new String(Base64Util.decodeFromUrlSafeString(urlEncode));
        logger.log(Level.INFO, "url 字符串 urlDecode: {0}", new Object[]{urlDecode});
    }
}
