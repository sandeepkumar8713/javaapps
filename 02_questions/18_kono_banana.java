// https://leetcode.com/problems/koko-eating-bananas/
// Question : Koko loves to eat bananas. There are n piles of bananas, the ith pile has
// piles[i] bananas. The guards have gone and will come back in h hours. Koko can decide
// her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and
// eats k bananas from that pile. If the pile has less than k bananas, she eats all of them
// instead and will not eat any more bananas during this hour. Koko likes to eat slowly but
// still wants to finish eating all the bananas before the guards return. Return the minimum
// integer k such that she can eat all the bananas within h hours.


class KokoBanana{

    static int findHoursTaken(int speed, int[] piles){
        int ans = 0;
        for (int item : piles){
            ans += Math.ceil(item/(speed + 0.0));
        }
        return ans;
    }

    static int findOptimalSpeed(int[] piles, int hrsLimit){
        int left = 1;
        int right = -Integer.MAX_VALUE;

        for (int item : piles){
            right = Math.max(right, item);
        }

        int minK = 0;
        int mid, hoursTaken;
        while (left < right){
            mid = left + (right - left) / 2;
            hoursTaken = findHoursTaken(mid, piles);
            if (hoursTaken <= hrsLimit){
                minK = mid;
                right = mid - 1;
            } else{
                left = mid + 1;
            }

        }

        return minK;

    }

    public static void main(String[] args){
        int[] piles = {3,6,7,11};
        int hrsLimit = 8;
        System.out.println(findOptimalSpeed(piles,hrsLimit));
    }
}