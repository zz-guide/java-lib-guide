package org.zz.lib.guide.jedis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jedis.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  set 命令
 *  EX ttl秒
 *  PX ttl毫秒
 *  NX 不存在则修改
 *  XX 存在则修改
 */
public class TestString {
    public static final Logger logger = Logger.getLogger("TestString");

    @Test
    public void testString(){
        JedisPool pool = JedisUtil.pool;
        try (Jedis jedis = pool.getResource()) {
            String selectRes = jedis.select(0);
            logger.log(Level.INFO, "jedis.select(): {0}", new Object[]{selectRes});
            Assertions.assertEquals(selectRes, JedisUtil.OK);

            for (int i = 1; i <= 10; i++) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String key = String.format("user:%s", i);
                String value = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"created_at\":\"%s\"}", i, "仔仔"+i, now);

                String setRes = jedis.set(key, value);
                logger.log(Level.INFO, "jedis.set(): {0}", new Object[]{setRes});
                Assertions.assertEquals(setRes, JedisUtil.OK);

                String getRes = jedis.get(key);
                logger.log(Level.INFO, "jedis.get(): {0}", new Object[]{getRes});
                Assertions.assertEquals(getRes, value);
            }
        }
    }

    @Test
    public void testStringWithExpire(){
        JedisPool pool = JedisUtil.pool;
        try (Jedis jedis = pool.getResource()) {
            String selectRes = jedis.select(0);
            logger.log(Level.INFO, "jedis.select(): {0}", new Object[]{selectRes});
            Assertions.assertEquals(selectRes, JedisUtil.OK);

            SetParams setParams = SetParams.setParams();
            setParams.ex(500); // 设置5秒ttl
            // setParams.px(5000); // 设置5000毫秒ttl

            for (int i = 1; i <= 10; i++) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String key = String.format("user_expire:%s", i);
                String value = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"created_at\":\"%s\"}", i, "仔仔过期"+i, now);

                String setRes = jedis.set(key, value, setParams);
                logger.log(Level.INFO, "jedis.set(): {0}", new Object[]{setRes});
                Assertions.assertEquals(setRes, JedisUtil.OK);

                String getRes = jedis.get(key);
                logger.log(Level.INFO, "jedis.get(): {0}", new Object[]{getRes});
                Assertions.assertEquals(getRes, value);
            }
        }
    }

    @Test
    public void testStringWithNx(){
        JedisPool pool = JedisUtil.pool;
        try (Jedis jedis = pool.getResource()) {
            String selectRes = jedis.select(0);
            logger.log(Level.INFO, "jedis.select(): {0}", new Object[]{selectRes});
            Assertions.assertEquals(selectRes, JedisUtil.OK);

            SetParams setParams = SetParams.setParams();
            setParams.ex(7); // 设置5秒ttl

            // 先设置
            for (int i = 1; i <= 10; i++) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String key = String.format("user_nx:%s", i);
                String value = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"created_at\":\"%s\"}", i, "仔仔"+i, now);

                String setRes = jedis.set(key, value, setParams);
                logger.log(Level.INFO, "jedis.set(): {0}", new Object[]{setRes});
                Assertions.assertEquals(setRes, JedisUtil.OK);

                String getRes = jedis.get(key);
                logger.log(Level.INFO, "jedis.get(): {0}", new Object[]{getRes});
                Assertions.assertEquals(getRes, value);
            }

            // 模拟key不存在的场景
            Thread.sleep(3000);

            SetParams setParamsUpdate = SetParams.setParams();
            setParamsUpdate.nx(); // 不存在就修改
            // 再修改
            for (int i = 1; i <= 10; i++) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String key = String.format("user_nx:%s", i);
                String value = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"created_at\":\"%s\"}", i, "仔仔_update"+i, now);

                String setRes = jedis.set(key, value, setParamsUpdate);
                logger.log(Level.INFO, "jedis.set(): {0}", new Object[]{setRes});
                Assertions.assertNotEquals(setRes, JedisUtil.OK);
                Assertions.assertNull(setRes);

                String getRes = jedis.get(key);
                logger.log(Level.INFO, "jedis.get(): {0}", new Object[]{getRes});
                Assertions.assertNotEquals(value, getRes);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testStringWithXx(){
        JedisPool pool = JedisUtil.pool;
        try (Jedis jedis = pool.getResource()) {
            String selectRes = jedis.select(0);
            logger.log(Level.INFO, "jedis.select(): {0}", new Object[]{selectRes});
            Assertions.assertEquals(selectRes, JedisUtil.OK);

            SetParams setParams = SetParams.setParams();
            setParams.ex(7); // 设置5秒ttl

            // 先设置
            for (int i = 1; i <= 10; i++) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String key = String.format("user_xx:%s", i);
                String value = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"created_at\":\"%s\"}", i, "仔仔"+i, now);

                String setRes = jedis.set(key, value, setParams);
                logger.log(Level.INFO, "jedis.set(): {0}", new Object[]{setRes});
                Assertions.assertEquals(setRes, JedisUtil.OK);

                String getRes = jedis.get(key);
                logger.log(Level.INFO, "jedis.get(): {0}", new Object[]{getRes});
                Assertions.assertEquals(getRes, value);
            }

            // 模拟key不存在的场景
            Thread.sleep(3000);

            SetParams setParamsUpdate = SetParams.setParams();
            setParamsUpdate.xx(); // 存在就修改
            // 再修改
            for (int i = 1; i <= 10; i++) {
                String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String key = String.format("user_xx:%s", i);
                String value = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"created_at\":\"%s\"}", i, "仔仔_update"+i, now);

                String setRes = jedis.set(key, value, setParamsUpdate);
                logger.log(Level.INFO, "jedis.set(): {0}", new Object[]{setRes});
                Assertions.assertNotNull(setRes);
                Assertions.assertEquals(setRes, JedisUtil.OK);

                String getRes = jedis.get(key);
                logger.log(Level.INFO, "jedis.get(): {0}", new Object[]{getRes});
                Assertions.assertEquals(value, getRes);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
