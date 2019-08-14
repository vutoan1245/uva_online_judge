import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class p00820 {
    static int nodeNum, src, dest;
    static int capacity[][], dist[];
    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (in.hasNext()) {

            nodeNum = in.nextInt();
            if (nodeNum == 0)
                break;

            capacity = new int[nodeNum][nodeNum];
            adjList = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < nodeNum; i++) {
                adjList.add(new ArrayList<Integer>());
            }

            src = in.nextInt() - 1;
            dest = in.nextInt() - 1;
            int edgeNum = in.nextInt();

            while (edgeNum-- > 0) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                adjList.get(from).add(to);
                adjList.get(to).add(from);

                int c = in.nextInt();
                capacity[from][to] += c;
                capacity[to][from] += c;
            }

            // Edmonds Karp's Algorithm
            int result = 0;
            while (bfs()) {
                result += dfs(src, Integer.MAX_VALUE);
            }

            System.out.printf("Network %d\n", caseCount++);
            System.out.printf("The bandwidth is %d.\n\n", result);

        }

        in.close();
    }

    static int dfs(int from, int currFlow) {
        if (from == dest)
            return currFlow;

        for (int i = 0; i < adjList.get(from).size(); i++) {
            int to = adjList.get(from).get(i);

            if (dist[to] == dist[from] + 1 && capacity[from][to] > 0) {
                int f = dfs(to, Math.min(currFlow, capacity[from][to]));
                if (f > 0) {
                    capacity[from][to] -= f;
                    capacity[to][from] += f;
                    return f;
                }
            }
        }
        return 0;
    }

    static boolean bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(src);

        dist = new int[nodeNum];
        for (int i = 0; i < nodeNum; i++) {
            dist[i] = -1;
        }
        dist[src] = 0;

        while (!q.isEmpty()) {
            int from = q.remove();
            if (from == dest)
                return true;

            for (int i = 0; i < adjList.get(from).size(); ++i) {
                int to = adjList.get(from).get(i);
                if (dist[to] == -1 && capacity[from][to] > 0) {
                    dist[to] = dist[from] + 1;
                    q.add(to);
                }
            }
        }

        return false;
    }
}