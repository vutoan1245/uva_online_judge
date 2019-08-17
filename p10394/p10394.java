import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

class p10394 {
    static final int MAX = 20000000;
    static int size = 1270607;
    static ArrayList<Integer> primeList;
    static ArrayList<Pair> twinPrimeList;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        seive();
        findAllPair();

        while (in.hasNext()) {
            int num = in.nextInt();

            Pair twin = twinPrimeList.get(num - 1);
            System.out.printf("(%d, %d)\n", twin.first, twin.second);
        }

        in.close();
    }

    static void findAllPair() {
        twinPrimeList = new ArrayList<Pair>();
        for (int i = 0; i < size - 1; i++) {
            if (primeList.get(i) == primeList.get(i + 1) - 2) {
                twinPrimeList.add(new Pair(primeList.get(i), primeList.get(i + 1)));
            }
        }
    }

    static void seive() {
        primeList = new ArrayList<Integer>(size);
        BitSet isPrime = new BitSet(MAX);
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

class Pair {
    int first, second;

    Pair(int f, int s) {
        first = f;
        second = s;
    }
}