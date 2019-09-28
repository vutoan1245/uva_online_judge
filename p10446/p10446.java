import java.math.BigInteger;
import java.util.*;

class p10446 {
    static final int MAX = 70;
    static BigInteger dp[][];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        dp = new BigInteger[MAX][MAX];

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                dp[i][j] = new BigInteger("-1");
            }
        }

        int caseCount = 1;
        while (in.hasNext()) {
            int n = in.nextInt();
            int back = in.nextInt();

            if (n > 60) {
                break;
            }

            BigInteger result = trib(n, back);
            System.out.printf("Case %d: %s\n", caseCount++, result);
        }

        in.close();
    }

    static BigInteger trib(int n, int back) {

        if (n <= 1) {
            return BigInteger.ONE;
        }

        if (dp[n][back].compareTo(new BigInteger("-1")) != 0) {
            return dp[n][back];
        }

        BigInteger count = BigInteger.ONE;
        for (int i = 1; i <= back; i++) {
            count = count.add(trib(n - i, back));
        }

        return dp[n][back] = count;
    }
}