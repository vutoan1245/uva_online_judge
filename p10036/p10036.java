import java.util.*;

public class p10036 {
    static int n, k;
    static int[] numList;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            n = in.nextInt();
            k = in.nextInt();

            numList = new int[n];
            for (int j = 0; j < n; j++) {
                numList[j] = in.nextInt();
            }

            dp = new int[n][105];
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[j], -1);
            }

            int result = solve(0, 0);

            System.out.println(result == 0 ? "Divisible" : "Not divisible");
        }

        in.close();
    }

    static int mod(int value, int k) {
        if (value < 0)
            value = value + (-value / k + 1) * k;
        return value % k;
    }

    static int solve(int index, int value) {

        int mod = mod(value, k);

        if (index == numList.length) {
            if (mod == 0)
                return 0;
            return 1;
        }

        if (dp[index][mod] != -1) {
            return dp[index][mod];
        }

        int result = Math.min(solve(index + 1, value + numList[index]), solve(index + 1, value - numList[index]));

        return dp[index][mod] = result;

    }

}