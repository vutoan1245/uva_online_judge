import java.util.*;

class p00196 {
    static int row, col;
    static String matrix[][];
    static int dp[][];
    static boolean visited[][];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while (caseNum-- > 0) {

            col = in.nextInt();
            row = in.nextInt();
            matrix = new String[row][col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = in.next();
                }
            }

            dp = new int[row][col];
            visited = new boolean[row][col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (!visited[i][j]) {
                        find(i, j);
                    }
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    System.out.print(dp[i][j]);
                    if (j != col - 1) {
                        System.out.print(" ");
                    } else {
                        System.out.println();
                    }
                }
            }
        }

        in.close();
    }

    static int find(int r, int c) {
        if (r >= row || c >= col)
            return 0;

        if (visited[r][c]) {
            return dp[r][c];
        }

        if (matrix[r][c].charAt(0) != '=') {
            visited[r][c] = true;
            return dp[r][c] = Integer.parseInt(matrix[r][c]);
        }

        String strArr[] = matrix[r][c].substring(1).split("\\+");

        int ans = 0;
        for (int i = 0; i < strArr.length; i++) {

            String temp = "";
            int j = 0;
            for (j = 0; j < strArr[i].length(); j++) {
                if (strArr[i].charAt(j) >= '0' && strArr[i].charAt(j) <= '9')
                    break;
                temp += strArr[i].charAt(j);
            }
            int factor = 1;
            int newC = 0;
            for (int g = 0; g < j; g++) {
                newC += factor * (temp.charAt(j - g - 1) - 'A' + 1);
                factor *= 26;
            }
            newC--;

            int newR = Integer.parseInt(strArr[i].substring(j)) - 1;

            ans += find(newR, newC);
        }

        visited[r][c] = true;
        return dp[r][c] = ans;
    }
}