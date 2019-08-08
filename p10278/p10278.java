import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class p10278 {
    static int fireStationNum, intersectionNum;
    static boolean isFireStation[];
    static ArrayList<ArrayList<Edge>> adjList;

    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());

        in.readLine();
        while (caseNum-- > 0) {
            String strArr[] = in.readLine().split(" ");
            fireStationNum = Integer.parseInt(strArr[0]);
            intersectionNum = Integer.parseInt(strArr[1]);

            isFireStation = new boolean[intersectionNum];
            for (int i = 0; i < fireStationNum; i++) {
                int index = Integer.parseInt(in.readLine()) - 1;
                isFireStation[index] = true;
            }

            adjList = new ArrayList<ArrayList<Edge>>(intersectionNum);
            for (int i = 0; i < intersectionNum; i++) {
                adjList.add(new ArrayList<Edge>());
            }

            String line;
            while ((line = in.readLine()) != null && !line.equals("")) {
                strArr = line.split(" ");
                int from = Integer.parseInt(strArr[0]) - 1;
                int to = Integer.parseInt(strArr[1]) - 1;
                int cost = Integer.parseInt(strArr[2]);

                adjList.get(from).add(new Edge(to, cost));
                adjList.get(to).add(new Edge(from, cost));
            }

            int result = Integer.MAX_VALUE, resultIndex = 0;
            for (int i = 0; i < intersectionNum; i++) {
                if (isFireStation[i])
                    continue;
                int holder = diskatra(i);
                if (holder < result) {
                    result = holder;
                    resultIndex = i;
                }
            }

            System.out.println(resultIndex + 1);
            if (caseNum != 0)
                System.out.println();
        }

        in.close();
    }

    static int diskatra(int index) {

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(index, 0));

        int cost[] = new int[intersectionNum];
        for (int i = 0; i < intersectionNum; i++) {
            if (isFireStation[i]) {
                cost[i] = 0;
                pq.add(new Node(i, 0));
            } else {
                cost[i] = Integer.MAX_VALUE;
            }
        }
        cost[index] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.currCost > cost[curr.from]) {
                continue;
            }

            for (int i = 0; i < adjList.get(curr.from).size(); i++) {
                Edge next = adjList.get(curr.from).get(i);
                if (curr.currCost + next.cost < cost[next.to]) {
                    cost[next.to] = curr.currCost + next.cost;
                    pq.add(new Node(next.to, cost[next.to]));
                }
            }
        }

        int result = cost[0];
        for (int i = 0; i < intersectionNum; i++) {
            result = Math.max(result, cost[i]);
        }

        return result;
    }
}

class Edge {
    int to, cost;

    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

class Node implements Comparable<Node> {
    int from, currCost;

    Node(int from, int currCost) {
        this.from = from;
        this.currCost = currCost;
    }

    public int compareTo(Node o) {
        return currCost - o.currCost;
    }
}