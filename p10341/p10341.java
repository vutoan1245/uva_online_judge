import java.util.Scanner;

class p10341 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int p = in.nextInt();
            int q = in.nextInt();
            int r = in.nextInt();
            int s = in.nextInt();
            int t = in.nextInt();
            int u = in.nextInt();

            // Binary Search the answer
            double start = 0, end = 1, mid = 0, value = 0;
            for (int i = 0; i < 50; i++) {
                mid = (start + end) / 2;
                value = p * Math.pow(Math.E, -mid) + q * Math.sin(mid) + r * Math.cos(mid) + s * Math.tan(mid)
                        + t * mid * mid + u;
                if (value < 0) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if (value > 1e-9 || value < -1e-9) {
                System.out.println("No solution");
            } else {
                System.out.printf("%.4f\n", mid);
            }
        }

        in.close();
    }
}