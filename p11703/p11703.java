import java.util.*;

public class p11703 {

    static final int max = 1000000;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int dp[] = new int[max + 1];
        dp[0] = 1;

        for (int i = 1; i <= max; i++) {
            dp[i] = dp[(int) (i - Math.sqrt(i))] + dp[(int) (Math.log(i))] + dp[(int) (i * Math.sin(i) * Math.sin(i))];
            dp[i] %= max;
        }

        while (true) {
            int i = in.nextInt();
            if (i == -1) {
                break;
            }

            System.out.println(dp[i]);
        }

        in.close();
    }

}