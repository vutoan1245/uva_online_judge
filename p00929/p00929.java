import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class p00929 {
    static final int POS_INF = 1000 * 1000 * 10;

    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());

        String strArr[];
        while (caseNum-- > 0) {
            int rowNum = Integer.parseInt(in.readLine());
            int colNum = Integer.parseInt(in.readLine());

            int matrix[][] = new int[rowNum][colNum];
            int currCost[][] = new int[rowNum][colNum];
            for (int i = 0; i < rowNum; i++) {
                strArr = in.readLine().split(" ");
                for (int j = 0; j < colNum; j++) {
                    matrix[i][j] = Integer.parseInt(strArr[j]);
                    currCost[i][j] = POS_INF;

                }
            }

            currCost[0][0] = matrix[0][0];

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(0, 0, currCost[0][0]));

            int result = 0;
            while (!pq.isEmpty()) {
                Node n = pq.poll();
                if (n.i == rowNum - 1 && n.j == colNum - 1) {
                    result = n.cost;
                    break;
                }

                // Top
                if (n.i - 1 >= 0) {
                    int i = n.i - 1;
                    int j = n.j;
                    if (n.cost + matrix[i][j] < currCost[i][j]) {
                        currCost[i][j] = n.cost + matrix[i][j];
                        pq.add(new Node(i, j, currCost[i][j]));
                    }
                }
                // Right
                if (n.j + 1 < colNum) {
                    int i = n.i;
                    int j = n.j + 1;
                    if (n.cost + matrix[i][j] < currCost[i][j]) {
                        currCost[i][j] = n.cost + matrix[i][j];
                        pq.add(new Node(i, j, currCost[i][j]));
                    }
                }
                // Bottom
                if (n.i + 1 < rowNum) {
                    int i = n.i + 1;
                    int j = n.j;
                    if (n.cost + matrix[i][j] < currCost[i][j]) {
                        currCost[i][j] = n.cost + matrix[i][j];
                        pq.add(new Node(i, j, currCost[i][j]));
                    }
                }
                // Left
                if (n.j - 1 >= 0) {
                    int i = n.i;
                    int j = n.j - 1;
                    if (n.cost + matrix[i][j] < currCost[i][j]) {
                        currCost[i][j] = n.cost + matrix[i][j];
                        pq.add(new Node(i, j, currCost[i][j]));
                    }
                }
            }

            System.out.println(result);
        }

        in.close();
    }
}

class Node implements Comparable<Node> {
    int i, j, cost;

    Node(int i, int j, int cost) {
        this.i = i;
        this.j = j;
        this.cost = cost;
    }

    public int compareTo(Node o) {
        return cost - o.cost;
    }
}