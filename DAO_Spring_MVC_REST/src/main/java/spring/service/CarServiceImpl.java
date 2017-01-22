package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.dao.CarsDao;
import spring.models.Car;

import java.util.List;
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarsDao carsDao;

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
