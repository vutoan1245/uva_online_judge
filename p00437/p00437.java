import java.util.Arrays;
import java.util.Scanner;

class p00437 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int blockNum;

        int caseCount = 1;
        while ((blockNum = in.nextInt()) != 0) {

            // Get input for each case
            int len = blockNum * 3;
            Block blockList[] = new Block[len];
            for (int i = 0; i < blockNum; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int z = in.nextInt();
                blockList[i * 3] = new Block(x, y, z);
                blockList[i * 3 + 1] = new Block(z, x, y);
                blockList[i * 3 + 2] = new Block(y, z, x);
            }

            // Calculate output
            Arrays.sort(blockList);
            int dp[] = new int[len];
            for (int i = 0; i < len; i++) {
                dp[i] = blockList[i].h;
            }

            int result = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    if ((blockList[i].w > blockList[j].w && blockList[i].l > blockList[j].l)) {
                        dp[i] = Math.max(dp[i], dp[j] + blockList[i].h);
                    }
                }
                if (dp[i] > result)
                    result = dp[i];
            }

            System.out.printf("Case %d: maximum height = %d\n", caseCount++, result);
        }

        in.close();
    }
}

class Block implements Comparable<Block> {
    // Width, Length, Height
    int w, l, h;

    Block(int x, int y, int z) {
        this.w = Math.min(x, y);
        this.l = Math.max(x, y);
        this.h = z;
    }

    @Override
    public int compareTo(Block o) {
        return w - o.w;
    }
}