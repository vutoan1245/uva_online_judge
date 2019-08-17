import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class p543 {
    static final int MAX = 1000000;
    static int size = 78499;
    static boolean isPrime[];
    static ArrayList<Integer> primeList;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        seive();

        while (true) {
            int num = in.nextInt();
            if (num == 0)
                break;

            for (int i = 0; i < size; i++) {
                int first = primeList.get(i);
                int second = num - first;
                if (isPrime[second]) {
                    System.out.printf("%d = %d + %d\n", num, first, second);
                    break;
                }
            }
        }

        in.close();
    }

    // Find list of prime number
    static void seive() {

        primeList = new ArrayList<Integer>(size);

        isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < MAX; i++) {
            if (isPrime[i]) {
                primeList.add(i);
            }
            for (int j = i + i; j < MAX; j += i) {
                isPrime[j] = false;
            }
        }
    }
}