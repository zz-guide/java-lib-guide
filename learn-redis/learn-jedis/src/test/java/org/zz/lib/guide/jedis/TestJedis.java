package org.zz.lib.guide.jedis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jedis.util.JedisUtil;
import redis.clients.jedis.JedisPool;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJedis {
    public static final Logger logger = Logger.getLogger("TestJedis");

    @Test
    public void testPool(){
        JedisPool pool = JedisUtil.pool;
        logger.log(Level.INFO, "连接redis: {0}", new Object[]{pool});
        Assertions.assertNotNull(pool);
    }
}
