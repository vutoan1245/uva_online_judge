import java.util.Arrays;
import java.util.Scanner;

class p10048 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 0;
        while (in.hasNext()) {
            int crossNum = in.nextInt();
            int routeNum = in.nextInt();
            int queryNum = in.nextInt();

            if (crossNum == 0)
                break;
            if (caseCount != 0)
                System.out.println();

            Edge edgeList[] = new Edge[routeNum];
            for (int i = 0; i < routeNum; i++) {
                edgeList[i] = new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
            }
            Arrays.sort(edgeList);

            // Kruskal Algorithm
            System.out.printf("Case #%d\n", ++caseCount);
            for (int i = 0; i < queryNum; i++) {
                int src = in.nextInt() - 1;
                int dest = in.nextInt() - 1;

                parent = new int[crossNum];
                for (int j = 0; j < crossNum; j++) {
                    parent[j] = j;
                }

                int result = -1;
                for (int j = 0; j < routeNum; j++) {
                    Edge e = edgeList[j];
                    int fromParent = findParent(e.from);
                    int toParent = findParent(e.to);

                    if (fromParent != toParent) {
                        parent[fromParent] = toParent;
                    }

                    if (findParent(src) == findParent(dest)) {
                        result = e.cost;
                        break;
                    }
                }

                // Print out result for each query
                if (result != -1)
                    System.out.println(result);
                else
                    System.out.println("no path");

            }
        }

        in.close();
    }

    static int findParent(int index) {
        if (index == parent[index])
            return index;
        return parent[index] = findParent(parent[index]);
    }
}

class Edge implements Comparable<Edge> {
    int from, to, cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}