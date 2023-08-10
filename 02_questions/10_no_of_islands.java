// https://leetcode.com/problems/number-of-islands/
// Question : Given a boolean 2D matrix, find the number of islands. A group of connected 1s forms an island.

class IslandsCount {
    
    static int[][] possibleDirection = {{-1, 0}, {1,0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    static void dfs(int[][] mat, int i,int j, int m, int n, int[][] visited){
        visited[i][j] = 1;
        for (int[] direction : possibleDirection){
            int nextI = i + direction[0];
            int nextJ = j + direction[1];

            if (0 <= nextI && nextI < m && 0 <= nextJ && nextJ < n && visited[nextI][nextJ] == 0){
                if (mat[nextI][nextJ] == 1){
                    dfs(mat, nextI,nextJ, m, n, visited);
                }   
            }
        }
    }

    static int getCount(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        int[][] visited = new int[m][n];

        int count = 0;
        for (int i=0; i < m; i++){
            for (int j=0; j < n; j++){
                if (mat[i][j] == 1 && visited[i][j] == 0){
                    System.out.printf("%d %d\n", i, j);
                    count++;
                    dfs(mat, i, j, m, n, visited);
                }
            }
        }

        return count;
    }


    public static void main(String[] args){
        int[][] mat = { {1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 1},
                        {1, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}};
        System.out.println(getCount(mat));
    }
                    
}
