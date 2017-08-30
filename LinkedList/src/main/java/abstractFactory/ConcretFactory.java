package abstractFactory;

import factory.*;

/**
 * Created by musaevrr on 20.08.2017.
 */
public class ConcretFactory implements AbstractFactory{
    public AnimalAbstract createAnimal() {
        return new ConcretAnimal();
    }
}
