class Node{
    private int value;
    private Node next;

    Node(int value){
        this.value = value;
        this.next = null;
    }

    int getValue(){
        return this.value;
    }

    void setNext(Node next){
        this.next = next;
    }

    Node getNext(){
        return this.next;
    }
}

class MergeList {

    static Node makeList(int[] inputArr){
        Node head = new Node(inputArr[0]);
        Node temp = head;
        int i = 1;
        while (i < inputArr.length){
            Node newNode = new Node(inputArr[i]);
            temp.setNext(newNode);
            temp = newNode;
            i++;
        }
        return head;
    }

    static Node merge(Node head_1, Node head_2){
        if (head_1 == null){
            return head_2;
        }
        if (head_2 == null){
            return head_1;
        }

        Node result = null;
        if (head_1.getValue() <= head_2.getValue()){
            result = head_1;
            result.setNext(merge(head_1.getNext(), head_2));
        } else{
            result = head_2;
            result.setNext(merge(head_1, head_2.getNext()));
        }
        return result;
    }
    

    static void printValues(Node head){
        Node temp = head;
        while (temp != null){
            System.out.printf("%d ", temp.getValue());
            temp = temp.getNext();
        }
        System.out.println();
    }
    
    public static void main(String[] args){
        int[] inputArr_1 = {10, 20, 30, 40, 50};
        Node head_1 = makeList(inputArr_1);

        int[] inputArr_2 = {5, 15, 18, 35, 60};
        Node head_2 = makeList(inputArr_2);

        Node head_3 = merge(head_1, head_2);
        printValues(head_3);
    }
    
}
