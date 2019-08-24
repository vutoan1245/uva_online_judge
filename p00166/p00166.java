import java.util.Scanner;

class p00166 {
    static final int SIZE = 6, MAX = 30000, INF = 100000;
    static int coinList[] = { 5, 10, 20, 50, 100, 200 };

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int seller[] = new int[MAX + 1];
        for (int i = 1; i <= MAX; i++) {
            seller[i] = INF;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = coinList[i]; j <= MAX; j++) {
                seller[j] = Math.min(seller[j], seller[j - coinList[i]] + 1);
            }
        }

        while (true) {
            int sum = 0;
            int coinAmount[] = new int[SIZE];
            for (int i = 0; i < SIZE; i++) {
                int coinNum = in.nextInt();
                coinAmount[i] = coinNum;
                sum += coinNum * coinList[i];
            }

            if (sum == 0)
                break;

            int wallet[] = new int[MAX + 1];
            for (int i = 1; i <= MAX; i++) {
                wallet[i] = INF;
            }

            for (int i = 0; i < 6; i++) {
                for (int k = sum; k >= 0; k--) {
                    for (int j = 1; j <= coinAmount[i]; j++) {
                        if (k - coinList[i] * j < 0)
                            break;
                        wallet[k] = Math.min(wallet[k], wallet[k - coinList[i] * j] + j);
                    }
                }
            }

            double value = in.nextDouble() + 0.005;
            int centValue = (int) (value * 100);

            int result = INF;
            for (int i = centValue; i <= MAX; i++) {
                result = Math.min(result, wallet[i] + seller[i - centValue]);
            }

            System.out.printf("%3d\n", result);

        }

        in.close();
    }
}