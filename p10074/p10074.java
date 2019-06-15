import java.util.Scanner;

class p10074{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            int rowNum = in.nextInt();
            int colNum = in.nextInt();

            if(rowNum == 0 && colNum == 0) break;

            int matrixSum[][] = new int[rowNum][colNum];

            int NEG_INF = -(100 * 100);
            for(int i = 0; i < rowNum; i++){
                for(int j = 0; j < colNum; j++){
                    matrixSum[i][j] = in.nextInt() != 1 ? 1 : NEG_INF;

                    if(i > 0) matrixSum[i][j] += matrixSum[i-1][j];
                    if(j > 0) matrixSum[i][j] += matrixSum[i][j-1];
                    if(i > 0 && j > 0) matrixSum[i][j] -= matrixSum[i-1][j-1];
                }
            }

            // Calculate output
            int result = 0;
            for(int i = 0; i < rowNum; ++i){
                for(int j = 0; j < colNum; ++j){
                    for(int k = i; k < rowNum; ++k){
                        for(int f = j; f < colNum; ++f){
                            int current = matrixSum[k][f];

                            if (i > 0)
                                current -= matrixSum[i - 1][f];
                            if (j > 0)
                                current -= matrixSum[k][j - 1];
                            if (i > 0 && j > 0)
                                current += matrixSum[i - 1][j - 1];

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