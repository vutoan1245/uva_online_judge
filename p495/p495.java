import java.math.BigInteger;
import java.util.Scanner;

class p495 {
    static final int MAX = 5000 + 1;
    static BigInteger fib[] = new BigInteger[MAX];
    // static long fib[] = new long[MAX];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        findAllFib();

        while (in.hasNext()) {
            int n = in.nextInt();
            System.out.printf("The Fibonacci number for %d is %d\n", n, fib[n]);
        }

        in.close();
    }

    static void findAllFib() {
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;

        for (int i = 2; i < MAX; i++) {
            fib[i] = fib[i - 1].add(fib[i - 2]);
        }
    }
}