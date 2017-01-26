package spring.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.ArrayList;
import java.util.List;

@JsonInclude
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private List<Car> mycars;

    public User(Integer id, String name, Integer age, List<Car> mycars) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mycars=mycars;
    }
    public User(String name,Integer age) {
        this.name = name;
        this.age = age;
    }
    public User() {
    }
    public Integer getId() { return id;}
    public String getName() {
            return name;
    }
    public Integer getAge() {
        return age;
    }
    public void setMycars(List<Car> cars){
        this.mycars=cars;
    }
    public List<Car> getMycars(){
        return  this.mycars;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
