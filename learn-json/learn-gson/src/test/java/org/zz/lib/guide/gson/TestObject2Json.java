package org.zz.lib.guide.gson;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.zz.lib.guide.gson.pojo.Student;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestObject2Json {
    @Test
    public void testObject2Json(){
        Logger logger = Logger.getLogger("gson -> testObject2Json");

        Student student = new Student();
        student.setId(1L);
        student.setName("用户");
        student.setCreatedAt(LocalDateTime.now());

        Gson gson = new Gson();
        String jsonString = gson.toJson(student);

        logger.log(Level.INFO, "student -> json str : {0}", new Object[]{jsonString});
    }
}
