import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

class p10948 {
    static final int MAX = 10000000;
    static int size = 664579;
    static BitSet isPrime;
    static ArrayList<Integer> primeList;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        seive();

        while (true) {
            int num = in.nextInt();
            if (num == 0)
                break;

            boolean possible = false;
            System.out.println(num + ":");
            for (int i = 0; primeList.get(i) < num; i++) {
                int first = primeList.get(i);
                int second = num - first;
                if (isPrime.get(second)) {
                    System.out.printf("%d+%d\n", first, second);
                    possible = true;
                    break;
                }
            }

            if (!possible) {
                System.out.println("NO WAY!");
            }

        }

        in.close();
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