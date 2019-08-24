import java.util.Scanner;
import java.util.Arrays;

class p11057 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int bookNum = in.nextInt();
            int bookList[] = new int[bookNum];
            for (int i = 0; i < bookNum; i++) {
                bookList[i] = in.nextInt();
            }

            int total = in.nextInt();

            // Binary Search the answer
            Arrays.sort(bookList);
            int pos = Arrays.binarySearch(bookList, total / 2);
            if (pos < 0) {
                pos = -(pos + 1);
            }

            for (int i = pos; i >= 0; i--) {
                int first = bookList[i];
                int second = total - first;
                int p = Arrays.binarySearch(bookList, second);

                if (p >= 0) {
                    if (p == i) {
                        int prev = p - 1;
                        int next = p + 1;
                        boolean condition = false;
                        while (prev >= 0 || next < bookNum) {
                            if (prev >= 0) {
                                if (bookList[prev] == second) {
                                    condition = true;
                                    break;
                                }
                                prev--;
                            }

                            if (next < bookNum) {
                                if (bookList[next] == second) {
                                    condition = true;
                                }
                                next++;
                            }
                        }

                        if (!condition)
                            continue;
                    }

                    System.out.printf("Peter should buy books whose prices are %d and %d.\n\n", Math.min(first, second),
                            Math.max(first, second));
                    break;
                }
            }
        }

        in.close();
    }
}