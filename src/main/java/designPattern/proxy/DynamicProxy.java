package designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by LiLi on 16/11/22.
 */
public class DynamicProxy implements InvocationHandler {
    private  Subject subject;

    DynamicProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling " + method);
        method.invoke(subject,args);
        System.out.println("after calling " + method);
        return null;
    }
}
