package linkedList;

public class LinkedListMergerSort {

    private static final int MAX_BUCKETS_COUNT = 32;
    private static Bucket[] buckets;

    private static class Bucket {
        // связный список корзины
        LinkedList list;
        // ранг корзины - количество элементов в списке
        int rank;

        Bucket() {
            list = new LinkedList();
            rank = 0;
        }
    }
    public LinkedList merger(LinkedList leftlist, LinkedList rightlist){
        LinkedList result=new LinkedList();
        while (leftlist.hasNext()&&rightlist.hasNext()) {
            if(leftlist.getValue()<rightlist.getValue()) {
                result.addlast(leftlist.getValue());
                System.out.print("<\t");
                leftlist.next();
            } else {
                result.addlast(rightlist.getValue());
                System.out.print(">\t");
                rightlist.next();
            }
        }
        while (leftlist.hasNext()){
            result.addlast(leftlist.getValue());
            leftlist.next();
            System.out.print("left add\t");
        }
        while (rightlist.hasNext()){
            result.addlast(rightlist.getValue());
            rightlist.next();
            System.out.print("right add\t");
        }
        System.out.println("count= "+result.getCount());
        return result;
    }
    public void bucketsinit() {
        buckets=new Bucket[MAX_BUCKETS_COUNT];
        for (int j=0;j<MAX_BUCKETS_COUNT;j++){
            buckets[j]=new Bucket();
        }
    }
    public  void bucketsmerger(int i){
        buckets[i-2].list=merger(buckets[i-2].list,buckets[i-1].list);;
        buckets[i-2].rank=buckets[i-2].list.getCount();
        buckets[i-1].list=new LinkedList();
        buckets[i-1].rank=0;
    }
    public LinkedList sort(LinkedList linkedList) {
        bucketsinit();
        int i=0;
        while (linkedList.hasNext()){
            buckets[i].list.add(linkedList.getValue());
            buckets[i].rank++;
            linkedList.next();
            i++;
            while (i>1&&(buckets[i-2].rank==buckets[i-1].rank)){
                bucketsmerger(i);
                i--;
            }
        }
        while (i>1){
            bucketsmerger(i);
            i--;
        }
        return  buckets[0].list;
    }
}
