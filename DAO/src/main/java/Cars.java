import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cars{
    private int id;
    private String model;
    private String color;
    private  int id_user;

    public Cars(int id, String model, String color, int id_user) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.id_user = id_user;
    }
    public Cars(int id, String model, String color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public boolean iquals (Cars cars){
        if(this.getId()==cars.getId()&&this.getModel()==cars.getModel()&&this.getColor()==cars.getColor()&&this.getId_user()==cars.getId_user()){
            return  true;
        } else {
            return false;
        }
    }}
