package daily0921.injection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

/**
 * Created by tqmall on 16/9/21.
 */
public class MyResource implements ApplicationContextAware{
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void resource() {
        Resource resource = applicationContext.getResource("classpath:");

    }
}
