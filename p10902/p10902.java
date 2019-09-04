import java.util.ArrayList;
import java.util.Scanner;

class p10902 {
    static final double EPS = 1e-9;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (true) {
            int n = in.nextInt();
            if (n == 0)
                break;

            ArrayList<Line> lineList = new ArrayList<Line>(n);
            for (int i = 0; i < n; i++) {
                Point first = new Point(in.nextDouble(), in.nextDouble());
                Point second = new Point(in.nextDouble(), in.nextDouble());

                lineList.add(new Line(first, second));
            }

            for (int i = 0; i < n; i++) {
                Line l = lineList.get(i);
                for (int j = i + 1; j < n; j++) {
                    if (l.isOverlap(lineList.get(j))) {
                        l.isTop = false;
                        break;
                    }
                }
            }

            System.out.print("Top sticks:");
            boolean isFirst = true;
            for (int i = 0; i < n; i++) {

                if (lineList.get(i).isTop) {
                    if (isFirst) {
                        System.out.print(" ");
                        isFirst = false;
                    } else {
                        System.out.print(", ");
                    }

                    System.out.print(i + 1);

                }
            }
            System.out.println(".");

        }

        in.close();
    }

    static class Point {
        double x, y;

        Point(double xx, double yy) {
            x = xx;
            y = yy;
        }

        boolean isBetween(Point a, Point b) {
            return x <= Math.max(a.x, b.x) + EPS && x + EPS >= Math.min(a.x, b.x) && y + EPS >= Math.min(a.y, b.y)
                    && y <= Math.max(a.y, b.y) + EPS;
        }
    }

    static class Line {
        Point first, second;
        double a, b, c;
        boolean isTop = true;

        Line(Point first, Point second) {
            if (Math.abs(first.x - second.x) < EPS) {
                a = 1.0;
                b = 0.0;
                c = -first.x;
            } else {
                a = (first.y - second.y) / (second.x - first.x);
                b = 1.0;
                c = -first.x * a - first.y;
            }

            this.first = first;
            this.second = second;
        }

        boolean isParallel(Line o) {
            return Math.abs(a - o.a) < EPS && Math.abs(b - o.b) < EPS;
        }

        boolean isSame(Line o) {
            return isParallel(o) && Math.abs(c - o.c) < EPS;
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

        public boolean isOverlap(Line o) {
            if (isSame(o)) {
                return first.isBetween(o.first, o.second) || second.isBetween(o.first, o.second)
                        || o.first.isBetween(first, second) || o.second.isBetween(first, second);
            }

            if (isParallel(o)) {
                return false;
            }

            Point intersect = this.intersect(o);

            return intersect.isBetween(first, second) && intersect.isBetween(o.first, o.second);
        }
    }
}
