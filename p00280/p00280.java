import java.util.ArrayList;
import java.util.Scanner;

class p00280 {
    static int vertexes, currentVertex;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean visited[], path[][];

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            vertexes = in.nextInt();
            if(vertexes == 0) break;

            // Get adj list for each graph
            graph = new ArrayList<ArrayList<Integer>>(vertexes);
            for(int i = 0; i < vertexes; i++){
                graph.add(new ArrayList<Integer>(vertexes));
            }
            while(in.hasNext()){
                int startVertex = in.nextInt();
                if(startVertex == 0) break;
    
                while(in.hasNext()){
                    int endVertex = in.nextInt();
                    if(endVertex == 0) break;
                    graph.get(startVertex-1).add(endVertex-1);
                }
            }

            // Find all possible link between two vertexes
            path = new boolean[vertexes][vertexes];
            for(int i = 0; i < vertexes; i++){
                visited = new boolean[vertexes];
                currentVertex = i;
                dfs(i);
            }
    
            int testVertexes = in.nextInt();
            for(int i = 0; i < testVertexes; i++){
                int testVertex = in.nextInt();
                
                // Find result
                int count = 0;
                ArrayList<Integer> results = new ArrayList<Integer>(vertexes);
                for(int j = 0; j < vertexes; j++){
                    if(path[testVertex-1][j] == false){
                        results.add(j+1);
                        count++;
                    }
                }
    
                // Print result
                System.out.print(count);
                for(int result: results){
                    System.out.print(" " + result);
                }
                System.out.println();
            }
        }
       

        in.close();
    }

    public static void dfs(int startVertex){
        if(visited[startVertex]) return;
        visited[startVertex] = true;

        for(int vertex: graph.get(startVertex)){
            path[currentVertex][vertex] = true;
            dfs(vertex);
        }
    }
}