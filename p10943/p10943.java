import java.util.*;

class p10943 {
    static final int MAX = 101;
    static int dp[][] = new int[MAX][MAX];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int n = in.nextInt();
            int k = in.nextInt();

            if (n == 0 && k == 0) {
                break;
            }

            System.out.println(find(n, k));

        }

        in.close();
    }

    public static int find(int n, int k) {
        if (k <= 1) {
            return 1;
        }

        if (dp[n][k] != 0) {
            return dp[n][k];
        }

        int result = 0;
        for (int i = 0; i <= n; i++) {
            result += find(n - i, k - 1);
            result %= 1000000;
        }

        return dp[n][k] = result;
    }
}