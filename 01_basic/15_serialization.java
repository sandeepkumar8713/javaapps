import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


class Student implements Serializable{  
    int id;  
    String name;
    public Student(int id, String name) {  
        this.id = id;  
        this.name = name;  
    }
   }  

class serilalizationExample {
    public static void main(String args[]){ 
    try
        {    
            //Creating the object    
            Student s1 =new Student(211, "sandeep");    

            //Creating stream and writing the object    
            FileOutputStream fout=new FileOutputStream("f.txt");    
            ObjectOutputStream out=new ObjectOutputStream(fout);    
            
            out.writeObject(s1);  // It writes the specified object to the ObjectOutputStream. 
            out.flush();          // It flushes the current output stream.    
            out.close();          // It closes the current output stream. 
            System.out.println("success");    
        }
        catch(Exception e){
            System.out.println(e);
        }

    try
    {  
        //Creating stream to read the object  
        ObjectInputStream in=new ObjectInputStream(new FileInputStream("f.txt"));  
        Student s=(Student)in.readObject();  
        //printing the data of the serialized object  
        System.out.println(s.id+" "+s.name);  
        //closing the stream  
        in.close();  
    }
    catch(Exception e){
        System.out.println(e);}  
    }  
}
