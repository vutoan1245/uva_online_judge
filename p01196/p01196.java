import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class p01196 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int blockNum;

        while ((blockNum = in.nextInt()) != 0) {
            Block blockList[] = new Block[blockNum];
            for (int i = 0; i < blockNum; i++) {
                blockList[i] = new Block(in.nextInt(), in.nextInt());
            }
            Arrays.sort(blockList);

            ArrayList<Integer> seq = new ArrayList<Integer>();

            for (int i = 0; i < blockNum; i++) {
                int m = blockList[i].m;
                int pos = binarySearch(seq, m);

                if (pos < 0) {
                    pos = -(pos + 1);
                }
                if (pos >= seq.size()) {
                    seq.add(m);
                } else {
                    seq.set(pos, m);
                }
            }

            System.out.println(seq.size());
        }

        System.out.println("*");

        in.close();
    }

    static int binarySearch(ArrayList<Integer> seq, int num) {
        int len = seq.size(), low = 0, high = len - 1;
        int ans = len;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (seq.get(mid) > num) {
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

}

class Block implements Comparable<Block> {
    int l, m;

    Block(int x, int y) {
        this.l = x;
        this.m = y;
    }

    public int compareTo(Block o) {
        if (l != o.l)
            return l - o.l;
        return m - o.m;
    }
}