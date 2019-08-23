import java.util.Scanner;
import java.util.ArrayList;

class p11935 {
    static final double ESP = 1e9;
    static ArrayList<Integer> distance;
    static ArrayList<String> type;
    static ArrayList<Integer> value;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Get input
        String strArr[];
        while (true) {
            distance = new ArrayList<Integer>();
            type = new ArrayList<String>();
            value = new ArrayList<Integer>();

            strArr = in.nextLine().split(" ");
            if (strArr[3].equals("0"))
                break;

            distance.add(Integer.parseInt(strArr[0]));
            type.add(strArr[1]);
            value.add(Integer.parseInt(strArr[3]));

            while (true) {
                strArr = in.nextLine().split(" ");

                if (strArr[1].equals("Fuel")) {
                    distance.add(Integer.parseInt(strArr[0]));
                    type.add(strArr[1]);
                    value.add(Integer.parseInt(strArr[3]));
                } else {
                    distance.add(Integer.parseInt(strArr[0]));
                    type.add(strArr[1]);
                    value.add(0);
                }

                if (strArr[1].equals("Goal"))
                    break;
            }

            // Binary Search the answer
            double start = 0, end = 100000, mid, result = 0;

            for (int i = 0; i < 100; i++) {
                mid = (end + start) / 2;
                if (validate(mid)) {
                    result = mid;
                    end = mid;
                } else {
                    start = mid;
                }
            }

            System.out.printf("%.3f\n", result);
        }

        in.close();
    }

    static boolean validate(double tank) {
        int len = distance.size();

        double currTank = tank;
        int currDistance = 0;
        int currFuelConsump = 0;
        int currLeak = 0;

        for (int i = 0; i < len; i++) {
            int travelDist = distance.get(i) - currDistance;
            currTank -= (travelDist / 100.0 * currFuelConsump);
            currTank -= travelDist * currLeak;

            if (currTank <= 0)
                return false;

            switch (type.get(i)) {
            case "Fuel":
                currFuelConsump = value.get(i);
                break;
            case "Gas":
                currTank = tank;
                break;
            case "Mechanic":
                currLeak = 0;
                break;
            case "Leak":
                currLeak++;
                break;
            case "Goal":
                break;
            }

            currDistance += travelDist;
        }

        return currTank <= ESP && currTank >= -ESP;
    }
}