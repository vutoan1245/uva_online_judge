import java.io.*;

class p00174 {
    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(in.readLine());
        in.readLine();

        while (caseNum-- > 0) {

            // Get input
            String strArr[] = in.readLine().split(" ");
            int roomNum = Integer.parseInt(strArr[0]);
            int catRoom = Integer.parseInt(strArr[1]) - 1;
            int mouseRoom = Integer.parseInt(strArr[2]) - 1;

            boolean cat[][] = new boolean[roomNum][roomNum];
            boolean mouse[][] = new boolean[roomNum][roomNum];

            for (int i = 0; i < roomNum; i++) {
                cat[i][i] = true;
                mouse[i][i] = true;
            }

            // Get Cat input
            while (true) {
                strArr = in.readLine().split(" ");
                int from = Integer.parseInt(strArr[0]) - 1;
                int to = Integer.parseInt(strArr[1]) - 1;

                if (from < 0 && to < 0)
                    break;

                cat[from][to] = true;
            }

            // Get Mouse input
            String line;
            while ((line = in.readLine()) != null) {
                strArr = line.split(" ");
                if (strArr.length == 1)
                    break;

                int from = Integer.parseInt(strArr[0]) - 1;
                int to = Integer.parseInt(strArr[1]) - 1;

                mouse[from][to] = true;
            }

            // Floyd Warshall Algorithm
            for (int k = 0; k < roomNum; k++) {
                for (int i = 0; i < roomNum; i++) {
                    for (int j = 0; j < roomNum; j++) {
                        cat[i][j] = cat[i][j] || (cat[i][k] && cat[k][j]);
                        mouse[i][j] = mouse[i][j] || (mouse[i][k] && mouse[k][j]);
                    }
                }
            }

            // Find all rooms that cat can access
            boolean catCan[] = new boolean[roomNum];
            for (int i = 0; i < roomNum; i++) {
                if (cat[catRoom][i]) {
                    catCan[i] = true;
                }
            }

            // Calcuate output
            boolean canMeet = false, notMeetCat = false;

            for (int i = 0; i < roomNum; i++) {
                if (cat[catRoom][i] && mouse[mouseRoom][i]) {
                    canMeet = true;
                }
            }

            for (int i = 0; i < roomNum; i++) {
                if (i == mouseRoom)
                    continue;
                if (!catCan[i] && !catCan[mouseRoom] && mouse[mouseRoom][i] && mouse[i][mouseRoom]) {
                    notMeetCat = true;
                }
            }

            System.out.println((canMeet ? "Y" : "N") + " " + (notMeetCat ? "Y" : "N"));
            if (caseNum != 0)
                System.out.println();
        }
    }
}