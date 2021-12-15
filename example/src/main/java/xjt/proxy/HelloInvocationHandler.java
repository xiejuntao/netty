package xjt.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloInvocationHandler implements InvocationHandler {
    private Object target;

    public HelloInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("天凉好个秋");
        Object result = method.invoke(target, args);
        System.out.println("衣裳薄");
        return result;
    }
}
