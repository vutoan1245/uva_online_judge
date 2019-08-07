import java.util.Arrays;
import java.util.Scanner;

public class p01235 {
    static int parent[];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            int keyNum = in.nextInt(), keyList[] = new int[keyNum];
            for (int i = 0; i < keyNum; ++i)
                keyList[i] = in.nextInt();

            int ans = 10000, k = 0;
            Edge[] edgeList = new Edge[keyNum * (keyNum - 1) >> 1];
            for (int i = 0; i < keyNum; ++i) {
                ans = Math.min(ans, getDist(0, keyList[i]));
                for (int j = i + 1; j < keyNum; ++j)
                    edgeList[k++] = new Edge(i, j, getDist(keyList[i], keyList[j]));
            }
            Arrays.sort(edgeList);

            parent = new int[keyNum];
            for (int i = 0; i < keyNum; i++) {
                parent[i] = i;
            }

            for (Edge e : edgeList) {
                int fromParent = findParent(e.from);
                int toParent = findParent(e.to);

                if (fromParent != toParent) {
                    parent[fromParent] = toParent;
                    ans += e.cost;
                }
            }

            System.out.println(ans);
        }
        in.close();
    }

    static int findParent(int index) {
        if (index == parent[index])
            return index;
        return parent[index] = findParent(parent[index]);
    }

    static int getDist(int x, int y) {
        int ans = 0;
        for (int i = 0; i < 4; ++i, x /= 10, y /= 10) {
            int d = Math.abs(x % 10 - y % 10);
            ans += Math.min(d, 10 - d);
        }
        return ans;
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int a, int b, int c) {
            from = a;
            to = b;
            cost = c;
        }

        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }

}