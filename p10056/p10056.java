import java.util.Scanner;

class p10056 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while (caseNum-- > 0) {
            int n = in.nextInt();
            double p = in.nextDouble();
            int i = in.nextInt();

            double ans;
            if (Math.abs(p) < 1e-9) {
                ans = 0.0;
            } else {
                ans = p * Math.pow(1 - p, i - 1) / (1 - Math.pow(1 - p, n));
            }

            System.out.printf("%.4f\n", ans);
        }

        in.close();
    }
}