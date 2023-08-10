import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/maximum-average-subarray-i/
// Question : The problem is to find maximum sum sub-array divisible by k and then return (sum / k).
// Given n boxes containing some chocolates arranged in a row. There are k number of students.
// The problem is to distribute maximum number of chocolates equally among k students by selecting
// a consecutive sequence of boxes from the given lot. Consider the boxes are arranged in a row with
// numbers from 1 to n from left to right. We have to select a group of boxes which are in consecutive
// order that could provide maximum number of chocolates equally to all the k students. An array arr[]
// is given representing the row arrangement of the boxes and arr[i]
// represents number of chocolates in that box at position 'i'.

class ChocolateDistribution {

    static int maxChocolate(int[] arr, int k){
        int n = arr.length;
        int[] cummSum = new int[n];
        cummSum[0] = arr[0];
        for (int i=1; i < n; i++){
            cummSum[i] = cummSum[i-1] + arr[i];
        }

        Map<Integer, Integer> remMap = new HashMap<Integer, Integer>();
        int rem = 0;
        int result = 0;
        for (int i=0; i < n; i++){
            rem = cummSum[i] % k;

            if (rem == 0){
                result = Math.max(result, cummSum[i]);
            }
            else{
                if (remMap.containsKey(rem)){
                    int index = remMap.get(rem);
                    result = Math.max(result, cummSum[i] - cummSum[index]);
                } else{
                    remMap.put(rem, i);
                }
            }
        }

        // Result should chocolate count to each student
        return result / k;
    }

    public static void main(String[] args){
        int[] arr = {2, 7, 6, 1, 4, 5};
        int k = 3;
        System.out.println(maxChocolate(arr, k));
    } 
}
