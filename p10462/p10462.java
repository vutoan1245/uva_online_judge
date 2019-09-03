import java.util.*;

class p10462 {
    static int nodeNum, edgeNum;
    static ArrayList<Edge> list;
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        for (int t = 1; t <= caseNum; t++) {
            nodeNum = in.nextInt();
            edgeNum = in.nextInt();
            list = new ArrayList<Edge>(edgeNum);

            for (int i = 0; i < edgeNum; i++) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                int cost = in.nextInt();
                list.add(new Edge(from, to, cost));
            }

            Collections.sort(list);

            // Kruskal's Algorithm
            parent = new int[nodeNum];
            for (int i = 0; i < nodeNum; i++) {
                parent[i] = i;
            }

            boolean taken[] = new boolean[edgeNum];
            int count = 0;
            for (int i = 0; i < edgeNum; i++) {
                Edge curr = list.get(i);
                int fromParent = findParent(curr.from);
                int toParent = findParent(curr.to);

                if (fromParent != toParent) {
                    parent[fromParent] = toParent;
                    taken[i] = true;
                    count++;
                }
            }

            System.out.printf("Case #%d : ", t);
            if (count != nodeNum - 1) {
                System.out.println("No way");
                continue;
            }

            int second = Integer.MAX_VALUE;
            for (int i = 0; i < edgeNum; i++) {
                if (taken[i]) {
                    parent = new int[nodeNum];
                    for (int j = 0; j < nodeNum; j++) {
                        parent[j] = j;
                    }

                    count = 0;
                    int sum = 0;
                    for (int j = 0; j < edgeNum; j++) {
                        if (j == i)
                            continue;
                        Edge curr = list.get(j);
                        int fromParent = findParent(curr.from);
                        int toParent = findParent(curr.to);

                        if (fromParent != toParent) {
                            parent[fromParent] = toParent;
                            count++;
                            sum += curr.cost;
                        }
                    }

                    if (count == nodeNum - 1) {
                        second = Math.min(second, sum);
                    }
                }
            }

            if (second != Integer.MAX_VALUE) {
                System.out.println(second);
            } else {
                System.out.println("No second way");
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

    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}