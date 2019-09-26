import java.util.*;

class p10337 {
    static final int INF = 100000000;
    static int distance, wind[][];
    static int dp[][];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            distance = in.nextInt() / 100;

            wind = new int[10][distance];
            for (int i = 9; i >= 0; i--) {
                for (int j = 0; j < distance; j++) {
                    wind[i][j] = in.nextInt();
                }
            }

            dp = new int[10][distance + 1];
            for (int i = 0; i < 10; i++) {
                Arrays.fill(dp[i], -1);
            }

            System.out.println(solve(0, 0));
            System.out.println();
        }

        in.close();
    }

    static int solve(int alt, int dist) {
        if (alt < 0 || alt > 9) {
            return INF;
        }

        if (dist == distance) {
            if (alt == 0) {
                return 0;
            } else {
                return INF;
            }
        }

        if (dp[alt][dist] != -1) {
            return dp[alt][dist];
        }

        int ans = INF;

        int w = wind[alt][dist] * -1;
        ans = Math.min(ans, 60 + w + solve(alt + 1, dist + 1));
        ans = Math.min(ans, 30 + w + solve(alt, dist + 1));
        ans = Math.min(ans, 20 + w + solve(alt - 1, dist + 1));

        return dp[alt][dist] = ans;
    }
}