package designPattern.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by LiLi on 16/11/22.
 */
public class Client {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();
        DynamicProxy dynamicProxy = new DynamicProxy(realSubject);
        Class clazz = realSubject.getClass();

        Subject subject = (Subject) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),dynamicProxy);
        subject.request();
    }
}
