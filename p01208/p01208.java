import java.util.*;

class p01208 {

    static int parent[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = Integer.parseInt(in.nextLine());
        for (int i = 1; i <= caseNum; i++) {

            // Get input
            int nodeNum = Integer.parseInt(in.nextLine());

            ArrayList<Edge> list = new ArrayList<Edge>(nodeNum * nodeNum);

            for (int j = 0; j < nodeNum; j++) {
                String strArr[] = in.nextLine().split(",");
                for (int k = 0; k < nodeNum; k++) {
                    int cost = parse(strArr[k]);
                    if (cost > 0) {
                        list.add(new Edge(j, k, cost));
                    }
                }
            }

            // Minimun spanning tree
            Collections.sort(list);

            parent = new int[nodeNum];
            for (int j = 0; j < nodeNum; j++) {
                parent[j] = j;
            }

            System.out.printf("Case %d:\n", i);
            for (int j = 0, size = list.size(); j < size; j++) {
                Edge curr = list.get(j);
                int fromParent = findParent(curr.from);
                int toParent = findParent(curr.to);

                if (fromParent != toParent) {
                    parent[fromParent] = toParent;
                    System.out.printf("%c-%c %d\n", curr.from + 'A', curr.to + 'A', curr.cost);
                }
            }
        }

        in.close();
    }

    static int parse(String str) {
        String newStr = "";
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i) - '0';
            if (c >= 0 && c <= 9) {
                newStr += c;
            }
        }

        return Integer.parseInt(newStr);
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

    public int compareTo(Edge o) {
        return cost - o.cost;
    }
}