package models;

public class Car {
    private int id;
    private String model;
    private String color;
   // private  int id_user;
    User user;

   /* public Car(int id, String model, String color, int id_user) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.id_user = id_user;
    }*/
    public Car(int id, String model, String color, User user) {
        this.id = id;
        this.model = model;
        this.color = color;
        this.user = user;
    }
    public Car(int id, String model, String color) {
        this.id = id;
        this.model = model;
        this.color = color;
    }
    public User getUser(){
        return  this.user;
    }
    public void setUser(User user){
        this.user=user;
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

   /* public int getId_user() {
        return id_user;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

   /* public void setId_user(int id_user) {
        this.id_user = id_user;
    }
*/
    public boolean iquals (Car car){
        if(this.getId()== car.getId()&&this.getModel()== car.getModel()&&this.getColor()== car.getColor()&&this.user== car.user){
            return  true;
        } else {
            return false;
        }
    }}