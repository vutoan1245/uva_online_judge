import java.util.Scanner;

class p10306 {
    static final int MAX = 300, INF = 10000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            int coinNum = in.nextInt();
            int S = in.nextInt();

            Coin coinList[] = new Coin[coinNum];
            for (int i = 0; i < coinNum; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                coinList[i] = new Coin(x, y);
            }

            int dp[][] = new int[MAX + 1][MAX + 1];
            for (int i = 0; i <= MAX; i++) {
                for (int j = 0; j <= MAX; j++) {
                    dp[i][j] = INF;
                }
            }
            dp[0][0] = 0;
            for (int i = 0; i < coinNum; i++) {
                int x = coinList[i].x;
                int y = coinList[i].y;
                for (int j = x; j <= MAX; j++) {
                    for (int k = y; k <= MAX; k++) {
                        dp[j][k] = Math.min(dp[j][k], dp[j - x][k - y] + 1);
                    }
                }
            }

            int result[] = new int[90001];
            for (int i = 0; i < 90001; i++) {
                result[i] = INF;
            }
            for (int i = 0; i <= MAX; i++) {
                for (int j = 0; j <= MAX; j++) {
                    if (dp[i][j] != INF) {
                        int value = i * i + j * j;
                        if (value < 90001)
                            result[value] = Math.min(result[value], dp[i][j]);
                    }
                }
            }

            int target = S * S;
            if (result[target] == INF) {
                System.out.println("not possible");
            } else {
                System.out.println(result[target]);
            }
        }

        in.close();
    }
}

class Coin {
    int x, y, pos;

    Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }
}