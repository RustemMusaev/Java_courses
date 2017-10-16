package rustem.cacheL1;


public class CacheL1 {

    private CacheL1In cacheIn;
    private CacheL1Out cacheOut;
    private CacheL1Main cacheMain;
    private CacheL2 cacheL2;

    public CacheL1(long maxSizeL1, long maxSizeL2, String file) {
        this.cacheIn = new CacheL1In((int) (maxSizeL1*0.2));
        this.cacheOut = new CacheL1Out((int) (maxSizeL1*0.6));
        this.cacheMain = new CacheL1Main((int) (maxSizeL1*0.2));
        this.cacheL2 = new CacheL2(maxSizeL2,file);
    }

    public Object startCache(Object object){
        Object current;
        if (cacheIn.contains(object)) {
            current = cacheIn.getObject(object);
        } else {
            if (cacheOut.contains(object)) {
                current = cacheOut.removeObject(object);
                while (cacheMain.isFull(object)) {
                    Object temp = cacheMain.remove();
                    cacheL2.addObject(temp);
                }
                cacheMain.addObject(current);
            } else if (cacheMain.contains(object)) {
                current = cacheMain.updateObject(object);
            } if (cacheL2.contains(object)) {
                current = cacheL2.getObject(object);
            } else {
                while (cacheIn.isFull(object)){
                    current = cacheIn.remove();
                    cacheOut.addObject(current);
                }
                current = cacheIn.addObject(object);
                return current;
            }
        }
        return current;
    }
}
