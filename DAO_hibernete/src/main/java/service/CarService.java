package service;

import dao.CarsDao;
import models.Car;

import java.util.List;

public class CarService {
    private CarsDao carsDao;

    public CarService(CarsDao carsDao) {
        this.carsDao = carsDao;
    }
    public boolean IsRegistred(String name) {
        return true;
    }
    public Car find(int id){
        return carsDao.find(id);
    };
    List<Car> findAll(){
        return carsDao.findAll();
    };
    public boolean save(Car car){
        return carsDao.save(car);
    };
    public boolean update(Car car){
        return carsDao.update(car);
    };
    public boolean delete(int id){
        return carsDao.delete(id);
    };
}

