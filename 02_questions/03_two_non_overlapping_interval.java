import java.util.Arrays;

// https://leetcode.com/problems/non-overlapping-intervals/
// Question : Given an array of intervals intervals where intervals[i] = [starti, endi],
// return the minimum number of intervals you need to remove to make the rest of the
// intervals non-overlapping.

class nonOverlapping {
    
    public static int eraseOverlapIntervals(int[][] intervals) {
        //Collection.sort(intervals, (o1, o2) -> o1[1] - o2[1]); For list of objects
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]); // For array of objects
        int earse_count = 0;
        int prev = 0;
        int i = 1;
        while (i < (intervals.length)){
            if (intervals[prev][1] > intervals[i][0]){
                earse_count++;
            }
            else{
                prev++;
            }
            i++;
        }
        return earse_count;
    }

    static public void main(String[] args){
        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println(eraseOverlapIntervals(intervals));

        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(eraseOverlapIntervals(intervals2));
    }

}
