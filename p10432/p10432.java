import java.util.*;

class p10432 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            double r = in.nextDouble();
            double n = in.nextDouble();

            double theta = (360.0 / n);
            double phi = Math.toRadians(90 - theta / 2);

            double a = Math.cos(phi) * r * 2;
            double h = Math.sin(phi) * r;

            double result = a * h * n / 2;

            System.out.printf("%.3f\n", result);
        }

        in.close();
    }
}