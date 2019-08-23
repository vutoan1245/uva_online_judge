import java.util.Scanner;

class p11413 {
    static int containerNum, vesselNum;
    static int containerList[];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            containerNum = in.nextInt();
            vesselNum = in.nextInt();
            containerList = new int[containerNum];
            for (int i = 0; i < containerNum; i++) {
                containerList[i] = in.nextInt();
            }

            // Binary Search the answer
            int start = 0, end = 1000000000, mid;
            int result = 0;

            while (end >= start) {
                mid = (end + start) / 2;

                if (validate(mid)) {
                    result = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            System.out.println(result);
        }

        in.close();
    }

    static boolean validate(int capacity) {
        int sum = 0, count = 0;

        for (int i = 0; i < containerNum; i++) {
            if (containerList[i] > capacity)
                return false;
            if (sum + containerList[i] > capacity) {
                count++;
                sum = containerList[i];
            } else {
                sum += containerList[i];
            }
        }

        if (sum != 0)
            count++;

        return count <= vesselNum;
    }
}