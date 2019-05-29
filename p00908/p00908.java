import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class p00908{
    static ArrayList<Edge> edges;
    static int parent[];
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            int computers = in.nextInt();

            // Get original cost
            int originalCost = 0;
            for(int i = 1; i < computers; i++){
                in.nextInt();in.nextInt();
                originalCost += in.nextInt();
            }

            // Get list of connection
            int newLines = in.nextInt();
            edges = new ArrayList<Edge>();
            for(int i = 0; i < newLines; i++){
                edges.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
            }
            int oldLines = in.nextInt();
            for(int i = 0; i < oldLines; i++){
                edges.add(new Edge(in.nextInt(), in.nextInt(), in.nextInt()));
            }

            //Calculate output
            Collections.sort(edges);
            parent = new int[computers];
            for(int i = 0; i < computers; i++){
                parent[i] = i;
            }
            // Kruskal's algorithm
            int newCost = 0;
            int len = edges.size();
            for(int i = 0; i < len; i++){
                int fromParent = findParent(edges.get(i).from);
                int toParent = findParent(edges.get(i).to);

                if(fromParent != toParent){
                    parent[fromParent] = toParent;
                    newCost += edges.get(i).cost;
                }
            }

            // Print result
            System.out.println(originalCost);
            System.out.println(newCost);
            if(in.hasNext()) System.out.println();
        }

        in.close();
    }

    static void union(int first, int second){
        int parentFirst = findParent(first);
        int parentSecond = findParent(second);

        parent[parentFirst] = parentSecond;
    }

    static int findParent(int index){
        if(parent[index] == index) return index;
        return parent[index] = findParent(parent[index]);
    }
}

class Edge implements Comparable<Edge>{
    int from;
    int to;
    int cost;

    Edge(int from, int to, int cost){
        this.from = from - 1;
        this.to = to - 1;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);
    }
}