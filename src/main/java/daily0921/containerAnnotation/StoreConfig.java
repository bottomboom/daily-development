package daily0921.containerAnnotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by tqmall on 16/10/25.
 */

/**
 * @Configuration
 * @Bean
 * 等价于在xml中写文档
 * <bean id = ></bean>
 */
@Configuration
@ImportResource(value = "classpath://spring-resource")
public class StoreConfig {
    @Value("${userName}")
    public String userName;

    @Value("${password}")
    public String password;

    @Value("${url}")
    public String url;

    @Bean
    public DateSourceManager dateSourceManager() {
        return new DateSourceManager(userName, password, url);
    }


    @Bean(name = "store",initMethod = "init", destroyMethod = "destroy")
    public Store stringStore() {
        return new StringStore();
    }
}
