import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class p01112{
    static int adjMatrix[][];
    static int nodeNum, targetNode, timeLimit;
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while(caseNum-->0){
            nodeNum = in.nextInt();
            targetNode = in.nextInt() - 1;
            timeLimit = in.nextInt();
            int edgeNum = in.nextInt();

            adjMatrix = new int[nodeNum][nodeNum];
            for(int i = 0; i < nodeNum; i++){
                Arrays.fill(adjMatrix[i], -1);
            }
            for(int i = 0; i < edgeNum; i++){
                int first = in.nextInt() - 1;
                int second = in.nextInt() - 1;
                adjMatrix[first][second] = in.nextInt();
            }

            int count = 0;
            for(int i = 0; i < nodeNum; i++){
                if(dijkstra(i))
                    count++;
            }

            System.out.println(count);
            if(caseNum != 0) System.out.println();
        }

        in.close();
    }

    static boolean dijkstra(int srcNode){
        int time[] = new int[nodeNum];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[srcNode] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(srcNode, 0));

        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(node.cost > time[node.index]) continue;

            for(int i = 0; i < nodeNum; i++){
                if(adjMatrix[node.index][i] == -1) continue;
                if(node.cost + adjMatrix[node.index][i] < time[i]){
                    time[i] = node.cost + adjMatrix[node.index][i];
                    pq.add(new Node(i, time[i]));
                }
            }
        }

        return time[targetNode] <= timeLimit;
    }
}

class Node implements Comparable<Node>{
    int index;
    int cost;
    
    Node(int index, int cost){
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}

class Edge implements Comparable<Edge> {
    int from, to, time;

    Edge(int from, int to, int time){
        this.from = from;
        this.to = to;
        this.time = time;
    }

    @Override
    public int compareTo(Edge o) {
        return time - o.time;
    }
}