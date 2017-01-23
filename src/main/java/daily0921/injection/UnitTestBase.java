package daily0921.injection;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

/**
 * Created by tqmall on 16/9/22.
 * 完成对spring配置文件的加载、销毁
 */
public class UnitTestBase {
    private ClassPathXmlApplicationContext context;

    private String springXmlPath;

    public UnitTestBase() {}

    public UnitTestBase(String springXmlPath) {
        this.springXmlPath = springXmlPath;
    }

    @Before
    public void before() {
        if (StringUtils.isEmpty(springXmlPath)) {
            springXmlPath = "classpath:spring-*.xml";
        }
        context = new ClassPathXmlApplicationContext(springXmlPath.split("[,\\s]+"));
        context.start();
    }

    @After
    public void after() {
        context.destroy();
    }

    @SuppressWarnings("uncheked")
    protected  <T extends Object> T getBean(String beanId) {
        return (T)context.getBean(beanId);
    }

    protected <T extends Object> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
