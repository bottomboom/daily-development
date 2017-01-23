package daily0921.injection;

import org.springframework.stereotype.Repository;

/**
 * Created by tqmall on 16/9/21.
 */

@Repository
public class InjectionDAOImpl implements InjectionDAO {
    public void save(String arg) {
        System.out.println("数据库保存数据："+arg);
    }
}
