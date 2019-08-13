import java.util.Scanner;
import java.util.TreeMap;

class p00436 {
    static final double EPS = 1e-9;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (true) {
            int n = in.nextInt();
            if (n == 0)
                break;

            TreeMap<String, Integer> mapCurrencyToIndex = new TreeMap<String, Integer>();
            int count = 0;
            for (int i = 0; i < n; i++) {
                String currency = in.next();
                mapCurrencyToIndex.put(currency, count++);
            }

            double adjMatrix[][] = new double[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j)
                        adjMatrix[i][j] = 1.0;
                    else
                        adjMatrix[i][j] = -1.0;
                }
            }

            int m = in.nextInt();
            for (int i = 0; i < m; i++) {
                String from = in.next();
                double exchange = in.nextDouble();
                String to = in.next();

                int fromIndex = mapCurrencyToIndex.get(from);
                int toIndex = mapCurrencyToIndex.get(to);

                adjMatrix[fromIndex][toIndex] = exchange;
            }

            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (adjMatrix[i][k] < 0 || adjMatrix[k][j] < 0)
                            continue;

                        adjMatrix[i][j] = Math.max(adjMatrix[i][j], adjMatrix[i][k] * adjMatrix[k][j]);
                    }
                }
            }

            boolean isProfit = false;
            for (int i = 0; i < n; i++) {
                if (adjMatrix[i][i] > 1.0 + EPS)
                    isProfit = true;
            }

            System.out.printf("Case %d: %s\n", caseCount++, isProfit ? "Yes" : "No");
        }

        in.close();
    }
}