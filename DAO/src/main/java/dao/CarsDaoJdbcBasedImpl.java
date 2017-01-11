package dao;

import factory.DataSourceFactory;
import models.Car;
import models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.activation.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class CarsDaoJdbcBasedImpl implements CarsDao {

    JdbcTemplate template;

    //language=SQL
    String SQL_SELECT_CAR="SELECT * FROM car;";
    //language=SQL
    String SQL_ADD_CAR = "insert into car values (?,?,?,?)";
    //language=SQL
    String SQL_FIND_CAR="SELECT * FROM car WHERE car_id=?";
    //language=SQL
    String SQL_UPDATE_CAR = "UPDATE car SET car_model=?, car_color=?, car_user_id=? WHERE car_id=?";
    //language=SQL
    String SQL_DELETE_CAR = "DELETE FROM car WHERE car_user_id=?";

    public CarsDaoJdbcBasedImpl(DataSource dataSource) {
        this.template=new JdbcTemplate((javax.sql.DataSource) dataSource);
    }

    public List<Car> findAll() {
        List<Car> carslist = new ArrayList<Car>();
        return carslist;
    }
    public  boolean save(Car car){
        template.update(SQL_ADD_CAR,car.getId(),car.getModel(),car.getColor(),car.getUser().getId());
        System.out.println("add car");
        return true;
    }
    public Car find(int id){
        Car result=null;
        //result=template.queryForObject;
        return result;
    }
    public  boolean update(Car car){
        template.update(SQL_UPDATE_CAR,car.getModel(),car.getColor(),car.getUser().getId(),car.getId());
        return true;
    }
    public  boolean delete(int id){
        template.update(SQL_DELETE_CAR,id);
        System.out.println("delete car");
        return true;
    }
}