import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class User {

    private int id;
    private String name;
    private int age;
    List<Cars> mycars;
    String status;

    public User(int id, String name,int age,Cars mycars) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mycars=null;
    }

    public User(int id, String name,int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public void setMycars(List<Cars> cars){
        this.mycars=cars;
    }

    public List<Cars> getMycars(){
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
