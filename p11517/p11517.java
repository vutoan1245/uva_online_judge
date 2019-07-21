import java.util.Scanner;

class p11517 {
    static final int MAX = 10000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while (caseNum-- > 0) {
            int totalPay = in.nextInt();
            int coinNum = in.nextInt();
            int coinList[] = new int[coinNum];

            for (int i = 0; i < coinNum; i++) {
                coinList[i] = in.nextInt();
            }

            // Calculate output
            Node dp[] = new Node[MAX + 1];
            for (int i = 0; i <= MAX; i++) {
                dp[i] = new Node();
            }
            dp[0].possible = true;
            dp[0].coinCount = 0;

            for (int i = 0; i < coinNum; i++) {
                for (int j = MAX; j >= coinList[i]; j--) {
                    if (dp[j - coinList[i]].possible) {
                        dp[j].possible = true;
                        dp[j].coinCount = Math.min(dp[j].coinCount, dp[j - coinList[i]].coinCount + 1);
                    }
                }
            }

            while (!dp[totalPay].possible) {
                totalPay++;
            }

            System.out.println(totalPay + " " + dp[totalPay].coinCount);
        }

        in.close();
    }
}

class Node {
    boolean possible = false;
    int coinCount = Integer.MAX_VALUE;
}