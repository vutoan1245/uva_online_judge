import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class p11747 {
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int nodeNum = in.nextInt();
            int edgeNum = in.nextInt();

            if (nodeNum == 0 && edgeNum == 0)
                break;

            Edge edgeList[] = new Edge[edgeNum];
            for (int i = 0; i < edgeNum; i++) {
                edgeList[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
            }
            Arrays.sort(edgeList);

            parent = new int[nodeNum];
            for (int i = 0; i < nodeNum; i++) {
                parent[i] = i;
            }
            ArrayList<Integer> notIncluded = new ArrayList<Integer>();
            for (int i = 0; i < edgeNum; i++) {
                int fromParent = findParent(edgeList[i].from);
                int toParent = findParent(edgeList[i].to);

                if (fromParent != toParent) {
                    parent[fromParent] = toParent;
                } else {
                    notIncluded.add(edgeList[i].cost);
                }
            }

            Collections.sort(notIncluded);

            if (notIncluded.size() == 0)
                System.out.println("forest");
            else {

                for (int i = 0; i < notIncluded.size(); i++) {
                    if (i != 0)
                        System.out.print(" ");
                    System.out.print(notIncluded.get(i));

                }
                System.out.println();
            }
        }
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