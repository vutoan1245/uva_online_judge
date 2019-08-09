import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

class p10187 {
    static final int INF = 1000 * 1000 * 24;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        int caseCount = 1;

        while (caseNum-- > 0) {
            System.out.printf("Test Case %d.\n", caseCount++);

            int routeNum = in.nextInt();
            TreeMap<String, Integer> mapNameToIndex = new TreeMap<String, Integer>();
            ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>(200);
            for (int i = 0; i < 200; i++) {
                adjList.add(new ArrayList<Node>());
            }
            int count = 0;

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

                int startTime = in.nextInt();
                int duration = in.nextInt();

                // Validate time
                int arr = (startTime + duration) % 24;
                if (startTime > 6 && startTime < 18 || arr > 6 && arr < 18)
                    continue;
                int a = startTime <= 6 ? startTime + 24 : startTime;
                int b = arr <= 6 ? arr + 24 : arr;
                if (b - a < 0)
                    continue;

                adjList.get(fromIndex).add(new Node(toIndex, startTime % 24, duration));
            }

            String src = in.next();
            String dest = in.next();
            if (!mapNameToIndex.containsKey(src) || !mapNameToIndex.containsKey(dest)) {
                System.out.println("There is no route Vladimir can take.");
                continue;
            }

            int srcIndex = mapNameToIndex.get(src);
            int destIndex = mapNameToIndex.get(dest);

            // Diskatra Algorithm
            int[] time = new int[count];
            Arrays.fill(time, INF);
            time[srcIndex] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<Node>();
            pq.add(new Node(srcIndex, 0, -1));
            while (!pq.isEmpty()) {

                Node n = pq.poll();
                if (n.currTime > time[n.index])
                    continue;

                int minStartTime = (n.currTime % 12 + 18) % 24;
                if (minStartTime <= 6)
                    minStartTime += 24;

                for (int i = 0, size = adjList.get(n.index).size(); i < size; i++) {
                    Node next = adjList.get(n.index).get(i);
                    int nextStartTime = next.currTime;
                    if (nextStartTime <= 6)
                        nextStartTime += 24;
                    if (minStartTime > nextStartTime)
                        nextStartTime += 12;
                    if (n.currTime + nextStartTime - minStartTime + next.duration < time[next.index]) {
                        time[next.index] = n.currTime + nextStartTime - minStartTime + next.duration;
                        pq.add(new Node(next.index, time[next.index], -1));
                    }

                }

            }

            if (time[destIndex] == INF)
                System.out.println("There is no route Vladimir can take.");
            else
                System.out.printf("Vladimir needs %d litre(s) of blood.\n", (time[destIndex] - 1) / 12);

        }

        in.close();
    }
}

class Node implements Comparable<Node> {
    int index, currTime, duration;

    Node(int index, int currTime, int duration) {
        this.index = index;
        this.currTime = currTime;
        this.duration = duration;
    }

    public int compareTo(Node t) {
        return currTime - t.currTime;
    }
}