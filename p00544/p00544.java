import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

class p00544 {
    static TreeMap<String, Integer> mapNameToIndex;

    static ArrayList<Edge> edgeList;
    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 0;
        while (in.hasNext()) {
            int cityNum = in.nextInt();
            int routeNum = in.nextInt();

            if (cityNum == 0 && routeNum == 0) {
                break;
            }
            mapNameToIndex = new TreeMap<String, Integer>();
            edgeList = new ArrayList<Edge>(routeNum);

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

                int maxLoad = in.nextInt();

                edgeList.add(new Edge(fromIndex, toIndex, maxLoad));
            }

            Collections.sort(edgeList);

            String beginCity = in.next();
            String endCity = in.next();

            if (!mapNameToIndex.containsKey(beginCity)) {
                mapNameToIndex.put(beginCity, count++);
            }
            if (!mapNameToIndex.containsKey(endCity)) {
                mapNameToIndex.put(endCity, count++);
            }

            int beginIndex = mapNameToIndex.get(beginCity);
            int endIndex = mapNameToIndex.get(endCity);

            // Calculate output
            // Kruskal Algorithm

            parent = new int[cityNum];
            for (int i = 0; i < cityNum; i++) {
                parent[i] = i;
            }

            int result = Integer.MAX_VALUE;
            for (int i = 0; i < routeNum; i++) {

                if (findParent(beginIndex) == findParent(endIndex)) {
                    break;
                }

                Edge e = edgeList.get(i);
                int from = e.from;
                int to = e.to;
                int cost = e.cost;

                if (findParent(from) != findParent(to)) {
                    parent[findParent(from)] = findParent(to);

                    if (cost < result)
                        result = cost;
                }
            }

            System.out.printf("Scenario #%d\n", ++caseCount);
            System.out.printf("%d tons\n\n", result);

        }

        in.close();
    }

    static int findParent(int index) {
        if (index == parent[index])
            return index;
        return parent[index] = findParent(parent[index]);
    }
}

class Edge implements Comparable<Edge> {
    int from, to, cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return o.cost - cost;
    }
}