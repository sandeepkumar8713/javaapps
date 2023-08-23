import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Design a service for find student marks and find topers.

class Student{
    int id;
    String name;
    int marks;

    public Student(int id, String name, int marks){
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    int getId(){
        return this.id;
    }

    String getName(){
        return this.name;
    }

    int getMarks(){
        return this.marks;
    }
}

class Node {
    Student student;
    Node left;
    Node right;

    public Node(Student student){
        this.student = student;
    }

    Student getStudent(){
        return this.student;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

class StudentMarks{

    Map<Integer,Student> studentMap;
    Node root;

    StudentMarks(){
        this.studentMap = new HashMap<Integer,Student>();
        this.root = null;
    }

    Node insertUtils(Node root, Student student){
        if (root == null){
            Node newNode = new Node(student);
            return newNode;
        }

        if (student.getMarks() < root.student.getMarks()){
            root.setLeft(insertUtils(root.getLeft(), student));
        }
        else{
            root.setRight(insertUtils(root.getRight(), student));
        }

        return root;
    }

    void insert(Student student){
        this.studentMap.put(student.getId(), student);
        this.root = insertUtils(this.root, student);
    }

    Student find_by_id(int id){
        return this.studentMap.get(id);
    }
    
    void find_topper_utils(Node root, int[] topK,List<Student> toppers){
        if (root == null || topK[0] < 0){
            return;
        }

        find_topper_utils(root.getRight(), topK, toppers);

        topK[0] -= 1;
        if (topK[0] >= 0){
            toppers.add(root.getStudent());
        }

        find_topper_utils(root.getLeft(), topK, toppers);
    }

    List<Student> find_topper(int topK){
        List<Student> toppers = new ArrayList<Student>();
        int[] k = {topK};
        find_topper_utils(root, k, toppers);
        return toppers;
    }

    public static void main(String[] args){
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(1, "Arun", 78));
        students.add(new Student(2, "Geeta", 86));
        students.add(new Student(3, "Shilphi", 65));
        students.add(new Student(4, "Monu", 12));

        StudentMarks studentMarks = new StudentMarks();
        
        for (Student student : students){
            studentMarks.insert(student);
        }

        Student found = studentMarks.find_by_id(3);
        if (found != null){
            System.out.println(found.getName());
        }

        System.out.println("");
        List<Student> toppers = studentMarks.find_topper(1);
        for (Student topper : toppers){
            System.out.println(topper.getName());
        }

        studentMarks.insert(new Student(5, "Sandeep", 95));

        System.out.println("");
        toppers = studentMarks.find_topper(1);
        for (Student topper : toppers){
            System.out.println(topper.getName());
        }

        System.out.println("");
        toppers = studentMarks.find_topper(3);
        for (Student topper : toppers){
            System.out.println(topper.getName());
        }

        System.out.println("");
    }
}