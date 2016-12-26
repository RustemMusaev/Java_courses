import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class CarsDaoSqlBasedImpl implements CarsDao{

    private static String url="jdbc:postgresql://localhost:5432/MavenDatabase";
    private static String name="maven_allow";
    private static String password="Qaz!23$56";

    public List<Cars> findAll() {
        List<Cars> carslist = new ArrayList<Cars>();
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement=connection.createStatement();
            String SQL_SELECT_CAR="SELECT * FROM car;";
            ResultSet resultSet=statement.executeQuery(SQL_SELECT_CAR);
            while (resultSet.next()){
            carslist.add(new Cars(resultSet.getInt("car_id"),resultSet.getString("car_model"),resultSet.getString("car_color"),resultSet.getInt("car_user_id")));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }  return carslist;
    }
    public  boolean save(Cars cars){
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement = connection.createStatement();
            //language=SQL
            String SQL_ADD_CAR = "insert into car values (" + cars.getId() + ", '" + cars.getModel() + "', '" +
                    cars.getColor() + "'," + cars.getId_user() + ")";
            statement.execute(SQL_ADD_CAR);
            connection=null;
            System.out.println("add car");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Cars find(int id){
       Connection connection=null;
        Cars result=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement=connection.createStatement();
            String SQL_FIND_CAR="SELECT * FROM car WHERE car_id="+id;
            ResultSet resultSet=statement.executeQuery(SQL_FIND_CAR);
            while (resultSet.next()){
                result=new Cars(resultSet.getInt("car_id"),resultSet.getString("car_model"),resultSet.getString("car_color"),resultSet.getInt("car_user_id"));
            }connection=null;
            System.out.println("find car");

       } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } return result;
    }
    public  boolean update(Cars cars){
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement = connection.createStatement();
            //language=SQL
            String SQL_UPDATE_CAR = "UPDATE car SET car_model='"+cars.getModel()
                    +"', car_color='"+cars.getColor()
                    +"', car_user_id="+cars.getId_user()
                    +" WHERE car_id="+cars.getId();
            statement.execute(SQL_UPDATE_CAR);
            connection=null;
            System.out.println("update car");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public  boolean delete(int id){
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,name,password);
            Statement statement = connection.createStatement();
            //language=SQL
            String SQL_DELETE_CAR = "DELETE FROM car WHERE car_user_id="+id+" AND car_id=23;";
            statement.execute(SQL_DELETE_CAR);
            connection=null;
            System.out.println("delete car");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}