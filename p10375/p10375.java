import java.util.Scanner;

class p10375 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int n1 = in.nextInt();
            int k1 = in.nextInt();
            int n2 = in.nextInt();
            int k2 = in.nextInt();

            System.out.printf("%.5f\n", findAns(n1, k1, n2, k2));
        }

        in.close();
    }

    static double findAns(int n1, int k1, int n2, int k2) {
        k1 = Math.min(k1, n1 - k1);
        k2 = Math.min(k2, n2 - k2);

        double ans = 1.0;
        for (int i = 1, j = 1; i <= k1 || j <= k2; i++, j++) {
            if (i <= k1)
                ans *= (double) (n1 - i + 1) / i;

            if (j <= k2)
                ans /= (double) (n2 - j + 1) / j;
        }

        return ans;
    }
}