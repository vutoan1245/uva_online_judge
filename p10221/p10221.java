import java.util.*;

class p10221 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int r = 6440 + in.nextInt();
            double angle = in.nextDouble();
            String type = in.next();

            if (type.equals("min")) {
                angle /= 60;
            }
            if (angle > 180) {
                angle = 360 - angle;
            }

            System.out.printf("%.6f %.6f\n", arcLength(r, angle), chordLength(r, angle));

        }

        in.close();
    }

    public static double arcLength(double r, double angle) {
        return (angle / 360) * circ(r);
    }

    public static double chordLength(double r, double angle) {
        return 2 * r * Math.sin(Math.toRadians(angle) / 2.0);
    }

    public static double circ(double r) {
        return r * 2 * Math.PI;
    }
}