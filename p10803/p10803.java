import java.util.Scanner;

class p10803 {
    static final int MAX = 1024;
    static final double INF = 1e9;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        int caseCount = 1;
        while (caseNum-- > 0) {

            int n = in.nextInt();

            Point pointList[] = new Point[n];
            for (int i = 0; i < n; i++) {
                pointList[i] = new Point(in.nextInt(), in.nextInt());
            }

            double matrix[][] = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = INF;
                }
                matrix[i][i] = 0;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    int d = distance(pointList[i], pointList[j]);
                    if (d <= 100)
                        matrix[i][j] = matrix[j][i] = Math.sqrt(d);
                }
            }

            // Floyd
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }

            double result = 0.0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result = Math.max(result, matrix[i][j]);

                }
            }

            System.out.printf("Case #%d:\n", caseCount++);
            if (result > INF / 10)
                System.out.println("Send Kurdy\n");
            else
                System.out.format("%.4f\n\n", result);

        }

        in.close();
    }

    static int distance(Point first, Point second) {
        int x = first.x - second.x;
        int y = first.y - second.y;

        return x * x + y * y;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}