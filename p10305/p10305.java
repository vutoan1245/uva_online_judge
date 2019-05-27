import java.util.ArrayList;
import java.util.Scanner;

class p10305{
    static ArrayList<ArrayList<Integer>> adjList;
    static ArrayList<Integer> stack;
    static boolean visited[];
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        while(true){
            // Get input
            int nodes = in.nextInt();
            int edges = in.nextInt();
            if(nodes == 0 && edges == 0) break;

            // Crate an adj list representation
            adjList = new ArrayList<ArrayList<Integer>>(nodes);
            for(int i = 0; i < nodes; i++){
                adjList.add(new ArrayList<Integer>(nodes));
            }
            for(int i = 0; i < edges; i++){
                int startNode = in.nextInt() - 1;
                int endNode = in.nextInt() - 1;
                adjList.get(startNode).add(endNode);
            }

            // Calculate output
            visited = new boolean[nodes];
            stack = new ArrayList<Integer>(nodes);
            for(int i = 0; i < nodes; i++){
                // Topological Sort
                if(!visited[i]){
                    dfs(i);
                }
            }

            String result = "";
            for(int node: stack){
                result = node + 1 + " " + result;
            }
        
            System.out.println(result.substring(0, result.length() - 1));
        }

        in.close();
    }

    static void dfs(int index){
        if(visited[index]) return;
        visited[index] = true;

        for(int node: adjList.get(index)){
            dfs(node);
        }
        stack.add(index);
    }
}