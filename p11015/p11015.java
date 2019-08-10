import java.util.Scanner;

class p11015 {
    static final int INF = 10000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (true) {
            int n = in.nextInt();
            int m = in.nextInt();

            if (n == 0 && m == 0)
                break;

            String nameList[] = new String[n];
            for (int i = 0; i < n; i++) {
                nameList[i] = in.next();
            }

            int matrix[][] = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = INF;
                }
                matrix[i][i] = 0;
            }

            for (int i = 0; i < m; i++) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                int cost = in.nextInt();

                matrix[from][to] = matrix[to][from] = cost;
            }

            // Floyd's Algorithm
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }

            int index = -1, min = INF;
            for (int i = 0; i < n; i++) {
                int curr = 0;
                for (int j = 0; j < n; j++) {
                    curr += matrix[j][i];
                }

                if (curr < min || curr == min && nameList[i].compareTo(nameList[index]) < 0) {
                    index = i;
                    min = curr;
                }
            }

            System.out.printf("Case #%d : %s\n", caseCount++, nameList[index]);
        }

        in.close();
    }
}