import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class p11733 {
    static int parent[];

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());
        int caseCount = 1;
        while (caseNum-- > 0) {
            // Get input
            String strArr[] = in.readLine().split(" ");
            int cityNum = Integer.parseInt(strArr[0]);
            int routeNum = Integer.parseInt(strArr[1]);
            int airPortCost = Integer.parseInt(strArr[2]);

            Edge edgeList[] = new Edge[routeNum];
            for (int i = 0; i < routeNum; i++) {
                strArr = in.readLine().split(" ");
                edgeList[i] = new Edge(Integer.parseInt(strArr[0]) - 1, Integer.parseInt(strArr[1]) - 1,
                        Integer.parseInt(strArr[2]));
            }
            Arrays.sort(edgeList);

            // Kruskal's algorithm
            parent = new int[cityNum];
            for (int i = 0; i < cityNum; i++) {
                parent[i] = i;
            }

            int routeCost = 0, airPortNum = cityNum;
            for (int i = 0; i < routeNum; i++) {
                if (edgeList[i].cost >= airPortCost)
                    break;

                Edge e = edgeList[i];
                int fromParent = findParent(e.from);
                int toParent = findParent(e.to);

                if (fromParent != toParent) {

                    routeCost += e.cost;
                    parent[fromParent] = toParent;
                    if (--airPortNum == 1)
                        break;
                }
            }

            System.out.printf("Case #%d: %d %d\n", caseCount++, routeCost + airPortNum * airPortCost, airPortNum);
        }

        in.close();
    }

    static int findParent(int index) {
        if (index == parent[index])
            return parent[index];
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