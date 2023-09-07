import java.util.PriorityQueue;

// CTCI : Q17_14_Smallest_K
// http://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
// https://leetcode.com/problems/kth-largest-element-in-an-array
// Write an efficient program for printing k largest elements in an array.
// Elements in array can be in any order. (Here answer will be whole min heap of size k.)
// Maintain a min heap which saves top k elements at any given point of time.

// stream[] = {10, 20, 11, 70, 50, 40, 100, 5, ...}
// k = 3
// Output: {_,   _, 10, 11, 20, 40, 50,  50, ...}



class Solution_1{

    static void get_largest_k(int[] stream, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int i = 0;
        while (i < k-1){
            pq.add(stream[i]);
            System.out.println("_");
            i++;
        }
        pq.add(stream[i]);
        i++;
        System.out.println(pq.peek());
        while (i < stream.length){
            int item = stream[i];
            if (item > pq.peek()){
                pq.poll();
                pq.add(stream[i]);
            }
            System.out.println(pq.peek());
            i++;
            // System.out.println(pq.toString());
        }
   
    }

    public static void main(String[] args){
        int[] stream = {10, 20, 11, 70, 50, 40, 100, 5};
        int k = 3;
        get_largest_k(stream, k);
    }
}