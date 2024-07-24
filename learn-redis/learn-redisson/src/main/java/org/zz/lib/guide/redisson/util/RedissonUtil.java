package org.zz.lib.guide.redisson.util;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
public class RedissonUtil {
    public static final String OK = "OK";

    public static RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useClusterServers().addNodeAddress("redis://192.168.200.253:6379").setPassword("123456");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
