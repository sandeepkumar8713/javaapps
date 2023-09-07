import java.util.HashMap;
import java.util.Map;

// You are given a Double Link List with one pointer of each node pointing
// to the next node just like in a single link list. The second pointer however CAN point
// to any node in the list and not just the previous node.
// Now write a program in O(n) time to duplicate(clone) this list.
// Make a map of old:new nodes

class Node{
    int data;
    Node next;
    Node random;

    public Node(int data){
        this.data = data;
        this.next = null;
        this.random = null;
    }
}

class LinkedList implements Cloneable{
    Node root;

    public LinkedList(){
        this.root = null;
    }

    public LinkedList(Node root){
        this.root = root;
    }

    public void push(int data){
        Node newNode = new Node(data);
        if (this.root == null){
            this.root = newNode;
        } else{
            Node temp = this.root;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
        
    }

    public void printList(){
        Node temp = this.root;
        while (temp != null){
            System.out.printf("%d ", temp.data);
            temp = temp.next;
        }
        System.out.println();
    }

    public void printListRandom(){
        Node temp = this.root;
        while (temp != null){
            System.out.printf("%d ", temp.data);
            temp = temp.random;
        }
        System.out.println();
    }

    @Override
    public Object clone(){
        // Old vs New node
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node temp = this.root;
        while (temp != null){
            Node newNode = new Node(temp.data);
            map.put(temp, newNode);
            temp = temp.next;
        }

        Node old = this.root;
        while (old != null){
            Node newNode = map.get(old);
            newNode.next = map.get(old.next);
            newNode.random = map.get(old.random);
            old = old.next;
        }
        
        return new LinkedList(map.get(this.root));
    }
}

class Solution_3 {
    public static void main(String[] args){
        LinkedList myList = new LinkedList();
        int[] nums = {5, 4, 3, 2, 1}; 
        for (int item : nums){
            myList.push(item);
        }

        myList.root.random = myList.root.next.next;
        myList.root.next.random = myList.root.next.next.next;
        myList.root.next.next.random = myList.root.next.next.next.next;
        myList.root.next.next.next.random = myList.root.next.next.next.next.next;
        myList.root.next.next.next.next.random = myList.root.next;

        myList.printList();
        myList.printListRandom();
        
        LinkedList myList_2 = (LinkedList)myList.clone();
        myList_2.printList();
        myList_2.printListRandom();

    }
}
