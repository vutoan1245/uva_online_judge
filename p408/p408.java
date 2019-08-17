import java.util.Scanner;

class p408 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int step = in.nextInt();
            int mod = in.nextInt();

            System.out.printf("%10d%10d    ", step, mod);
            System.out.println(gcd(step, mod) == 1 ? "Good Choice" : "Bad Choice");
            System.out.println();
        }

        in.close();
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}