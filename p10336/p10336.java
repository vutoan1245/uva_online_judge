import java.util.Arrays;
import java.util.Scanner;

class p10336{
    static int row, col, currVisit,graph[][];
    static boolean visited[][];
    static CharNode[] currNode;
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();
        for(int i = 0; i < cases; i++){
            row = in.nextInt();
            col = in.nextInt();

            // Make a 2D graph representation of map
            graph = new int[row][col];
            for(int j = 0; j < row; j++){
                String line = in.next();
                for(int k = 0; k < col; k++){
                    // Convert character to number
                    graph[j][k] = line.charAt(k) - 'a';
                }
            }


            // Calculate output
            visited = new boolean[row][col];
            currNode = new CharNode[26];
            for(int j = 0; j < 26; j++){
                currNode[j] = new CharNode((char)(j+'a'));
            }
            
            for(int j = 0; j < row; j++){
                for(int k = 0; k < col; k++){
                    if(visited[j][k] == false){
                        currVisit = graph[j][k];
                        dfs(j, k);
                        currNode[graph[j][k]].value++;
                    }
                }
            }
            Arrays.sort(currNode);


            // Print output
            System.out.printf("World #%d\n", i+1);
            for(int j = 0; j < 26; j++){
                if(currNode[j].value == 0) break;
                System.out.println(currNode[j].character + ": " + currNode[j].value);
            }

        }
        in.close();
    }

    static void dfs(int rowPos, int colPos){
        if(visited[rowPos][colPos]) return;
        visited[rowPos][colPos] = true;

        if(checkInput(rowPos+1, colPos))
            dfs(rowPos+1, colPos);

        if(checkInput(rowPos-1, colPos))
            dfs(rowPos-1, colPos);

        if(checkInput(rowPos, colPos+1))
            dfs(rowPos, colPos+1);  

        if(checkInput(rowPos, colPos-1))
            dfs(rowPos, colPos-1);

    }

    static boolean checkInput(int rowPos, int colPos){
        return rowPos >= 0 && rowPos < row && colPos >= 0 && colPos < col && graph[rowPos][colPos] == currVisit;
    }

}

class CharNode implements Comparable<CharNode>{
    char character;
    public int value;

    CharNode(char character){
        this.character = character;
        this.value = 0;
    }

    @Override
    public int compareTo(CharNode other) {
        return Integer.compare(other.value, this.value);
    }
}