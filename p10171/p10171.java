import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class p10171 {
    static final int INF = 26 * 26 * 500;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int routeNum = in.nextInt();
            if (routeNum == 0)
                break;

            int matrix1[][] = new int[26][26];
            int matrix2[][] = new int[26][26];

            for (int i = 0; i < 26; ++i) {
                Arrays.fill(matrix1[i], INF);
                Arrays.fill(matrix2[i], INF);
                matrix1[i][i] = matrix2[i][i] = 0;
            }
            while (routeNum-- > 0) {
                char type = in.next().charAt(0);
                char direction = in.next().charAt(0);
                int from = in.next().charAt(0) - 'A';
                int to = in.next().charAt(0) - 'A';
                int energy = in.nextInt();

                int[][] matrix = type == 'Y' ? matrix1 : matrix2;
                matrix[from][to] = Math.min(matrix[from][to], energy);

                if (direction == 'B')
                    matrix[to][from] = Math.min(matrix[to][from], energy);
            }

            // Floyd
            for (int k = 0; k < 26; ++k) {
                for (int i = 0; i < 26; ++i) {
                    for (int j = 0; j < 26; ++j) {
                        matrix1[i][j] = Math.min(matrix1[i][j], matrix1[i][k] + matrix1[k][j]);
                        matrix2[i][j] = Math.min(matrix2[i][j], matrix2[i][k] + matrix2[k][j]);
                    }
                }
            }

            int src1 = in.next().charAt(0) - 'A';
            int src2 = in.next().charAt(0) - 'A';

            int finalEnergy = INF;
            ArrayList<Character> cityList = new ArrayList<Character>();

            for (int i = 0; i < 26; ++i) {
                int combineEnergy = matrix1[src1][i] + matrix2[src2][i];
                if (combineEnergy < finalEnergy) {
                    finalEnergy = combineEnergy;
                    cityList = new ArrayList<Character>();
                }

                if (combineEnergy < INF && combineEnergy == finalEnergy)
                    cityList.add((char) (i + 'A'));
            }

            if (cityList.size() == 0) {
                System.out.println("You will never meet.");
            } else {
                System.out.print(finalEnergy);
                for (char x : cityList)
                    System.out.print(" " + x);
                System.out.println();
            }
        }

        in.close();
    }
}