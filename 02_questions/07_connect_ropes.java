import java.util.PriorityQueue;

// https://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
// Question : There are given n ropes of different lengths, we need to connect these ropes
// into one rope. The cost to connect two ropes is equal to sum of their lengths. We need
// to connect the ropes with minimum cost.
// https://stackoverflow.com/questions/14165325/is-there-a-heap-in-java
// PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
// PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
//
// add --> to add element to the queue. O(log n)
// remove --> to get and remove the min/max. O(log n)
// peek --> to get, but not remove the min/max. O(1)

class ConnectRopes {

    public static int findMinCost(int[] inpArr){

        int minCost = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int item : inpArr){
            minHeap.add(item);
        }

        int first, second;
        while (minHeap.size() >= 2){
            first = minHeap.remove();
            second = minHeap.remove();
            minCost += first + second;
            minHeap.add(first + second);
        }

        return minCost;
    }

    public static void main(String args[]){
        int[] inpArr = new int[]{4, 3, 2, 6};
        System.out.println(findMinCost(inpArr));
    }
    
}
