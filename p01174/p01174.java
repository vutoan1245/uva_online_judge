import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

class p01174 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while (caseNum-- > 0) {
            int cityNum = in.nextInt();
            int routeNum = in.nextInt();
            TreeMap<String, Integer> mapNameToIndex = new TreeMap<String, Integer>();

            int count = 0;
            Edge edgeList[] = new Edge[routeNum];
            for (int i = 0; i < routeNum; i++) {
                String from = in.next();
                String to = in.next();
                int cost = in.nextInt();

                if (!mapNameToIndex.containsKey(from)) {
                    mapNameToIndex.put(from, count++);
                }
                if (!mapNameToIndex.containsKey(to)) {
                    mapNameToIndex.put(to, count++);
                }

                int fromIndex = mapNameToIndex.get(from);
                int toIndex = mapNameToIndex.get(to);
                edgeList[i] = new Edge(fromIndex, toIndex, cost);
            }
            Arrays.sort(edgeList);

            parent = new int[cityNum];
            for (int i = 0; i < cityNum; i++) {
                parent[i] = i;
            }

            int totalCost = 0;
            for (int i = 0; i < routeNum; i++) {
                int fromParent = findParent(edgeList[i].from);
                int toParent = findParent(edgeList[i].to);

                if (fromParent != toParent) {
                    parent[fromParent] = toParent;
                    totalCost += edgeList[i].cost;
                }
            }

            System.out.println(totalCost);
            if (caseNum != 0)
                System.out.println();
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

    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}