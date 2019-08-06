import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class p11228 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        int caseCount = 1;
        while (caseNum-- > 0) {
            int cityNum = in.nextInt();
            double threshold = in.nextDouble();

            Point cityList[] = new Point[cityNum];
            for (int i = 0; i < cityNum; i++) {
                cityList[i] = new Point(in.nextInt(), in.nextInt());
            }

            ArrayList<Edge> edgeList = new ArrayList<Edge>();
            for (int i = 1; i < cityNum; i++) {
                for (int j = 0; j < i; j++) {
                    edgeList.add(new Edge(i, j, distance(cityList[i], cityList[j])));
                }
            }
            Collections.sort(edgeList);

            parent = new int[cityNum];
            for (int i = 0; i < cityNum; i++) {
                parent[i] = i;
            }

            int stateNum = cityNum;
            int size = edgeList.size();
            double routeDistance = 0, railDistance = 0;
            for (int i = 0; i < size; i++) {

                int fromParent = findParent(edgeList.get(i).from);
                int toParent = findParent(edgeList.get(i).to);

                if (fromParent != toParent) {
                    if (threshold >= edgeList.get(i).cost) {
                        parent[fromParent] = toParent;
                        routeDistance += (edgeList.get(i).cost);
                        stateNum--;
                    } else {
                        parent[fromParent] = toParent;
                        railDistance += (edgeList.get(i).cost);
                    }

                }
            }

            System.out.printf("Case #%d: %d %d %d\n", caseCount++, stateNum, (int) Math.round(routeDistance),
                    (int) Math.round(railDistance));
        }

        in.close();
    }

    static int findParent(int index) {
        if (index == parent[index])
            return index;
        return parent[index] = findParent(parent[index]);
    }

    static double distance(Point first, Point second) {
        int x = first.x - second.x;
        int y = first.y - second.y;
        return Math.hypot(x, y);
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int compareTo(Edge o) {
        return Double.compare(cost, o.cost);
    }
}