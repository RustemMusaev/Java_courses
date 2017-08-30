package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by musaevrr on 20.08.2017.
 */
public class Server implements Observer {
    private String name;
    List<Listener> list;

    public Server(String name) {
        this.name = name;
        list = new ArrayList<Listener>();
    }

    public void register(Listener listener) {
        if (listener != null) {
            list.add(listener);
            listener.addServer(this);
        }
    }

    public void unRegister(Listener listener) {
        list.remove(listener);
    }

    public void update(String name) {
        this.name = name;
        System.out.println("My name " + name);
        for (Listener listener: list) {
            listener.getUdate();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
