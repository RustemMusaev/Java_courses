package spring.models;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private int age;
    private List<Car> mycars=new ArrayList<Car>();

    public User(int id, String name, int age, List<Car> mycars) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mycars=mycars;
    }
    public User(int id, String name,int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public int getId() { return id;}
    public String getName() {
        if(this!=null) {
            return name;
        } else {
            System.out.println("not name");
            return "not name";
        }
    }

    public int getAge() {
        return age;
    }

    public void setMycars(List<Car> cars){
        this.mycars=cars;
    }
    public void addMyCar(Car car){ this.mycars.add(car); }
    public List<Car> getMycars(){
        return  this.mycars;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean iquals (User user){
        if(this.getId()==user.getId()&&this.getName()==user.getName()&&this.getAge()==user.getAge()){
            return  true;
        } else {
            return false;
        }
    }
}
