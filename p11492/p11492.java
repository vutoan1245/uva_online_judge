import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

class p11492 {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int routeNum;

        while ((routeNum = in.nextInt()) != 0) {
            TreeMap<String, Integer> mapNameToIndex = new TreeMap<String, Integer>();
            ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>(5000);
            for (int i = 0; i < 5000; i++) {
                adjList.add(new ArrayList<Node>());
            }

            int count = 0;
            String src = in.next();
            if (!mapNameToIndex.containsKey(src)) {
                mapNameToIndex.put(src, count++);
            }
            String dest = in.next();
            if (!mapNameToIndex.containsKey(dest)) {
                mapNameToIndex.put(dest, count++);
            }

            int srcIndex = mapNameToIndex.get(src);
            int destIndex = mapNameToIndex.get(dest);

            for (int i = 0; i < routeNum; i++) {
                String from = in.next();
                String to = in.next();

                if (!mapNameToIndex.containsKey(from)) {
                    mapNameToIndex.put(from, count++);
                }
                if (!mapNameToIndex.containsKey(to)) {
                    mapNameToIndex.put(to, count++);
                }

                int fromIndex = mapNameToIndex.get(from);
                int toIndex = mapNameToIndex.get(to);

                String word = in.next();

                adjList.get(fromIndex).add(new Node(toIndex, word.length(), word.charAt(0) - 'a'));
                adjList.get(toIndex).add(new Node(fromIndex, word.length(), word.charAt(0) - 'a'));
            }

            // Diskatra Algorithm
            int[][] dist = new int[count][26];
            PriorityQueue<Node> q = new PriorityQueue<Node>();

            for (int i = 0; i < count; i++)
                Arrays.fill(dist[i], INF);

            for (int i = 0; i < 26; i++) {
                dist[srcIndex][i] = 0;
                q.add(new Node(srcIndex, 0, i));
            }

            while (!q.isEmpty()) {
                Node curr = q.remove();

                if (curr.length > dist[curr.lang][curr.c])
                    continue;

                for (int i = 0; i < adjList.get(curr.lang).size(); i++) {
                    Node next = adjList.get(curr.lang).get(i);

                    if (next.c != curr.c && curr.length + next.length < dist[next.lang][next.c]) {
                        dist[next.lang][next.c] = curr.length + next.length;
                        q.add(new Node(next.lang, dist[next.lang][next.c], next.c));
                    }
                }
            }
            int result = INF;
            for (int i = 0; i < 26; i++)
                result = Math.min(result, dist[destIndex][i]);

            if (result == INF)
                System.out.println("impossivel");
            else
                System.out.println(result);

        }

        in.close();
    }
}

class Node implements Comparable<Node> {
    int lang, length, c;

    Node(int lang, int legnth, int c) {
        this.lang = lang;
        this.length = legnth;
        this.c = c;
    }

    public int compareTo(Node o) {
        return length - o.length;
    }
}