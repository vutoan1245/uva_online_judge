import java.util.Arrays;
import java.util.Scanner;

class p10667 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while (caseNum-- > 0) {
            int size = in.nextInt();
            int blockNum = in.nextInt();
            int NEG_INF = -(size * size);

            int matrix[][] = new int[size][size];
            for (int i = 0; i < size; i++) {
                Arrays.fill(matrix[i], 1);
            }
            for (int i = 0; i < blockNum; i++) {
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();

                for (int j = x1 - 1; j < x2; j++) {
                    for (int k = y1 - 1; k < y2; k++) {
                        matrix[j][k] = NEG_INF;
                    }
                }
            }

            // Calculate output
            int dp[][] = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    dp[i][j] = matrix[i][j];

                    if (i > 0)
                        dp[i][j] += dp[i - 1][j];
                    if (j > 0)
                        dp[i][j] += dp[i][j - 1];
                    if (i > 0 && j > 0)
                        dp[i][j] -= dp[i - 1][j - 1];
                }
            }

            int result = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    for (int k = i; k < size; k++) {
                        for (int g = j; g < size; g++) {
                            int area = dp[k][g];
                            if (i > 0)
                                area -= dp[i - 1][g];
                            if (j > 0)
                                area -= dp[k][j - 1];
                            if (i > 0 && j > 0)
                                area += dp[i - 1][j - 1];

                            result = Math.max(result, area);
                        }
                    }
                }
            }

            System.out.println(result);
        }

        in.close();
    }

    static void printTable(int matrix[][]) {
        for (int arr[] : matrix) {
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}