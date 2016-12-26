import java.sql.*;
import java.util.Scanner;

public class Main {
    //language=SQL
    private static final String SQL_INSERT_USER=
            "INSERT INTO group_user(user_id,user_name) VALUES (?, ?);";
    private static final String SQL_SELECT_USER=
            "SELECT * FROM car;";

    public static void main(String[] args) {

        Connection connection=null;
        String url="jdbc:postgresql://localhost:5432/MavenDatabase";
        String name="maven_allow";
        String password="Qaz!23$56";

        System.out.println("Input user_id =");
        Scanner in=new Scanner(System.in);
        int id=in.nextInt();

        try {
            //передаем драйвер в виртуальную таблицу
            Class.forName("org.postgresql.Driver");
            //говорим виртуальной машине создать коннект
            connection= DriverManager.getConnection(url,name,password);//name,password);
            //создаем выражение,которое может исполняться
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SQL_SELECT_USER);


            while (resultSet.next()) {
                if(resultSet.getInt("car_user_id")==id) {
                    System.out.println("car_id=\t" + resultSet.getInt("car_id") + "\tcar_model=\t" + resultSet.getString("car_model")+"\tcar_color=\t" + resultSet.getString("car_color"));
                }
            }

          /*  PreparedStatement preparedStatement=connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setInt(1,44);
            preparedStatement.setString(2,"Vasya");
            preparedStatement.execute();*/

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
