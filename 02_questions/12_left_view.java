// Question : Given a Binary Tree, print left view of it. Left view of a Binary Tree is set of
// nodes visible when tree is visited from left side.
// Pre-order

class Node{
    private int value;
    private Node left;
    private Node right;

    Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    void setLeft(Node left){
        this.left = left;
    }

    void setRight(Node right){
        this.right = right;
    }

    Node getRight(){
        return this.right;
    }

    Node getLeft(){
        return this.left;
    }

    int getValue(){
        return value;
    }
}

class LeftView {

    static void printLeftViewUtil(Node root, int[] maxLevel, int level){
        if (root == null){
            return;
        }
        if (maxLevel[0] < level){
            System.out.println(root.getValue());
            maxLevel[0] = level;
        }
        printLeftViewUtil(root.getLeft(), maxLevel, level + 1);
        printLeftViewUtil(root.getRight(), maxLevel, level + 1);
    }
    
    static void printLeftView(Node root){
        int[] maxLevel = {-1};
        printLeftViewUtil(root, maxLevel, 0);
    }


    public static void main(String args[]){
        Node root = new Node(12);
        root.setLeft(new Node(10));
        root.setRight(new Node(20));

        root.getRight().setLeft(new Node(25));
        root.getRight().setRight(new Node(40));

        printLeftView(root);
    }
}
