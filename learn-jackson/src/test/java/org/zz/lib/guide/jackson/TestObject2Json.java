package org.zz.lib.guide.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.jackson.pojo.Student;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestObject2Json {
    @Test
    public void testObject2Json(){
        // https://blog.csdn.net/u012060033/article/details/136991731
        Logger logger = Logger.getLogger("jackson -> testObject2Json");

        Student student = new Student();
        student.setId(1L);
        student.setName("用户");
        student.setCreatedAt(LocalDateTime.now());

        // 空字符串 转 JSONObject 为 null
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.log(Level.INFO, "student -> json str : {0}", new Object[]{jsonString});
    }
}
