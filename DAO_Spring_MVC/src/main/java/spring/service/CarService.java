package spring.service;

import spring.models.Car;

import java.util.List;

public interface CarService {

    public boolean IsRegistred(String name);
    public Car find(int id);
    List<Car> findAll();
    public boolean save(Car car);
    public boolean update(Car car);
    public boolean delete(int id);
}

