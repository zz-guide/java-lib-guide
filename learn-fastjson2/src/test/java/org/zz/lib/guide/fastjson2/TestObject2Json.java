package org.zz.lib.guide.fastjson2;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.fastjson2.pojo.Student;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestObject2Json {
    @Test
    public void testObject2Json(){
        Logger logger = Logger.getLogger("fastjson2 -> testObject2Json");

        Student student = new Student();
        student.setId(1L);
        student.setName("用户");
        student.setCreatedAt(LocalDateTime.now());

        String studentJsonStr = JSON.toJSONString(student);
        logger.log(Level.INFO, "student -> json string {0}", new Object[]{studentJsonStr});

        byte[] studentJsonBytes = JSON.toJSONBytes(student);
        logger.log(Level.INFO, "student -> json bytes {0}", new Object[]{studentJsonBytes});
    }
}
