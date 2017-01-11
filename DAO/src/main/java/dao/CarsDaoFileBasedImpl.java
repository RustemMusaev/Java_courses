package dao;
/*
import models.Car;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CarsDaoFileBasedImpl implements CarsDao {

    String carsFileName;
    final String SEPARATOR="\t";

    CarsDaoFileBasedImpl(String carsFileName) {
        this.carsFileName = carsFileName;
    }
    public List<Car> findAll() {
        List<Car> carslist = new ArrayList<Car>();
    //    List<models.Car> carsList=new ArrayList<models.Car>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(carsFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] carsdata=line.split("\t");
                Car car =new Car(Integer.parseInt(carsdata[0]),carsdata[1],carsdata[2],Integer.parseInt(carsdata[3]));
                carslist.add(car);
            }
        } catch (Exception e) {
           // throw new IllegalArgumentException();
        }
         return carslist;
    }
    public Car find(int id){
        List<Car> carList =this.findAll();
        Car findcars = null;
        for (Car car : carList){
            if(id== car.getId()) {
                findcars= car;
            }
        }
        return findcars;
    }
    public  boolean save(Car car){
        List<Car> carList =this.findAll();
        int count=0;
        for (Car carssearch: carList) {
            if (car.getId() == carssearch.getId()) {
                count++;
            }
        }
        if(count==0) {
            carList.add(car);
            WriteCarsFile(carList,carsFileName);
            System.out.println("Добавлен новый models.Car");
        } else {
            System.out.println("Не уникальный id");
        }
        return true;
    }
    public  boolean update(Car car){
        List<Car> carList =this.findAll();
        for (Car carssearch: carList){
            if(car.getId()==carssearch.getId()) {
                carssearch.setModel(car.getModel());
                carssearch.setColor(car.getColor());
                carssearch.setId_user(car.getId_user());
            }
        }
        WriteCarsFile(carList,carsFileName);
        return true;
    }
    public  boolean delete(int id){
        List<Car> carList =this.findAll();
        int indexremove=-1;
        for (Car carssearch: carList){
            if(carssearch.getId()==id){
                indexremove= carList.indexOf(carssearch);
            }
        }
        if(indexremove!=-1){
            carList.remove(indexremove);
            System.out.println("Delete complete");
            WriteCarsFile(carList,carsFileName);
        } else {
            System.out.println("id не найден");
        }
        return true;
    }

    public  void WriteCarsFile(List<Car> carList, String carsFileName){
        try(FileWriter writer = new FileWriter(carsFileName, false))
        {
            for(Car car : carList) {
                String text = car.getId() + SEPARATOR + car.getModel() + SEPARATOR + car.getColor() +SEPARATOR + car.getId_user() + System.getProperty("line.separator");
                writer.write(text);
            }
        } catch (IOException ex){
            throw new IllegalArgumentException();
        }
    }
    }
*/