package rustem.cacheL1;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import rustem.ObjectSizeFetcher;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class CacheL2 {
    private long maxSize;
    private long size;
    private String file;

    public CacheL2(long maxSize, String file) {
        this.maxSize = maxSize;
        this.file = file;
    }

    public boolean writeToFile(Map map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(file), map);
        }catch (JsonGenerationException e) {
            System.out.println("JsonGenerationException");
        } catch (JsonMappingException e) {
            System.out.println("JsonMappingException");
        } catch (IOException e) {
            System.out.println("Error read/wrie file"+ file);
        }
        return true;
    }

    public TreeMap<Object, Integer> readFile() {
        TreeMap<Object, Integer> map = new TreeMap<Object, Integer>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(
                    new File(file), new TypeReference<Map<Object, Integer>>() {
                    });

        } catch (JsonGenerationException e) {
            System.out.println("JsonGenerationException");
        } catch (JsonMappingException e) {
            System.out.println("JsonMappingException");
        } catch (IOException e) {
            System.out.println("Error read/wrie file"+ file);
        }
        return map;
    }

    public Object addObject(Object object) {
        TreeMap map = readFile();
        long objectSize = ObjectSizeFetcher.getObjectSize(object);
        while (maxSize < (size + objectSize)) {
            Object current = map.pollFirstEntry();
            size = size - ObjectSizeFetcher.getObjectSize(current);
        }
        map.put(object, 0);
        writeToFile(map);
        size = size + objectSize;
        return object;

    }

    public boolean contains(Object object) {
        TreeMap map = readFile();
        return map.containsKey(object);
    }

    public Object getObject(Object object) {
        Object current= updateObject(object);
        return current;
    }

    public Object updateObject(Object object) {
        TreeMap map = readFile();
        Integer current = (Integer) map.get(object);
        map.replace(object, new Integer(current.intValue() + 1));
        writeToFile(map);
        return object;
    }
}
