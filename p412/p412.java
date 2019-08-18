import java.util.Scanner;

class p412 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int n = in.nextInt();
            if (n == 0)
                break;

            int numList[] = new int[n];
            for (int i = 0; i < n; i++) {
                numList[i] = in.nextInt();
            }

            int g = 0, count = 0;
            ;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (gcd(numList[i], numList[j]) == 1) {
                        g++;
                    }
                    count++;
                }
            }

            if (g == 0) {
                System.out.println("No estimate for this data set.");
            } else {
                double pi = Math.sqrt(((double) (6 * count) / (double) g));
                System.out.printf("%.6f\n", pi);
            }
        }

        in.close();
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}