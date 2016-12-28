import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoSqlBasedImpl implements UsersDao {

    Connection connection;
    public UsersDaoSqlBasedImpl(Connection connection) {
        this.connection=connection;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<User>();
         try {
             String SQL_SELECT_USER="SELECT * FROM group_user;";
             Statement statement=connection.createStatement();
             ResultSet resultSet=statement.executeQuery(SQL_SELECT_USER);
             while (resultSet.next()){
                userList.add(new User(resultSet.getInt("user_id"),resultSet.getString("user_name"),
                        resultSet.getInt("user_age"),getUserCars(resultSet.getInt("user_id"))));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    @Override
    public User find(int id) {
        User result=null;
        try {
            String SQL_FIND_USER="SELECT * FROM group_user WHERE user_id="+id;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SQL_FIND_USER);
            while (resultSet.next()){
                result=new User(resultSet.getInt("user_id"),resultSet.getString("user_name"),resultSet.getInt("user_age"));
            }
         } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("find user");
        return result;
    }
    @Override
    public boolean save(User user) {
        try {
            //language=SQL
            String SQL_SAVE_USER ="INSERT INTO group_user VALUES ("+user.getId()+", '"+user.getName()+"', "+user.getAge()+");";
            Statement statement = connection.createStatement();
            statement.execute(SQL_SAVE_USER);
            for (Cars cars:user.getMycars()){
                //language=SQL
                String SQL_SAVE_USERCAR = "insert into car values (" + cars.getId() + ", '" + cars.getModel() + "', '" +
                        cars.getColor() + "'," + user.getId() + ")";
                statement.execute(SQL_SAVE_USERCAR);
                System.out.println("add car, car id"+cars.getId());
            }
        } catch (SQLException e) {
            System.out.println("ERROR add user");
            e.printStackTrace();
            return false;
        }
        System.out.println("add user");
        return true;
    }
    @Override
    public boolean update(User user) {
        try {
            //language=SQL
            String SQL_UPDATE_USER = "UPDATE group_user SET user_name='"+user.getName()
                    +"', user_age="+user.getAge()
                    +" WHERE user_id="+user.getId();
            Statement statement = connection.createStatement();
            statement.execute(SQL_UPDATE_USER);
      } catch (SQLException e) {
            System.out.println("ERROR update user");
            e.printStackTrace();
            return false;
        }
        System.out.println("update user");
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            //language=SQL
            String SQL_DELETE_USER = "DELETE FROM group_user WHERE user_id="+id;
            Statement statement = connection.createStatement();
            statement.execute(SQL_DELETE_USER);
        } catch (SQLException e) {
            System.out.println("ERROR delete user and his cars");
            e.printStackTrace();
            return false;
        }
        System.out.println("delete user and his cars");
        return true;
    }

    @Override
    public void printmycarslist(User users) {//ДОДЕЛАТЬ
        CarsDaoSqlBasedImpl carsDaoSqlBased=new CarsDaoSqlBasedImpl(connection);
        List<Cars> carsList=carsDaoSqlBased.findAll();
        System.out.println("My car list:");
        for(Cars car:carsList) {
            System.out.println("car_id= "+car.getId()+"\t car_model= "+car.getModel()+"\t car_color= "+car.getColor());
        }
    }

    @Override
    public List<Cars> getUserCars(int user_id) {
        List<Cars> carsList=new ArrayList<Cars>();
        try {
            //language=SQL
            String SQL_FIND_CAR="SELECT * FROM car WHERE car_user_id="+user_id;
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SQL_FIND_CAR);
            while (resultSet.next()){
                carsList.add(new Cars(resultSet.getInt("car_id"),resultSet.getString("car_model"),resultSet.getString("car_color"),resultSet.getInt("car_user_id")));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("find car");
        return carsList;
    }

}
