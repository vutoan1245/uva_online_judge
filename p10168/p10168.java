import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

class p10168 {
    static final int MAX = 10000000;
    static int size = 1184, result[];
    static BitSet isPrime;
    static ArrayList<Integer> primeList;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        seive();

        while (in.hasNext()) {
            int num = in.nextInt();

            result = new int[4];
            if (findSolution(num, 4)) {
                System.out.printf("%d %d %d %d\n", result[0], result[1], result[2], result[3]);
            } else {
                System.out.println("Impossible.");
            }
        }

        in.close();
    }

    static boolean findSolution(int n, int count) {
        if (n <= 0)
            return false;

        if (count == 1) {
            if (isPrime.get(n)) {
                result[4 - count] = n;
                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < size && primeList.get(i) < n; i++) {
            if (findSolution(n - primeList.get(i), count - 1)) {
                result[4 - count] = primeList.get(i);
                return true;
            }
        }

        return false;
    }

    // Find list of Prime numbers
    static void seive() {
        primeList = new ArrayList<Integer>(size);
        isPrime = new BitSet(MAX);
        for (int i = 2; i < MAX; i++) {
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