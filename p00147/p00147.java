import java.util.Scanner;

class p00147 {
    static int coinList[] = { 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };
    static int size = coinList.length, max = 30001;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        long dp[] = new long[max + 1];
        dp[0] = 1;

        for (int i = 0; i < size; i++) {
            for (int j = coinList[i]; j <= max; j++) {
                dp[j] += dp[j - coinList[i]];
            }
        }

        while (true) {
            double value = in.nextDouble();
            if (value == 0.0)
                break;

            int cents = (int) (value * 100 + 0.5);
            long coinNum = dp[cents];

            System.out.printf("%6.2f%17d\n", value, coinNum);
        }

        in.close();
    }
}