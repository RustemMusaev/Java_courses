package spring.service;

import spring.dao.CarsDao;
import spring.models.Car;

import java.util.List;

public class CarServiceImpl implements CarService {

    private CarsDao carsDao;

    public CarServiceImpl(CarsDao carsDao) {
        this.carsDao = carsDao;
    }

    public CarServiceImpl() {
    }

    public boolean IsRegistred(String name) {
        return true;
    }

    public Car find(int id) {
        return carsDao.find(id);
    }

    ;

    public List<Car> findAll() {
        return carsDao.findAll();
    }

    ;

    public boolean save(Car car) {
        return carsDao.save(car);
    }

    ;

    public boolean update(Car car) {
        return carsDao.update(car);
    }

    ;

    public boolean delete(int id) {
        return carsDao.delete(id);
    }
}
