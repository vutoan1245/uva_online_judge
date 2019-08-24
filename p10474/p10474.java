import java.util.Scanner;
import java.util.Arrays;

class p10474 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (true) {
            int n = in.nextInt();
            int q = in.nextInt();
            if (n == 0 && q == 0) {
                break;
            }

            int array[] = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = in.nextInt();
            }
            Arrays.sort(array);

            System.out.printf("CASE# %d:\n", caseCount++);
            for (int i = 0; i < q; i++) {
                int num = in.nextInt();
                int pos = Arrays.binarySearch(array, num);

                if (pos < 0) {
                    System.out.printf("%d not found\n", num);
                } else {
                    while (pos - 1 >= 0 && array[pos - 1] == num) {
                        pos--;
                    }
                    System.out.printf("%d found at %d\n", num, pos + 1);
                }
            }
        }

        in.close();
    }
}