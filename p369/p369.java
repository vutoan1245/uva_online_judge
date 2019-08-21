import java.util.Scanner;

class p369 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        long[][] dp = new long[101][101];
        dp[0][0] = 1;
        for (int i = 1; i <= 100; ++i) {
            dp[i][0] = 1;
            for (int j = 1; j <= i; ++j)
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        }

        for (long arr[] : dp) {
            for (long num : arr) {
                System.out.printf("%4d ", num);
            }
            System.out.println();
        }
        while (true) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0)
                break;

            System.out.printf("%d things taken %d at a time is %d exactly.\n", n, m, dp[n][m]);
        }

        in.close();
    }
}