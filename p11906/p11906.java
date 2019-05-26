import java.util.Scanner;

class p11906{
    static int row, col, jumpFirst, jumpSecond;
    static int grid[][];
    static boolean visited[][];
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();
        for(int i = 0; i < cases; i++){
            // Get input for each case
            row = in.nextInt();
            col = in.nextInt();
            jumpFirst = in.nextInt();
            jumpSecond = in.nextInt();

            grid = new int[row][col];
            visited = new boolean[row][col];
            int waterSquares = in.nextInt();
            for(int j = 0; j < waterSquares; j++){
                grid[in.nextInt()][in.nextInt()] = -1;
            }

            // Calculate output
            grid[0][0] = findAllPaths(0, 0);
            int even = 0, odd = 0;
            for(int j = 0; j < row; j++){
                for(int k = 0; k < col; k++){
                    if(grid[j][k] != -1 && visited[j][k]){
                        if(grid[j][k] % 2 == 0){
                            even++;
                        } else {
                            odd++;
                        }
                    }
                }
            }
            // Print output
            System.out.printf("Case %d: %d %d\n", i+1, even, odd);
        }

        in.close();
    }

    public static int findAllPaths(int rowPos, int colPos){
        if(visited[rowPos][colPos]) return grid[rowPos][colPos];
        visited[rowPos][colPos] = true;

        int result = 0;
        boolean currentVisited[][] = new boolean[row][col];

        // All possible jump
        if(checkInput(rowPos + jumpFirst, colPos + jumpSecond, currentVisited)) result++;
        if(checkInput(rowPos + jumpFirst, colPos - jumpSecond, currentVisited)) result++;
        if(checkInput(rowPos - jumpFirst, colPos + jumpSecond, currentVisited)) result++;
        if(checkInput(rowPos - jumpFirst, colPos - jumpSecond, currentVisited)) result++;
        if(checkInput(rowPos + jumpSecond, colPos + jumpFirst, currentVisited)) result++;
        if(checkInput(rowPos + jumpSecond, colPos - jumpFirst, currentVisited)) result++;
        if(checkInput(rowPos - jumpSecond, colPos + jumpFirst, currentVisited)) result++;
        if(checkInput(rowPos - jumpSecond, colPos - jumpFirst, currentVisited)) result++;

        return result;
    }

    public static boolean checkInput(int rowPos, int colPos, boolean currentVisited[][]){
        boolean result = (rowPos >= 0 && rowPos < row && colPos >= 0 && colPos < col && grid[rowPos][colPos] != -1);
        if(result){
            if(currentVisited[rowPos][colPos]) return false;
            currentVisited[rowPos][colPos] = true;

            if(visited[rowPos][colPos] == false)
                grid[rowPos][colPos] = findAllPaths(rowPos, colPos);
        }

        return result;
    }
}