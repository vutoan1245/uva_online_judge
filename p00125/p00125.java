import java.util.*;

class p00125 {
    static final int MAX = 30;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 0;
        while (in.hasNext()) {
            int n = in.nextInt();
            int matrix[][] = new int[MAX][MAX];

            int limit = 0;
            for (int i = 0; i < n; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                matrix[from][to] = 1;

                limit = Math.max(limit, from);
                limit = Math.max(limit, to);
            }
            limit++;

            // All Shortest Path Algorithm
            for (int k = 0; k < MAX; k++) {
                for (int i = 0; i < MAX; i++) {
                    for (int j = 0; j < MAX; j++) {
                        matrix[i][j] += matrix[i][k] * matrix[k][j];
                    }
                }
            }

            for (int k = 0; k < limit; k++) {
                if (matrix[k][k] != 0)
                    for (int i = 0; i < limit; i++) {
                        for (int j = 0; j < limit; j++) {
                            if (matrix[i][k] != 0 && matrix[k][j] != 0) {
                                matrix[i][j] = -1;
                            }
                        }
                    }
            }

            System.out.printf("matrix for city %d\n", caseCount++);
            for (int i = 0; i < limit; i++) {
                for (int j = 0; j < limit; j++) {

                    System.out.print(matrix[i][j]);

                    if (j != limit - 1) {
                        System.out.print(" ");
                    } else {
                        System.out.println();
                    }
                }
            }
        }

        in.close();
    }
}