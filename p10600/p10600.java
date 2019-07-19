import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class p10600 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while (caseNum-- > 0) {
            int schoolNum = in.nextInt();
            int routeNum = in.nextInt();

            ArrayList<Edge> edgeList = new ArrayList<Edge>(routeNum);
            for (int i = 0; i < routeNum; i++) {
                edgeList.add(new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
            }
            Collections.sort(edgeList);

            // Kruskal Algorithm
            parent = new int[schoolNum];
            for (int i = 0; i < schoolNum; i++) {
                parent[i] = i;
            }

            ArrayList<Integer> included = new ArrayList<Integer>();
            int minCost = 0;
            for (int i = 0; i < routeNum; i++) {
                Edge e = edgeList.get(i);
                int from = e.from;
                int to = e.to;
                int fromParent = findParent(from);
                int toParent = findParent(to);

                if (fromParent != toParent) {
                    included.add(i);
                    parent[fromParent] = toParent;
                    minCost += e.cost;
                }
            }

            // Calculate second lowest cost
            int secondMinCost = Integer.MAX_VALUE;
            for (int j = 0; j < included.size(); j++) {
                for (int i = 0; i < schoolNum; i++) {
                    parent[i] = i;
                }

                int tempCost = 0;
                for (int i = 0; i < routeNum; i++) {
                    if (i == included.get(j))
                        continue;
                    Edge e = edgeList.get(i);
                    int from = e.from;
                    int to = e.to;
                    int fromParent = findParent(from);
                    int toParent = findParent(to);

                    if (fromParent != toParent) {
                        parent[fromParent] = toParent;
                        tempCost += e.cost;
                    }
                }
                if (validate() && secondMinCost > tempCost)
                    secondMinCost = tempCost;
            }

            System.out.println(minCost + " " + secondMinCost);
        }

        in.close();
    }

    static boolean validate() {
        for (int i = 0; i < parent.length; i++) {
            if (findParent(i) != findParent(0))
                return false;
        }

        return true;
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