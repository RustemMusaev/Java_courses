package rustem.cacheL1;

import rustem.ObjectSizeFetcher;

import java.util.LinkedList;
import java.util.List;

public class CacheL1In {
    private List queue;
    private long size;
    private long maxSize;



    public CacheL1In(long maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.queue = new LinkedList();
    }

    public boolean contains(Object object){
        return queue.contains(object);
    }

    public Object getObject(Object object){
        Object current = queue.get(queue.indexOf(object));
        return current;
    }

    public Object addObject(Object object) {
            queue.add(object);
            size=size+ ObjectSizeFetcher.getObjectSize(object);
            return object;
    }

    public boolean isFull(Object object){
        return  (maxSize) < (size + ObjectSizeFetcher.getObjectSize(object));
    }

    public Object remove(){
        Object current = queue.remove(0);
        size = size - ObjectSizeFetcher.getObjectSize(current);
        return current;
    }
}
