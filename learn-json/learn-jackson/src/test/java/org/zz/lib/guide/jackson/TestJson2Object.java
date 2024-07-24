package org.zz.lib.guide.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestJson2Object {
    public static final Logger logger = Logger.getLogger("TestJson2Object");

    @Test
    public void testSquareBrackets(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object> list = null;
        try {
            list = objectMapper.readValue("[]", new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 方括号 转 List 类型 : {0}", new Object[]{list});
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testBrace(){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = null;
        try {
            map = objectMapper.readValue("{}", new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 花括号 转 Map 类型 : {0}", new Object[]{map});
        Assertions.assertNotNull(map);
        Assertions.assertTrue(map.isEmpty());
    }

    @Test
    public void testEmptyString(){
        ObjectMapper objectMapper = new ObjectMapper();
        Object obj = null;
        try {
            // 空字符串直接报错，抛异常了
            // No content to map due to end-of-input
            obj = objectMapper.readValue("", Object.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, " 空字符串 转 Object 类型 : {0}", new Object[]{obj});
        Assertions.assertNull(obj);
    }

    @Test
    public void testNull(){
        ObjectMapper objectMapper = new ObjectMapper();
        Object obj = null;
//        try {
//            // 因为 readValue 重载的关系，无法传入null
//            obj = objectMapper.readValue(null, Object.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        logger.log(Level.INFO, " 空字符串 转 Object 类型 : {0}", new Object[]{obj});
        Assertions.assertNull(obj);
    }

}
