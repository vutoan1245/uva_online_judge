import java.util.Scanner;

class p10354 {
    static final int INF = 10000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int placeNum = in.nextInt();
            int routeNum = in.nextInt();
            int boss = in.nextInt() - 1;
            int office = in.nextInt() - 1;
            int home = in.nextInt() - 1;
            int market = in.nextInt() - 1;

            // Floyd's Algorithm
            int bossMatrix[][] = new int[placeNum][placeNum];
            int myMatrix[][] = new int[placeNum][placeNum];
            for (int i = 0; i < placeNum; i++) {
                for (int j = 0; j < placeNum; j++) {
                    bossMatrix[i][j] = INF;
                    myMatrix[i][j] = INF;
                }
                bossMatrix[i][i] = 0;
                myMatrix[i][i] = 0;
            }

            for (int i = 0; i < routeNum; i++) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                int cost = in.nextInt();

                bossMatrix[from][to] = bossMatrix[to][from] = cost;
                myMatrix[from][to] = myMatrix[to][from] = cost;
            }

            // Find boss path
            for (int k = 0; k < placeNum; k++) {
                for (int i = 0; i < placeNum; i++) {
                    for (int j = 0; j < placeNum; j++) {
                        bossMatrix[i][j] = Math.min(bossMatrix[i][j], bossMatrix[i][k] + bossMatrix[k][j]);
                    }
                }
            }

            boolean bossPath[] = new boolean[placeNum];
            for (int i = 0; i < placeNum; i++) {
                if (bossMatrix[boss][i] + bossMatrix[i][office] == bossMatrix[boss][office])
                    bossPath[i] = true;
            }

            for (int i = 0; i < placeNum; i++) {
                if (bossPath[i]) {
                    for (int j = 0; j < placeNum; j++) {
                        myMatrix[i][j] = myMatrix[j][i] = INF;
                    }
                }
            }

            // Find my path
            for (int k = 0; k < placeNum; k++) {
                for (int i = 0; i < placeNum; i++) {
                    for (int j = 0; j < placeNum; j++) {
                        myMatrix[i][j] = Math.min(myMatrix[i][j], myMatrix[i][k] + myMatrix[k][j]);
                    }
                }
            }

            if (myMatrix[home][market] >= INF) {
                System.out.println("MISSION IMPOSSIBLE.");
            } else {
                System.out.println(myMatrix[home][market]);
            }
        }

        in.close();
    }
}