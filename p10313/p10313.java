import java.util.Scanner;

class p10313 {
    static final int MAX = 1000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        long dp[][] = new long[MAX + 1][MAX + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= MAX; i++) {
            for (int j = i; j <= MAX; j++) {
                for (int k = 1; k <= j; k++)
                    dp[j][k] += dp[j - i][k - 1];

            }
        }

        while (in.hasNext()) {
            String strArr[] = in.nextLine().split(" ");

            int n = Integer.parseInt(strArr[0]);
            int l1 = 0, l2 = 0;
            if (strArr.length == 1) {
                l1 = 0;
                l2 = n;
            } else if (strArr.length == 2) {
                l1 = 0;
                l2 = Integer.parseInt(strArr[1]);
            } else if (strArr.length == 3) {
                l1 = Integer.parseInt(strArr[1]);
                l2 = Integer.parseInt(strArr[2]);
            }

            long result = 0;
            for (int i = l1; i <= l2; i++) {
                result += dp[n][i];
            }

            System.out.println(result);
        }

        in.close();
    }
}