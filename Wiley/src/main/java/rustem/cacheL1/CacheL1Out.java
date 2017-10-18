package rustem.cacheL1;


import rustem.ObjectSizeFetcher;

import java.util.LinkedList;
import java.util.List;

public class CacheL1Out {
    private long size;
    private long maxSize;
    private List queue;

    public CacheL1Out(long maxSize) {
        this.size = 0;
        this.maxSize = maxSize;
        this.queue = new LinkedList();
    }

    public boolean contains(Object object) {
        return queue.contains(object);
    }

    public Object addObject(Object object) {
        long objectSize = ObjectSizeFetcher.getObjectSize(object);
        while (maxSize < (size + objectSize)) {
            Object current = queue.remove(0);
            size = size - ObjectSizeFetcher.getObjectSize(current);
        }
        queue.add(object);
        size = size + objectSize;
        return object;
    }

    public Object removeObject(Object object) {
        Object current = queue.remove(queue.indexOf(object));
        size = size - ObjectSizeFetcher.getObjectSize(current);
        return current;
    }
}
