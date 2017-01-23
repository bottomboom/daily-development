package daily0921.injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tqmall on 16/9/23.
 */
@Service
public class InjectionServiceImpl implements InjectionService {

    @Autowired
    private InjectionDAO injectionDAO;

    @Override
   // @Autowired(required = false)
    public void save(String arg) {
        injectionDAO.save(arg);
        arg = arg + this.hashCode();
        System.out.println("Service保存数据:"+arg);
    }
}
