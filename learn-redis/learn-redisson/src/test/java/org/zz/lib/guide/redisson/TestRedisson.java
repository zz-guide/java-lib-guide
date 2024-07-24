package org.zz.lib.guide.redisson;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.zz.lib.guide.redisson.util.RedissonUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRedisson {
    public static final Logger logger = Logger.getLogger("TestRedisson");

    @Test
    public void testPool(){
        RedissonClient redissonClient = RedissonUtil.getRedissonClient();
        logger.log(Level.INFO, "连接 redis: {0}", new Object[]{redissonClient});
        Assertions.assertNotNull(redissonClient);
    }
}
