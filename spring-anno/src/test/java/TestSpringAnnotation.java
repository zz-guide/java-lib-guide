import com.xulei.config.ApplicationContextConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpringAnnotation {
    static AnnotationConfigApplicationContext context;

    static {
        context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);
    }

    @Test
    public void loadWithConfigurationAnnotation() {

    }
}
