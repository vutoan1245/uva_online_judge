import java.util.Scanner;

class p10130 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {

            // Get input
            int itemNum = in.nextInt();
            Item itemList[] = new Item[itemNum];
            for (int i = 0; i < itemNum; i++) {
                itemList[i] = new Item(in.nextInt(), in.nextInt());
            }

            int peopleNum = in.nextInt();
            int limit[] = new int[peopleNum];
            int max = 0;
            for (int i = 0; i < peopleNum; i++) {
                limit[i] = in.nextInt();
                max = Math.max(max, limit[i]);
            }

            // Calculate all possible answers
            int dp[][] = new int[itemNum + 1][max + 1];

            for (int j = 1; j <= itemNum; j++) {
                Item currItem = itemList[j - 1];
                for (int k = 1; k <= max; k++) {
                    if (k < currItem.weight) {
                        dp[j][k] = dp[j - 1][k];
                    } else {
                        dp[j][k] = Math.max(currItem.value + dp[j - 1][k - currItem.weight], dp[j - 1][k]);
                    }
                }
            }

            int result = 0;
            for (int i = 0; i < peopleNum; i++) {
                result += dp[itemNum][limit[i]];
            }

            System.out.println(result);
        }

        in.close();
    }
}

class Item {
    int value, weight;

    Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }
}