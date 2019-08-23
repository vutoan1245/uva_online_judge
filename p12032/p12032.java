import java.util.Scanner;

class p12032 {
    static int ringNum, ringList[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        for (int i = 1; i <= caseNum; i++) {
            ringNum = in.nextInt();
            ringList = new int[ringNum + 1];
            ringList[0] = 0;
            for (int j = 1; j <= ringNum; j++) {
                ringList[j] = in.nextInt();
            }

            // Binary Search the Answer
            int start = 1, end = 10000000, mid, result = 0;

            while (end >= start) {
                mid = (end + start) / 2;

                if (validate(mid)) {
                    result = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }

            }

            System.out.printf("Case %d: %d\n", i, result);
        }

        in.close();
    }

    static boolean validate(int n) {
        for (int i = 1; i <= ringNum; i++) {
            int diff = ringList[i] - ringList[i - 1];
            if (diff > n)
                return false;
            if (diff == n) {
                n--;
            }
        }

        return true;
    }
}