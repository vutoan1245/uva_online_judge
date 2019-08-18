import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class p583 {
    static int MAX = 1000000;
    static ArrayList<Integer> primeList;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        sieve();
        while (true) {
            int n = in.nextInt();
            if (n == 0)
                break;

            ArrayList<Integer> pfList = findPrimeFactors(n);
            System.out.print(n + " = " + pfList.get(0));

            for (int i = 1, size = pfList.size(); i < size; i++)
                System.out.print(" x " + pfList.get(i));
            System.out.println();
        }

        in.close();
    }

    static void sieve() {
        boolean[] isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);

        primeList = new ArrayList<Integer>(MAX / 10);
        for (int i = 2; i < MAX; ++i)
            if (isPrime[i]) {
                primeList.add(i);
                if (1l * i * i < MAX)
                    for (int j = i * i; j < MAX; j += i)
                        isPrime[j] = false;
            }
    }

    static ArrayList<Integer> findPrimeFactors(int n) {
        ArrayList<Integer> pfList = new ArrayList<Integer>();
        if (n < 0) {
            pfList.add(-1);
            n *= -1;
        }

        int pfIndex = 0, p = primeList.get(pfIndex);
        while (1l * p * p <= n) {
            while (n % p == 0) {
                n /= p;
                pfList.add(p);
            }
            p = primeList.get(++pfIndex);
        }
        if (n != 1)
            pfList.add(n);
        return pfList;
    }
}