import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/rotting-oranges/
// Question : Given a matrix of dimension m*n where each cell in the matrix can have
// values 0, 1 or 2 which has the following meaning:
// 0: Empty cell
// 1: Cells have fresh oranges
// 2: Cells have rotten oranges
// So we have to determine what is the minimum time required so that all the oranges
// become rotten. A rotten orange at index [i,j] can rot other fresh orange at indexes
// [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right). If
// it is impossible to rot every orange then simply return -1.

class RottenOranges{

    static int[][] possibleDirection = {{-1, 0}, {1,0}, {0, -1}, {0, 1}};

    public static int findTime(int[][] matrix){

        List<int[]> queue = new ArrayList<int[]>();
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i=0; i < m; i++){
            for (int j=0; j < n; j++){
                if (matrix[i][j] == 2){
                    int[] pos = {i, j, 0};
                    queue.add(pos);
                }
            }
        }

        int maxLength = -1;
        while (queue.size() != 0){
            int[] currentPos = queue.remove(0);
            maxLength = Math.max(maxLength, currentPos[2]);

            for (int[] direction : possibleDirection){
                int nextI = currentPos[0] + direction[0];
                int nextJ = currentPos[1] + direction[1];

                if (0 <= nextI && nextI < m && 0 <= nextJ && nextJ < n){
                    if (matrix[nextI][nextJ] == 1){
                        matrix[nextI][nextJ] = 2;
                        int[] nextPos = {nextI, nextJ, currentPos[2] + 1};
                        queue.add(nextPos);
                    }
                }
            }
        }

        for (int i=0; i < m; i++){
            for (int j=0; j < n; j++){
                if (matrix[i][j] == 1){
                    return -1;
                }
            }
        }

        return maxLength;
    }

    static public void main(String[] args){

        int[][] matrix = {{2, 1, 0, 2, 1},
                          {1, 0, 1, 2, 1},
                          {1, 0, 0, 2, 1}};
        System.out.println(findTime(matrix));
    }
}
