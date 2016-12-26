import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoSqlBasedImpl implements UsersDao {

    private static String url="jdbc:postgresql://localhost:5432/MavenDatabase";
    private static String name="maven_allow";
    private static String password="Qaz!23$56";

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<User>();
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection(url,name,password);
            Statement statement=connection.createStatement();
            String SQL_SELECT_USER="SELECT * FROM group_user;";
            ResultSet resultSet=statement.executeQuery(SQL_SELECT_USER);
          //  List<Cars> carsList=new ArrayList<Cars>();
            while (resultSet.next()){
                //carsList=getUserCars(resultSet.getInt("user_id"));
                userList.add(new User(resultSet.getInt("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getInt("user_age"),getUserCars(resultSet.getInt("user_id"))));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }  return userList;
    }
    @Override
    public User find(int id) {
        Connection connection=null;
        User result=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement=connection.createStatement();
            String SQL_FIND_USER="SELECT * FROM group_user WHERE user_id="+id;
            ResultSet resultSet=statement.executeQuery(SQL_FIND_USER);
            while (resultSet.next()){
                result=new User(resultSet.getInt("user_id"),resultSet.getString("user_name"),resultSet.getInt("user_age"));
            }connection=null;
            System.out.println("find user");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } return result;
    }
    @Override
    public boolean save(User user) {
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement = connection.createStatement();
            //language=SQL
            String SQL_SAVE_USER ="INSERT INTO group_user VALUES ("+user.getId()+", '"+user.getName()+"', "+user.getAge()+");";
            statement.execute(SQL_SAVE_USER);
            connection=null;
            System.out.println("add user");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean update(User user) {
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement = connection.createStatement();
            //language=SQL
            String SQL_UPDATE_USER = "UPDATE group_user SET user_name='"+user.getName()
                    +"', user_age="+user.getAge()
                    +" WHERE user_id="+user.getId();
            statement.execute(SQL_UPDATE_USER);
            connection=null;
            System.out.println("update user");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void printmycarslist(User users) {//ДОДЕЛАТЬ
        CarsDaoSqlBasedImpl carsDaoSqlBased=new CarsDaoSqlBasedImpl();
        List<Cars> carsList=carsDaoSqlBased.findAll();
        System.out.println("My car list:");
        for(Cars car:carsList) {
            System.out.println("car_id= "+car.getId()+"\t car_model= "+car.getModel()+"\t car_color= "+car.getColor());
        }
    }

    @Override
    public List<Cars> getUserCars(int user_id) {
        Connection connection=null;
        List<Cars> carsList=new ArrayList<Cars>();
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement=connection.createStatement();
            String SQL_FIND_CAR="SELECT * FROM car WHERE car_user_id="+user_id;
            ResultSet resultSet=statement.executeQuery(SQL_FIND_CAR);
            while (resultSet.next()){
                carsList.add(new Cars(resultSet.getInt("car_id"),resultSet.getString("car_model"),resultSet.getString("car_color"),resultSet.getInt("car_user_id")));
            }
            connection=null;
            System.out.println("find car");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } return carsList;
    }
}
