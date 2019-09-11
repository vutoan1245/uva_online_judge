import java.util.*;

class p10209 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            double a = in.nextDouble();

            double section = Math.asin(0.5) / 2 * a * a;
            double tri = Math.sqrt(a * a - a * a / 4) * a / 4;
            double h1 = a - Math.sqrt(a * a - a * a / 4);

            double z = a * h1 - 2 * (section - tri);
            double y = a * a - a * a * Math.PI / 4 - 2 * z;
            double x = a * a - 4 * z - 4 * y;

            System.out.printf("%.3f %.3f %.3f\n", x, 4 * y, 4 * z);
        }

        in.close();
    }
}