package org.zz.lib.guide.jedis.util;


import redis.clients.jedis.JedisPool;

public class JedisUtil {
   public static final String OK = "OK";

   public static final JedisPool pool = new JedisPool("192.168.200.253", 6379, null, "123456");
}
