package spring.dao;

import spring.models.Car;

import java.util.List;

public interface CarsDao {

    Car find(int id);
    List<Car> findAll();
    boolean save(Car car);
    boolean update(Car car);
    boolean delete(int id);
}