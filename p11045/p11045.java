import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class p11045 {
    static int shirtNum, volunteerNum, max, src, target;
    static int parent[], capacity[][];

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());

        while (caseNum-- > 0) {
            String strArr[] = in.readLine().split(" ");
            shirtNum = Integer.parseInt(strArr[0]);
            volunteerNum = Integer.parseInt(strArr[1]);

            max = volunteerNum + 6 + 2;
            src = 0;
            target = max - 1;
            capacity = new int[max][max];

            int shirtsPerSize = shirtNum / 6;
            for (int i = 1; i <= 6; i++) {
                capacity[0][i] = shirtsPerSize;
            }

            for (int i = 0; i < volunteerNum; i++) {
                strArr = in.readLine().split(" ");
                for (String s : strArr) {
                    int volunteerIndex = i + 6 + 1;
                    int shirtIndex = sizeToIndex(s) + 1;
                    capacity[shirtIndex][volunteerIndex] = 1;
                    capacity[volunteerIndex][target] = 1;

                }
            }

            // Edmonds Kark's Algorithm
            int maxFlow = 0;
            while (bfs()) {
                maxFlow += augment(target, Integer.MAX_VALUE);
            }

            if (maxFlow == volunteerNum) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        in.close();
    }

    static int augment(int index, int currFlow) {
        if (index == src)
            return currFlow;
        int from = parent[index];
        int flow = augment(from, Math.min(currFlow, capacity[from][index]));
        capacity[from][index] -= flow;
        capacity[index][from] += flow;
        return flow;
    }

    static boolean bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(src);

        parent = new int[max];
        for (int i = 0; i < max; i++) {
            parent[i] = -1;
        }

        boolean visited[] = new boolean[max];
        visited[src] = true;

        while (!q.isEmpty()) {
            int from = q.poll();

            for (int to = 0; to < max; to++) {
                if (!visited[to] && capacity[from][to] > 0) {
                    visited[to] = true;
                    parent[to] = from;
                    if (to == target)
                        return true;
                    q.add(to);
                }
            }
        }

        return false;
    }

    static int sizeToIndex(String s) {
        switch (s) {
        case "XXL":
            return 0;
        case "XL":
            return 1;
        case "L":
            return 2;
        case "M":
            return 3;
        case "S":
            return 4;
        case "XS":
            return 5;
        }

        return -1;
    }
}