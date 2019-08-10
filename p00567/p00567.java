import java.io.IOException;
import java.util.Scanner;

class p00567 {
    static final int INF = 20 * 20;

    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (in.hasNext()) {

            int matrix[][] = new int[20][20];
            for (int i = 0; i < 20; ++i)
                for (int j = 0; j < 20; j++)
                    matrix[i][j] = INF;

            for (int i = 0; i < 19; ++i) {
                int k = in.nextInt();
                while (k-- > 0) {
                    int j = in.nextInt() - 1;
                    matrix[i][j] = matrix[j][i] = 1;
                }
            }

            for (int k = 0; k < 20; ++k)
                for (int i = 0; i < 20; ++i)
                    for (int j = 0; j < 20; ++j)
                        matrix[i][j] = Math.min(matrix[i][k] + matrix[k][j], matrix[i][j]);

            int query = in.nextInt();
            System.out.printf("Test Set #%d\n", caseCount++);
            while (query-- > 0) {
                int from = in.nextInt();
                int to = in.nextInt();
                System.out.printf("%2d to %2d: %d\n", from, to, matrix[from - 1][to - 1]);
            }
            System.out.println();
        }

        in.close();
    }
}