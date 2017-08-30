package observer;

/**
 * Created by musaevrr on 20.08.2017.
 */
public interface Observer {

    void register(Listener listener);
    void unRegister(Listener listener);
    void update(String name);
    String getName();
}
