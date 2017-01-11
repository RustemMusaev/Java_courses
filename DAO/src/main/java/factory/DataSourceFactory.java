package factory;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataSourceFactory {
    private static DataSourceFactory instanse;
    private DataSource dataSource;
    private  static String PROPERTIES_FILE_NAME =
            "C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\src\\main\\resources\\contex.properties";

    static {
            instanse=new DataSourceFactory();
    }
    public static DataSourceFactory getInstanse(){
        return instanse;
    }
    public DataSource getDataSource(){
        return dataSource;
    }


    private DataSourceFactory() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driverClassName = properties.getProperty("db.driver");
        String dbUrl = properties.getProperty("db.url");
        String dbUserName = properties.getProperty("db.user.name");
        String dbPassword = properties.getProperty("db.password");

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(driverClassName);
        driverManagerDataSource.setUrl(dbUrl);
        driverManagerDataSource.setUsername(dbUserName);
        driverManagerDataSource.setPassword(dbPassword);
        dataSource = driverManagerDataSource;
    }
}
