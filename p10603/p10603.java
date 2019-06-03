import java.util.*;
import java.io.*;

class p10603 {
    static int maxSize[], target;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String args[]) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());
        while(caseNum-->0){
            String strArr[] = in.readLine().split(" ");
            maxSize = new int[3];
            maxSize[0] = Integer.parseInt(strArr[0]);
            maxSize[1] = Integer.parseInt(strArr[1]);
            maxSize[2] = Integer.parseInt(strArr[2]);
            target = Integer.parseInt(strArr[3]);

            int currentCost[][] = new int[maxSize[0]+1][maxSize[1]+1];
            for(int i = 0; i <= maxSize[0]; i++){
                Arrays.fill(currentCost[i], INF);
            }
            int result[] = new int[maxSize[2]+1];
            Arrays.fill(result, INF);

            // Dijkstra's algorithm
            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(0, 0, 0));
            while(!pq.isEmpty()){
                Node n = pq.poll();

                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        if(i == j) continue;

                        int pug[] = new int[]{n.a, n.b, maxSize[2] - n.a - n.b};
                        int newCost = n.cost + pour(i, j, pug);

                        if(newCost < currentCost[pug[0]][pug[1]]){
                            currentCost[pug[0]][pug[1]] = newCost;
                            result[pug[0]] = Math.min(result[pug[0]], newCost);
                            result[pug[1]] = Math.min(result[pug[1]], newCost);
                            result[pug[2]] = Math.min(result[pug[2]], newCost);

                            pq.add(new Node(pug[0], pug[1], newCost));
                        }

                    }
                }
            }

            while(result[target] == INF) target--;
            System.out.println(result[target] + " " + target);
        }
    }

    static int pour(int from, int to, int pug[]){
        int result = 0;
        if(pug[from] <= maxSize[to] - pug[to]){
            result = pug[from];
            pug[to] += pug[from];
            pug[from] = 0;
        } else {
            result = maxSize[to] - pug[to];
            pug[from] -= result;
            pug[to] += result;
        }

        return result;
    }
}

class Node implements Comparable<Node>{
    int a, b, cost;

    Node(int i, int j, int cost){
        this.a = i;
        this.b = j;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}