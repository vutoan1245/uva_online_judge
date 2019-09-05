import java.util.*;

class p00920 {
    static final double EPS = 1e-9;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            int n = in.nextInt();

            Point pointList[] = new Point[n];
            for (int i = 0; i < n; i++) {
                pointList[i] = new Point(in.nextInt(), in.nextInt());
            }
            Arrays.sort(pointList);

            double result = 0.0;
            double currX = 0, currY = 0;

            for (int i = 1; i < n; i++) {
                Point point = pointList[i];

                if (point.y > currY) {

                    Line slope = new Line(pointList[i], pointList[i - 1]);
                    Line horizon = new Line(new Point(currX, currY), new Point(currX - 1, currY));

                    Point c = slope.intersect(horizon);

                    result += point.distance(c);

                    currY = point.y;
                    currX = point.x;

                }
            }

            System.out.printf("%.2f\n", result);
        }

        in.close();
    }

    static class Point implements Comparable<Point> {
        double x, y;

        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }

        Point(double xx, double yy) {
            x = xx;
            y = yy;
        }

        double distance(Point o) {
            return Math.hypot(x - o.x, y - o.y);
        }

        public int compareTo(Point o) {
            return Double.compare(o.x, x);
        }
    }

    static class Line {
        double a, b, c;

        Line(Point first, Point second) {
            if (Math.abs(first.x - second.x) < EPS) {
                a = 1.0;
                b = 0;
                c = -first.x;
            } else {
                a = -(second.y - first.y) / (second.x - first.x);
                b = 1.0;
                c = -a * first.x - first.y;
            }
        }

        public boolean isParallel(Line o) {
            return Math.abs(a - o.a) <= EPS && Math.abs(b - o.b) <= EPS;
        }

        public boolean isSame(Line o) {
            return isParallel(o) && Math.abs(c - o.c) <= EPS;
        }

        Point intersect(Line o) {
            if (isParallel(o))
                return null;

            double x = (b * o.c - c * o.b) / (a * o.b - b * o.a);
            double y;
            if (Math.abs(b) > EPS)
                y = -a * x - c;
            else
                y = -o.a * x - o.c;

            return new Point(x, y);
        }
    }
}
