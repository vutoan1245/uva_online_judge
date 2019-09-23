import java.util.*;

class p10003 {
    static final int MAX = 1000000;
    static int cutNum;
    static int cutList[];
    static int dp[][];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int stickLen = in.nextInt();
            if (stickLen == 0) {
                break;
            }

            cutNum = in.nextInt();

            cutList = new int[cutNum + 2];
            cutList[cutNum + 1] = stickLen;
            for (int i = 1; i <= cutNum; i++) {
                cutList[i] = in.nextInt();
            }

            dp = new int[cutNum + 2][cutNum + 2];

            System.out.printf("The minimum cutting is %d.\n", cut(0, cutNum + 1));
        }

        in.close();
    }

    static int cut(int left, int right) {
        if (left + 1 == right) {
            return 0;
        }

        if (dp[left][right] != 0) {
            return dp[left][right];
        }

        int result = MAX, cost = cutList[right] - cutList[left];
        for (int i = left + 1; i < right; i++) {
            result = Math.min(result, cost + cut(left, i) + cut(i, right));
        }

        return dp[left][right] = result;
    }
}