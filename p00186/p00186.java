import java.util.*;
import java.io.*;

class p00186 {
    static final int MAX = 100;
    static TreeMap<String, Integer> mapNameToIndex = new TreeMap<String, Integer>();
    static TreeMap<Integer, String> mapIndexToName = new TreeMap<Integer, String>();
    static int matrix[][] = new int[MAX][MAX];
    static int parent[][] = new int[MAX][MAX];
    static String highwayList[][] = new String[MAX][MAX];

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int index = 0;

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                matrix[i][j] = 100000000;
            }
            matrix[i][i] = 0;
        }

        String strArr[];
        while (true) {
            strArr = in.readLine().split(",");
            if (strArr.length == 1)
                break;

            String from = strArr[0];
            String to = strArr[1];
            String highway = strArr[2];
            int distance = Integer.parseInt(strArr[3]);

            if (!mapNameToIndex.containsKey(from)) {
                mapNameToIndex.put(from, index++);
                mapIndexToName.put(index - 1, from);
            }
            if (!mapNameToIndex.containsKey(to)) {
                mapNameToIndex.put(to, index++);
                mapIndexToName.put(index - 1, to);
            }

            int fromIndex = mapNameToIndex.get(from);
            int toIndex = mapNameToIndex.get(to);

            if (distance < matrix[fromIndex][toIndex]) {
                matrix[fromIndex][toIndex] = matrix[toIndex][fromIndex] = distance;
                highwayList[fromIndex][toIndex] = highwayList[toIndex][fromIndex] = highway;
            }
        }

        // Floyd Warshall Algorithm
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                parent[i][j] = i;
            }
        }

        for (int k = 0; k < MAX; k++) {
            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    int sum = matrix[i][k] + matrix[k][j];
                    if (sum < matrix[i][j]) {
                        matrix[i][j] = sum;
                        parent[i][j] = parent[k][j];
                    }
                }
            }
        }

        String line;
        while ((line = in.readLine()) != null) {
            strArr = line.split(",");

            String src = strArr[0];
            String dest = strArr[1];

            if (!mapNameToIndex.containsKey(src)) {
                mapNameToIndex.put(src, index++);
            }

            if (!mapNameToIndex.containsKey(dest)) {
                mapNameToIndex.put(dest, index++);
            }

            int srcIndex = mapNameToIndex.get(src);
            int destIndex = mapNameToIndex.get(dest);

            System.out.print(
                    "\n\nFrom                 To                   Route      Miles\n-------------------- -------------------- ---------- -----\n");
            printRoute(srcIndex, destIndex);
            System.out.print("                                                     -----\n");
            System.out.printf("                                          Total      %5d\n",
                    matrix[srcIndex][destIndex]);
        }

        in.close();
    }

    static void printRoute(int from, int to) {
        if (from == to)
            return;

        printRoute(from, parent[from][to]);

        System.out.printf("%-20s %-20s %-10s %5d\n", mapIndexToName.get(parent[from][to]), mapIndexToName.get(to),
                highwayList[parent[from][to]][to], matrix[parent[from][to]][to]);
    }
}