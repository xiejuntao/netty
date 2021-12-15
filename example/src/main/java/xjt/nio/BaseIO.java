package xjt.nio;

public class BaseIO {
    public static void print(String format,String... params){
        System.out.println(String.format("[" + Thread.currentThread().getId() + "]" + format,params));
    }
}
