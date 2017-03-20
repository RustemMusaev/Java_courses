package ru.rustem.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class implement all method TestDao interface. In constructor object transmitted name of properties file.
 * Method getConnection() use this file for return creating connection on database. Each method use SQL request for obtains results.
 * findAll() method open connection, using SQL request get row on table and add her to List<Integer>. When all rows are processed, method
 * return this list as result. After close connection.
 * deleteAll() method after delete all rows in table, checks count of delete rows. If count of delete rows is null, then table was empty.
 * save() method input parametr count rows for insert to table in database. After open connection method prepared statemet using all
 * data and after insert this informathion on table.
 */
public class TestDaoImpl implements TestDao{

    private Connection connection;
    String propertiesFileName;

    public TestDaoImpl(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }
    @Override
    public  List<Integer> findAll() {
        connection = getConnection();
        List<Integer> result = new ArrayList<Integer>();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM testdb");
            while (resultSet.next()) {
               result.add(resultSet.getInt("column_1"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public void deleteAll() {
        connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            if(statement.executeUpdate("DELETE FROM testdb") > 0){
                System.out.println("Deleted All Rows In The Table Successfully...");
            }else{
                System.out.println("Table already empty.");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean save(int count) {
        connection = getConnection();
        try {
            Class.forName("org.postgresql.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TestDB","postgres","postgres");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO testdb (column_1) VALUES (?)");
        for (int i = 0;i < count; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
                return false;
            }
        }
    public Connection getConnection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propertiesFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            Class.forName(properties.getProperty("db.driver"));
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
