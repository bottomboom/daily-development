package daily0921.containerAnnotation;

/**
 * Created by tqmall on 16/10/25.
 */

public class StringStore implements Store {

    public void init() {
        System.out.println("StringStore is initing.");
    }

    public void destroy() {
        System.out.println("StringStore is destroying.");
    }
}
