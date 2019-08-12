import java.util.Scanner;

class test {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int n = in.nextInt();

            double matrix[][][] = new double[n + 1][n][n];
            int parent[][][] = new int[n + 1][n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j)
                        matrix[0][i][j] = 1.0;
                    else
                        matrix[0][i][j] = in.nextDouble();

                    parent[0][i][j] = i;
                }
            }

            for (int len = 1; len <= n; len++) {
                for (int k = 0; k < n; k++) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            double holder = matrix[len - 1][i][k] * matrix[0][k][j];

                            if (holder > matrix[len][i][j]) {
                                matrix[len][i][j] = holder;
                                parent[len][i][j] = k;
                            }
                        }
                    }
                }
            }

            boolean solutionExist = false;
            for (int len = 1; len <= n && !solutionExist; len++) {
                for (int i = 0; i < n; i++) {
                    if (matrix[len][i][i] > 1.01) {
                        solutionExist = true;

                        int solution[] = new int[len + 1];
                        solution[len] = parent[len][i][i];
                        for (int l = len - 1; l >= 0; l--) {
                            solution[l] = parent[l][i][solution[l + 1]];
                        }

                        for (int sol : solution) {
                            System.out.print(sol + 1 + " ");
                        }
                        System.out.println(solution[0] + 1);

                        break;
                    }
                }
            }

            if (!solutionExist) {
                System.out.println("no arbitrage sequence exists");
            }

        }

        in.close();
    }
}