package singleton;

public class Singleton {

    private static volatile Singleton instanse;

    private Singleton(){
        System.out.println("1");
    };

    public static synchronized Singleton getInstanse(){
        if (instanse == null) {
            synchronized (Singleton.class) {
                if (instanse == null) {
                    instanse = new Singleton();
                }
            }
        }
        return instanse;
    }
}
