package factory;

import com.sun.prism.impl.FactoryResetException;

/**
 * Created by musaevrr on 20.08.2017.
 */
public class Factory {

    public Factory(){
    }

    public Animal createAnimal(Integer i) {
        if (i > 0) {
            return new Dog();
        }
        if (i < 0) {
            return new Cat();
        }
        return new NullObject();
    }
}
