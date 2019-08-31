import java.util.*;

class p01056 {
    static final int INF = 1000000;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (true) {
            int peopleNum = in.nextInt();
            int routeNum = in.nextInt();
            if (peopleNum == 0 && routeNum == 0) {
                break;
            }

            TreeMap<String, Integer> mapNameToIndex = new TreeMap<String, Integer>();
            int index = 0;

            int matrix[][] = new int[peopleNum][peopleNum];
            for (int i = 0; i < peopleNum; i++) {
                for (int j = 0; j < peopleNum; j++) {
                    matrix[i][j] = INF;
                }
            }

            // Get input
            for (int i = 0; i < routeNum; i++) {
                String from = in.next();
                String to = in.next();

                if (!mapNameToIndex.containsKey(from)) {
                    mapNameToIndex.put(from, index++);
                }
                if (!mapNameToIndex.containsKey(to)) {
                    mapNameToIndex.put(to, index++);
                }

                int fromIndex = mapNameToIndex.get(from);
                int toIndex = mapNameToIndex.get(to);

                matrix[fromIndex][toIndex] = 1;
                matrix[toIndex][fromIndex] = 1;
            }

            // Floyd Warshall Algorithm
            for (int k = 0; k < peopleNum; k++) {
                for (int i = 0; i < peopleNum; i++) {
                    for (int j = 0; j < peopleNum; j++) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }

            // Calcuate output
            int result = 0;
            boolean disconnected = false;
            for (int i = 0; i < peopleNum && !disconnected; i++) {
                for (int j = 0; j < peopleNum; j++) {
                    if (i == j)
                        continue;

                    if (matrix[i][j] == INF) {
                        disconnected = true;
                        break;
                    } else {
                        result = Math.max(result, matrix[i][j]);
                    }
                }
            }

            System.out.printf("Network %d: ", caseCount++);
            System.out.println(disconnected ? "DISCONNECTED" : result);
            System.out.println();
        }

        in.close();
    }
}