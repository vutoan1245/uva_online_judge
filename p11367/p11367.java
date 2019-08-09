import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class p11367 {
    static int cityNum, fuelPrice[];
    static ArrayList<ArrayList<Edge>> adjList;

    static final int INF = 100 * 1000 * 10000;

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader((System.in)));

        // Get input
        String strArr[] = in.readLine().split(" ");
        cityNum = Integer.parseInt(strArr[0]);
        int routeNum = Integer.parseInt(strArr[1]);

        strArr = in.readLine().split(" ");
        fuelPrice = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            fuelPrice[i] = Integer.parseInt(strArr[i]);
        }

        adjList = new ArrayList<ArrayList<Edge>>(cityNum);
        for (int i = 0; i < cityNum; i++) {
            adjList.add(new ArrayList<Edge>(cityNum));
        }
        for (int i = 0; i < routeNum; i++) {
            strArr = in.readLine().split(" ");
            int from = Integer.parseInt(strArr[0]);
            int to = Integer.parseInt(strArr[1]);
            int length = Integer.parseInt(strArr[2]);

            adjList.get(from).add(new Edge(to, length));
            adjList.get(to).add(new Edge(from, length));
        }

        // Query
        int queryNum = Integer.parseInt(in.readLine());
        for (int i = 0; i < queryNum; i++) {
            strArr = in.readLine().split(" ");
            int capacity = Integer.parseInt(strArr[0]);
            int src = Integer.parseInt(strArr[1]);
            int dest = Integer.parseInt(strArr[2]);

            int ans = dijkstra(src, dest, capacity);
            if (ans == INF)
                System.out.println("impossible");
            else
                System.out.println(ans);

        }

        in.close();
    }

    static int dijkstra(int src, int dest, int capacity) {
        int[][] dist = new int[cityNum][capacity + 1];
        for (int i = 0; i < cityNum; i++)
            Arrays.fill(dist[i], INF);

        dist[src][0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(src, 0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.remove();
            if (curr.cost > dist[curr.from][curr.fuel])
                continue;

            for (int i = 0; i < adjList.get(curr.from).size(); i++) {
                Edge next = adjList.get(curr.from).get(i);

                if (next.length <= curr.fuel && curr.cost < dist[next.city][curr.fuel - next.length]) {
                    dist[next.city][curr.fuel - next.length] = curr.cost;
                    pq.add(new Node(next.city, curr.fuel - next.length, curr.cost));
                }
            }

            if (curr.fuel < capacity && curr.cost + fuelPrice[curr.from] < dist[curr.from][curr.fuel + 1]) {
                dist[curr.from][curr.fuel + 1] = curr.cost + fuelPrice[curr.from];
                pq.add(new Node(curr.from, curr.fuel + 1, curr.cost + fuelPrice[curr.from]));
            }

        }
        return dist[dest][0];
    }
}

class Node implements Comparable<Node> {
    int from, fuel, cost;

    Node(int x, int y, int z) {
        from = x;
        fuel = y;
        cost = z;
    }

    public int compareTo(Node o) {
        return cost - o.cost;
    }

}

class Edge {
    int city, length;

    Edge(int to, int length) {
        this.city = to;
        this.length = length;
    }
}