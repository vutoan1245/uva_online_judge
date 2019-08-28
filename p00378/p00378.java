import java.util.*;

class p00378 {
    static final double EPS = 1e-9;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        System.out.println("INTERSECTING LINES OUTPUT");
        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            Point p1 = new Point(in.nextDouble(), in.nextDouble());
            Point p2 = new Point(in.nextDouble(), in.nextDouble());
            Line l1 = new Line(p1, p2);

            Point p3 = new Point(in.nextDouble(), in.nextDouble());
            Point p4 = new Point(in.nextDouble(), in.nextDouble());
            Line l2 = new Line(p3, p4);

            if (l1.isSame(l2)) {
                System.out.println("LINE");
            } else if (l1.isParallel(l2)) {
                System.out.println("NONE");
            } else {
                Point intersect = l1.intersect(l2);
                System.out.printf("POINT %.2f %.2f\n", intersect.x, intersect.y);
            }
        }
        System.out.println("END OF OUTPUT");

        in.close();
    }

    static class Point {
        double x, y;

        Point(double xx, double yy) {
            x = xx;
            y = yy;
        }
    }

    static class Line {
        double a, b, c;

        Line(Point first, Point second) {
            if (first.x == second.x) {
                a = 1.0;
                b = 0.0;
                c = -first.x;
            } else {
                a = (double) (first.y - second.y) / (second.x - first.x);
                b = 1.0;
                c = -a * first.x - first.y;
            }
        }

        boolean isParallel(Line o) {
            return Math.abs(a - o.a) <= EPS;
        }

        boolean isSame(Line o) {
            return this.isParallel(o) && Math.abs(c - o.c) <= EPS;
        }

        Point intersect(Line o) {
            if (this.isParallel(o))
                return null;

            double x = (b * o.c - c * o.b) / (a * o.b - b * o.a);
            double y;
            if (Math.abs(b) > EPS)
                y = -(a * x + c);
            else
                y = -(o.a * x + o.c);

            return new Point(x, y);
        }

    }
}