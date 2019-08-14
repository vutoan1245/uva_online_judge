import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class p00259 {
    static final int MAX = 38, INF = 1000000;
    static int src = 0, dest = 37;
    static int capacity[][], dist[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {

            // Get input
            String line, strArr[];
            int totalApp = 0;
            capacity = new int[MAX][MAX];

            while (in.hasNext() && !(line = in.nextLine()).equals("")) {

                strArr = line.split(" ");

                int app = strArr[0].charAt(0) - 'A' + 1;
                int appNum = strArr[0].charAt(1) - '0';
                totalApp += appNum;
                capacity[0][app] = appNum;

                for (int i = 0, size = strArr[1].length() - 1; i < size; i++) {
                    int computer = strArr[1].charAt(i) - '0' + 27;
                    capacity[app][computer] = 1;
                }
            }

            for (int i = 27; i <= 36; ++i)
                capacity[i][37] = 1;

            // Admonds Karp's Algorithm
            int maxFlow = 0;
            while (bfs()) {
                maxFlow += dfs(dest, INF);
            }

            if (maxFlow == totalApp) {
                for (int i = 27; i <= 36; ++i) {
                    boolean isUsed = false;
                    for (int j = 1; j <= 26 && !isUsed; j++)
                        if (capacity[i][j] == 1) {
                            System.out.print((char) (j + 'A' - 1));
                            isUsed = true;
                        }

                    if (!isUsed)
                        System.out.print('_');
                }
                System.out.println();
            } else {
                System.out.println('!');
            }

        }

        in.close();
    }

    static boolean bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(src);

        dist = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            dist[i] = -1;
        }
        dist[src] = src;

        while (!q.isEmpty()) {
            int from = q.poll();
            if (from == dest)
                return true;

            for (int to = 0; to < MAX; ++to)
                if (capacity[from][to] > 0 && dist[to] == -1) {
                    dist[to] = from;
                    q.add(to);
                }
        }

        return false;
    }

    static int dfs(int to, int currFlow) {
        if (to == src)
            return currFlow;

        int from = dist[to];
        currFlow = dfs(from, Math.min(currFlow, capacity[from][to]));

        capacity[from][to] -= currFlow;
        capacity[to][from] += currFlow;
        return currFlow;
    }
}