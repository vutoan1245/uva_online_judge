import java.util.Scanner;

class p10699 {
    static final int MAX = 1000000 + 1;
    static int numDiffPF[] = new int[MAX];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        findNumDiffPF();
        int n;
        while ((n = in.nextInt()) != 0) {
            System.out.printf("%d : %d\n", n, numDiffPF[n]);
        }

        in.close();
    }

    static void findNumDiffPF() {
        for (int i = 2; i < MAX; i++) {
            if (numDiffPF[i] == 0) {
                for (int j = i; j < MAX; j += i) {
                    numDiffPF[j]++;
                }
            }
        }
    }
}