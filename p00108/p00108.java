import java.util.Scanner;

class p00108{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            // Get input
            int size = in.nextInt();
            int matrixSum[][] = new int[size][size]; // matrixSum[i][j] contains sum from (0, 0) to [i][j]
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    matrixSum[i][j] = in.nextInt();
                    if(i > 0) matrixSum[i][j] += matrixSum[i-1][j];
                    if(j > 0) matrixSum[i][j] += matrixSum[i][j-1];
                    if(i > 0 && j > 0) matrixSum[i][j] -= matrixSum[i-1][j-1];
                }
            }

            // Calculate output
            int result = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){ // (i, j) start point
                    for(int k = i; k < size; k++){
                        for(int f = j; f < size; f++){ // (k, f) end point

                            int currentSum = matrixSum[k][f]; // currentSum contain sum from (i, j) to (k, f)
                            if(i > 0) currentSum -= matrixSum[i-1][f];
                            if(j > 0) currentSum -= matrixSum[k][j-1];
                            if(i > 0 && j > 0) currentSum += matrixSum[i-1][j-1];

                            result = Math.max(result, currentSum);
                        }
                    }
                }
            }

            System.out.println(result);
        }

        in.close();
    }
}