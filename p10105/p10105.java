import java.util.Scanner;

class p10105 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int comb[][] = new int[14][14];
        for (int i = 0; i < 14; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }

        while (in.hasNext()) {
            int n = in.nextInt();
            int k = in.nextInt();

            int result = 1;
            for (int i = 0; i < k; i++) {
                int p = in.nextInt();
                result *= comb[n][p];
                n -= p;
            }

            System.out.println(result);
        }

        in.close();
    }
}