package designPattern.proxy;

/**
 * Created by LiLi on 16/11/22.
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject is working.");
    }
}
