package org.zz.lib.guide.guava;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestGuava {

    @Test
    public void testIsEmpty(){
        Logger logger = Logger.getLogger("guava -> testIsEmpty");

        logger.log(Level.INFO, "{0}", new Object[]{});
    }
}
