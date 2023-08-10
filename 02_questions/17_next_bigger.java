import java.util.ArrayList;
import java.util.List;

// Similar : https://leetcode.com/problems/next-greater-element-i/
// Question : Given an array, print the Next Greater Element (NGE) for every element. The Next greater Element for an
// element x is the first greater element on the right side of x in array. Elements for which no greater element exist,
// consider next greater element as -1.

class NextBigger {

    static void findNextGreater(int[] arr){
        List<Integer> stack = new ArrayList<Integer>();
        stack.add(arr[0]);
        int n = arr.length;
        int i = 1;
        while (i < n){
            while (stack.size() > 0 && stack.get(stack.size() - 1) < arr[i]){
                System.out.printf("%d --> %d\n", stack.get(stack.size() - 1), arr[i]);
                stack.remove(stack.size() - 1);
            }
            stack.add(arr[i]);
            i++;
        }

        while (stack.size() > 0){
            System.out.printf("%d --> %d\n", stack.get(stack.size() - 1), -1);
            stack.remove(stack.size() - 1);
        }
    }

    public static void main(String[] args){
        int[] arr = {11, 13, 21, 3};
        findNextGreater(arr);
    }
    
}   
