import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class p11368 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            int n = in.nextInt();
            Doll[] dolls = new Doll[n];
            for (int i = 0; i < n; i++)
                dolls[i] = new Doll(in.nextInt(), in.nextInt());
            Arrays.sort(dolls);

            ArrayList<Integer> decreasing = new ArrayList<Integer>(n);
            for (int i = 0; i < n; i++) {

                int pos = binarySearch(decreasing, dolls[i].w);

                if (pos >= decreasing.size()) {
                    decreasing.add(dolls[i].w);
                } else {
                    decreasing.set(pos, dolls[i].w);
                }
            }

            System.out.println(decreasing.size());
        }

        in.close();

    }

    static int binarySearch(ArrayList<Integer> decreasing, int w) {
        int result = decreasing.size(), low = 0, high = decreasing.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (decreasing.get(mid) > w) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;

            }
        }
        return result;
    }

    static class Doll implements Comparable<Doll> {
        int w, h;

        Doll(int x, int y) {
            w = x;
            h = y;
        }

        public int compareTo(Doll d) {
            if (h != d.h)
                return d.h - h;
            return w - d.w;
        }
    }
}