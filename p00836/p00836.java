import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class p00836 {
    public static void main(String args[]) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());
        in.readLine();
        String line;
        int MINUS_INF = -(25 * 25);

        while(caseNum-->0){
            line = in.readLine();
            int len = line.length();
            int matrixSum[][] = new int[len][len];

            for(int i = 0; i < len; i++){
                for(int j = 0; j < len; j++){
                    matrixSum[i][j] = (line.charAt(j) == '0') ? MINUS_INF : 1;

                    if(i > 0) matrixSum[i][j] += matrixSum[i-1][j];
                    if(j > 0) matrixSum[i][j] += matrixSum[i][j-1];
                    if(i > 0 && j > 0) matrixSum[i][j] -= matrixSum[i-1][j-1];
                }
                line = in.readLine();
            }

            // Calculate output
            int result = 0;

            for (int i = 0; i < len; ++i)
                for (int j = 0; j < len; ++j)
                    for (int k = i; k < len; ++k)
                        for (int f = j; f < len; ++f)
                        {
                            int current = matrixSum[k][f];
                            if (i > 0)
                                current -= matrixSum[i - 1][f];
                            if (j > 0)
                                current -= matrixSum[k][j - 1];
                            if (i > 0 && j > 0)
                                current += matrixSum[i - 1][j - 1];
                            result = Math.max(result, current);
                        }

            System.out.println(result);
            if(caseNum != 0) System.out.println();
        }
    }
}