import java.util.Arrays;
import java.util.Scanner;

public class p10525 {

    static final int INF = 1000000000;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            int placeNum = in.nextInt();
            int routeNum = in.nextInt();
            int length[][] = new int[placeNum][placeNum];
            int time[][] = new int[placeNum][placeNum];

            for (int i = 0; i < placeNum; i++) {
                Arrays.fill(length[i], INF);
                Arrays.fill(time[i], INF);
                length[i][i] = time[i][i] = 0;
            }

            while (routeNum-- > 0) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                int t = in.nextInt(), l = in.nextInt();
                if (t < time[from][to]) {
                    length[from][to] = length[to][from] = l;
                    time[from][to] = time[to][from] = t;
                } else if (t == time[from][to] && l < length[from][to]) {
                    length[from][to] = length[to][from] = l;
                }
            }

            // Floyd's Algorithm
            for (int k = 0; k < placeNum; k++)
                for (int i = 0; i < placeNum; i++)
                    for (int j = 0; j < placeNum; j++)
                        if (time[i][j] > time[i][k] + time[k][j]) {
                            time[i][j] = time[i][k] + time[k][j];
                            length[i][j] = length[i][k] + length[k][j];
                        } else if (time[i][j] == time[i][k] + time[k][j]
                                && length[i][j] > length[i][k] + length[k][j]) {
                            length[i][j] = length[i][k] + length[k][j];
                        }

            int queryNum = in.nextInt();
            while (queryNum-- > 0) {
                int src = in.nextInt() - 1;
                int dest = in.nextInt() - 1;
                if (length[src][dest] == INF)
                    System.out.println("No Path.");
                else
                    System.out.printf("Distance and time to reach destination is %d & %d.\n", length[src][dest],
                            time[src][dest]);
            }
            if (caseNum != 0)
                System.out.print("\n");
        }

        in.close();
    }
}
