import java.util.Scanner;

class p00423 {
    static final int INF = 1000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int matrix[][] = new int[n][n];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                String s = in.next();
                if (s.equals("x"))
                    matrix[i][j] = matrix[j][i] = INF;
                else
                    matrix[i][j] = matrix[j][i] = Integer.parseInt(s);
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        int result = 0;
        for (int i = 1; i < n; i++) {
            if (matrix[0][i] < INF)
                result = Math.max(result, matrix[0][i]);
        }
        System.out.println(result);

        in.close();
    }
}