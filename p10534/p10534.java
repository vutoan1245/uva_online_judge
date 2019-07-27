import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class p10534 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {

            // Get input
            int len = in.nextInt();
            int numList[] = new int[len];
            for (int i = 0; i < len; i++) {
                numList[i] = in.nextInt();
            }

            // Calculate output
            ArrayList<Integer> increasing = new ArrayList<Integer>();
            ArrayList<Integer> decreasing = new ArrayList<Integer>();

            int topIndex = 0, bottomIndex = len - 1;
            boolean isTop = true;

            for (int i = 0; i <= len; i++) {
                if (isTop) {
                    int num = numList[topIndex];
                    int pos = Collections.binarySearch(increasing, num);

                    if (pos < 0) {
                        pos = -(pos + 1);
                    }

                    if (pos >= increasing.size()) {
                        increasing.add(num);
                    } else {
                        increasing.set(pos, num);
                    }
                    topIndex++;
                } else {
                    int num = numList[bottomIndex];
                    int pos = Collections.binarySearch(decreasing, num);

                    if (pos < 0) {
                        pos = -(pos + 1);
                    }

                    if (pos >= decreasing.size()) {
                        decreasing.add(num);
                    } else {
                        decreasing.set(pos, num);
                    }
                    bottomIndex--;
                }

                if (increasing.size() < decreasing.size()) {
                    isTop = true;
                } else {
                    isTop = false;
                }

            }

            int len1 = increasing.size();
            int len2 = decreasing.size();
            int result = len1 + len2 - 1;
            if (result % 2 == 0) {
                result--;
            }
            System.out.println(result);
        }

        in.close();
    }
}