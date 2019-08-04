import java.util.Scanner;

class p00983 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int size = in.nextInt();
            int subSize = in.nextInt() - 1;

            long matrixSum[][] = new long[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrixSum[i][j] = in.nextInt();

                    if (i > 0)
                        matrixSum[i][j] += matrixSum[i - 1][j];
                    if (j > 0)
                        matrixSum[i][j] += matrixSum[i][j - 1];
                    if (i > 0 && j > 0)
                        matrixSum[i][j] -= matrixSum[i - 1][j - 1];
                }
            }

            // calculate output
            int newSize = size - subSize;
            long blurrMatrix[][] = new long[newSize][newSize];
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    blurrMatrix[i][j] = matrixSum[i + subSize][j + subSize];

                    if (i > 0)
                        blurrMatrix[i][j] -= matrixSum[i - 1][j + subSize];
                    if (j > 0)
                        blurrMatrix[i][j] -= matrixSum[i + subSize][j - 1];
                    if (i > 0 && j > 0)
                        blurrMatrix[i][j] += matrixSum[i - 1][j - 1];
                }
            }

            int sum = 0;
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    System.out.println(blurrMatrix[i][j]);
                    sum += blurrMatrix[i][j];
                }
            }
            System.out.println(sum);

            if (in.hasNext())
                System.out.println();

        }

        in.close();
    }

}