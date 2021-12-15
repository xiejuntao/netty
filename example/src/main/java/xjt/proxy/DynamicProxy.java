package xjt.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args){
        //HelloImpl hello = new HelloImpl();
//        IHello proxy = (IHello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader()
//                ,HelloImpl.class.getInterfaces(),new HelloInvocationHandler(new HelloImpl()));
//        proxy.hi();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("人生如斯");
                proxy.invokeSuper(obj, args);
                System.out.println("情真情痴");
                return obj;
            }
        });
        HelloImpl hello = (HelloImpl)enhancer.create();
        hello.love();
    }
}
