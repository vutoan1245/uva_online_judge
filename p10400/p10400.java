import java.util.*;

class p10400 {
    static final int MAX = 32000;
    static int n, numList[], target;
    static int dp[][];
    static String parent[][];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            n = in.nextInt();

            numList = new int[n];
            for (int i = 0; i < n; i++) {
                numList[i] = in.nextInt();
            }

            target = in.nextInt();

            dp = new int[n][MAX * 2 + 1];
            parent = new String[n + 1][MAX * 2 + 1];

            for (int i = 0; i <= n; i++) {
                for (int j = 0; j < MAX * 2 + 1; j++) {
                    parent[i][j] = "";
                }
            }

            int isValid = solve(1, numList[0]);

            if (isValid == 1) {
                String result = numList[0] + parent[1][numList[0] + MAX] + "=" + target;
                System.out.println(result);

            } else {
                System.out.println("NO EXPRESSION");
            }
        }

        in.close();

    }

    static int solve(int index, int value) {
        if (Math.abs(value) > MAX)
            return -1;

        if (index == n) {
            return value == target ? 1 : -1;
        }

        if (dp[index][value + MAX] != 0) {
            return dp[index][value + MAX];
        }

        int num = numList[index];

        if (solve(index + 1, value + num) == 1) {
            parent[index][value + MAX] = "+" + num + parent[index + 1][value + num + MAX];
            return dp[index][value + MAX] = 1;
        }

        if (solve(index + 1, value - num) == 1) {
            parent[index][value + MAX] = "-" + num + parent[index + 1][value - num + MAX];
            return dp[index][value + MAX] = 1;

        }

        if (solve(index + 1, value * num) == 1) {
            parent[index][value + MAX] = "*" + num + parent[index + 1][value * num + MAX];
            return dp[index][value + MAX] = 1;

        }

        if (value % num == 0) {
            if (solve(index + 1, value / num) == 1) {
                parent[index][value + MAX] = "/" + num + parent[index + 1][value / num + MAX];
                return dp[index][value + MAX] = 1;
            }
        }

        return dp[index][value + MAX] = -1;
    }
}