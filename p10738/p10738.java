import java.util.Scanner;

public class p10738 {

    static final int MAX = 1000000 + 1;
    static int mu[] = new int[MAX], M[] = new int[MAX];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        findAll();

        int n;
        while ((n = in.nextInt()) != 0) {
            System.out.printf("%8d%8d%8d\n", n, mu[n], M[n]);
        }

        in.close();
    }

    static void findAll() {
        boolean[] isSquareFree = new boolean[MAX];
        for (int i = 0; i < MAX; i++) {
            isSquareFree[i] = true;
        }

        // Modified Sieve
        int numDiffPF[] = new int[MAX];
        for (int i = 2; i < MAX; ++i) {
            if (numDiffPF[i] == 0) {
                for (int j = i; j < MAX; j += i) {
                    numDiffPF[j]++;
                }
            }

            for (int j = 2; i * j < MAX; j++) {
                if (j % i == 0) {
                    isSquareFree[i * j] = false;
                }
            }
        }

        mu[1] = 1;
        for (int i = 2; i < MAX; ++i) {
            if (isSquareFree[i])
                if ((numDiffPF[i] % 2) == 0)
                    mu[i] = 1;
                else
                    mu[i] = -1;
        }

        for (int i = 1; i < MAX; i++)
            M[i] = M[i - 1] + mu[i];
    }
}