package org.zz.lib.guide.commons;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCommonsIo {

    @Test
    public void testIsEmpty(){
        Logger logger = Logger.getLogger("commons-io -> testIsEmpty");

        logger.log(Level.INFO, "{0}", new Object[]{});
    }
}
