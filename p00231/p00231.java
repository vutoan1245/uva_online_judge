import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class p00231 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int value, caseCount = 1;
        while ((value = in.nextInt()) != -1) {
            if (caseCount != 1)
                System.out.println();
            ArrayList<Integer> missleList = new ArrayList<Integer>();
            missleList.add(value);

            while ((value = in.nextInt()) != -1) {
                missleList.add(value);
            }
            Collections.reverse(missleList);

            int len = missleList.size();

            int dp[] = new int[len];
            Arrays.fill(dp, 1);

            int result = 0;
            for (int i = 1; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    if (missleList.get(i) >= missleList.get(j)) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                if (dp[i] > result)
                    result = dp[i];
            }

            System.out.printf("Test #%d:\n", caseCount++);
            System.out.printf("  maximum possible interceptions: %d\n", result);
        }

        in.close();
    }
}