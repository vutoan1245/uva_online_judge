import java.util.*;

class p12047 {
    static final int INF = 100000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {

            // Get input
            int nodeNum = in.nextInt();
            int routeNum = in.nextInt();
            int src = in.nextInt() - 1;
            int dest = in.nextInt() - 1;
            int limit = in.nextInt();

            ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>(nodeNum);
            for (int i = 0; i < nodeNum; i++) {
                adjList.add(new ArrayList<Edge>());
            }

            for (int i = 0; i < routeNum; i++) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                int cost = in.nextInt();
                adjList.get(from).add(new Edge(to, cost));
            }

            // Dijkatra's Algorithm
            Node currCost[] = new Node[nodeNum];
            for (int i = 0; i < nodeNum; i++) {
                currCost[i] = new Node(i, INF, 0);
            }
            currCost[src].cost = 0;

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(src, 0, 0));

            while (!pq.isEmpty()) {
                Node n = pq.poll();
                int from = n.from;
                int cost = n.cost;
                int max = n.max;

                for (int i = 0; i < adjList.get(from).size(); i++) {
                    Edge next = adjList.get(from).get(i);

                    int to = next.to;
                    int newCost = cost + next.cost;
                    int newMax = Math.max(max, next.cost);

                    if (newCost <= limit && newMax > currCost[to].max) {
                        currCost[to].cost = newCost;
                        currCost[to].max = Math.max(newMax, currCost[to].max);

                        pq.add(new Node(to, newCost, newMax));
                    }
                }

            }

            if (currCost[dest].cost >= INF || currCost[dest].cost > limit) {
                System.out.println(-1);
            } else {
                System.out.println(currCost[dest].max);
            }
        }

        in.close();
    }

    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int from, cost, max;

        Node(int from, int cost, int max) {
            this.from = from;
            this.cost = cost;
            this.max = max;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}