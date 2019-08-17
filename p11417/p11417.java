import java.util.Scanner;

class p11417 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int n = in.nextInt();
            if (n == 0)
                break;

            int g = 0;
            for (int i = 1; i < n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    g += gcd(i, j);
                }
            }

            System.out.println(g);
        }

        in.close();
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}