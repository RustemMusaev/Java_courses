public class Main {
    public static void main(String[] args) {
        LinkedList linkedList=LinkedList.createLinkedLidt(19);
        linkedList.printList();

        LinkedListMergerSort list=new LinkedListMergerSort();
        linkedList=list.sort(linkedList);
        linkedList.printList();
    }
}
