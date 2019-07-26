import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class p10131 {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        // Get input
        ArrayList<Elephant> list = new ArrayList<>();
        int index = 1;
        while (in.hasNext()) {
            list.add(new Elephant(in.nextInt(), in.nextInt(), index++));
        }
        Elephant[] elephants = list.toArray(new Elephant[list.size()]);
        Arrays.sort(elephants);

        // Calculate output
        int len = elephants.length;
        int dp[] = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(1, dp[i]);
            for (int j = 0; j < i; j++) {
                if (elephants[i].iq < elephants[j].iq && elephants[i].w > elephants[j].w) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }

        int max = 0;
        for (int i : dp) {
            max = Math.max(i, max);
        }

        System.out.println(max);

        // Find list of elephant
        int result[] = new int[max];
        boolean first = true;
        int last = 0;
        for (int i = dp.length - 1; i >= 0 && max > 0; i--) {
            if (dp[i] == max && (first || last < elephants[i].iq)) {
                last = elephants[i].iq;
                result[--max] = elephants[i].index;
                first = false;
            }
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

        in.close();
    }
}

class Elephant implements Comparable<Elephant> {
    int w, iq, index;

    Elephant(int w, int iq, int index) {
        this.w = w;
        this.iq = iq;
        this.index = index;
    }

    public int compareTo(Elephant e) {
        return this.w - e.w;
    }
}
