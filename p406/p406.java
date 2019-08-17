import java.util.ArrayList;
import java.util.Scanner;

public class p406 {
    static ArrayList<Integer> primeList;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            // Get input
            int n = in.nextInt();
            int c = in.nextInt();

            // Calculate output
            findPrimeList(n);

            int size = primeList.size();
            int start = 0;
            int length = 0;
            if (size % 2 == 0) {
                length = 2 * c;
                start = (size - length) / 2;
            } else {
                length = 2 * c - 1;
                start = (size - length) / 2;
            }
            if (length > size) {
                start = 0;
                length = size;
            }

            // Print output
            System.out.printf("%d %d:", n, c);
            for (int i = start; i < start + length; i++) {
                System.out.print(" " + primeList.get(i));
            }
            System.out.print("\n\n");
        }

        in.close();
    }

    static void findPrimeList(int n) {
        primeList = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                primeList.add(i);
            }
        }
    }

    static boolean isPrime(int n) {
        if (n == 1) {
            return true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}