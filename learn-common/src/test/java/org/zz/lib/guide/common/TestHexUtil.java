package org.zz.lib.guide.common;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.common.util.HexUtil;
import org.zz.lib.guide.common.util.HexUtil2;
import org.zz.lib.guide.common.util.HexUtil3;
import org.zz.lib.guide.common.util.HexUtil4;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestHexUtil {
    public static final byte[] TEST_BYTES = new byte[]{-31, 10, -36, 57, 73, -70, 89, -85, -66, 86, -32, 87, -14, 15, -120, 62};

    @Test
    public void testByteToHex() {
        Logger logger = Logger.getLogger("byte -> testByteToHex");

        logger.log(Level.INFO, "原始 byte[]: {0}", new Object[]{Arrays.toString(TEST_BYTES)});

        String shaLowerStr = HexUtil.bytes2HexLower(TEST_BYTES);
        logger.log(Level.INFO, "HexUtil bytes2HexLower: {0}", new Object[]{shaLowerStr});

        String shaLowerStr2 = HexUtil2.bytes2HexLower(TEST_BYTES);
        logger.log(Level.INFO, "HexUtil2 bytes2HexLower: {0}", new Object[]{shaLowerStr2});

        String shaLowerStr3 = HexUtil3.bytes2HexLower(TEST_BYTES);
        logger.log(Level.INFO, "HexUtil3 bytes2HexLower: {0}", new Object[]{shaLowerStr3});

        String shaLowerStr4 = HexUtil4.bytes2HexLower(TEST_BYTES);
        logger.log(Level.INFO, "HexUtil4 bytes2HexLower: {0}", new Object[]{shaLowerStr4});

        String shaLowerStr10 = Hex.encodeHexString(TEST_BYTES);
        logger.log(Level.INFO, "Hex.encodeHexString: {0}", new Object[]{shaLowerStr10});

        Assertions.assertEquals(shaLowerStr, shaLowerStr2);
        Assertions.assertEquals(shaLowerStr2, shaLowerStr3);
        Assertions.assertEquals(shaLowerStr3, shaLowerStr4);
        Assertions.assertEquals(shaLowerStr4, shaLowerStr10);
    }
}
