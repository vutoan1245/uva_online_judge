import java.util.Arrays;
import java.util.Scanner;

class p10099 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (true) {
            int cityNum = in.nextInt();
            int routeNum = in.nextInt();

            if (cityNum == 0 && routeNum == 0) {
                break;
            }

            Edge edgeList[] = new Edge[routeNum];
            for (int i = 0; i < routeNum; i++) {
                edgeList[i] = new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
            }
            Arrays.sort(edgeList);

            int src = in.nextInt() - 1;
            int dest = in.nextInt() - 1;
            int passengerNum = in.nextInt();

            parent = new int[cityNum];
            for (int i = 0; i < cityNum; i++) {
                parent[i] = i;
            }

            int minCost = 0;
            for (Edge e : edgeList) {
                int fromParent = findParent(e.from);
                int toParent = findParent(e.to);

                if (fromParent != toParent) {
                    parent[fromParent] = toParent;
                }

                if (findParent(src) == findParent(dest)) {
                    minCost = e.cost;
                    break;
                }
            }
            minCost--;

            int tripNum = (int) Math.ceil(((double) passengerNum / (double) minCost));
            System.out.printf("Scenario #%d\n", caseCount++);
            System.out.printf("Minimum Number of Trips = %d\n\n", tripNum);

        }

        in.close();
    }

    static int findParent(int index) {
        if (parent[index] == index)
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

    public int compareTo(Edge o) {
        return o.cost - cost;
    }
}