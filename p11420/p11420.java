import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class p11420 {

    static BigInteger[][][] dp;
    static int n;

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);

        while (true) {
            n = in.nextInt();
            int s = in.nextInt();

            if (n < 0) {
                break;
            }

            dp = new BigInteger[2][n + 1][s + 1];

            System.out.println(solve(1, 0, s).toString());
        }

        in.close();
    }

    static BigInteger solve(int top, int index, int remain) {
        if (remain < 0) {
            return BigInteger.ZERO;
        }

        if (index == n) {
            if (remain == 0) {
                return BigInteger.ONE;
            } else {
                return BigInteger.ZERO;
            }
        }

        if (dp[top][index][remain] != null) {
            return dp[top][index][remain];
        }

        BigInteger ans = BigInteger.ZERO;

        if (top == 1) {
            ans = ans.add(solve(1, index + 1, remain - 1));
            ans = ans.add(solve(0, index + 1, remain));
        } else {
            ans = ans.add(solve(1, index + 1, remain));
            ans = ans.add(solve(0, index + 1, remain));
        }

        return dp[top][index][remain] = ans;
    }
}