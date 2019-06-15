import java.util.Scanner;

class p10827{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while(caseNum-->0){
            int size = in.nextInt();
            int triple = size * 3;

            int matrixSum[][] = new int[triple][triple];

            // Get input
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    int num = in.nextInt();
                    matrixSum[i+size][j+size] = num;
                    matrixSum[i+size+size][j+size] = num;
                    matrixSum[i+size][j+size+size] = num;
                    matrixSum[i+size+size][j+size+size] = num;
                }
            }

            for(int i = 0; i < triple; i++){
                for(int j = 0; j < triple; j++){
                    if(i > 0) matrixSum[i][j] += matrixSum[i-1][j];
                    if(j > 0) matrixSum[i][j] += matrixSum[i][j-1];
                    if(i > 0 && j > 0) matrixSum[i][j] -= matrixSum[i-1][j-1];
                }
            }


            // Calculate ouput
            int result = Integer.MIN_VALUE;
            for(int i = size; i < triple; i++){
                for(int j = size; j < triple; j++){
                    for(int k = i; k < triple && k < i + size; k++){
                        for(int f = j; f < triple && f < j + size; f++){
                            int current = matrixSum[k][f];

                            if(i > 0) current -= matrixSum[i-1][f];
                            if(j > 0) current -= matrixSum[k][j-1];
                            if(i > 0 && j > 0) current += matrixSum[i-1][j-1];

                            result = Math.max(result, current);
                        }
                    }
                }
            }

            System.out.println(result);
        }

        in.close();
    }
}