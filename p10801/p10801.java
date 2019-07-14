import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class p10801 {
    static int elevatorNum, targetFloor, elevatorTime[];
    static int adjMatrix[][];
    final static int INF = Integer.MAX_VALUE;

    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while((line = in.readLine()) != null){
            String strArr[] = line.split(" ");
            elevatorNum = Integer.parseInt(strArr[0]);
            targetFloor = Integer.parseInt(strArr[1]);

            elevatorTime = new int[elevatorNum];
            strArr = in.readLine().split(" ");
            for(int i = 0; i < elevatorNum; i++){
                elevatorTime[i] = Integer.parseInt(strArr[i]);
            }

            adjMatrix = new int[elevatorNum][100];
            for(int i = 0; i < elevatorNum; i++){
                Arrays.fill(adjMatrix[i], -1);
            }

            for(int i = 0; i < elevatorNum; i++){
                strArr = in.readLine().split(" ");
                for(String str : strArr){
                    adjMatrix[i][Integer.parseInt(str)] = 1;
                }
            }
            
            int time[] = new int[100];
            Arrays.fill(time, INF);
            time[0] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(0, 0));

            while(!pq.isEmpty()){
                Node n = pq.poll();

                if(n.time > time[n.floor]) continue;

                for(int i = 0; i < elevatorNum; i++){
                    if(adjMatrix[i][n.floor] == -1) continue;

                    for(int j = 0; j < 100; j++){
                        if(adjMatrix[i][j] == -1 || n.floor == j) continue;

                        int waitTime = n.floor == 0 && n.time == 0 ? 0 : 60;
                        int newTime = n.time + waitTime + elevatorTime[i] * Math.abs(n.floor - j);
                        if(newTime < time[j]){
                            time[j] = newTime;
                            pq.add(new Node(j, newTime));
                        }
                    }
                }
            }

            int result = time[targetFloor];
            if(result == INF)
                System.out.println("IMPOSSIBLE");
            else
                System.out.println(result);

        }

        in.close();
    }
}

class Node implements Comparable<Node>{
    int floor, time;
    Node(int floor, int time){
        this.floor = floor;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        return time - o.time;
    }
}