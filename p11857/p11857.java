import java.util.Arrays;
import java.util.Scanner;

class p11857 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int cityNum = in.nextInt();
            int routeNum = in.nextInt();
            if (cityNum == 0 && routeNum == 0)
                break;

            Edge edgeList[] = new Edge[routeNum];
            for (int i = 0; i < routeNum; i++) {
                edgeList[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
            }
            Arrays.sort(edgeList);

            parent = new int[cityNum];
            for (int i = 0; i < cityNum; i++) {
                parent[i] = i;
            }

            int mininumCapacity = 0;
            for (int i = 0; i < routeNum; i++) {
                int fromParent = findParent(edgeList[i].from);
                int toParent = findParent(edgeList[i].to);

                if (fromParent != toParent) {
                    parent[fromParent] = toParent;
                    if (edgeList[i].cost > mininumCapacity) {
                        mininumCapacity = edgeList[i].cost;
                    }
                }
            }
            boolean possible = true;
            for (int i = 0; i < cityNum; i++) {
                if (findParent(i) != findParent(0))
                    possible = false;
            }

            if (possible)
                System.out.println(mininumCapacity);
            else
                System.out.println("IMPOSSIBLE");
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