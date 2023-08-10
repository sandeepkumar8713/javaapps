// https://leetcode.com/problems/single-number-ii/description/
// Question : Given an integer array nums where every element appears three times
// except for one, which appears exactly once. Find the single element and return it.
// You must implement a solution with a linear runtime complexity and use only
// constant extra space.

class SingleNumber {

    static int findSingle(int[] nums){
        int limit = 31;
        int i = 0;
        int count = 0;
        int single;
        int result = 0;
        while (i <= limit){
            count = 0;
            for (int item : nums){
                count += (item >> i) & 1;
            }
            single = count % 3;
            result |= single << i;
            i++;
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = {2, 2, 3, 2};
        System.out.println(findSingle(nums));

        int[] nums_2 = {0, 1, 0, 1, 0, 1, 99};
        System.out.println(findSingle(nums_2));
    }   
    
}
