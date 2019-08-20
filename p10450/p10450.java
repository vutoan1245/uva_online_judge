import java.util.Scanner;

class p10450 {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int MAX = 53;
        long fib[] = new long[MAX];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < MAX; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        int caseNum = in.nextInt();
        int caseCount = 1;
        while (caseNum-- > 0) {
            int n = in.nextInt();

            System.out.printf("Scenario #%d:\n", caseCount++);
            System.out.println(fib[n + 1]);
            System.out.println();

        }

        in.close();
    }
}