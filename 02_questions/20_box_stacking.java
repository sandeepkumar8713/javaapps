import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// https://www.geeksforgeeks.org/box-stacking-problem-dp-22/
// Question : You are given a set of n types of rectangular 3-D boxes, where the i^th box has
// height h(i), width w(i) and depth d(i) (all real numbers). You want to create a stack of
// boxes which is as tall as possible, but you can only stack a box on top of another box if
// the dimensions of the 2-D base of the lower box are each strictly larger than those of the
// 2-D base of the higher box. Of course, you can rotate a box so that any side functions as
// its base. It is also allowable to use multiple instances of the same type of box.

class Box{
    private int height;
    private int length;
    private int width;

    Box(int height, int length, int width){
        this.height = height;
        this.length = length;
        this.width = width;
    }

    int getArea(){
        return length * width;
    }

    public static Comparator<Box> getCompByArea(){
        Comparator<Box> comp = new Comparator<Box>(){
            @Override
            public int compare(Box s1, Box s2)
            {
                return s2.getArea() - s1.getArea();
            }        
        };
        return comp;
    }  

    public int getHeight(){
        return height;
    }

    public int getLength(){
        return length;
    }

    public int getWidth(){
        return width;
    }

}

class BoxStacking {

    List<Box> boxes;
    static int Dimension = 3;

    BoxStacking(int[][] inpMat){
        boxes = new ArrayList<Box>();
        int start;

        for (int[] arr : inpMat){
            start = 0;
            for (int j=0; j < Dimension; j++){
                boxes.add(new Box(arr[start], arr[(start + 1)% Dimension], arr[(start + 2) % Dimension]));
                start = (start + 1) % Dimension;
            }
        }
    }

    int findMaxHeight(){
        Collections.sort(this.boxes, Box.getCompByArea());
        int n = this.boxes.size();
        int[] MSH = new int[n];

        for (int i = 0; i < n; i++){
            MSH[i] = this.boxes.get(i).getHeight();
        }

        int maxHeight = 0;
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (this.boxes.get(j).getLength() > this.boxes.get(i).getLength() &&
                    this.boxes.get(j).getWidth() > this.boxes.get(i).getWidth()) {
                        if (MSH[j] + this.boxes.get(i).getHeight() > MSH[i]){
                            MSH[i] = MSH[j] + this.boxes.get(i).getHeight();
                        }
                        maxHeight = Math.max(maxHeight,  MSH[i]);
                }
            }
        }

        return maxHeight;
    }
    
    
    public static void main(String[] args){
    int[][] inpMat = {{4, 6, 7},
                     {1, 2, 3},
                     {4, 5, 6},
                    {10, 12, 32}};
    BoxStacking boxStacking = new BoxStacking(inpMat);
    System.out.println(boxStacking.findMaxHeight());
    }
}
