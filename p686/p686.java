import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

class p686 {
    static final int MAX = (1 << 15);
    static int size = 3512;
    static BitSet isPrime;
    static ArrayList<Integer> primeList;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        seive();

        while (true) {
            int num = in.nextInt();
            if (num == 0)
                break;

            int mid = num / 2, result = 0;

            for (int i = 0; primeList.get(i) <= mid; i++) {
                int first = primeList.get(i);
                int second = num - first;

                if (isPrime.get(second)) {
                    result++;
                }
            }

            System.out.println(result);
        }

        in.close();
    }

    // Get list of Prime numbers
    static void seive() {
        primeList = new ArrayList<Integer>(size);
        isPrime = new BitSet(size);
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