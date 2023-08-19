// https://leetcode.com/problems/shopping-offers/
// Question : In LeetCode Store, there are n items to sell. Each item has a price. However, there are
// some special offers, and a special offer consists of one or more different kinds of items with a sale price.
// You are given an integer array price where price[i] is the price of the ith item, and an integer array
// needs where needs[i] is the number of pieces of the ith item you want to buy. You are also given an
// array special where special[i] is of size n + 1 where special[i][j] is the number of pieces of the
// jth item in the ith offer and special[i][n] (i.e., the last integer in the array) is the price of the ith offer.
// Return the lowest price you have to pay for exactly certain items as given, where you could make optimal
// use of the special offers. You are not allowed to buy more items than you want, even if that would lower
// the overall price. You could use any of the special offers as many times as you want.
// 
// Example : Input: price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
// Output: 14
// Explanation: There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
// In special offer 1, you can pay $5 for 3A and 0B
// In special offer 2, you can pay $10 for 1A and 2B.
// You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
// Complexity : O(k^n) count of all possible combinations of needs.
// where k is highest need and n is number of needs

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ShoppingOffer{

    static boolean canBuy(List<Integer> special, List<Integer> needs, List<Integer> remainingNeeds){
        for (int i = 0; i < needs.size(); i++){
            int remaining = needs.get(i) - special.get(i);
            if (remaining < 0){
                return false;
            }
            remainingNeeds.add(i, remaining);
        }
        return true;
    }


    static int findLowestPrice(Map<List<Integer>,Integer> dp, List<Integer> price, List<List<Integer>> specials, List<Integer> needs){
        int total_needs = 0;
        for(int i = 0; i< needs.size(); i++){
            total_needs+=needs.get(i);
        }

        if (total_needs == 0){
            return 0;
        }

        if (dp.containsKey(needs)){
            return dp.get(needs);
        }

        int actualPrice = 0;
        for (int i =0;i < price.size(); i++){
            actualPrice += price.get(i) * needs.get(i);
        }

        int minPrice = Integer.MAX_VALUE;
        
        for (List<Integer> special : specials){
            List<Integer> remainingNeeds = new ArrayList<Integer>();

            if (canBuy(special, needs, remainingNeeds)){
                int specialPrice = findLowestPrice(dp, price, specials, remainingNeeds) + special.get(special.size() - 1);
                minPrice = Math.min(minPrice, specialPrice);
            }

        }

        minPrice = Math.min(minPrice, actualPrice);
        dp.put(needs, minPrice);
        return minPrice;
    }

    public static void main(String[] args){
        List<Integer> price = Arrays.asList(2, 5);

        List<List<Integer>> specials = new ArrayList<List<Integer>>();
        specials.add(Arrays.asList(3, 0, 5));
        specials.add(Arrays.asList(1, 2, 10));

        List<Integer> needs = Arrays.asList(3, 2);

        Map<List<Integer>,Integer> dp = new HashMap<List<Integer>,Integer>();
        System.out.println(findLowestPrice(dp, price, specials, needs));

        // price = [2,2]
        // special = [[1,1,1],[1,1,2],[1,1,3],[1,1,4],[1,1,5],[1,1,6],[1,1,7],[1,1,8],[1,1,9],[1,1,10],[1,1,11],[1,1,12],[1,1,13],[1,1,14],[1,1,15]]
        // needs = [10,10]
    }
}
