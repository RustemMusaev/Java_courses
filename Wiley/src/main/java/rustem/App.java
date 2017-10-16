package rustem;


import rustem.cacheL1.CacheL1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App {

    public static final String PROPERTIES_FILE_NAME = "application.properties";

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
            properties.load(new FileInputStream(PROPERTIES_FILE_NAME));
            long CACHE_L_1_SIZE = Long.parseLong(properties.getProperty("cacheL1.size"));
            long CACHE_L_2_SIZE = Long.parseLong(properties.getProperty("cacheL2.size"));
            String CACHE_L_2_FILE =properties.getProperty("cacheL2.file");
            CacheL1 cacheL1 = new CacheL1(CACHE_L_1_SIZE,CACHE_L_2_SIZE,CACHE_L_2_FILE);


        Object object = new Object();
        Object current = cacheL1.startCache(object);
    }

}
