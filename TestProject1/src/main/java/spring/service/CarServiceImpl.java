package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dao.CarsDao;
import spring.models.Car;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarsDao carsDao;

    public Car find(Integer id) {
        return carsDao.find(id);
    }
    public List<Car> findAll() {
        return carsDao.findAll();
    }
    public Integer save(Car car) {
        return carsDao.save(car);
    }
    public Integer update(Car car) {
        return carsDao.update(car);
    }
    public Integer delete(Integer id) { return carsDao.delete(id);}
}
