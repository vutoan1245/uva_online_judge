import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class p10092 {
    static final int INF = 1000000;
    static int categoryNum, problemNum, max, src, target;
    static int adjMatrix[][], parent[];
    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            categoryNum = in.nextInt();
            problemNum = in.nextInt();

            if (categoryNum == 0 && problemNum == 0)
                break;

            // Initialize
            max = categoryNum + problemNum + 2;
            src = 0;
            target = max - 1;
            adjMatrix = new int[max][max];
            adjList = new ArrayList<ArrayList<Integer>>(max);
            for (int i = 0; i < max; i++) {
                adjList.add(new ArrayList<Integer>());
            }

            // Get input
            int totalProblem = 0;
            for (int i = 0; i < categoryNum; i++) {
                int num = in.nextInt();
                totalProblem += num;
                adjMatrix[src][i + 1] = num;
            }

            for (int i = 1; i <= problemNum; i++) {
                int e = in.nextInt();
                for (int j = 1; j <= e; j++) {
                    int problemIndex = i + categoryNum;
                    int categoryIndex = in.nextInt();

                    adjMatrix[categoryIndex][problemIndex] = 1;
                    adjMatrix[problemIndex][target] = 1;

                    adjList.get(categoryIndex).add(i);
                }
            }

            // Edmonds Karp's Algorithm
            int maxFlow = 0;
            while (bfs()) {
                maxFlow += augment(target, INF);
            }

            if (maxFlow == totalProblem) {
                System.out.println(1);
                for (int i = 1; i <= categoryNum; i++) {
                    boolean f = false;
                    for (int j : adjList.get(i)) {
                        if (adjMatrix[i][j + categoryNum] == 0) {
                            if (f)
                                System.out.print(" ");
                            f = true;
                            System.out.print(j + "");
                        }
                    }
                    System.out.print("\n");
                }
            } else {
                System.out.println(0);
            }

        }

        in.close();
    }

    static int augment(int index, int currFlow) {
        if (index == src)
            return currFlow;

        int from = parent[index];
        int flow = augment(from, Math.min(currFlow, adjMatrix[from][index]));

        adjMatrix[from][index] -= flow;
        adjMatrix[index][from] += flow;
        return flow;
    }

    static boolean bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(src);

        boolean[] visited = new boolean[max];
        visited[src] = true;

        parent = new int[max];
        for (int i = 0; i < max; i++) {
            parent[i] = -1;
        }

        while (!q.isEmpty()) {
            int from = q.poll();

            for (int to = 0; to < max; to++) {
                if (adjMatrix[from][to] > 0 && !visited[to]) {
                    q.add(to);
                    visited[to] = true;
                    parent[to] = from;
                    if (to == target) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}