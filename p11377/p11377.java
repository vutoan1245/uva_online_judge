import java.util.*;

class p11377 {
    static final int INF = 10000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        for (int k = 1; k <= caseNum; k++) {

            // Get input
            int cityNum = in.nextInt();
            int routeNum = in.nextInt();
            int airportNum = in.nextInt();

            boolean isAirport[] = new boolean[cityNum];
            for (int i = 0; i < airportNum; i++) {
                int c = in.nextInt() - 1;
                isAirport[c] = true;
            }

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < cityNum; i++) {
                adjList.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < routeNum; i++) {
                int from = in.nextInt() - 1;
                int to = in.nextInt() - 1;
                adjList.get(from).add(to);
                adjList.get(to).add(from);
            }

            int queryNum = in.nextInt();
            System.out.printf("Case %d:\n", k);
            for (int i = 0; i < queryNum; i++) {
                int src = in.nextInt() - 1;
                int dest = in.nextInt() - 1;

                if (src == dest) {
                    System.out.println(0);
                    continue;
                }

                // Dijkatra's Algorithm
                int currCost[] = new int[cityNum];
                for (int j = 0; j < cityNum; j++) {
                    currCost[j] = INF;
                }
                currCost[src] = isAirport[src] ? 0 : 1;

                PriorityQueue<Node> pq = new PriorityQueue<Node>();
                pq.add(new Node(src, currCost[src]));

                while (!pq.isEmpty()) {
                    Node n = pq.poll();
                    int from = n.from;
                    int cost = n.cost;

                    if (cost > currCost[from])
                        continue;

                    for (int j = 0; j < adjList.get(from).size(); j++) {
                        int to = adjList.get(from).get(j);

                        int newCost = cost + (isAirport[to] ? 0 : 1);
                        if (newCost < currCost[to]) {
                            currCost[to] = newCost;
                            pq.add(new Node(to, newCost));
                        }
                    }
                }

                System.out.println(currCost[dest] >= INF ? -1 : currCost[dest]);
            }

            System.out.println();
        }

        in.close();
    }

    static class Node implements Comparable<Node> {
        int from, cost;

        Node(int from, int cost) {
            this.from = from;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }
}