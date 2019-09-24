import java.util.*;

class p10465 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int m = in.nextInt();
            int n = in.nextInt();
            int t = in.nextInt();

            if (n < m) {
                int temp = n;
                n = m;
                m = temp;
            }

            int dp[] = new int[10000];
            dp[m] = 1;
            dp[n] = 1;

            for (int i = m; i <= t; i++) {
                if (dp[i] != 0) {
                    if (i + m <= t)
                        dp[i + m] = Math.max(dp[i + m], dp[i] + 1);
                    if (i + n <= t)
                        dp[i + n] = Math.max(dp[i + n], dp[i] + 1);
                }
            }

            int index = t;
            while (index > 0 && dp[index] == 0) {
                index--;
            }

            if (index < t) {
                System.out.println(dp[index] + " " + (t - index));
            } else {
                System.out.println(dp[index]);
            }

        }

        in.close();
    }

}