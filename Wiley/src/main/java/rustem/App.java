package rustem;


import rustem.cacheL1.CacheL1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class App {

    public static final String PROPERTIES_FILE_NAME = "application.properties";
    static long cacheL1Size;
    static long cacheL2Size;
    static String cacheL2File;

    public static void main(String[] args) {
        try {
            init(PROPERTIES_FILE_NAME);
        } catch (IOException e) {
            System.out.println("File not found/ Error open file");
        }
        CacheL1 cacheL1 = new CacheL1(cacheL1Size, cacheL2Size, cacheL2File);

        Object object = new Object();
        Object current = cacheL1.startCache(object);
    }

    private static void init(String file) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(file));
        long cacheL1Size = Long.parseLong(properties.getProperty("cacheL1.size"));
        long cacheL2Size = Long.parseLong(properties.getProperty("cacheL2.size"));
        String cacheL2File = properties.getProperty("cacheL2.file");

    }

}
