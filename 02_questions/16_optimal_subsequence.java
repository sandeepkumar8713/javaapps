// https://leetcode.com/discuss/interview-question/3597465/Wayfair-or-SDE-2-(L2-4)-or-Bengaluru-or-OA
// An intern at HackerRank is assigned is finding the optimal middle subsequence.
// An optimal middle subsequence is the subsequence chosen[] of length 3 chosen from an array arr, such
// that chosen[0] < chosen[1] > chosen[2] and that the sum of its elements is the minimum possible.
// Given an array, return the sum of the values of the optimal middle subsequence. 
// If there is none, return -1.
// Note: A subsequence of an array is obtained by deleting some (possibly 0) elements from the array
// without changing the order of the remaining elements. For example, [1, 3] is a subsequence of [1,
// 2, 3, 4] while [4, 2] is not.

class OptimalSubsequence {

    static int findOptimalSum(int[] arr){
        int n = arr.length;
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        int minResult = 1000000;

        leftMin[0] = arr[0];
        rightMin[n-1] = arr[n-1];
        
        int i = 1;
        while (i < n){
            leftMin[i] = Math.min(leftMin[i-1], arr[i]);
            i++;
        }

        i = n-2;
        while (i >= 0){
            rightMin[i] = Math.min(rightMin[i+1], arr[i]);
            i--;
        }

        i = 1;
        while (i < n - 1){
            if (leftMin[i-1] < arr[i] && arr[i] > rightMin[i+1]){
                minResult = Math.min(minResult, leftMin[i-1] + arr[i] + rightMin[i+1]);
            }
            i++;
        }
        return minResult;
    }   

    public static void main(String[] args){
        int[] arr = {3, 4, 5, 1, 2, 3, 1};
        System.out.println(findOptimalSum(arr));
    }
    
}
