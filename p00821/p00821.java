import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class p00821 {
    static final int INF = 1000000;

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String line;
        int caseCount = 1;
        while ((line = in.readLine()) != null) {
            String strArr[] = line.split(" ");
            int len = strArr.length;
            if (len == 2)
                break;

            int matrix[][] = new int[100][100];
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    matrix[i][j] = INF;
                }
            }

            boolean index[] = new boolean[100];
            for (int i = 0; i < len - 2; i += 2) {
                int from = Integer.parseInt(strArr[i]) - 1;
                int to = Integer.parseInt(strArr[i + 1]) - 1;
                index[from] = true;
                index[to] = true;

                matrix[from][to] = 1;
            }

            // Floyd
            for (int k = 0; k < 100; k++) {
                for (int i = 0; i < 100; i++) {
                    for (int j = 0; j < 100; j++) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }

            int total = 0, count = 0;
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (i == j || matrix[i][j] >= INF)
                        continue;

                    total += matrix[i][j];
                    count++;
                }
            }
            double result = (double) total / (double) count;
            System.out.printf("Case %d: average length between pages = %.3f clicks\n", caseCount++, result);
        }
    }
}