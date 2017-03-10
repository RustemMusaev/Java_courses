package spring.service;

import spring.models.Car;

import java.util.List;

public interface CarService {
    Car find(Integer id);
    List<Car> findAll();
    Integer save(Car car);
    Integer update(Car car);
    Integer delete(Integer id);
}

