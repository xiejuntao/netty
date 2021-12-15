package xjt.classloader;

public class TestMyClassLoader {
    public static void main(String[] args){
        MyClassLoader mcl = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
        try {
            Class<?> c1 = Class.forName("xjt.classloader.Person", true, mcl);
            Object person = c1.newInstance();
            System.out.println(person.getClass());
            System.out.println(person.getClass().getClassLoader());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
