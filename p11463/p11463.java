import java.util.Scanner;

class p11463 {
    static final int INF = 1000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        int caseCount = 0;

        while (caseNum-- > 0) {
            int buildingNum = in.nextInt();
            int routeNum = in.nextInt();

            int matrix[][] = new int[buildingNum][buildingNum];
            for (int i = 0; i < buildingNum; i++) {
                for (int j = 0; j < buildingNum; j++) {
                    matrix[i][j] = INF;
                }
                matrix[i][i] = 0;
            }

            for (int i = 0; i < routeNum; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                matrix[from][to] = matrix[to][from] = 1;
            }

            // Floyd's Algorithm
            for (int k = 0; k < buildingNum; k++)
                for (int i = 0; i < buildingNum; i++)
                    for (int j = 0; j < buildingNum; j++)
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);

            int src = in.nextInt();
            int dest = in.nextInt();
            int max = 0;
            for (int i = 0; i < buildingNum; i++)
                max = Math.max(max, matrix[src][i] + matrix[i][dest]);

            System.out.printf("Case %d: %d\n", ++caseCount, max);
        }

        in.close();
    }
}