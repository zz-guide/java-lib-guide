package org.zz.lib.guide.jedis;

import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jedis.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.logging.Logger;

/**
 *  list 命令
 */
public class TestHash {
    public static final Logger logger = Logger.getLogger("TestHash");

    @Test
    public void testHash(){
        JedisPool pool = JedisUtil.pool;
        try (Jedis jedis = pool.getResource()) {

        }
    }
}
