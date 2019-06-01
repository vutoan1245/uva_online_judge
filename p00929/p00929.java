import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class p00929{
    public static void main(String args[]) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());
        for(int i = 0; i < caseNum; i++){
            int rowNum = Integer.parseInt(in.readLine());
            int colNum = Integer.parseInt(in.readLine());
            int graph[][] = new int[rowNum][colNum];
            int result[][] = new int[rowNum][colNum];
            for(int j = 0; j < rowNum; j++){
                String strArr[] = in.readLine().split(" ");
                for(int k = 0; k < colNum; k++){
                    graph[j][k] = Integer.parseInt(strArr[k]);
                    result[j][k] = Integer.MAX_VALUE;
                }
            }

            // Calculate output
            int[] dx = new int[]{-1, 0, 0, 1};
            int[] dy = new int[]{0, -1, 1, 0};
            result[0][0] = graph[0][0];

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.offer(new Node(0, 0, graph[0][0]));

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                if (node.row == rowNum - 1 && node.col == colNum - 1) {
                    System.out.println(result[rowNum - 1][colNum - 1]);
                    break;
                }
                for (int k = 0; k < 4; k++) {
                    int r = node.row + dx[k];
                    int c = node.col + dy[k];
                    if (r >= 0 && r < rowNum && c >= 0 && c < colNum && graph[r][c] + result[node.row][node.col] < result[r][c]) {
                        result[r][c] = graph[r][c] + result[node.row][node.col];
                        pq.add(new Node(r, c, result[r][c]));
                    }
                }
            }
        }

        in.close();
    }
}

class Node implements Comparable<Node> {
    int cost, row, col;
    
    public Node(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }
    
    public int compareTo(Node o) {
        return cost - o.cost;
    }
}