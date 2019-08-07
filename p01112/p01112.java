import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class p01112 {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());

        while (caseNum-- > 0) {
            in.readLine();
            int cellNum = Integer.parseInt(in.readLine());
            int exit = Integer.parseInt(in.readLine()) - 1;
            int timeLimit = Integer.parseInt(in.readLine());
            int edgeNum = Integer.parseInt(in.readLine());

            ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>();
            for (int i = 0; i < cellNum; i++) {
                adjList.add(new ArrayList<Edge>());
            }

            for (int i = 0; i < edgeNum; i++) {
                String strArr[] = in.readLine().split(" ");
                int from = Integer.parseInt(strArr[0]) - 1;
                int to = Integer.parseInt(strArr[1]) - 1;
                int cost = Integer.parseInt(strArr[2]);

                adjList.get(from).add(new Edge(to, cost));
            }

            int result = 0;
            for (int i = 0; i < cellNum; i++) {
                int currTime[] = new int[cellNum];
                for (int j = 0; j < cellNum; j++) {
                    currTime[j] = INF;
                }
                currTime[i] = 0;

                PriorityQueue<Node> pq = new PriorityQueue<Node>();
                pq.add(new Node(i, 0));

                while (!pq.isEmpty()) {
                    Node n = pq.poll();

                    if (n.time > timeLimit) {
                        break;
                    }
                    if (n.from == exit) {
                        result++;
                        break;
                    }

                    for (int j = 0; j < adjList.get(n.from).size(); j++) {
                        Edge next = adjList.get(n.from).get(j);
                        if (n.time + next.time < currTime[next.to]) {
                            currTime[next.to] = n.time + next.time;
                            pq.add(new Node(next.to, currTime[next.to]));
                        }
                    }
                }
            }

            System.out.println(result);
            if (caseNum != 0)
                System.out.println();

        }
    }
}

class Node implements Comparable<Node> {
    int from, time;

    Node(int from, int cost) {
        this.from = from;
        this.time = cost;
    }

    public int compareTo(Node o) {
        return time - o.time;
    }
}

class Edge {
    int to, time;

    Edge(int to, int cost) {
        this.to = to;
        this.time = cost;
    }
}