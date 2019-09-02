import java.util.*;

class p10356 {
    static final int INF = 1000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (in.hasNext()) {
            // Get input
            int nodeNum = in.nextInt();
            int routeNum = in.nextInt();

            ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>(nodeNum);

            for (int i = 0; i < nodeNum; i++) {
                adjList.add(new ArrayList<Edge>());
            }

            for (int i = 0; i < routeNum; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                int cost = in.nextInt();

                adjList.get(from).add(new Edge(to, cost));
                adjList.get(to).add(new Edge(from, cost));
            }

            // Dijkatra's Algorithm
            int dist[][] = new int[2][nodeNum];
            for (int i = 0; i < nodeNum; i++) {
                dist[0][i] = INF;
                dist[1][i] = INF;
            }

            dist[0][0] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(0, 0, 0));

            while (!pq.isEmpty()) {
                Node n = pq.poll();
                int from = n.from;

                for (int i = 0, size = adjList.get(from).size(); i < size; i++) {
                    Edge next = adjList.get(from).get(i);
                    int to = next.to;
                    int riding = (n.riding + 1) % 2;

                    int newCost = n.cost + next.cost;
                    if (newCost < dist[riding][to]) {
                        dist[riding][to] = newCost;
                        pq.add(new Node(to, newCost, riding));
                    }
                }
            }

            System.out.printf("Set #%d\n", caseCount++);
            System.out.println(dist[0][nodeNum - 1] >= INF ? "?" : dist[0][nodeNum - 1]);
        }

        in.close();
    }

    static class Node implements Comparable<Node> {
        int from, cost, riding;

        Node(int from, int cost, int riding) {
            this.from = from;
            this.cost = cost;
            this.riding = riding;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}