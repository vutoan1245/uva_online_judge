import java.util.*;

class p00587 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseCount = 1;
        while (true) {
            String line = in.nextLine();
            if (line.equals("END")) {
                break;
            }

            line = line.substring(0, line.length() - 1);
            String strArr[] = line.split(",");

            Point curr = new Point(0, 0);
            for (String str : strArr) {
                int steps = 0;
                String direction = "";

                int len = str.length();
                for (int i = 0; i < len; i++) {
                    if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                        steps = Integer.parseInt(str.substring(0, i));
                        direction = str.substring(i);
                        break;
                    }
                }

                Vector vec = findVec(direction).scale(steps);
                curr = curr.translate(vec);
            }

            System.out.printf("Map #%d\n", caseCount++);
            System.out.printf("The treasure is located at (%.3f,%.3f).\n", curr.x, curr.y);
            System.out.printf("The distance to the treasure is %.3f.\n\n", curr.distance(new Point(0, 0)));
        }

        in.close();
    }

    static Vector findVec(String direction) {
        switch (direction) {
        case "E":
            return new Vector(1, 0);
        case "NE":
            return new Vector(1 / Math.sqrt(2), 1 / Math.sqrt(2));
        case "N":
            return new Vector(0, 1);
        case "NW":
            return new Vector(-1 / Math.sqrt(2), 1 / Math.sqrt(2));
        case "W":
            return new Vector(-1, 0);
        case "SW":
            return new Vector(-1 / Math.sqrt(2), -1 / Math.sqrt(2));
        case "S":
            return new Vector(0, -1);
        case "SE":
            return new Vector(1 / Math.sqrt(2), -1 / Math.sqrt(2));
        }

        return new Vector(-1000, -1000);
    }
}

class Vector {
    double x, y;

    Vector(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public Vector scale(double v) {
        return new Vector(x * v, y * v);
    }
}

class Point {
    double x, y;

    Point(double xx, double yy) {
        x = xx;
        y = yy;
    }

    public Point translate(Vector a) {
        return new Point(x + a.x, y + a.y);
    }

    public double distance(Point o) {
        return Math.hypot(x - o.x, y - o.y);
    }
}