package org.zz.lib.guide.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jackson.pojo.Student;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  null, "", 空数组，空对象，空map, 空list, 自定义对象
 */

public class TestObject2Json {
    public static final Logger logger = Logger.getLogger("TestObject2Json");

    @Test
    public void testCustomObject() {
        Student student = new Student();
        student.setId(1L);
        student.setName("用户");
        student.setAge(33);
        student.setIsSenior(true);
        student.setD("json序列化之后不显示");
        student.setCreatedAt(LocalDateTime.now());

        // 通过 ObjectMapper 对象上的方法来实现
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // 开启支持 LocalDateTime

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " user对象 转 json: {0}", new Object[]{jsonString});
        Assertions.assertNotNull(jsonString);
    }

    @Test
    public void testEmptyString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString("");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 空字符串对象 转 json : {0}", new Object[]{jsonString});
        Assertions.assertEquals(jsonString, "");
    }

    @Test
    public void testBraceString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString("[]");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 方括号字符串对象 转 json : {0}", new Object[]{jsonString});
        Assertions.assertEquals(jsonString, "[]");
    }

    @Test
    public void testSquareBracketsString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString("{}");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 花括号字符串对象 转 json : {0}", new Object[]{jsonString});
        Assertions.assertEquals(jsonString, "{}");
    }

    @Test
    public void testNull() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(null);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " null 转 json : {0}", new Object[]{jsonString});

        Assertions.assertNull(jsonString);
    }

    @Test
    public void testObject() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            // 没有实现序列化接口，所以直接报错了
            jsonString = objectMapper.writeValueAsString(new Object());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 空的Object对象 转 json : {0}", new Object[]{jsonString});
    }

    @Test
    public void testObjectArray() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            // 没有实现序列化接口，所以直接报错了
            Object[] objects = new Object[]{};
            jsonString = objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 空的Object对象数组 转 json : {0}", new Object[]{jsonString});
        Assertions.assertEquals(jsonString, "[]");
    }

    @Test
    public void testEmptyMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            // 没有实现序列化接口，所以直接报错了
            Map<Object, Object> map = Map.of();
            jsonString = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 空的Map对象 转 json : {0}", new Object[]{jsonString});
        Assertions.assertEquals(jsonString, "{}");
    }

    @Test
    public void testEmptyList() {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            // 没有实现序列化接口，所以直接报错了
            List<Object> list = List.of();
            jsonString = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 空的List对象 转 json : {0}", new Object[]{jsonString});
        Assertions.assertEquals(jsonString, "[]");
    }
}
