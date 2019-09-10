import java.util.*;

class p12578 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while (caseNum-- > 0) {
            int l = in.nextInt();

            double r = 1.0 * l / 5;
            double w = l * 6.0 / 10.0;

            double rectArea = l * w;
            double circleArea = r * r * Math.PI;

            System.out.printf("%.2f %.2f\n", circleArea, rectArea - circleArea);
        }

        in.close();
    }
}