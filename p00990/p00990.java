import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class p00990 {
    static int w;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {

            // Get input
            int timeLimit = in.nextInt();
            w = in.nextInt();

            int itemNum = in.nextInt();
            Item itemList[] = new Item[itemNum];
            for (int i = 0; i < itemNum; i++) {
                itemList[i] = new Item(in.nextInt() * w * 3, in.nextInt());
            }

            // Calculate output
            // Dynamic Programing: Knapsack
            int dp[][] = new int[itemNum + 1][timeLimit + 1];
            for (int i = 1; i <= itemNum; i++) {
                Item currItem = itemList[i - 1];
                for (int j = 1; j <= timeLimit; j++) {
                    if (j < currItem.time) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - currItem.time] + currItem.value);
                    }
                }
            }

            // Find capture items
            int totalValue = dp[itemNum][timeLimit];
            int itemCount = 0;
            ArrayList<Integer> itemCapture = new ArrayList<Integer>();

            int currTime = timeLimit;
            for (int i = itemNum; i >= 1; i--) {

                while (i != 0 && dp[i][currTime] != dp[i - 1][currTime]) {
                    currTime -= itemList[i - 1].time;
                    itemCount++;
                    itemCapture.add(i - 1);
                    break;
                }
            }
            Collections.sort(itemCapture);

            // Print result
            System.out.println(totalValue);
            System.out.println(itemCount);
            for (int i = 0; i < itemCapture.size(); i++) {
                int index = itemCapture.get(i);
                System.out.println(itemList[index].time / w / 3 + " " + itemList[index].value);
            }

            if (in.hasNext()) {
                System.out.println();
            }
        }

        in.close();
    }
}

class Item {
    int time, value;

    Item(int time, int value) {
        this.time = time;
        this.value = value;
    }
}