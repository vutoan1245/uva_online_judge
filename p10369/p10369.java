import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class p10369 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {

            // Get input
            int satelliteNum = in.nextInt();
            int outpostNum = in.nextInt();

            Point pointList[] = new Point[outpostNum];
            for (int i = 0; i < outpostNum; i++) {
                pointList[i] = new Point(in.nextInt(), in.nextInt());
            }

            // Calculate output
            ArrayList<Edge> edgeList = new ArrayList<Edge>(outpostNum * outpostNum);

            for (int i = 0; i < outpostNum; i++) {
                for (int j = 0; j < outpostNum; j++) {
                    if (i == j)
                        continue;

                    edgeList.add(new Edge(i, j, findDistance(pointList[i], pointList[j])));
                }
            }
            Collections.sort(edgeList);

            // Kruskal Algorithm
            parent = new int[outpostNum];
            for (int i = 0; i < outpostNum; i++) {
                parent[i] = i;
            }

            ArrayList<Double> costList = new ArrayList<Double>(outpostNum);
            for (int i = 0; i < edgeList.size(); i++) {
                Edge e = edgeList.get(i);

                int from = e.from;
                int to = e.to;
                int fromParent = findParent(from);
                int toParent = findParent(to);

                if (fromParent == toParent)
                    continue;

                parent[fromParent] = toParent;
                costList.add(e.cost);
            }

            Collections.sort(costList);

            // Print output
            System.out.printf("%.2f\n", costList.get(costList.size() - satelliteNum));

        }

        in.close();
    }

    static int findParent(int index) {
        if (index == parent[index])
            return index;
        return parent[index] = findParent(parent[index]);
    }

    static double findDistance(Point first, Point second) {
        return Math.hypot(first.x - second.x, first.y - second.y);
    }
}

class Edge implements Comparable<Edge> {
    int from, to;
    double cost;

    Edge(int from, int to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(cost, o.cost);
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}