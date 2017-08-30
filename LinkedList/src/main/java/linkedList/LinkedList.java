package linkedList;

public class LinkedList {
    private class Node {
        private int value;
        private Node next;
        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public int getValue() {
        return top.value;
    }

    public  int count;
    private Node top;
    private Node last;

    public Node current(){
        return top;
    }
    public LinkedList (){
        top=null;
        count=0;
        last=null;
    }
    public void add(int value){
        if (count==0) {
            top=new Node(value);
            count=1;
        } else  {
            Node temp;
            temp=top;
            top=new Node(value);
            top.next=temp;
            count++;
        }
    }
    public void addlast(int value){
        if (count==0) {
            top=new Node(value);
            count=1;
            last=top;

        } else  {
            last.next=new Node(value);
            last=last.next;
            count++;
        }
    }
    public int getCount(){
        return count;
    }
    public boolean hasNext() {
        if (top == null) {
            return false;
        } else {
            return true;
        }
    }
    public Node next() {
        top = top.next;
        return top;
    }
    public void printList(){
        Node temp=this.top;
        while (this.hasNext()) {
            System.out.print("\t"+this.getValue());
            this.next();
        }
        this.top=temp;
        System.out.println();
    }
    public static LinkedList createLinkedLidt(int size) {
        LinkedList linkedList=new LinkedList();
        int i=0;
        while (i<size) {
            linkedList.add((int)(Math.random()*1000));
            i++;
        }
        return linkedList;
    }
}