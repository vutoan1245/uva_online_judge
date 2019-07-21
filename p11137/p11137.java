import java.util.ArrayList;
import java.util.Scanner;

class p11137 {
    static final int MAX = 10000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Calculate all possible answer
        int coinNum = 21;
        int coinList[] = new int[coinNum];
        for (int i = 1; i <= coinNum; i++) {
            coinList[i - 1] = i * i * i;
        }

        long dp[] = new long[MAX + 1];
        dp[0] = 1;
        for (int i = 0; i < coinNum; i++) {
            for (int j = coinList[i]; j <= MAX; j++) {
                dp[j] += dp[j - coinList[i]];
            }
        }

        // Get input
        while (in.hasNext()) {
            System.out.println(dp[in.nextInt()]);
        }

        in.close();
    }
}