import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


class p10278{
    public static void main(String args[]) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());
        in.readLine();

        for(int i = 0; i < caseNum; i++){
            if(i != 0) System.out.println();
            String strArr[] = in.readLine().split(" ");
            int fireStationNum = Integer.parseInt(strArr[0]);
            int nodeNum = Integer.parseInt(strArr[1]);

            boolean fireStation[] = new boolean[nodeNum];
            // strArr = in.readLine().split(" ");
            for(int j = 0; j < fireStationNum; j++){
                fireStation[Integer.parseInt(in.readLine())-1] = true;
            }

            // Create an adj list of graph
            ArrayList<ArrayList<Edge>> edgeList = new ArrayList<ArrayList<Edge>>(nodeNum);
            for(int j = 0; j < nodeNum; j++){
                edgeList.add(new ArrayList<Edge>(nodeNum));
            }
            String line;
            while((line = in.readLine())!= null && !line.equals("")){
                strArr = line.split(" ");
                int node1 = Integer.parseInt(strArr[0]) - 1;
                int node2 = Integer.parseInt(strArr[1]) - 1;
                int cost = Integer.parseInt(strArr[2]);

                edgeList.get(node1).add(new Edge(node2, cost));
                edgeList.get(node2).add(new Edge(node1, cost));
            }

            // Calculate output
            int resultNode = 0, resultPath = Integer.MAX_VALUE;
            // Put a fire station in every intersection and calculate maximum distance
            for(int j = 0; j < nodeNum; j++){

                if(fireStation[j]) continue;

                int minPath[] = new int[nodeNum];
                Arrays.fill(minPath, Integer.MAX_VALUE);

                PriorityQueue<Node> pq = new PriorityQueue<Node>();
                pq.add(new Node(j, 0));
                minPath[j] = 0;
                for(int k = 0; k < nodeNum; k++){
                    if(fireStation[k]){
                        pq.add(new Node(k, 0));
                        minPath[k] = 0;
                    }
                }

                while(!pq.isEmpty()){
                    Node node = pq.poll();
                    if(node.cost > minPath[node.pos]) continue;
                    for(Edge e: edgeList.get(node.pos)){
                        if(e.cost + node.cost < minPath[e.to]){
                            minPath[e.to] = e.cost + node.cost;
                            pq.add(new Node(e.to, e.cost + node.cost));
                        }
                    }
                }

                int currentMax = 0;
                for(int k = 0; k < nodeNum; k++){
                    currentMax = Math.max(minPath[k], currentMax);
                }

                if(currentMax < resultPath){
                    resultPath = currentMax;
                    resultNode = j;
                }
            }

            System.out.println(resultNode + 1);
        }

        in.close();
    }
}

class Edge implements Comparable<Edge>{
    int to;
    int cost;
    Edge(int to, int cost){
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}

class Node implements Comparable<Node>{
    int pos;
    int cost;

    Node(int pos, int cost){
        this.pos = pos;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}