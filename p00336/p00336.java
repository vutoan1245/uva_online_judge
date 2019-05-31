import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class p00336{
    static ArrayList<ArrayList<Integer>> edgeList;
    static HashMap<Integer, Integer> mapNumToNode;
    static int count, maxSize, cases;
    static Queue<QueueNode> queue;
    static boolean visited[];
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            int edgeNum = in.nextInt();
            if(edgeNum == 0) break;

            edgeList = new ArrayList<ArrayList<Integer>>(30);
            mapNumToNode = new HashMap<Integer, Integer>();
            count = 0;

            for(int i = 0 ; i < 30; i++){
                edgeList.add(new ArrayList<Integer>(30));
            }

            // Get a adj list of node
            for(int i = 0; i < edgeNum; i++){
                int from = in.nextInt();
                int to = in.nextInt();

                // map node value to index
                if(!mapNumToNode.containsKey(from)){
                    mapNumToNode.put(from, count++);
                }
                int newFrom = mapNumToNode.get(from);
                
                if(!mapNumToNode.containsKey(to)){
                    mapNumToNode.put(to, count++);
                }
                int newTo = mapNumToNode.get(to);

                edgeList.get(newFrom).add(newTo);
                edgeList.get(newTo).add(newFrom);
            }

            // Calculate output
            while(in.hasNext()){
                int startNode = in.nextInt();
                int size = in.nextInt();
                if(startNode == 0 && size == 0) break;

                if(!mapNumToNode.containsKey(startNode)) {
                    mapNumToNode.put(startNode, count++);
                }
                int indexValue = mapNumToNode.get(startNode);

                cases++;
                maxSize = size;
                visited = new boolean[count];

                // Do bfs until max size reach
                queue = new LinkedList<QueueNode>();
                queue.add(new QueueNode(indexValue, 0));
                while(!queue.isEmpty()){
                    bfs(queue.poll());
                }

                // Print output
                int result = 0;
                for(boolean condition: visited){
                    if(!condition) result++;
                }
                System.out.printf("Case %d: %d nodes not reachable from node %d with TTL = %d.\n", cases, result, startNode, size);
            }

        }
        
        in.close();
    }

    static void bfs(QueueNode currentNode){
        if(visited[currentNode.node]) return;
        if(currentNode.size > maxSize) return;
        
        visited[currentNode.node] = true;
        for(int node: edgeList.get(currentNode.node)){
            queue.add(new QueueNode(node, currentNode.size+1));
        }
    }
}

class QueueNode{
    int node;
    int size;

    QueueNode(int node, int size){
        this.node = node;
        this.size = size;
    }
}
