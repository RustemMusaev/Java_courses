import abstractFactory.AbstractFactory;
import abstractFactory.AnimalAbstract;
import abstractFactory.ConcretFactory;
import builder.Person;
import factory.Animal;
import factory.Factory;
import observer.*;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Main {
    public static void main(String[] args) {
       /* linkedList.LinkedList linkedList=linkedList.LinkedList.createLinkedLidt(50);
        linkedList.printList();

        linkedList.LinkedListMergerSort list=new linkedList.LinkedListMergerSort();
        linkedList=list.sort(linkedList);
        linkedList.printList();
        Singleton.getInstanse();
        Singleton.getInstanse();
        Singleton.getInstanse();
        Singleton.getInstanse();

        Person person = new Person.Builder().name("Rustem").build();
        Person person1 = new Person.Builder().id(2).build();
        Animal animal = new Factory().createAnimal(1);
        Animal animal1 = new Factory().createAnimal(0);
        Animal animal2 = new Factory().createAnimal(-3);
        System.out.println(animal.name());
        System.out.println(animal1.name());
        System.out.println(animal2.name());
        Observer observer = new Server("Server");
        Listener listener1 = new Listener1("First");
        Listener listener2 = new Listener2("Second");
        observer.update("ertert");
        observer.register(listener1);
        observer.register(listener2);
        observer.update("QWE");
       AbstractFactory factory = new ConcretFactory();
        AnimalAbstract animalAbstract = factory.createAnimal();
        animalAbstract.run();*/
        List<String> list = Arrays.asList("asd","asdasd","aaaa","ff","ds");
        Comparator<String> comparator = (s1,s2) -> s1.compareTo(s2);
      //  Collections.sort(list,(a,b) -> Integer.compare(a.length(),b.length()));
        System.out.println(list.get(0));
        list.stream().map(String::toUpperCase).filter(s -> s.startsWith("A")).mapToInt(a->a.length()).sorted().forEach(System.out::println);
    }

}
