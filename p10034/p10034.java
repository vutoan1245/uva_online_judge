import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class p10034{
    static ArrayList<Edge> edgeList;
    static int parent[];
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();
        for(int i = 0; i < cases; i++){
            int nodes = in.nextInt();
            Node nodeList[] = new Node[nodes];
            for(int j = 0; j < nodes; j++){
                nodeList[j] = new Node(in.nextDouble(), in.nextDouble());
            }

            // Get all edges
            edgeList = new ArrayList<Edge>();
            for(int j = 0; j < nodes; j++){
                for(int k = j+1; k < nodes; k++){
                    double cost = calCost(nodeList[j], nodeList[k]);
                    edgeList.add(new Edge(j, k, cost));
                }
            }

            // Kruskal's algorithm
            parent = new int[nodes];
            for(int j = 0; j < nodes; j++){
                parent[j] = j;
            }
            double cost = 0;
            int len = edgeList.size();
            Collections.sort(edgeList);
            for(int j = 0; j < len; j++){
                int fromParent = findParent(edgeList.get(j).from);
                int toParent = findParent(edgeList.get(j).to);

                if(fromParent != toParent){
                    parent[fromParent] = toParent;
                    cost += edgeList.get(j).cost;
                
                }
            }

            // Print result
            System.out.printf("%.2f\n", cost);
            if(i != cases-1) System.out.println();
        }

        in.close();
    }

    static int findParent(int index){
        if(parent[index] == index) return index;
        return parent[index] = findParent(parent[index]);
    }

    static double calCost(Node first, Node second){
        return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
    }
}

class Edge implements Comparable<Edge> {
    int from;
    int to;
    double cost;

    Edge(int from, int to, double cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.cost, other.cost);
    }
}

class Node {
    double x;
    double y;

    Node(double x, double y){
        this.x = x;
        this.y = y;
    }
}