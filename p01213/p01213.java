import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

class p01213 {
    static final int MAX_SUM = 1120, MAX_LEN = 14;
    static int dp[][] = new int[MAX_LEN + 1][MAX_SUM + 1];

    public static void main(String args[]) {

        findAll();

        Scanner in = new Scanner(System.in);

        while (true) {
            int n = in.nextInt();
            int k = in.nextInt();
            if (n == 0 && k == 0)
                break;

            System.out.println(dp[k][n]);
        }

        in.close();
    }

    static void findAll() {
        ArrayList<Integer> primeList = new ArrayList<Integer>();
        BitSet isPrime = new BitSet(MAX_SUM);

        for (int i = 2; i < MAX_SUM; i++) {
            isPrime.set(i, true);
        }

        for (int i = 2; i < MAX_SUM; i++) {
            if (isPrime.get(i)) {
                primeList.add(i);
                for (int j = i + i; j < MAX_SUM; j += i) {
                    isPrime.set(j, false);
                }
            }
        }

        int size = primeList.size();

        dp[0][0] = 1;

        for (int i = 0; i < size; i++) {
            int prime = primeList.get(i);
            for (int j = MAX_LEN; j > 0; j--) {
                for (int k = prime; k <= MAX_SUM; k++) {
                    dp[j][k] += dp[j - 1][k - prime];
                }
            }
        }
    }
}