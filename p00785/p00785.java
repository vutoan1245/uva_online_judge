import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class p00785 {
    static int ROW = 32, COL = 82;
    static boolean visited[][] = new boolean[ROW][COL];
    static char matrix[][] = new char[ROW][COL];
    static char borderChar;
    static ArrayList<String> stringList;
    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String line;
        stringList = new ArrayList<String>();
        while((line = in.readLine()) != null){
            if(line.length() > 0 && line.charAt(0) == '_'){
                solve(stringList);
                System.out.println(line);
                stringList = new ArrayList<String>();
                continue;
            }
            stringList.add(line);
        }
    }

    static void solve(ArrayList<String> stringList){

        for(int i = 0; i < ROW; i++){
            Arrays.fill(matrix[i], ' ');
            Arrays.fill(visited[i], false);
        }

        for(int i = 0; i < stringList.size(); i++){
            for(int j = 0; j < stringList.get(i).length(); j++){
                matrix[i][j] = stringList.get(i).charAt(j);
            }
        }
        findBorderChar();

        for(int i = 0; i < stringList.size(); i++){
            for(int j = 0; j < stringList.get(i).length(); j++){
                char currentChar = matrix[i][j];
                if(!visited[i][j] && currentChar != borderChar && currentChar != ' '){
                    floodFill(i, j, currentChar);
                }
            }
        }

        // Print out result
        for(int i = 0; i < stringList.size(); i++){
            for(int j = 0; j < stringList.get(i).length(); j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    static void findBorderChar(){
        for(char arr[] : matrix){
            for(char c : arr){
                if(c != ' '){
                    borderChar = c;
                }
            }
        }
    }

    static void floodFill(int r, int c, char currentChar){
        if(validate(r, c)){
            visited[r][c] = true;
            matrix[r][c] = currentChar;
            floodFill(r+1, c, currentChar);
            floodFill(r-1, c, currentChar);
            floodFill(r, c+1, currentChar);
            floodFill(r, c-1, currentChar);
        }
    }

    static boolean validate(int r, int c){
        if(r >= 0 && r < stringList.size() && c >= 0 && c < stringList.get(r).length()){
            return !visited[r][c] && matrix[r][c] != borderChar;
        }
        return false;
    }
}