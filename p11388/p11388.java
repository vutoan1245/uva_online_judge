import java.util.Scanner;

class p11388 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();

        while (caseNum-- > 0) {
            int g = in.nextInt();
            int l = in.nextInt();

            if (l % g != 0)
                System.out.println(-1);
            else
                System.out.println(g + " " + l);
        }

        in.close();
    }

}