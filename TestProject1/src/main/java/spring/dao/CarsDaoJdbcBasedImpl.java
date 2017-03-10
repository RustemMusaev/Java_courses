package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spring.models.Car;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarsDaoJdbcBasedImpl implements CarsDao {

    private JdbcTemplate template;

    //language=SQL
    String SQL_SELECT_CAR="SELECT * FROM car;";
    //language=SQL
    String SQL_ADD_CAR = "INSERT INTO car(model,color,user_id) values (?,?,?)";
    //language=SQL
    String SQL_FIND_CAR="SELECT * FROM car WHERE id=?";
    //language=SQL
    String SQL_UPDATE_CAR = "UPDATE car SET model=?, color=? WHERE id=?";
    //language=SQL
    String SQL_DELETE_CAR = "DELETE FROM car WHERE id=?";
    @Autowired
    public CarsDaoJdbcBasedImpl(DataSource dataSource){
        this.template=new JdbcTemplate(dataSource);
    }

    private RowMapper<Car> carRowMapper = new RowMapper<Car>() {
        public Car mapRow(ResultSet resultSet, int i) throws SQLException {
            Car car = new Car(resultSet.getInt("id"),resultSet.getString("model"), resultSet.getString("color"));
            return car;
        }
    };
    public List<Car> findAll() {
        return template.query(SQL_SELECT_CAR,carRowMapper);
    }
    public  Integer save(Car car){
        return template.update(SQL_ADD_CAR,car.getModel(),car.getColor(),car.getUser().getId());
    }
    public Car find(Integer id){
        return template.queryForObject(SQL_FIND_CAR,new Object[]{id},carRowMapper);
    }
    public  Integer update(Car car){
        return template.update(SQL_UPDATE_CAR,car.getModel(),car.getColor(),car.getId());
    }
    public  Integer delete(Integer id){
        return template.update(SQL_DELETE_CAR,id);
    }
}