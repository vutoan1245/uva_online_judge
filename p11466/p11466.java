import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

class p11466 {
    static final int MAX = (int) 10000000;
    static BitSet isPrime = new BitSet(MAX);
    static ArrayList<Integer> primeList = new ArrayList<Integer>();

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        seive();
        while (true) {
            long n = Math.abs(in.nextLong());
            if (n == 0)
                break;

            System.out.println(findMaxPrimeFactor(n));
        }

        in.close();

    }

    static long findMaxPrimeFactor(long n) {
        int count = 0;
        long result = -1;

        for (int prime : primeList)
            if (1l * prime * prime > n)
                break;
            else if (n % prime == 0) {
                count++;
                result = prime;
                while (n % prime == 0)
                    n /= prime;
            }
        if (n != 1) {
            count++;
            result = n;
        }

        if (count <= 1)
            return -1;
        else
            return result;
    }

    static void seive() {
        for (int i = 0; i < MAX; i++) {
            isPrime.set(i, true);
        }

        for (int i = 2; i < MAX; i++) {
            if (isPrime.get(i)) {
                primeList.add(i);
                for (int j = i + i; j < MAX; j += i) {
                    isPrime.set(j, false);
                }
            }
        }
    }
}