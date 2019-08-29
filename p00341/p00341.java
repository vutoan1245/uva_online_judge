import java.util.*;

class p00341 {
    static final int INF = 1000000;
    static int n, src, dest, parent[], dist[];
    static ArrayList<ArrayList<Edge>> adjList;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while ((n = in.nextInt()) != 0) {
            adjList = new ArrayList<ArrayList<Edge>>(n);
            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<Edge>(n));
            }

            for (int i = 0; i < n; i++) {
                int m = in.nextInt();
                while (m-- > 0) {
                    adjList.get(i).add(new Edge(i, in.nextInt() - 1, in.nextInt()));
                }
            }

            src = in.nextInt() - 1;
            dest = in.nextInt() - 1;

            diskatra();

            System.out.printf("Case %d: Path =", caseCount++);
            int d = dest;
            String s = "";
            while (parent[d] != d) {
                s = " " + (d + 1) + s;
                d = parent[d];
            }
            s = " " + (src + 1) + s;
            System.out.print(s);

            System.out.printf("; %d second delay\n", dist[dest]);
        }

        in.close();
    }

    static void diskatra() {
        dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
        }
        dist[src] = 0;

        parent = new int[n];
        parent[src] = src;

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            int from = curr.from;
            int currCost = curr.cost;

            if (currCost < dist[from])
                continue;

            for (int i = 0; i < adjList.get(from).size(); i++) {
                Edge next = adjList.get(from).get(i);

                int newCost = currCost + next.cost;
                if (newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.add(new Node(next.to, newCost));
                    parent[next.to] = from;
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int from, cost;

    Node(int from, int currCost) {
        this.from = from;
        this.cost = currCost;
    }

    public int compareTo(Node o) {
        return cost - o.cost;
    }
}

class Edge {
    int from, to, cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

}