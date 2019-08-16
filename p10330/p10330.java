import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class p10330 {
    static final int INF = 100000000;
    static int max, src, dest;
    static int parent[], capacity[][];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int regulatorNum = in.nextInt();

            // Initialize
            max = (regulatorNum << 1) + 2;
            src = 0;
            dest = max - 1;
            capacity = new int[max][max];
            for (int i = 0; i < regulatorNum; i++) {
                int from = (i << 1) + 1;
                int to = (i << 1) + 2;
                capacity[from][to] = in.nextInt();
            }

            // Get input
            int edgeNum = in.nextInt();
            while (edgeNum-- > 0) {
                int from = ((in.nextInt() - 1) << 1) + 2;
                int to = ((in.nextInt() - 1) << 1) + 1;
                capacity[from][to] = in.nextInt();
            }

            int b = in.nextInt();
            int d = in.nextInt();
            for (int i = 0; i < b; i++) {
                int bIndex = ((in.nextInt() - 1) << 1) + 1;
                capacity[0][bIndex] = INF;
            }
            for (int i = 0; i < d; i++) {
                int dIndex = ((in.nextInt() - 1) << 1) + 2;
                capacity[dIndex][dest] = INF;
            }

            // Edmonds Karp's Algirithm
            int maxFlow = 0;
            while (bfs()) {
                maxFlow += augment(dest, INF);
            }

            System.out.println(maxFlow);
        }

        in.close();
    }

    static int augment(int index, int currFlow) {
        if (index == src)
            return currFlow;

        int from = parent[index];
        int flow = augment(from, Math.min(currFlow, capacity[from][index]));
        capacity[from][index] -= flow;
        capacity[index][from] += flow;
        return flow;
    }

    static boolean bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(src);

        parent = new int[max];

        boolean visited[] = new boolean[max];
        visited[src] = true;

        while (!q.isEmpty()) {
            int from = q.poll();

            for (int to = 0; to < max; to++) {
                if (!visited[to] && capacity[from][to] > 0) {
                    visited[to] = true;
                    parent[to] = from;
                    if (to == dest)
                        return true;
                    q.add(to);
                }
            }
        }

        return false;
    }
}