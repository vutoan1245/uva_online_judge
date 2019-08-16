import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class p10480 {
    static int src = 1, dest = 2, max;
    static boolean adjMatrix[][];
    static int parent[], capacity[][];
    static long visited1, visited2;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int n = in.nextInt();
            int m = in.nextInt();

            if (n == 0 && m == 0)
                break;

            // Initialize
            max = n + 1;
            capacity = new int[max][max];
            adjMatrix = new boolean[max][max];

            // Get input
            for (int i = 0; i < m; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                int c = in.nextInt();

                adjMatrix[from][to] = adjMatrix[to][from] = true;
                capacity[from][to] = capacity[to][from] = c;
            }

            // Edmonds Kark's Algorithm
            while (bfs()) {
                augment(dest, Integer.MAX_VALUE);
            }

            // Find minimum cost
            visited1 = visited2 = 0;
            dfs2(2);
            dfs1(1);

            System.out.println();
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

    public static void dfs1(int from) {
        visited1 |= (1L << from);
        for (int to = 1; to < max; to++)
            if (capacity[from][to] == 0 && ((1L << to) & visited2) != 0 && adjMatrix[from][to])
                System.out.println(from + " " + to);
            else if (adjMatrix[from][to] && ((1L << to) & visited1) == 0)
                dfs1(to);
    }

    public static void dfs2(int from) {
        visited2 |= (1L << from);
        for (int to = 1; to < max; to++)
            if (capacity[to][from] > 0 && ((visited2 & (1L << to)) == 0))
                dfs2(to);
    }
}