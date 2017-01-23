package daily0921.mutiBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by tqmall on 16/10/24.
 */
@Component
public class BeanInvoker {
    /**
     * List 只存对象
     */
    @Autowired
    private List<BeanInterface> beanInterfaceList;

    /**
     * Map 存<String,Object>,存Id和对象
     */
    @Autowired
    private Map<String, BeanInterface> beanInterfaceMap;

    /**
     * Interface 有多个实现类时，用Qualifier指定哪一个
     */
    @Autowired
    @Qualifier(value = "beanImplTwo")
    private BeanInterface beanInterface;

    public void say() {
        if (null != beanInterfaceList && 0 != beanInterfaceList.size()) {
            System.out.println("---------List-------------");
            for (BeanInterface beanInterface : beanInterfaceList) {
                System.out.println(beanInterface.getClass().getName());
            }
        }
        else {
            System.out.println("beanInterfaceList is null");
        }

        if (null != beanInterfaceMap && 0 != beanInterfaceMap.size()) {
            System.out.println("-----------Map-------------");
            for (Map.Entry<String, BeanInterface> entry : beanInterfaceMap.entrySet()) {
                System.out.println(entry.getKey()+"      "+entry.getValue());
            }
        }
        else {
            System.out.println("beanInterfaceMap is null");
        }

        if (null != beanInterface) {
            System.out.println("---------BeanInterface---------");
            System.out.println("BeanInterface:"+beanInterface.getClass().getName());
        }
    }
}
