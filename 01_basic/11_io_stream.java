import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

class FileCopy{

    public static void copyFile(String srcFile, String destFile) throws FileNotFoundException, IOException{

        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        int i;
        while ( (i=fis.read()) != -1){
            fos.write(i);
        }
        fis.close();
        fos.close();

        System.out.println("Data written to " + destFile);
    }

    public static void main(String[] args){
        try{
            FileCopy.copyFile("sample_input.text", "sample_output.text");
        } catch(Exception e){
            e.printStackTrace();
        }
        
    } 
}

