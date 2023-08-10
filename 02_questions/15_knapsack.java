// Question : Given weights and values of n items, put these items in a knapsack of capacity W to
// get the maximum total value in the knapsack.

class Knapsack {

    static int findMaxValue(int[] itemValues, int[] itemWeights,int weightLimit){
        int n = itemValues.length;
        int[][] dp = new int[n+1][weightLimit+1];

        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= weightLimit; j++){
                if (i == 0 || j == 0){
                    dp[i][j] = 0;
                    continue;
                }

                if (itemWeights[i-1] <= j){
                    dp[i][j] = Math.max(itemValues[i-1] + dp[i-1][j - itemWeights[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][weightLimit];
    }

    public static void main(String[] args){
        int[] itemValues = {60, 100, 120};
        int[] itemWeights = {10, 20, 30};
        int weightLimit = 50;
        System.out.println(findMaxValue(itemValues, itemWeights, weightLimit));
    }
    
    
}
