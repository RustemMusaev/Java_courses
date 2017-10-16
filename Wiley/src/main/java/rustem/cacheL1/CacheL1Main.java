package rustem.cacheL1;

import rustem.ObjectSizeFetcher;

import java.util.*;

public class CacheL1Main {
    private long size;
    private long maxSize;
    private TreeMap<Object,Integer> map;

    public CacheL1Main(long size) {
        this.size = size;
        this.map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (map.get(o1).intValue() < map.get(o2).intValue()){
                    return 1;
                }else
                    return -1;
            }
        });
    }

    public boolean contains(Object object){
        return map.containsKey(object);
    }

    public Object updateObject(Object object) {
            Integer current = (Integer) map.get(object);
            map.replace(object,new Integer(current.intValue()+1));
        return object;
    }
    public Object removeObject(Object object) {
        return map.remove(object);
    }

    public Object addObject(Object object){
        Map.Entry<Object,Integer> entry = map.pollFirstEntry();
        map.put(object, 0);
        return entry.getKey();
    }

    public boolean isFull(Object object){
        return  (maxSize) < (size + ObjectSizeFetcher.getObjectSize(object));
    }
    public Object remove(){
        Object current = map.pollFirstEntry();
        size =size - ObjectSizeFetcher.getObjectSize(current);
        return current;
    }

}
