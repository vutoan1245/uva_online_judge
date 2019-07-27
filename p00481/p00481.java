import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class p00481 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        ArrayList<Long> numList = new ArrayList<Long>();
        ArrayList<Long> incSub = new ArrayList<Long>();
        int incSubIndex[] = new int[100000];
        int parent[] = new int[100000];

        int count = 0, maxLength = 0, bestEnd = 0;
        while (in.hasNext()) {
            Long num = in.nextLong();
            numList.add(num);

            int pos = Collections.binarySearch(incSub, num);

            if (pos < 0) {
                pos = -(pos + 1);
            }

            if (pos >= incSub.size()) {
                incSub.add(num);
            } else {
                incSub.set(pos, num);
            }

            incSubIndex[pos] = count;
            parent[pos] = pos > 0 ? incSubIndex[pos - 1] : -1;

            if (pos + 1 > maxLength) {
                maxLength = pos + 1;
                bestEnd = count;
            }
            count++;
        }

        int len = incSub.size();

        System.out.println(len);
        System.out.println("-");

        String result = "";
        while (parent[bestEnd] >= 0) {
            result += numList.get(bestEnd);
            bestEnd = parent[bestEnd];
        }

        System.out.print(result);

        in.close();
    }
}