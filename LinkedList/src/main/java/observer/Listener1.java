package observer;


/**
 * Created by musaevrr on 20.08.2017.
 */
public class Listener1 implements Listener {

    private String name;
    private Observer observer;

    public Listener1(String name) {
        this.name = name;
    }


    public void addServer(Observer observer) {
        this.observer = observer;
    }

    public void getUdate() {
        System.out.println("First say"+this.observer.getName());
    }
}
