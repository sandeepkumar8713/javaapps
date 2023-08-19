import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Iterator;

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

    public static Comparator<Student> getComparator(){
        Comparator<Student> comp = new Comparator<Student>(){
            @Override
            public int compare(Student s1, Student s2){
                return s2.marks - s1.marks;
            }
        };
        return comp;
    }

    int getId(){
        return this.id;
    }

    String getName(){
        return this.name;
    }
}

class StudentMarks{

    Map<Integer,Student> studentMap;
    PriorityQueue<Student> marksList;

    StudentMarks(){
        this.studentMap = new HashMap<Integer,Student>();
        this.marksList = new PriorityQueue<Student>(Student.getComparator());
    }

    void insert(Student student){
        this.studentMap.put(student.getId(), student);
        this.marksList.add(student);
    }

    Student find_by_id(int id){
        return this.studentMap.get(id);
    }

    List<Student> find_topper(int topK){
        List<Student> res = new ArrayList<Student>();
        Iterator<Student> it = this.marksList.iterator();
        int i = 0;
        while (it.hasNext() && i < topK){
            res.add(it.next());
            i++;
        }
        return res;
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