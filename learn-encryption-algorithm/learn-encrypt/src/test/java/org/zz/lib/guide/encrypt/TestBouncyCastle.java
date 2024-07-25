package org.zz.lib.guide.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Test;

import java.security.Security;
import java.util.logging.Logger;

public class TestBouncyCastle {
    public static final Logger logger = Logger.getLogger("TestBouncyCastle");

    public static final String T_PLAINTEXT = "hello 世界";

    public static final String T_SALT = "12345678"; // 8
    public static final String T_KEY = "12345678"; // 8

    @Test
    public void t1() {
        Security.addProvider(new BouncyCastleProvider());

    }
}
