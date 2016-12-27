
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class CarsDaoSqlBasedImpl implements CarsDao{

    Connection connection;
    public CarsDaoSqlBasedImpl(Connection connection) {
        this.connection=connection;
    }

    public List<Cars> findAll() {
        List<Cars> carslist = new ArrayList<Cars>();
        try {
            //language=SQL
            String SQL_SELECT_CAR="SELECT * FROM car;";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SQL_SELECT_CAR);
            while (resultSet.next()){
            carslist.add(new Cars(resultSet.getInt("car_id"),resultSet.getString("car_model"),resultSet.getString("car_color"),resultSet.getInt("car_user_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carslist;
    }
    public  boolean save(Cars cars){
        try {
           //language=SQL
            String SQL_ADD_CAR = "insert into car values (" + cars.getId() + ", '" + cars.getModel() + "', '" +
                    cars.getColor() + "'," + cars.getId_user() + ")";
            Statement statement = connection.createStatement();
            statement.execute(SQL_ADD_CAR);
           }  catch (SQLException e) {
            System.out.println("ERROR add car");
            e.printStackTrace();
            return false;
        }
        System.out.println("add car");
        return true;
    }
    public Cars find(int id){
        Cars result=null;
        try {
            String SQL_FIND_CAR="SELECT * FROM car WHERE car_id="+id;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SQL_FIND_CAR);
            resultSet.next();
            result=new Cars(resultSet.getInt("car_id"),resultSet.getString("car_model"),resultSet.getString("car_color"),resultSet.getInt("car_user_id"));
       }
        catch (SQLException e) {
            System.out.println("ERROR find car");
            e.printStackTrace();
        }
        System.out.println("find car");
        return result;
    }
    public  boolean update(Cars cars){
        try {
           //language=SQL
            String SQL_UPDATE_CAR = "UPDATE car SET car_model='"+cars.getModel()+"', car_color='"+cars.getColor()
                    +"', car_user_id="+cars.getId_user()+" WHERE car_id="+cars.getId();
            Statement statement = connection.createStatement();
            statement.execute(SQL_UPDATE_CAR);
        } catch (SQLException e) {
            System.out.println("ERROR update car");
            e.printStackTrace();
            return false;
        }
        System.out.println("update car");
        return true;
    }
    public  boolean delete(int id){
        try {
            //language=SQL
            String SQL_DELETE_CAR = "DELETE FROM car WHERE car_user_id="+id;
            Statement statement = connection.createStatement();
            statement.execute(SQL_DELETE_CAR);
        } catch (SQLException e) {
            System.out.println("ERROR delete car");
            e.printStackTrace();
            return false;
        }
        System.out.println("delete car");
        return true;
    }
}