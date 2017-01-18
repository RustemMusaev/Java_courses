package spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spring.models.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarsDaoJdbcBasedImpl implements CarsDao {

    private JdbcTemplate template;

    //language=SQL
    String SQL_SELECT_CAR="SELECT * FROM car;";
    //language=SQL
    String SQL_ADD_CAR = "insert into car values (?,?,?,?)";
    //language=SQL
    String SQL_FIND_CAR="SELECT * FROM car WHERE car_id=?";
    //language=SQL
    String SQL_UPDATE_CAR = "UPDATE car SET car_model=?, car_color=?, car_user_id=? WHERE car_id=?";
    //language=SQL
    String SQL_DELETE_CAR = "DELETE FROM car WHERE car_id=?";
    @Autowired
    public CarsDaoJdbcBasedImpl(DataSource dataSource){
        this.template=new JdbcTemplate(dataSource);
    }

    private RowMapper<Car> carRowMapper = new RowMapper<Car>() {
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car(resultSet.getInt("car_id"), resultSet.getString("car_model"), resultSet.getString("car_color"));
            return car;
        }
    };
    public List<Car> findAll() {
        List<Car> carslist = new ArrayList<Car>();
        return carslist;
    }
    public  boolean save(Car car){
        template.update(SQL_ADD_CAR,car.getId(),car.getModel(),car.getColor(),car.getUser().getId());
        return true;
    }

    public Car find(int id){
        return template.queryForObject(SQL_FIND_CAR,new Object[]{id},carRowMapper);
    }
    public  boolean update(Car car){
        template.update(SQL_UPDATE_CAR,car.getModel(),car.getColor(),car.getUser().getId(),car.getId());
        return true;
    }
    public  boolean delete(int id){
        template.update(SQL_DELETE_CAR,id);
         return true;
    }
}