import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class p00459{
    static ArrayList<ArrayList<Integer>> adjList;
    static boolean visited[];
    public static void main(String args[]) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int cases = Integer.parseInt(in.readLine());
        in.readLine();
        for(int i = 0; i < cases; i++){
            int biggestNum = in.readLine().charAt(0) - 'A' + 1;
            adjList = new ArrayList<ArrayList<Integer>>(biggestNum);
            for(int j = 0; j < biggestNum; j++){
                adjList.add(new ArrayList<Integer>(biggestNum));
            }

            String str;
            while((str = in.readLine()) != null && str.length() > 0){
                int first = str.charAt(0) - 'A';
                int second = str.charAt(1) - 'A';
                // System.out.println(first + " " + second);
                adjList.get(first).add(second);
                adjList.get(second).add(first);
            }

            // Calculate output
            visited = new boolean[biggestNum];
            int result = 0;
            for(int j = 0; j < biggestNum; j++){
                if(!visited[j]){
                    dfs(j);
                    result++;
                }
            }

            System.out.println(result);
            if(str != null) System.out.println();
        }
    }

    public static void dfs(int index){
        if(visited[index]) return;
        visited[index] = true;

        for(int node: adjList.get(index)){
            dfs(node);
        }
    }
}