package org.zz.lib.guide.hutool;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestStrUtil {

    @Test
    public void testIsEmpty(){
        Logger logger = Logger.getLogger("hutool -> testIsEmpty");

        String emptyStr = "";
        Assertions.assertTrue(StrUtil.isEmpty(emptyStr));
        logger.log(Level.INFO, "StrUtil.isEmpty(\"\") : {0}", new Object[]{StrUtil.isEmpty(emptyStr)});

        String nullStr = null;
        Assertions.assertTrue(StrUtil.isEmpty(nullStr));
        logger.log(Level.INFO, "StrUtil.isEmpty(null) : {0}", new Object[]{StrUtil.isEmpty(nullStr)});

        String spaceStr = " ";
        Assertions.assertFalse(StrUtil.isEmpty(spaceStr)); // 空格字符串 isEmpty 为 false
        logger.log(Level.INFO, "StrUtil.isEmpty(\" \") : {0}", new Object[]{StrUtil.isEmpty(spaceStr)});
    }

    @Test
    public void testIsBlank(){
        Logger logger = Logger.getLogger("hutool -> testIsBlank");

        String emptyStr = "";
        Assertions.assertTrue(StrUtil.isBlank(emptyStr));
        logger.log(Level.INFO, "StrUtil.isBlank(\"\") : {0}", new Object[]{StrUtil.isBlank(emptyStr)});

        String nullStr = null;
        Assertions.assertTrue(StrUtil.isBlank(nullStr));
        logger.log(Level.INFO, "StrUtil.isBlank(null) : {0}", new Object[]{StrUtil.isBlank(nullStr)});

        String spaceStr = " ";
        Assertions.assertTrue(StrUtil.isBlank(spaceStr)); // 空格字符串 isBlank 为 true
        logger.log(Level.INFO, "StrUtil.isBlank(\" \") : {0}", new Object[]{StrUtil.isBlank(spaceStr)});
    }
}
