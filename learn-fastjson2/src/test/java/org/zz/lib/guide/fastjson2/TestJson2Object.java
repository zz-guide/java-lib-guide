package org.zz.lib.guide.fastjson2;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.fastjson2.pojo.Student;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJson2Object {
    @Test
    public void testJson2Object1(){
        Logger logger = Logger.getLogger("fastjson2 -> testJson2Object1");

        // 空字符串 转 JSONObject 为 null
        String jsonStr = "";
        JSONObject obj = JSON.parseObject(jsonStr);
        Assertions.assertNull(obj);
    }

    @Test
    public void testJson2Object2(){
        Logger logger = Logger.getLogger("fastjson2 -> testJson2Object2");

        // 大括号字符串转 JSONObject 不为null
        String jsonStr = "{}";
        JSONObject obj = JSON.parseObject(jsonStr);
        Assertions.assertNotNull(obj);
        Assertions.assertTrue(obj.isEmpty());
    }

    @Test
    public void testJson2Object3(){
        Logger logger = Logger.getLogger("fastjson2 -> testJson2Object3");

        // 一对中括号无法被识别为有效的json字符串
        String jsonStr = "[]";
        JSONObject obj = JSON.parseObject(jsonStr);
        Assertions.assertNotNull(obj);
    }

    @Test
    public void testJson2Object4(){
        Logger logger = Logger.getLogger("fastjson2 -> testJson2Object4");

        // 一对中括号无法被识别为有效的json字符串
        String jsonStr = "{\"id\": 1,\"name\": \"用户\", \"created_at\": \"2024-07-11 12:21:12\"}";
        JSONObject obj = JSON.parseObject(jsonStr);
        logger.log(Level.INFO, "json str -> student id: {0}", new Object[]{obj.getLongValue("id")});
        logger.log(Level.INFO, "json str -> student name: {0}", new Object[]{obj.getString("name")});
        logger.log(Level.INFO, "json str -> student createdAt: {0}", new Object[]{obj.getDate("created_at")});
    }

    @Test
    public void testJson2Object5(){
        Logger logger = Logger.getLogger("fastjson2 -> testJson2Object5");

        // 一对中括号无法被识别为有效的json字符串
        String jsonStr = "{\"id\": 1,\"name\": \"用户\", \"created_at\": \"2024-07-11 12:21:12\"}";
        JSONObject obj = JSON.parseObject(jsonStr);
        // JSONObject 转 javabean
        Student student = obj.toJavaObject(Student.class);
        Assertions.assertNotNull(student);

        logger.log(Level.INFO, "json str -> student: {0}", new Object[]{student});

    }
}
