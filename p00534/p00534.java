import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class p00534 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (true) {
            int stoneNum = in.nextInt();
            if (stoneNum == 0)
                break;

            Point stoneList[] = new Point[stoneNum];
            for (int i = 0; i < stoneNum; i++) {
                stoneList[i] = new Point(in.nextInt(), in.nextInt());
            }

            ArrayList<Edge> edgeList = new ArrayList<Edge>();
            for (int i = 0; i < stoneNum; i++) {
                for (int j = 0; j < i; j++) {
                    edgeList.add(new Edge(i, j, distance(stoneList[i], stoneList[j])));
                }
            }
            Collections.sort(edgeList);

            parent = new int[stoneNum];
            for (int i = 0; i < stoneNum; i++) {
                parent[i] = i;
            }

            double result = 0;
            for (Edge e : edgeList) {
                int fromParent = findParent(e.from);
                int toParent = findParent(e.to);

                if (fromParent != toParent) {
                    parent[fromParent] = toParent;
                }

                if (findParent(0) == findParent(1)) {
                    result = Math.sqrt(e.cost);
                    break;
                }
            }

            System.out.printf("Scenario #%d\n", caseCount++);
            System.out.printf("Frog Distance = %.3f\n\n", result);
        }

        in.close();

    }

    static int findParent(int index) {
        if (index == parent[index])
            return index;
        return parent[index] = findParent(parent[index]);
    }

    static int distance(Point first, Point second) {
        int x = Math.abs(first.x - second.x);
        int y = Math.abs(first.y - second.y);
        return x * x + y * y;
    }
}

class Edge implements Comparable<Edge> {
    int from, to;
    int cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}